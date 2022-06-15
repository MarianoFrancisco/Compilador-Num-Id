package com.mariano;

import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * Hello world!
 *
 */
@CommandLine.Command(name="lexer",mixinStandardHelpOptions = true,version="0.0.1",
description = "Simple lexer that recognizes ID, NUM and EOF")
public class App implements Callable<Integer>
{
    @CommandLine.Option(names = {"-f","--file"}, description = "File to read",required = false)
    private File file;
    @Override
    public Integer call() throws Exception {
        if (file!=null){
            BufferedReader bfr = Files.newBufferedReader(file.toPath());
            DemoLexer lexer = new DemoLexer(bfr);
            Token token = lexer.yylex();
            while (token.getTokenType()!=TokenConstant.EOF) {
                System.out.println(token);
                token = lexer.yylex();
            }
        }else{
            Scanner scanner = new Scanner(System.in);
            String input = "";
            while (!input.equals("exit")) {
                System.out.print("Por favor ingrese la cadena >");
                input = scanner.nextLine();
                if (input.equals("exit")){
                    break;
                }
                DemoLexer demoLexer = new DemoLexer(new StringReader(input));
                Token token = demoLexer.yylex();
                System.out.println(token);
            }
        }
        return 0;
    }
    public static void main( String[] args )
    {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
