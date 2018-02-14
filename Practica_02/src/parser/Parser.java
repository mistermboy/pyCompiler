
package parser;

import scanner.Scanner;

public class Parser {

    // * Tokens
    public final static int INT_CONSTANT = 257;    
    
    private Scanner scanner;
    
    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }
}