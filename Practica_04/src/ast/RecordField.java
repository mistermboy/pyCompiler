package ast;

public class RecordField {

	private String name;
	private Type type;
	private int offset;

	public RecordField(String name, Type type, int offset) {
		super();
		this.name = name;
		this.type = type;
		this.offset = offset;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "RecordField [name=" + name + ", type=" + type + ", offset=" + offset + "]";
	}

}
