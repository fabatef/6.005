package abc.sound;

public class Together implements Music {
    /* Abstraction Function:
     * Together represents two pieces of music playing at the same time.
     * The pieces start at the same instant, but may end at different times.
     * 
     * Rep invariant: true
     * 
     * Safety from rep exposure:
     * all fields are immutable, private and final
     * 
     */
    final private Music m1;
    final private Music m2;

    private void checkRep() {
        assert m1 != null;
        assert m2 != null;
    }
    
    /**
     * Make a Together of two pieces of music.
     * @param m1 one piece of music
     * @param m2 another piece of music
     */
    public Together(Music m1, Music m2) {
        this.m1 = m1;
        this.m2 = m2;
        checkRep();
    }
    
    /**
     * @return one of the pieces of music in this together
     */
    public Music top() {
        return m1;
    }
    
    /**
     * @return the other piece of music in this together
     */
    public Music bottom() {
        return m2;
    }
    /**
     * Transpose the pieces in this Together.
     */
    public Music transpose(int semitonesUp) {
        checkRep();
        return new Together(m1.transpose(semitonesUp), m2.transpose(semitonesUp));
    }
    
    /**
     * Play the pieces of this Together, together.
     */
    public void play(SequencePlayer player, int startTick) {
        m1.play(player, startTick);
        m2.play(player, startTick);
        checkRep();
    }
    
    @Override
    public int hashCode() {
        checkRep();
        final int prime = 31;
        return m1.hashCode() + prime * m2.hashCode();

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Together other = (Together) obj;
        checkRep();
        return m1.equals(other.m1) && m2.equals(other.m2);
    }

    @Override
    public String toString() {
        return "together(" + m1.toString() + " |||| " + m2.toString() + ")";
    }

	@Override
	public int numTicks() {
		return Math.max(m1.numTicks(), m2.numTicks());
	}

    @Override
    public Music setNumTicks(int newNumTicks) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Music key(String keySig) {
		
		return new Together(m1.key(keySig), m2.key(keySig));
	}
}
