package ast;

import visitor.Visitor;

public class Logical extends AbstractExpression {

	private Expression left;
	private Expression right;

	private String logicalOperator;

	public Logical(int i, int j, Expression left, String logicalOperator, Expression right) {
		this.row = i;
		this.column = j;
		this.left = left;
		this.logicalOperator = logicalOperator;
		this.right = right;
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
	public int getLine() {
		return column;
	}

	public String getLogicalOperator() {
		return logicalOperator;
	}

	public void setLogicalOperator(String logicalOperator) {
		this.logicalOperator = logicalOperator;
	}

	@Override
	public int getColumn() {
		return row;
	}

	@Override
	public String toString() {
		String cad = "";
		cad += this.left.toString() + "";
		cad += this.logicalOperator;
		cad += this.right.toString() + "";
		return cad;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
