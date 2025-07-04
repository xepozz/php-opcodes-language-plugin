/* PHPOp JFlex Lexer for Pattern-Based Parsing */

package com.github.xepozz.php_opcodes_language.language.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.github.xepozz.php_opcodes_language.language.psi.PHPOpTypes;
import java.util.Stack;

%%

%class PHPOpLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%state YYINITIAL
%state IN_BLOCK

// Common macros
WHITESPACE = [ \t\f]
NEWLINE = \r\n|\r|\n
COMMENT = "#"[^\n]*

// Identifier patterns
IDENTIFIER = [a-zA-Z][a-zA-Z0-9_\-]*
NUMBER = [0-9]+
TEXT = [^\s{\}(\)\[\]<\>\|\#\'\`\-\+\?\@][^\s{\}(\)\[\]<\>]*
SYMBOL = [\-\+\~\?\<\>\@]

QUOTTED_STRING = "\""(\\\"|[^\"])*"\""
BACKTICK_STRING = "`"(\\\`|[^\`])*"`"

// Special symbols
LBRACE = "{"
RBRACE = "}"
LPAREN = "("
RPAREN = ")"
LBRACKET = "["
RBRACKET = "]"

%{
private Stack<Integer> stack = new Stack<>();

public void yypushState(int newState) {
  stack.push(yystate());
  yybegin(newState);
}

public void yypopState() {
  yybegin(stack.pop());
}
%}

%%

<YYINITIAL, IN_BLOCK> {
{LBRACE}                                     { yypushState(IN_BLOCK); return PHPOpTypes.LBRACE; }
{RBRACE}                                     { yypopState(); return PHPOpTypes.RBRACE; }

// Special symbols
{LPAREN}                                     { return PHPOpTypes.LPAREN; }
{RPAREN}                                     { return PHPOpTypes.RPAREN; }
{LBRACKET}                                   { return PHPOpTypes.LBRACKET; }
{RBRACKET}                                   { return PHPOpTypes.RBRACKET; }

// Common elements
{IDENTIFIER}                                 { return PHPOpTypes.IDENTIFIER; }
{NUMBER}                                     { return PHPOpTypes.NUMBER; }
{SYMBOL}                                     { return PHPOpTypes.SYMBOL; }
{TEXT}|{QUOTTED_STRING}|{BACKTICK_STRING}    { return PHPOpTypes.TEXT; }

// Whitespace and comments
{WHITESPACE}                                 { return TokenType.WHITE_SPACE; }
{NEWLINE}                                    { return PHPOpTypes.EOL; }
{COMMENT}                                    { return PHPOpTypes.COMMENT; }
}

// Catch any other character
[^]                                            { return TokenType.BAD_CHARACTER; }
