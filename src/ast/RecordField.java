package ast;

import tipo.Type;
import visitor.Visitor;

public class RecordField implements ASTNode {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private String name;
	private Type type;
	private int offset;

	public RecordField(int i, int j, String name, Type type, int offset) {
		super();
		this.row = i;
		this.column = j;
		this.name = name;
		this.type = type;
		this.offset = offset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Field[name:" + this.name + ",type:" + this.getType() + " offset:" + this.offset;
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
	public boolean equals(Object r) {
		return this.getName().equals(((RecordField) r).getName());
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
