package main;

public class Read implements Statement {

	private Expression read;

	public Read(int i, int j, Variable variable) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the read
	 */
	public Expression getRead() {
		return read;
	}

	/**
	 * @param read
	 *            the read to set
	 */
	public void setRead(Expression read) {
		this.read = read;
	}

}
