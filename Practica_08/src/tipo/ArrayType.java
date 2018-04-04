package tipo;

import ast.ASTNode;
import visitor.Visitor;

public class ArrayType implements Type {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private int off;
	private Type of;

	public ArrayType(int row, int column, int off, Type of) {
		super();
		this.row = row;
		this.column = column;
		this.off = off;
		this.of = of;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getOff() {
		return off;
	}

	public void setOff(int off) {
		this.off = off;
	}

	public Type getOf() {
		return of;
	}

	public void setOf(Type of) {
		this.of = of;
	}

	@Override
	public String toString() {
		return "[ " + this.off + " ]" + this.of.toString();
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public boolean isLogical() {
		return false;
	}

}
