import java.io.*;
import java.net.*;

public class WebStreams {
    
    public static void main(String[] args) throws IOException {
        
        String ps0 = "http://web.mit.edu/6.005/www/fa15/psets/ps0/";
        
        // create a URL object from ps0
        URL myURL = new URL(ps0);
        
        // open an input stream from the URL
        InputStream myInputStream= myURL.openStream();
        
        // wrap the input stream with a BufferedReader
        BufferedReader in = new BufferedReader(new InputStreamReader(myInputStream));

        
        // read from the reader one line at a time, printing each line
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
        }
        in.close();
    }
}

//Thread saftey arguments
//