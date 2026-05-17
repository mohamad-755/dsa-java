import java.util.*;

public class Graph {

    private List<List<Integer>> adjacency;

    public Graph(int[][] matrix) {
        adjacency = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            adjacency.add(new ArrayList<>());
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    adjacency.get(i).add(j);
                }
            }
        }
    }

    public List<Integer> getNeighbors(int node) {
        return adjacency.get(node);
    }

    public int size() {
        return adjacency.size();
    }

    public void printGraph() {
        for (int i = 0; i < size(); i++) {
            System.out.println(i + " -> " + adjacency.get(i));
        }
    }

    public int degree(int node) {
        return adjacency.get(node).size();
    }

    public boolean isLeaf(int node) {
        return adjacency.get(node).size() == 1;
    }

    public int maxNeighborDegree(int node) {
        List<Integer> neighbors = adjacency.get(node);

        if (degree(node) == 0)
            return -1;

        int max = -1;
        for (int neighbor : neighbors) {
            int deg = degree(neighbor);
            if (deg > max) {
                max = deg;
            }
        }

        return max;

    }

    public int sharedNeighbors(int a, int b) {
        List<Integer> a1 = adjacency.get(a);
        List<Integer> b1 = adjacency.get(b);
        int count = 0;

        for (int i = 0; i < a1.size(); i++) {
            if (b1.contains(a1.get(i))) {
                count++;
            }
        }
        return count;
    }

    public int secondDegree(int node) {

        List<Integer> neighbors = getNeighbors(node);
        Set<Integer> result = new HashSet<>();

        for (int neighbor : neighbors) {
            for (int second : adjacency.get(neighbor)) {
                result.add(second);
            }
        }

        result.remove(node);
        result.removeAll(neighbors);

        return result.size();
    }

    public ArrayList<Integer> dfs(int start) {
        boolean[] visited = new boolean[size()];
        ArrayList<Integer> result = new ArrayList<>();

        dfsHelper(start, visited, result);

        return result;
    }

    private void dfsHelper(int node, boolean[] visited, ArrayList<Integer> result) {
        visited[node] = true;
        result.add(node);

        for (int n : getNeighbors(node)) {
            if (!visited[n]) {
                dfsHelper(n, visited, result);
            }
        }

    }

    public boolean isUndirected() {
        for (int u = 0; u < adjacency.size(); u++) {
            for (Integer v : getNeighbors(u)) {
                if (v < 0 || v >= adjacency.size() || !adjacency.get(v).contains(u)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStar() {
        int n = size();
        int central = 0;

        for (int i = 0; i < n; i++) {
            int deg = degree(i);

            if (deg == n - 1)
                central++;
            else if (!isLeaf(i))
                return false;
        }

        return central == 1;

    }

    public int findStarCenter() {
        for (int u = 0; u < size(); u++) {
            if (!getNeighbors(u).isEmpty()) {
                int v = getNeighbors(u).get(0);

                if (degree(u) > degree(v))
                    return u;
                else
                    return v;
            }
        }
        return -1;
    }

    public boolean isMesh() {
        int n = size();

        for (int i = 0; i < n; i++) {
            int deg = degree(i);
            if (deg != n - 1)
                return false;
        }
        return true;
    }

    public boolean isRing() {
        int n = size();

        for (int i = 0; i < n; i++) {
            if (degree(i) != 2)
                return false;
        }

        return dfs(0).size() == n;
    }

    public boolean isTree() {
        int n = size();

        if (dfs(0).size() != n)
            return false;

        int sum = 0;
        for (int i = 0; i < size(); i++) {
            sum += degree(i);
        }
        if (sum / 2 != n - 1)
            return false;
        return true;
    }

    public String detectTopology() {
        if (!isUndirected())
            return "Invalid network";
        if (isRing())
            return "Ring";
        if (isMesh())
            return "Mesh";
        if (isStar())
            return "Star";
        if (isTree())
            return "Tree";
        return "Hybrid";
    }
    /*
     * import java.util.ArrayList;
     * import java.util.List;
     * import java.util.Set;
     * import java.util.HashSet;
     * 
     * class Graph {
     * private List<List<Integer>> adjacency;
     * 
     * public Graph(int[][] matrix) {
     * adjacency = new ArrayList<>(matrix.length);
     * for (int i = 0; i < matrix.length; i++) {
     * List<Integer> neighbors = new ArrayList<>();
     * for (int j = 0; j < matrix[i].length; j++) {
     * if (matrix[i][j] == 1) {
     * neighbors.add(j);
     * }
     * }
     * adjacency.add(neighbors);
     * }
     * }
     * 
     * public List<Integer> getNeighbors(int node) {
     * return adjacency.get(node);
     * }
     * 
     * public int size() {
     * return adjacency.size();
     * }
     * 
     * public void printGraph() {
     * for (int i = 0; i < this.size(); i++) {
     * System.out.println(i + " -> " + adjacency.get(i));
     * }
     * }
     * 
     * public int degree(int node) {
     * return adjacency.get(node).size();
     * }
     * 
     * public boolean isLeaf(int node) {
     * return degree(node) == 0;
     * }
     * 
     * public int maxNeighborDegree(int node) {
     * int maxDegree = -1;
     * for (int neighbor : getNeighbors(node)) {
     * int degree = degree(neighbor);
     * if (degree > maxDegree) {
     * maxDegree = degree;
     * }
     * }
     * return maxDegree;
     * }
     * 
     * public int sharedNeighbors(int a, int b) {
     * Set<Integer> neighborsA = new HashSet<>(getNeighbors(a));
     * neighborsA.retainAll(getNeighbors(b));
     * return neighborsA.size();
     * }
     * 
     * public ArrayList<Integer> dfs(int start) {
     * boolean[] visited = new boolean[size()];
     * ArrayList<Integer> result = new ArrayList<>();
     * dfsHelper(start, visited, result);
     * return result;
     * }
     * 
     * public ArrayList<Integer> dfsHelper(int node, boolean[] visited,
     * ArrayList<Integer> result) {
     * visited[node] = true;
     * result.add(node);
     * for (int neighbor : getNeighbors(node)) {
     * if (visited[neighbor]) {
     * continue;
     * }
     * dfsHelper(neighbor, visited, result);
     * }
     * return result;
     * }
     * 
     * public boolean isUndirected() {
     * for (int i = 0; i < this.size(); i++) {
     * for (int neighbor : getNeighbors(i)) {
     * if (!getNeighbors(neighbor).contains(i)) {
     * return false;
     * }
     * }
     * }
     * return true;
     * }
     * 
     * public boolean isStar() {
     * int centerCount = 0;
     * for (int i = 0; i < this.size(); i++) {
     * if (degree(i) == this.size() - 1) {
     * centerCount++;
     * } else if (degree(i) != 1) {
     * return false;
     * }
     * }
     * return centerCount == 1 || (centerCount == 2 && this.size() == 2);
     * }
     * 
     * public int findStarCenter() {
     * int arbitraryVertex = 0;
     * List<int> firstRow = adjacency[arbitraryVertex];
     * if (firstRow.size() != 1) {
     * return arbitraryVertex
     * }
     * return firstRow.get(0);
     * }
     * 
     * public boolean isMesh() {
     * int n = this.size();
     * for (int i = 0; i < n; i++) {
     * if (degree(i) != n - 1) {
     * return false;
     * }
     * }
     * return true;
     * }
     * 
     * public boolean isRing() {
     * int n = this.size();
     * for (int i = 0; i < n; i++) {
     * if (degree(i) != 2) {
     * return false;
     * }
     * }
     * 
     * return dfs(0).size() == n; // Check if all nodes are connected
     * }
     * 
     * public boolean isTree() {
     * int n = this.size();
     * int edgeCount = 0;
     * for (int i = 0; i < n; i++) {
     * edgeCount += degree(i);
     * }
     * 
     * return edgeCount == 2 * (n - 1) && dfs(0).size() == n; // Check if it's
     * connected and has n-1 edges
     * }
     * 
     * public String detectTopology() {
     * if (!undirected()) {
     * return "Invalid Network";
     * } else if (isRing()) {
     * return "Ring";
     * } else if (isMesh()) {
     * return "Mesh";
     * } else if (isStar()) {
     * return "Star";
     * } else if (isTree()) {
     * return "Tree";
     * } else {
     * return "Hybrid";
     * }
     * }
     * }
     * 
     * public class lab_12 {
     * public static void main(String[] args) {
     * int[][] matrix = {
     * { 0, 1, 1, 0, 1 },
     * { 1, 0, 0, 1, 1 },
     * { 1, 0, 1, 1, 0 },
     * { 0, 1, 0, 0, 1 },
     * { 1, 1, 0, 0, 0 }
     * };
     * Graph graph = new Graph(matrix);
     * graph.printGraph();
     * 
     * System.out.println("Degree of node 0: " + graph.degree(0));
     * System.out.println("Is node 2 a leaf? " + graph.isLeaf(2));
     * System.out.println("Max neighbor degree of node 0: " +
     * graph.maxNeighborDegree(0));
     * System.out.println("Shared neighbors of node 0 and 1: " +
     * graph.sharedNeighbors(0, 1));
     * System.out.println("DFS starting from node 3: " + graph.dfs(3));
     * }
     * }
     */
}
