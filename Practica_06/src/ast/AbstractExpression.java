package ast;

public abstract class AbstractExpression implements Expression {

	private boolean lValue;

	@Override
	public abstract int getLine();

	@Override
	public abstract int getColumn();

	@Override
	public abstract boolean getLValue();

	@Override
	public abstract void setLValue(boolean value);

}
