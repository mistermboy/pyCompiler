package ast;

public class Variable implements Expression {

	private String nameString;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Variable(int i, int j, String nameString) {
		this.row = i;
		this.column = j;
		this.nameString = nameString;
	}

	/**
	 * @return the nameString
	 */
	public String getNameString() {
		return nameString;
	}

	/**
	 * @param nameString
	 *            the nameString to set
	 */
	public void setNameString(String nameString) {
		this.nameString = nameString;
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
		return "" + nameString;
	}

}
