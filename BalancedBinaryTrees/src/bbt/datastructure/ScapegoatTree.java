package bbt.datastructure;

import bbt.util.List;

public class ScapegoatTree<T> extends BinaryTree {
    private double alpha;
    private int size = 0;
    private int maxSize = 0;
    private ScapegoatTree<T> parent;
    
    /**
     * Constructor for a new scapegoat tree.
     */
    public ScapegoatTree() {
        this(0.85);
    }
    
    public ScapegoatTree(double alpha) {
        super();
        this.alpha = alpha;
        parent = null;
    }
    
    /**
     * Inserts the given element to the tree.
     * @param element The value to be inserted
     */
    @Override // this had to be reimplemented since ScapegoatTree acts differently compared to AVL and Treap
    public void insert(Comparable element) {
        T elem = (T) element;
        insertHelper(elem, 0, this.size + 1);
    }
    
    /**
     * Helper function for insertion in scapegoat tree.
     * @param element element to be inserted
     * @param depth depth of recursion
     * @param treeSize size of the tree after insertion
     * @return value 0 means no scapegoat needs to be found. 1 means we need scapegoat and 2 means rebuilding the current one.
     */
    private int insertHelper(T element, int depth, int treeSize) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        int findScapegoat;
        this.size++;
        this.maxSize = Math.max(this.maxSize, this.size);
        if (this.getValue() == null) {
            this.setValue((Comparable) element);
            findScapegoat = depth > Math.log(treeSize) / Math.log(1 / alpha) ? 1 : 0;
        } else if (this.getValue().compareTo((Comparable) element) >= 0) {
            if (this.getLeftChild() == null) {
                ScapegoatTree<T> child = new ScapegoatTree<>(alpha);
                child.setParent(this);
                this.setLeftChild(child);
            }
            findScapegoat = ((ScapegoatTree<T>) this.getLeftChild()).insertHelper(element, depth + 1, treeSize);
        } else {
            if (this.getRightChild() == null) {
                ScapegoatTree<T> child = new ScapegoatTree<>(alpha);
                child.setParent(this);
                this.setRightChild(child);
            }
            findScapegoat = ((ScapegoatTree<T>) this.getRightChild()).insertHelper(element, depth + 1, treeSize);
        }
        if (findScapegoat == 1 && this.parent != null && this.getSize() > alpha * this.parent.getSize()) {
            findScapegoat = 2;
        } else if (findScapegoat == 2) {
            findScapegoat = 0;
            
            boolean left = this.getParent().getLeftChild() == this;
            List<ScapegoatTree<T>> children = this.inOrderTraversal();
            ScapegoatTree<T> newThis = ScapegoatTree.rebuild(children, parent);
            this.setParent(newThis.getParent());
            this.copyFrom(newThis);
            if (this.parent != null) {
                if (left) {
                    this.getParent().setLeftChild(this);
                } else {
                    this.getParent().setRightChild(this);
                }
            }
        }
        
        return findScapegoat;
    }
    
    /**
     * Returns list of ScapegoatTrees in the order of in-order traversal - first left subtree, then current node and then right subtree.
     * @return list of ScapegoatTrees in in-order order
     */
    private List<ScapegoatTree<T>> inOrderTraversal() {
        List<ScapegoatTree<T>> nodes = new List<>();
        if (this.getLeftChild() != null) {
            nodes = ((ScapegoatTree<T>) this.getLeftChild()).inOrderTraversal();
        }
        nodes.add(this);
        if (this.getRightChild() != null) {
            List<ScapegoatTree<T>> right = ((ScapegoatTree<T>) this.getRightChild()).inOrderTraversal();
            for (int i = 0; i < right.getSize(); i++) {
                nodes.add(right.get(i));
            }
        }
        return nodes;
    }
    
    /**
     * Rebuilds as balanced binary tree as possible.
     * @param <T> type of values in the tree
     * @param children list of children
     * @param parent parent of the current node
     * @return new root of the tree
     */
    private static <T> ScapegoatTree<T> rebuild(List<ScapegoatTree<T>> children, ScapegoatTree<T> parent) {
        if (children.getSize() > 0) {
            int mid = children.getSize() / 2;
            
            List<ScapegoatTree<T>> smaller = new List<>();
            List<ScapegoatTree<T>> larger = new List<>();
            ScapegoatTree<T> tree = new ScapegoatTree<>(children.get(mid).getAlpha());
            
            tree.copyFrom(children.get(mid));
            tree.setParent(parent);
            tree.setSize(children.getSize());
            
            for (int i = 0; i < mid; i++) {
                smaller.add(children.get(i));
            }
            for (int i = mid + 1; i < children.getSize(); i++) {
                larger.add(children.get(i));
            }

            tree.setLeftChild(ScapegoatTree.rebuild(smaller, tree));
            tree.setRightChild(ScapegoatTree.rebuild(larger, tree));
            
            return tree;
        }
        return null;
    }
    
    @Override
    protected void insertCallback() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void eraseCallback() {
        if (this.getValue() == null) {
            this.size = 0;
        } else {
            this.size = 1;
            if (this.getLeftChild() != null) {
                this.size += ((ScapegoatTree<T>) this.getLeftChild()).getSize();
            }
            if (this.getRightChild() != null) {
                this.size += ((ScapegoatTree<T>) this.getRightChild()).getSize();
            }
        }
        if (this.parent == null) { // i.e. this node is root
            if (this.size < alpha * this.maxSize) {
                this.rebuildRoot();
            }
        }
    }
    
    /**
     * During deletion the condition was violated, so this function rebuilds the tree.
     */
    private void rebuildRoot() {
        this.maxSize = this.size;
        List<ScapegoatTree<T>> children = this.inOrderTraversal();
        ScapegoatTree<T> newThis = ScapegoatTree.rebuild(children, parent);
        
        ScapegoatTree<T> leftChild = (ScapegoatTree<T>) newThis.getLeftChild();
        this.setLeftChild(leftChild);
        this.setRightChild(newThis.getRightChild());
        this.setParent(newThis.getParent());
        this.setValue(newThis.getValue());
    }
    
    @Override
    protected BinaryTree create() {
        return new ScapegoatTree(this.alpha);
    }
    
    /**
     * Returns the size of the subtree.
     * @return the size of the subtree
     */
    private int getSize() {
        return this.size;
    }
    
    /**
     * Sets the size of the subtree.
     * @param the size of the subtree
     */
    private void setSize(int sz) {
        this.size = sz;
    }
    
    /**
     * Returns the parent of the node.
     * @return the parent of the node
     */
    private ScapegoatTree<T> getParent() {
        return this.parent;
    }
    
    /**
     * Set parent node for tree.
     * @param tree parent of tree
     */
    private void setParent(ScapegoatTree<T> tree) {
        this.parent = tree;
    }
    
    /**
     * Clears the tree.
     */
    @Override
    public void clear() {
        this.size = 0;
        super.clear();
    }
    
    /**
     * Returns alpha.
     * @return alpha value
     */
    public double getAlpha() {
        return this.alpha;
    }
}