package py.visitor;

import java.util.ArrayList;
import java.util.List;

import py.ast.Arithmetic;
import py.ast.Assignment;
import py.ast.Cast;
import py.ast.CharLiteral;
import py.ast.Comparison;
import py.ast.Expression;
import py.ast.FieldAccess;
import py.ast.FunDefinition;
import py.ast.IfStatement;
import py.ast.Indexing;
import py.ast.IntLiteral;
import py.ast.Invocation;
import py.ast.Logical;
import py.ast.Read;
import py.ast.RealLiteral;
import py.ast.Return;
import py.ast.Statement;
import py.ast.UnaryMinus;
import py.ast.UnaryNot;
import py.ast.Variable;
import py.ast.WhileStatement;
import py.tipo.ErrorType;
import py.tipo.FunctionType;
import py.tipo.Type;

public class TypeCheckingVisitor extends AbstractVisitor {

	@Override
	public Object visit(Variable v, Object object) {
		if (v.getVarDefinition() != null) {
			v.setType(v.getVarDefinition().getType());
		}
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

		if (a.getLeft().getType() != null && a.getRight().getType() != null) {
			a.getLeft().setType(a.getRight().getType().promotesTo(a.getLeft().getType()));
			if (a.getLeft().getType() == null) {
				a.getLeft().setType(
						new ErrorType(a.getLeft(), "ERROR: No es posible realizar la asignación en " + a.toString())); // Error
																														// por
																														// mala
																														// promoción
																														// de
																														// tipos
			}

		}

		return null;
	}

	@Override
	public Object visit(Arithmetic a, Object object) {
		a.getLeft().accept(this, object);
		a.getRight().accept(this, object);

		a.setType(a.getLeft().getType().arithmetic(a.getRight().getType()));
		if (a.getType() == null) {
			a.setType(
					new ErrorType(a, "ERROR: No ha sido posible realizar la operación aritmética en " + a.toString()));
		}
		a.setLValue(false);
		return null;
	}

