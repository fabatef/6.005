import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitURL {
    private final static String[] EXAMPLE_URLS = new String[] {
        "ssh://athena.dialup.mit.edu/mit/6.005/git/fa15/psets/ps1/bitdiddle.git",
        "ssh://athena.dialup.mit.edu/mit/6.005/git/sp16/psets/ps3/bmw2016.git",
        "ssh://athena.dialup.mit.edu/mit/6.005/git/fa15/inclass/ic07-find/rcm.git",
        "ssh://athena.dialup.mit.edu/mit/6.005/git/sp13/inclass/ic13-af-ri/rcm.git",
    };
        
    public static void main(String[] args) {
        // Unmaintainable regex
        final String originalRegex = 
                "ssh://(\\w+@)?athena.dialup.mit.edu/mit/6.005/git/(?<term>(fa|sp)\\d\\d)/(inclass/(?<inclass>\\w+(-\\w+)+)|psets/(?<pset>ps\\d))/(?<student>\\w+)\\.git";        

        // Regex written like a regular grammar with nonterminals.
        // Fill in each TODO with a regular expression, imitating a grammar rule.
        // Then assemble them into yourRegex at the end.
        final String hostname = "athena.dialup.mit.edu";
        final String path = "/mit/6.005/git/";
        final String term = "(?<term>(fa|sp)\\d\\d)/";
        final String inclass = "inclass/((?<inclass>\\w+(-\\w+)+)";
        final String pset = "(psets/?<pset>ps\\d))/";
        final String username = "(?<student>\\w+)\\.git";
        final String urlRegex = "ssh://" + hostname
                                 + path + term+ inclass + pset + username;
        
      //url ::= 'ssh://' hostname path '/' term(inclass |pset) '/' username '.git'
        
        Pattern pattern = Pattern.compile(urlRegex);
        for (String url : EXAMPLE_URLS) {
            System.out.println(url);
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                System.out.print("\t term=" + matcher.group("term")); // matcher.group(1)); pull out the first parenthesized bit from the regex
                System.out.print("\t inclass=" + matcher.group("inclass"));
                System.out.print("\t pset=" + matcher.group("pset"));
                System.out.print("\t student=" + matcher.group("student"));
                System.out.print("\n\n");
            }
        }
    }
}
