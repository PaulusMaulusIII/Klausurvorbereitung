package main.java.trees;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

public abstract class Tree<K extends Comparable<K>, V> implements Iterable<Node<K, V>> {
    private Node<K, V> rootNode;
    private Node<K, V> currentNode;

    private Traversal traversal = Traversal.INORDER;

    public enum Traversal {
        INORDER,
        POSTORDER,
        PREORDER;
    }

    public enum Rotation {
        DLEFT,
        LEFT,
        RIGHT,
        DRIGHT;
    }

    public Tree() {
        super();
    }

    public Tree(Node<K, V> root) {
        super();
        this.rootNode = root;
    }

    public Node<K, V> getRootNode() {
        return rootNode;
    }

    public Node<K, V> getNode(K key) {
        for (Node<K, V> node : this) {
            if (node.getKey().equals(key)) {
                return node;
            }
        }
        throw new NoSuchElementException("No node of this key in this tree");
    }

    public void setRootNode(Node<K, V> rootNode) {
        this.rootNode = rootNode;
    }

    public void setTraversal(Traversal traversal) {
        this.traversal = traversal;
    }

    public void setCurrentNode(Node<K, V> currentNode) {
        this.currentNode = currentNode;
    }

    public Node<K, V> getCurrentNode() {
        return currentNode;
    }

    public int getNodeDistance(Node<K, V> from, Node<K, V> to) {
        if (from == null || to == null) {
            return -1;
        }
        if (from == to) {
            return 0;
        }
        LinkedList<Node<K, V>> queue = new LinkedList<>();
        Map<Node<K, V>, Integer> visited = new HashMap<>();
        queue.add(from);
        visited.put(from, 0);
        while (!queue.isEmpty()) {
            Node<K, V> current = queue.poll();
            if (current == null) {
                return -1;
            }
            int distance = visited.get(current);
            if (current == to) {
                return distance;
            }
            if (current.hasLeft() && !visited.containsKey(current.getLeft())) {
                queue.add(current.getLeft());
                visited.put(current.getLeft(), distance + 1);
            }
            if (current.hasRight() && !visited.containsKey(current.getRight())) {
                queue.add(current.getRight());
                visited.put(current.getRight(), distance + 1);
            }
            if (current.getParent() != null && !visited.containsKey(current.getParent())) {
                queue.add(current.getParent());
                visited.put(current.getParent(), distance + 1);
            }
        }
        return -1;
    }

    public int getTreeHeight() {
        int height = -1;
        for (Node<K, V> leaf : getLeaves()) {
            int dis = getNodeDistance(getRootNode(), leaf);
            if (dis > height) {
                height = dis;
            }
        }
        return height;
    }

    public int getSubTreeHeight() {
        int height = -1;
        for (Node<K, V> leaf : getLeaves()) {
            int dis = getNodeDistance(currentNode, leaf);
            if (dis > height) {
                height = dis;
            }
        }
        return height;
    }

    public int getSubTreeHeight(Node<K, V> node) {
        int height = -1;
        for (Node<K, V> leaf : getLeaves(node)) {
            int dis = getNodeDistance(node, leaf);
            if (dis > height) {
                height = dis;
            }
        }
        return height;
    }

    public int getNodeHeight(Node<K, V> node) {
        return getNodeDistance(rootNode, node);
    }

    public int getNodeCount() {
        int count = 0;
        Iterator<Node<K, V>> it = this.iterator();
        while (it.hasNext()) {
            it.next();
            count++;
        }
        return count;
    }

    public List<Node<K, V>> getLeaves() {
        List<Node<K, V>> leaves = new LinkedList<Node<K, V>>();
        for (Node<K, V> node : this) {
            if ((!node.hasLeft()) && (!node.hasRight())) {
                leaves.add(node);
            }
        }
        return leaves;
    }

