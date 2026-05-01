import java.util.List;
import java.util.ArrayList;

class AVLNode {
    int data, height;
    AVLNode left, right;

    public AVLNode(int data) {
        this.data = data;
        this.height = 0; // Initially, when a node is created, its height is 1.
    }
}

public class AVLTree {
    public AVLNode root;

    // A utility function to get the height of the tree
    private int height(AVLNode node) {
        if (node == null)
            return -1;
        return node.height;
    }

    // A utility function to get the balance factor of the node
    private int getBalance(AVLNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // A utility function to update the height of a node
    private void updateHeight(AVLNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // A utility function to right rotate subtree rooted with y
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T = x.right;
        x.right = y;
        y.left = T;

        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T = y.left;
        y.left = x;
        x.right = T;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // The function to insert a new key in the subtree rooted with node
    public AVLNode insert(AVLNode node, int data) {
        // Normal BST insertion
        // first check if the tree exists or not. if not, create new tree
        if (node == null) {
            return (new AVLNode(data));
        }
        // insert the data at the right place
        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else // Duplicate keys not allowed
            return node;

        // update the height of the tree since you add new element
        updateHeight(node);

        // check if the tree is balanced or not
        int balance = getBalance(node);

        // LL
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        // RR
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);

        // RL
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // LR
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // The function to delete a node with a given key in the subtree rooted with
    // node
    public AVLNode delete(AVLNode node, int data) {
        // Normal BST delete
        if (node == null)
            return node;
        if (data < node.data)
            node.left = delete(node.left, data);
        else if (data > node.data)
            node.right = delete(node.right, data);
        else {
            // check if the root has one child or 0
            if ((node.left == null) || (node.right == null)) {
                AVLNode temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                // if no child
                if (temp == null) {
                    temp = node;
                    node = null;
                } else // replace it the node with its child
                    node = temp;
            } else {
                // find the min value from the right subTree
                AVLNode temp = minValueNode(node.right);

                node.data = temp.data;

                node.right = delete(node.right, temp.data);
            }
        }

        //
        if (node == null)
            return node;

        //
        updateHeight(node);

        //
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // A utility function to get the node with the minimum key value found in that
    // tree
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    // A utility function to perform inorder traversal of AVL tree
    private void inorder(AVLNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.data + " ");
        inorder(node.right);
    }

    public void inorder() {
        inorder(root);
    }

    // A utility function to search a given key in AVL tree
    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(AVLNode node, int data) {
        if (node == null) {
            return false;
        }
        if (node.data > data) {
            return search(node.left, data);
        } else if (node.data < data) {
            return search(node.right, data);
        } else {
            return true;
        }
    }

    // Prints the AVL tree in a structured format
    private void printAVLTree() {
        if (root == null)
            return;

        List<List<String>> lines = new ArrayList<>();
        List<AVLNode> level = new ArrayList<>();
        List<AVLNode> next = new ArrayList<>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (AVLNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.data);
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

            List<AVLNode> tmp = level;
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
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = '|';
                        } else {
                            if (j < line.size() && line.get(j) != null)
                                c = '|';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "-");
                        }
                        System.out.print(j % 2 == 0 ? "|" : "|");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "-" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null)
                    f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }

    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();

        avlTree.root = avlTree.insert(avlTree.root, 9);
        avlTree.root = avlTree.insert(avlTree.root, 5);
        avlTree.root = avlTree.insert(avlTree.root, 10);
        avlTree.root = avlTree.insert(avlTree.root, 0);
        avlTree.root = avlTree.insert(avlTree.root, 6);
        avlTree.root = avlTree.insert(avlTree.root, 11);
        avlTree.root = avlTree.insert(avlTree.root, -1);
        avlTree.root = avlTree.insert(avlTree.root, 1);
        avlTree.root = avlTree.insert(avlTree.root, 2);

        avlTree.printAVLTree();
        /*
         * The constructed AVL Tree would be
         * 9
         * / \
         * 1 10
         * / \ \
         * 0 5 11
         * / / \
         * -1 2 6
         */

        System.out.println("Inorder traversal" +
                " of constructed tree is : ");
        avlTree.inorder();
        System.out.println();
        // -1 0 1 2 5 6 9 10 11
        avlTree.root = avlTree.delete(avlTree.root, 10);

        avlTree.printAVLTree();
        /*
         * The AVL Tree after deletion would be
         * 1
         * / \
         * 0 9
         * / / \
         * -1 5 11
         * / \
         * 2 6
         */

        System.out.println("\nInorder traversal" +
                " of tree after deletion is : ");
        avlTree.inorder();
        System.out.println();
        // -1 0 1 2 5 6 9 11
    }
}
