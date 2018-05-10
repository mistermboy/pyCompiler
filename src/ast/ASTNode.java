package ast;

import visitor.Visitor;

public interface ASTNode {

	int DEFAULT_ROW_COLUMN = 0;

	int getLine();

	int getColumn();

	Object accept(Visitor v, Object o);
}
