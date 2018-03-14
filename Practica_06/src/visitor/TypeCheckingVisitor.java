package visitor;

import ast.Arithmetic;
import ast.Assignment;
import ast.Cast;
import ast.CharLiteral;
import ast.Comparison;
import ast.FieldAccess;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Indexing;
import ast.IntLiteral;
import ast.Invocation;
import ast.Logical;
import ast.Negation;
import ast.Program;
import ast.Read;
import ast.RealLiteral;
import ast.RecordField;
import ast.RecordType;
import ast.Return;
import ast.UnaryMinus;
import ast.VarDefinition;
import ast.Variable;
import ast.WhileStatement;
import ast.Write;

public class TypeCheckingVisitor implements Visitor {

	@Override
	public Object visit(Variable v, Object object) {
		v.setLValue(true);
		return null;
	}

	@Override
	public Object visit(Arithmetic a, Object object) {
		// a.getLeft().a
		return null;
	}

	@Override
	public Object visit(Assignment assignment, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Cast cast, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Comparison comparison, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Indexing indexing, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Invocation invocation, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Logical logical, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Negation negation, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Program program, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Read read, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RecordField recordField, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(RecordType recordType, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Return return1, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(WhileStatement whileStatement, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Write write, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
