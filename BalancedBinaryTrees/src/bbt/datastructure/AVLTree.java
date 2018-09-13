package bbt.datastructure;

public class AVLTree<T> extends BinaryTree {
    private int height;
    
    public AVLTree () {
        super();
        this.height = 0;
    }
    
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
        // update height
        this.height = 1;
        int leftSubtreeHeight = 0;
        int rightSubtreeHeight = 0;
        if (this.getLeftChild() != null) {
            leftSubtreeHeight = ((AVLTree<T>)this.getLeftChild()).getHeight();
            this.height = Math.max(this.height, leftSubtreeHeight + 1);
        }
        if (this.getRightChild() != null) {
            rightSubtreeHeight = ((AVLTree<T>)this.getRightChild()).getHeight();
            this.height = Math.max(this.height, rightSubtreeHeight + 1);
        }
        // tree rotations need to be implemented
        if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
            // recalculate heights
            this.height = 1;
            if (this.getLeftChild() != null) {
                this.height = Math.max(this.height, ((AVLTree<T>)this.getLeftChild()).getHeight());
            }
            if (this.getRightChild() != null) {
                this.height = Math.max(this.height, ((AVLTree<T>)this.getRightChild()).getHeight());
            }
        }
    }

    @Override
    public void erase(Comparable element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Returns the height of the binary tree
     * @return The height of the binary tree
     */
    private int getHeight () {
        return this.height;
    }
    
    /**
     * Updates the height variable of the binary tree
     */
    private void updateAndFixHeight () {
        if (this.getValueAtRoot() == null) {
            this.height = 0;
            return;
        }
        this.height = 1;
        if (this.getLeftChild() != null) {
            this.height = Math.max(this.height, ((AVLTree<T>)this.getLeftChild()).getHeight());
        }
        if (this.getRightChild() != null) {
            this.height = Math.max(this.height, ((AVLTree<T>)this.getRightChild()).getHeight());
        }
    }
    
    /**
     * Rotates the tree clockwise as a part of maintaining the AVL condition
     */
    private void rotateClockwise () {
        AVLTree left = (AVLTree)this.getLeftChild();
        Comparable rootValue = this.getValueAtRoot();
        Comparable leftValue = left.getValueAtRoot();
        this.setValueAtRoot(leftValue);
        this.setLeftChild(left.getLeftChild());
        left.setLeftChild(left.getRightChild());
        left.setRightChild((AVLTree)this.getRightChild());
        left.setValueAtRoot(rootValue);
        this.setRightChild(left);
    }
    
    /**
     * Rotates the tree counterclockwise as a part of maintaining the AVL condition
     */
    private void rotateCounterclockwise () {
        AVLTree right = (AVLTree)this.getRightChild();
        Comparable rootValue = this.getValueAtRoot();
        Comparable rightValue = right.getValueAtRoot();
        this.setValueAtRoot(rightValue);
        this.setRightChild(right.getRightChild());
        right.setRightChild(right.getLeftChild());
        right.setLeftChild((AVLTree)this.getLeftChild());
        right.setValueAtRoot(rootValue);
        this.setLeftChild(right);
    }
}
