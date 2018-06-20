package abc.sound;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import abc.parser.*;

/**
 * Music represents a piece of music played by multiple instruments.
 * 
 */
public interface Music {
    
    /*
     * Datatype definition 
     * Music = Note(pitch:Pitch, numTicks: int, numTicksPerBeat: int, (optional) accidentalApplied: boolean)
     *       + Rest(numTicks: int, numTicksPerBeat: int)
     *       + Concat(m1:Music, m2:Music)
     *       + Together(m1:Music, m2:Music)
     *                  
     */
  
	
	/**
	 * Retrieves header information from the input abc file
	 * @param file: abc file to parse
	 * @return a Map of the information and their respective values
	 * @throws IOException
	 */
    public static Map<String, String> parseHeader(File file) throws IOException {
    	try {
    		String line;
            String parseWithXyz="";
            Boolean check= false;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null){
                if (!check){
                	check= line.startsWith("K");
                	parseWithXyz += line + "\n";         
                }else{
                	continue;	
                }     
            }
            reader.close();        
            CharStream streamXyz = new ANTLRInputStream(parseWithXyz);           
            XyzLexer lexerXyz= new XyzLexer(streamXyz);
            lexerXyz.reportErrorsAsExceptions();           
            TokenStream tokensXyz = new CommonTokenStream(lexerXyz);
            XyzParser parserXyz = new XyzParser(tokensXyz);        
            parserXyz.reportErrorsAsExceptions();
            ParseTree treeXyz = parserXyz.root();
            ParseTreeWalker walkerXyz = new ParseTreeWalker();             
            //Trees.inspect(treeXyz, parserXyz);         
            MakeHeader listener = new MakeHeader();
            walkerXyz.walk(listener, treeXyz);           
            return listener.getHeader();          
        } catch (RuntimeException run) {
            throw new IllegalArgumentException("file format is invalid");
        }
    }
    
    /**
     * makes a Music object from an Abc file. Constructed as specified by the header
     * @param file: abc file to read from
     * @return  a Music object
     * @throws IOException
     */
    public static Music parseBody(File file) throws IOException {
   		String line;
           String parseWithAbc = "";
           Boolean check= false;
           BufferedReader reader = new BufferedReader(new FileReader(file));
           while ((line = reader.readLine()) != null){
               if (!check){
               	check= line.startsWith("K");
               	continue;         
               }else{             	
               	parseWithAbc+=line + "\n" ;	              	
               }     
           }
           reader.close();
           CharStream streamAbc = new ANTLRInputStream(parseWithAbc);                     
           AbcLexer lexerAbc = new AbcLexer(streamAbc);
           lexerAbc.reportErrorsAsExceptions();       
           TokenStream tokensAbc = new CommonTokenStream(lexerAbc);
           AbcParser parserAbc = new AbcParser(tokensAbc);       
           parserAbc.reportErrorsAsExceptions();          
           ParseTree treeAbc = parserAbc.root();           
           //Trees.inspect(treeAbc, parserAbc);         
           ParseTreeWalker walkerAbc = new ParseTreeWalker();           
           MakeBody listener = new MakeBody();
           walkerAbc.walk(listener, treeAbc);            
           return listener.getBody();  
   }
    
    /**
     * @return the total number of ticks of this piece
     */
    int numTicks();
    
    /**
     * 
     * @param newNumTicks total number of ticks of this piece
     * @return Music object produced from another music object
     */
    Music setNumTicks(int newNumTicks);
    
    /**
     * Transpose all notes upward or downward in pitch.
     * @param semitonesUp semitones by which to transpose
     * @return for Music m, return m' such that for all notes n in m, the
     *         corresponding note n' in m' has
     *         n'.pitch() == n.pitch().transpose(semitonesUp), and m' is
     *         otherwise identical to m
     */
    Music transpose(int semitonesUp);
    
    /**
     * Play this piece.
     * @param player player to play on
     * @param startTick when to play
     */
    void play(SequencePlayer player, int startTick);
    
    /**
     * makes a Music object that plays with the specified key signature
     * @param keySig : the key signature
     * @return  a new Music object
     */
    Music key(String keySig);
    
}
