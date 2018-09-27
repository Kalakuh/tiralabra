package bbt.testing;

import bbt.util.List;
import bbt.util.Pair;

public class InsertCheckEraseNTest extends Test {
    private int n;
    
    /**
     * Inserts numbers 1..n to the tree, then checks that it contains them, and then erases them.
     * @param n Number of elements
     */
    public InsertCheckEraseNTest(int n) {
        this.n = n;
    }
    
    @Override
    protected List<Pair<String, Integer>> getInput() {
        List<Pair<String, Integer>> input = new List<>();
        for (int i = 1; i <= n; i++) {
            input.add(new Pair(Test.INSERT, i));
        }
        for (int i = 1; i <= n; i++) {
            input.add(new Pair(Test.CONTAINS, i));
        }
        for (int i = 1; i <= n; i++) {
            input.add(new Pair(Test.ERASE, i));
        }
        return input;
    }
}
