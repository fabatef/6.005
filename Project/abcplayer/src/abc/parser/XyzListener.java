// Generated from Xyz.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XyzParser}.
 */
public interface XyzListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link XyzParser#root}.
   * @param ctx the parse tree
   */
  void enterRoot(XyzParser.RootContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#root}.
   * @param ctx the parse tree
   */
  void exitRoot(XyzParser.RootContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#abcHeader}.
   * @param ctx the parse tree
   */
  void enterAbcHeader(XyzParser.AbcHeaderContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#abcHeader}.
   * @param ctx the parse tree
   */
  void exitAbcHeader(XyzParser.AbcHeaderContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldNumber}.
   * @param ctx the parse tree
   */
  void enterFieldNumber(XyzParser.FieldNumberContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldNumber}.
   * @param ctx the parse tree
   */
  void exitFieldNumber(XyzParser.FieldNumberContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldTitle}.
   * @param ctx the parse tree
   */
  void enterFieldTitle(XyzParser.FieldTitleContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldTitle}.
   * @param ctx the parse tree
   */
  void exitFieldTitle(XyzParser.FieldTitleContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#otherFields}.
   * @param ctx the parse tree
   */
  void enterOtherFields(XyzParser.OtherFieldsContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#otherFields}.
   * @param ctx the parse tree
   */
  void exitOtherFields(XyzParser.OtherFieldsContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldComposer}.
   * @param ctx the parse tree
   */
  void enterFieldComposer(XyzParser.FieldComposerContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldComposer}.
   * @param ctx the parse tree
   */
  void exitFieldComposer(XyzParser.FieldComposerContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldDefaultLength}.
   * @param ctx the parse tree
   */
  void enterFieldDefaultLength(XyzParser.FieldDefaultLengthContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldDefaultLength}.
   * @param ctx the parse tree
   */
  void exitFieldDefaultLength(XyzParser.FieldDefaultLengthContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldMeter}.
   * @param ctx the parse tree
   */
  void enterFieldMeter(XyzParser.FieldMeterContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldMeter}.
   * @param ctx the parse tree
   */
  void exitFieldMeter(XyzParser.FieldMeterContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldTempo}.
   * @param ctx the parse tree
   */
  void enterFieldTempo(XyzParser.FieldTempoContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldTempo}.
   * @param ctx the parse tree
   */
  void exitFieldTempo(XyzParser.FieldTempoContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldVoice}.
   * @param ctx the parse tree
   */
  void enterFieldVoice(XyzParser.FieldVoiceContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldVoice}.
   * @param ctx the parse tree
   */
  void exitFieldVoice(XyzParser.FieldVoiceContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#fieldKey}.
   * @param ctx the parse tree
   */
  void enterFieldKey(XyzParser.FieldKeyContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#fieldKey}.
   * @param ctx the parse tree
   */
  void exitFieldKey(XyzParser.FieldKeyContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#key}.
   * @param ctx the parse tree
   */
  void enterKey(XyzParser.KeyContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#key}.
   * @param ctx the parse tree
   */
  void exitKey(XyzParser.KeyContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#keynote}.
   * @param ctx the parse tree
   */
  void enterKeynote(XyzParser.KeynoteContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#keynote}.
   * @param ctx the parse tree
   */
  void exitKeynote(XyzParser.KeynoteContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#keyAccidental}.
   * @param ctx the parse tree
   */
  void enterKeyAccidental(XyzParser.KeyAccidentalContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#keyAccidental}.
   * @param ctx the parse tree
   */
  void exitKeyAccidental(XyzParser.KeyAccidentalContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#modeMinor}.
   * @param ctx the parse tree
   */
  void enterModeMinor(XyzParser.ModeMinorContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#modeMinor}.
   * @param ctx the parse tree
   */
  void exitModeMinor(XyzParser.ModeMinorContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#basenote}.
   * @param ctx the parse tree
   */
  void enterBasenote(XyzParser.BasenoteContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#basenote}.
   * @param ctx the parse tree
   */
  void exitBasenote(XyzParser.BasenoteContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#meter}.
   * @param ctx the parse tree
   */
  void enterMeter(XyzParser.MeterContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#meter}.
   * @param ctx the parse tree
   */
  void exitMeter(XyzParser.MeterContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#meterFraction}.
   * @param ctx the parse tree
   */
  void enterMeterFraction(XyzParser.MeterFractionContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#meterFraction}.
   * @param ctx the parse tree
   */
  void exitMeterFraction(XyzParser.MeterFractionContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#tempo}.
   * @param ctx the parse tree
   */
  void enterTempo(XyzParser.TempoContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#tempo}.
   * @param ctx the parse tree
   */
  void exitTempo(XyzParser.TempoContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#noteLengthStrict}.
   * @param ctx the parse tree
   */
  void enterNoteLengthStrict(XyzParser.NoteLengthStrictContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#noteLengthStrict}.
   * @param ctx the parse tree
   */
  void exitNoteLengthStrict(XyzParser.NoteLengthStrictContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#comment}.
   * @param ctx the parse tree
   */
  void enterComment(XyzParser.CommentContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#comment}.
   * @param ctx the parse tree
   */
  void exitComment(XyzParser.CommentContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#endOfLine}.
   * @param ctx the parse tree
   */
  void enterEndOfLine(XyzParser.EndOfLineContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#endOfLine}.
   * @param ctx the parse tree
   */
  void exitEndOfLine(XyzParser.EndOfLineContext ctx);
  /**
   * Enter a parse tree produced by {@link XyzParser#text}.
   * @param ctx the parse tree
   */
  void enterText(XyzParser.TextContext ctx);
  /**
   * Exit a parse tree produced by {@link XyzParser#text}.
   * @param ctx the parse tree
   */
  void exitText(XyzParser.TextContext ctx);
}