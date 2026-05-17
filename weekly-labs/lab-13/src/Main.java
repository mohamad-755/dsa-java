import java.util.*;

class Graph {
    private int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++)
            for (int k = 0; k < MAX_VERTS; k++)
                adjMat[j][k] = 0;
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = weight;
        adjMat[end][start] = weight;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public int[][] getAdjMat() {
        return adjMat;
    }

    // Add this method to get the number of vertices in the graph
    public int getVertexCount() {
        return nVerts;
    }
}

class Vertex {
    public char label;

    public Vertex(char lab) {
        label = lab;
    }
}

class Edge implements Comparable<Edge> {
    public int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class DisjointSet {
    private int[] parent;

    public DisjointSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        parent[yRoot] = xRoot;
    }
}

public class Main {
    public static void main(String[] args) {
        int costPerDay = 500;
        Scanner scanner = new Scanner(System.in);
        Graph graph = readGraph(scanner);

        int savings = calculateSavings(graph, costPerDay);
        System.out.println("Total Savings = " + savings);
    }

    public static Graph readGraph(Scanner scanner) {
        // TO DO
        Graph g = new Graph();
        int V = scanner.nextInt();
        for (int i = 0; i < V; i++) {
            g.addVertex((char) ('A' + i));
        }

        int E = scanner.nextInt();
        for (int i = 0; i < E; i++) {
            int origin = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            g.addEdge(origin, destination, weight);
        }
        return g;
        /*
         * INPUT FORMAT: |V| |E|
         * C1 C2 W1
         * C2 C3 W2 ..........
         * 
         * This is an example (you should past this in the terminal):
         * 13 22
         * 0 1 40
         * 0 7 53
         * 0 11 45
         * 1 2 18
         * 1 6 86
         * 2 3 30
         * 2 5 55
         * 3 4 77
         * 3 5 40
         * 4 5 90
         * 4 6 60
         * 5 6 60
         * 6 7 40
         * 6 9 74
         * 7 8 80
         * 8 9 50
         * 8 10 32
         * 8 11 30
         * 9 10 62
         * 10 11 30
         * 10 12 37
         * 11 12 30
         */
    }

    public static int calculateSavings(Graph graph, int costPerDay) {
        return (calculateTotalRoadLength(graph) - kruskalMST(graph)) * costPerDay;
    }

    public static int calculateTotalRoadLength(Graph graph) {
        int len = 0;
        int[][] a = graph.getAdjMat();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            for (int j = i + 1; j < graph.getVertexCount(); j++) {
                if (a[i][j] != 0) {
                    len += a[i][j];
                }
            }
        }
        return len;

    }

    // Kruskal's algorithm to find the minimum spanning tree weight
    public static int kruskalMST(Graph graph) {
        int V = graph.getVertexCount();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (graph.getAdjMat()[i][j] != 0) {
                    pq.add(new Edge(i, j, graph.getAdjMat()[i][j]));
                }
            }
        }

        int mstWeight = 0;
        DisjointSet ds = new DisjointSet(V);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.src;
            int v = edge.dest;
            int uSet = ds.find(u);
            int vSet = ds.find(v);
            if (uSet != vSet) {
                mstWeight += edge.weight;
                ds.union(uSet, vSet);
            }
        }
        return mstWeight;
    }

}

/*
 * Test case:
 * 
 * 13 22
 * 0 1 40
 * 0 7 53
 * 0 11 45
 * 1 2 18
 * 1 6 86
 * 2 3 30
 * 2 5 55
 * 3 4 77
 * 3 5 40
 * 4 5 90
 * 4 6 60
 * 5 6 60
 * 6 7 40
 * 6 9 74
 * 7 8 80
 * 8 9 50
 * 8 10 32
 * 8 11 30
 * 9 10 62
 * 10 11 30
 * 10 12 37
 * 11 12 30
 * 
 * 
 * Test case:
 * 9 7
 * 0 1 2
 * 1 2 3
 * 2 3 3
 * 1 3 2
 * 3 4 1
 * 6 7 2
 * 6 8 3
 * Total Savings = 1500
 * 
 * Test case:
 * 3 3
 * 0 1 3
 * 0 2 90
 * 2 1 2
 * Total Savings = 45000
 */
