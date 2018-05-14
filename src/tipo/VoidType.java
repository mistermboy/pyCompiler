package tipo;

import visitor.Visitor;

public class VoidType extends AbstractType {

	private static VoidType instance = new VoidType();

	private VoidType() {
	}

	public static VoidType getInstance() {
		if (instance == null)
			instance = new VoidType();
		return instance;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "VoidType";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public boolean isBuildingType() {
		return true;
	}

}
