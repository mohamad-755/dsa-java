import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BST {
    public Node root;

    public BST(int[] keys) {
        Node root = null;
        for (int key : keys) {
            root = insert(root, key);
        }
        this.root = root;
    }

    public BST(Node root) {
        this.root = root;
    }

    public Node insert(int key) {
        return insert(root, key);
    }

    private Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        }

        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + "  ");
        inorder(root.right);

    }

    public void searchKeyAndParentRecursively(int key) {
        searchKeyAndParentRecursively(root, key, null);
    }

    private void searchKeyAndParentRecursively(Node root, int key, Node parent) {
        if (root == null) {
            System.out.println("key not found");
            return;
        }
        if (root.data == key) {
            if (parent == null)
                System.out.println("The node with key " + root.data + " is root node");
            else if (parent.left == root)
                System.out.println("The given key is the left node of the node with key " + parent.data);
            else
                System.out.println("The given key is the right node of the node with key " + parent.data);
            return;
        }
        if (root.data > key) {
            searchKeyAndParentRecursively(root.left, key, root);
        } else {
            searchKeyAndParentRecursively(root.right, key, root);
        }

    }

    public void printLeaves() {
        printLeaves(root);
    }

    public void printLeaves(Node root) {
        if (root == null) {
            return;
        }
        printLeaves(root.left);
        if (root.left == null && root.right == null)
            System.out.print(root.data + " ");
        printLeaves(root.right);
    }

    public void kthLargest(int k) {
        int i = 0;
        Node res = kthLargest(root, i, k);
        if (res != null) {
            System.out.println("\n" + res.data);
        } else {
            System.out.println("Out of Range");
        }
    }

    public Node kthLargest(Node root, int i, int k) {
        if (root == null)
            return null;

        int r = size(root.right); // number of nodes greater than root

        if (k == r + 1)
            return root; // root is the k-th largest
        if (k <= r)
            return kthLargest(root.right, i, k); // go right
        return kthLargest(root.left, i, k - (r + 1)); // go left with reduced k
    }

    // helper: count nodes in subtree
    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public void kthSmallest(int k) {
        Node res = kthSmallest(root, 0, k);
        if (res != null) {
            System.out.println("\n" + res.data);
        } else {
            System.out.println("Out of Range");
        }
    }

    public Node kthSmallest(Node root, int i, int k) {
        if (root == null)
            return null;

        int l = size(root.left); // nodes smaller than root

        if (k == l + 1)
            return root; // root is k-th smallest
        if (k <= l)
            return kthSmallest(root.left, i, k);
        return kthSmallest(root.right, i, k - (l + 1));
    }

    public void transformSum() {
        int sum = findSum(root);
        transformSum(root, sum);
    }

    public int findSum(Node root) {
        if (root == null)
            return 0;
        return root.data + findSum(root.left) + findSum(root.right);
    }

    public int transformSum(Node root, int sum) {
        if (root == null) {
            return sum;
        }
        sum = transformSum(root.left, sum);
        int old = root.data;
        root.data = sum;
        sum -= old;
        sum = transformSum(root.right, sum);

        return sum;
    }

    // You can use this method if you want to add to the sorted list
    private void inOrderTraversal(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, result);
        result.add(node.data);
        inOrderTraversal(node.right, result);
    }

    public void transformToBST() {
        List<Integer> sortedValues = new ArrayList<>();
        inOrderTraversal(root, sortedValues);
        Collections.sort(sortedValues);

        root = sortedArrayToBST(sortedValues, 0, sortedValues.size() - 1);
    }

    private Node sortedArrayToBST(List<Integer> sortedArray, int start, int end) {
        if (start > end)
            return null;
        int mid = (end + start) / 2;
        Node root = new Node(sortedArray.get(mid));

        root.left = sortedArrayToBST(sortedArray, start, mid - 1);
        root.right = sortedArrayToBST(sortedArray, mid + 1, end);
        return root;
    }
}
