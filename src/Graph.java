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
            adjacencyList.get(from).get(to);
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