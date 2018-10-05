package bbt.testing;

import bbt.util.Pair;
import bbt.datastructure.BinaryTree;
import bbt.util.List;
import java.util.HashMap;

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
     * @return Boolean that is true if no mistakes happened on contains function.
     */
    public boolean run(BinaryTree<Integer> tree) {
        List<Pair<String, Integer>> input = getInput();
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean success = true;
        
        for (int i = 0; i < input.getSize(); i++) {
            Pair<String, Integer> pair = input.get(i);
            switch (pair.getFirst()) {
                case INSERT:
                    this.insert(tree, pair.getSecond(), map);
                    break;
                case CONTAINS:
                    success = this.checkContains(tree, pair.getSecond(), map) || success;
                    break;
                case ERASE:
                    this.erase(tree, pair.getSecond(), map);
                    break;
                default:
                    System.out.println("Unknown command in the test: " + pair.getFirst());
                    throw new Error();
            }
        }
        return success;
    }
    
    /**
     * Insert a integer to tree
     * @param tree Tree for insertion
     * @param key Key to be inserted
     * @param map Map for checker
     */
    private void insert(BinaryTree<Integer> tree, Integer key, HashMap<Integer, Integer> map) {
        tree.insert(key);
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
        map.put(key, map.get(key) + 1);
    }
    
    /**
     * Checks that the key is in the tree iff it's in the map.
     * @param tree Tree for checking
     * @param key Key to be checked
     * @param map Map for checker
     * @return true if consistent with checker
     */
    private boolean checkContains(BinaryTree<Integer> tree, Integer key, HashMap<Integer, Integer> map) {
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
        if ((map.get(key) != 0) != tree.contains(key)) {
            return false;
        }
        return true;
    }
    
    /**
     * Erase a integer from tree.
     * @param tree Tree for deletion
     * @param key Key to be deleted
     * @param map Map for checker
     */
    private void erase(BinaryTree<Integer> tree, Integer key, HashMap<Integer, Integer> map) {
        boolean b = tree.erase(key);
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
        if (map.get(key) > 0) {
            map.put(key, map.get(key) - 1);
        }
    }
    
    /**
     * Function that returns the test data as List<String, Integer>.
     * @return Test data
     */
    protected abstract List<Pair<String, Integer>> getInput();
}
