/**
 * A mutable set of characters.
 */
public class CharSet implements Set<Object> {
    
    private String s = "";
    
    // Abstraction function:
    //   represents a set of characters
    // Representation invariant:
    //   there are no duplicate characters in s(representation of the abstract charSet)
    //   
    // Safety from rep exposure:
    //   TODO
    
    private void checkRep() {
        for (int i = 0; i < s.length(); ++i) {
            assert s.indexOf(s.charAt(i), i+1) == -1;
        }
    }
    
    /**
     * Make a new empty character set.
     */
    public CharSet() {
        checkRep();
    }
    
 
    @Override
    public int size() {
        checkRep();
        return s.length();
    }
    
    @Override
    public boolean contains(Object e) {
    	char c= (Character) e;
        checkRep();
        
        return s.indexOf(c) != -1;
    }
    

    @Override
    public void add(Object c) {
        if ( ! contains(c)) /*&& s.length() <= Integer.MAX_VALUE)*/ {
            s += c;
        }
        checkRep();
    }
    

    @Override
    public void remove(Object e) {
    	char c= (Character) e;
        int i = s.indexOf(c);
        if (i != -1) {
            s = s.substring(0, i) + s.substring(i+1, s.length());
        }
        checkRep();
    }
}
