 package py.codegeneration;

import py.ast.FieldAccess;
import py.ast.Indexing;
import py.ast.VarDefinition;
import py.ast.Variable;
import py.tipo.IntType;

public class AdressCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	private ValueCodeGeneratorVisitor valueCgVisitor;

	public AdressCodeGeneratorVisitor(CodeGenerator cg) {
		super(cg);
	}

	public void setValueVisitor(ValueCodeGeneratorVisitor valueVisitor) {
		this.valueCgVisitor = valueVisitor;
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

	@Override
	public Object visit(Indexing indexing, Object o) {
		indexing.getLeft().accept(this, o);
		indexing.getRight().accept(valueCgVisitor, o);
		cg.push(indexing.getType().numberOfBytes());
		cg.mul(IntType.getInstance());
		cg.add(IntType.getInstance());
		return null;
	}

}
