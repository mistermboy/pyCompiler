package main;

public class UnaryMinus implements Expression {

	private Expression operand;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public UnaryMinus(int i, int j, Variable variable) {
		this.row = i;
		this.column = j;
	}

	/**
	 * @return the operand
	 */
	public Expression getOperand() {
		return operand;
	}

	/**
	 * @param operand
	 *            the operand to set
	 */
	public void setOperand(Expression operand) {
		this.operand = operand;
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
		return operand.toString();
	}

}
