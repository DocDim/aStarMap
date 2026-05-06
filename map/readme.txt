/******************************************************************************
 *  readme.txt template                                                   
 *  Map
 *****************************************************************************/

Name(s):   Miltiade D. Tchifou    
Login(s):      
Precept #:  
OS:   Windows 11       
Compiler:   
Editor:     
Hours:      


/******************************************************************************
 *  Explain your overall approach.
 *****************************************************************************/
 I implement the following optimization:
 - Lazy initialization of the priority queue
 - Early termination when destination is reached
 - Efficient priority updates using IndexPQ (Switched from binary heap to 4-ary heap for improved performance.
 Heap is now 0-indexed instead of 1-indexed.)
 - Uses Euclidean distance as heuristic in a Euclidean graph.

/******************************************************************************
 *  Which input files did you use to test your program? Mark the
 *  ones where your answers agreed with our reference solutions and
 *  the ones where it disagreed. How long (in seconds) did your program
 *  take to solve each instance? How many vertices did it examine
 *  on average per shortest path query?
 *****************************************************************************/

Input file               basic Running Time (ms)     optimized Running Time (ms)    A* (ms)    Agreed?
-----------------------------------------------------------------------------------------------------
usa-10.txt				291.810						 81.620 						23.710		Y
usa-100short.txt		2060.307					 75.216							71.241		Y
usa-100long.txt			2051.606					 676.769						207.481		Y


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

/******************************************************************************
 *  List whatever help (if any) that you received.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
