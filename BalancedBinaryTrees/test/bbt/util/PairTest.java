package bbt.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PairTest {
    private Pair<String, Integer> pair;
    
    /**
     * Ran before each test.
     */
    @Before
    public void setUp() {
        pair = new Pair("Hello", 1337);
    }

    /**
     * Test of getFirst method, of class Pair.
     */
    @Test
    public void testGetFirst() {
        assertEquals("Hello", pair.getFirst());
    }

    /**
     * Test of getSecond method, of class Pair.
     */
    @Test
    public void testGetSecond() {
        assertEquals((Integer) 1337, pair.getSecond());
    }

    /**
     * Test of setFirst method, of class Pair.
     */
    @Test
    public void testSetFirst() {
        pair.setFirst("Foo");
        assertEquals("Foo", pair.getFirst());
    }

    /**
     * Test of setSecond method, of class Pair.
     */
    @Test
    public void testSetSecond() {
        pair.setSecond(2018);
        assertEquals((Integer) 2018, pair.getSecond());
    }
}
