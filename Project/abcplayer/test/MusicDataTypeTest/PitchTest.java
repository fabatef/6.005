package MusicDataTypeTest;


import static org.junit.Assert.*;

import org.junit.Test;

import abc.sound.Pitch;


public class PitchTest {
	
	//testing strategy
	//    transpose   (a semitone is 1/12th of an octave)
	//                (a semitone is the smallest distance between two notes)  
	//
	//       - transpose a pitch whose 1 semitone up is neither a flat nor a sharp but another pitch
	//       - transpose a pitch whose 1 semitone down is neither a flat nor a sharp but another pitch
	//       - transpose a pitch whose 1 semitone up is a sharp
	//       - transpose a pitch whose 1 semitone down is a flat
	//       - transpose a pitch two semitones higher to make a double sharp
	//       - transpose a pitch two semitones lower to make a double flat
	//       - transpose a pitch twelve semitones up to drive it up into a higher octave.
	//       - transpose a pitch twelve semitones down to drive it down into a lower octave.
	//   difference
	//       - difference between pitches that don't have a flat or a sharp between them (E-F, and B-C)
	//       - difference between pitches that do have a flat or a sharp between them 
	//  lessThan
	//       - lessThan of a pitch that's higher than Other.
	//       - lessThan of a pitch that's lower than Other
	//       - two equal pitches. 
	
	//Testing Transpose
	
	@Test
	//Partition 1: transpose a pitch whose 1 semitone up is neither a flat nor a sharp but another pitch
	             //  (i.e) transpose E a semitone up to get an F
	public void transposeESemitoneUp(){
		Pitch E= new Pitch('E');
		Pitch F= E.transpose(1);
		assertTrue(F.toString().equals("F"));	
	}
	
	@Test
	//Partition 2: transpose a pitch whose 1 semitone down is neither a flat nor a sharp but another pitch
	             //  (i.e) transpose C a semitone down to get a B, (in an octave below the middle C)
	public void transposeCSemitoneDown(){
		Pitch C= new Pitch('C');
		Pitch lowerOctaveB= C.transpose(-1);
		assertTrue(lowerOctaveB.toString().equals("B,"));	
	}
	
	@Test
	//Partition 3: transpose a pitch whose 1 semitone up is a sharp
	             //  (i.e) transpose C a semitone up to get a C sharp
	public void transposeCSemitoneUP(){
		Pitch C= new Pitch('C');
		Pitch sharpC= C.transpose(1);
		assertTrue(sharpC.toString().equals("^C"));	
	}
	
	
	@Test
	//Partition 4: transpose a pitch whose 1 semitone down is a flat
	             //  (i.e) transpose D a semitone down to get a D flat /a C sharp
	public void transposeDsemitoneDown(){
		Pitch D= new Pitch('D');
		Pitch flatD= D.transpose(-1);
		assertTrue(flatD.toString().equals("^C"));	
	}
	
	@Test
	//Partition 5: transpose a pitch two semitones higher to make a double sharp
	             //  (i.e) transpose C two semitones up to get a double C sharp (one semitone up is a C sharp
	             //   and one semitone up from a C sharp is a D.                                                          
	public void transposeDoubleSharp(){
		Pitch C= new Pitch('C');
		Pitch doubleSharpC= C.transpose(2);
		assertTrue(doubleSharpC.toString().equals("D"));	 
	}
	
	@Test
	//Partition 6: transpose a pitch two semitones lower to make a double flat
	             //  (i.e) transpose F sharp  two semitones down to get an E (one semitone down is an F
	             //   and one semitone down from an F is an E.                                                          
	public void transposeDoubleFlat(){
		Pitch sharpF= new Pitch('F').transpose(1);
		Pitch doubleFlatF= sharpF.transpose(-2);
		assertTrue(doubleFlatF.toString().equals("E"));	 
	}
	
	@Test
	//Partition 7: transpose a pitch twelve semitones up to drive it up into a higher octave.
	            // (i.e) transpose middle C to a c
	public void transposeOctaveHigh(){
		Pitch middleC= new Pitch('C');
		Pitch highC= middleC.transpose(12);
		assertTrue(highC.toString().equals("C'"));	 
	}
	
	@Test
	//Partition 8: transpose a pitch twelve semitones down to drive it down into a higher octave.
	            // (i.e) transpose middle C to a C,
	public void transposeOctaveLower(){
		Pitch middleC= new Pitch('C');
		Pitch lowC= middleC.transpose(-12);
		assertTrue(lowC.toString().equals("C,"));	 
	}
	
	
	//testing difference
	
	@Test
	//Partition 1: difference between pitches that don't have a flat or a sharp between them (E-F, and B-C)
	            // (i.e) transpose middle C to a C,
	public void differenceNoSharpOrFlat(){
		Pitch E= new Pitch('E');
		Pitch F= new Pitch('F');
		assertEquals(1, F.difference(E));	 
	}
	
	@Test
	//Partition 2: difference between pitches that do have a flat or a sharp between them 
	public void differenceSharp(){
		Pitch middleC= new Pitch('C');
		Pitch D= new Pitch('D');
		assertEquals(2, D.difference(middleC));		 
	}
	
	//testing lessThan
	
	@Test
	//Partition 1: lessThan of a pitch that's higher than Other.
	public void lessThanHigherPitch(){
		Pitch middleC= new Pitch('C');
		Pitch D= new Pitch('D');
		assertFalse(D.lessThan(middleC));		 
	}
	
	@Test
	//Partition 2: lessThan of a pitch that's lower than Other.
	public void lessThanLowerPitch(){
		Pitch E= new Pitch('E');
		Pitch F= new Pitch('F');
		assertTrue(E.lessThan(F));		  
	}
	
	@Test
	//Partition 3: lessThan of two Equal pitches
	public void lessThanEqualPitchs(){
		//System.out.println(new Pitch('C').transpose(-1));
		Pitch sharpC= new Pitch('C').transpose(1);
		
		Pitch flatD= new Pitch('D').transpose(-1);    
		assertFalse(flatD.lessThan(sharpC));
		assertTrue(flatD.equals(sharpC));
	} 	
	
}
