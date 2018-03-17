package ast;

public class Arithmetic implements Expression {

	private String operator;

	private Expression left;
	private Expression right;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Arithmetic(int i, int j, Expression left, String string, Expression right) {
		this.row = i;
		this.column = j;
		this.left = left;
		this.operator = string;
		this.right = right;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
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
		return this.row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public String toString() {
		String cad = "";
		cad += this.left.toString() + "";
		cad += this.operator + "";
		cad += this.right.toString() + "";
		return cad;
	}

}