package ast;

public class Cast implements Expression {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private Expression exp;
	private Type castType;

	public Cast(int row, int column, Expression exp, Type castType) {
		super();
		this.row = row;
		this.column = column;
		this.exp = exp;
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
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
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
		return "Cast [row=" + row + ", column=" + column + ", exp=" + exp + ", castType=" + castType + "]";
	}

}
