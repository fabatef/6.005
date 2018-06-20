package MusicDataTypeTest;

import static org.junit.Assert.*;

import org.junit.Test;

import abc.sound.Music;
import abc.sound.Rest;

public class RestTest {
    
    /*
     * Testing strategy
     * 
     * Partitions for Rest:
     *  1. numTicks: 0, 1, >1
     * 
     *  1. Music transpose(int semitonesUp)
     *      semitonesUp = 1, -1, 2, -2, 12, -12 
     *  
     *  2. void play(SequencePlayer player, int startTick)
     *  
     */
    
    Music restZero = new Rest(0, 4);
    Music restOne = new Rest(1, 4);
    Music restTwo = new Rest(2, 4);
    
    @Test 
    public void restZeroNumTickTest(){
        assertEquals(0, restZero.numTicks());        
    }
    
    @Test 
    public void restOneNumTickTest(){
        assertEquals(1, restOne.numTicks());        
    }
    
    @Test 
    public void restTwoNumTicksTest(){
        assertEquals(2, restTwo.numTicks());        
    }
    
    @Test
    public void restZeroTransposeTest(){
        assertEquals(restZero, restZero.transpose(1));
        assertEquals(restZero, restZero.transpose(-1));
        assertEquals(restZero, restZero.transpose(2));
        assertEquals(restZero, restZero.transpose(-2));
        assertEquals(restZero, restZero.transpose(12));
        assertEquals(restZero, restZero.transpose(-12));
    }
    
    @Test
    public void restOneTransposeTest(){
        assertEquals(restOne, restOne.transpose(1));
        assertEquals(restOne, restOne.transpose(-1));
        assertEquals(restOne, restOne.transpose(2));
        assertEquals(restOne, restOne.transpose(-2));
        assertEquals(restOne, restOne.transpose(12));
        assertEquals(restOne, restOne.transpose(-12));
    }
    
    @Test
    public void restTwoTransposeTest(){
        assertEquals(restTwo, restTwo.transpose(1));
        assertEquals(restTwo, restTwo.transpose(-1));
        assertEquals(restTwo, restTwo.transpose(2));
        assertEquals(restTwo, restTwo.transpose(-2));
        assertEquals(restTwo, restTwo.transpose(12));
        assertEquals(restTwo, restTwo.transpose(-12));
    }
    
    /*
     * Test for equality, toString and hashCode TODO
     */
    
    @Test
    public void restToStringTest(){
        assertEquals("z0.0", restZero.toString());
        assertEquals("z0.25", restOne.toString());
        assertEquals("z0.5", restTwo.toString());
    }
    
    @Test
    public void restEqualityTest(){
        assertTrue(restZero.equals(restZero));
        assertTrue(restOne.equals(restOne));
        assertTrue(restTwo.equals(restTwo));
    }
    
    @Test
    public void restHashCodeTest(){
        assertEquals(restZero.hashCode(), restZero.hashCode());
        assertEquals(restOne.hashCode(), restOne.hashCode());
        assertEquals(restTwo.hashCode(), restTwo.hashCode());
    }
}
