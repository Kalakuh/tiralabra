package bbt.util;

public class Pair<T, S> {
    private T first;
    private S second;
    
    /**
     * Constructor for a new pair
     * @param first first value
     * @param second second value
     */
    public Pair (T first, S second) {
        this.first = first;
        this.second = second;
    }
    
    /**
     * Returns the first value of the pair
     * @return first value
     */
    public T getFirst () {
        return this.first;
    }
    
    /**
     * Returns the second value of the pair
     * @return second value
     */
    public S getSecond () {
        return this.second;
    }
    
    /**
     * Sets the first value
     * @param value new first value
     */
    public void setFirst (T value) {
        this.first = value;
    }
    
    /**
     * Sets the second value
     * @param value new second value
     */
    public void setSecond (S value) {
        this.second = value;
    }
}
