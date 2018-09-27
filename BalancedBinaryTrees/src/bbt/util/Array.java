package bbt.util;

public class Array<T> {
    Object[] array;
    
    /**
     * Constructor for a fixed size array
     * @param n size of array
     */
    public Array(int n) {
        array = new Object[n];
    }
    
    /**
     * Set i-th element of array
     * @param i index
     * @param x element
     */
    public void set(int i, T x) {
        array[i] = x;
    }
    
    /**
     * Return i-th element of array
     * @param i index of element
     * @return element at i-th position
     */
    public T get(int i) {
        return (T) array[i];
    }
}
