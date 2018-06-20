package abc.sound;

public class Concat implements Music {
    /*
     * Abstraction Function:
     * Concat represents two pieces of music played one after the other.
     * Hence the starting time of the second music is the end time of the first
     * 
     * Rep invariant: true
     * 
     * Safety Argument
     * all fields are private, final and immutable
     */
    private final Music first;
    private final Music second;
    
    private void checkRep() {
        assert first != null;
        assert second != null;
    }
    
    /**
     * Make a Music sequence that plays m1 followed by m2.
     * @param m1 music to play first
     * @param m2 music to play second
     */
    public Concat(Music m1, Music m2) {
        this.first = m1;
        this.second = m2;
        checkRep();
    }
    
    /**
     * @return first piece in this concatenation
     */
    public Music first() {
        return first;
    }
    
    /**
     * @return second piece in this concatenation
     */
    public Music second() {
        return second;
    }
    
    /**
     * Transpose the pieces in this concatenation.
     */
    public Music transpose(int semitonesUp) {
        return new Concat(first.transpose(semitonesUp), second.transpose(semitonesUp));
    }
    
    /**
     * Play this concatenation.
     */
    public void play(SequencePlayer player, int startTick) {
        first.play(player,startTick);
        second.play(player, startTick
        		+ first.numTicks());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return first.hashCode() + prime * second.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Concat other = (Concat) obj;
        return first.equals(other.first) && second.equals(other.second);
    }

    @Override
    public String toString() {
        return first + " " + second;
    }

	@Override
	public int numTicks() {
		return first.numTicks() + second.numTicks();
	}

    @Override
    public Music setNumTicks(int newNumTicks) {
        return new Concat(first.setNumTicks(newNumTicks), second.setNumTicks(newNumTicks));
    }

	@Override
	public Music key(String keySig){
		return new Concat(first.key(keySig), second.key(keySig));
	}
}
