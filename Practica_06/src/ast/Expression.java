package ast;

public interface Expression extends ASTNode {

	boolean getLValue();

	void setLValue(boolean lValue);

}
