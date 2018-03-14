package ast;

public abstract class AbstractExpression implements Expression {

	private boolean lValue = false;

	@Override
	public boolean getLValue() {
		return lValue;
	}

	@Override
	public void setLValue(boolean lValue) {
		this.lValue = lValue;
	}

}
