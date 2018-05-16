package ast;

import visitor.Visitor;

public class Variable extends AbstractExpression {

	private String nameString;

	private Definition varDefinition;

	public Variable(int i, int j, String nameString) {
		this.row = i;
		this.column = j;
		this.nameString = nameString;
	}

	/**
	 * @return the nameString
	 */
	public String getNameString() {
		return nameString;
	}

	/**
	 * @param nameString
	 *            the nameString to set
	 */
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	@Override
	public int getLine() {
		return this.row;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public String toString() {
		return "" + nameString;
	}

	@Override
	public Object accept(Visitor v, Object object) {
		return v.visit(this, object);
	}

	public Definition getVarDefinition() {
		return varDefinition;
	}

	public void setVarDefinition(Definition definition) {
		this.varDefinition = definition;
	}

}
