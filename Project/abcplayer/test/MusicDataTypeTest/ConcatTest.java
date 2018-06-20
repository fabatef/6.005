package MusicDataTypeTest;

import static org.junit.Assert.*;
import org.junit.Test;
import abc.sound.*;


public class ConcatTest {
    //testing strategy
	//    numTicks
    //      - m1.numTicks() = m2.numTicks()
    //      - m1.numTicks() < m2.numTicks()
    //      - m1.numTicks() > m2.numTicks()
    //    transpose
	//      - transpose of two music objects  
    //    equals and hashCode
	//       - two equal music objects
	//       - two unequal music objects
	//    setNumTicks
	//       - lower than original numTicks
	//       - higher than original numTicks
	//       - equal to the original numTicks
	//    key and play
	//       -  
  
    Music noteE = new Note(new Pitch('E'), 2, 1);
    Music noteSmallC = new Note(new Pitch('C').transpose(12), 2, 1);
    Music restOfNumTicks6 = new Rest(6, 2);
    Music Rest2 = new Rest(1, 1);
    Music Rest1= new Rest(2,1);
    Music concatNoteERest = new Concat(noteE, restOfNumTicks6);
    Music concatNoteENoteSmallC = new Concat(noteE, noteSmallC);


   
  //Partition 1: m1.numTicks() = m2.numTicks()
    @Test
    public void twoEqualDurationMusicConcat() {
        Music concatCE = new Concat(noteE, noteSmallC);
        assertEquals( noteE.numTicks()+ noteSmallC.numTicks(), concatCE.numTicks());     
    }  
    
   //Partition 2: // m1.numTicks() > m2.numTicks()
    @Test
    public void firstMusicLongerThanSecondConcat(){
        Music noteEWithRestOfNumTicks6 = new Concat(restOfNumTicks6, Rest2);
        assertEquals(restOfNumTicks6.numTicks()+ Rest2.numTicks(), noteEWithRestOfNumTicks6.numTicks());
        assertFalse(Rest2.numTicks() == noteEWithRestOfNumTicks6.numTicks());
        
    }
    
    //Partition 3: m1.numTicks() < m2.numTicks()
    @Test
    public void secondMusicLongerThanfirstConcat() {
       Music twoConcats = new Concat(concatNoteERest, concatNoteENoteSmallC);
       assertEquals(concatNoteERest.numTicks()+ concatNoteENoteSmallC.numTicks(), twoConcats.numTicks() );
    }
    
    //Testing transpose
    @Test
    public void transposeConcat() {
        Music transposedConcat = new Concat(noteE, concatNoteENoteSmallC).transpose(1);
        Music concatOfTransposedMusic = new Concat(noteE.transpose(1), concatNoteENoteSmallC.transpose(1));
        assertEquals(transposedConcat, concatOfTransposedMusic);
    }
    
    
    //Test covers hashCode method and Equals method
    @Test
    public void hasCodeAndEqualsOfConcat() {
        Music firstConcat = new Concat(noteE, concatNoteENoteSmallC).transpose(1);
        Music concatOfTransposedMusic = new Concat(noteE.transpose(1), concatNoteENoteSmallC.transpose(1));
        assertEquals(firstConcat, concatOfTransposedMusic);
        assertEquals(firstConcat.hashCode(), concatOfTransposedMusic.hashCode());
    }
    
   //Testing Tuplets
    
   //Test covers two Notes Concatenated to make a duplet 
   // each component is played 3/2 of its original duration
    @Test
    public void TwoNotesConcatedDuplets() {  // (2Ec
    	int numTicksPerBeat= 2;
    	int numTicks= 3;
    	Music E = new Note(new Pitch('E'), numTicks,numTicksPerBeat);
    	Music smallC= new Note(new Pitch('C').transpose(12), numTicks, numTicksPerBeat);
        Music dupletsCE = new Concat(E, smallC);
      
        assertEquals(E.numTicks()+ smallC.numTicks() , dupletsCE.numTicks()); 
    }  
    
    //Test covers three Notes Concatenated to make a triplet
    // each component is played for 2/3rd of its original duration
    @Test
    public void ThreeNotesConcatedTriplets() {  // (3cEc
    	int numTicksPerBeat= 3;
    	int numTicks= 2;
    	Music E = new Note(new Pitch('E'), numTicks,numTicksPerBeat);
    	Music smallC= new Note(new Pitch('C').transpose(12), numTicks, numTicksPerBeat);
        Music triplet = new Concat(smallC, new Concat(E, smallC));
        assertEquals(E.numTicks() + 2*smallC.numTicks(), triplet.numTicks()); 
    } 
    
    //Test covers three Notes Concatenated to make a quadruplets
    // each component is played for 3/4th of its orignal duration
    @Test
    public void ThreeNotesConcatedQuadruplets() {  // (4EcEc
    	int numTicksPerBeat= 4;
    	int numTicks= 3;
    	Music E = new Note(new Pitch('E'), numTicks,numTicksPerBeat);
    	Music smallC= new Note(new Pitch('C').transpose(12), numTicks, numTicksPerBeat);
        Music quad = new Concat(new Concat(E, smallC), new Concat(E, smallC));
        assertEquals(2*E.numTicks() + 2*smallC.numTicks(), quad.numTicks()); 
    } 
    
    //Testing setNumTicks
    
    //Partition 1: set higher than original numTicks
    @Test
    public void setHigherNumTicks(){
    	Music concatCE = new Concat(noteE, noteSmallC);
    	int numTicksCE= concatCE.numTicks();
    	Music newConcatCE= concatCE.setNumTicks(numTicksCE+2);
    	assertEquals((numTicksCE+2)*2, newConcatCE.numTicks());
    }
    
    //Partition 2: set lower than original numTicks
    @Test
    public void setLowerNumTicks(){
    	Music concatCE = new Concat(noteE, noteSmallC);
    	int numTicksCE= concatCE.numTicks();
    	Music newConcatCE= concatCE.setNumTicks(numTicksCE-2);
    	assertEquals((numTicksCE-2)*2, newConcatCE.numTicks());
    }
    

    

}
