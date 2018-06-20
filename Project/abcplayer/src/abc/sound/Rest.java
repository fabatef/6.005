package abc.sound;

/**
 * Rest represents a pause in a piece of music.
 */
public class Rest implements Music {
    
    /*
     * Abstraction function:
     *  Rest represents a pause in a piece of music
     *  
     * Rep invariant:
     *  numTicks >= 0
     *  
     * Safety from rep exposure:
     *  all fields are immutable and final
     */
    
    private final int numTicks;
    private final int numTicksPerBeat;
    
    private void checkRep() {
        assert numTicks >= 0;
    }
    
    /**
     * Make a Rest that lasts for duration ticks.
     * @param numTicks duration in ticks, must be >= 0
     */
    public Rest(int numTicks, int numTicksPerBeat) { 
        this.numTicks = numTicks;
        this.numTicksPerBeat= numTicksPerBeat;
        checkRep();
    }
    
    /**
     * Transpose this rest.
     */
    public Music transpose(int semitonesUp) {
        return this;
    }
    
    /**
     * Play this rest.
     */
    public void play(SequencePlayer player, int startTick) {
        return;
    }
    
    @Override
    public int hashCode() {
        long durationBits = Double.doubleToLongBits(numTicks);
        return (int) (durationBits ^ (durationBits >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Rest other = (Rest) obj;
        return numTicks == other.numTicks;
    }
    
    @Override
    public String toString() {
        return "z" + numTicks/(double) numTicksPerBeat;
    }

	@Override
	public int numTicks() {
		return numTicks;
	}

	@Override
	public Music setNumTicks(int newNumTicks) {
		return new Rest(newNumTicks, numTicksPerBeat);
	}

	@Override
	public Music key(String keySig) {
		return this;
	}
}
