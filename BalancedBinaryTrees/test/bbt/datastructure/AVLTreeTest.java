package bbt.datastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AVLTreeTest {
    private AVLTree<Integer> instance;
    
    @Before
    public void setUp() {
        instance = new AVLTree<>();
    }
    
    /**
     * Test that inserting null is not allowed
     */
    @Test
    public void testInsertingNullIsNotAllowed() {
        try {
            instance.insert(null);
            fail("Program allowed inserting null");
        } catch (IllegalArgumentException e) {
            
        }
    }
    
    /**
     * Test that inserted element can be found
     */
    @Test
    public void testInsertedElementIsFound() {
        instance.insert(1);
        instance.insert(3);
        instance.insert(2);
        assertTrue(instance.contains(2));
    }

    /**
     * Test that erasing an inserted element from the tree works
     */
    @Test
    public void testInsertAndErase() {
        instance.insert(1);
        instance.insert(2);
        assertTrue(instance.contains(2));
        instance.erase(1);
        assertTrue(!instance.contains(1));
        assertTrue(instance.contains(2));
    }
    
    /**
     * Test erasing on larger trees 
     */
    @Test
    public void testInsertAndEraseLarger() {
        instance.insert(0);
        instance.insert(1);
        instance.insert(2);
        instance.insert(3);
        instance.insert(4);
        instance.insert(8);
        instance.insert(7);
        instance.insert(6);
        instance.insert(5);
        instance.erase(3);
        assertTrue(!instance.contains(3));
        assertTrue(instance.contains(0));
        assertTrue(instance.contains(1));
        assertTrue(instance.contains(2));
        assertTrue(instance.contains(4));
        assertTrue(instance.contains(5));
        assertTrue(instance.contains(6));
        assertTrue(instance.contains(7));
        assertTrue(instance.contains(8));
    }
    
}
