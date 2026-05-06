/*********************************************************************
 *  Indirect priority queue.
 *
 *  The priority queue maintains its own copy of the priorities,
 *  unlike the one in Algorithms in Java.
 *
 *  This code is from "Algorithms in Java, Third Edition,
 *  by Robert Sedgewick, Addison-Wesley, 2003.

 *********************************************************************/
/*********************************************************************
 *  Optimized Indirect Priority Queue using D-ary Heap (D = 4)
 *
 *  Changes from original:
 *  - Switched from binary heap to 4-ary heap for improved performance.
 *  - Heap is now 0-indexed instead of 1-indexed.
 *  - Adjusted fixUp and fixDown accordingly.
 *  
 *  Lazy Initialization of the Priority Queue
 *  - Added contains() to check if element k is in the priority queue.
 *********************************************************************/

public class IndexPQOptimized {
    private static final int D = 4;     // Degree of the D-ary heap (4-ary)
    private int N;                      // Number of elements in PQ
    private int[] pq;                   // Heap: position -> element
    private int[] qp;                   // Inverse: element -> position
    private double[] priority;          // Priority values

    public IndexPQOptimized(int maxN) {
        pq = new int[maxN + 1];         // Heap array (0-indexed)
        qp = new int[maxN + 1];         // Inverse map
        priority = new double[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;  // Initialize as not in PQ
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // Check if element k is in the priority queue
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    // Insert element k with priority value
    public void insert(int k, double value) {
        qp[k] = N;
        pq[N] = k;
        priority[k] = value;
        fixUp(N++);  // Restore heap invariant
    }

    // Delete and return element with minimum priority
    public int delMin() {
        int min = pq[0];
        exch(0, --N);     // Move last to root and shrink heap
        fixDown(0);       // Restore heap
        qp[min] = -1;     // Mark as removed
        pq[N] = -1;       // Optional: clear slot
        return min;
    }

    // Change priority of element k to new value
    public void change(int k, double value) {
        double oldValue = priority[k];
        priority[k] = value;
        if (value < oldValue) fixUp(qp[k]);
        else fixDown(qp[k]);
    }

    /***************** Heap Helper Methods *****************/

    // Check if priority of i is greater than j
    private boolean greater(int i, int j) {
        return priority[pq[i]] > priority[pq[j]];
    }

    // Exchange elements in heap and update inverse map
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    /*
    private void fixUp(int[] a, int k)  {
        while (k > 1 && greater(a[k/2], a[k])) {
            exch(a[k], a[k/2]);
            k = k/2;
        }
    }

    private void fixDown(int[] a, int k, int N) {
        int j;
        while (2*k <= N) {
            j = 2*k;
            if (j < N && greater(a[j], a[j+1])) j++;
            if (!greater(a[k], a[j])) break;
            exch(a[k], a[j]);
            k = j;
        }
    }
    */

    // Fix up in a 4-ary heap
    private void fixUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / D;
            if (!greater(parent, i)) break;
            exch(i, parent);
            i = parent;
        }
    }

    // Fix down in a 4-ary heap
    private void fixDown(int i) {
        while (true) {
            int min = i;
            for (int j = 1; j <= D; j++) {
                int child = D * i + j;
                if (child < N && greater(min, child)) {
                    min = child;
                }
            }
            if (min == i) break;
            exch(i, min);
            i = min;
        }
    }
}
