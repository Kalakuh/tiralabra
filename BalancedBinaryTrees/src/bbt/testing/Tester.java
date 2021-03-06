package bbt.testing;

import bbt.datastructure.BinaryTree;
import bbt.util.Pair;

// The tests will assume that the datatype of nodes is Integer

public class Tester {
    private BinaryTree<Integer> tree;
    
    /**
     * Constructor for a Tester class.
     * @param tree An instance of a tree for which the tests are run
     */
    public Tester(BinaryTree tree) {
        this.tree = tree;
    }
    
    /**
     * Runs the given test and gives a report about it.
     * @param test Instance of a Test
     */
    public void runTest(Test test) {
        tree.clear();
        Pair<Boolean, Long> result = test.run(tree);
        boolean success = result.getFirst();
        long time = result.getSecond();
        
        double spentSeconds = time / 1000.0;
        System.out.println("----- TEST -----");
        System.out.println("Tree: " + tree.getClass().getSimpleName());
        System.out.println("Test: " + test.getClass().getSimpleName() + test);
        System.out.println("Correct: " + success);
        System.out.format("Time: %.3fs\n", spentSeconds);
    }
}
