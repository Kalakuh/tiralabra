package bbt.datastructures;

public abstract class BinaryTree<T extends Comparable<T>> {
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;
    private T value;
    
    public BinaryTree () {
        this.leftChild = null;
        this.rightChild = null;
    }
    
    public abstract void insert (T element);
    
    public boolean contains (T element) {
        if (this.value == null) {
            return false;
        } else if (this.value.equals(element)) {
            return true;
        } else if (element.compareTo(this.value) < 0) {
            if (this.leftChild == null) {
                return false;
            }
            return this.leftChild.contains(element);
        } else if (this.rightChild == null) {
            return false;
        }
        return this.rightChild.contains(element);
    }
    
    public abstract void erase (T element);
    
    protected T getValueAtRoot () {
        return this.value;
    }
    
    protected void setValueAtRoot (T newValue) {
        this.value = newValue;
    }
    
    protected BinaryTree<T> getLeftChild () {
        return this.leftChild;
    }
    
    protected void setLeftChild (BinaryTree<T> tree) {
        this.leftChild = tree;
    }
    
    protected BinaryTree<T> getRightChild () {
        return this.rightChild;
    }
    
    protected void setRightChild (BinaryTree<T> tree) {
        this.rightChild = tree;
    }
}
