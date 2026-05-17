import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Vertex1 {
    public int value; // label (e.g. 'A')
    public boolean wasVisited;

    // -------------------------------------------------------------
    public Vertex1(int val) // constructor
    {
        value = val;
        wasVisited = false;
    }
    // -------------------------------------------------------------
} // end class Vertex

////////////////////////////////////////////////////////////

class Road {
    int destination;
    int weight;

    public Road(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph1 {
    private ArrayList<Road>[] adjList;
    private int nVerts;

    public Graph1(int vertices) {
        nVerts = vertices;
        adjList = new ArrayList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int start, int end, int weight) {
        adjList[start].add(new Road(end, weight));
    }

    public void findLightedPath(int start, int end) {
        Queue<Integer> x = new LinkedList<>();
        boolean[] visited = new boolean[nVerts];
        int[] parent = new int[nVerts];

        for (int i = 0; i < nVerts; i++) {
            parent[i] = -1;
        }

        x.add(start);
        visited[start] = true;

        while (!x.isEmpty()) {
            Integer v = x.remove();

            if (v == end) {
                printPath(parent, start, end);
                return;
            }
            for (Road edge : adjList[v]) {
                if (edge.weight == 1 && !visited[edge.destination]) {
                    x.add(edge.destination);
                    visited[edge.destination] = true;
                    parent[edge.destination] = v;
                }
            }
        }
        System.out.println("No fully lighted path from " + start + " to " + end);
    }

    private void printPath(int[] parent, int start, int end) {
        Stack<Integer> path = new Stack<>();

        int current = end;

        while (current != -1) {
            path.push(current);
            current = parent[current];
        }

        System.out.print("The person can take the path: ");

        while (!path.isEmpty()) {
            System.out.print(path.pop());

            if (!path.isEmpty()) {
                System.out.print(" > ");
            }
        }

        System.out.println();
    }

} // end class Graph

////////////////////////////////////////////////////////////

public class Main1 {
    public static void main(String[] args) {
        Graph1 g = new Graph1(5);

        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 4, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 0);

        g.findLightedPath(0, 4);
    }
}