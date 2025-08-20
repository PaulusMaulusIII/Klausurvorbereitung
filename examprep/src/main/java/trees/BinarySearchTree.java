package main.java.trees;

public class BinarySearchTree<K extends Comparable<K>, V> extends Tree<K, V> {

    public BinarySearchTree(Node<K, V> root) {
        super(root);
    }

    @Override
    public void insertNode(Node<K, V> node) {
        if (getRootNode() == null) {
            setRootNode(node);
            return;
        }
        Node<K, V> currentNode = getRootNode();
        while (true) {
            int cmp = node.getKey().compareTo(currentNode.getKey());
            if (cmp > 0) {
                if (currentNode.hasRight()) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(node);
                    return;
                }
            } else if (cmp < 0) {
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
    public void deleteNode(K key) {
        setRootNode(deleteRecursive(getRootNode(), key));
    }

    private Node<K, V> deleteRecursive(Node<K, V> root, K key) {
        if (root == null)
            return null;
        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeft(deleteRecursive(root.getLeft(), key));
        } else if (cmp > 0) {
            root.setRight(deleteRecursive(root.getRight(), key));
        } else {
            // Node to delete found
            if (!root.hasLeft())
                return root.getRight();
            if (!root.hasRight())
                return root.getLeft();
            // Node with two children: get inorder successor
            Node<K, V> minNode = findMin(root.getRight());
            root.setKey(minNode.getKey());
            root.setValue(minNode.getValue());
            root.setRight(deleteRecursive(root.getRight(), minNode.getKey()));
        }
        return root;
    }

    private Node<K, V> findMin(Node<K, V> node) {
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
