/*************************************************************************
 *  Dijkstra's algorithm.
 *  Optimized: lazy initialization of priority queue and early termination.
 *************************************************************************/

import java.awt.Color;

public class DijkstraOptimized {
    private static double INFINITY = Double.MAX_VALUE;
    private static double EPSILON  = 0.000001;

    private EuclideanGraph G;
    private double[] dist;
    private int[] pred;

    public DijkstraOptimized(EuclideanGraph G) {
        this.G = G;
    }
    
    public void compute(int s, int d) {
        dijkstra(s, d);
    }

    // Return shortest path distance from s to d
    public double distance(int s, int d) {
        dijkstra(s, d);
        return dist[d];
    }

    // Print shortest path from s to d (reversed order)
    public void showPath(int d, int s) {
        dijkstra(s, d);
        if (pred[d] == -1) {
            System.out.println(d + " is unreachable from " + s);
            return;
        }
        for (int v = d; v != s; v = pred[v])
            System.out.print(v + "-");
        System.out.println(s);
    }

    // Plot shortest path from s to d
    public void drawPath(int s, int d) {
        dijkstra(s, d);
        if (pred[d] == -1) return;
        Turtle.setColor(Color.red);
        for (int v = d; v != s; v = pred[v])
            G.point(v).drawTo(G.point(pred[v]));
        Turtle.render();
    }

    // Dijkstra's algorithm with lazy priority queue initialization and early exit
    private void dijkstra(int s, int d) {
        int V = G.V();

        dist = new double[V];
        pred = new int[V];

        // Optimization: combined initialization loop for dist and pred
        for (int v = 0; v < V; v++) {
            dist[v] = INFINITY; 
            pred[v] = -1;
        }

        // Optimization: Lazy initialization of priority queue
        IndexPQOptimized pq = new IndexPQOptimized(V); // Use updated IndexPQ (e.g., 4-ary heap)
        dist[s] = 0.0;
        pred[s] = s;
        pq.insert(s, dist[s]);

        while (!pq.isEmpty()) {
            int v = pq.delMin();

            // Optimization: Early exit if destination node reached
            if (v == d) break;

            IntIterator i = G.neighbors(v);
            while (i.hasNext()) {
                int w = i.next();
                double newDist = dist[v] + G.distance(v, w);

                if (newDist < dist[w] - EPSILON) {
                    dist[w] = newDist;
                    pred[w] = v;

                    // Optimization: Avoid inserting duplicate nodes in the PQ
                    if (pq.contains(w)) {
                        pq.change(w, newDist); // Decrease priority
                    } else {
                        pq.insert(w, newDist); // Insert new node
                    }
                }
            }
        }
    }
}
