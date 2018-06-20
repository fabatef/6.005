package src;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.junit.Assert*;

public class WordTest{
	
	@Test
	public void testFrequency(){
    	File testFile= new File("src/blah.txt");
    	List<String> test= mostCommonWords(testFile);
		System.out.println(test); 
	}
	
}