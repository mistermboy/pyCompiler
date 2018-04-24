package tipo;

import java.util.List;

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

	@Override
	public Type logical() {
		return null;
	}

	@Override
	public Type dot(String s) {
		return null;
	}

	@Override
	public Type squareBrackets(Type type) {
		return null;
	}

	@Override
	public Type canBeCast(Type type) {
		return null;
	}

	@Override
	public Type promotesTo(Type type) {
		return null;
	}

	@Override
	public Type parentesis(List<Type> types) {
		return null;
	}

	@Override
	public boolean isBuildingType() {
		return false;
	}

	@Override
	public int numberOfBytes() {
		return 0;
	}

	@Override
	public char suffix() {
		return 0;

	}

}
