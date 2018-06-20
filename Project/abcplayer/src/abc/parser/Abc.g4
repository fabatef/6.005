/*
 * Compile all your grammars using
 *       java -jar ../../../lib/antlr.jar *.g4
 * then Refresh in Eclipse.
 */
grammar Abc;
import Configuration;

// Music
// WHITESPACE is explicit in the body, don't automatically ignore it

root : abcMusic EOF;

abcMusic : abcLine+;
abcLine : element+ NEWLINE | midTuneField | comment| NEWLINE;
element : noteElement | tupletElement | barline | nthRepeat | WHITESPACE; 

noteElement : note | multiNote;

// note is either a pitch or a rest
note : noteOrRest (noteLength | noteLengthStrict)?;
noteOrRest : pitch | rest;
pitch : accidental? basenote octave?;
octave : '\''+ | ','+;
noteLength : DIGIT+;
noteLengthStrict : (DIGIT+)? '/'( DIGIT+)?;


// '^' is sharp, '_' is flat, and '=' is neutral
accidental : '^' | '^^' | '_' | '__' | '=';

basenote : 'C' | 'D' | 'E' | 'F' | 'G' | 'A' | 'B'
        | 'c' | 'd' | 'e' | 'f' | 'g' | 'a' | 'b';

rest : 'z';

// tuplets
tupletElement : duplet| triplet| quadruplet;
//tupletSpec : '(' DIGIT;

duplet: '(2' noteElement noteElement;
triplet: '(3' noteElement noteElement noteElement;
quadruplet: '(4' noteElement noteElement noteElement noteElement;

// chords
multiNote : '[' note+ ']';

barline : '|' | '||' | '[|' | '|]' | ':|' | '|:';
nthRepeat : '[1' | '[2';

// A voice field might reappear in the middle of a piece
// to indicate the change of a voice
midTuneField : fieldVoice;
fieldVoice : 'V:' text endOfLine;

comment : '%' text NEWLINE;
endOfLine : comment | NEWLINE;

text : (basenote|ALPHA|WHITESPACE|PUNCTUATIONS|DIGIT)* ;

ALPHA: [A-Za-z];

PUNCTUATIONS: ('(' | ')' | '.' | ',' | '=' | '{' | '}' | '*' | ';' | '+' | '-'|'\'');

NEWLINE : ('\n' | '\r' '\n'?)+;

DIGIT: [0-9];

WHITESPACE : (' ' | '\t')+ -> skip;
