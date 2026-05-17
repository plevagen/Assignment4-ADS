# ASSIGNMENT 4: Graph Traversal and Representation System

# A. Project Overview:
Graph is a data structure that is used to describe the interaction between objects. The graph consists of 2 parts:
1) Vertex is an element of the graph, which is an object.
2) An edge connects the vertices, showing the connection between them.
   
BFS (breadth-first search) and DFS (depth-first search) are two fundamental graph algorithms used to explore graph vertices and graph edges. For more information about them, see point C

---

# B. Class Descriptions:
1) VERTEX CLASS:

Represents a single node in the graph with a unique integer identifier.

Code: 
```
public class Vertex {
private int id;

    public Vertex(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "Vertex(" + id + ")";
    }
}
```

Some features of the class:

1) id is private. It provides encapsulation, which allow not to change data from the outside and does not violate the graph structure.
2) toString() makes debugging easy: System.out.println(vertex) prints Vertex(3) instead of a memory address.

2. EDGE CLASS:

Represents a directed connection from one vertex (source) to another (destination).

Code: 

```public class Edge {
    private Vertex source;
    private Vertex destination;

    public Edge(Vertex source, Vertex destination){
        this.source = source;
        this.destination = destination;
    }

    public Vertex getSource(){
        return source;
    }

    public Vertex getDestination(){
        return destination;
    }

    @Override
    public String toString() {
        return "Edge(" + source.getId() + " -> " + destination.getId() + ")";
    }

}
```

Some features of the class:

1) Both edge creation fields are private: an effective way to make them immutable.
2) toString() produces a clear human-readable format, e.g. Edge(0 -> 1).
3) The class uses Vertex objects rather than raw integers, keeping the design object-oriented and type-safe.

3. GRAPH CLASS:

The main class of the project. Connects the Vertex and Edge classes, actually implementing the graph model. The BFS and DFS algorithms are also implemented in this class.

4. ADJACENCY LIST:

Adjacency list is a way to represent a graph in which a list of adjacent vertices is stored for each vertex.

How it's works:

Code: 
``` 
public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<ArrayList<Integer>> adjacencyList;

    public Graph() {
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
        adjacencyList.add(new ArrayList<>());
    }
    
    public void addEdge(int from, int to) {
        if (from < vertices.size() && to < vertices.size()) {
            adjacencyList.get(from).add(to);
        }
    }

```
Both ArrayLists are kept in sync by index:
1) vertices.get(i) → the Vertex object with id = i
2) adjacencyList.get(i) → a list of integer IDs that vertex i connects to.

When addVertex(v) is called, the vertex is appended to vertices and a new empty ArrayList<Integer> is appended to adjacencyList. When addEdge(from, to) is called, to is appended to adjacencyList.get(from).
The bounds check from < vertices.size() && to < vertices.size() prevents IndexOutOfBoundsException if invalid IDs are passed.

```
public void printGraph() {
        System.out.println("Graph Structure");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("Vertex " + i + "->");
            System.out.println(adjacencyList.get(i));
        }
    }
```

printGraph() iterates through every vertex and prints its neighbor list. getSize() returns vertices.size() — used by Experiment Class to label performance results.

---
# C. ALGORITHM DESCRIPTION

1. DEPTH-FIRST SEARCH

Description: Depth-First Search  an algorithm for traversing a graph or tree.It works like a recursive algorithm. DFS explores as deep as possible along each branch before backtracking. It commits fully to one path before trying another. Uses a stack as vertex storage.

Graph for Step-By-Step demonstration: ![Graph Example.png](docs/diagrams/Graph%20Example.png)

