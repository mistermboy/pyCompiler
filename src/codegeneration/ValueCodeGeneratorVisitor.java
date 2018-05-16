package codegeneration;

import ast.Arithmetic;
import ast.Cast;
import ast.CharLiteral;
import ast.Comparison;
import ast.Expression;
import ast.FieldAccess;
import ast.Indexing;
import ast.IntLiteral;
import ast.Invocation;
import ast.Logical;
import ast.RealLiteral;
import ast.UnaryMinus;
import ast.UnaryNot;
import ast.Variable;
import tipo.CharType;
import tipo.FunctionType;
import tipo.IntType;
import tipo.Type;

public class ValueCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	AdressCodeGeneratorVisitor adressCgVisitor;

	public ValueCodeGeneratorVisitor(CodeGenerator cg, AdressCodeGeneratorVisitor adressCgVisitor) {
		super(cg);
		this.adressCgVisitor = adressCgVisitor;
	}

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
		
		if (arithmetic.getLeft().getType().suffix() == 'B' && arithmetic.getRight().getType().suffix() == 'B') {
			arithmetic.getLeft().accept(this, object);
			cg.b2i();
			arithmetic.getRight().accept(this, object);
			cg.b2i();
			cg.aritmetic(arithmetic.getOperator(), IntType.getInstance());
			cg.i2b();
		} else {
			arithmetic.getLeft().accept(this, object);
			cg.convert(arithmetic.getLeft().getType(), arithmetic.getType());
			arithmetic.getRight().accept(this, object);
			cg.convert(arithmetic.getRight().getType(), arithmetic.getType());
			cg.aritmetic(arithmetic.getOperator(), arithmetic.getType());
		}

		return null;

	}

	@Override
	public Object visit(Comparison comparison, Object o) {

		Type superType = comparison.getLeft().getType().superType(comparison.getRight().getType());
		comparison.getLeft().accept(this, o);
		cg.convert(comparison.getLeft().getType(), superType);
		comparison.getRight().accept(this, o);
		cg.convert(comparison.getRight().getType(), superType);
		cg.comparison(comparison.getComparator(), superType);

		return null;
	}

	@Override
	public Object visit(Cast cast, Object o) {
		cast.getExp().accept(this, o);
		cg.cast(cast.getExp().getType(), cast.getCastType());
		return null;
	}

	@Override
	public Object visit(Logical logical, Object o) {
		logical.getLeft().accept(this, o);
		cg.convert(logical.getLeft().getType(), logical.getType());
		logical.getRight().accept(this, o);
		cg.convert(logical.getRight().getType(), logical.getType());
		cg.logic(logical.getLogicalOperator());

		return null;

	}

	@Override
	public Object visit(UnaryNot negation, Object o) {
		negation.getOperand().accept(this, o);
		cg.not();
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object o) {
		fieldAccess.accept(adressCgVisitor, o);
		cg.load(fieldAccess.getType());
		return null;
	}

	@Override
	public Object visit(Indexing indexing, Object o) {
		indexing.accept(adressCgVisitor, o);
		cg.load(indexing.getType());
		return null;
	}

	@Override
	public Object visit(Invocation invocation, Object o) {
		int i = 0;
		for (Expression s : invocation.getArguments()) {
			s.accept(this, o);
			cg.convert(s.getType(),
					((FunctionType) invocation.getFuncion().getType()).getParameters().get(i++).getType());
		}
		cg.call(invocation.getFuncion().getNameString());
		return null;

	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object o) {

		unaryMinus.getOperand().accept(this, o);
		cg.push(-1);
		cg.convert(IntType.getInstance(), unaryMinus.getType());
		cg.mul(unaryMinus.getOperand().getType());
		return null;
	}

}
