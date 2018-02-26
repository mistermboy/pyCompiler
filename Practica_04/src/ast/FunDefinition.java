package ast;

import java.util.List;

public class FunDefinition implements Definition {

	private List<Statement> statements;
	private String name;
	private Type type;
	
	
	public FunDefinition(List<Statement> statements, String name) {
		super();
		this.statements = statements;
		this.name = name;
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
	public String toString() {
		return "FunDefinition [statements=" + statements + ", name=" + name + ", type=" + type + "]";
	}
	
	

}
