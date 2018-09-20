package bbt.datastructure;

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
     * Function that should return a new instance of the used Tree type
     * @return New instance of the used tree type
     */
    protected abstract BinaryTree<T> create ();
    
    /**
     * Inserts the given element to the tree.
     * @param element The value to be inserted
     */
    public void insert(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        // find the location where the node should be inserted
        if (this.getValueAtRoot() == null) {
            this.setValueAtRoot(element);
        } else if (this.getValueAtRoot().compareTo(element) >= 0) {
            if (this.getLeftChild() == null) {
                this.setLeftChild(this.create());
            }
            this.getLeftChild().insert(element);
        } else {
            if (this.getRightChild() == null) {
                this.setRightChild(this.create());
            }
            this.getRightChild().insert(element);
        }
        this.insertCallback();
    }
    
    
    /**
     * Function that's called just before insert returns
     */
    protected abstract void insertCallback ();
    
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
     * @return Returns true if value was found and erased
     */
    public abstract boolean erase (T element);
    
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
    
    /**
     * Rotates the tree clockwise
     */
    protected void rotateClockwise () {
        BinaryTree<T> left = this.getLeftChild();
        T rootValue = this.getValueAtRoot();
        T leftValue = left.getValueAtRoot();
        this.setValueAtRoot(leftValue);
        this.setLeftChild(left.getLeftChild());
        left.setLeftChild(left.getRightChild());
        left.setRightChild(this.getRightChild());
        left.setValueAtRoot(rootValue);
        this.setRightChild(left);
    }
    
    /**
     * Rotates the tree counterclockwise
     */
    protected void rotateCounterclockwise () {
        BinaryTree<T> right = this.getRightChild();
        T rootValue = this.getValueAtRoot();
        T rightValue = right.getValueAtRoot();
        this.setValueAtRoot(rightValue);
        this.setRightChild(right.getRightChild());
        right.setRightChild(right.getLeftChild());
        right.setLeftChild(this.getLeftChild());
        right.setValueAtRoot(rootValue);
        this.setLeftChild(right);
    }
}
