package bbt.datastructure;

import java.util.Random;

public class Treap<T> extends BinaryTree {
    private int priority;
    
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
    
}
