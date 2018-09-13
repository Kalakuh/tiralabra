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
        assertTrue(!instance.contains(2));
    }
    
}
