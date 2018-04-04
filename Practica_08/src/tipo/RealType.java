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
		return "double";
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

		if (type instanceof RealType) {
			return this;
		}

		return null;
	}

	@Override
	public Type arithmetic() {
		return this;
	}

}