package ast;

public class Indexing implements Statement, Expression {

	private Expression arguments;
	private Expression variable;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Indexing(int i, int j, Expression variable, Expression arguments) {
		this.row = i;
		this.column = j;
		this.variable = variable;
		this.arguments = arguments;
	}

	@Override
	public int getLine() {
		return column;
	}

	@Override
	public int getColumn() {
		return row;
	}

	public Expression getArguments() {
		return arguments;
	}

	public void setArguments(Expression arguments) {
		this.arguments = arguments;
	}

	public Expression getVariable() {
		return variable;
	}

	public void setVariable(Expression variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "Indexing [arguments=" + arguments + ", variable=" + variable + ", row=" + row + ", column=" + column
				+ "]";
	}

}
