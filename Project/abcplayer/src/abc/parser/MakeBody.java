package abc.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import abc.parser.AbcListener;
import abc.parser.AbcParser.AbcLineContext;
import abc.parser.AbcParser.AbcMusicContext;
import abc.parser.AbcParser.AccidentalContext;
import abc.parser.AbcParser.BasenoteContext;
import abc.parser.AbcParser.ElementContext;
import abc.parser.AbcParser.MidTuneFieldContext;
import abc.parser.AbcParser.NoteElementContext;
import abc.parser.AbcParser.NoteLengthContext;
import abc.parser.AbcParser.NoteLengthStrictContext;
import abc.parser.AbcParser.NoteOrRestContext;
import abc.parser.AbcParser.OctaveContext;
import abc.parser.AbcParser.PitchContext;
import abc.parser.AbcParser.RestContext;
import abc.parser.AbcParser.*;
import abc.sound.*;


/** 
 * Make a Music object from the parse tree generated by Abc.g4
 */

public class MakeBody implements AbcListener{   
	
	private Map<String, Music> musicMap = new HashMap<String, Music>();   
	private Stack<Music> track= new Stack<Music>();
	public static int numTicksPerBeat = 192;
	private String currentVoice = "";
	
	//vars to keep track of repeats
	private Map<String, Music> repeatMap = new HashMap<String, Music>();
	private Stack<Music> repeat = new Stack<Music>();
	private boolean doRepeat = false;
	private boolean enteredFirstRepeat= false;

	//vars to keep track of accidentals
	private boolean encounteredAccidental= false;
	boolean flat= false;
	boolean sharp= false;
	boolean natural= false;
	int numTransposeAcc= 0;
	private String accidentalNote= "";

	/**
	 * transfers the music objects that were on the main music stack to the music map along with 
	 * the currentVoice after concatenating them into a single Music object, Concat 
	 */
	private void transferMusicStackToMusicMap(){
		if (!track.isEmpty()) { 
			Music base= track.pop();
			int iter= track.size();
			for(int i=0; i< iter ; i++){
				Music element= track.pop();
				base= new Concat(element,base);
			}
			musicMap.put(currentVoice, base); 
		} 
	}


	/**
	 * transfers the music objects that were on the repeat stack to the repeat map along with 
	 * the currentVoice after concatenating them into a single Music object, Concat 
	 */
	private void transferRepeatStackToRepeatMap(){
		if (!repeat.isEmpty()) {
			Music repeatbase = repeat.pop();
			int iter= repeat.size();
			for(int i=0; i< iter; i++){
				Music element= repeat.pop();
				repeatbase= new Concat(element,repeatbase);
			} 
			repeatMap.put(currentVoice, repeatbase);
		}
	}
	
	
	/**
	 * @return the final music piece as a single Music object, Together, without the key signature applied
	 */
	public Music getBody(){
		Music music = new Rest(0,numTicksPerBeat); // ends up not being used. Just to avoid using null
		for (String voice : musicMap.keySet()){
			if (music.numTicks() == 0) { // the first instance
				music = musicMap.get(voice);
			} else {
				music = new Together(music, musicMap.get(voice));                     
			}
		}

		return music;
	}

	@Override public void exitRoot(RootContext ctx) { 
		/*
		 * Since the elements in the main music stack (track) are added to the musicMap when we change voices,
		 * exitRoot handles adding the last voice
		 * 
		 */
		transferMusicStackToMusicMap();
		transferRepeatStackToRepeatMap();
	}

