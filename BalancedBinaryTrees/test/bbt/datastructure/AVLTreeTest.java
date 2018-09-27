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
    private int avlConditionHelper(AVLTree tree) {
        if (tree != null) {
            int left = avlConditionHelper((AVLTree) tree.getLeftChild());
            int right = avlConditionHelper((AVLTree) tree.getRightChild());
            int height = Math.max(left, right) + 1;
            if (Math.abs(left - right) > 1) {
                fail("The height of left and right subtree differs by more than 1");
            }
            return height;
        }
        return 0;
    }
    
    /**
     * Checks that the heap condition actually holds for the tree
     * @param tree AVL Tree to be checked
     * @return Returns true if the condition holds
     */
    private int heightCheckHelper(AVLTree tree) {
        try {
            if (tree != null) {
                Method method = tree.getClass().getDeclaredMethod("getHeight");
                method.setAccessible(true);
                int left = heightCheckHelper((AVLTree) tree.getLeftChild());
                int right = heightCheckHelper((AVLTree) tree.getRightChild());
                int height = Math.max(left, right) + 1;
                if (height != (int) method.invoke(tree)) {
                    fail("The height variable of the tree is not correct");
                }
                return height;
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
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
     * Test AVL condition
     */
    @Test
    public void testAVLCondition() {
        instance.insert(0);
        instance.insert(2);
        instance.insert(1);
        avlConditionHelper(instance);
    }
    
    /**
     * Test heights are correct
     */
    @Test
    public void testHeightCorrectness() {
        instance.insert(0);
        instance.insert(2);
        instance.insert(1);
        instance.insert(3);
        instance.erase(0);
        heightCheckHelper(instance);
    }
    
    /**
     * Tests clearing the tree - if this works for AVL then we can assume it works for everything, since it's implemented in BinaryTree
     */
    @Test
    public void testClear() {
        instance.insert(1);
        instance.insert(2);
        instance.clear();
        assertTrue(!instance.contains(2) && !instance.contains(1));
    }
    
    /**
     * Test inserting the same element and then erasing
     */
    @Test
    public void testInsertAndEraseSame() {
        instance.insert(1);
        instance.insert(1);
        instance.erase(1);
        assertTrue(instance.contains(1));
    }
}
