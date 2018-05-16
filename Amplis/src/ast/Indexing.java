package ast;

import visitor.Visitor;

public class Indexing extends AbstractExpression implements Statement {

	private Expression left;
	private Expression right;

	public Indexing(int i, int j, Expression left, Expression right) {
		this.row = i;
		this.column = j;
		this.right = right;
		this.left = left;
	}

	@Override
	public int getLine() {
		return column;
	}

	@Override
	public int getColumn() {
		return row;
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression arguments) {
		this.left = arguments;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression variable) {
		this.right = variable;
	}

	@Override
	public String toString() {
		return "" + this.left + "[ " + this.right + " ]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
