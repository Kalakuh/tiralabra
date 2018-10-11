package bbt.testing;

import bbt.util.List;
import bbt.util.Pair;
import java.util.Random;

public class InsertAndThenRandomTest extends Test {
    private int n;
    private int m;
    private int k;
    private int seed;
    
    /**
     * Insert n integers from range [0, k[ and then do m random queries with integers from same range.
     * @param n Number of elements
     * @param k Range of random values in queries is [0, k[
     */
    public InsertAndThenRandomTest(int n, int m, int k) {
        this(n, m, k, 0);
    }
    
    public InsertAndThenRandomTest(int n, int m, int k, int seed) {
        this.n = n;
        this.k = k;
        this.m = m;
        this.seed = seed;
    }
    
    @Override
    protected List<Pair<String, Integer>> getInput() {
        Random random = new Random(seed);
        List<Pair<String, Integer>> input = new List<>();
        for (int i = 1; i <= n; i++) {
            int value = (int)(Math.random() * k);
            input.add(new Pair(Test.INSERT, value));
        }
        for (int i = 1; i <= m; i++) {
            int value = (int)(Math.random() * k);
            if (Math.random() * 3 < 1) {
                input.add(new Pair(Test.INSERT, value));
            } else if (Math.random() * 2 < 1) {
                input.add(new Pair(Test.CONTAINS, value));
            } else {
                input.add(new Pair(Test.ERASE, value));
            }
        }
        return input;
    }
    
    @Override
    public String toString() {
        return "(n = " + this.n + ", m = " + this.m + ", k = " + this.k + ", seed = " + this.seed + ")";
    }
}
