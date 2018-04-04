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
		return "real";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}