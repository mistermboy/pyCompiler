package ast;

import visitor.Visitor;

public class Assignment implements Statement {

	private Expression left;
	private Expression right;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Assignment(int i, int j, Expression left, Expression right) {
		this.row = i;
		this.column = j;
		this.left = left;
		this.right = right;
	}

	@Override
	public int getLine() {
		return this.row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	/**
	 * @return the left
	 */
	public Expression getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(Expression left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Expression getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Expression right) {
		this.right = right;
	}

	@Override
	public String toString() {
		String cad = "";
		cad += this.left.toString() + "";
		cad += "=";
		cad += this.right.toString() + "";
		return cad;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
