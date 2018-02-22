package scanner;

import java.io.FileReader;
import java.io.IOException;

import scanner.Scanner;
import parser.Parser;

public class TestScanner {

	public static void main(String args[]) throws IOException {
	    String fileName = "input.txt";
		FileReader fr = null;
		try {
			fr=new FileReader(fileName);
		} catch(IOException io) {
			System.err.println("The file "+fileName+" could not be opened.");
			return;
		}
		
		Scanner scanner = new Scanner(fr);
		Parser parser = new Parser(scanner);
		int token, i = 0;
		while ((token=scanner.yylex())!=0) {
			expectedTokens[i].assertEquals(scanner.getLine(), scanner.getColumn(),scanner.yytext().toString(), token, scanner.getYylval());
			i++;
		}		    
	}

	private static LexicalInfo[] expectedTokens = new LexicalInfo[] {
			new LexicalInfo(6, 5, "0", Parser.INT_CONSTANT, 0),
			new LexicalInfo(6, 7, "123", Parser.INT_CONSTANT, 123),
			new LexicalInfo(7, 5, "0", Parser.INT_CONSTANT, 0),
			new LexicalInfo(7, 7, "12", Parser.INT_CONSTANT, 12),
			
			new LexicalInfo(11, 5, "12.3", Parser.REAL_CONSTANT, 12.3),
			new LexicalInfo(11, 11, "2.", Parser.REAL_CONSTANT, 2.0),
			new LexicalInfo(11, 15, ".34", Parser.REAL_CONSTANT, 0.34),
			new LexicalInfo(12, 5, "34.12E-3", Parser.REAL_CONSTANT, 0.03412),
			new LexicalInfo(12, 15, "3e3", Parser.REAL_CONSTANT, 3000.0),
			
			new LexicalInfo(16, 5, "var1", Parser.ID, "var1"),
			new LexicalInfo(16, 11, "_var_1", Parser.ID, "_var_1"),
			new LexicalInfo(16, 19, "VAR_1_AB_2", Parser.ID, "VAR_1_AB_2"),
						
			new LexicalInfo(20, 5, "'a'", Parser.CHAR_CONSTANT, 'a'),
			new LexicalInfo(20, 10, "'b'", Parser.CHAR_CONSTANT, 'b'),
			new LexicalInfo(20, 15, "'.'", Parser.CHAR_CONSTANT, '.'),
			new LexicalInfo(20, 20, "'-'", Parser.CHAR_CONSTANT, '-'),
			new LexicalInfo(20, 25, "'~'", Parser.CHAR_CONSTANT, '~'),
			new LexicalInfo(21, 5, "'\\n'", Parser.CHAR_CONSTANT, '\n'),
			new LexicalInfo(21, 11, "'\\t'", Parser.CHAR_CONSTANT, '\t'),
			new LexicalInfo(22, 5, "'\\126'", Parser.CHAR_CONSTANT, '~'), 
		};
}

class LexicalInfo {
	
	private int line, column, tokenKey;
	
	private String lexeme;

	public Object semanticValue;
	
	public LexicalInfo(int line, int column, String lexeme, int tokenKey, Object semanticValue) {
		this.line = line;
		this.column = column;
		this.lexeme = lexeme;
		this.tokenKey = tokenKey;
		this.semanticValue = semanticValue;
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(line ").append(this.line)
			.append(", column ").append(this.column)
			.append(", lexeme '").append(this.lexeme).append("'")
			.append(", token key ").append(this.tokenKey)
			.append(", semantic value ").append(this.semanticValue)
			.append(")");
		return sb.toString();
	}
	
	public void assertEquals(int line, int column, String lexeme, int tokenKey, Object semanticValue) {
		assert this.line == line : "Assert failed in token " + this + ". Expected line " + this.line + ", obtained " + line + ".";
		assert this.column == column : "Assert failed in token " + this + ". Expected column " + this.column + ", obtained " + column + ".";
		assert this.lexeme.equals(lexeme) : "Assert failed in token " + this + ". Expected lexeme " + this.lexeme + ", obtained " + lexeme + ".";
		assert this.tokenKey == tokenKey : "Assert failed in token " + this + ". Expected token name " + this.tokenKey + ", obtained " + tokenKey + ".";
		assert this.semanticValue.equals(semanticValue) : "Assert failed in token " + this + ". Expected semanticValue " + this.semanticValue + ", obtained " + semanticValue + ".";
	}
}
