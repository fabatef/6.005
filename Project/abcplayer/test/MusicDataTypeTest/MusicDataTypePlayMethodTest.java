package MusicDataTypeTest;

import static org.junit.Assert.assertEquals;

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


/**
 * Test the play method of the MusicDataType.
 * @category no_didit
 */
public class MusicDataTypePlayMethodTest {
    
    Pitch E = new Pitch('E');
    Pitch C = new Pitch('C');
    Pitch D = new Pitch('D');
    
    Note noteE = new Note(E, 1, 1);
    Note noteC = new Note(C, 3, 1);
    Note noteD = new Note(D, 0, 1);
    
    Music noteSmallC = new Note(new Pitch('C').transpose(12), 2, 1);
    Music restOfNumTicks6 = new Rest(6, 2);
    Music Rest2 = new Rest(1, 1);
    Music Rest1= new Rest(2,1);
    Music concatNoteERest = new Concat(noteE, restOfNumTicks6);
    Music concatNoteENoteSmallC = new Concat(noteE, noteSmallC);


    @Test
    public void notePlayTest() throws MidiUnavailableException, InvalidMidiDataException{
        SequencePlayer player1 = new SequencePlayer(100, 8);
        player1.addNote(new Pitch('C').toMidiNote(), 0, 3);       
        SequencePlayer player2 = new SequencePlayer(100, 8);
        Note noteC = new Note(C, 3, 1);
        noteC.play(player2, 0);
        assertEquals(player1.toString(), player2.toString());  
    }
    
    
    // Testing play method on music objects with the same length

    @Test
    public void togetherPlaySameLengthNodesTest() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player1 = new SequencePlayer(100, 8);
        player1.addNote(new Pitch('C').toMidiNote(), 0, 3);
        player1.addNote(new Pitch('D').toMidiNote(), 0, 3);
        
        SequencePlayer player2 = new SequencePlayer(100, 8);
        Music noteD = new Note(new Pitch('D'), 3, 1);
        Music noteC = new Note(new Pitch('C'), 3, 1);
        Together noteCAndD = new Together(noteC, noteD);
        noteCAndD.play(player2, 0);
        
        assertEquals(player1.toString(), player2.toString());
    }
    
    // Testing play on music objects with different length

    @Test
    public void togetherPlaySameDifferentNodesTest() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player1 = new SequencePlayer(100, 8);
        player1.addNote(new Pitch('C').toMidiNote(), 0, 3);
        player1.addNote(new Pitch('D').toMidiNote(), 0, 3);
        
        SequencePlayer player2 = new SequencePlayer(100, 8);
        Music noteD = new Note(new Pitch('D'), 3, 1);
        Music noteC = new Note(new Pitch('C'), 3, 1);
        Together noteCAndD = new Together(noteC, noteD);
        noteCAndD.play(player2, 0);
        
        assertEquals(player1.toString(), player2.toString());
    }
    
    // Testing play on a startTick not equals to 0
    
    @Test
    public void togetherPlayDifferentStartTick() throws MidiUnavailableException, InvalidMidiDataException {
        SequencePlayer player1 = new SequencePlayer(100, 8);
        player1.addNote(new Pitch('C').toMidiNote(), 1, 3);
        
        SequencePlayer player2 = new SequencePlayer(100, 8);
        Music noteC = new Note(new Pitch('C'), 3, 1);
        noteC.play(player2, 1);
        
        assertEquals(player1.toString(), player2.toString());
    }
    
    
    //Partition 3: set equal to original numTicks
    @Test
    public void setEqualNumTicks(){
        Concat concatCE = new Concat(noteE, noteSmallC);
        int numTicksCE= concatCE.numTicks();
        Music newConcatCE= concatCE.setNumTicks(numTicksCE);
        assertEquals((numTicksCE)*2, newConcatCE.numTicks());
    }
    
    //Testing play on concat
    
    @Test
    public void testPlay() throws MidiUnavailableException, InvalidMidiDataException{
        SequencePlayer player1= new SequencePlayer(100,8);
        SequencePlayer player2= new SequencePlayer(100, 8);
        
        Music concatCE = new Concat(noteE, noteSmallC);
        
        concatCE.play(player1, 0);
        noteE.play(player2, 0);
        noteSmallC.play(player2, noteE.numTicks());
        
        assertEquals(player2.toString(),player1.toString());

    }  
    
}
    