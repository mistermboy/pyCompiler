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
	
}
