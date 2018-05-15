package ast;

import visitor.Visitor;

public class AlterVal implements Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private Expression exp;
	private String operator;

	public AlterVal(int row, int column, Expression expr, String operator) {
		super();
		this.row = row;
		this.column = column;
		this.exp = expr;
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Expression getExpr() {
		return exp;
	}

	public void setExpr(Expression expr) {
		this.exp = expr;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public String toString() {
		return exp.toString() + operator;
	}

}
