package bbt.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashMapTest {
    private HashMap<Integer, String> instance;
    
    @Before
    public void setUp() {
        instance = new HashMap<>();
    }
    
    @Test
    public void testEmptyGet() {
        assertEquals(instance.get(1), null);
    }
    
    @Test
    public void testPut() {
        instance.put(1, "Hello");
        assertEquals(instance.get(1), "Hello");
    }
    
    @Test
    public void testPutUpdate() {
        instance.put(1, "Hello");
        instance.put(1, "World");
        assertEquals(instance.get(1), "World");
    }
    
    @Test
    public void testPutMultiple() {
        instance.put(1, "Hello");
        instance.put(2, "World");
        assertEquals(instance.get(1), "Hello");
        assertEquals(instance.get(2), "World");
    }
    
    @Test
    public void testExpandTwice() {
        instance.put(1, "Hello");
        instance.put(2, "World");
        instance.put(3, "Foo");
        instance.put(4, "Bar");
        instance.put(5, "Baz");
        assertEquals(instance.get(1), "Hello");
        assertEquals(instance.get(2), "World");
        assertEquals(instance.get(3), "Foo");
        assertEquals(instance.get(4), "Bar");
        assertEquals(instance.get(5), "Baz");
    }
}
