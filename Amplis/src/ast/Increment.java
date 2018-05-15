package ast;

import visitor.Visitor;

public class Increment implements Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private Variable variable;

	public Increment(int row, int column, Variable variable) {
		super();
		this.row = row;
		this.column = column;
		this.variable = variable;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
