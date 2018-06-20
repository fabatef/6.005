package MusicDataTypeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import abc.sound.Note;
import abc.sound.Pitch;
import abc.sound.SequencePlayer;

public class NoteTest {
    
    /*
     * Testing strategy
     * 
     * Partitions for Note:
     *  1. numTicks: 0, 1, >1
     * 
     *  1. Music transpose(int semitonesUp)
     *      semitonesUp = 1, -1, 2, -2, 12, -12 
     * 
     */
    Pitch E = new Pitch('E');
    Pitch C = new Pitch('C');
    Pitch D = new Pitch('D');
    
    Note noteE = new Note(E, 1, 1);
    Note noteC = new Note(C, 3, 1);
    Note noteD = new Note(D, 0, 1);
    
    @Test 
    public void noteENumTickTest(){
        assertEquals(1, noteE.numTicks());        
    }
    
    @Test 
    public void noteCNumTickTest(){
        assertEquals(3, noteC.numTicks());        
    }
    
    @Test 
    public void noteDNumTicksTest(){
        assertEquals(0, noteD.numTicks());        
    }
    
    @Test
    public void noteETransposeTest(){
        assertEquals(new Note(E.transpose(1), 1, 1), noteE.transpose(1));
        assertEquals(new Note(E.transpose(-1), 1, 1), noteE.transpose(-1));
        assertEquals(new Note(E.transpose(2), 1, 1), noteE.transpose(2));
        assertEquals(new Note(E.transpose(-2), 1, 1), noteE.transpose(-2));
        assertEquals(new Note(E.transpose(12), 1, 1), noteE.transpose(12));
        assertEquals(new Note(E.transpose(-12), 1, 1), noteE.transpose(-12));
    }
    
    @Test
    public void noteCTransposeTest(){
        assertEquals(new Note(C.transpose(1), 3, 1), noteC.transpose(1));
        assertEquals(new Note(C.transpose(-1), 3, 1), noteC.transpose(-1));
        assertEquals(new Note(C.transpose(2), 3, 1), noteC.transpose(2));
        assertEquals(new Note(C.transpose(-2), 3, 1), noteC.transpose(-2));
        assertEquals(new Note(C.transpose(12), 3, 1), noteC.transpose(12));
        assertEquals(new Note(C.transpose(-12), 3, 1), noteC.transpose(-12));
    }
    
    @Test
    public void noteDTransposeTest(){
        assertEquals(new Note(D.transpose(1), 0, 1), noteD.transpose(1));
        assertEquals(new Note(D.transpose(-1), 0, 1), noteD.transpose(-1));
        assertEquals(new Note(D.transpose(2), 0, 1), noteD.transpose(2));
        assertEquals(new Note(D.transpose(-2), 0, 1), noteD.transpose(-2));
        assertEquals(new Note(D.transpose(12), 0, 1), noteD.transpose(12));
        assertEquals(new Note(D.transpose(-12), 0, 1), noteD.transpose(-12));
    }
    
    /*
     * Test for equality, toString and hashCode
     */
    
    @Test
    public void noteToStringTest(){
        assertEquals("E1.0", noteE.toString());
        assertEquals("C3.0", noteC.toString());
        assertEquals("D0.0", noteD.toString());
    }
    
    @Test
    public void noteEqualityTest(){
        assertTrue(noteE.equals(noteE));
        assertTrue(noteC.equals(noteC));
        assertTrue(noteD.equals(noteD));
    }
    
    @Test
    public void noteHashCodeTest(){
        assertEquals(noteE.hashCode(), noteE.hashCode());
        assertEquals(noteC.hashCode(), noteC.hashCode());
        assertEquals(noteD.hashCode(), noteD.hashCode());
    }
   
    

}
