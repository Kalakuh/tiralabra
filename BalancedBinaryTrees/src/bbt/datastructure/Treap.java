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
        if (element == null) {
            throw new IllegalArgumentException();
        }
        // find the location where the node should be inserted
        if (this.getValueAtRoot() == null) {
            this.setValueAtRoot(element);
        } else if (this.getValueAtRoot().compareTo(element) >= 0) {
            if (this.getLeftChild() == null) {
                this.setLeftChild(new Treap<>());
            }
            this.getLeftChild().insert(element);
        } else {
            if (this.getRightChild() == null) {
                this.setRightChild(new Treap<>());
            }
            this.getRightChild().insert(element);
        }
        // check heap condition
        this.checkHeapCondition();
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
    
    /**
     * Sets the priority of the treap
     * @param priority New priority
     */
    private void setPriority (int priority) {
        this.priority = priority;
    }

    /**
     * Check heap condition - that is, whether highest priorities are topmost - and possibly fix it
     */
    private void checkHeapCondition() {
        // After insertion or deletion at most one of the children can have a priority higher than the priority of this node
        if (this.getLeftChild() != null) {
            int leftPriority = ((Treap<T>)this.getLeftChild()).getPriority();
            if (leftPriority > this.priority) {
                this.rotateClockwise();
            }
        }
        if (this.getRightChild() != null) {
            int rightPriority = ((Treap<T>)this.getRightChild()).getPriority();
        }
    }
    
    /**
     * Rotates the tree clockwise and updates priorities
     */
    @Override
    protected void rotateClockwise () {
        int leftPriority = ((Treap<T>)this.getLeftChild()).getPriority();
        ((Treap<T>)this.getLeftChild()).setPriority(this.priority);
        this.setPriority(leftPriority);
        super.rotateClockwise();
    }
    
    /**
     * Rotates the tree counterclockwise and updates priorities
     */
    @Override
    protected void rotateCounterclockwise () {
        int rightPriority = ((Treap<T>)this.getRightChild()).getPriority();
        ((Treap<T>)this.getRightChild()).setPriority(this.priority);
        this.setPriority(rightPriority);
        super.rotateCounterclockwise();
    }
}
