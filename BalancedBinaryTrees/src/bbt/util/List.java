package bbt.util;

import java.security.InvalidParameterException;

public class List<T> {
    private int size;
    private int arrayLength;
    private Array<T> array;
    
    /**
     * Constructor for a dynamic sized array
     */
    public List () {
        this.size = 0;
        this.arrayLength = 1;
        this.array = new Array<T>(arrayLength);
    }
    
    /**
     * Add element x to array
     * @param x element to be inserted
     */
    public void add (T x) {
        if (size == arrayLength) {
            Array<T> next = new Array<>(2 * arrayLength);
            for (int i = 0; i < arrayLength; i++) {
                next.set(i, array.get(i));
            }
            arrayLength *= 2;
            array = next;
        }
        array.set(size, x);
        size++;
    }
    
    /**
     * Get i-th element
     * @param i index of element
     * @return i-th element
     */
    public T get (int i) {
        if (i < 0 || i >= size) {
            throw new InvalidParameterException();
        }
        return array.get(i);
    }
    
    /**
     * Get size of the list
     * @return the size of the list
     */
    public int getSize () {
        return this.size;
    }
}
