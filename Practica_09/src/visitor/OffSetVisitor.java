package visitor;

import ast.FunDefinition;
import ast.Statement;
import ast.VarDefinition;
import tipo.FunctionType;

public class OffSetVisitor extends AbstractVisitor {

	private int globalOffSet = 0;
	private int paramOffSet = 4;
	private int localOffSet = 0;

	@Override
	public Object visit(VarDefinition varDefinition, Object o) {
		if (varDefinition.getScope() == 0) {
			varDefinition.setOffset(globalOffSet);
			globalOffSet += varDefinition.getType().numberOfBytes();
		} else {

			if ((boolean) o) {
				varDefinition.setOffset(paramOffSet);
				paramOffSet += varDefinition.getType().numberOfBytes();
			} else {
				localOffSet -= varDefinition.getType().numberOfBytes();
				varDefinition.setOffset(localOffSet);
			}
		}
		System.out.println("Nombre: " + varDefinition.getName() + " OffSet: " + varDefinition.getOffset());
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {
		funDefinition.getType().accept(this, o);
		if (funDefinition.getStatements() != null) {
			for (Statement statement : funDefinition.getStatements()) {
				statement.accept(this, false);
			}
		}
		this.localOffSet = 0;
		this.paramOffSet = 4;
		return null;

	}

	@Override
	public Object visit(FunctionType functionType, Object o) {
		functionType.getReturnType().accept(this, o);
		for (int i = functionType.getParameters().size() - 1; i >= 0; i--) {
			functionType.getParameters().get(i).accept(this, true);
		}
		return null;
	}

}
