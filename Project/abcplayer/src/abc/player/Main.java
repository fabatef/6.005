package abc.player;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import abc.parser.MakeBody;
import abc.sound.Music;
import abc.sound.SequencePlayer;
/**
 * Main entry point of your application.
 */
public class Main {

    /**
     * Plays the input file using Java MIDI API and displays
     * header information to the standard output stream.
     * 
     * (Your code should not exit the application abnormally using
     * System.exit().)
     * 
     * @param file the name of input abc file
     * @throws IOException 
     * @throws InvalidMidiDataException 
     * @throws MidiUnavailableException 
     */
    public static void play(String file) throws IOException, MidiUnavailableException, InvalidMidiDataException {
        File abcfile= new File(file);
        Map<String, String> headerInfo = Music.parseHeader(abcfile);
        Music music = Music.parseBody(abcfile).key(headerInfo.get("fieldKey")); //handling key signature
        int fieldTempo = 100;
        double defaultLength = 0.125;
        if (headerInfo.containsKey("fieldTempo")) {
        	fieldTempo = Integer.valueOf(headerInfo.get("fieldTempo"));
    		double tempoSpecLength= Double.valueOf(headerInfo.get("fieldTempoMeterFraction"));
        	if (headerInfo.containsKey("fieldDefaultLength")) {
        		defaultLength= Double.valueOf(headerInfo.get("fieldDefaultLength"));
        		}
    		if(! (defaultLength==tempoSpecLength)){
    			fieldTempo= (int) ((fieldTempo * tempoSpecLength)/defaultLength);
        	}
    
        }      
        SequencePlayer player = new SequencePlayer(fieldTempo, MakeBody.numTicksPerBeat);
        music.play(player, 0);
        System.out.println(player);
        player.play();

    }

    public static void main(String[] args) throws IOException, MidiUnavailableException, InvalidMidiDataException {
        // CALL play() HERE USING ARGS
    	/*for (String filename: args){
    	    play(filename);
    	}*/
    	
    	String filename = "sample_abc/little_night_music.abc";
    	play(filename);
    }
}
