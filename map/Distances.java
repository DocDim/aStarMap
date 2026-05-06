/*************************************************************************
 *  Compilation:  javac Distances.java
 *  Execution:    java Distances file < input.txt
 *  Dependencies: EuclideanGraph.java Dijkstra.java In.java StdIn.java
 *
 *  Reads in a map from a file, and repeatedly reads in two integers s
 *  and d from standard input, and prints the distance of the shortest
 *  path from s to d to standard output.
 *
 ****************************************************************************/


public class Distances {

    public static void main(String[] args) {

        // read in the graph from a file
        In graphin = new In(args[0]);
        EuclideanGraph G = new EuclideanGraph(graphin);
        DijkstraOptimized dijkstra = new DijkstraOptimized(G);
        AStar aStar = new AStar(G);
        System.err.println("Done reading the graph " + args[0]);
        System.err.println("Enter query pairs from stdin");

        // read in the s-d pairs from standard input
        while(!StdIn.isEmpty()) {
            int s = StdIn.readInt();
            int d = StdIn.readInt();
            // ----- Dijkstra section -----
            System.out.println("Running Dijkstra...");  
            System.out.println("Distance = " + dijkstra.distance(s, d));
            
            // ----- A* section -----
            System.out.println("Running A*...");  
            System.out.println("Distance = " + aStar.distance(s, d));
        }
     
    }
}
