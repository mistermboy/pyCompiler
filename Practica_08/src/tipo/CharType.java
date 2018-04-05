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
		return "char";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public Type comparison(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType) {
			return this;
		}

		return null;
	}

	@Override
	public Type logical(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType) {
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

		return null;
	}
	
	@Override
	public Type promotesTo(Type type) {
		if (type instanceof ErrorType) {
			return type;
		}

		if (type instanceof CharType) {
			return this;
		}

		return null;
	}


}
