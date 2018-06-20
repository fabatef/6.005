
/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

package minesweeper.server;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

import org.junit.Test;

import minesweeper.server.MinesweeperServer;


//Testing strategy
//  -test commands on a single thread
//  -test commands on two threads
/**
 * Tests basic LOOK and DIG commands and X,Y directions.
 */
public class MinesweeperServerTest {
    
    private static final String LOCALHOST = "127.0.0.1";
    private static final int PORT = 4000 + new Random().nextInt(1 << 15);
    
    private static final int MAX_CONNECTION_ATTEMPTS = 10;
    
    private static final String BOARDS_PKG = "minesweeper/";
    
    /**
     * Start a MinesweeperServer in debug mode with a board file from BOARDS_PKG.
     * @param boardFile board to load
     * @return thread running the server
     * @throws IOException if the board file cannot be found
     */
    private static Thread startMinesweeperServer(String boardFile) throws IOException {
        final URL boardURL = ClassLoader.getSystemClassLoader().getResource(BOARDS_PKG + boardFile);
        if (boardURL == null) {
            throw new IOException("Failed to locate resource " + boardFile);
        }
        final String boardPath;
        try {
            boardPath = new File(boardURL.toURI()).getAbsolutePath();
        } catch (URISyntaxException urise) {
            throw new IOException("Invalid URL " + boardURL, urise);
        }
        final String[] args = new String[] {
                "--debug",
                "--port", Integer.toString(PORT),
                "--file", boardPath
        };
        Thread serverThread = new Thread(() -> MinesweeperServer.main(args));
        serverThread.start();
        return serverThread;
    }
    
    
    private static Thread startMinesweeperServerCustom(int x, int y) {
    	
        final String[] args = new String[] {
                "--debug",
                "--port", Integer.toString(PORT),
                "--size", Integer.toString(x)+ "," + Integer.toString(y)
        };
        Thread serverThread = new Thread(() -> MinesweeperServer.main(args));
        serverThread.start();
        return serverThread;
    }
    
    /**
     * Connect to a MinesweeperServer and return the connected socket.
     * @param server abort connection attempts if the server thread dies
     * @return socket connected to the server
     * @throws IOException if the connection fails
     */
    private static Socket connectToMinesweeperServer(Thread server) throws IOException {
        int attempts = 0;
        while (true) {
            try {
                Socket socket = new Socket(LOCALHOST, PORT);
                socket.setSoTimeout(3000);
                return socket;
            } catch (ConnectException ce) {
                if ( ! server.isAlive()) {
                    throw new IOException("Server thread not running");
                }
                if (++attempts > MAX_CONNECTION_ATTEMPTS) {
                    throw new IOException("Exceeded max connection attempts", ce);
                }
                try { Thread.sleep(attempts * 10); } catch (InterruptedException ie) { }
            }
        }
    }
    
    
    
    @Test(timeout = 10000)
    public void publishedTestSingleThreadFromFile() throws IOException {

        Thread thread = startMinesweeperServer("board_file_5.txt");

        Socket socket = connectToMinesweeperServer(thread);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        assertTrue("expected HELLO message", in.readLine().startsWith("Welcome"));

        out.println("look");
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());

        out.println("dig 3 1");
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - 1 - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());

        out.println("dig 4 1");
        assertEquals("BOOM!", in.readLine());

        out.println("look"); // debug mode is on
        assertEquals("             ", in.readLine());
        assertEquals("             ", in.readLine());
        assertEquals("             ", in.readLine());
        assertEquals("             ", in.readLine());
        assertEquals("             ", in.readLine());
        assertEquals("1 1          ", in.readLine());
        assertEquals("- 1          ", in.readLine());

        out.println("bye");
        socket.close();
    }
    
/*    @Test(timeout = 10000)
    public void TwoThreads() throws IOException {

        Thread thread = startMinesweeperServer("board_file_5.txt");
        Thread thread2= startMinesweeperServer("board_file_5.txt");

        Socket socket = connectToMinesweeperServer(thread);
        Socket socket2 = connectToMinesweeperServer(thread2);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        PrintWriter out2 = new PrintWriter(socket2.getOutputStream(), true);
        

        assertTrue("expected HELLO message", in.readLine().startsWith("Welcome"));
        assertTrue("expected HELLO message", in2.readLine().startsWith("Welcome"));

        out.println("look");
        out2.println("look");
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        assertEquals("- - - - - - -", in.readLine());
        
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());

        out2.println("dig 3 1");
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - 1 - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());
        assertEquals("- - - - - - -", in2.readLine());

        out.println("dig 4 1");
        assertEquals("BOOM!", in.readLine()); //dig the board on the first thread

        out2.println("look"); //look on the second thread
        assertEquals("             ", in2.readLine());
        assertEquals("             ", in2.readLine());
        assertEquals("             ", in2.readLine());
        assertEquals("             ", in2.readLine());
        assertEquals("             ", in2.readLine());
        assertEquals("1 1          ", in2.readLine());
        assertEquals("- 1          ", in2.readLine());

        out2.println("bye");
        socket2.close();
        socket.close();   
    }
    
    @Test(timeout = 10000)
    public void SingleThreadSpecifiedSize() throws IOException {

        Thread thread = startMinesweeperServerCustom(3,3);

        Socket socket = connectToMinesweeperServer(thread);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        assertTrue("expected HELLO message", in.readLine().startsWith("Welcome"));

        out.println("look");
        assertEquals("- - -", in.readLine());
        assertEquals("- - -", in.readLine());
        assertEquals("- - -", in.readLine());

        out.println("bye");
        socket.close();
    }*/
       
}






