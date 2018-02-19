package ast;

public interface ASTNode {

	int DEFAULT_ROW_COLUMN = 0;

	public int getLine();

	public int getColumn();
}
