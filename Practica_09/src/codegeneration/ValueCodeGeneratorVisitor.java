package codegeneration;

import ast.Arithmetic;
import ast.CharLiteral;
import ast.Comparison;
import ast.IntLiteral;
import ast.RealLiteral;
import ast.Variable;
import tipo.Type;

public class ValueCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	@Override
	public Object visit(IntLiteral intLiteral, Object o) {

		cg.push(intLiteral.getValue());
		return null;

	}

	@Override
	public Object visit(CharLiteral charLiteral, Object o) {

		cg.push(charLiteral.getValue());
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object o) {

		cg.push(realLiteral.getValue());
		return null;
	}

	@Override
	public Object visit(Variable variable, Object object) {

		variable.accept(adressCgVisitor, object);
		cg.load(variable.getVarDefinition().getType());

		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object object) {

		arithmetic.getLeft().accept(this, object);
		cg.convert(arithmetic.getLeft().getType(), arithmetic.getRight().getType());
		arithmetic.getRight().accept(this, object);
		cg.convert(arithmetic.getType(), arithmetic.getLeft().getType());
		cg.aritmetic(arithmetic.getOperator(), arithmetic.getLeft().getType());

		return null;

	}

	@Override
	public Object visit(Comparison comparison, Object o) {

		Type superType = comparison.getLeft().getType().superType(comparison.getType());
		comparison.getLeft().accept(this, o);
		cg.convert(comparison.getLeft().getType(), superType);
		comparison.getRight().accept(this, o);
		cg.convert(comparison.getRight().getType(), superType);
		cg.aritmetic(comparison.getComparator(), superType);

		return null;
	}

}
