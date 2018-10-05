package bbt;

import bbt.datastructure.*;
import bbt.testing.*;

public class Main {
    /**
     * The main function... Currently runs simple tests.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test test = new RandomNCommandsTest(1000000, 1);
        
        Tester avlTester = new Tester(new AVLTree());
        avlTester.runTest(test);
        
        Tester treapTester = new Tester(new Treap());
        treapTester.runTest(test);
        
        Tester scapegoatTester = new Tester(new ScapegoatTree());
        scapegoatTester.runTest(test);
    }
}