Step-By-Step work:
```
We started from vertex 0. 
Visit 0 -> Looking unvisited neighbors -> Go to neighbor 1
Visit 1 -> Looking unvisited neighbors -> Go to neighbor 3 
Visit 3 -> Looking unvisited neighbors -> Go to neighbor 6
Visit 6 -> Looking unvisited neighbors -> Go to neighbor 9
Visit 9 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 6
Visit 6 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 3
Visit 3 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 1
Visit 1 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 0
Visit 0 -> Looking unvisited neighbors -> Go to neighbor 2
Visit 2 -> Looking unvisited neighbors -> Go to neighbor 5
Visit 5 -> Looking unvisited neighbors -> Go to neighbor 8
Visit 8 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 5
Visit 5 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 2
Visit 2 -> Looking unvisited neighbors -> No unvisited neighbors -> return to 0

DFS output: [0, 1, 3, 6, 9, 4, 7, 2, 5, 8]

Note: It was a case of bypassing the graph. When a certain vertex is found, the algorithm works the same way,
only when the desired vertex is found, it completes its work.
```

Time Complexity: O(V+E) - each edge and vertex is checked at least once

Space Complexity: O(V) - the recursive call stack depth is bounded by the depth of the graph.

Use cases:
1. Checking the path for certain routes
2. For logic games (in particular, building a tree of possible moves in a maze)
3. Topological sorting of nodes in a linear sequence

--- 
2. BREADTH-FIRST SEARCH

Description: The breadth-first search algorithm explores the graph by levels: first, it explores the neighbors of the starting point, then the neighbors of the neighbors, and so on until there are no unexplored neighbors left. After adding the neighbors of a vertex, the previous vertex is deleted. A Queue is used as vertex storage. After adding the neighbors of a vertex, the previous vertex is deleted. This is necessary to free up space in the queue so that the algorithm works correctly. BFS operates on the FIFO(First In, First Out) principle. Not recursive, unlike a DFS

Step-By-Step:

```
Start from 0 -> Add 0 to queue -> [0] 
Visit 0 -> Added a neighbors of 0: (1,2) -> Remove 0 from queue -> [1,2]
Visit 1 -> Added a neigbors of 1: (3,4) -> Remove 1 from queue -> [2,3,4]
Visit 2 -> Added a neigbors of 2: (5) -> Remove 2 from queue -> [3,4,5]
Visit 3 -> Added a neigbors of 3: (6) -> Remove 3 from queue -> [4,5,6]
Visit 4 -> Added a neigbors of 4: (7) -> Remove 4 from queue -> [5,6,7]
Visit 5 -> Added a neigbors of 5: (8) -> Remove 5 from queue -> [6,7,8]
Visit 6 -> Added a neighbors of 6: (9) -> Remove 6 from queue -> [7,8,9]
Visit 7 -> No neighbors for ad -> Remove 7 from queue -> [8, 9]
Visit 8 -> No neighbors for ad -> Remove 8 from queue -> [9]
Visit 9 -> No neighbors for ad -> Remove 9 from queue -> []

BFS: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```

Time Complexity: O(V+E) - works the same way as DFS

Space Complexity: O(V) - the visited array and queue each hold at most V elements

Use cases: 
1. Finding the shortest path in unweighted graphs
2. Level-order tree traversal
3. Using network protocols to send packets or messages to all network nodes.

---
# D. EXPERIMENTAL RESULTS

Graph Structure:

For demonstrating results on different graphs, we create graphs with two types of edges:
1. Chain edges: 0 -> 1, 1 -> 2, ..., (n-2) -> (n-1)
2. Skip-3 edges: 0 -> 3, 3 -> 6, 6 -> 9, ...

For n = 10, vertex 0 has neighbors [1, 3], vertex 3 has neighbors [4, 6], etc. The skip-3 edges create shortcuts, making the graph more realistic and the traversal orders non-trivial.

Results:
```
Graph Size      BFS Time (ns)        DFS Time (ns)
---------------------------------------------------
10              408456               252799              
30              776824               915512              
100             1978868              1567398    
```

Screenshots:
Demonstration Experiment on small graph:
![Demonstration on small graph.png](docs/screenshots/Demonstration%20on%20small%20graph.png)
Running Experiment in different sizes:
![REDF.png](docs/screenshots/REDF.png)
![Running Experiment in different sizes.png](docs/screenshots/Running%20Experiment%20in%20different%20sizes.png)
Result of Experiment:
![Results of Experiment.png](docs/screenshots/Results%20of%20Experiment.png)
