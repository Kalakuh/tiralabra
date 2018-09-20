package bbt.datastructure;

public class AVLTree<T> extends BinaryTree {
    private int height;
    
    /**
     * Parameterless constructor for the AVLTree
     */
    public AVLTree () {
        super();
        this.height = 0;
    }
    
    @Override
    protected BinaryTree create () {
        return new AVLTree<>();
    }
    
    @Override
    protected void insertCallback () {
        this.checkAVLCondition();
    }
    
    @Override
    protected void eraseCallback() {
        this.checkAVLCondition();
    }
    
    /**
     * Checks if the AVL condition is violated and then possibly fixes it.
     * AVL condition says that the absolute difference between the height of
     * the left and right subtree should not be more than one.
     */
    private void checkAVLCondition () {
        this.updateHeight();
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
            if (this.getLeftChild().getValueAtRoot() == null) {
                this.setLeftChild(null);
            } else {
                this.height = Math.max(this.height, ((AVLTree<T>)this.getLeftChild()).getHeight() + 1);
            }
        }
        if (this.getRightChild() != null) {
            if (this.getRightChild().getValueAtRoot() == null) {
                this.setRightChild(null);
            } else {
                this.height = Math.max(this.height, ((AVLTree<T>)this.getRightChild()).getHeight() + 1);
            }
        }
    }
    
    /**
     * Rotates the tree clockwise as a part of maintaining the AVL condition
     */
    @Override
    protected void rotateClockwise () {
        super.rotateClockwise();
        this.updateHeight();
        ((AVLTree<T>)this.getRightChild()).updateHeight();
    }
    
    /**
     * Rotates the tree counterclockwise as a part of maintaining the AVL condition
     */
    @Override
    protected void rotateCounterclockwise () {
        super.rotateCounterclockwise();
        this.updateHeight();
        ((AVLTree<T>)this.getLeftChild()).updateHeight();
    }
}
