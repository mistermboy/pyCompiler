package ast;

import java.util.List;

public class Indexing implements Expression {

	private List<Expression> arguments;
	private Variable variable;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Indexing(int i, int j, Variable variable, List<Expression> arguments) {
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

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public String toString() {
		return "Indexing [arguments=" + arguments + ", variable=" + variable + ", row=" + row + ", column=" + column
				+ "]";
	}

}