	@Override
	public void exitNote(NoteContext ctx) {
		// matched noteOrRest (noteLength | noteLengthStrict)?
		NoteOrRestContext noteOrRest= ctx.noteOrRest();
		Music basenote; 
		int numTicks= numTicksPerBeat;

		//get duration of note if matched DIGIT+
		if (ctx.noteLength()!=null){
			numTicks*=Integer.valueOf(ctx.noteLength().getText());
		}

		//get duration of note if matched (DIGIT+)? '/' (DIGIT+) 
		if(ctx.noteLengthStrict()!= null){
			if (!(ctx.noteLengthStrict().getText().equals("/"))){
				String[] duration= ctx.noteLengthStrict().getText().split("/");  
				if(duration[0].isEmpty()){ // matched /digit : treated as  1/DIGIT
					numTicks*= (1.0/Integer.valueOf(duration[1]));
				} else if (duration.length == 1){ //matched  digit/  : treated as DIGIT/2
					numTicks*= Integer.valueOf(duration[0])/2;
				}
				else{
					numTicks= numTicks*Integer.valueOf(duration[0])/Integer.valueOf(duration[1]);
				}
			} else{  // matched a / : treated as 1/2
				numTicks*=1/2; 
			}   		   		
		}

		if(noteOrRest.pitch() != null){
			PitchContext pitch= noteOrRest.pitch();
			String note= pitch.basenote().getText(); // [A-G] or [a-g]

			if(pitch.accidental()!=null){   //accidental encountered. Change the accidental states accordingly
				numTransposeAcc= pitch.accidental().getText().length();
				encounteredAccidental= true;
				sharp= pitch.accidental().getText().startsWith("^");
				flat= pitch.accidental().getText().startsWith("_");
				natural= pitch.accidental().getText().startsWith("=");
				accidentalNote= note;   
			}

			if( note.matches("[A-G]")){  
				basenote= new Note(new Pitch(note.charAt(0)),numTicks,numTicksPerBeat, encounteredAccidental);    		
			}else{//matched [a-g] i.e. an octave higher than their upper case counterparts
				basenote= new Note(new Pitch(note.toUpperCase().charAt(0)), numTicks, numTicksPerBeat, encounteredAccidental).transpose(12);
			}

			if(pitch.octave()!=null){ // matched a '/''+ or a ','+
				int numTranspose= 12*pitch.octave().getText().length(); //number of times to transpose
				if(pitch.octave().getText().startsWith("'")){
					basenote= basenote.transpose(numTranspose);
				}else if (pitch.octave().getText().startsWith(",")){
					basenote= basenote.transpose(-1*numTranspose);
				}
			}

			if(encounteredAccidental && accidentalNote.equals(pitch.basenote().getText()) ){ 
				// check if we've encountered an accidental and if the current basenote matches
				// the basenote of the accidental encountered
				if(sharp){    				
					basenote= basenote.transpose(numTransposeAcc);   			
				}else if(flat){
					basenote= basenote.transpose(-1*numTransposeAcc);
				}
			}

		}

		// else we have a rest
		else{
			basenote= new Rest(numTicks, numTicksPerBeat);
		}

		track.push(basenote); 

		if (doRepeat){ 
			repeat.push(basenote);
		} 
	}

	@Override
	public void exitTupletElement(TupletElementContext ctx) { // duplet| triplet| quadruplet;

		int numberOfElements;
		double multiplier;
		if (ctx.duplet() !=null){
			multiplier = 1.5;
			numberOfElements= 2;
		} else if (ctx.triplet() !=null){
			multiplier = 2.0/3.0;
			numberOfElements= 3;
		} else {
			multiplier = 0.75;
			numberOfElements= 4;
		}

		List<Music> tupletElems = new ArrayList<Music>() ;

		for (int i = 0; i < numberOfElements; i++){ //pop the tuplet elements off the main stack and adjust
			Music element = track.pop();           // their timing according to the spec given.
			Music updated = element.setNumTicks((int)(element.numTicks()*multiplier));
			tupletElems.add(updated);
		}
		Collections.reverse(tupletElems); //make sure Music objects get pushed into the stack in the right order

		if (doRepeat){
			for (Music elem: tupletElems){
				repeat.push(elem);
			}
		}
		for (Music elem: tupletElems){
			track.push(elem); //put the adjusted tuplets back into the main stack
		}
	}
	@Override
	public void exitMultiNote(MultiNoteContext ctx) {
		// matched '[' note+ ']'
		List<NoteContext> notes= ctx.note();
		Music chord= track.pop();

		for (int i = 0; i < notes.size()-1; i++){ //pop the notes that are part of a chord from the stack
			Music element = track.pop();
			chord = new Together(element, chord); //perform a together on them
		}
		if (doRepeat){
			for (int i = 0; i < notes.size(); i++){
				repeat.pop();
			}
			repeat.push(chord);
		}

		track.push(chord); //put the chord created in the main music stack

	}
	@Override
	public void exitBarline(BarlineContext ctx) {
		// '|' | '||' | '[|' | '|]' | ':|' | '|:'

		if (ctx.getText().equals("|") | ctx.getText().equals("||")){
			encounteredAccidental= false;  //make sure accidentals only get propagated in a single measure
		}

		if (ctx.getText().equals("[|")){
			//do nothing
		}

		if (ctx.getText().equals("|]")){
			doRepeat = true;   //reset repeat
		}

		if (ctx.getText().equals("|:")){ //entered a new repeat block 
			repeat = new Stack<Music>();  //re-initialize the repeat stack

		}
		if (ctx.getText().equals(":|")){ //exiting a repeat block
			List<Music> repeatElems= new ArrayList<Music>();
			@SuppressWarnings("unchecked")
			Stack<Music> trackCopy= (Stack<Music>) track.clone();
			int iter= 0;
			if (!doRepeat && !(enteredFirstRepeat)){  //handles the case of a repeat where there's no repeat 
				iter= track.size();                   //entry signifier.  i.e. ...m m m m :|
				for(int i=0; i<iter; i++){
					repeatElems.add(trackCopy.pop());
				}
				Collections.reverse(repeatElems);  //to push into the stack in the right order

				for (Music elem: repeatElems){
					track.push(elem); //push whatever was on the stack back onto itself
				}

			} else {   // handles everything else
				iter = repeat.size();
				for(int i=0; i<iter; i++){
					repeatElems.add(repeat.pop());
				}	

				Collections.reverse(repeatElems); //push in the right ordrer

				for (Music elem: repeatElems){
					track.push(elem);
				}
			}
		}      
	}

