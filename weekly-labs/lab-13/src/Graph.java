import java.util.*;

class Vertex {
    public int value; // label (e.g. 'A')
    public boolean wasVisited;

    // -------------------------------------------------------------
    public Vertex(int val) // constructor
    {
        value = val;
        wasVisited = false;
    }
    // -------------------------------------------------------------
} // end class Vertex

///////////////////////////////////////////////////////////////

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][]; // adjacency matrix
    private int nVerts; // current number of vertices

    // ------------------------------------------------------------
    public Graph() // constructor
    {
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) // set adjacency
            for (int k = 0; k < MAX_VERTS; k++) // matrix to 0
                adjMat[j][k] = 0;
    } // end constructor
      // -------------------------------------------------------------

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    // -------------------------------------------------------------
    public void displayVertex(int v) {
        System.out.print(vertexList[v].value);
    }

    public void DFS_iterative(Graph g, Vertex s) {
        Stack<Integer> S = new Stack<>();
        boolean[] visited = new boolean[nVerts];

        S.push(s.value);
        visited[s.value] = true;

        while (!S.isEmpty()) {
            Integer v = S.peek();
            S.pop();

            displayVertex(v);

            for (int i = 0; i < nVerts; i++) {
                if (adjMat[v][i] != 0 && !visited[i]) {
                    S.push(i);
                    visited[i] = true;
                }
            }
        }
    }

    public void DFS_recursive(Graph g, Vertex s) {
        boolean[] visited = new boolean[nVerts];
        DFS_recursive_helper(s.value, visited);

    }

    public void DFS_recursive_helper(int v, boolean[] visited) {
        visited[v] = true;
        displayVertex(v);

        for (int i = 0; i < nVerts; i++) {
            if (adjMat[v][i] != 0 && !visited[i]) {
                DFS_recursive_helper(i, visited);
            }
        }
    }

    public void BFS_iterative(Graph g, Vertex s) {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[nVerts];

        Q.add(s.value);
        visited[s.value] = true;

        while (!Q.isEmpty()) {
            Integer v = Q.remove();
            displayVertex(v);

            for (int i = 0; i < nVerts; i++) {
                if (adjMat[v][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    Q.add(i);
                }
            }
        }
    }

    public void BFS_recursive(Vertex s) {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[nVerts];

        Q.add(s.value);
        visited[s.value] = true;

        BFS_recursive_Helper(Q, visited);
    }

    public void BFS_recursive_Helper(Queue<Integer> Q, boolean[] visited) {
        if (Q.isEmpty()) {
            return;
        }
        int x = Q.remove();

        displayVertex(x);

        for (int i = 0; i < nVerts; i++) {
            if (adjMat[x][i] != 0 && !visited[i]) {
                visited[i] = true;
                Q.add(i);
            }
        }
        BFS_recursive_Helper(Q, visited);
    }

} // end class Graph
///////////////////////////////////////////////////////////////