public class Main {
    public static void main(String[] args){
        System.out.println("Small Graph Demonstration");

        Graph smallGraph = new Graph();

        for(int i = 0; i < 10; i++){
            smallGraph.addVertex(new Vertex(i));
        }

        smallGraph.addEdge(0, 1);
        smallGraph.addEdge(0, 2);
        smallGraph.addEdge(1, 3);
        smallGraph.addEdge(1, 4);
        smallGraph.addEdge(2, 5);
        smallGraph.addEdge(3, 6);
        smallGraph.addEdge(4, 7);
        smallGraph.addEdge(5, 8);
        smallGraph.addEdge(6, 9);

        smallGraph.printGraph();
        System.out.println();

        smallGraph.bfs(0);
        smallGraph.dfs(0);

        System.out.println();

        Experiment exp = new Experiment();
        exp.runMultipleTests();
        exp.printResults();
    }
}
