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
    
    public boolean run(BinaryTree<Integer> tree) {
        List<Pair<String, Integer>> input = getInput();
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean success = true;
        
        for (int i = 0; i < input.getSize(); i++) {
            Pair<String, Integer> pair = input.get(i);
            if (pair.getFirst().equals(INSERT)) {
                this.insert(tree, pair, map);
            } else if (pair.getFirst().equals(CONTAINS)) {
                success = this.contains(tree, pair, map) || success;
            } else if (pair.getFirst().equals(ERASE)) {
                this.erase(tree, pair, map);
            } else {
                System.out.println("Unknown command in the test: " + pair.getFirst());
                throw new Error();
            }
        }
        return success;
    }
    
    private void insert(BinaryTree<Integer> tree, Pair<String, Integer> pair, HashMap<Integer, Integer> map) {
        tree.insert(pair.getSecond());
        if (!map.containsKey(pair.getSecond())) {
            map.put(pair.getSecond(), 0);
        }
        map.put(pair.getSecond(), map.get(pair.getSecond()) + 1);
    }
    
    private boolean contains(BinaryTree<Integer> tree, Pair<String, Integer> pair, HashMap<Integer, Integer> map) {
        if (!map.containsKey(pair.getSecond())) {
            map.put(pair.getSecond(), 0);
        }
        if ((map.get(pair.getSecond()) != 0) != tree.contains(pair.getSecond())) {
            return false;
        }
        return true;
    }
    
    private void erase(BinaryTree<Integer> tree, Pair<String, Integer> pair, HashMap<Integer, Integer> map) {
        tree.insert(pair.getSecond());
        if (!map.containsKey(pair.getSecond())) {
            map.put(pair.getSecond(), 0);
        }
        map.put(pair.getSecond(), map.get(pair.getSecond()) + 1);
    }
    
    protected abstract List<Pair<String, Integer>> getInput();
}
