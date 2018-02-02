package exp;

public class Arithmetic implements Expression {

	private String operator;

	private Expression left;
	private Expression right;

	public Arithmetic(int i, int j, IntLiteral intLiteral, String string, Variable variable) {
		// TODO Auto-generated constructor stub
	}

	public Arithmetic(int i, int j, Arithmetic arithmetic, String string, Arithmetic arithmetic2) {
		// TODO Auto-generated constructor stub
	}

	public Arithmetic(int i, int j, Arithmetic arithmetic, String string, Variable variable) {
		// TODO Auto-generated constructor stub
	}

	public Arithmetic(int i, int j, UnaryMinus unaryMinus, String string, IntLiteral intLiteral) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator
	 *            the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
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
