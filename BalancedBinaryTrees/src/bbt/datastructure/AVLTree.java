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
        // find the location where the node should be inserted
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
        // update height of subtree
        this.updateHeight();
        checkAVLCondition();
    }

    @Override
    public void erase(Comparable element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Checks if the AVL condition is violated and then possibly fixes it.
     * AVL condition says that the absolute difference between the height of
     * the left and right subtree should not be more than one
     */
    private void checkAVLCondition () {
        int leftSubtreeHeight = 0;
        int rightSubtreeHeight = 0;
        if (this.getLeftChild() != null) {
            leftSubtreeHeight = ((AVLTree<T>)this.getLeftChild()).getHeight();
        }
        if (this.getRightChild() != null) {
            rightSubtreeHeight = ((AVLTree<T>)this.getRightChild()).getHeight();
        }
        // tree rotations
        if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
            // AVL condition is violated
            if (leftSubtreeHeight > rightSubtreeHeight) {
                AVLTree<T> leftChild = (AVLTree<T>)this.getLeftChild();
                if (leftChild.getRightChild() != null) {
                    int leftRightSubtreeHeight = ((AVLTree<T>)leftChild.getRightChild()).getHeight();
                    int leftLeftSubtreeHeight = 0;
                    if (leftChild.getLeftChild() != null) {
                        leftLeftSubtreeHeight = ((AVLTree<T>)leftChild.getLeftChild()).getHeight();
                    }
                    if (leftRightSubtreeHeight > leftLeftSubtreeHeight) {
                        leftChild.rotateCounterclockwise();
                        leftChild.updateHeight();
                    }
                }
                this.rotateClockwise();
            } else {
                AVLTree<T> rightChild = (AVLTree<T>)this.getRightChild();
                if (rightChild.getLeftChild() != null) {
                    int rightLeftSubtreeHeight = ((AVLTree<T>)rightChild.getLeftChild()).getHeight();
                    int rightRightSubtreeHeight = 0;
                    if (rightChild.getRightChild() != null) {
                        rightRightSubtreeHeight = ((AVLTree<T>)rightChild.getRightChild()).getHeight();
                    }
                    if (rightLeftSubtreeHeight > rightRightSubtreeHeight) {
                        rightChild.rotateClockwise();
                        rightChild.updateHeight();
                    }
                }
                this.rotateCounterclockwise();
            }
            
            this.updateHeight();
        }
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
    private void updateHeight () {
        if (this.getValueAtRoot() == null) {
            this.height = 0;
            return;
        }
        this.height = 1;
        if (this.getLeftChild() != null) {
            this.height = Math.max(this.height, ((AVLTree<T>)this.getLeftChild()).getHeight() + 1);
        }
        if (this.getRightChild() != null) {
            this.height = Math.max(this.height, ((AVLTree<T>)this.getRightChild()).getHeight() + 1);
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
        this.updateHeight();
        left.updateHeight();
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
        this.updateHeight();
        right.updateHeight();
    }
}
