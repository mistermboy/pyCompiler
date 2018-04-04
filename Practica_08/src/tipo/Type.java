package tipo;

import ast.ASTNode;

public interface Type extends ASTNode {

	boolean isLogical();
	
	Type arithmetic(Type type);
	
	Type arithmetic();

}
