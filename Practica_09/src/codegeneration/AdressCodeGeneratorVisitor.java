package codegeneration;

import ast.VarDefinition;
import ast.Variable;
import tipo.IntType;

public class AdressCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	public AdressCodeGeneratorVisitor(CodeGenerator cg) {
		super(cg);
	}

	@Override
	public Object visit(Variable variable, Object object) {

		VarDefinition v = (VarDefinition) variable.getVarDefinition();

		if (variable.getVarDefinition().getScope() == 0) {
			cg.pusha(v.getOffset());
		} else {
			cg.pushabp();
			cg.push(v.getOffset());
			cg.add(IntType.getInstance());
		}

		return null;
	}

}
