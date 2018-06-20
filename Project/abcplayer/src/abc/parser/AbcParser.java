// Generated from Abc.g4 by ANTLR 4.5.1

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
public class AbcParser extends Parser {
  static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

  protected static final DFA[] _decisionToDFA;
  protected static final PredictionContextCache _sharedContextCache =
    new PredictionContextCache();
  public static final int
    T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
    T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, 
    T__16=17, T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, 
    T__23=24, T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, 
    T__30=31, T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, 
    T__37=38, ALPHA=39, PUNCTUATIONS=40, NEWLINE=41, DIGIT=42, WHITESPACE=43;
  public static final int
    RULE_root = 0, RULE_abcMusic = 1, RULE_abcLine = 2, RULE_element = 3, 
    RULE_noteElement = 4, RULE_note = 5, RULE_noteOrRest = 6, RULE_pitch = 7, 
    RULE_octave = 8, RULE_noteLength = 9, RULE_noteLengthStrict = 10, RULE_accidental = 11, 
    RULE_basenote = 12, RULE_rest = 13, RULE_tupletElement = 14, RULE_duplet = 15, 
    RULE_triplet = 16, RULE_quadruplet = 17, RULE_multiNote = 18, RULE_barline = 19, 
    RULE_nthRepeat = 20, RULE_midTuneField = 21, RULE_fieldVoice = 22, RULE_comment = 23, 
    RULE_endOfLine = 24, RULE_text = 25;
  public static final String[] ruleNames = {
    "root", "abcMusic", "abcLine", "element", "noteElement", "note", "noteOrRest", 
    "pitch", "octave", "noteLength", "noteLengthStrict", "accidental", "basenote", 
    "rest", "tupletElement", "duplet", "triplet", "quadruplet", "multiNote", 
    "barline", "nthRepeat", "midTuneField", "fieldVoice", "comment", "endOfLine", 
    "text"
  };

