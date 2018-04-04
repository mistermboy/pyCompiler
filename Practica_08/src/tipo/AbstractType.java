package tipo;

import ast.ASTNode;
import visitor.Visitor;

public class AbstractType implements Type {

	protected int row = ASTNode.DEFAULT_ROW_COLUMN;
	protected int column = ASTNode.DEFAULT_ROW_COLUMN;

	@Override
	public boolean isLogical() {
		return false;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return null;
	}

	@Override
	public Type arithmetic(Type type) {
		return null;
	}

	@Override
	public Type arithmetic() {
		return null;
	}

	@Override
	public Type comparison(Type type) {
		return null;
	}

	@Override
	public Type logical(Type type) {
		return null;
	}

}
