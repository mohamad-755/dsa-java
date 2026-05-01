public class Driver {
        public static void main(String[] args) {
                int[] keys = { 13, 9, 24, 2, 10, 14, 30 };
                BST tree = new BST(keys);

                // Uncomment the below each section alone to test your code.
                // System.out.println("In Order:");
                // tree.inorder();
                // //Expected Output: 2 9 10 13 14 24 30

                // System.out.println("\n\nSearching for 13");
                // tree.searchKeyAndParentRecursively(13);
                // //Expected Output: The node with key 13 is root node

                // System.out.println("\nSearching for 2");
                // tree.searchKeyAndParentRecursively(2);
                // //Expected Output: The given key is the left node of the node with key 9

                // System.out.println("\nSearching for 30");
                // tree.searchKeyAndParentRecursively(30);
                // //Expected Output: The given key is the right node of the node with key 24

                // System.out.println("\nSearching for 50");
                // tree.searchKeyAndParentRecursively(50);
                // //Expected Output: Key not found

                // System.out.println("\nPrint Leaves");
                // tree.printLeaves();
                // //Expected Output: 2 10 14 30

                System.out.print("In order: ");
                tree.inorder(); // or whatever your inorder print is
                System.out.println();

                System.out.println("\nk-th largest");
                tree.kthLargest(1);
                // //Expected Output: 30
                tree.kthLargest(2);
                // //Expected Output: 14
                tree.kthLargest(6);
                // //Expected Output: "Out Of Range"

                // System.out.println("\nk-th smallest");
                // tree.kthSmallest(1);
                // //Expected Output: 2
                // tree.kthSmallest(2);
                // //Expected Output: 10
                // tree.kthSmallest(6);
                // //Expected Output: "Out Of Range"

                // tree.transformSum();
                // tree.inorder();
                // //Expected Output: 102 100 91 81 68 54 30

                /*
                 * Node root2 = new Node(8);
                 * root2.left = new Node(3);
                 * root2.right = new Node(5);
                 * root2.left.left = new Node(10);
                 * root2.left.right = new Node(2);
                 * root2.right.left = new Node(4);
                 * root2.right.right = new Node(6);
                 * BST treeNotBST = new BST(root2);
                 * 
                 * System.out.println("\nBinary Tree:");
                 * treeNotBST.inorder();
                 * treeNotBST.transformToBST();
                 * System.out.println("\nBinary Search Tree:");
                 * treeNotBST.inorder();
                 */
                /**
                 * Expected Ouput: Binary Tree:
                 * 10 3 2 8 4 5 6
                 * Binary Search Tree:
                 * 2 3 4 5 6 8 10
                 **/

        }
}
