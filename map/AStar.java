/*************************************************************************
 *  A* Search algorithm (optimized).
 *  Uses Euclidean distance as heuristic in a Euclidean graph.
 *  Optimizations:
 *    - Lazy initialization of the priority queue
 *    - Early termination when destination is reached
 *    - Efficient priority updates using IndexPQ
 *************************************************************************/

import java.awt.Color;

public class AStar {
    private static final double INFINITY = Double.MAX_VALUE;
    private static final double EPSILON = 1e-6; // For floating-point comparison

    private EuclideanGraph G;
    private double[] gScore;   // Cost from start to current node
    private double[] fScore;   // Estimated cost from start to goal through current
    private int[] pred;        // Predecessor array to reconstruct path

    public AStar(EuclideanGraph G) {
        this.G = G;
    }

    // Heuristic: straight-line distance from node to goal
    private double heuristic(int v, int goal) {
        return G.distance(v, goal);
    }
    
 // Returns shortest distance from s to d using A*
    public void compute(int s, int d) {
        aStar(s, d);       
    }

    // Returns shortest distance from s to d using A*
    public double distance(int s, int d) {
        aStar(s, d);
        return gScore[d];
    }

    // Prints shortest path from destination to source
    public void showPath(int d, int s) {
        aStar(s, d);
        if (pred[d] == -1) {
            System.out.println(d + " is unreachable from " + s);
            return;
        }
        for (int v = d; v != s; v = pred[v])
            System.out.print(v + "-");
        System.out.println(s);
    }

    // Visually renders shortest path from source to destination
    public void drawPath(int s, int d) {
        aStar(s, d);
        if (pred[d] == -1) return;
        Turtle.setColor(Color.blue);
        for (int v = d; v != s; v = pred[v])
            G.point(v).drawTo(G.point(pred[v]));
        Turtle.render();
    }

    // Core A* logic
    private void aStar(int s, int d) {
        int V = G.V();  // Number of vertices
        gScore = new double[V];
        fScore = new double[V];
        pred = new int[V];

        // Initialize all distances as infinite and predecessors as -1
        for (int v = 0; v < V; v++) {
            gScore[v] = INFINITY;
            fScore[v] = INFINITY;
            pred[v] = -1;
        }

        // Source node initialization
        gScore[s] = 0.0;        
        pred[s] = s;
        fScore[s] = heuristic(s, d);

        // Optimized priority queue
        IndexPQOptimized pq = new IndexPQOptimized(V);
        pq.insert(s, fScore[s]);

        while (!pq.isEmpty()) {
            int v = pq.delMin();

            // Early termination: destination reached
            if (v == d) break;

            IntIterator i = G.neighbors(v);
            while (i.hasNext()) {
                int w = i.next();
                double tentativeG = gScore[v] + G.distance(v, w);

                // Relaxation step
                if (tentativeG < gScore[w] - EPSILON) {
                    gScore[w] = tentativeG;
                    fScore[w] = tentativeG + heuristic(w, d);
                    pred[w] = v;

                    // Update or insert into priority queue
                    if (pq.contains(w)) {
                        pq.change(w, fScore[w]);
                    } else {
                        pq.insert(w, fScore[w]);
                    }
                }
            }
        }
    }
}
