package hogwarts;

import hogwarts.model.Castle;
import hogwarts.model.Castle.SameNameException;
import hogwarts.model.House;
import hogwarts.model.Wizard;

import java.util.ArrayList;
import java.util.List;

public class WizardProcs1 {
    
    // Implement the wizard processing functions using familiar Java loops
    
    /**  @return names of wizards in input */
    static List<String> names(List<Wizard> wizards) {
        List<String> names= new ArrayList<String>();
        for (Wizard wizard: wizards){
        	names.add(wizard.getName());
        }
        return names;
    }
    
    /** @return houses of wizards in input */
    static List<House> houses(List<Wizard> wizards) {
        List<House> houses= new ArrayList<House>();
        for (Wizard wizard: wizards){
        	houses.add(wizard.getHouse());
        }
        return houses;
    }
    
    /** @return only the wizards in input with more than 2 friends */
    static List<Wizard> friendly(List<Wizard> wizards) {
        return null; // TODO
    }
    
    /** @return number of wizards in input who are in house Hufflepuff */
    static int countHufflepuffs(List<Wizard> wizards) {
        return 0; // TODO
    }
    
    /** @return wizard in input whose name is last alphabetically */
    static Wizard lastByName(List<Wizard> wizards) {
        return null; // TODO
    }
    
    public static void main(String[] args) throws SameNameException {
        Castle castle = Hogwarts.makeHogwarts();
        List<Wizard> wizards = castle.wizards();
        
        System.out.println("Wizards:     " + wizards);
        System.out.println("Names:       " + names(wizards));
        System.out.println("Houses:      " + houses(wizards));
        System.out.println("Friendly:    " + friendly(wizards));
        System.out.println("Hufflepuffs: " + countHufflepuffs(wizards));
        System.out.println("Lex last:    " + lastByName(wizards));
    }
}