	@Override
	public Object visit(Cast cast, Object o) {
		cast.getExp().accept(this, o);
		cast.getCastType().accept(this, o);

		cast.setType(cast.getCastType().canBeCast(cast.getExp().getType()));
		if (cast.getType() == null) {
			cast.setType(new ErrorType(cast, "ERROR: No es posible realizar el casteo en " + cast.toString()));
		}

		cast.setLValue(false);
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object o) {
		comparison.getLeft().accept(this, o);
		comparison.getRight().accept(this, o);

		comparison.setType(comparison.getLeft().getType().comparison(comparison.getRight().getType()));
		if (comparison.getType() == null) {
			comparison.setType(new ErrorType(comparison,
					"ERROR:  No ha sido posible realizar la operación de comparación en " + comparison.toString()));
		}

		comparison.setLValue(false);
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object o) {
		charLiteral.setType(py.tipo.CharType.getInstance());
		charLiteral.setLValue(false);
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

	@Override
	public Object visit(FieldAccess fieldAccess, Object o) {
		fieldAccess.getExp().accept(this, o);

		if (fieldAccess.getExp().getType() != null) {
			fieldAccess.setType(fieldAccess.getExp().getType().dot(fieldAccess.getName()));
			if (fieldAccess.getType() == null) {
				fieldAccess.setType(new ErrorType(fieldAccess,
						"ERROR: No es posible acceder al campo del registro en " + fieldAccess.toString()));
			}
		}

		if (fieldAccess.getExp().getLValue()) {
			fieldAccess.setLValue(true);
		}
		return null;
	}

	@Override
	public Object visit(Indexing indexing, Object o) {
		indexing.getRight().accept(this, o);
		indexing.getLeft().accept(this, o);

		indexing.setType(indexing.getLeft().getType().squareBrackets(indexing.getRight().getType()));
		if (indexing.getType() == null) {
			indexing.setType(
					new ErrorType(indexing, "ERROR: No es posible acceder al array en " + indexing.toString()));
		}

		if (!indexing.getLeft().getLValue()) {
			new ErrorType(indexing, "ERROR: Se esperaba un Lvalue en: " + indexing.getLeft());
		} else {
			indexing.setLValue(true);
		}

		return true;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object o) {
		intLiteral.setType(py.tipo.IntType.getInstance());
		intLiteral.setLValue(false);
		return true;
	}

	@Override
	public Object visit(Invocation invocation, Object o) {
		invocation.getFuncion().accept(this, o);

		List<Type> types = new ArrayList<Type>();
		if (invocation.getArguments() != null) {

			for (Expression e : invocation.getArguments()) {
				e.accept(this, o);
				types.add(e.getType());
			}
		}

		invocation.setType(invocation.getFuncion().getType().parentesis(types));
		if (invocation.getType() == null) {
			invocation.setType(new ErrorType(invocation,
					"ERROR: No ha sido posible invocar a la función en: " + invocation.toString()));
		}

		invocation.setLValue(false);
		return null;
	}

	@Override
	public Object visit(Logical logical, Object o) {
		logical.getLeft().accept(this, o);
		logical.getRight().accept(this, o);

		logical.setType(logical.getLeft().getType().logical(logical.getRight().getType()));
		if (logical.getType() == null) {
			logical.setType(new ErrorType(logical,
					"ERROR: Se esperaban tipos iguales (Enteros o Carácteres) en " + logical.toString()));
		}

		logical.setLValue(false);
		return null;
	}

	@Override
	public Object visit(UnaryNot negation, Object o) {
		negation.getOperand().accept(this, o);

		negation.setType(negation.getOperand().getType().logical());
		if (negation.getType() == null) {
			negation.setType(
					new ErrorType(negation, "ERROR: Se esperaba un tipo entero o caracter en " + negation.toString()));
		}

		negation.setLValue(false);
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object o) {
		realLiteral.setType(py.tipo.RealType.getInstance());
		realLiteral.setLValue(false);
		return null;
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object o) {
		unaryMinus.getOperand().accept(this, o);

		unaryMinus.setType(unaryMinus.getOperand().getType().arithmetic());
		if (unaryMinus.getType() == null) {
			unaryMinus.setType(
					new ErrorType(unaryMinus, "ERROR: Se esperaba un tipo entero o real en " + unaryMinus.toString()));
		}

		unaryMinus.setLValue(false);
		return null;
	}

	@Override
	public Object visit(WhileStatement whileStatement, Object o) {
		whileStatement.getCondition().accept(this, o);

		if (whileStatement.getCondition().getType() != null) {
			if (!whileStatement.getCondition().getType().isLogical()) {
				whileStatement.getCondition().setType(new ErrorType(whileStatement.getCondition(),
						"ERROR: No se ha encontrado un valor booleano en " + whileStatement.getCondition().toString()));
			}
		}

		for (Statement s : whileStatement.getBody()) {
			s.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement, Object o) {
		ifStatement.getCondition().accept(this, o);

		if (!ifStatement.getCondition().getType().isLogical()) {
			ifStatement.getCondition().setType(new ErrorType(ifStatement.getCondition(),
					"ERROR: No se ha encontrado un valor booleano en " + ifStatement.getCondition().toString()));
		}

		if (ifStatement.getIfBody() != null) {
			for (Statement statement : ifStatement.getIfBody()) {
				statement.accept(this, o);
			}
		}

		if (ifStatement.getElseBody() != null) {
			for (Statement statement : ifStatement.getElseBody()) {
				statement.accept(this, o);
			}
		}
		return null;
	}

	@Override
	public Object visit(Return return1, Object o) {
		return1.getExpression().accept(this, o);

		FunctionType fType = (FunctionType) o;
		if (return1.getExpression().getType().promotesTo(fType.getReturnType()) == null) {
			return1.getExpression().setType(
					new ErrorType(return1, "ERROR: El valor retornado no es el esperado en: " + return1.toString()));
			;
		}
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {
		funDefinition.getType().accept(this, o);
		if (funDefinition.getStatements() != null) {
			for (Statement statement : funDefinition.getStatements()) {
				statement.accept(this, funDefinition.getType());
			}
		}
		return null;

	}

	@Override
	public Object visit(FunctionType functionType, Object o) {
		if (!functionType.getReturnType().isBuildingType()) {
			new ErrorType(functionType, "ERROR: El valor de retorno de la función no es un tipo simple en: "
					+ functionType.getReturnType().toString());
		}
		functionType.getReturnType().accept(this, o);
		for (Statement s : functionType.getParameters()) {
			s.accept(this, o);
		}
		return null;
	}

}
