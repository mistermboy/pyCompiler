package ast;

public class Negation implements Expression {

	private Expression exp;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Negation(int i, int j, Expression exp) {
		this.row = i;
		this.column = j;
		this.exp = exp;
	}

	/**
	 * @return the operand
	 */
	public Expression getOperand() {
		return exp;
	}

	/**
	 * @param operand
	 *            the operand to set
	 */
	public void setOperand(Expression operand) {
		this.exp = operand;
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
		return exp.toString();
	}

}
