package py.tipo;

import java.util.List;

import py.ast.RecordField;
import py.visitor.Visitor;

public class RecordType extends AbstractType {

	private List<RecordField> fields;

	public RecordType(int row, int column, List<RecordField> fields) {
		super();
		this.row = row;
		this.column = column;
		this.fields = fields;
	}

	public List<RecordField> getFields() {
		return fields;
	}

	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}

	// @Override
	// public String toString() {
	// // String cad = "STRUCT {";
	// // for (RecordField r : this.getFields()) {
	// // cad += "\n \t" + r.toString();
	// // }
	// // cad += "\n }";
	// // return cad;
	// return "struct";
	// }

	@Override
	public String toString() {
		return "RecordType [fields=" + fields + "]";
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public Type dot(String s) {
		for (RecordField r : getFields()) {
			if (r.getName().equals(s)) {
				return r.getType();
			}
		}
		return null;
	}

	@Override
	public int numberOfBytes() {
		int bytes = 0;
		for (RecordField f : this.getFields()) {
			bytes += f.getType().numberOfBytes();
		}
		return bytes;
	}

	@Override
	public RecordField get(String nombre) {
		for (RecordField r : fields) {
			if (nombre.equals(r.getName())) {
				return r;
			}
		}
		return null;
	}

}
