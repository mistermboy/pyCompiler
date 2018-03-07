package ast;

import java.util.List;
import tipo.Type;

public class FunDefinition implements Definition {

	private List<Statement> statements;
	private String name;
	private Type type;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public FunDefinition(int i, int j, String name, Type type, List<Statement> statements) {
		super();
		this.row = i;
		this.column = j;
		this.statements = statements;
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		String cad = "def " + this.name + type.toString() + "{\n";
		for (Statement s : this.getStatements()) {
			cad += "\n \t" + s.toString();
		}
		cad += "\n}";
		return cad;
	}

}
