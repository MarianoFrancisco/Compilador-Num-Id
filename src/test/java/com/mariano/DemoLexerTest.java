package com.mariano;

import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class DemoLexerTest {
    @Test
    public void matchId() throws IOException {
        String testString = "id1";
        Reader stringReader = new StringReader(testString);
        DemoLexer lexer = new DemoLexer(stringReader);
        Token token = lexer.yylex();
        assertEquals(TokenConstant.ID, token.getTokenType());
    }
    @Test
    public void matchNum() throws IOException {
        String testString = "31458";
        Reader stringReader = new StringReader(testString);
        DemoLexer lexer = new DemoLexer(stringReader);
        Token token = lexer.yylex();
        assertEquals(TokenConstant.NUM, token.getTokenType());
    }
    @Test
    public void noMatch() throws IOException {
        String testString = "@";
        Exception exception = assertThrows(IOException.class, () -> {
            Reader stringReader = new StringReader(testString);
            DemoLexer lexer = new DemoLexer(stringReader);
            Token token = lexer.yylex();
            assertEquals(TokenConstant.NUM, token.getTokenType());
        });
    }
}
