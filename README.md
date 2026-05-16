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

Code:
``` 
import java.util.*;
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

    public void printGraph() {
        System.out.println("Graph Structure");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("Vertex " + i + "->");
            System.out.println(adjacencyList.get(i));
        }
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[vertices.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS traversal: ");

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.println(cur + " ");

            for (int neighbor : adjacencyList.get(cur)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices.size()];
        System.out.println("DFS traversal");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int cur, boolean[] visited) {
        visited[cur] = true;
        System.out.println(cur + " ");

        for (int neighbor : adjacencyList.get(cur)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public int getSize(){
        return vertices.size();
    }
}
```

4. ADJACENCY LIST:

Adjacency list is a way to represent a graph in which a list of adjacent vertices is stored for each vertex.

How it's works:

1) vertices.get(i) → the Vertex object with id = i
2) adjacencyList.get(i) → a list of integer IDs that vertex i connects to.

When addVertex(v) is called, the vertex is appended to vertices and a new empty ArrayList<Integer> is appended to adjacencyList. When addEdge(from, to) is called, to is appended to adjacencyList.get(from).
The bounds check from < vertices.size() && to < vertices.size() prevents IndexOutOfBoundsException if invalid IDs are passed.
printGraph() iterates through every vertex and prints its neighbor list. getSize() returns vertices.size() — used by Experiment Class to label performance results.