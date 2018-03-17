package ast;

import java.util.List;

import tipo.Type;
import visitor.Visitor;

public class RecordType implements Type {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private List<RecordField> fields;

	public RecordType(int row, int column, List<RecordField> fields) {
		super();
		this.row = row;
		this.column = column;
		this.fields = fields;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public List<RecordField> getFields() {
		return fields;
	}

	public void setFields(List<RecordField> fields) {
		this.fields = fields;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		String cad = "STRUCT {";
		for (RecordField r : this.getFields()) {
			cad += "\n \t" + r.toString();
		}
		cad += "\n }";
		return cad;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
