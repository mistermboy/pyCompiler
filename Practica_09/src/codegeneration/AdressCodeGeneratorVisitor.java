package codegeneration;

import ast.VarDefinition;
import ast.Variable;

public class AdressCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	@Override
	public Object visit(Variable variable, Object object) {

		VarDefinition v = (VarDefinition) variable.getVarDefinition();
		
		if (variable.getVarDefinition().getScope() == 0) {
			cg.pusha(v.getOffset());
		} else {
			cg.pushbp();
			cg.pushi(v.getOffset());
			cg.add(variable.getVarDefinition().getType());
		}

		return null;
	}

}
