import java.util.*;

/**
 * Mutable registrar that manages class registration.
 */
public class Registrar {
    
    private final Map<String, List<String>> registration = new HashMap<>();
    
    /**
     * Update subject registration according to preregistration.
     */
    public void loadPreregistration() {
        // ...
        // ...
        // ...
        registration.putIfAbsent("alyssa", new ArrayList<>(Arrays.asList("6.005", "7.33", "21W.759")));
        // ...
        // ...
        // ...
        registration.putIfAbsent("benbit", Arrays.asList("6.005", "7.33", "21W.759"));
        
        // arrays.aslist uses an array in the background so you can't use add. It throws
        // an unsupported operation exception.
        
        // ...
        // ...
        // ...
    }
    
    /** 
     * @return current subject registration for all students
     *  defensive copying won't work since the keys in the new copy still refer
     *  to the same array list
     *  
     *  fix: return an unmodifiable view of the list of subjects username registered for
     */
    public List<String> getRegistration( String username) {
        return Collections.unmodifiableList(registration.get(username));
    }
    
    /**
     * Register a student in a subject, if their credit limit allows, and space
     * is available, and [TODO etc. etc.].
     * @param username student to register
     * @param subject class to register for
     * @return true if and only if the student is now registered for the subject
     */
    
    // This can't currently be implemented because the mutable list of classes
    // people registered for can be accessed and changed (since registration is mutable)
    
    public boolean registerForClass(String username, String subject) {
        return false; // unimplemented
    }
    
}