  private static final String[] _LITERAL_NAMES = {
    null, "'''", "','", "'/'", "'^'", "'^^'", "'_'", "'__'", "'='", "'C'", 
    "'D'", "'E'", "'F'", "'G'", "'A'", "'B'", "'c'", "'d'", "'e'", "'f'", 
    "'g'", "'a'", "'b'", "'z'", "'(2'", "'(3'", "'(4'", "'['", "']'", "'|'", 
    "'||'", "'[|'", "'|]'", "':|'", "'|:'", "'[1'", "'[2'", "'V:'", "'%'"
  };
  private static final String[] _SYMBOLIC_NAMES = {
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, null, null, null, null, null, null, null, null, null, 
    null, null, null, "ALPHA", "PUNCTUATIONS", "NEWLINE", "DIGIT", "WHITESPACE"
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
  public String getGrammarFileName() { return "Abc.g4"; }

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

  public AbcParser(TokenStream input) {
    super(input);
    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
  }
  public static class RootContext extends ParserRuleContext {
    public AbcMusicContext abcMusic() {
      return getRuleContext(AbcMusicContext.class,0);
    }
    public TerminalNode EOF() { return getToken(AbcParser.EOF, 0); }
    public RootContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_root; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterRoot(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitRoot(this);
    }
  }

  public final RootContext root() throws RecognitionException {
    RootContext _localctx = new RootContext(_ctx, getState());
    enterRule(_localctx, 0, RULE_root);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(52);
      abcMusic();
      setState(53);
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

  public static class AbcMusicContext extends ParserRuleContext {
    public List<AbcLineContext> abcLine() {
      return getRuleContexts(AbcLineContext.class);
    }
    public AbcLineContext abcLine(int i) {
      return getRuleContext(AbcLineContext.class,i);
    }
    public AbcMusicContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_abcMusic; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterAbcMusic(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitAbcMusic(this);
    }
  }

  public final AbcMusicContext abcMusic() throws RecognitionException {
    AbcMusicContext _localctx = new AbcMusicContext(_ctx, getState());
    enterRule(_localctx, 2, RULE_abcMusic);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(56); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(55);
        abcLine();
        }
        }
        setState(58); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << NEWLINE) | (1L << WHITESPACE))) != 0) );
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

  public static class AbcLineContext extends ParserRuleContext {
    public TerminalNode NEWLINE() { return getToken(AbcParser.NEWLINE, 0); }
    public List<ElementContext> element() {
      return getRuleContexts(ElementContext.class);
    }
    public ElementContext element(int i) {
      return getRuleContext(ElementContext.class,i);
    }
    public MidTuneFieldContext midTuneField() {
      return getRuleContext(MidTuneFieldContext.class,0);
    }
    public CommentContext comment() {
      return getRuleContext(CommentContext.class,0);
    }
    public AbcLineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_abcLine; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterAbcLine(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitAbcLine(this);
    }
  }

  public final AbcLineContext abcLine() throws RecognitionException {
    AbcLineContext _localctx = new AbcLineContext(_ctx, getState());
    enterRule(_localctx, 4, RULE_abcLine);
    int _la;
    try {
      setState(70);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case T__9:
      case T__10:
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
      case T__24:
      case T__25:
      case T__26:
      case T__28:
      case T__29:
      case T__30:
      case T__31:
      case T__32:
      case T__33:
      case T__34:
      case T__35:
      case WHITESPACE:
        enterOuterAlt(_localctx, 1);
        {
        setState(61); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(60);
          element();
          }
          }
          setState(63); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << WHITESPACE))) != 0) );
        setState(65);
        match(NEWLINE);
        }
        break;
      case T__36:
        enterOuterAlt(_localctx, 2);
        {
        setState(67);
        midTuneField();
        }
        break;
      case T__37:
        enterOuterAlt(_localctx, 3);
        {
        setState(68);
        comment();
        }
        break;
      case NEWLINE:
        enterOuterAlt(_localctx, 4);
        {
        setState(69);
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

  public static class ElementContext extends ParserRuleContext {
    public NoteElementContext noteElement() {
      return getRuleContext(NoteElementContext.class,0);
    }
    public TupletElementContext tupletElement() {
      return getRuleContext(TupletElementContext.class,0);
    }
    public BarlineContext barline() {
      return getRuleContext(BarlineContext.class,0);
    }
    public NthRepeatContext nthRepeat() {
      return getRuleContext(NthRepeatContext.class,0);
    }
    public TerminalNode WHITESPACE() { return getToken(AbcParser.WHITESPACE, 0); }
    public ElementContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_element; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterElement(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitElement(this);
    }
  }

  public final ElementContext element() throws RecognitionException {
    ElementContext _localctx = new ElementContext(_ctx, getState());
    enterRule(_localctx, 6, RULE_element);
    try {
      setState(77);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case T__9:
      case T__10:
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
      case T__26:
        enterOuterAlt(_localctx, 1);
        {
        setState(72);
        noteElement();
        }
        break;
      case T__23:
      case T__24:
      case T__25:
        enterOuterAlt(_localctx, 2);
        {
        setState(73);
        tupletElement();
        }
        break;
      case T__28:
      case T__29:
      case T__30:
      case T__31:
      case T__32:
      case T__33:
        enterOuterAlt(_localctx, 3);
        {
        setState(74);
        barline();
        }
        break;
      case T__34:
      case T__35:
        enterOuterAlt(_localctx, 4);
        {
        setState(75);
        nthRepeat();
        }
        break;
      case WHITESPACE:
        enterOuterAlt(_localctx, 5);
        {
        setState(76);
        match(WHITESPACE);
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

  public static class NoteElementContext extends ParserRuleContext {
    public NoteContext note() {
      return getRuleContext(NoteContext.class,0);
    }
    public MultiNoteContext multiNote() {
      return getRuleContext(MultiNoteContext.class,0);
    }
    public NoteElementContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteElement; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterNoteElement(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitNoteElement(this);
    }
  }

  public final NoteElementContext noteElement() throws RecognitionException {
    NoteElementContext _localctx = new NoteElementContext(_ctx, getState());
    enterRule(_localctx, 8, RULE_noteElement);
    try {
      setState(81);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case T__9:
      case T__10:
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
        enterOuterAlt(_localctx, 1);
        {
        setState(79);
        note();
        }
        break;
      case T__26:
        enterOuterAlt(_localctx, 2);
        {
        setState(80);
        multiNote();
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

  public static class NoteContext extends ParserRuleContext {
    public NoteOrRestContext noteOrRest() {
      return getRuleContext(NoteOrRestContext.class,0);
    }
    public NoteLengthContext noteLength() {
      return getRuleContext(NoteLengthContext.class,0);
    }
    public NoteLengthStrictContext noteLengthStrict() {
      return getRuleContext(NoteLengthStrictContext.class,0);
    }
    public NoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_note; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterNote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitNote(this);
    }
  }

  public final NoteContext note() throws RecognitionException {
    NoteContext _localctx = new NoteContext(_ctx, getState());
    enterRule(_localctx, 10, RULE_note);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(83);
      noteOrRest();
      setState(86);
      switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
      case 1:
        {
        setState(84);
        noteLength();
        }
        break;
      case 2:
        {
        setState(85);
        noteLengthStrict();
        }
        break;
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

  public static class NoteOrRestContext extends ParserRuleContext {
    public PitchContext pitch() {
      return getRuleContext(PitchContext.class,0);
    }
    public RestContext rest() {
      return getRuleContext(RestContext.class,0);
    }
    public NoteOrRestContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteOrRest; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterNoteOrRest(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitNoteOrRest(this);
    }
  }

  public final NoteOrRestContext noteOrRest() throws RecognitionException {
    NoteOrRestContext _localctx = new NoteOrRestContext(_ctx, getState());
    enterRule(_localctx, 12, RULE_noteOrRest);
    try {
      setState(90);
      switch (_input.LA(1)) {
      case T__3:
      case T__4:
      case T__5:
      case T__6:
      case T__7:
      case T__8:
      case T__9:
      case T__10:
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
        enterOuterAlt(_localctx, 1);
        {
        setState(88);
        pitch();
        }
        break;
      case T__22:
        enterOuterAlt(_localctx, 2);
        {
        setState(89);
        rest();
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

  public static class PitchContext extends ParserRuleContext {
    public BasenoteContext basenote() {
      return getRuleContext(BasenoteContext.class,0);
    }
    public AccidentalContext accidental() {
      return getRuleContext(AccidentalContext.class,0);
    }
    public OctaveContext octave() {
      return getRuleContext(OctaveContext.class,0);
    }
    public PitchContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_pitch; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterPitch(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitPitch(this);
    }
  }

  public final PitchContext pitch() throws RecognitionException {
    PitchContext _localctx = new PitchContext(_ctx, getState());
    enterRule(_localctx, 14, RULE_pitch);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(93);
      _la = _input.LA(1);
      if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) {
        {
        setState(92);
        accidental();
        }
      }

      setState(95);
      basenote();
      setState(97);
      _la = _input.LA(1);
      if (_la==T__0 || _la==T__1) {
        {
        setState(96);
        octave();
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

  public static class OctaveContext extends ParserRuleContext {
    public OctaveContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_octave; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterOctave(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitOctave(this);
    }
  }

  public final OctaveContext octave() throws RecognitionException {
    OctaveContext _localctx = new OctaveContext(_ctx, getState());
    enterRule(_localctx, 16, RULE_octave);
    int _la;
    try {
      setState(109);
      switch (_input.LA(1)) {
      case T__0:
        enterOuterAlt(_localctx, 1);
        {
        setState(100); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(99);
          match(T__0);
          }
          }
          setState(102); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==T__0 );
        }
        break;
      case T__1:
        enterOuterAlt(_localctx, 2);
        {
        setState(105); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(104);
          match(T__1);
          }
          }
          setState(107); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==T__1 );
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

  public static class NoteLengthContext extends ParserRuleContext {
    public List<TerminalNode> DIGIT() { return getTokens(AbcParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(AbcParser.DIGIT, i);
    }
    public NoteLengthContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteLength; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterNoteLength(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitNoteLength(this);
    }
  }

  public final NoteLengthContext noteLength() throws RecognitionException {
    NoteLengthContext _localctx = new NoteLengthContext(_ctx, getState());
    enterRule(_localctx, 18, RULE_noteLength);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(112); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(111);
        match(DIGIT);
        }
        }
        setState(114); 
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
    public List<TerminalNode> DIGIT() { return getTokens(AbcParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(AbcParser.DIGIT, i);
    }
    public NoteLengthStrictContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_noteLengthStrict; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterNoteLengthStrict(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitNoteLengthStrict(this);
    }
  }

  public final NoteLengthStrictContext noteLengthStrict() throws RecognitionException {
    NoteLengthStrictContext _localctx = new NoteLengthStrictContext(_ctx, getState());
    enterRule(_localctx, 20, RULE_noteLengthStrict);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(121);
      _la = _input.LA(1);
      if (_la==DIGIT) {
        {
        setState(117); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(116);
          match(DIGIT);
          }
          }
          setState(119); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
        }
      }

      setState(123);
      match(T__2);
      setState(129);
      _la = _input.LA(1);
      if (_la==DIGIT) {
        {
        setState(125); 
        _errHandler.sync(this);
        _la = _input.LA(1);
        do {
          {
          {
          setState(124);
          match(DIGIT);
          }
          }
          setState(127); 
          _errHandler.sync(this);
          _la = _input.LA(1);
        } while ( _la==DIGIT );
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

  public static class AccidentalContext extends ParserRuleContext {
    public AccidentalContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_accidental; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterAccidental(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitAccidental(this);
    }
  }

  public final AccidentalContext accidental() throws RecognitionException {
    AccidentalContext _localctx = new AccidentalContext(_ctx, getState());
    enterRule(_localctx, 22, RULE_accidental);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(131);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
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

  public static class BasenoteContext extends ParserRuleContext {
    public BasenoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_basenote; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterBasenote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitBasenote(this);
    }
  }

  public final BasenoteContext basenote() throws RecognitionException {
    BasenoteContext _localctx = new BasenoteContext(_ctx, getState());
    enterRule(_localctx, 24, RULE_basenote);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(133);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21))) != 0)) ) {
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

  public static class RestContext extends ParserRuleContext {
    public RestContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_rest; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterRest(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitRest(this);
    }
  }

  public final RestContext rest() throws RecognitionException {
    RestContext _localctx = new RestContext(_ctx, getState());
    enterRule(_localctx, 26, RULE_rest);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(135);
      match(T__22);
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

  public static class TupletElementContext extends ParserRuleContext {
    public DupletContext duplet() {
      return getRuleContext(DupletContext.class,0);
    }
    public TripletContext triplet() {
      return getRuleContext(TripletContext.class,0);
    }
    public QuadrupletContext quadruplet() {
      return getRuleContext(QuadrupletContext.class,0);
    }
    public TupletElementContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_tupletElement; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterTupletElement(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitTupletElement(this);
    }
  }

  public final TupletElementContext tupletElement() throws RecognitionException {
    TupletElementContext _localctx = new TupletElementContext(_ctx, getState());
    enterRule(_localctx, 28, RULE_tupletElement);
    try {
      setState(140);
      switch (_input.LA(1)) {
      case T__23:
        enterOuterAlt(_localctx, 1);
        {
        setState(137);
        duplet();
        }
        break;
      case T__24:
        enterOuterAlt(_localctx, 2);
        {
        setState(138);
        triplet();
        }
        break;
      case T__25:
        enterOuterAlt(_localctx, 3);
        {
        setState(139);
        quadruplet();
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

  public static class DupletContext extends ParserRuleContext {
    public List<NoteElementContext> noteElement() {
      return getRuleContexts(NoteElementContext.class);
    }
    public NoteElementContext noteElement(int i) {
      return getRuleContext(NoteElementContext.class,i);
    }
    public DupletContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_duplet; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterDuplet(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitDuplet(this);
    }
  }

  public final DupletContext duplet() throws RecognitionException {
    DupletContext _localctx = new DupletContext(_ctx, getState());
    enterRule(_localctx, 30, RULE_duplet);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(142);
      match(T__23);
      setState(143);
      noteElement();
      setState(144);
      noteElement();
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

  public static class TripletContext extends ParserRuleContext {
    public List<NoteElementContext> noteElement() {
      return getRuleContexts(NoteElementContext.class);
    }
    public NoteElementContext noteElement(int i) {
      return getRuleContext(NoteElementContext.class,i);
    }
    public TripletContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_triplet; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterTriplet(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitTriplet(this);
    }
  }

  public final TripletContext triplet() throws RecognitionException {
    TripletContext _localctx = new TripletContext(_ctx, getState());
    enterRule(_localctx, 32, RULE_triplet);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(146);
      match(T__24);
      setState(147);
      noteElement();
      setState(148);
      noteElement();
      setState(149);
      noteElement();
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

  public static class QuadrupletContext extends ParserRuleContext {
    public List<NoteElementContext> noteElement() {
      return getRuleContexts(NoteElementContext.class);
    }
    public NoteElementContext noteElement(int i) {
      return getRuleContext(NoteElementContext.class,i);
    }
    public QuadrupletContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_quadruplet; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterQuadruplet(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitQuadruplet(this);
    }
  }

  public final QuadrupletContext quadruplet() throws RecognitionException {
    QuadrupletContext _localctx = new QuadrupletContext(_ctx, getState());
    enterRule(_localctx, 34, RULE_quadruplet);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(151);
      match(T__25);
      setState(152);
      noteElement();
      setState(153);
      noteElement();
      setState(154);
      noteElement();
      setState(155);
      noteElement();
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

  public static class MultiNoteContext extends ParserRuleContext {
    public List<NoteContext> note() {
      return getRuleContexts(NoteContext.class);
    }
    public NoteContext note(int i) {
      return getRuleContext(NoteContext.class,i);
    }
    public MultiNoteContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_multiNote; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterMultiNote(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitMultiNote(this);
    }
  }

  public final MultiNoteContext multiNote() throws RecognitionException {
    MultiNoteContext _localctx = new MultiNoteContext(_ctx, getState());
    enterRule(_localctx, 36, RULE_multiNote);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(157);
      match(T__26);
      setState(159); 
      _errHandler.sync(this);
      _la = _input.LA(1);
      do {
        {
        {
        setState(158);
        note();
        }
        }
        setState(161); 
        _errHandler.sync(this);
        _la = _input.LA(1);
      } while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22))) != 0) );
      setState(163);
      match(T__27);
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

  public static class BarlineContext extends ParserRuleContext {
    public BarlineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_barline; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterBarline(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitBarline(this);
    }
  }

  public final BarlineContext barline() throws RecognitionException {
    BarlineContext _localctx = new BarlineContext(_ctx, getState());
    enterRule(_localctx, 38, RULE_barline);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(165);
      _la = _input.LA(1);
      if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33))) != 0)) ) {
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

  public static class NthRepeatContext extends ParserRuleContext {
    public NthRepeatContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_nthRepeat; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterNthRepeat(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitNthRepeat(this);
    }
  }

  public final NthRepeatContext nthRepeat() throws RecognitionException {
    NthRepeatContext _localctx = new NthRepeatContext(_ctx, getState());
    enterRule(_localctx, 40, RULE_nthRepeat);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(167);
      _la = _input.LA(1);
      if ( !(_la==T__34 || _la==T__35) ) {
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

  public static class MidTuneFieldContext extends ParserRuleContext {
    public FieldVoiceContext fieldVoice() {
      return getRuleContext(FieldVoiceContext.class,0);
    }
    public MidTuneFieldContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_midTuneField; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterMidTuneField(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitMidTuneField(this);
    }
  }

  public final MidTuneFieldContext midTuneField() throws RecognitionException {
    MidTuneFieldContext _localctx = new MidTuneFieldContext(_ctx, getState());
    enterRule(_localctx, 42, RULE_midTuneField);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(169);
      fieldVoice();
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
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterFieldVoice(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitFieldVoice(this);
    }
  }

  public final FieldVoiceContext fieldVoice() throws RecognitionException {
    FieldVoiceContext _localctx = new FieldVoiceContext(_ctx, getState());
    enterRule(_localctx, 44, RULE_fieldVoice);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(171);
      match(T__36);
      setState(172);
      text();
      setState(173);
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

  public static class CommentContext extends ParserRuleContext {
    public TextContext text() {
      return getRuleContext(TextContext.class,0);
    }
    public TerminalNode NEWLINE() { return getToken(AbcParser.NEWLINE, 0); }
    public CommentContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_comment; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterComment(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitComment(this);
    }
  }

  public final CommentContext comment() throws RecognitionException {
    CommentContext _localctx = new CommentContext(_ctx, getState());
    enterRule(_localctx, 46, RULE_comment);
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(175);
      match(T__37);
      setState(176);
      text();
      setState(177);
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
    public TerminalNode NEWLINE() { return getToken(AbcParser.NEWLINE, 0); }
    public EndOfLineContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_endOfLine; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterEndOfLine(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitEndOfLine(this);
    }
  }

  public final EndOfLineContext endOfLine() throws RecognitionException {
    EndOfLineContext _localctx = new EndOfLineContext(_ctx, getState());
    enterRule(_localctx, 48, RULE_endOfLine);
    try {
      setState(181);
      switch (_input.LA(1)) {
      case T__37:
        enterOuterAlt(_localctx, 1);
        {
        setState(179);
        comment();
        }
        break;
      case NEWLINE:
        enterOuterAlt(_localctx, 2);
        {
        setState(180);
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
    public List<TerminalNode> ALPHA() { return getTokens(AbcParser.ALPHA); }
    public TerminalNode ALPHA(int i) {
      return getToken(AbcParser.ALPHA, i);
    }
    public List<TerminalNode> WHITESPACE() { return getTokens(AbcParser.WHITESPACE); }
    public TerminalNode WHITESPACE(int i) {
      return getToken(AbcParser.WHITESPACE, i);
    }
    public List<TerminalNode> PUNCTUATIONS() { return getTokens(AbcParser.PUNCTUATIONS); }
    public TerminalNode PUNCTUATIONS(int i) {
      return getToken(AbcParser.PUNCTUATIONS, i);
    }
    public List<TerminalNode> DIGIT() { return getTokens(AbcParser.DIGIT); }
    public TerminalNode DIGIT(int i) {
      return getToken(AbcParser.DIGIT, i);
    }
    public TextContext(ParserRuleContext parent, int invokingState) {
      super(parent, invokingState);
    }
    @Override public int getRuleIndex() { return RULE_text; }
    @Override
    public void enterRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).enterText(this);
    }
    @Override
    public void exitRule(ParseTreeListener listener) {
      if ( listener instanceof AbcListener ) ((AbcListener)listener).exitText(this);
    }
  }

  public final TextContext text() throws RecognitionException {
    TextContext _localctx = new TextContext(_ctx, getState());
    enterRule(_localctx, 50, RULE_text);
    int _la;
    try {
      enterOuterAlt(_localctx, 1);
      {
      setState(190);
      _errHandler.sync(this);
      _la = _input.LA(1);
      while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ALPHA) | (1L << PUNCTUATIONS) | (1L << DIGIT) | (1L << WHITESPACE))) != 0)) {
        {
        setState(188);
        switch (_input.LA(1)) {
        case T__8:
        case T__9:
        case T__10:
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
          {
          setState(183);
          basenote();
          }
          break;
        case ALPHA:
          {
          setState(184);
          match(ALPHA);
          }
          break;
        case WHITESPACE:
          {
          setState(185);
          match(WHITESPACE);
          }
          break;
        case PUNCTUATIONS:
          {
          setState(186);
          match(PUNCTUATIONS);
          }
          break;
        case DIGIT:
          {
          setState(187);
          match(DIGIT);
          }
          break;
        default:
          throw new NoViableAltException(this);
        }
        }
        setState(192);
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
    "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3-\u00c4\4\2\t\2"+
      "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
      "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
      "\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
      "\30\4\31\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\3\6\3;\n\3\r\3\16"+
      "\3<\3\4\6\4@\n\4\r\4\16\4A\3\4\3\4\3\4\3\4\3\4\5\4I\n\4\3\5\3\5\3"+
      "\5\3\5\3\5\5\5P\n\5\3\6\3\6\5\6T\n\6\3\7\3\7\3\7\5\7Y\n\7\3\b\3\b"+
      "\5\b]\n\b\3\t\5\t`\n\t\3\t\3\t\5\td\n\t\3\n\6\ng\n\n\r\n\16\nh\3\n"+
      "\6\nl\n\n\r\n\16\nm\5\np\n\n\3\13\6\13s\n\13\r\13\16\13t\3\f\6\fx"+
      "\n\f\r\f\16\fy\5\f|\n\f\3\f\3\f\6\f\u0080\n\f\r\f\16\f\u0081\5\f\u0084"+
      "\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\5\20\u008f\n\20\3"+
      "\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3"+
      "\23\3\23\3\24\3\24\6\24\u00a2\n\24\r\24\16\24\u00a3\3\24\3\24\3\25"+
      "\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
      "\3\32\3\32\5\32\u00b8\n\32\3\33\3\33\3\33\3\33\3\33\7\33\u00bf\n\33"+
      "\f\33\16\33\u00c2\13\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30"+
      "\32\34\36 \"$&(*,.\60\62\64\2\6\3\2\6\n\3\2\13\30\3\2\37$\3\2%&\u00c9"+
      "\2\66\3\2\2\2\4:\3\2\2\2\6H\3\2\2\2\bO\3\2\2\2\nS\3\2\2\2\fU\3\2\2"+
      "\2\16\\\3\2\2\2\20_\3\2\2\2\22o\3\2\2\2\24r\3\2\2\2\26{\3\2\2\2\30"+
      "\u0085\3\2\2\2\32\u0087\3\2\2\2\34\u0089\3\2\2\2\36\u008e\3\2\2\2"+
      " \u0090\3\2\2\2\"\u0094\3\2\2\2$\u0099\3\2\2\2&\u009f\3\2\2\2(\u00a7"+
      "\3\2\2\2*\u00a9\3\2\2\2,\u00ab\3\2\2\2.\u00ad\3\2\2\2\60\u00b1\3\2"+
      "\2\2\62\u00b7\3\2\2\2\64\u00c0\3\2\2\2\66\67\5\4\3\2\678\7\2\2\38"+
      "\3\3\2\2\29;\5\6\4\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\5\3"+
      "\2\2\2>@\5\b\5\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2BC\3\2\2\2"+
      "CD\7+\2\2DI\3\2\2\2EI\5,\27\2FI\5\60\31\2GI\7+\2\2H?\3\2\2\2HE\3\2"+
      "\2\2HF\3\2\2\2HG\3\2\2\2I\7\3\2\2\2JP\5\n\6\2KP\5\36\20\2LP\5(\25"+
      "\2MP\5*\26\2NP\7-\2\2OJ\3\2\2\2OK\3\2\2\2OL\3\2\2\2OM\3\2\2\2ON\3"+
      "\2\2\2P\t\3\2\2\2QT\5\f\7\2RT\5&\24\2SQ\3\2\2\2SR\3\2\2\2T\13\3\2"+
      "\2\2UX\5\16\b\2VY\5\24\13\2WY\5\26\f\2XV\3\2\2\2XW\3\2\2\2XY\3\2\2"+
      "\2Y\r\3\2\2\2Z]\5\20\t\2[]\5\34\17\2\\Z\3\2\2\2\\[\3\2\2\2]\17\3\2"+
      "\2\2^`\5\30\r\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ac\5\32\16\2bd\5\22\n"+
      "\2cb\3\2\2\2cd\3\2\2\2d\21\3\2\2\2eg\7\3\2\2fe\3\2\2\2gh\3\2\2\2h"+
      "f\3\2\2\2hi\3\2\2\2ip\3\2\2\2jl\7\4\2\2kj\3\2\2\2lm\3\2\2\2mk\3\2"+
      "\2\2mn\3\2\2\2np\3\2\2\2of\3\2\2\2ok\3\2\2\2p\23\3\2\2\2qs\7,\2\2"+
      "rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\25\3\2\2\2vx\7,\2\2wv\3"+
      "\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{w\3\2\2\2{|\3\2\2\2"+
      "|}\3\2\2\2}\u0083\7\5\2\2~\u0080\7,\2\2\177~\3\2\2\2\u0080\u0081\3"+
      "\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083"+
      "\177\3\2\2\2\u0083\u0084\3\2\2\2\u0084\27\3\2\2\2\u0085\u0086\t\2"+
      "\2\2\u0086\31\3\2\2\2\u0087\u0088\t\3\2\2\u0088\33\3\2\2\2\u0089\u008a"+
      "\7\31\2\2\u008a\35\3\2\2\2\u008b\u008f\5 \21\2\u008c\u008f\5\"\22"+
      "\2\u008d\u008f\5$\23\2\u008e\u008b\3\2\2\2\u008e\u008c\3\2\2\2\u008e"+
      "\u008d\3\2\2\2\u008f\37\3\2\2\2\u0090\u0091\7\32\2\2\u0091\u0092\5"+
      "\n\6\2\u0092\u0093\5\n\6\2\u0093!\3\2\2\2\u0094\u0095\7\33\2\2\u0095"+
      "\u0096\5\n\6\2\u0096\u0097\5\n\6\2\u0097\u0098\5\n\6\2\u0098#\3\2"+
      "\2\2\u0099\u009a\7\34\2\2\u009a\u009b\5\n\6\2\u009b\u009c\5\n\6\2"+
      "\u009c\u009d\5\n\6\2\u009d\u009e\5\n\6\2\u009e%\3\2\2\2\u009f\u00a1"+
      "\7\35\2\2\u00a0\u00a2\5\f\7\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
      "\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
      "\u00a6\7\36\2\2\u00a6\'\3\2\2\2\u00a7\u00a8\t\4\2\2\u00a8)\3\2\2\2"+
      "\u00a9\u00aa\t\5\2\2\u00aa+\3\2\2\2\u00ab\u00ac\5.\30\2\u00ac-\3\2"+
      "\2\2\u00ad\u00ae\7\'\2\2\u00ae\u00af\5\64\33\2\u00af\u00b0\5\62\32"+
      "\2\u00b0/\3\2\2\2\u00b1\u00b2\7(\2\2\u00b2\u00b3\5\64\33\2\u00b3\u00b4"+
      "\7+\2\2\u00b4\61\3\2\2\2\u00b5\u00b8\5\60\31\2\u00b6\u00b8\7+\2\2"+
      "\u00b7\u00b5\3\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\63\3\2\2\2\u00b9\u00bf"+
      "\5\32\16\2\u00ba\u00bf\7)\2\2\u00bb\u00bf\7-\2\2\u00bc\u00bf\7*\2"+
      "\2\u00bd\u00bf\7,\2\2\u00be\u00b9\3\2\2\2\u00be\u00ba\3\2\2\2\u00be"+
      "\u00bb\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c2"+
      "\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\65\3\2\2\2"+
      "\u00c2\u00c0\3\2\2\2\30<AHOSX\\_chmoty{\u0081\u0083\u008e\u00a3\u00b7"+
      "\u00be\u00c0";
  public static final ATN _ATN =
    new ATNDeserializer().deserialize(_serializedATN.toCharArray());
  static {
    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
      _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
    }
  }
}