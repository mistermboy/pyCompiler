package tipo;

import visitor.Visitor;

public class CharType extends AbstractType {

	private static CharType instance = new CharType();

	private CharType() {
	}

	public static CharType getInstance() {
		if (instance == null)
			instance = new CharType();
		return instance;
	}

	@Override
	public String toString() {
		return "CharType";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public Type arithmetic(Type type) {

		if (type instanceof ErrorType || type instanceof RealType || type instanceof IntType) {
			return type;
		}

		if (type instanceof CharType) {
			return this;
		}

		return null;

	}

	@Override
	public Type comparison(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType || type instanceof IntType || type instanceof RealType) {
			return IntType.getInstance();
		}

		return null;
	}

	@Override
	public Type logical(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType || type instanceof IntType) {
			return IntType.getInstance();
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
		if (type instanceof ErrorType || type instanceof IntType || type instanceof RealType) {
			return type;
		}

		if (type instanceof CharType) {
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
		return 1;
	}

	@Override
	public char suffix() {
		return 'B';
	}

	@Override
	public Type superType(Type type) {

		if (type instanceof ErrorType || type instanceof RealType || type instanceof IntType) {
			return type;
		}

		if (type instanceof CharType) {
			return IntType.getInstance();
		}

		return null;
	}

}
