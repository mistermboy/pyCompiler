package tipo;

import visitor.Visitor;

public class RealType extends AbstractType {

	private static RealType instance = new RealType();

	private RealType() {
	}

	public static RealType getInstance() {
		if (instance == null)
			instance = new RealType();
		return instance;
	}

	@Override
	public String toString() {
		return "RealType";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public Type arithmetic(Type type) {

		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof RealType || type instanceof IntType || type instanceof CharType) {
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
			return IntType.getInstance();
		}

		return null;
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
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof RealType) {
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
		return 4;
	}

	@Override
	public char suffix() {
		return 'F';
	}

	@Override
	public Type superType(Type type) {

		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof RealType || type instanceof IntType || type instanceof CharType) {
			return this;
		}

		return null;
	}

}