package tipo;

import ast.ASTNode;
import visitor.Visitor;

public class VoidType implements Type {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private static VoidType instance = new VoidType();

	private VoidType() {
	}

	public static VoidType getInstance() {
		if (instance == null)
			instance = new VoidType();
		return instance;
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

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "void";
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
