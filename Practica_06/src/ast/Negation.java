package ast;

public class Negation extends AbstractExpression {

	private Expression expresion;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Negation(int i, int j, Expression expresion) {
		this.row = i;
		this.column = j;
		this.expresion = expresion;
	}

	/**
	 * @return the operand
	 */
	public Expression getOperand() {
		return expresion;
	}

	/**
	 * @param operand
	 *            the operand to set
	 */
	public void setOperand(Expression operand) {
		this.expresion = operand;
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
		return "! " + expresion.toString();
	}

}
