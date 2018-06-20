package hogwarts.model;

import java.util.*;

public class Castle {
    
    private final Map<String, Wizard> wizardsByName = new HashMap<>();
    
    // Rep invariant:
    //    wizardsByName != null
    //    for all values w in wizardsByName,
    //        w.getCastle() == this
    //    for all key,value pairs (n,w) in wizardsByName,
    //        n.equals(w.getName())
    
    /** Make a Castle. */
    public Castle() {
    }
    
    /**
     * Add a wizard to the castle.
     * @param wizard wizard to add, requires wizard.getCastle() == this
     * @throws SameNameException if another wizard in the castle has the same name
     */
    public synchronized void add(Wizard wizard) throws SameNameException {
        Wizard w2 = wizardsByName.get(wizard.getName());
        if (w2 != null) {
            if (wizard.equals(w2)) return; // w is already in the castle
            throw new SameNameException(wizard.getName() + " already in this castle");
        }
        wizardsByName.put(wizard.getName(), wizard);
    }
    
    /**
     * Look up a wizard by name.
     * @param name name of wizard to look up
     * @return wizard by that name, or null if no such wizard
     */
    public synchronized Wizard lookup(String name) {
        return wizardsByName.get(name);
    }
    
    /**
     * Return a list of all wizards in the castle.
     * @return snapshot of this castle's wizards
     */
    public synchronized List<Wizard> wizards() {
        return new ArrayList<>(wizardsByName.values());
    }
    
    public static class SameNameException extends Exception {
        private static final long serialVersionUID = 1L;
        
        public SameNameException(String msg) {
            super(msg);
        }
    }
}
