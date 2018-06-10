package py.codegeneration;

import py.ast.Arithmetic;
import py.ast.Assignment;
import py.ast.Cast;
import py.ast.CharLiteral;
import py.ast.Comparison;
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
import py.visitor.Visitor;

public class AbstractCodeGeneratorVisitor implements Visitor {

	protected CodeGenerator cg;

	@Override
	public Object visit(Arithmetic arithmetic, Object object) {
		throw new IllegalStateException("Plantilla no definida en Arithmetic");
	}

	public AbstractCodeGeneratorVisitor(CodeGenerator cg) {
		super();
		this.cg = cg;
	}

	@Override
	public Object visit(Variable variable, Object object) {

		throw new IllegalStateException("Plantilla no definida en Variable");
	}

	@Override
	public Object visit(Assignment assignment, Object o) {

		throw new IllegalStateException("Plantilla no definida en Assignment");
	}

	@Override
	public Object visit(Cast cast, Object o) {

		throw new IllegalStateException("Plantilla no definida en Cast");
	}

	@Override
	public Object visit(CharLiteral charLiteral, Object o) {

		throw new IllegalStateException("Plantilla no definida en CharLiteral");
	}

	@Override
	public Object visit(Comparison comparison, Object o) {

		throw new IllegalStateException("Plantilla no definida en Comparison");
	}

	@Override
	public Object visit(FieldAccess fieldAccess, Object o) {

		throw new IllegalStateException("Plantilla no definida en FieldAccess");
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {

		throw new IllegalStateException("Plantilla no definida en FunDefinition");
	}

	@Override
	public Object visit(IfStatement ifStatement, Object o) {

		throw new IllegalStateException("Plantilla no definida en IfStatement");
	}

	@Override
	public Object visit(Indexing indexing, Object o) {

		throw new IllegalStateException("Plantilla no definida en Indexing");
	}

	@Override
	public Object visit(IntLiteral intLiteral, Object o) {

		throw new IllegalStateException("Plantilla no definida en IntLiteral");
	}

	@Override
	public Object visit(Invocation invocation, Object o) {

		throw new IllegalStateException("Plantilla no definida en Invocation");
	}

	@Override
	public Object visit(Logical logical, Object o) {

		throw new IllegalStateException("Plantilla no definida en Logical");
	}

	@Override
	public Object visit(UnaryNot negation, Object o) {

		throw new IllegalStateException("Plantilla no definida en UnaryNot");
	}

	@Override
	public Object visit(Program program, Object o) {

		throw new IllegalStateException("Plantilla no definida en Program");
	}

	@Override
	public Object visit(Read read, Object o) {

		throw new IllegalStateException("Plantilla no definida en Read");
	}

	@Override
	public Object visit(RealLiteral realLiteral, Object o) {

		throw new IllegalStateException("Plantilla no definida en RealLiteral");
	}

	@Override
	public Object visit(RecordField recordField, Object o) {

		throw new IllegalStateException("Plantilla no definida en RecordField");
	}

	@Override
	public Object visit(RecordType recordType, Object o) {

		throw new IllegalStateException("Plantilla no definida en RecordType");
	}

	@Override
	public Object visit(Return return1, Object o) {

		throw new IllegalStateException("Plantilla no definida en Return");
	}

	@Override
	public Object visit(UnaryMinus unaryMinus, Object o) {

		throw new IllegalStateException("Plantilla no definida en UnaryMinus");
	}

	@Override
	public Object visit(VarDefinition varDefinition, Object o) {

		// throw new IllegalStateException("Plantilla no definida en VarDefinition");
		return null;
	}

	@Override
	public Object visit(WhileStatement whileStatement, Object o) {

		throw new IllegalStateException("Plantilla no definida en WhileStatement");
	}

	@Override
	public Object visit(Write write, Object o) {

		throw new IllegalStateException("Plantilla no definida en Write");
	}

	@Override
	public Object visit(ArrayType arrayType, Object o) {

		throw new IllegalStateException("Plantilla no definida en ArrayType");
	}

	@Override
	public Object visit(CharType charType, Object o) {

		throw new IllegalStateException("Plantilla no definida en CharType");
	}

	@Override
	public Object visit(ErrorType errorType, Object o) {

		throw new IllegalStateException("Plantilla no definida en ErrorType");
	}

	@Override
	public Object visit(FunctionType functionType, Object o) {

		throw new IllegalStateException("Plantilla no definida en FunctionType");
	}

	@Override
	public Object visit(IntType intType, Object o) {

		throw new IllegalStateException("Plantilla no definida en IntType");
	}

	@Override
	public Object visit(RealType realType, Object o) {

		throw new IllegalStateException("Plantilla no definida en RealType");
	}

	@Override
	public Object visit(VoidType voidType, Object o) {

		throw new IllegalStateException("Plantilla no definida en VoidType");
	}

}
