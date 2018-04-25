package codegeneration;

import ast.Variable;

public class AdressCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	@Override
	public Object visit(Variable variable, Object object) {

		if (variable.getVarDefinition().getScope() == 0) {
			cg.pusha(variable.getVarDefinition().getOffset());
		} else {
			cg.pushbp();
			cg.pushi(variable.getVarDefinition().getOffset());
			cg.add(variable.getVarDefinition().getType());
		}

		return null;
	}

}
