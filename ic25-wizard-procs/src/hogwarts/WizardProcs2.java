package hogwarts;

import static java.util.stream.Collectors.toList;
import hogwarts.model.*;
import hogwarts.model.Castle.SameNameException;

import java.util.List;

public class WizardProcs2 {
    
    // Implement using map/filter/reduce
    
    public static void main(String[] args) throws SameNameException {
        Castle castle = Hogwarts.makeHogwarts();
        List<Wizard> wizards = castle.wizards();
        
        System.out.println("Wizards:     " + wizards);
        
        // we need a Stream<Wizard> to use map/filter/reduce
        // to obtain a Stream from a List, use stream()
        // to obtain a List from a Stream, use collect(Collectors.toList())
        //   collect is just reduce optimized for mutable containers
        System.out.println("Wizards:     " + wizards.stream()
                                                    .map(w -> w)
                                                    .collect(toList()));
        
        System.out.println("Names:       " +  wizards.stream()
                                              .map(w -> w.getName())  //.map(Wizard::getName)  is a method reference you could use instead
                                              .collect(toList()));
        //System.out.println("Houses:      " + ...);
        //System.out.println("Friendly:    " + ...);
        //System.out.println("Hufflepuffs: " + ...);
        //System.out.println("Lex last:    " + ...);
    }
}
