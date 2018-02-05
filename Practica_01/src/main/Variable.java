package main;

public class Variable implements Expression {

	private String nameString;

	public Variable(int i, int j, String nameString) {
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

}
