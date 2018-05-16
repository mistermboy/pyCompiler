package ast;

import visitor.Visitor;

public class UnaryMinus extends AbstractExpression {

	private Expression expresion;

	public UnaryMinus(int i, int j, Expression expresion) {
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
		return "-" + expresion.toString();
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
