package bbt.datastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreapTest {
    private Treap<Integer> instance;
    
    @Before
    public void setUp() {
        instance = new Treap<>();
    }
    
    /**
     * Checks that the heap condition actually holds for the treap
     * @param treap Treap to be checked
     * @return Returns true if the condition holds
     */
    private boolean heapConditionHelper (Treap treap) {
        try {
            if (treap == null) return true;
            try {
                Method method = treap.getClass().getDeclaredMethod("getPriority");
                method.setAccessible(true);
                if (treap.getLeftChild() != null) {
                    if ((int)method.invoke((Treap)treap.getLeftChild()) > (int)method.invoke(treap)) {
                        return false;
                    }
                }
                if (treap.getRightChild() != null) {
                    if ((int)method.invoke((Treap)treap.getRightChild()) > (int)method.invoke(treap)) {
                        return false;
                    }
                }
                return heapConditionHelper((Treap)treap.getLeftChild()) && heapConditionHelper((Treap)treap.getRightChild());
            } catch (IllegalAccessException ex) {
                Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
    
    /**
     * Test heap condition
     */
    @Test
    public void testHeapCondition() {
        instance.insert(0);
        instance.insert(1);
        instance.insert(2);
        instance.insert(3);
        instance.insert(4);
        instance.insert(8);
        instance.insert(7);
        instance.insert(6);
        instance.insert(5);
        instance.insert(0);
        instance.insert(1);
        instance.insert(2);
        instance.insert(3);
        instance.insert(4);
        instance.insert(8);
        instance.insert(7);
        instance.insert(6);
        instance.insert(5);
        instance.erase(4);
        instance.erase(6);
        assertTrue(heapConditionHelper(instance));
    }
}
