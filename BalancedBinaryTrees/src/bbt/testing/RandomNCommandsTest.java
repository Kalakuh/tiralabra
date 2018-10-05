package bbt.testing;

import bbt.util.List;
import bbt.util.Pair;
import java.util.Random;

public class RandomNCommandsTest extends Test {
    private int n;
    private int k;
    private int seed;
    
    /**
     * Test returns n random queries, which prefer insertion so that the trees actually grow and we can test them
     * @param n Number of elements
     * @param k Range of random values in queries is [0, k[
     */
    public RandomNCommandsTest(int n, int k) {
        this(n, k, 0);
    }
    
    public RandomNCommandsTest(int n, int k, int seed) {
        this.n = n;
        this.k = k;
        this.seed = seed;
    }
    
    @Override
    protected List<Pair<String, Integer>> getInput() {
        Random random = new Random(seed);
        List<Pair<String, Integer>> input = new List<>();
        for (int i = 1; i <= n; i++) {
            int value = (int)(Math.random() * k);
            if (Math.random() > 0.5) {
                input.add(new Pair(Test.INSERT, value));
            } else if (Math.random() > 0.5) {
                input.add(new Pair(Test.CONTAINS, value));
            } else {
                input.add(new Pair(Test.ERASE, value));
            }
        }
        return input;
    }
}
