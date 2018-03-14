package ast;

public class Indexing extends AbstractExpression implements Statement{

	private Expression left;
	private Expression right;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Indexing(int i, int j, Expression left, Expression right) {
		this.row = i;
		this.column = j;
		this.right = right;
		this.left = left;
	}

	@Override
	public int getLine() {
		return column;
	}

	@Override
	public int getColumn() {
		return row;
	}

	public Expression getArguments() {
		return left;
	}

	public void setArguments(Expression arguments) {
		this.left = arguments;
	}

	public Expression getVariable() {
		return right;
	}

	public void setVariable(Expression variable) {
		this.right = variable;
	}

	@Override
	public String toString() {
		return "" + this.left + "[ " + this.right + " ]";
	}

}
