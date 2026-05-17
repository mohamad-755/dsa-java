public class BSTpriorityQueue {

    // A node stores a value (String) and a priority (int, lower = more important)
    static class Node {
        String value;
        int priority;
        Node left, right;

        public Node(String value, int priority) {
            this.value = value;
            this.priority = priority;
        }

        public String toString() {
            return value + " (priority=" + priority + ")";
        }
    }

    // -------------------------------------------------------
    // BST Priority Queue class
    // The BST is ordered by priority (left = lower priority number = more
    // important)
    // -------------------------------------------------------
    static class PriorityQueueBST {

        Node root;

        public PriorityQueueBST() {
            root = null;
        }

        // EnQueue: insert a new node into the BST ordered by priority
        // If priorities are equal, insert to the right
        public void enQueue(String value, int priority) {
            root = insert(root, value, priority);
        }

        // Helper for enQueue (recursive insert)
        private Node insert(Node node, String value, int priority) {
            if (node == null) {
                return new Node(value, priority);
            }
            if (priority < node.priority) {
                node.left = insert(node.left, value, priority);
            } else {
                node.right = insert(node.right, value, priority);
            }
            return node;
        }

        // DeQueue: remove and return the node with the HIGHEST priority
        // (i.e., the node with the LOWEST priority number — leftmost node in BST)
        public Node deQueue() {
            Node min = peek();
            ;
            root = removeMin(root);
            return min;
        }

        // Helper: find and remove the leftmost node
        private Node removeMin(Node node) {
            if (node.left == null) {
                return node.right;
            }
            node.left = removeMin(node.left);
            return node;
        }

        // Peek: return (without removing) the highest priority node
        private Node peek(Node node) {
            if (node.left == null) {
                return node;
            }
            return peek(node.left);
        }

        public Node peek() {
            return peek(root);
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    // -------------------------------------------------------
    // Driver
    // -------------------------------------------------------
    public static void main(String[] args) {

        PriorityQueueBST pq = new PriorityQueueBST();

        // EnQueue tasks
        pq.enQueue("Fix critical bug", 1);
        pq.enQueue("Write report", 4);
        pq.enQueue("Team meeting", 2);
        pq.enQueue("Reply to emails", 5);
        pq.enQueue("Deploy to staging", 2);
        pq.enQueue("Code review", 3);

        System.out.println("Peek (highest priority): " + pq.peek());

        System.out.println("\n--- DeQueue order (highest priority first) ---");
        while (!pq.isEmpty()) {
            System.out.println("Removed: " + pq.deQueue());
        }

        // Expected output (priority order, ties kept in insertion order):
        // Removed: Fix critical bug (priority=1)
        // Removed: Team meeting (priority=2)
        // Removed: Deploy to staging (priority=2)
        // Removed: Code review (priority=3)
        // Removed: Write report (priority=4)
        // Removed: Reply to emails (priority=5)
    }
}