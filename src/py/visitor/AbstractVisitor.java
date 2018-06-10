package py.visitor;

import py.ast.Arithmetic;
import py.ast.Assignment;
import py.ast.Cast;
import py.ast.CharLiteral;
import py.ast.Comparison;
import py.ast.Definition;
import py.ast.Expression;
import py.ast.FieldAccess;
import py.ast.FunDefinition;
import py.ast.IfStatement;
import py.ast.Indexing;
import py.ast.IntLiteral;
import py.ast.Invocation;
import py.ast.Logical;
import py.ast.Program;
import py.ast.Read;
import py.ast.RealLiteral;
import py.ast.RecordField;
import py.ast.Return;
import py.ast.Statement;
import py.ast.UnaryMinus;
import py.ast.UnaryNot;
import py.ast.VarDefinition;
import py.ast.Variable;
import py.ast.WhileStatement;
import py.ast.Write;
import py.tipo.ArrayType;
import py.tipo.CharType;
import py.tipo.ErrorType;
import py.tipo.FunctionType;
import py.tipo.IntType;
import py.tipo.RealType;
import py.tipo.RecordType;
import py.tipo.VoidType;

public class AbstractVisitor implements Visitor {

	@Override
	public Object visit(Variable v, Object object) {
		return null;
	}

	@Override
	public Object visit(Arithmetic a, Object object) {
		a.getLeft().accept(this, object);
		a.getRight().accept(this, object);
		return null;
	}

	@Override
	public Object visit(Assignment a, Object o) {
		a.getLeft().accept(this, o);
		a.getRight().accept(this, o);
		return null;
	}

	@Override
	public Object visit(Cast cast, Object o) {
		cast.getExp().accept(this, o);
		cast.getCastType().accept(this, o);
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object o) {
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object o) {
		comparison.getLeft().accept(this, o);
		comparison.getRight().accept(this, o);
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object o) {
		fieldAccess.getExp().accept(this, o);
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {
		funDefinition.getType().accept(this, o);
		if (funDefinition.getStatements() != null) {
			for (Statement statement : funDefinition.getStatements()) {
				statement.accept(this, o);
			}
		}
		return null;

	}

	@Override
	public Object visit(IfStatement ifStatement, Object o) {
		ifStatement.getCondition().accept(this, o);
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
	public Object visit(Indexing indexing, Object o) {
		indexing.getRight().accept(this, o);
		indexing.getLeft().accept(this, o);
		return true;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object o) {
		return true;
	}

	@Override
	public Object visit(Invocation invocation, Object o) {
		invocation.getFuncion().accept(this, o);
		if (invocation.getArguments() != null) {
			for (Expression e : invocation.getArguments()) {
				e.accept(this, o);
			}
		}
		return null;
	}

	@Override
	public Object visit(Logical logical, Object o) {
		logical.getLeft().accept(this, o);
		logical.getRight().accept(this, o);
		return null;
	}

	@Override
	public Object visit(UnaryNot negation, Object o) {
		negation.getOperand().accept(this, o);
		return null;
	}

	@Override
	public Object visit(Program program, Object o) {
		for (Definition def : program.getDefinitions()) {
			def.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(Read read, Object o) {
		read.getExpression().accept(this, o);
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object o) {
		return null;
	}

	@Override
	public Object visit(RecordField recordField, Object o) {
		recordField.getType().accept(this, o);
		return null;
	}

	@Override
	public Object visit(RecordType recordType, Object o) {
		for (RecordField r : recordType.getFields()) {
			r.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(Return return1, Object o) {
		return1.getExpression().accept(this, o);
		return null;
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object o) {
		unaryMinus.getOperand().accept(this, o);
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object o) {
		varDefinition.getType().accept(this, o);
		return null;
	}

	@Override
	public Object visit(WhileStatement whileStatement, Object o) {
		whileStatement.getCondition().accept(this, o);
		for (Statement s : whileStatement.getBody()) {
			s.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(Write write, Object o) {
		write.getExpresion().accept(this, o);
		return null;
	}

	@Override
	public Object visit(ArrayType arrayType, Object o) {
		arrayType.getOf().accept(this, o);
		return null;
	}

	@Override
	public Object visit(CharType charType, Object o) {
		return null;
	}

	@Override
	public Object visit(ErrorType errorType, Object o) {
		return null;
	}

	@Override
	public Object visit(FunctionType functionType, Object o) {
		functionType.getReturnType().accept(this, o);
		for (Statement s : functionType.getParameters()) {
			s.accept(this, o);
		}
		return null;
	}

	@Override
	public Object visit(IntType intType, Object o) {
		return null;
	}

	@Override
	public Object visit(RealType realType, Object o) {
		return null;
	}

	@Override
	public Object visit(VoidType voidType, Object o) {
		return null;
	}

}
