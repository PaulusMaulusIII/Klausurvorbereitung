package main.java.trees;

public class BinarySearchTree extends Tree<Integer,Integer> {

    public BinarySearchTree(Node<Integer, Integer> root) {
        super(root);
    }

    @Override
    public void insertNode(Node<Integer,Integer> node) {
        if (getRootNode() == null) {
            setRootNode(node);
            return;
        }
        Node<Integer,Integer> currentNode = getRootNode();
        while (true) {
            if (node.getKey() > currentNode.getKey()) {
                if (currentNode.hasRight()) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(node);
                    return;
                }
            } else if (node.getKey() < currentNode.getKey()) {
                if (currentNode.hasLeft()) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(node);
                    return;
                }
            } else {
                currentNode.setValue(node.getValue());
                return;
            }
        }
    }

    @Override
    public void deleteNode(Integer key) {
        setRootNode(deleteRecursive(getRootNode(), key));
    }

    private Node<Integer, Integer> deleteRecursive(Node<Integer, Integer> root, Integer key) {
        if (root == null) return null;
        if (key < root.getKey()) {
            root.setLeft(deleteRecursive(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(deleteRecursive(root.getRight(), key));
        } else {
            // Node to delete found
            if (!root.hasLeft()) return root.getRight();
            if (!root.hasRight()) return root.getLeft();
            // Node with two children: get inorder successor
            Node<Integer, Integer> minNode = findMin(root.getRight());
            root.setKey(minNode.getKey());
            root.setValue(minNode.getValue());
            root.setRight(deleteRecursive(root.getRight(), minNode.getKey()));
        }
        return root;
    }

    private Node<Integer, Integer> findMin(Node<Integer, Integer> node) {
        while (node.hasLeft()) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public void balanceTree() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'balanceTree'");
    }

    @Override
    public void rotateSubTree(Rotation r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rotateSubTree'");
    }
    
}
