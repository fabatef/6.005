// Generated from Abc.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AbcParser}.
 */
public interface AbcListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link AbcParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(AbcParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(AbcParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#abcMusic}.
   * @param ctx the parse tree
   */
  void enterAbcMusic(AbcParser.AbcMusicContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#abcMusic}.
   * @param ctx the parse tree
   */
  void exitAbcMusic(AbcParser.AbcMusicContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#abcLine}.
   * @param ctx the parse tree
   */
  void enterAbcLine(AbcParser.AbcLineContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#abcLine}.
   * @param ctx the parse tree
   */
  void exitAbcLine(AbcParser.AbcLineContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#element}.
   * @param ctx the parse tree
   */
  void enterElement(AbcParser.ElementContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#element}.
   * @param ctx the parse tree
   */
  void exitElement(AbcParser.ElementContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#noteElement}.
   * @param ctx the parse tree
   */
  void enterNoteElement(AbcParser.NoteElementContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#noteElement}.
   * @param ctx the parse tree
   */
  void exitNoteElement(AbcParser.NoteElementContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#note}.
   * @param ctx the parse tree
   */
  void enterNote(AbcParser.NoteContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#note}.
   * @param ctx the parse tree
   */
  void exitNote(AbcParser.NoteContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#noteOrRest}.
   * @param ctx the parse tree
   */
  void enterNoteOrRest(AbcParser.NoteOrRestContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#noteOrRest}.
   * @param ctx the parse tree
   */
  void exitNoteOrRest(AbcParser.NoteOrRestContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#pitch}.
   * @param ctx the parse tree
   */
  void enterPitch(AbcParser.PitchContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#pitch}.
   * @param ctx the parse tree
   */
  void exitPitch(AbcParser.PitchContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#octave}.
   * @param ctx the parse tree
   */
  void enterOctave(AbcParser.OctaveContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#octave}.
   * @param ctx the parse tree
   */
  void exitOctave(AbcParser.OctaveContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#noteLength}.
   * @param ctx the parse tree
   */
  void enterNoteLength(AbcParser.NoteLengthContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#noteLength}.
   * @param ctx the parse tree
   */
  void exitNoteLength(AbcParser.NoteLengthContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#noteLengthStrict}.
   * @param ctx the parse tree
   */
  void enterNoteLengthStrict(AbcParser.NoteLengthStrictContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#noteLengthStrict}.
   * @param ctx the parse tree
   */
  void exitNoteLengthStrict(AbcParser.NoteLengthStrictContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#accidental}.
   * @param ctx the parse tree
   */
  void enterAccidental(AbcParser.AccidentalContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#accidental}.
   * @param ctx the parse tree
   */
  void exitAccidental(AbcParser.AccidentalContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#basenote}.
   * @param ctx the parse tree
   */
  void enterBasenote(AbcParser.BasenoteContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#basenote}.
   * @param ctx the parse tree
   */
  void exitBasenote(AbcParser.BasenoteContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#rest}.
   * @param ctx the parse tree
   */
  void enterRest(AbcParser.RestContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#rest}.
   * @param ctx the parse tree
   */
  void exitRest(AbcParser.RestContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#tupletElement}.
   * @param ctx the parse tree
   */
  void enterTupletElement(AbcParser.TupletElementContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#tupletElement}.
   * @param ctx the parse tree
   */
  void exitTupletElement(AbcParser.TupletElementContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#duplet}.
   * @param ctx the parse tree
   */
  void enterDuplet(AbcParser.DupletContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#duplet}.
   * @param ctx the parse tree
   */
  void exitDuplet(AbcParser.DupletContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#triplet}.
   * @param ctx the parse tree
   */
  void enterTriplet(AbcParser.TripletContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#triplet}.
   * @param ctx the parse tree
   */
  void exitTriplet(AbcParser.TripletContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#quadruplet}.
   * @param ctx the parse tree
   */
  void enterQuadruplet(AbcParser.QuadrupletContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#quadruplet}.
   * @param ctx the parse tree
   */
  void exitQuadruplet(AbcParser.QuadrupletContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#multiNote}.
   * @param ctx the parse tree
   */
  void enterMultiNote(AbcParser.MultiNoteContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#multiNote}.
   * @param ctx the parse tree
   */
  void exitMultiNote(AbcParser.MultiNoteContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#barline}.
   * @param ctx the parse tree
   */
  void enterBarline(AbcParser.BarlineContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#barline}.
   * @param ctx the parse tree
   */
  void exitBarline(AbcParser.BarlineContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#nthRepeat}.
   * @param ctx the parse tree
   */
  void enterNthRepeat(AbcParser.NthRepeatContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#nthRepeat}.
   * @param ctx the parse tree
   */
  void exitNthRepeat(AbcParser.NthRepeatContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#midTuneField}.
   * @param ctx the parse tree
   */
  void enterMidTuneField(AbcParser.MidTuneFieldContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#midTuneField}.
   * @param ctx the parse tree
   */
  void exitMidTuneField(AbcParser.MidTuneFieldContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#fieldVoice}.
   * @param ctx the parse tree
   */
  void enterFieldVoice(AbcParser.FieldVoiceContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#fieldVoice}.
   * @param ctx the parse tree
   */
  void exitFieldVoice(AbcParser.FieldVoiceContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#comment}.
   * @param ctx the parse tree
   */
  void enterComment(AbcParser.CommentContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#comment}.
   * @param ctx the parse tree
   */
  void exitComment(AbcParser.CommentContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#endOfLine}.
   * @param ctx the parse tree
   */
  void enterEndOfLine(AbcParser.EndOfLineContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#endOfLine}.
   * @param ctx the parse tree
   */
  void exitEndOfLine(AbcParser.EndOfLineContext ctx);
  /**
   * Enter a parse tree produced by {@link AbcParser#text}.
   * @param ctx the parse tree
   */
  void enterText(AbcParser.TextContext ctx);
  /**
   * Exit a parse tree produced by {@link AbcParser#text}.
   * @param ctx the parse tree
   */
  void exitText(AbcParser.TextContext ctx);
}