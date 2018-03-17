package ast;

import tipo.Type;
import visitor.Visitor;

public class VarDefinition implements Definition, Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private int scope = 0;
	private int offset;
	private String name;
	private Type type;

	public VarDefinition(int row, int column, String name, Type type) {
		super();
		this.row = row;
		this.column = column;
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "" + this.name + ":" + this.type;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
