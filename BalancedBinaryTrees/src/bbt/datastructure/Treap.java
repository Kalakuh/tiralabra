package bbt.datastructure;

import java.util.Random;

public class Treap<T> extends BinaryTree {
    private int priority;
    
    /**
     * Default constructor for treap
     */
    public Treap () {
        Random random = new Random();
        this.priority = random.nextInt();
    }
    
    @Override
    public void insert(Comparable element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean erase(Comparable element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Returns the priority of the treap node
     * @return The priority of the treap
     */
    private int getPriority () {
        return this.priority;
    }
}
