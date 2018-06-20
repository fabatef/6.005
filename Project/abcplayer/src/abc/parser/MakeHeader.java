package abc.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import abc.parser.XyzParser.* ;

/** 
 * Make a Xyz header from a parse tree. 
 */

public class MakeHeader implements XyzListener{
    
    private Map<String, String> HeaderMap = new HashMap<String, String>();
    
    /** 
     * @return a map with all the fields in the header
     */
    public Map<String, String> getHeader(){
        return HeaderMap;      
    }
    
    @Override
    public void exitRoot(RootContext ctx) {
        // Do nothing        
    }

    @Override
    public void exitAbcHeader(AbcHeaderContext ctx) {
        // do nothing 
    }

    @Override
    public void exitFieldNumber(FieldNumberContext ctx) {
        // matched 'X:' DIGIT+ endOfLine;
        List<TerminalNode> number = ctx.DIGIT();
        String fieldNumber = "";
        for (int i=0; i < number.size(); i++){
            fieldNumber += number.get(i).getText();
        }
        HeaderMap.put("fieldNumber", fieldNumber);       
    }

    @Override
    public void exitFieldTitle(FieldTitleContext ctx) {
        // matched 'T:' text endOfLine;
        TextContext title = ctx.text();
        String fieldTitle = title.getText();  
        HeaderMap.put("fieldTitle", fieldTitle);
    }

    @Override
    public void exitOtherFields(OtherFieldsContext ctx) {
        // matched fieldComposer | fieldDefaultLength | fieldMeter | fieldTempo | fieldVoice | comment    
    }

    @Override
    public void exitFieldComposer(FieldComposerContext ctx) {
        // matched 'C:' text endOfLine;
        TextContext composer = ctx.text();
        String fieldComposer = composer.getText();
        HeaderMap.put("fieldComposer", fieldComposer);       
    }

    @Override
    public void exitFieldDefaultLength(FieldDefaultLengthContext ctx) {
        // matched 'L:' noteLengthStrict endOfLine;
        NoteLengthStrictContext defaultLength = ctx.noteLengthStrict();
        String[] fieldDefaultLengthArr = defaultLength.getText().split("/");;
        double meter = Double.parseDouble(fieldDefaultLengthArr[0])/Integer.parseInt(fieldDefaultLengthArr[1]);
        HeaderMap.put("fieldDefaultLength", String.valueOf(meter));
    }

    @Override
    public void exitFieldMeter(FieldMeterContext ctx) {
        // matched 'M:' meter endOfLine;
        MeterContext meter = ctx.meter();       
        String fieldMeter = meter.getText();
        HeaderMap.put("fieldMeter", fieldMeter);
    }

    @Override
    public void exitFieldTempo(FieldTempoContext ctx) {
        // do nothing
    }
    
    @Override
    public void exitFieldVoice(abc.parser.XyzParser.FieldVoiceContext ctx) {
        //matched 'V:' text endOfLine
        TextContext voice = ctx.text();
        String fieldVoice = voice.getText();
        HeaderMap.put("fieldVoice"+fieldVoice, fieldVoice);
    }

    @Override
    public void exitFieldKey(FieldKeyContext ctx) {
        // matched 'K:' key endOfLine;
        KeyContext key = ctx.key();
        String fieldKey = key.getText();  
        HeaderMap.put("fieldKey", fieldKey);
    }

    @Override
    public void exitKey(KeyContext ctx) {
        // Do nothing        
    }

    @Override
    public void exitKeynote(KeynoteContext ctx) {
        // Do nothing       
    }

    @Override
    public void exitKeyAccidental(KeyAccidentalContext ctx) {
        // Do nothing      
    }

    @Override
    public void exitModeMinor(ModeMinorContext ctx) {
        // Do nothing      
    }

    @Override
    public void exitBasenote(abc.parser.XyzParser.BasenoteContext ctx) {
        // Do nothing       
    }

    @Override
    public void exitMeter(MeterContext ctx) {
        // Do nothing       
    }

    @Override
    public void exitMeterFraction(MeterFractionContext ctx) {
        // Do nothing      
    }

    @Override
    public void exitTempo(TempoContext ctx) {
        List<TerminalNode> digitList = ctx.DIGIT();
        String digit = "";
        for (TerminalNode digits : digitList) {
            digit += digits.getText();
        }
        HeaderMap.put("fieldTempo",digit);
        String[] meterFraction = ctx.meterFraction().getText().split("/");  // DIGIT+ '/' DIGIT+;
        double meter = Double.parseDouble(meterFraction[0])/Integer.parseInt(meterFraction[1]);
        HeaderMap.put("fieldTempoMeterFraction", Double.toString(meter));       
    }

    @Override
    public void exitNoteLengthStrict(NoteLengthStrictContext ctx) {
        // Do nothing      
    }

    @Override
    public void exitComment(abc.parser.XyzParser.CommentContext ctx) {
        // Do nothing        
    }

    @Override
    public void exitEndOfLine(abc.parser.XyzParser.EndOfLineContext ctx) {
        // Do nothing       
    }

    @Override
    public void exitText(abc.parser.XyzParser.TextContext ctx) {
        // Do nothing     
    }   
    
    @Override public void enterRoot(abc.parser.XyzParser.RootContext ctx) {}    
    @Override public void enterAbcHeader(AbcHeaderContext ctx) {}  
    @Override public void enterFieldNumber(FieldNumberContext ctx) {}    
    @Override public void enterFieldTitle(FieldTitleContext ctx) {} 
    @Override public void enterOtherFields(OtherFieldsContext ctx) {}    
    @Override public void enterFieldComposer(FieldComposerContext ctx) {}
    @Override public void enterFieldDefaultLength(FieldDefaultLengthContext ctx) {}  
    @Override public void enterFieldMeter(FieldMeterContext ctx) {}   
    @Override public void enterFieldTempo(FieldTempoContext ctx) {}
    @Override public void enterFieldKey(FieldKeyContext ctx) {}    
    @Override public void enterFieldVoice(abc.parser.XyzParser.FieldVoiceContext ctx) {} 
    @Override public void enterKeynote(KeynoteContext ctx) {}   
    @Override public void enterKeyAccidental(KeyAccidentalContext ctx) {}   
    @Override public void enterKey(KeyContext ctx) {}    
    @Override public void enterModeMinor(ModeMinorContext ctx) {}   
    @Override public void enterBasenote(abc.parser.XyzParser.BasenoteContext ctx) {}   
    @Override public void enterMeter(MeterContext ctx) {}  
    @Override public void enterMeterFraction(MeterFractionContext ctx) {} 
    @Override public void enterTempo(TempoContext ctx) {} 
    @Override public void enterNoteLengthStrict(abc.parser.XyzParser.NoteLengthStrictContext ctx) {} 
    @Override public void enterComment(abc.parser.XyzParser.CommentContext ctx) {}
    @Override public void enterEndOfLine(abc.parser.XyzParser.EndOfLineContext ctx) {}
    @Override public void enterText(abc.parser.XyzParser.TextContext ctx) {}
    @Override public void enterEveryRule(ParserRuleContext arg0) {}
    @Override public void exitEveryRule(ParserRuleContext arg0) {}
    @Override public void visitErrorNode(ErrorNode arg0) {}
    @Override public void visitTerminal(TerminalNode arg0) {}   
}

