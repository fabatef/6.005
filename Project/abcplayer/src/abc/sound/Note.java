package abc.sound;

import java.util.HashMap;
import java.util.Map;

/**
 * Note represents a note played by an instrument. 
 */
public class Note implements Music {
    /*
     * Abstraction function:
     *  Note represents a note played by an instrument
     *  
     * Rep invariant:
     *  numTicks >= 0
     *  
     * Safety from rep exposure:
     *  all fields are immutable and final
     */
    private  int numTicks;
    private final int numTicksPerBeat;
    private final Pitch pitch;
    public static final Map<String, String> keySigMapping= new HashMap<String,String>();
    private boolean accidentalApplied;
    static{
        
//      F major has one flat, Bb
        keySigMapping.put("F","_B,");
//      _B major has two flats, Bb Eb
        keySigMapping.put("_B","_B,_E,");
//      _E major has three flats, Bb Eb Ab
        keySigMapping.put("_E","_B,_E,_A,");
//      _A major has four flats, Bb Eb Ab Db
        keySigMapping.put("_A","_B,_E,_A,_D,");
//      _D major has five flats, Bb Eb Ab Db Gb
        keySigMapping.put("_D","_B,_E,_A,_D,_G,");
//      _G major has six flats, Bb Eb Ab Db Gb Cb
        keySigMapping.put("_G","_B,_E,_A,_D,_G,_C,");
//      _C major has seven flats, Bb Eb Ab Db Gb Cb Fb
        keySigMapping.put("_C","_B,_E,_A,_D,_G,_C,_F"); 
              	
//    	C major has no sharps or flats
        
//    	G major has one sharp F#
    	keySigMapping.put("G","^F,");
//    	D major has two sharps, F# C#
    	keySigMapping.put("D","^F,^C,");
//    	A major has three sharps, F# C# G#
    	keySigMapping.put("A","^F,^C,^G,");
//    	E major has four sharps, F# C# G# D#
        keySigMapping.put("E","^F,^C,^G,^D,");
//    	B major has five sharps, F# C# G# D# A#
        keySigMapping.put("B","^F,^C,^G,^D,^A,");
//    	F# major has six sharps, F# C# G# D# A# E#
        keySigMapping.put("F#","^F,^C,^G,^D,^A,^E,");
//    	C# major has seven sharps, F# C# G# D# A# E# B#
    	keySigMapping.put("C#","^F,^C,^G,^D,^A,^E,^B,"); 
    	
    	keySigMapping.put("Em", keySigMapping.get("G"));
        keySigMapping.put("Bm", keySigMapping.get("D"));
        keySigMapping.put("F#m", keySigMapping.get("A"));
        keySigMapping.put("C#m", keySigMapping.get("E"));
        keySigMapping.put("G#m", keySigMapping.get("B"));
        keySigMapping.put("_Am", keySigMapping.get("B"));
        keySigMapping.put("_Em", keySigMapping.get("F#"));
        keySigMapping.put("_Bm", keySigMapping.get("C#"));
        keySigMapping.put("Fm", keySigMapping.get("_A"));
        keySigMapping.put("Cm", keySigMapping.get("_E"));
        keySigMapping.put("Gm", keySigMapping.get("_B"));
        keySigMapping.put("D#m", keySigMapping.get("F#"));
        keySigMapping.put("A#m", keySigMapping.get("C#"));
    }
    
    private void checkRep() {
        assert numTicks >= 0;
        assert pitch != null;
    }
    
    /**
     * Make a Note played by instrument for duration ticks.
     * @param numTicks duration in ticks, must be >= 0
     * @param pitch pitch to play
     * @param accidentalApplied true if accidental has been applied to the Note, otherwise false
     */
    public Note(Pitch pitch, int numTicks, int numTicksPerBeat, boolean accidentalApplied) {
        this.numTicks = numTicks;
        this.numTicksPerBeat= numTicksPerBeat;
        this.pitch = pitch;
        this.accidentalApplied = accidentalApplied;
        checkRep();
    }
    /**
     * Make a Note played by instrument for duration ticks.
     * @param pitch pitch to play
     * @param numTicks duration in ticks, must be >= 0
     * @param numTicksPerBeat number of ticks per beat
     */
    public Note(Pitch pitch, int numTicks, int numTicksPerBeat) {
        this.numTicks = numTicks;
        this.numTicksPerBeat= numTicksPerBeat;
        this.pitch = pitch;
        this.accidentalApplied = false;
        checkRep();
    }
    
    /**
     * @return pitch of this note
     */
    public Pitch pitch() {
        return pitch;
    }
    
    @Override
    public int numTicks(){
    	return numTicks;
    }
    
    public Music setNumTicks(int newNumTicks){
    	return new Note(pitch, newNumTicks, numTicksPerBeat, accidentalApplied);
    }
    
    /**
     * Transpose this note.
     */
    public Music transpose(int semitonesUp) {
        return new Note(pitch.transpose(semitonesUp), numTicks, numTicksPerBeat, accidentalApplied);
    }
    
    /**
     * Play this note.
     */
    public void play(SequencePlayer player, int startTick) {
        player.addNote(pitch.toMidiNote() , startTick, numTicks);
    }

    @Override
    public int hashCode() {
        long durationBits = Double.doubleToLongBits(numTicks);
        return (int) (durationBits ^ (durationBits >>> 32))
                + pitch.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Note other = (Note) obj;
        return numTicks == other.numTicks
                && pitch.equals(other.pitch);
    }

    @Override
    public String toString() {
        return pitch.toString() + numTicks/(double) numTicksPerBeat;
    }

	@Override
	public Music key(String keySig) {
		
		Music returnVal= this;
		if(!(keySigMapping.containsKey(keySig)) | accidentalApplied){
			return returnVal;
		}
		String[] notesToHandle= keySigMapping.get(keySig).split(",");
		for (int i= 0; i< notesToHandle.length; i++){
			if(pitch.toString().equals(String.valueOf(notesToHandle[i].charAt(1))) && notesToHandle[i].startsWith("^")){
				returnVal= this.transpose(1);
			}else if (pitch.toString().equals(String.valueOf(notesToHandle[i].charAt(1))) && notesToHandle[i].startsWith("_")){
				returnVal= this.transpose(-1);
			}
		}	
		return returnVal;
	}
}
