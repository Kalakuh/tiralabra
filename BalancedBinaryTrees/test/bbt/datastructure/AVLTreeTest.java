package bbt.datastructure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Checks that the heap condition actually holds for the tree
     * @param tree AVL Tree to be checked
     * @return Returns true if the condition holds
     */
    private int avlConditionHelper (AVLTree tree) {
        try {
            if (tree == null) return 0;
            try {
                Method method = tree.getClass().getDeclaredMethod("getHeight");
                method.setAccessible(true);
                int left = avlConditionHelper((AVLTree)tree.getLeftChild());
                int right = avlConditionHelper((AVLTree)tree.getRightChild());
                int height = Math.max(left, right) + 1;
                if (Math.abs(left - right) > 1) {
                    fail("The height of left and right subtree differs by more than 1");
                }
                if (height != (int)method.invoke(tree)) {
                    fail("The height variable of the tree is not correct");
                }
                return height;
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(TreapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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
     * Test AVL condition
     */
    @Test
    public void testAVLConditionSmall() {
        instance.insert(0);
        instance.insert(1);
        avlConditionHelper(instance);
    }   
    
    /**
     * Test AVL condition
     */
    @Test
    public void testAVLConditionLarger() {
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
        instance.erase(5);
        instance.insert(5);
        avlConditionHelper(instance);
    }
    
    /**
     * Tests clearing the tree - if this works for AVL then we can assume it works for everything, since it's implemented in BinaryTree
     */
    @Test
    public void testClear () {
        instance.insert(1);
        instance.insert(2);
        instance.clear();
        assertTrue(!instance.contains(2));
    }
    
    /**
     * Test inserting the same element and then erasing
     */
    @Test
    public void testInsertAndEraseSame () {
        instance.insert(1);
        instance.insert(1);
        instance.erase(1);
        assertTrue(instance.contains(1));
    }
}
