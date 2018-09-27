package bbt;

import bbt.datastructure.*;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        BinaryTree<Integer> tree = new ScapegoatTree<>();
        System.out.println("Tree contains 2: " + tree.contains(2));
        tree.insert(3);
        System.out.println("Tree contains 2: " + tree.contains(2));
        tree.insert(0);
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(0);
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        System.out.println("Tree contains 0: " + tree.contains(0));
        System.out.println("Tree contains 1: " + tree.contains(1));
        System.out.println("Tree contains 2: " + tree.contains(2));
        System.out.println("Tree contains 3: " + tree.contains(3));
        tree.erase(0);
        tree.erase(1);
        tree.erase(2);
        tree.erase(4);
        tree.erase(2);
        System.out.println("Tree contains 0: " + tree.contains(0));
        System.out.println("Tree contains 1: " + tree.contains(1));
        System.out.println("Tree contains 2: " + tree.contains(2));
        System.out.println("Tree contains 3: " + tree.contains(3));
    }
}
