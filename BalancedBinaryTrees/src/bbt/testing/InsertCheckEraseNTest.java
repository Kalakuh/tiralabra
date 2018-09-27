package bbt.testing;

import bbt.util.Pair;
import java.util.ArrayList;

public class InsertCheckEraseNTest extends Test {
    private int n;
    
    /**
     * Inserts numbers 1..n to the tree, then checks that it contains them, and then erases them
     * @param n Number of elements
     */
    public InsertCheckEraseNTest (int n) {
        this.n = n;
    }
    
    @Override
    protected ArrayList<Pair<String, Integer>> getInput() {
        ArrayList<Pair<String, Integer>> input = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            input.add(new Pair("INSERT", i));
        }
        for (int i = 1; i <= n; i++) {
            input.add(new Pair("CONTAINS", i));
        }
        for (int i = 1; i <= n; i++) {
            input.add(new Pair("ERASE", i));
        }
        return input;
    }
}
