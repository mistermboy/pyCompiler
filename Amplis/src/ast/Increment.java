package ast;

import visitor.Visitor;

public class Increment implements Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private Expression exp;

	public Increment(int row, int column, Expression expr) {
		super();
		this.row = row;
		this.column = column;
		this.exp = expr;
	}

	public Expression getExpr() {
		return exp;
	}

	public void setExpr(Expression expr) {
		this.exp = expr;
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
		return v.visit(this, o);
	}

	@Override
	public String toString() {
		return exp.toString() + "++";
	}

}
