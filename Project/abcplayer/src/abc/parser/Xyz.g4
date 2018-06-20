/*
 * Compile all your grammars using
 *       java -jar ../../../lib/antlr.jar *.g4
 * then Refresh in Eclipse.
 */
grammar Xyz;
import Configuration;

/* 	Header
 *	ignore WHITESPACE between terminals in the header
 */
root : abcHeader EOF;
abcHeader : fieldNumber comment* fieldTitle otherFields* fieldKey;

fieldNumber : 'X:' DIGIT+ endOfLine;
fieldTitle : 'T:' text endOfLine;
otherFields : fieldComposer | fieldDefaultLength | fieldMeter | fieldTempo | fieldVoice | comment;
fieldComposer : 'C:' text endOfLine;
fieldDefaultLength : 'L:' noteLengthStrict endOfLine;
fieldMeter : 'M:' meter endOfLine;
fieldTempo : 'Q:' tempo endOfLine;
fieldVoice : 'V:' text endOfLine;
fieldKey : 'K:' key endOfLine;

key : keynote modeMinor?;
keynote : basenote keyAccidental?;
keyAccidental : '#' | 'b';
modeMinor : 'm';

basenote : 'C' | 'D' | 'E' | 'F' | 'G' | 'A' | 'B'
        | 'c' | 'd' | 'e' | 'f' | 'g' | 'a' | 'b';

meter : 'C' | 'C|' | meterFraction;
meterFraction : DIGIT+ '/' DIGIT+;

tempo : meterFraction '=' DIGIT+;
noteLengthStrict : (DIGIT+)? '/' DIGIT+;

comment : '%' text NEWLINE;
endOfLine : comment | NEWLINE;

text : (basenote|ALPHA|WHITESPACE|PUNCTUATIONS|DIGIT|modeMinor)* ;

ALPHA: [A-Z] | [a-z];

PUNCTUATIONS: ('(' | ')' | '.' | ',' | '=' | '{' | '}' | '*' | ';' | '+' | '-'| '\'');

NEWLINE : ('\n' | '\r' '\n'?)+;

DIGIT: [0-9];

WHITESPACE : (' ' | '\t')+ -> skip;