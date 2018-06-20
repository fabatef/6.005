package MusicDataTypeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import abc.sound.Concat;
import abc.sound.Music;
import abc.sound.Note;
import abc.sound.Pitch;
import abc.sound.Rest;
import abc.sound.SequencePlayer;
import abc.sound.Together;


public class TogetherTest {
    //testing strategy
    //    Partitions:
    //    Partition for Together(m1,m2) -> result:
    //      partitioning over length of each input and output music object
    //      m1.numTicks() = m2.numTicks()
    //      m1.numTicks() < m2.numTicks()
    //      m1.numTicks() > m2.numTicks()
    //      result.numTIcks = m1.numTicks(), result.numTicks() = m2.numTicks(), or both
    // use observer to test various methods of Together
    // transpose
    //    Together(m1,m2).transpose(x) equals Together(m1.transpose(x), m2.transpose(x))
    //
    // hashCode
    //    Are equal if two Together musics are equal
    //
    //play method
    //
  
    Music noteE = new Note(new Pitch('E'), 2, 1);
    Music noteD = new Note(new Pitch('D'), 3, 1);
    Music noteC = new Note(new Pitch('C'), 3, 1);
    Music noteSmallC = new Note(new Pitch('C').transpose(12), 2, 1);
    Music restOfNumTicks6 = new Rest(6, 2);
    Music Rest2 = new Rest(1, 1);
    Music concatNoteERest = new Concat(noteE, restOfNumTicks6);
    Music concatNoteENoteSmallC = new Concat(noteE, noteSmallC);   
    
    
    //This test cover 
    //m1.numTicks() = m2.numTicks(), result.numTicks() = m1.numTicks() = m2.numTicks()
  
    @Test
    public void twoEqualDurationMusicTogther() {
        Music chordCE = new Together(noteE, noteSmallC);
        assertEquals("Expected numTicks should be equal to that of noteE", noteE.numTicks(), 
                        chordCE.numTicks());
        assertEquals("Expected numTicks should be equal to that of noteSmallC", noteSmallC.numTicks(), 
                chordCE.numTicks());        
    }  
    
    //This test cover
    //m1.numTicks() > m2.numTicks(), result.numTicks() = m1.numTicks
    @Test
    public void firstMusicLongerThanSecondTogether() {
        Music noteEWithRestOfNumTicks6 = new Together(restOfNumTicks6, Rest2);
        assertEquals(restOfNumTicks6.numTicks(), noteEWithRestOfNumTicks6.numTicks());
        assert(!(Rest2.numTicks() == noteEWithRestOfNumTicks6.numTicks()));
    }
    
    //This test cover
    //m1.numTicks() < m2.numTicks(), result.numTicks() = m2.numTicks
    @Test
    public void secondMusicLongerThanfirstTogether() {
       Music twoConcats = new Together(concatNoteENoteSmallC, concatNoteERest);
       assertEquals(concatNoteERest.numTicks(), twoConcats.numTicks());
       assert(!(concatNoteENoteSmallC.numTicks() == twoConcats.numTicks()));    }

    // Test covers Together of more than two music elements
    @Test
    public void moreThanTwoMusics() {
        Music threeMusicObjectsTogether = new Together(new Together(noteE, noteSmallC), restOfNumTicks6);
        assert threeMusicObjectsTogether.numTicks() == Math.max(noteE.numTicks(), 
                                                            Math.max(noteSmallC.numTicks(), restOfNumTicks6.numTicks()));
        
    }
    
    //Test covers transpose of result and Equals methods
    @Test
    public void transposeTogether() {
        Music transposedTogether = new Together(noteE, concatNoteENoteSmallC).transpose(1);
        Music togetherOfTransposedMusic = new Together(noteE.transpose(1), concatNoteENoteSmallC.transpose(1));
        assertEquals("Should be the same", transposedTogether, togetherOfTransposedMusic);
    }
    
    
    //Test covers hashCode method and Equals method
//    Order matters for together of music element but doesn't for same element 
    @Test
    public void orderOfDifferentMusicTogether() {
        Music TogetherOfEAndC = new Together(noteE, new Note(new Pitch('C'), 3, 1));
        Music TogetherOfCAndA = new Together(new Note(new Pitch('C'), 3, 1), noteE);
        assertFalse(TogetherOfCAndA.equals(TogetherOfEAndC));
    }
    
    @Test
    public void orderOfSameMusicTogether() {
        Music firstTogetherOfEAndC = new Together(noteE, new Note(new Pitch('C'), 3, 1));
        Music secondTogetherOfEAndC = new Together(new Note(new Pitch('E'), 2, 1), noteC);
        assertEquals("Should be the same", firstTogetherOfEAndC,secondTogetherOfEAndC);
        assertEquals("should have the same hasCode", firstTogetherOfEAndC.hashCode(), secondTogetherOfEAndC.hashCode());
    }
    
    
    //Test top and Bottom Methods in Together
    @Test
    public void topMethdOfTogether() {
        Together together = new Together(noteE, concatNoteENoteSmallC);
        assertEquals("first music in together is noteE", noteE, together.top());
        assertEquals("second music in together is concatNoteENoteSmallC", concatNoteENoteSmallC, together.bottom());
    }
    


}
