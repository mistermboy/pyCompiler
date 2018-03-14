package ast;

public class Read implements Statement {

	private Expression read;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Read(int i, int j, Variable variable) {
		this.row = i;
		this.column = j;
		this.read = variable;
	}

	/**
	 * @return the read
	 */
	public Expression getRead() {
		return read;
	}

	/**
	 * @param read
	 *            the read to set
	 */
	public void setRead(Expression read) {
		this.read = read;
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
		return read.toString();

	}

}
