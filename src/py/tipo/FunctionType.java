package py.tipo;

import java.util.List;

import py.ast.VarDefinition;
import py.visitor.Visitor;

public class FunctionType extends AbstractType {
	private Type returnType;
	private List<VarDefinition> parameters;

	public FunctionType(int row, int column, Type returnType, List<VarDefinition> parameters) {
		super();
		this.row = row;
		this.column = column;
		this.returnType = returnType;
		this.parameters = parameters;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public List<VarDefinition> getParameters() {
		return parameters;
	}

	public void setParameters(List<VarDefinition> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		String cad = "(";
		for (VarDefinition v : this.getParameters()) {
			cad += " " + v.toString() + ",";
		}
		cad += " ):";
		cad += "" + getReturnType().toString();
		return cad;
	}

	@Override
	public Object accept(Visitor v, Object o) {
		return v.visit(this, o);
	}

	@Override
	public Type parentesis(List<Type> types) {
		if (types.size() == parameters.size()) {
			for (int i = 0; i < types.size(); i++) {
				if (types.get(i).promotesTo(parameters.get(i).getType()) == null) {
					return null;
				}
			}
			return returnType;
		}
		return null;
	}

	@Override
	public int numberOfBytes() {
		if (this.getReturnType() instanceof VoidType) {
			return 0;
		}

		if (this.getReturnType() instanceof ErrorType) {
			throw new IllegalStateException();
		}

		return this.getReturnType().numberOfBytes();

	}

}
