import java.util.*;

public class AVLSet<T extends Comparable<T>> {

    private class Node {
        T data;
        Node left, right;
        int height;

        Node(T data) {
            this.data = data;
            this.height = 1;
        }
    }

    private Node root;

    public boolean add(T value) {
        if (contains(value))
            return false;
        root = insert(root, value);
        return true;
    }

    public boolean remove(T value) {
        if (!contains(value))
            return false;
        root = delete(root, value);
        return true;
    }

    private Node insert(Node node, T key) {
        if (node == null)
            return new Node(key);
        int c = key.compareTo(node.data);
        if (c < 0) {
            node.left = insert(node.left, key);
        } else if (c > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        updateHeight(node);
        return balance(node);

    }

    private Node delete(Node node, T key) {
        if (node == null) {
            return null;
        }
        int c = key.compareTo(node.data);
        if (c < 0) {
            node.left = delete(node.left, key);
        } else if (c > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node min = getMin(node.right);
            node.data = min.data;
            node.right = delete(node.right, min.data);
        }
        updateHeight(node);
        return balance(node);
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean contains(T value) {
        Node temp = root;
        while (temp != null) {
            int c = value.compareTo(temp.data);
            if (c > 0) {
                temp = temp.right;
            } else if (c < 0) {
                temp = temp.left;
            } else {
                return true;
            }
        }
        return false;
    }

    public void inOrderPrint() {
        System.out.print("In - order ( sorted unique elements ): ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        updateHeight(node);
        int bal = getBalance(node);
        if (bal > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left); // LR
            return rotateRight(node); // LL

        }

        if (bal < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right); // RL
            return rotateLeft(node); // RR
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t = x.right;
        x.right = y;
        y.left = t;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node t = y.left;
        y.left = x;
        x.right = t;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // Tree printing logic based on IntTreeNode wrapper
    private class IntTreeNode {
        String data;
        IntTreeNode left, right;

        IntTreeNode(String data) {
            this.data = data;
        }
    }

    // Converts AVL Node tree to IntTreeNode tree for printing
    private IntTreeNode wrap(Node node) {
        if (node == null)
            return null;

        IntTreeNode n = new IntTreeNode(String.valueOf(node.data));
        n.left = wrap(node.left);
        n.right = wrap(node.right);
        return n;
    }

    // Prints the AVL tree level by level with branch connectors
    public void printBtree() {
        IntTreeNode r = wrap(root);
        if (r == null)
            return;

        List<List<String>> lines = new ArrayList<>();
        List<IntTreeNode> level = new ArrayList<>();
        List<IntTreeNode> next = new ArrayList<>();

        level.add(r);
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (IntTreeNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.data;
                    line.add(aa);

                    if (aa.length() > widest)
                        widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null)
                        nn++;
                    if (n.right != null)
                        nn++;
                }
            }

            if (widest % 2 == 1)
                widest++;
            lines.add(line);

            List<IntTreeNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);

        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    char c = ' ';

                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null)
                            c = '|';
                        else if (j < line.size() && line.get(j) != null)
                            c = '|';
                    }

                    System.out.print(c);

                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "-");
                        }

                        System.out.print("|");

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "-" : " ");
                        }
                    }
                }
                System.out.println();
            }

            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null)
                    f = "";

                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                for (int k = 0; k < gap1; k++)
                    System.out.print(" ");
                System.out.print(f);
                for (int k = 0; k < gap2; k++)
                    System.out.print(" ");
            }

            System.out.println();
            perpiece /= 2;
        }
    }

    public static void main(String[] args) {
        AVLSet<Integer> avlSet = new AVLSet<>();
        int[] values = { 50, 30, 30, 70, 20, 30, 40, 60, 60, 80, 10, 25, 35, 45 };

        System.out.println("Inserting values:");
        for (int val : values) {
            avlSet.add(val);
            System.out.print(val + " ");
        }

        System.out.println("\n\nAVL Tree (structure):");
        avlSet.printBtree();

        System.out.println("\nSorted unique elements (in-order):");
        avlSet.inOrderPrint();

        System.out.println("\nRemoving 70, 30, and 25:");
        avlSet.remove(70);
        avlSet.remove(30);
        avlSet.remove(25);

        System.out.println("\nAVL Tree after deletions:");
        avlSet.printBtree();

        System.out.println("\nRemoving 80:");
        avlSet.remove(80);

        System.out.println("\nAVL Tree after deletions:");
        avlSet.printBtree();

        System.out.println("\nSorted unique elements after deletions:");
        avlSet.inOrderPrint();
    }
}
