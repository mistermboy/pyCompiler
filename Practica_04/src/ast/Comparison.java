package ast;

public class Comparison implements Expression {

	private Expression left;
	private Expression right;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Comparison(int i, int j, Expression left, Expression right) {
		this.row = i;
		this.column = j;
		this.left = left;
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
		cad += "==";
		cad += this.right.toString() + "";
		return cad;
	}

}
