package ast;

import visitor.Visitor;

public interface Expression extends ASTNode {

	boolean getLValue();

	void setLValue(boolean lValue);

}
