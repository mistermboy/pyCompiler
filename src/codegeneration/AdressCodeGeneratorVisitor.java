package codegeneration;

import ast.FieldAccess;
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
			cg.pushbp();
			cg.push(v.getOffset());
			cg.add(IntType.getInstance());
		}

		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object o) {
		fieldAccess.getExp().accept(this, o);
		cg.push(fieldAccess.getExp().getType().get(fieldAccess.getName()).getOffset());
		cg.add(IntType.getInstance());
		return null;
	}

}
