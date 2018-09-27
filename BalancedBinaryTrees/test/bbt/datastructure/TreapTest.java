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
    private boolean heapConditionHelper(Treap treap) {
        try {
            if (treap != null) {
                boolean correct = true;
                Method method = treap.getClass().getDeclaredMethod("getPriority");
                method.setAccessible(true);
                if (treap.getLeftChild() != null) {
                    correct = correct && (int) method.invoke((Treap) treap.getLeftChild()) <= (int) method.invoke(treap);
                }
                if (treap.getRightChild() != null) {
                    correct = correct && (int) method.invoke((Treap) treap.getRightChild()) <= (int) method.invoke(treap);
                }
                return correct && heapConditionHelper((Treap) treap.getLeftChild()) && heapConditionHelper((Treap) treap.getRightChild());
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
     * Test heap condition
     */
    @Test
    public void testHeapCondition() {
        instance.insert(0);
        instance.insert(1);
        instance.insert(2);
        instance.insert(3);
        instance.insert(4);
        instance.insert(5);
        instance.insert(6);
        assertTrue(heapConditionHelper(instance));
    }
}
