package tipo;

import ast.ASTNode;
import errorHandler.EH;
import visitor.Visitor;

public class ErrorType extends AbstractType {

	private String message;
	private ASTNode node;

	public ErrorType(ASTNode node, String message) {
		this.setNode(node);
		this.message = message;
		EH.getEH().addErrors(this);
	}

	public ErrorType(int i, int j, String message) {
		this.row = i;
		this.column = j;
		this.message = message;
		EH.getEH().addErrors(this);
	}

	@Override
	public int getLine() {
		if (node == null) {
			return row;
		}
		return node.getLine();
	}

	@Override
	public int getColumn() {
		if (node == null) {
			return column;
		}
		return node.getLine();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ASTNode getNode() {
		return node;
	}

	public void setNode(ASTNode node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "ErrorType at row=" + getLine() + " column=" + getColumn() + "\n" + message;
	}
	
	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}
	
}