	@Override public void enterBarline(BarlineContext ctx) {
		if (ctx.getText().equals("|:")){
			doRepeat= true;  //entered 
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public void exitNthRepeat(NthRepeatContext ctx) {
		// '[1' | '[2';
		if (ctx.getText().equals("[1")){
			enteredFirstRepeat = true;
			if (!doRepeat){
				repeat = (Stack<Music>) track.clone();
			} 
			doRepeat = false; //don't repeat whatever follows [1
		}
		if (ctx.getText().equals("[2")){
			//do nothing;
		}

	}

	@Override
	public void exitFieldVoice(FieldVoiceContext ctx) {  
		TextContext voice= ctx.text();
		if (!(currentVoice.equals(voice.getText()))){  //empty the stack for the next voice in line
			transferMusicStackToMusicMap();            //and save its contents in the Maps
			transferRepeatStackToRepeatMap();
		}
		assert(track.size() == 0);
		currentVoice= voice.getText();   //reset the current voice
		if (musicMap.keySet().contains(currentVoice)){
			track.push(musicMap.get(currentVoice));  //get the previously stored value of the new current
		}                                            // voice and put it in the music stack   
		if (doRepeat){
			if (repeatMap.keySet().contains(currentVoice)){ 
				repeat.push(repeatMap.get(currentVoice));  // do same for repeat stack
			}
		}
	}

	@Override public void enterFieldVoice(FieldVoiceContext ctx) {}
	@Override public void exitText(TextContext ctx) {}
	@Override public void exitComment(CommentContext ctx){}    
	@Override public void exitEndOfLine(EndOfLineContext ctx) {}
	@Override public void enterRoot(RootContext ctx) {}
	@Override public void enterAbcMusic(AbcMusicContext ctx) {}
	@Override public void enterAbcLine(AbcLineContext ctx) {}
	@Override public void enterElement(ElementContext ctx) {}
	@Override public void enterNote(NoteContext ctx) {}
	@Override public void enterNoteElement(NoteElementContext ctx) {}
	@Override public void enterNoteOrRest(NoteOrRestContext ctx) {}
	@Override public void enterPitch(PitchContext ctx) {}
	@Override public void enterOctave(OctaveContext ctx) {}
	@Override public void enterNoteLength(NoteLengthContext ctx) {}
	@Override public void enterNoteLengthStrict(NoteLengthStrictContext ctx) {}
	@Override public void enterAccidental(AccidentalContext ctx) {}
	@Override public void enterRest(RestContext ctx) {}
	@Override public void enterBasenote(BasenoteContext ctx) {}
	@Override public void enterTupletElement(TupletElementContext ctx) {}
	@Override public void enterMultiNote(MultiNoteContext ctx) {}    
	@Override public void enterNthRepeat(NthRepeatContext ctx) {}
	@Override public void enterMidTuneField(MidTuneFieldContext ctx) {}   
	@Override public void enterText(TextContext ctx) {}
	@Override public void enterComment(CommentContext ctx) {}
	@Override public void enterEndOfLine(EndOfLineContext ctx) {}
	@Override public void enterEveryRule(ParserRuleContext arg0) {}
	@Override public void exitEveryRule(ParserRuleContext arg0) {}
	@Override public void visitErrorNode(ErrorNode arg0) {}
	@Override public void visitTerminal(TerminalNode arg0) {}
	@Override public void enterDuplet(DupletContext ctx) {}
	@Override public void exitDuplet(DupletContext ctx) {}
	@Override public void enterTriplet(TripletContext ctx){}
	@Override public void exitTriplet(TripletContext ctx) {}
	@Override public void enterQuadruplet(QuadrupletContext ctx) {}
	@Override public void exitQuadruplet(QuadrupletContext ctx){}
	@Override public void exitAbcMusic(AbcMusicContext ctx) {}
	@Override public void exitAbcLine(AbcLineContext ctx) {}
	@Override public void exitElement(ElementContext ctx) {}
	@Override public void exitNoteElement(NoteElementContext ctx) {}
	@Override public void exitNoteOrRest(NoteOrRestContext ctx) {}
	@Override public void exitPitch(PitchContext ctx) {}
	@Override public void exitOctave(OctaveContext ctx) {}
	@Override public void exitNoteLength(NoteLengthContext ctx) {}
	@Override public void exitNoteLengthStrict(NoteLengthStrictContext ctx) {}
	@Override public void exitAccidental(AccidentalContext ctx) {}
	@Override public void exitBasenote(BasenoteContext ctx) {}
	@Override public void exitRest(RestContext ctx) {}
	@Override public void exitMidTuneField(MidTuneFieldContext ctx) {} 
}