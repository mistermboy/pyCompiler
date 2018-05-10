package ast;

import visitor.Visitor;

public class Write implements Statement {

	private Expression expresion;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Write(int i, int j, Expression expresion) {
		this.row = i;
		this.column = j;
		this.expresion = expresion;
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
		return "print " + expresion.toString();
	}

	public Expression getExpresion() {
		return expresion;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
