package bbt.datastructures;

public abstract class BinaryTree<T extends Comparable<T>> {
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;
    private T value;
    
    /**
     * Initializes a new binary tree. The values need to be inserted by using function insert.
     */
    public BinaryTree () {
        this.leftChild = null;
        this.rightChild = null;
    }
    
    /**
     * Inserts the given element to the tree.
     * @param element The value to be inserted
     */
    public abstract void insert (T element);
    
    /**
     * Checks whether the given parameter can be found in the tree
     * @param element The value that's searched for
     * @return Returns true if the element is found
     */
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
    
    /**
     * Erases the given element from the tree. This deletes a single instance of the element.
     * @param element The value to be erased
     */
    public abstract void erase (T element);
    
    /**
     * Returns the value of the tree
     * @return The value of the tree
     */
    protected T getValueAtRoot () {
        return this.value;
    }
    
    /**
     * Sets the value of the root
     * @param newValue The new value for the root
     */
    protected void setValueAtRoot (T newValue) {
        this.value = newValue;
    }
    
    /**
     * Returns the left child of the root
     * @return The left child of the root
     */
    protected BinaryTree<T> getLeftChild () {
        return this.leftChild;
    }
    
    /**
     * Sets the left child of the root
     * @param tree The new left child for the root
     */
    protected void setLeftChild (BinaryTree<T> tree) {
        this.leftChild = tree;
    }
    
    /**
     * Returns the right child of the root
     * @return The right child of the root
     */
    protected BinaryTree<T> getRightChild () {
        return this.rightChild;
    }
    
    /**
     * Sets the right child of the root
     * @param tree The new right child for the root
     */
    protected void setRightChild (BinaryTree<T> tree) {
        this.rightChild = tree;
    }
}
