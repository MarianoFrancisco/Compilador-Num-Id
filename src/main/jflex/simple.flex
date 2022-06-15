package com.mariano;
import java.io.*;

%%
%public
%class DemoLexer

digit =[0-9]
letter= [a-zA-Z]
whitespace=[ \t\r]
newline = [\n]r
%type Token

%eofval{
    return new Token(TokenConstant.EOF,null);
    /*Hacer algo al final del archivo*/
%eofval}
%%
{letter}({letter}|{digit})* {return new Token(TokenConstant.ID,yytext());}
{digit}+ {return new Token(TokenConstant.NUM,yytext());}
{whitespace}+ {/*ignorar*/}
{newline}+ {/*ignorar*/}
[^] { throw new java.io.IOException("La cadena es ilegal > "+yytext());}