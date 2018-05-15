package ast;

import visitor.Visitor;

public class AlterAssigVal implements Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private Expression left;
	private Expression rigth;
	private String operator;

	public AlterAssigVal(int row, int column, Expression left, Expression right, String operator) {
		super();
		this.row = row;
		this.column = column;
		this.left = left;
		this.rigth = right;
		this.operator = operator;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
		//return v.visit(this, o);
		return null;
	}

	@Override
	public String toString() {
		return left.toString() + operator + rigth.toString();
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRigth() {
		return rigth;
	}

	public void setRigth(Expression rigth) {
		this.rigth = rigth;
	}

}
