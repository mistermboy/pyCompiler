package ast;

import java.util.List;

public class Program implements ASTNode {

	private List<Statement> statements;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Program(int i, int j, List<Statement> statements) {
		this.row = i;
		this.column = j;
		this.statements = statements;
	}

	public int getLine() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public String toString() {
		return "Program=";
	}

}
