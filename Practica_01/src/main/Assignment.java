package main;

import exp.Arithmetic;
import exp.Expression;
import exp.Variable;

public class Assignment implements Statement {

	private Expression left;
	private Expression right;

	public Assignment(int i, int j, Variable variable, Arithmetic arithmetic) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the left
	 */
	public Expression getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public void setLeft(Expression left) {
		this.left = left;
	}

	/**
	 * @return the right
	 */
	public Expression getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public void setRight(Expression right) {
		this.right = right;
	}

}
