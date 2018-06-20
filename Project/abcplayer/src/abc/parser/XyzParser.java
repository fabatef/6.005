// Generated from Xyz.g4 by ANTLR 4.5.1

package abc.parser;
// Do not edit this .java file! Edit the .g4 file and re-run Antlr.

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XyzParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, 
    T__16=17, T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, 
    T__23=24, T__24=25, T__25=26, T__26=27, T__27=28, ALPHA=29, PUNCTUATIONS=30, 
    NEWLINE=31, DIGIT=32, WHITESPACE=33;
  public static final int
    RULE_root = 0, RULE_abcHeader = 1, RULE_fieldNumber = 2, RULE_fieldTitle = 3, 
    RULE_otherFields = 4, RULE_fieldComposer = 5, RULE_fieldDefaultLength = 6, 
    RULE_fieldMeter = 7, RULE_fieldTempo = 8, RULE_fieldVoice = 9, RULE_fieldKey = 10, 
    RULE_key = 11, RULE_keynote = 12, RULE_keyAccidental = 13, RULE_modeMinor = 14, 
    RULE_basenote = 15, RULE_meter = 16, RULE_meterFraction = 17, RULE_tempo = 18, 
    RULE_noteLengthStrict = 19, RULE_comment = 20, RULE_endOfLine = 21, 
    RULE_text = 22;
  public static final String[] ruleNames = {
    "root", "abcHeader", "fieldNumber", "fieldTitle", "otherFields", "fieldComposer", 
    "fieldDefaultLength", "fieldMeter", "fieldTempo", "fieldVoice", "fieldKey", 
    "key", "keynote", "keyAccidental", "modeMinor", "basenote", "meter", 
    "meterFraction", "tempo", "noteLengthStrict", "comment", "endOfLine", 
    "text"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'X:'", "'T:'", "'C:'", "'L:'", "'M:'", "'Q:'", "'V:'", "'K:'", 
    "'#'", "'b'", "'m'", "'C'", "'D'", "'E'", "'F'", "'G'", "'A'", "'B'", 
    "'c'", "'d'", "'e'", "'f'", "'g'", "'a'", "'C|'", "'/'", "'='", "'%'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, "ALPHA", "PUNCTUATIONS", "NEWLINE", "DIGIT", 
    "WHITESPACE"
  };
  public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

  /**
   * @deprecated Use {@link #VOCABULARY} instead.
   */
  @Deprecated
  public static final String[] tokenNames;
  static {
    tokenNames = new String[_SYMBOLIC_NAMES.length];
    for (int i = 0; i < tokenNames.length; i++) {
      tokenNames[i] = VOCABULARY.getLiteralName(i);
      if (tokenNames[i] == null) {
        tokenNames[i] = VOCABULARY.getSymbolicName(i);
      }

      if (tokenNames[i] == null) {
        tokenNames[i] = "<INVALID>";
      }
    }
  }

  @Override
  @Deprecated
  public String[] getTokenNames() {
    return tokenNames;
  }

  @Override

  public Vocabulary getVocabulary() {
    return VOCABULARY;
  }

  @Override
  public String getGrammarFileName() { return "Xyz.g4"; }

  @Override
  public String[] getRuleNames() { return ruleNames; }

  @Override
  public String getSerializedATN() { return _serializedATN; }

  @Override
  public ATN getATN() { return _ATN; }


      // This method makes the parser stop running if it encounters
      // invalid input and throw a RuntimeException.
      public void reportErrorsAsExceptions() {
          // To prevent any reports to standard error, add this line:
          //removeErrorListeners();
          
          addErrorListener(new BaseErrorListener() {
              public void syntaxError(Recognizer<?, ?> recognizer,
                                      Object offendingSymbol, 
                                      int line, int charPositionInLine,
                                      String msg, RecognitionException e) {
                  throw new ParseCancellationException(msg, e);
              }
          });
      }

  public XyzParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public AbcHeaderContext abcHeader() {
      return getRuleContext(AbcHeaderContext.class,0);
    }
    public TerminalNode EOF() { return getToken(XyzParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(46);
      abcHeader();
      setState(47);
      match(EOF);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class AbcHeaderContext extends ParserRuleContext {
    public FieldNumberContext fieldNumber() {
      return getRuleContext(FieldNumberContext.class,0);
    }
    public FieldTitleContext fieldTitle() {
      return getRuleContext(FieldTitleContext.class,0);
    }
    public FieldKeyContext fieldKey() {
      return getRuleContext(FieldKeyContext.class,0);
    }
    public List<CommentContext> comment() {
      return getRuleContexts(CommentContext.class);
    }
    public CommentContext comment(int i) {
      return getRuleContext(CommentContext.class,i);
    }
    public List<OtherFieldsContext> otherFields() {
      return getRuleContexts(OtherFieldsContext.class);
    }
    public OtherFieldsContext otherFields(int i) {
      return getRuleContext(OtherFieldsContext.class,i);
    }
    public AbcHeaderContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_abcHeader; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterAbcHeader(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitAbcHeader(this);
    }
  }

  public final AbcHeaderContext abcHeader() throws RecognitionException {
    AbcHeaderContext _localctx = new AbcHeaderContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_abcHeader);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(49);
      fieldNumber();
      setState(53);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while (_la==T__27) {
        {
        {
        setState(50);
        comment();
        }
        }
        setState(55);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(56);
      fieldTitle();
      setState(60);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__27))) != 0)) {
        {
        {
        setState(57);
        otherFields();
        }
        }
        setState(62);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      setState(63);
      fieldKey();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldNumberContext extends ParserRuleContext {
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public List<TerminalNode> DIGIT() { return getTokens(XyzParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(XyzParser.DIGIT, i);
    }
    public FieldNumberContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldNumber; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldNumber(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldNumber(this);
    }
  }

  public final FieldNumberContext fieldNumber() throws RecognitionException {
    FieldNumberContext _localctx = new FieldNumberContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_fieldNumber);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(65);
      match(T__0);
      setState(67); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(66);
        match(DIGIT);
        }
        }
        setState(69); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      setState(71);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldTitleContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldTitleContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldTitle; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldTitle(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldTitle(this);
    }
  }

  public final FieldTitleContext fieldTitle() throws RecognitionException {
    FieldTitleContext _localctx = new FieldTitleContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_fieldTitle);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(73);
      match(T__1);
      setState(74);
      text();
      setState(75);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class OtherFieldsContext extends ParserRuleContext {
    public FieldComposerContext fieldComposer() {
      return getRuleContext(FieldComposerContext.class,0);
    }
    public FieldDefaultLengthContext fieldDefaultLength() {
      return getRuleContext(FieldDefaultLengthContext.class,0);
    }
    public FieldMeterContext fieldMeter() {
      return getRuleContext(FieldMeterContext.class,0);
    }
    public FieldTempoContext fieldTempo() {
      return getRuleContext(FieldTempoContext.class,0);
    }
    public FieldVoiceContext fieldVoice() {
      return getRuleContext(FieldVoiceContext.class,0);
    }
    public CommentContext comment() {
      return getRuleContext(CommentContext.class,0);
    }
    public OtherFieldsContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_otherFields; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterOtherFields(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitOtherFields(this);
    }
  }

  public final OtherFieldsContext otherFields() throws RecognitionException {
    OtherFieldsContext _localctx = new OtherFieldsContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_otherFields);
    try {
      setState(83);
      switch (_input.LA(1)) {
      case T__2:
        enterOuterAlt(_localctx, 1);
        {
        setState(77);
        fieldComposer();
        }
        break;
      case T__3:
        enterOuterAlt(_localctx, 2);
        {
        setState(78);
        fieldDefaultLength();
        }
        break;
      case T__4:
        enterOuterAlt(_localctx, 3);
        {
        setState(79);
        fieldMeter();
        }
        break;
      case T__5:
        enterOuterAlt(_localctx, 4);
        {
        setState(80);
        fieldTempo();
        }
        break;
      case T__6:
        enterOuterAlt(_localctx, 5);
        {
        setState(81);
        fieldVoice();
        }
        break;
      case T__27:
        enterOuterAlt(_localctx, 6);
        {
        setState(82);
        comment();
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldComposerContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldComposerContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldComposer; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldComposer(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldComposer(this);
    }
  }

  public final FieldComposerContext fieldComposer() throws RecognitionException {
    FieldComposerContext _localctx = new FieldComposerContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_fieldComposer);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(85);
      match(T__2);
      setState(86);
      text();
      setState(87);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldDefaultLengthContext extends ParserRuleContext {
    public NoteLengthStrictContext noteLengthStrict() {
      return getRuleContext(NoteLengthStrictContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldDefaultLengthContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldDefaultLength; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldDefaultLength(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldDefaultLength(this);
    }
  }

  public final FieldDefaultLengthContext fieldDefaultLength() throws RecognitionException {
    FieldDefaultLengthContext _localctx = new FieldDefaultLengthContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_fieldDefaultLength);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(89);
      match(T__3);
      setState(90);
      noteLengthStrict();
      setState(91);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldMeterContext extends ParserRuleContext {
    public MeterContext meter() {
      return getRuleContext(MeterContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldMeterContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldMeter; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldMeter(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldMeter(this);
    }
  }

  public final FieldMeterContext fieldMeter() throws RecognitionException {
    FieldMeterContext _localctx = new FieldMeterContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_fieldMeter);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(93);
      match(T__4);
      setState(94);
      meter();
      setState(95);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldTempoContext extends ParserRuleContext {
    public TempoContext tempo() {
      return getRuleContext(TempoContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldTempoContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldTempo; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldTempo(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldTempo(this);
    }
  }

  public final FieldTempoContext fieldTempo() throws RecognitionException {
    FieldTempoContext _localctx = new FieldTempoContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_fieldTempo);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(97);
      match(T__5);
      setState(98);
      tempo();
      setState(99);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldVoiceContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldVoiceContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldVoice; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldVoice(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldVoice(this);
    }
  }

  public final FieldVoiceContext fieldVoice() throws RecognitionException {
    FieldVoiceContext _localctx = new FieldVoiceContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_fieldVoice);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(101);
      match(T__6);
      setState(102);
      text();
      setState(103);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class FieldKeyContext extends ParserRuleContext {
    public KeyContext key() {
      return getRuleContext(KeyContext.class,0);
    }
    public EndOfLineContext endOfLine() {
      return getRuleContext(EndOfLineContext.class,0);
    }
    public FieldKeyContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_fieldKey; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterFieldKey(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitFieldKey(this);
    }
  }

  public final FieldKeyContext fieldKey() throws RecognitionException {
    FieldKeyContext _localctx = new FieldKeyContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_fieldKey);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(105);
      match(T__7);
      setState(106);
      key();
      setState(107);
      endOfLine();
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class KeyContext extends ParserRuleContext {
    public KeynoteContext keynote() {
      return getRuleContext(KeynoteContext.class,0);
    }
    public ModeMinorContext modeMinor() {
      return getRuleContext(ModeMinorContext.class,0);
    }
    public KeyContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_key; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterKey(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitKey(this);
    }
  }

  public final KeyContext key() throws RecognitionException {
    KeyContext _localctx = new KeyContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_key);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(109);
      keynote();
      setState(111);
      _la = _input.LA(1);
      if (_la==T__10) {
        {
        setState(110);
        modeMinor();
        }
      }

      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class KeynoteContext extends ParserRuleContext {
    public BasenoteContext basenote() {
      return getRuleContext(BasenoteContext.class,0);
    }
    public KeyAccidentalContext keyAccidental() {
      return getRuleContext(KeyAccidentalContext.class,0);
    }
    public KeynoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_keynote; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterKeynote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitKeynote(this);
    }
  }

  public final KeynoteContext keynote() throws RecognitionException {
    KeynoteContext _localctx = new KeynoteContext(_ctx, getState());
    enterRule(_localctx, 24, RULE_keynote);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(113);
      basenote();
      setState(115);
      _la = _input.LA(1);
      if (_la==T__8 || _la==T__9) {
        {
        setState(114);
        keyAccidental();
        }
      }

      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class KeyAccidentalContext extends ParserRuleContext {
    public KeyAccidentalContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_keyAccidental; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterKeyAccidental(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitKeyAccidental(this);
    }
  }

  public final KeyAccidentalContext keyAccidental() throws RecognitionException {
    KeyAccidentalContext _localctx = new KeyAccidentalContext(_ctx, getState());
    enterRule(_localctx, 26, RULE_keyAccidental);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(117);
      _la = _input.LA(1);
      if ( !(_la==T__8 || _la==T__9) ) {
      _errHandler.recoverInline(this);
      } else {
        consume();
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class ModeMinorContext extends ParserRuleContext {
    public ModeMinorContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_modeMinor; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterModeMinor(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitModeMinor(this);
    }
  }

  public final ModeMinorContext modeMinor() throws RecognitionException {
    ModeMinorContext _localctx = new ModeMinorContext(_ctx, getState());
    enterRule(_localctx, 28, RULE_modeMinor);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(119);
      match(T__10);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class BasenoteContext extends ParserRuleContext {
    public BasenoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_basenote; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterBasenote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitBasenote(this);
    }
  }

  public final BasenoteContext basenote() throws RecognitionException {
    BasenoteContext _localctx = new BasenoteContext(_ctx, getState());
    enterRule(_localctx, 30, RULE_basenote);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(121);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23))) != 0)) ) {
      _errHandler.recoverInline(this);
      } else {
        consume();
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MeterContext extends ParserRuleContext {
    public MeterFractionContext meterFraction() {
      return getRuleContext(MeterFractionContext.class,0);
    }
    public MeterContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_meter; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterMeter(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitMeter(this);
    }
  }

  public final MeterContext meter() throws RecognitionException {
    MeterContext _localctx = new MeterContext(_ctx, getState());
    enterRule(_localctx, 32, RULE_meter);
    try {
      setState(126);
      switch (_input.LA(1)) {
      case T__11:
        enterOuterAlt(_localctx, 1);
        {
        setState(123);
        match(T__11);
        }
        break;
      case T__24:
        enterOuterAlt(_localctx, 2);
        {
        setState(124);
        match(T__24);
        }
        break;
      case DIGIT:
        enterOuterAlt(_localctx, 3);
        {
        setState(125);
        meterFraction();
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class MeterFractionContext extends ParserRuleContext {
    public List<TerminalNode> DIGIT() { return getTokens(XyzParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(XyzParser.DIGIT, i);
    }
    public MeterFractionContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_meterFraction; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterMeterFraction(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitMeterFraction(this);
    }
  }

  public final MeterFractionContext meterFraction() throws RecognitionException {
    MeterFractionContext _localctx = new MeterFractionContext(_ctx, getState());
    enterRule(_localctx, 34, RULE_meterFraction);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(129); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(128);
        match(DIGIT);
        }
        }
        setState(131); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      setState(133);
      match(T__25);
      setState(135); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(134);
        match(DIGIT);
        }
        }
        setState(137); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class TempoContext extends ParserRuleContext {
    public MeterFractionContext meterFraction() {
      return getRuleContext(MeterFractionContext.class,0);
    }
    public List<TerminalNode> DIGIT() { return getTokens(XyzParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(XyzParser.DIGIT, i);
    }
    public TempoContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_tempo; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterTempo(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitTempo(this);
    }
  }

  public final TempoContext tempo() throws RecognitionException {
    TempoContext _localctx = new TempoContext(_ctx, getState());
    enterRule(_localctx, 36, RULE_tempo);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(139);
      meterFraction();
      setState(140);
      match(T__26);
      setState(142); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(141);
        match(DIGIT);
        }
        }
        setState(144); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class NoteLengthStrictContext extends ParserRuleContext {
    public List<TerminalNode> DIGIT() { return getTokens(XyzParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(XyzParser.DIGIT, i);
    }
    public NoteLengthStrictContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteLengthStrict; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterNoteLengthStrict(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitNoteLengthStrict(this);
    }
  }

  public final NoteLengthStrictContext noteLengthStrict() throws RecognitionException {
    NoteLengthStrictContext _localctx = new NoteLengthStrictContext(_ctx, getState());
    enterRule(_localctx, 38, RULE_noteLengthStrict);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(151);
      _la = _input.LA(1);
      if (_la==DIGIT) {
        {
        setState(147); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(146);
          match(DIGIT);
          }
          }
          setState(149); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
        }
      }

      setState(153);
      match(T__25);
      setState(155); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(154);
        match(DIGIT);
        }
        }
        setState(157); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( _la==DIGIT );
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class CommentContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public TerminalNode NEWLINE() { return getToken(XyzParser.NEWLINE, 0); }
    public CommentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_comment; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterComment(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitComment(this);
    }
  }

  public final CommentContext comment() throws RecognitionException {
    CommentContext _localctx = new CommentContext(_ctx, getState());
    enterRule(_localctx, 40, RULE_comment);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(159);
      match(T__27);
      setState(160);
      text();
      setState(161);
      match(NEWLINE);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class EndOfLineContext extends ParserRuleContext {
    public CommentContext comment() {
      return getRuleContext(CommentContext.class,0);
    }
    public TerminalNode NEWLINE() { return getToken(XyzParser.NEWLINE, 0); }
    public EndOfLineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_endOfLine; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterEndOfLine(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitEndOfLine(this);
    }
  }

  public final EndOfLineContext endOfLine() throws RecognitionException {
    EndOfLineContext _localctx = new EndOfLineContext(_ctx, getState());
    enterRule(_localctx, 42, RULE_endOfLine);
    try {
      setState(165);
      switch (_input.LA(1)) {
      case T__27:
        enterOuterAlt(_localctx, 1);
        {
        setState(163);
        comment();
        }
        break;
      case NEWLINE:
        enterOuterAlt(_localctx, 2);
        {
        setState(164);
        match(NEWLINE);
        }
        break;
      default:
        throw new NoViableAltException(this);
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static class TextContext extends ParserRuleContext {
    public List<BasenoteContext> basenote() {
      return getRuleContexts(BasenoteContext.class);
    }
    public BasenoteContext basenote(int i) {
      return getRuleContext(BasenoteContext.class,i);
    }
    public List<TerminalNode> ALPHA() { return getTokens(XyzParser.ALPHA); }
    public TerminalNode ALPHA(int i) {
      return getToken(XyzParser.ALPHA, i);
    }
    public List<TerminalNode> WHITESPACE() { return getTokens(XyzParser.WHITESPACE); }
    public TerminalNode WHITESPACE(int i) {
      return getToken(XyzParser.WHITESPACE, i);
    }
    public List<TerminalNode> PUNCTUATIONS() { return getTokens(XyzParser.PUNCTUATIONS); }
    public TerminalNode PUNCTUATIONS(int i) {
      return getToken(XyzParser.PUNCTUATIONS, i);
    }
    public List<TerminalNode> DIGIT() { return getTokens(XyzParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(XyzParser.DIGIT, i);
    }
    public List<ModeMinorContext> modeMinor() {
      return getRuleContexts(ModeMinorContext.class);
    }
    public ModeMinorContext modeMinor(int i) {
      return getRuleContext(ModeMinorContext.class,i);
    }
    public TextContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_text; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).enterText(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof XyzListener ) ((XyzListener)listener).exitText(this);
    }
  }

  public final TextContext text() throws RecognitionException {
    TextContext _localctx = new TextContext(_ctx, getState());
    enterRule(_localctx, 44, RULE_text);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(175);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << ALPHA) | (1L << PUNCTUATIONS) | (1L << DIGIT) | (1L << WHITESPACE))) != 0)) {
        {
        setState(173);
        switch (_input.LA(1)) {
        case T__9:
        case T__11:
        case T__12:
        case T__13:
        case T__14:
        case T__15:
        case T__16:
        case T__17:
        case T__18:
        case T__19:
        case T__20:
        case T__21:
        case T__22:
        case T__23:
          {
          setState(167);
          basenote();
          }
          break;
        case ALPHA:
          {
          setState(168);
          match(ALPHA);
          }
          break;
        case WHITESPACE:
          {
          setState(169);
          match(WHITESPACE);
          }
          break;
        case PUNCTUATIONS:
          {
          setState(170);
          match(PUNCTUATIONS);
          }
          break;
        case DIGIT:
          {
          setState(171);
          match(DIGIT);
          }
          break;
        case T__10:
          {
          setState(172);
          modeMinor();
          }
          break;
        default:
          throw new NoViableAltException(this);
        }
        }
        setState(177);
        _errHandler.sync(this);
        _la = _input.LA(1);
      }
      }
    }
    catch (RecognitionException re) {
      _localctx.exception = re;
      _errHandler.reportError(this, re);
      _errHandler.recover(this, re);
    }
    finally {
      exitRule();
    }
    return _localctx;
  }

  public static final String _serializedATN =
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u00b5\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
      "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
      "\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
      "\30\3\2\3\2\3\2\3\3\3\3\7\3\66\n\3\f\3\16\39\13\3\3\3\3\3\7\3=\n\3"+
      "\f\3\16\3@\13\3\3\3\3\3\3\4\3\4\6\4F\n\4\r\4\16\4G\3\4\3\4\3\5\3\5"+
      "\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6V\n\6\3\7\3\7\3\7\3\7\3\b\3\b"+
      "\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3"+
      "\f\3\f\3\f\3\r\3\r\5\rr\n\r\3\16\3\16\5\16v\n\16\3\17\3\17\3\20\3"+
      "\20\3\21\3\21\3\22\3\22\3\22\5\22\u0081\n\22\3\23\6\23\u0084\n\23"+
      "\r\23\16\23\u0085\3\23\3\23\6\23\u008a\n\23\r\23\16\23\u008b\3\24"+
      "\3\24\3\24\6\24\u0091\n\24\r\24\16\24\u0092\3\25\6\25\u0096\n\25\r"+
      "\25\16\25\u0097\5\25\u009a\n\25\3\25\3\25\6\25\u009e\n\25\r\25\16"+
      "\25\u009f\3\26\3\26\3\26\3\26\3\27\3\27\5\27\u00a8\n\27\3\30\3\30"+
      "\3\30\3\30\3\30\3\30\7\30\u00b0\n\30\f\30\16\30\u00b3\13\30\3\30\2"+
      "\2\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\4\3\2\13\f"+
      "\4\2\f\f\16\32\u00b6\2\60\3\2\2\2\4\63\3\2\2\2\6C\3\2\2\2\bK\3\2\2"+
      "\2\nU\3\2\2\2\fW\3\2\2\2\16[\3\2\2\2\20_\3\2\2\2\22c\3\2\2\2\24g\3"+
      "\2\2\2\26k\3\2\2\2\30o\3\2\2\2\32s\3\2\2\2\34w\3\2\2\2\36y\3\2\2\2"+
      " {\3\2\2\2\"\u0080\3\2\2\2$\u0083\3\2\2\2&\u008d\3\2\2\2(\u0099\3"+
      "\2\2\2*\u00a1\3\2\2\2,\u00a7\3\2\2\2.\u00b1\3\2\2\2\60\61\5\4\3\2"+
      "\61\62\7\2\2\3\62\3\3\2\2\2\63\67\5\6\4\2\64\66\5*\26\2\65\64\3\2"+
      "\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:"+
      ">\5\b\5\2;=\5\n\6\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2"+
      "\2\2@>\3\2\2\2AB\5\26\f\2B\5\3\2\2\2CE\7\3\2\2DF\7\"\2\2ED\3\2\2\2"+
      "FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\5,\27\2J\7\3\2\2\2KL\7"+
      "\4\2\2LM\5.\30\2MN\5,\27\2N\t\3\2\2\2OV\5\f\7\2PV\5\16\b\2QV\5\20"+
      "\t\2RV\5\22\n\2SV\5\24\13\2TV\5*\26\2UO\3\2\2\2UP\3\2\2\2UQ\3\2\2"+
      "\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\13\3\2\2\2WX\7\5\2\2XY\5.\30\2Y"+
      "Z\5,\27\2Z\r\3\2\2\2[\\\7\6\2\2\\]\5(\25\2]^\5,\27\2^\17\3\2\2\2_"+
      "`\7\7\2\2`a\5\"\22\2ab\5,\27\2b\21\3\2\2\2cd\7\b\2\2de\5&\24\2ef\5"+
      ",\27\2f\23\3\2\2\2gh\7\t\2\2hi\5.\30\2ij\5,\27\2j\25\3\2\2\2kl\7\n"+
      "\2\2lm\5\30\r\2mn\5,\27\2n\27\3\2\2\2oq\5\32\16\2pr\5\36\20\2qp\3"+
      "\2\2\2qr\3\2\2\2r\31\3\2\2\2su\5 \21\2tv\5\34\17\2ut\3\2\2\2uv\3\2"+
      "\2\2v\33\3\2\2\2wx\t\2\2\2x\35\3\2\2\2yz\7\r\2\2z\37\3\2\2\2{|\t\3"+
      "\2\2|!\3\2\2\2}\u0081\7\16\2\2~\u0081\7\33\2\2\177\u0081\5$\23\2\u0080"+
      "}\3\2\2\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081#\3\2\2\2\u0082\u0084"+
      "\7\"\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2"+
      "\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\7\34\2\2\u0088"+
      "\u008a\7\"\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089"+
      "\3\2\2\2\u008b\u008c\3\2\2\2\u008c%\3\2\2\2\u008d\u008e\5$\23\2\u008e"+
      "\u0090\7\35\2\2\u008f\u0091\7\"\2\2\u0090\u008f\3\2\2\2\u0091\u0092"+
      "\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093\'\3\2\2\2\u0094"+
      "\u0096\7\"\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0095"+
      "\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u0095\3\2\2"+
      "\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\7\34\2\2\u009c"+
      "\u009e\7\"\2\2\u009d\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u009d"+
      "\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0)\3\2\2\2\u00a1\u00a2\7\36\2\2\u00a2"+
      "\u00a3\5.\30\2\u00a3\u00a4\7!\2\2\u00a4+\3\2\2\2\u00a5\u00a8\5*\26"+
      "\2\u00a6\u00a8\7!\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8"+
      "-\3\2\2\2\u00a9\u00b0\5 \21\2\u00aa\u00b0\7\37\2\2\u00ab\u00b0\7#"+
      "\2\2\u00ac\u00b0\7 \2\2\u00ad\u00b0\7\"\2\2\u00ae\u00b0\5\36\20\2"+
      "\u00af\u00a9\3\2\2\2\u00af\u00aa\3\2\2\2\u00af\u00ab\3\2\2\2\u00af"+
      "\u00ac\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b3"+
      "\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2/\3\2\2\2\u00b3"+
      "\u00b1\3\2\2\2\22\67>GUqu\u0080\u0085\u008b\u0092\u0097\u0099\u009f"+
      "\u00a7\u00af\u00b1";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}