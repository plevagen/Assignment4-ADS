import java.util.*;

public class Graph {
    private ArrayList<Vertex> vertices;
    private ArrayList<ArrayList<Integer>> adjacencyList;

    public Graph(){
        vertices = new ArrayList<>();
        adjacencyList = new ArrayList<>();
    }

    public void addVertex(Vertex v){
        vertices.add(v);
        adjacencyList.add(new ArrayList<>());
    }

    public void addEdge(int from, int to){
        if(from < vertices.size() && to < vertices.size()){
            adjacencyList.get(from).get(to);
        }
    }

    public void printGraph(){
        System.out.println("Graph Structure");
        for(int i = 0; i < vertices.size(); i++){
            System.out.print("Vertex " + i + "->");
            System.out.println(adjacencyList.get(i));
        }
    }
}
