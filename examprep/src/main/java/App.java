package main.java;

import java.util.Random;

import main.java.trees.BinarySearchTree;
import main.java.trees.Node;

public class App {
    final static long SEED = Long.MAX_VALUE;
    final static int MAX = 100;

    public static void main(String[] args) throws Exception {
        Random gen = new Random();
        BinarySearchTree bst = new BinarySearchTree(new Node<Integer, Integer>(MAX / 2, MAX / 2));
        while (bst.getTreeHeight() < 10) {
            bst.insertNode(new Node<Integer, Integer>(gen.nextInt(MAX), gen.nextInt(MAX)));
        }
        bst.printTree();
    }

    
}
