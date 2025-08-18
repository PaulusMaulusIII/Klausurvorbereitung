package main.java;

// import main.java.tools.ConsoleColors;
import java.util.Random;

public class Main {

    public static final long SEED = Long.MAX_VALUE;

    public static void main(String[] args) {
        Random random = new Random(SEED);
        System.out.println();
        System.out.flush();
        Main main = new Main();
        BinarySearchTree bst = main.new BinarySearchTree(main.new Node(random.nextInt(20)));
        for (int i = 0; i < 20; i++) {
            bst.insertNode(main.new Node(random.nextInt(20)));
        }   
        System.out.println(bst.toString());
    }

    public class Node {
        int value;
        Node left = null;
        Node right = null;

        public Node(int value) {
            this.value = value;
        }
    }

    public abstract class Tree {
        Node root;

        public Tree(Node root) {
            this.root = root;
        }

        public abstract void insertNode(Node node);

        public abstract void removeNode(Node node);

        public abstract Node getNode(int val);

        public abstract int getHeight();

        public  boolean hasNode(int val) {
            return getNode(val) != null;
        }

        @Override
        public String toString() {
            return toString(root, 0, "S");
        }

        public String toString(Node node, int depth, String tag) {
            return "    ".repeat(depth) + tag + ": " + node.value + " -> {"
                        + (node.left != null ? "\n" + toString(node.left, depth + 1, "L") : "") + ", "
                        + (node.right != null ? "\n" + toString(node.right, depth + 1, "R") : "") 
                    + "\n" + "    ".repeat(depth) + "}";
        }
    }

    public class BinarySearchTree extends Tree {

        public BinarySearchTree(Node root) {
            super(root);
        }

        public void insertNode(Node node, Node reference) {
            if (node.value == reference.value) {
                return;
            }
            if (node.value < reference.value) {
                if (reference.left == null) {
                    reference.left = node;
                    return;
                }
                insertNode(node, reference.left);
            } else {
                if (reference.right == null) {
                    reference.right = node;
                    return;
                }
                insertNode(node, reference.right);
            }
            return;
        }

        @Override
        public void insertNode(Node node) {
            insertNode(node, this.root);
        }

        @Override
        public void removeNode(Node node) {
            throw new UnsupportedOperationException("Unimplemented method 'removeNode'");
        }

        @Override
        public Node getNode(int val) {
            return getNode(val, root);
        }

        public Node getNode(int val, Node current){
            if (current.value == val) {
                return current;
            } else if (current.value < val && current.left != null) {
                return getNode(val, current.left);
            } else if (current.value > val && current.right != null) {
                return getNode(val, current.right);
            }
            return null;
        }

        @Override
        public int getHeight() {
            throw new UnsupportedOperationException("Unimplemented method 'getHeight'");
        }

    }
}
