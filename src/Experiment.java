public class Experiment {
    private long[][] results;
    private int testCount;

    public Experiment(){
        results = new long[10][3];
        testCount = 0;
    }

    public void runTraversals(Graph g){
        System.out.println("Running traversals on graphs with " + g.getSize() + "vertices");

        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();
        long bfsTime = bfsEnd - bfsStart;

        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();
        long dfsTime = dfsEnd - dfsStart;

        System.out.println("BFS time: " + bfsTime + " ns");
        System.out.println("DFS time: " + dfsTime + " ns");

        if(testCount < results.length){
            results[testCount][0] = g.getSize();
            results[testCount][1] = bfsTime;
            results[testCount][2] = dfsTime;
            testCount++;
        }
    }

    public void runMultipleTests(){
        System.out.println("Experiments with Graph Sizes");

        Graph small = buildGraph(10);
        runTraversals(small);

        Graph medium = buildGraph(30);
        runTraversals(medium);

        Graph large = buildGraph(100);
        runTraversals(large);
    }

    public Graph buildGraph(int n){
        Graph g = new Graph();

        for(int i = 0; i < n; i++){
            g.addVertex(new Vertex(i));
        }

        for(int i = 0; i < n - 1; i++){
            g.addEdge(i, i + 1);
        }

        for(int i = 0; i  + 3 < n; i += 3){
            g.addEdge(i, i + 3);
        }

        return g;
    }

    public void printResults() {
        System.out.println("\n========== RESULTS TABLE ==========");
        System.out.printf("%-15s %-20s %-20s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("---------------------------------------------------");

        for (int i = 0; i < testCount; i++) {
            System.out.printf("%-15d %-20d %-20d%n",
                    results[i][0],
                    results[i][1],
                    results[i][2]);
        }
    }
}

