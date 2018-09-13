package bbt.datastructure;

public class AVLTree<T> extends BinaryTree {
    private int height;
    
    @Override
    public void insert(Comparable element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (this.getValueAtRoot() == null) {
            this.setValueAtRoot(element);
        } else if (this.getValueAtRoot().compareTo(element) >= 0) {
            if (this.getLeftChild() == null) {
                this.setLeftChild(new AVLTree());
            }
            this.getLeftChild().insert(element);
        } else {
            if (this.getRightChild() == null) {
                this.setRightChild(new AVLTree());
            }
            this.getRightChild().insert(element);
        }
        // tree rotations need to be implemented
    }

    @Override
    public void erase(Comparable element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int getHeight () {
        return this.height;
    }
}
