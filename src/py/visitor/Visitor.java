package py.visitor;

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

public interface Visitor {

	Object visit(Arithmetic arithmetic, Object object);

	Object visit(Variable variable, Object object);

	Object visit(Assignment assignment, Object o);

	Object visit(Cast cast, Object o);

	Object visit(CharLiteral charLiteral, Object o);

	Object visit(Comparison comparison, Object o);

	Object visit(FieldAccess fieldAccess, Object o);

	Object visit(FunDefinition funDefinition, Object o);

	Object visit(IfStatement ifStatement, Object o);

	Object visit(Indexing indexing, Object o);

	Object visit(IntLiteral intLiteral, Object o);

	Object visit(Invocation invocation, Object o);

	Object visit(Logical logical, Object o);

	Object visit(UnaryNot negation, Object o);

	Object visit(Program program, Object o);

	Object visit(Read read, Object o);

	Object visit(RealLiteral realLiteral, Object o);

	Object visit(RecordField recordField, Object o);

	Object visit(RecordType recordType, Object o);

	Object visit(Return return1, Object o);

	Object visit(UnaryMinus unaryMinus, Object o);

	Object visit(VarDefinition varDefinition, Object o);

	Object visit(WhileStatement whileStatement, Object o);

	Object visit(Write write, Object o);

	Object visit(ArrayType arrayType, Object o);

	Object visit(CharType charType, Object o);

	Object visit(ErrorType errorType, Object o);

	Object visit(FunctionType functionType, Object o);

	Object visit(IntType intType, Object o);

	Object visit(RealType realType, Object o);

	Object visit(VoidType voidType, Object o);

}
