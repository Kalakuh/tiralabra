package bbt.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {
    private List<Integer> instance;
    
    /**
     * Ran before each test.
     */
    @Before
    public void setUp() {
        instance = new List<>();
    }

    /**
     * Test that list is initially empty.
     */
    @Test
    public void testEmptyInitially() {
        assertEquals(0, instance.getSize());
    }
    
    /**
     * Test adding a number
     */
    @Test
    public void testAdd() {
        instance.add(5);
        assertEquals((Integer) 5, instance.get(0));
    }
    
    /**
     * Test adding multiple numbers
     */
    @Test
    public void testAddMultiple() {
        instance.add(5);
        instance.add(25);
        instance.add(125);
        assertEquals((Integer) 125, instance.get(2));
    }
}