    public List<Node<K, V>> getLeaves(Node<K, V> root) {
        List<Node<K, V>> leaves = new LinkedList<Node<K, V>>();
        Node<K, V> node = root;
        if (node == null) {
            return leaves;
        }
        while (node.hasLeft() || node.hasRight()) {
            if (!node.hasLeft() && !node.hasRight()) {
                leaves.add(node);
                return leaves;
            }
            if (node.hasLeft()) {
                leaves.addAll(getLeaves(node.getLeft()));
            }
            if (node.hasRight()) {
                leaves.addAll(getLeaves(node.getRight()));
            }
            return leaves;
        }
        return leaves;
    }

    public abstract void insertNode(Node<K, V> node);

    public abstract void deleteNode(K key);

    public abstract void balanceTree();

    public abstract void rotateSubTree(Rotation r);

    @Override
    public Iterator<Node<K, V>> iterator() {
        return switch (traversal) {
            case Traversal.INORDER -> inorderIterator();
            case Traversal.POSTORDER -> postOrderIterator();
            case Traversal.PREORDER -> preOrderIterator();
            default -> throw new IllegalArgumentException("Unexpected value: " + traversal);
        };
    }

    public Iterator<Node<K, V>> inorderIterator() {
        return new Iterator<Node<K, V>>() {
            private Stack<Node<K, V>> stack = new Stack<>();
            private Node<K, V> current = rootNode;

            @Override
            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            @Override
            public Node<K, V> next() {
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }
                if (stack.isEmpty()) {
                    throw new NoSuchElementException("No next node!");
                }
                Node<K, V> node = stack.pop();
                current = node.getRight();
                return node;
            }
        };
    }

    public Iterator<Node<K, V>> preOrderIterator() {
        return new Iterator<Node<K, V>>() {
            private Stack<Node<K, V>> stack = new java.util.Stack<>();

            {
                if (rootNode != null) {
                    stack.push(rootNode);
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No next node!");
                }
                Node<K, V> node = stack.pop();
                if (node.hasRight()) {
                    stack.push(node.getRight());
                }
                if (node.hasLeft()) {
                    stack.push(node.getLeft());
                }
                return node;
            }
        };
    }

    public Iterator<Node<K, V>> postOrderIterator() {
        return new Iterator<Node<K, V>>() {
            private Stack<Node<K, V>> stack = new Stack<>();
            private Node<K, V> lastVisited = null;
            private Node<K, V> current = rootNode;

            @Override
            public boolean hasNext() {
                return !stack.isEmpty() || current != null;
            }

            @Override
            public Node<K, V> next() {
                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }
                if (stack.isEmpty()) {
                    throw new NoSuchElementException("No next node!");
                }
                Node<K, V> peekNode = stack.peek();
                if (peekNode.getRight() != null && lastVisited != peekNode.getRight()) {
                    current = peekNode.getRight();
                    return next();
                } else {
                    lastVisited = stack.pop();
                    return lastVisited;
                }
            }
        };
    }

    public void printTree() {
        printTreeLabeled(rootNode, "", true, "[ROOT] ");
    }

    private void printTreeLabeled(Node<K, V> node, String prefix, boolean isTail, String label) {
        if (node == null)
            return;
        System.out.println(prefix + (isTail ? "└── " : "├── ") + label + node.getKey());
        boolean hasLeft = node.getLeft() != null;
        boolean hasRight = node.getRight() != null;
        if (hasLeft || hasRight) {
            if (hasLeft && hasRight) {
                printTreeLabeled(node.getLeft(), prefix + (isTail ? "    " : "│   "), false, "[L] ");
                printTreeLabeled(node.getRight(), prefix + (isTail ? "    " : "│   "), true, "[R] ");
            } else if (hasLeft) {
                printTreeLabeled(node.getLeft(), prefix + (isTail ? "    " : "│   "), true, "[L] ");
            } else {
                printTreeLabeled(node.getRight(), prefix + (isTail ? "    " : "│   "), true, "[R] ");
            }
        }
    }
}
