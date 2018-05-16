package ast;

import visitor.Visitor;

public class Comparison extends AbstractExpression {

	private Expression left;
	private Expression right;

	private String comparator;

	public Comparison(int i, int j, Expression left, String comparator, Expression right) {
		this.row = i;
		this.column = j;
		this.left = left;
		this.comparator = comparator;
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

	@Override
	public int getColumn() {
		return row;
	}

	@Override
	public String toString() {
		String cad = "";
		cad += this.left.toString() + "";
		cad += this.comparator + "";
		cad += this.right.toString() + "";
		return cad;
	}

	public String getComparator() {
		return comparator;
	}

	public void setComparator(String comparator) {
		this.comparator = comparator;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
