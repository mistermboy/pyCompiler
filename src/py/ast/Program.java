package py.ast;

import java.util.ArrayList;
import java.util.List;

import py.tipo.FunctionType;
import py.tipo.VoidType;
import py.visitor.Visitor;

public class Program implements ASTNode {

	private List<Definition> definitions;

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	public Program(int i, int j, List<Definition> definitions, List<Statement> statements) {
		this.row = i;
		this.column = j;
		this.definitions = definitions;
		FunDefinition main = new FunDefinition(i, j, "main",
				new FunctionType(i, j, VoidType.getInstance(), new ArrayList<VarDefinition>()), statements);
		this.definitions.add(main);
	}

	public int getLine() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public String toString() {
		String cad = "Program=";
		for (Definition d : this.definitions) {
			cad += d.toString();
		}
		return cad;
	}

	public List<Definition> getDefinitions() {
		return definitions;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
}
