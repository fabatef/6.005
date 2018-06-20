import java.util.List;

import javax.swing.SwingUtilities;

/**
 * Represents an immutable single-elimination tournament.
 */
public interface Tournament {
    
    /**
     * @param player the only player in the tournament
     * @return tournament with only the given player
     */
    public static Tournament single(Player player) {
        return new Bye(player);
    }
    
    /**
     * @param t1, t2
     * @return 
     */
    public static Tournament match(Tournament t1, Tournament t2) {
    	return new Match(t1, t2);
    }
    
    // TODO winner
    /**
     * 
     * @return winner of this tournament, the player with the higher skill at every game
     */
    public Player winner();
    public boolean balanced();
    
}

// in general, define every class in its own file
// but for this exercise, write your concrete variants below
// don't use the "public" modifier on the classes

class Bye implements Tournament {
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO fields
	private final Player player;
    
    // TODO constructor
	public Bye(Player p){
		this.player= p;
		
	}
   @Override 
	
	public Player winner(){
		return this.player;
	}
	
	@Override
	public boolean balanced(){
		return true;
	}
    
}

class Match implements Tournament {
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO fields
	private final Tournament t1;
	private final Tournament t2;
    
    // TODO constructor
	public Match(Tournament t1, Tournament t2){
		this.t1= t1;
		this.t2= t2;
	}
    
    // TODO @Override winner
    
	@Override
	public Player winner(){
		if (t1.winner().skill()> t2.winner().skill()){
			return t1.winner();
		} else{
			return t2.winner();
		}
	}
	
	@Override
	public boolean balanced(){
		if (t1.winner().skill()==t2.winner().skill()){
			return true;
		} else{
			return false;
		}
		
	}
	
}

