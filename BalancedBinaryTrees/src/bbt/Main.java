package bbt;

import bbt.datastructure.*;
import bbt.testing.*;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        Tester avlTester = new Tester(new AVLTree());
        avlTester.runTest(new InsertCheckEraseNTest(1000000));
        
        Tester treapTester = new Tester(new Treap());
        treapTester.runTest(new InsertCheckEraseNTest(1000000));
        
        Tester scapegoatTester = new Tester(new ScapegoatTree());
        scapegoatTester.runTest(new InsertCheckEraseNTest(1000000));
    }
}
