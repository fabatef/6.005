/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

/**
 * This file is the grammar file used by ANTLR.
 *
 * In order to compile this file, navigate to this folder
 * (src/expressivo/parser) and run the following command:
 * 
 * java -jar ../../../lib/antlr.jar Expression.g4
 *
 * PS3 instructions: you are free to change this grammar.
 *
 */

grammar Expression;

/*
 * This puts the package declaration at the top of the output Java
 * files. Do not change these lines.
 */
@header {
package expressivo.parser;
// Do not edit this .java file! Edit the grammar in Expression.g4 and re-run Antlr.
}

/*
 * This adds code to the generated lexer and parser. Do not change these lines. 
 */
@members {
    // This method makes the lexer or parser stop running if it encounters
    // invalid input and throw a RuntimeException.
    public void reportErrorsAsExceptions() {
        removeErrorListeners();
        addErrorListener(new ExceptionThrowingErrorListener());
    }
    
    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }
}

/*
 * Nonterminal rules (a.k.a. parser rules) must be lowercase, e.g. "root" and "sum" below.
 *
 * Terminal rules (a.k.a. lexical rules) must be UPPERCASE, e.g. NUMBER below.
 * Terminals can be defined with quoted strings or regular expressions.
 *
 * You should make sure you have one rule that describes the entire input.
 * This is the "start rule". The start rule should end with the special token
 * EOF so that it describes the entire input. Below, "root" is the start rule.
 *
 * For more information, see reading 18 about parser generators, which explains
 * how to use Antlr and has links to reference information.
 */

root : sum EOF;
sum : product ('+' product)*;  
product: addend ('*' addend)*; 
addend : NUMBER | '(' sum ')' | ALPHA | '(' product ')';
NUMBER : INT|DOUBLE;
ALPHA: [a-zA-z]+;
INT: [0-9]+;
DOUBLE: INT '.' INT* | '.' INT+;


/* tell Antlr to ignore spaces around tokens. */
SPACES : [ ]+ -> skip;