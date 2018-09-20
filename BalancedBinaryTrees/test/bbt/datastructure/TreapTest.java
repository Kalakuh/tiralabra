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
    
    private boolean heapConditionHelper (Treap t) {
        try {
            if (t == null) return true;
            try {
                Method method = t.getClass().getDeclaredMethod("getPriority");
                method.setAccessible(true);
                if (t.getLeftChild() != null) {
                    if ((int)method.invoke((Treap)t.getLeftChild()) > (int)method.invoke(t)) {
                        System.out.println("my " + (int)method.invoke(t) + " his " + (int)method.invoke((Treap)t.getLeftChild()));
                        return false;
                    }
                }
                if (t.getRightChild() != null) {
                    if ((int)method.invoke((Treap)t.getRightChild()) > (int)method.invoke(t)) {
                        System.out.println("my " + (int)method.invoke(t) + " his " + (int)method.invoke((Treap)t.getRightChild()));
                        return false;
                    }
                }
                return heapConditionHelper((Treap)t.getLeftChild()) && heapConditionHelper((Treap)t.getRightChild());
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
        assertTrue(heapConditionHelper(instance));
    }
}
