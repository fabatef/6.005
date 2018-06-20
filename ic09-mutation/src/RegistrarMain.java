import java.util.List;
import java.util.Map;

public class RegistrarMain {
    
    final static String ALYSSA = "alyssa";
    final static String BEN = "benbit";
    
    public static void main(String[] args) {
        
        Registrar registrar = new Registrar();
        
        registrar.loadPreregistration();
        
        final Map<String, List<String>> allReg = registrar.getRegistration();
        
        System.out.println(ALYSSA + " and " + BEN + " have same registration? "
                + allReg.get(ALYSSA).equals(allReg.get(BEN)));
        
        for (final String username : new String[] { ALYSSA, BEN }) {
            System.out.println("update " + username + "'s registration...");
            final List<String> userReg = allReg.get(username);
            System.out.println(username + " was registered for " + userReg);
            //userReg.add("11.127");
            userReg.set(0, "11.127");
            System.out.println(username + " now registered for " + userReg);
        }
    }
    
    // for each subject in registration, remove all others with the
    // same course number before the decimal point
    public static void oneSubjectPerCourse(List<String> registration) {
        throw new RuntimeException("unimplemented");
    }
}
