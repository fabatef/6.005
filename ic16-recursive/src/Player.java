/**
 * Represents a named player in a tournament with skill 0-100
 */
public class Player {
    
    // Abstraction function:
    //   represents a player with a name and some quantifiable level of skill
    // Representation invariant:
    //   player name should be a non-empty string
	//   0<= skill <=100
    // Safety from rep exposure:
    //   The fields are all private final and immutable
    
    // TODO fields
	private final String player;
	private final int skill;
    
    /**
     * TODO specification
     */
	
    public Player(String name, int skill) {
        this.player= name;
        this.skill= skill;
    }
    
    public void checkRep(){
    	assert( 0<= skill && skill <= 100);
    }
    
    // TODO methods
    
    public int skill(){
    	return this.skill ;
    }
    
    public String name(){
    	return this.player;
    }
    
    @Override
    public boolean equals(Object obj){
    	if (!(obj instanceof Player)){
    		return false;
    	}
    	Player that= (Player) obj;
    	
    	return this.name().equals(that.name()) && this.skill== that.skill;
    }
    
    @Override 
    public int hashCode(){
    	return 17;//name().hashCode() + skill;
    }
    
    @Override
    public String toString(){
    	return this.name() + " -> " + this.skill();
    }
    
 
    
}
