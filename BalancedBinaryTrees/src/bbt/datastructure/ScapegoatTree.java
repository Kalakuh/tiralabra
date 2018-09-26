package bbt.datastructure;

import java.util.ArrayList;

public class ScapegoatTree<T> extends BinaryTree {
    private final double ALPHA = 2.0 / 3.0;
    private int size = 0;
    private ScapegoatTree<T> parent;
    
    public ScapegoatTree () {
        super();
        parent = null;
    }
    
    /**
     * Inserts the given element to the tree.
     * @param element The value to be inserted
     */
    @Override // this had to be reimplemented since ScapegoatTree acts differently compared to AVL and Treap
    public void insert (Comparable element) {
        T elem = (T)element;
        insertHelper(elem, 0, this.size + 1);
    }
    
    /**
     * Helper function for insertion in scapegoat tree
     * @param element element to be inserted
     * @param depth depth of recursion
     * @param treeSize size of the tree after insertion
     * @return does the code require a scapegoat after insertion
     */
    private boolean insertHelper (T element, int depth, int treeSize) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        boolean findScapegoat = false;
        this.size++;
        if (this.getValueAtRoot() == null) {
            this.setValueAtRoot((Comparable)element);
            findScapegoat = depth > Math.log(treeSize) / Math.log(ALPHA);
        } else if (this.getValueAtRoot().compareTo((Comparable)element) >= 0) {
            if (this.getLeftChild() == null) {
                ScapegoatTree<T> child = new ScapegoatTree<>();
                child.setParent(this);
                this.setLeftChild(child);
            }
            findScapegoat = ((ScapegoatTree<T>)this.getLeftChild()).insertHelper(element, depth + 1, treeSize);
        } else {
            if (this.getRightChild() == null) {
                ScapegoatTree<T> child = new ScapegoatTree<>();
                child.setParent(this);
                this.setRightChild(child);
            }
            findScapegoat = ((ScapegoatTree<T>)this.getRightChild()).insertHelper(element, depth + 1, treeSize);
        }
        
        if (findScapegoat && this.parent != null && this.getSize() > ALPHA * this.parent.getSize()) {
            ArrayList<ScapegoatTree<T>> children = this.inOrderTraversal();
        }
        
        return findScapegoat;
    }
    
    /**
     * Returns list of ScapegoatTrees in the order of in-order traversal - first left subtree, then current node and then right subtree
     * @return list of ScapegoatTrees in in-order order
     */
    private ArrayList<ScapegoatTree<T>> inOrderTraversal () {
        ArrayList<ScapegoatTree<T>> nodes = new ArrayList<>();
        if (this.getLeftChild() != null) {
            nodes = ((ScapegoatTree<T>)this.getLeftChild()).inOrderTraversal();
        }
        nodes.add(this);
        if (this.getRightChild() != null) {
            ArrayList<ScapegoatTree<T>> right = ((ScapegoatTree<T>)this.getRightChild()).inOrderTraversal();
            for (ScapegoatTree<T> tree : right) {
                nodes.add(tree);
            }
        }
        return nodes;
    }

    @Override
    protected void insertCallback() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void eraseCallback() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected BinaryTree create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Returns the size of the subtree
     * @return the size of the subtree
     */
    private int getSize () {
        return this.size;
    }
    
    /**
     * Returns the parent of the node
     * @return the parent of the node
     */
    private ScapegoatTree<T> getParent () {
        return this.parent;
    }
    
    /**
     * Set parent node for tree
     * @param tree parent of tree
     */
    private void setParent (ScapegoatTree<T> tree) {
        this.parent = tree;
    }
}