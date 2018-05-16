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
		return "IntType";
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

		if (type instanceof ErrorType || type instanceof RealType) {
			return type;
		}

		if (type instanceof IntType || type instanceof CharType) {
			return this;
		}

		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

	@Override
	public Type comparison(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType || type instanceof IntType ||  type instanceof RealType) {
			return this;
		}

		return null;
	}

	@Override
	public Type logical(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof IntType) {
			return this;
		}

		return null;
	}

	@Override
	public Type logical() {
		return this;
	}

	@Override
	public Type canBeCast(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType) {
			return this;
		}

		if (type instanceof IntType) {
			return this;
		}

		if (type instanceof RealType) {
			return this;
		}

		return null;
	}

	@Override
	public Type promotesTo(Type type) {
		if (type instanceof ErrorType || type instanceof RealType) {
			return type;
		}

		if (type instanceof IntType) {
			return this;
		}

		return null;
	}

	@Override
	public boolean isBuildingType() {
		return true;
	}

	@Override
	public int numberOfBytes() {
		return 2;
	}

	@Override
	public char suffix() {
		return 'I';
	}

	@Override
	public Type superType(Type type) {

		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof RealType) {
			return RealType.getInstance();
		}

		if (type instanceof IntType || type instanceof CharType) {
			return this;
		}

		return null;

	}

}
