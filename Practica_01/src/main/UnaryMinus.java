package main;

public class UnaryMinus implements Expression {

	private Expression operand;

	public UnaryMinus(int i, int j, Variable variable) {
		// TODO Auto-generated constructor stub
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

}
