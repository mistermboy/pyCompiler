package main;

public class Write implements Statement {

	private Expression write;

	public Write(int i, int j, Variable variable) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the write
	 */
	public Expression getWrite() {
		return write;
	}

	/**
	 * @param write
	 *            the write to set
	 */
	public void setWrite(Expression write) {
		this.write = write;
	}

}
