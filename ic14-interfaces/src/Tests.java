import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() { assert false; }
    
    // Testing strategy
    //   TODO
    
    @Test
    public void testEmpty() {
        CharSet cs = new CharSet();
        assertEquals(0, cs.size());
        assertFalse(cs.contains('z'));
    }
    
    @Test
    public void testEmptyAddSingleton() {
        CharSet cs = new CharSet();
        cs.add('b'); // cs is now { b }
        assertEquals(1, cs.size());
        assertTrue(cs.contains('b'));
        assertFalse(cs.contains('z'));
    }
    
    @Test
    public void testSingletonRemoveEmpty() {
        CharSet cs = new CharSet();
        cs.add('b'); // cs is now { b }
        cs.remove('b'); // cs is now { }
        assertEquals(0, cs.size());
        assertFalse(cs.contains('b'));
        assertFalse(cs.contains('z'));
    }
    
    // TODO additional tests
    
}
