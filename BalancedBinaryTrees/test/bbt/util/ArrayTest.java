package bbt.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayTest {
    private Array<Integer> instance;
    
    /**
     * Ran before each test.
     */
    @Before
    public void setUp() {
        instance = new Array<>(10);
    }

    /**
     * Test test and get.
     */
    @Test
    public void testGetAndSet() {
        instance.set(0, 10);
        assertEquals((Integer) 10, instance.get(0));
    }
}
