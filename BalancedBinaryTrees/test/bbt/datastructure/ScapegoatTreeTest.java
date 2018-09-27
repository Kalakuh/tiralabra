package bbt.datastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScapegoatTreeTest {
    private ScapegoatTree<Integer> instance;
    
    /**
     * Ran before each test.
     */
    @Before
    public void setUp() {
        instance = new ScapegoatTree<>();
    }
    
    /**
     * Test that inserting null is not allowed.
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
     * Test that inserted element can be found.
     */
    @Test
    public void testInsertedElementIsFound() {
        instance.insert(1);
        instance.insert(3);
        instance.insert(2);
        assertTrue(instance.contains(2));
    }

    /**
     * Test that erasing an inserted element from the tree works.
     */
    @Test
    public void testInsertAndErase() {
        instance.insert(1);
        instance.insert(2);
        instance.erase(1);
        assertTrue(!instance.contains(1));
    }
    
    /**
     * Tests clearing the tree - if this works for AVL then we can assume it works for everything, since it's implemented in BinaryTree.
     */
    @Test
    public void testClear() {
        instance.insert(1);
        instance.insert(2);
        instance.clear();
        assertTrue(!instance.contains(2));
    }
}
