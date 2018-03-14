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

	Object visit(Negation negation, Object o);

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

}