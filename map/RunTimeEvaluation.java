import java.util.ArrayList;

/*************************************************************************
 *  RunTimeEvaluation.java
 *  
 *  This program:
 *    - Loads a Euclidean graph from a file
 *    - Loads multiple sets of queries (source-destination pairs)
 *    - Runs three algorithms: Basic Dijkstra, Optimized Dijkstra, and A*
 *    - Measures and prints the total execution time for each
 *************************************************************************/

public class RunTimeEvaluation {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java RunTimeEvaluation <graphFile> <queryFile1> [<queryFile2> ...]");
            return;
        }

        // Read in the graph from the first argument (graph file)
        In graphIn = new In(args[0]);
        EuclideanGraph G = new EuclideanGraph(graphIn);
        System.err.println("Done reading the graph: " + args[0]);

        // Loop over each queries file provided
        for (int i = 1; i < args.length; i++) {
        	 In queriesFile = new In(args[i]);
            System.err.println("Done reading the queries: " + args[i]);

            // Initialize algorithms
            Dijkstra dijkstraBasic = new Dijkstra(G);
            DijkstraOptimized dijkstraOptimized = new DijkstraOptimized(G);
            AStar aStar = new AStar(G);

            // Read source-destination (s-d) pairs from the queries file
            int ex =1;        
	        ArrayList<int[]> queries = new ArrayList<>();
	        String s ;
	        String d ;
	        
	        while (ex!=-1) {
	        	s = queriesFile.readString();
	            d = queriesFile.readString();
	            if (s != null ) {
	            	queries.add(new int[]{Integer.parseInt(s), Integer.parseInt(d)});	
	            } 
	            else ex=-1;        	
	        } 

            // --- Basic Dijkstra ---
            System.out.println("Running Basic Dijkstra...");
            long dijkstraBasicStart = System.nanoTime();
            for (int[] query : queries) {
                dijkstraBasic.compute(query[0], query[1]);
            }
            long dijkstraBasicEnd = System.nanoTime();
            double dijkstraBasicTimeMillis = (dijkstraBasicEnd - dijkstraBasicStart) / 1_000_000.0;
            System.out.printf("Dijkstra Basic total execution time: %.3f ms\n", dijkstraBasicTimeMillis);
            System.out.println();

            // --- Optimized Dijkstra ---
            System.out.println("Running Optimized Dijkstra...");
            long dijkstraOptStart = System.nanoTime();
            for (int[] query : queries) {
                dijkstraOptimized.compute(query[0], query[1]);
            }
            long dijkstraOptEnd = System.nanoTime();
            double dijkstraOptTimeMillis = (dijkstraOptEnd - dijkstraOptStart) / 1_000_000.0;
            System.out.printf("Dijkstra Optimized total execution time: %.3f ms\n", dijkstraOptTimeMillis);
            System.out.println();

            // --- A* Search ---
            System.out.println("Running A*...");
            long aStarStart = System.nanoTime();
            for (int[] query : queries) {
                aStar.compute(query[0], query[1]);
            }
            long aStarEnd = System.nanoTime();
            double aStarTimeMillis = (aStarEnd - aStarStart) / 1_000_000.0;
            System.out.printf("A* total execution time: %.3f ms\n", aStarTimeMillis);
            System.out.println();
        }
    }
}
