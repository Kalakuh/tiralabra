package bbt.util;

public class HashMap<T, S> {
    private final static long A = 3;
    private final static long B = 5;
    private final static long C = 11;
    private int arrayLength = 1;
    private final int MAX_STEPS = 10;
    private Array<T> keys;
    private Array<S> values;
    
    public HashMap() {
        keys = new Array<>(arrayLength);
        values = new Array<>(arrayLength);
    }
    
    /**
     * Get value from hash map by key.
     * @param key key
     * @return element with the given key
     */
    public S get(T key) {
        for (int k = 0; k < MAX_STEPS; k++) {
            int keyHash = hash(key, k);
            if (keys.get(keyHash) == null) {
                continue;
            }
            if (keys.get(keyHash).equals(key)) {
                return values.get(keyHash);
            }
        }
        return null;
    }
    
    /**
     * Hash key with offset
     * @param key Key to be hashed
     * @param offset Offset for initial hashCode
     * @return hashed value between [0, arrayLength[
     */
    private int hash(T key, int offset) {
        long hash = key.hashCode() + offset;
        long value = C * ((hash * (hash * hash) % arrayLength) % arrayLength) + B * ((hash * hash) % arrayLength) + A * hash;
        value %= arrayLength;
        if (value < 0) {
            value += arrayLength;
        }
        return (int) value;
    }
    
    /**
     * The hashmap has become too slow and needs to increase its size
     */
    private void expand() {
        List<T> oldKeys = new List<>();
        List<S> oldValues = new List<>();
        for (int i = 0; i < arrayLength; i++) {
            if (keys.get(i) != null) {
                oldKeys.add(keys.get(i));
                oldValues.add(values.get(i));
            }
        }
        
        arrayLength *= 2;
        keys = new Array<>(arrayLength);
        values = new Array<>(arrayLength);
        
        for (int i = 0; i < oldKeys.getSize(); i++) {
            put(oldKeys.get(i), oldValues.get(i));
        }
    }
    
    /**
     * Insert given value to hash map
     * @param key Key for the value
     * @param value Value to be put into map
     */
    public void put(T key, S value) {
        for (int k = 0; k < MAX_STEPS; k++) {
            int keyHash = hash(key, k);
            if (keys.get(keyHash) == null) {
                keys.set(keyHash, key);
                values.set(keyHash, value);
                return;
            } else if (keys.get(keyHash).equals(key)) {
                values.set(keyHash, value);
                return;
            }
        }
        expand();
        put(key, value);
    }
}
