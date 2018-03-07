package tipo;

import ast.ASTNode;

public class IntType implements Type {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private static IntType instance = new IntType();

	private IntType() {
	}

	public static IntType getInstance() {
		if (instance == null)
			instance = new IntType();
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
		return "int";
	}

}
