package bbt.datastructures;

public abstract class BinaryTree<T extends Comparable<T>> {
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;
    private T value;
    
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
    
    public T getValueAtRoot () {
        return this.value;
    }
    
    public void setValueAtRoot (T newValue) {
        this.value = newValue;
    }
}
