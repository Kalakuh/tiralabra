package bbt.testing;

import bbt.util.Pair;
import bbt.datastructure.BinaryTree;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Input uses the following format:
 * ("INSERT", n)    - inserts n into the tree
 * ("CONTAINS", n)  - checks if tree contains n
 * ("ERASE", n)     - tries to erase a single instance of n from the tree
 */

public abstract class Test {
    
    public boolean run (BinaryTree<Integer> tree) {
        ArrayList<Pair<String, Integer>> input = getInput();
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean success = true;
        
        for (Pair<String, Integer> pair : input) {
            switch (pair.getFirst()) {
                case "INSERT":
                    tree.insert(pair.getSecond());
                    if (!map.containsKey(pair.getSecond())) {
                        map.put(pair.getSecond(), 0);
                    }
                    map.put(pair.getSecond(), map.get(pair.getSecond()) + 1);
                    break;
                case "CONTAINS":
                    if (!map.containsKey(pair.getSecond())) {
                        map.put(pair.getSecond(), 0);
                    }
                    if ((map.get(pair.getSecond()) != 0) != tree.contains(pair.getSecond())) {
                        success = false;
                    }
                    break;
                case "ERASE":
                    tree.insert(pair.getSecond());
                    if (!map.containsKey(pair.getSecond())) {
                        map.put(pair.getSecond(), 0);
                    }
                    map.put(pair.getSecond(), map.get(pair.getSecond()) + 1);
                    break;
                default:
                    System.out.println("Unknown command in the test: " + pair.getFirst());
                    throw new Error();
            }
        }
        return success;
    }
    
    protected abstract ArrayList<Pair<String, Integer>> getInput ();
}
