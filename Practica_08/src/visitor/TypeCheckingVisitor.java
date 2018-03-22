package visitor;

import ast.Assignment;
import ast.Read;
import ast.Variable;
import tipo.ErrorType;

public class TypeCheckingVisitor extends AbstractVisitor {

	@Override
	public Object visit(Variable v, Object object) {
		v.setLValue(true);
		return null;
	}

	@Override
	public Object visit(Assignment a, Object o) {
		a.getLeft().accept(this, o);
		a.getRight().accept(this, o);
		if (!a.getLeft().getLValue()) {
			new ErrorType(a.getLeft(), "ERROR: Se esperaba un Lvalue en: " + a.getLeft());
		}
		return null;
	}

	@Override
	public Object visit(Read read, Object o) {
		read.getExpression().accept(this, o);
		if (!read.getExpression().getLValue()) {
			new ErrorType(read.getExpression(), "ERROR: Se esperaba un Lvalue en: " + read.getExpression());
		}
		return null;
	}

}
