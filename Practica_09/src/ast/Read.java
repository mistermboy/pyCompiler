package ast;

import visitor.Visitor;

public class Read implements Statement {

	private Expression expresion;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Read(int i, int j, Expression expresion) {
		this.row = i;
		this.column = j;
		this.expresion = expresion;
	}

	/**
	 * @return the read
	 */
	public Expression getExpression() {
		return expresion;
	}

	/**
	 * @param read
	 *            the read to set
	 */
	public void setExpression(Expression read) {
		this.expresion = read;
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
		return "input" + expresion.toString();

	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
