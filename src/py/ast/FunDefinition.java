package py.ast;

import java.util.List;

import py.tipo.FunctionType;
import py.tipo.Type;
import py.visitor.Visitor;

public class FunDefinition implements Definition {

	private List<Statement> statements;
	private String name;
	private Type type;
	private int scope;

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

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public int getScope() {
		return this.scope;
	}

	@Override
	public void setScope(int scope) {
		this.scope = scope;
	}

	public int localBytes() {
		int total = 0;
		for (Statement s : statements) {
			if (s instanceof VarDefinition) {
				total += ((VarDefinition) s).getType().numberOfBytes();
			}
		}
		return total;
	}

	public int paramBytes() {
		int total = 0;
		for (VarDefinition v : ((FunctionType) getType()).getParameters()) {
			total += v.getType().numberOfBytes();

		}
		return total;
	}

}
