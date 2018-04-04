package tipo;

import visitor.Visitor;

public class IntType extends AbstractType {

	private static IntType instance = new IntType();

	private IntType() {
	}

	public static IntType getInstance() {
		if (instance == null)
			instance = new IntType();
		return instance;
	}

	@Override
	public String toString() {
		return "int";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public boolean isLogical() {
		return true;
	}

	@Override
	public Type arithmetic(Type type) {

		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof IntType) {
			return this;
		}

		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

}
