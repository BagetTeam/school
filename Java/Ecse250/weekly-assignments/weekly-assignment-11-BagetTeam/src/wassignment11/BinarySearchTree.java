package wassignment11;

public class BinarySearchTree<T extends Comparable<T>> {
    public class Node {
        T key;
        Node left;
        Node right;

        public Node(T key) {
            this.key = key;
        }
    }

    Node root = null;

    public void add(T key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }

        if (root == null) {
            root = new Node(key);
            return;
        }

        add(root, key);
    }

    private void add(Node node, T key) {
        // TODO: Add your code here
        if (key.compareTo(node.key) < 0) {
            if (node.left == null) {
                node.left = new Node(key);
                return;
            } 
            else {
                add(node.left, key);
            }
        }
        else if (key.compareTo(node.key) > 0) {
            if (node.right == null) {
                node.right = new Node(key);
                return;
            } 
            else {
                add(node.right, key);
            }
        }
    }

    public void printPreOrder() {
        printPreOrder(root);
        System.out.println("");
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println("");
    }

    public void printPostOrder() {
        printPostOrder(root);
        System.out.println("");
    }

    private void printPreOrder(Node node) {
        // TODO: Add your code here
        if (node == null) {
            return;
        }
        System.out.print(node.key.toString() + ", ");
        if (node.left != null) {
            printPreOrder(node.left);
        }
        if (node.right != null) {
            printPreOrder(node.right);
        }

    }

    private void printInOrder(Node node) {
        //TODO: Add your code here
        if (node == null) {
            return;
        }
        if (node.left != null) {
            printInOrder(node.left);
        }
        System.out.print(node.key.toString() + ", ");
        if (node.right != null) {
            printInOrder(node.right);
        }

    }

    private void printPostOrder(Node node) {
        //TODO: Add your code here
        if (node == null) {
            return;
        }
        if (node.left != null) {
            printPostOrder(node.left);
        }
        if (node.right != null) {
            printPostOrder(node.right);
        }
        System.out.print(node.key.toString() + ", ");
    }
}