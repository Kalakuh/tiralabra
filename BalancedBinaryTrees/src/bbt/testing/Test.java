package bbt.testing;

import bbt.util.Pair;
import bbt.datastructure.BinaryTree;
import bbt.util.List;
import bbt.util.HashMap;

/*
 * Input uses the following format:
 * ("INSERT", n)    - inserts n into the tree
 * ("CONTAINS", n)  - checks if tree contains n
 * ("ERASE", n)     - tries to erase a single instance of n from the tree
 */

public abstract class Test {
    public static final String INSERT = "insert";
    public static final String CONTAINS = "contains";
    public static final String ERASE = "erase";
    
    /**
     * Run the test on the given tree.
     * @param tree Tree for which the test is ran.
     * @return (Boolean that is true if no mistakes happened on contains function, time spent).
     */
    public Pair<Boolean, Long> run(BinaryTree<Integer> tree) {
        List<Pair<String, Integer>> input = getInput();
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean success = true;
        long time = 0;
        
        for (int i = 0; i < input.getSize(); i++) {
            Pair<String, Integer> pair = input.get(i);
            switch (pair.getFirst()) {
                case INSERT:
                    time += this.insert(tree, pair.getSecond(), map);
                    break;
                case CONTAINS:
                    Pair<Boolean, Long> result = this.checkContains(tree, pair.getSecond(), map);
                    success = result.getFirst() || success;
                    time += result.getSecond();
                    break;
                case ERASE:
                    time += this.erase(tree, pair.getSecond(), map);
                    break;
                default:
                    System.out.println("Unknown command in the test: " + pair.getFirst());
                    throw new Error();
            }
        }
        return new Pair(success, time);
    }
    
    /**
     * Insert a integer to tree
     * @param tree Tree for insertion
     * @param key Key to be inserted
     * @param map Map for checker
     * @return time spent
     */
    private long insert(BinaryTree<Integer> tree, Integer key, HashMap<Integer, Integer> map) {
        long startTime = System.currentTimeMillis();
        tree.insert(key);
        long endTime = System.currentTimeMillis();
        
        if (map.get(key) == null) {
            map.put(key, 0);
        }
        map.put(key, map.get(key) + 1);
        return endTime - startTime;
    }
    
    /**
     * Checks that the key is in the tree iff it's in the map.
     * @param tree Tree for checking
     * @param key Key to be checked
     * @param map Map for checker
     * @return (true if consistent with checker, spent time)
     */
    private Pair<Boolean, Long> checkContains(BinaryTree<Integer> tree, Integer key, HashMap<Integer, Integer> map) {
        if (map.get(key) == null) {
            map.put(key, 0);
        }
        
        long startTime = System.currentTimeMillis();
        boolean inTree = tree.contains(key);
        long endTime = System.currentTimeMillis();
        
        boolean inMap = map.get(key) != 0;
        
        long time = startTime - endTime;
        if (inTree != inMap) {
            return new Pair(false, time);
        }
        return new Pair(true, time);
    }
    
    /**
     * Erase a integer from tree.
     * @param tree Tree for deletion
     * @param key Key to be deleted
     * @param map Map for checker
     * @return time spend
     */
    private long erase(BinaryTree<Integer> tree, Integer key, HashMap<Integer, Integer> map) {
        long startTime = System.currentTimeMillis();
        boolean b = tree.erase(key);
        long endTime = System.currentTimeMillis();
        if (map.get(key) == null) {
            map.put(key, 0);
        }
        if (map.get(key) > 0) {
            map.put(key, map.get(key) - 1);
        }
        return endTime - startTime;
    }
    
    /**
     * Function that returns the test data as List<String, Integer>.
     * @return Test data
     */
    protected abstract List<Pair<String, Integer>> getInput();
    
    @Override
    public String toString() {
        return "()";
    }
}
