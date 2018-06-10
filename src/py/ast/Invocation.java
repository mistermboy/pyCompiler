package py.ast;

import java.util.List;

import py.visitor.Visitor;

public class Invocation extends AbstractExpression implements Statement{

	private List<Expression> arguments;

	private Variable funcionName;

	public Invocation(int row, int column, Variable funcionName, List<Expression> arguments) {
		super();
		this.row = row;
		this.column = column;
		this.arguments = arguments;
		this.funcionName = funcionName;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public List<Expression> getArguments() {
		return arguments;
	}

	public void setArguments(List<Expression> arguments) {
		this.arguments = arguments;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Variable getFuncion() {
		return funcionName;
	}

	public void setFuncion(Variable funcion) {
		this.funcionName = funcion;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		String cad = "" + this.funcionName + "( ";
		for (Expression e : this.getArguments()) {
			cad += " " + e.toString() + ",";
		}
		cad += " )";
		return cad;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
