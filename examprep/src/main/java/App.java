package main.java;

import java.util.Random;

import main.java.trees.AVLTree;
import main.java.trees.BinarySearchTree;
import main.java.trees.Node;

public class App {
    final static long SEED = Long.MAX_VALUE;
    final static int MAX = 1000;
    final static int MAX_HEIGHT = 4;

    public static void main(String[] args) throws Exception {
        Random gen = new Random();
        AVLTree<Integer, Integer> avl = new AVLTree<Integer, Integer>(new Node<Integer, Integer>(MAX / 2, MAX / 2));

        boolean avlFull = false;
        while (!avlFull) {
            int ranKey = gen.nextInt(MAX);
            int ranVal = gen.nextInt(MAX);
            if (!avlFull)
                avl.insertNode(new Node<Integer, Integer>(ranKey, ranVal));
            System.out.println();
            System.out.println("Inserted: "+new Node<Integer,Integer>(ranKey, ranVal).toString());
            System.out.println("AVL Tree\nNodes: " + avl.getNodeCount()+ ", Height: "+ avl.getTreeHeight());
            avl.printTree();
            avlFull = (avl.getTreeHeight() > MAX_HEIGHT);
        }
    }

}
