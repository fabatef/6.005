import java.io.*;
import java.util.Arrays;

/**
 * An example of composing streams. Also demonstrates byte array input/output streams.
 */
public class Streams {
    
    public static void main(String[] args) {
        
        // part 1: input streams
        System.out.println("-- input streams --");
        
        byte[] inputBytes = new byte[] {
                72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33, 10, 86, 101,
                110, 105, 44, 32, 118, 105, 100, 105, 44, 32, 118, 105, 99, 105, 10, 77,
                114, 46, 32, 87, 97, 116, 115, 111, 110, 32, 45, 45, 32, 99, 111, 109,
                101, 32, 104, 101, 114, 101, 32, 45, 45, 32, 73, 32, 119, 97, 110, 116,
                32, 116, 111, 32, 115, 101, 101, 32, 121, 111, 117, 46, 10 };
        
        // byte stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(inputBytes);
        
        // byte stream --> character stream
        InputStreamReader inputReader = new InputStreamReader(inputStream);
        
        // character stream --> buffered stream
        BufferedReader bufferedReader= new BufferedReader(inputReader);
        
        // read from the stream one line at a time, print each line
        String input;
        try{
            while((input = bufferedReader.readLine()) != null){
            	System.out.println(input);
            }
        	
        } catch (IOException e){
        	//to do when encountered 
        }

        
        
        
        
        // part 2: output streams
        System.out.println("\n-- output streams --");
        
        // byte stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        // character stream --> byte stream
        OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
        
        // convenient printer --> character stream
        PrintWriter printWriter = new PrintWriter(outputWriter);
        
        // print each line to the stream, one line at a time
        for (String line : new String[] {
                "Hofstadter's Law:",
                "It always takes longer than you expect,",
                "even when you take into account Hofstadter's Law." }) {
            printWriter.println(line);   //printWriter has a buffer that holds on to its values
        }
        
        // either do a printWriter.flush() to release the values
        // or do printWriter.print(..)  this doesn't need to be flushed
        
        // print output to console
        byte[] outputBytes = outputStream.toByteArray();
        System.out.println("output (" + outputBytes.length + " bytes) = " + Arrays.toString(outputBytes));
    }
}
