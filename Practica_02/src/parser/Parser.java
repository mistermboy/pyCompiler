
package parser;

import scanner.Scanner;

public class Parser {

	// * Tokens
	public final static int INT_CONSTANT = 257;

	public final static int INPUT = 258;
	public final static int PRINT = 259;
	public final static int DEF = 260;
	public final static int WHILE = 261;
	public final static int IF = 262;
	public final static int ELSE = 263;
	public final static int INT = 264;
	public final static int DOUBLE = 265;
	public final static int CHAR = 266;
	public final static int STRUCT = 267;
	public final static int RETURN = 268;
	public final static int VOID = 269;
	
	public final static int IDENT = 270;
	
	public final static int REAL_CONSTANT = 271;
	public final static int CHARACTER_CONSTANT = 271;

	private Scanner scanner;

	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
}