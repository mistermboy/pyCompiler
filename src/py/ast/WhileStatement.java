package py.ast;

import java.util.List;

import py.visitor.Visitor;

public class WhileStatement implements Statement {

	private int row = ASTNode.DEFAULT_ROW_COLUMN;
	private int column = ASTNode.DEFAULT_ROW_COLUMN;

	private List<Statement> body;
	private Expression condition;

	public WhileStatement(int row, int column, List<Statement> body, Expression condition) {
		super();
		this.row = row;
		this.column = column;
		this.body = body;
		this.condition = condition;
	}

	@Override
	public int getLine() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public List<Statement> getBody() {
		return body;
	}

	public void setBody(List<Statement> body) {
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		String cad = "while " + this.condition + ":";
		for (Statement s : this.getBody()) {
			cad += "\n \t" + s.toString();
		}
		cad += "\n}";
		return cad;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

}
