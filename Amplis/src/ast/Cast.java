package ast;

import tipo.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {

	private Expression expresion;
	private Type castType;

	public Cast(int row, int column, Expression expresion, Type castType) {
		super();
		this.row = row;
		this.column = column;
		this.expresion = expresion;
		this.castType = castType;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Expression getExp() {
		return expresion;
	}

	public void setExp(Expression exp) {
		this.expresion = exp;
	}

	public Type getCastType() {
		return castType;
	}

	public void setCastType(Type castType) {
		this.castType = castType;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "( " + this.castType + " ) " + this.expresion;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
