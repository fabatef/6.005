/**
 * A mutable set of characters.
 */
public interface Set<E> {
    
    // creators:
    /** Make an empty set.
     * 
     *  @param <E> type of elements in the set
     *  @return a new set instance, initially empty */
	
  /*  public static Set<Character> empty(){
    	
    	return new CharSet();
    }*/
    
    
 // observers:
	 /**
     * Get size of the set.
     * @return the number of elements in this set
     */
	public int size();
	
    /**
     * Test for membership.
     * @param e an element
     * @return true iff this set contains e
     */
	
	public boolean contains(E e);
  
 // producers: None
    

  
 // mutators:
	
    /**
     * Modifies this set by adding e to the set.
     * @param e element to add
     */	
	 public void add(E e);
	 
	/**
	 * Modifies this set by removing e, if found.
	 * If e is not found in the set, has no effect.
	 * @param e element to remove
	 * @throws IllegalStateException if this set is full
	 */
	 public void remove(E e);
   
    
}
