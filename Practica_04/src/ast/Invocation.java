package ast;

import java.util.List;

public class Invocation implements Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private List<Expression> arguments;

	private Variable funcion;

	public Invocation(int row, int column, List<Expression> arguments, Variable funcion) {
		super();
		this.row = row;
		this.column = column;
		this.arguments = arguments;
		this.funcion = funcion;
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
		return funcion;
	}

	public void setFuncion(Variable funcion) {
		this.funcion = funcion;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "Invocation [row=" + row + ", column=" + column + ", arguments=" + arguments + ", funcion=" + funcion
				+ "]";
	}

}
