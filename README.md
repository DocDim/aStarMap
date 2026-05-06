# Optimized Map Pathfinding: Dijkstra & A*

This project implements high-performance versions of **Dijkstra’s Shortest Path Algorithm** and **A* Search** specifically optimized for large-scale geographic maps. It focuses on reducing computational overhead through specialized data structures and heuristic search strategies.

---

## 🚀 Key Optimizations

To achieve superior performance over standard implementations, several core optimizations were integrated:

* **Lazy Priority Queue Initialization**: Rather than pre-loading every vertex in the graph, nodes are added to the priority queue only as they are discovered.
* **Early Termination**: The search process stops the moment the destination node is reached, preventing unnecessary exploration of the remaining graph.
* **4-ary Heap Implementation**: Replaced the standard binary heap with a **0-indexed 4-ary heap** (`IndexPQOptimized`) to provide faster priority updates.
* **Euclidean Heuristic (A*)**: Uses the "as-the-crow-flies" distance to bias the search toward the destination, significantly outperforming Dijkstra on long-distance queries.

---

## 📊 Performance Benchmarks

Testing was conducted on real-world map data (Windows 11 environment) comparing basic implementations against these optimized versions:

| Input File | Basic Dijkstra (ms) | Optimized Dijkstra (ms) | A* Search (ms) |
| :--- | :--- | :--- | :--- |
| `usa-10.txt` | 291.810 | 81.620 | **23.710** |
| `usa-100short.txt` | 2,060.307 | 75.216 | **71.241** |
| `usa-100long.txt` | 2,051.606 | 676.769 | **207.481** |

---

## 🎨 Visualization

The project utilizes a `Turtle` graphics component to visually render the search paths on a coordinate-based `EuclideanGraph`:
* **Red Paths**: Represent the shortest path found using the Optimized Dijkstra algorithm.
* **Blue Paths**: Represent the shortest path found using the A* Search algorithm.

---

## 🛠️ Technologies Used

* **Language**: Java
* **Data Structures**: Custom 4-ary Heap, Index Priority Queue
* **Graphics**: Turtle library for path rendering
* **Environment**: Windows 11

---

## 📝 Authors

* **Miltiade D. Tchifou**