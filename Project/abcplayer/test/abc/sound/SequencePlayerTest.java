package abc.sound;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import org.junit.Test;

/**
 * Test some music playing stuff.
 * @category no_didit
 */

public class SequencePlayerTest {
    

    @Test

    public void testForPiece1(){   
    	
    	try{
             SequencePlayer player = new SequencePlayer(100, 8);
             
             //measure 1:   C C C3/4 D/4 E
             player.addNote(new Pitch('C').toMidiNote(), 0, 12);
             player.addNote(new Pitch('C').toMidiNote(), 12, 12);
             player.addNote(new Pitch('C').toMidiNote(), 24, 9);
             player.addNote(new Pitch('D').toMidiNote(), 33, 3);
             player.addNote(new Pitch('E').toMidiNote(), 36, 12); 
             
             //measure 2:  E3/4 D/4 E3/4 F/4 G2
             player.addNote(new Pitch('E').toMidiNote(), 48, 9);
             player.addNote(new Pitch('D').toMidiNote(), 57, 3);
             player.addNote(new Pitch('E').toMidiNote(), 60, 9);
             player.addNote(new Pitch('F').toMidiNote(), 69, 3);
             player.addNote(new Pitch('G').toMidiNote(), 72, 24);
             
             //measure 3:   (3c/2c/2c/2 (3G/2G/2G/2 (3E/2E/2E/2 (3C/2C/2C/2
             player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 4);
             player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 100, 4);
             player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 104, 4);
             player.addNote(new Pitch('G').toMidiNote(), 108, 4);
             player.addNote(new Pitch('G').toMidiNote(), 112, 4);
             player.addNote(new Pitch('G').toMidiNote(), 116, 4);
             player.addNote(new Pitch('E').toMidiNote(), 120, 4);
             player.addNote(new Pitch('E').toMidiNote(), 124, 4);
             player.addNote(new Pitch('E').toMidiNote(), 128, 4);
             player.addNote(new Pitch('C').toMidiNote(), 132, 4);
             player.addNote(new Pitch('C').toMidiNote(), 136, 4);
             player.addNote(new Pitch('C').toMidiNote(), 140, 4);
             
             //measure 4:  G3/4 F/4 E3/4 D/4 C2
             player.addNote(new Pitch('G').toMidiNote(), 144, 9);
             player.addNote(new Pitch('F').toMidiNote(), 153, 3);
             player.addNote(new Pitch('E').toMidiNote(), 156, 9);
             player.addNote(new Pitch('D').toMidiNote(), 165, 3);
             player.addNote(new Pitch('C').toMidiNote(), 168, 24);

             //System.out.println(player);
             // play!
             /*player.play();
             try {
				System.in.read();
             } catch (IOException e) {
				e.printStackTrace();
             }*/


             /*
              * Note: A possible weird behavior of the Java sequencer: Even if the
              * sequencer has finished playing all of the scheduled notes and is
              * manually closed, the program may not terminate. This is likely
              * due to daemon threads that are spawned when the sequencer is
              * opened but keep on running even after the sequencer is killed. In
              * this case, you need to explicitly exit the program with
              * System.exit(0).
              */
        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        }

    }
    
    @Test
    public void testForPiece2() {
       try{
            SequencePlayer player = new SequencePlayer(200, 12);
            /*
             * measure 1: [e/2 ^F/2]:| z/2 [e/2 ^F/2] z/2 [c/2 ^F/2] [E ^F]
             */
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 0, 6);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 0, 6);            
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 6, 6);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 6, 6);            
            // player.addNote(new Pitch('z').toMidiNote(), 12, 6);           
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 18, 6);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 18, 6);           
            // player.addNote(new Pitch('z').toMidiNote(), 24, 6);            
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 30, 6);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 30, 6);            
            player.addNote(new Pitch('E').toMidiNote(), 36, 12);
            player.addNote(new Pitch('F').transpose(1).toMidiNote(), 36, 12);
            
            /*
             * measure 2: [B G g] z G z
             */           
            player.addNote(new Pitch('B').toMidiNote(), 48, 12);
            player.addNote(new Pitch('G').toMidiNote(), 48, 12);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 48, 12);
            // player.addNote(new Pitch('z').toMidiNote(), 60, 12);
            player.addNote(new Pitch('G').toMidiNote(), 72, 12);
            // player.addNote(new Pitch('z').toMidiNote(), 84, 12);
            
            /*
             * measure 3: c3/2 G/2 z E
             */
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 96, 18);
            player.addNote(new Pitch('G').toMidiNote(), 114, 6);
            // player.addNote(new Pitch('z').toMidiNote(), 120, 12);
            player.addNote(new Pitch('E').toMidiNote(), 132, 12);
                        
            /*
             * measure 4: E/2 A B _B/2 A
             */
            player.addNote(new Pitch('E').toMidiNote(), 144, 6);
            player.addNote(new Pitch('A').toMidiNote(), 150, 12);
            player.addNote(new Pitch('B').toMidiNote(), 162, 12);
            player.addNote(new Pitch('B').transpose(-1).toMidiNote(), 174, 6);
            player.addNote(new Pitch('A').toMidiNote(), 180, 12);
            
            /*
             * measure 5: (3Geg a f/2 g/2
             */
            player.addNote(new Pitch('G').toMidiNote(), 192, 8);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 200, 8);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 208, 8);
            player.addNote(new Pitch('A').transpose(Pitch.OCTAVE).toMidiNote(), 216, 12);
            player.addNote(new Pitch('F').transpose(Pitch.OCTAVE).toMidiNote(), 228, 6);
            player.addNote(new Pitch('G').transpose(Pitch.OCTAVE).toMidiNote(), 234, 6);
            
            /*
             * measure 6: z/2 e c/2 d/2 B3/4 z3/4
             */

            //player.addNote(new Pitch('z').toMidiNote(), 240, 6);
            player.addNote(new Pitch('E').transpose(Pitch.OCTAVE).toMidiNote(), 246, 12);
            player.addNote(new Pitch('C').transpose(Pitch.OCTAVE).toMidiNote(), 258, 6);
            player.addNote(new Pitch('D').transpose(Pitch.OCTAVE).toMidiNote(), 264, 6);
            player.addNote(new Pitch('B').toMidiNote(), 270, 9);
            //player.addNote(new Pitch('z').toMidiNote(), 279, 9);
            
            //System.out.println(player);

            // play!
           /* player.play();
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            /*
             * Note: A possible weird behavior of the Java sequencer: Even if the
             * sequencer has finished playing all of the scheduled notes and is
             * manually closed, the program may not terminate. This is likely
             * due to daemon threads that are spawned when the sequencer is
             * opened but keep on running even after the sequencer is killed. In
             * this case, you need to explicitly exit the program with
             * System.exit(0).
             */
            // System.exit(0);

        } catch (MidiUnavailableException mue) {
            mue.printStackTrace();
        } catch (InvalidMidiDataException imde) {
            imde.printStackTrace();
        }  
    }
}
