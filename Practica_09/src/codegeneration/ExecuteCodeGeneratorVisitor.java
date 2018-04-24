package codegeneration;

import ast.Arithmetic;
import ast.Definition;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Program;
import ast.Read;
import ast.Statement;
import ast.VarDefinition;
import ast.Write;

public class ExecuteCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {

	private CodeGenerator cg;

	@Override
	public Object visit(Program program, Object o) {
		for (Definition def : program.getDefinitions()) {
			if (def instanceof VarDefinition) {
				def.accept(this, o);
			}
		}

		cg.callMain();
		cg.halt();

		for (Definition def : program.getDefinitions()) {
			if (def instanceof FunDefinition) {
				def.accept(valueCgVisitor, o);
			}
		}

		return null;
	}

	@Override
	public Object visit(Write write, Object o) {

		write.getExpresion().accept(valueCgVisitor, o);
		cg.out(write.getExpresion().getType());

		return null;
	}

	@Override
	public Object visit(Read read, Object o) {

		read.getExpression().accept(adressCgVisitor, o);
		cg.in(read.getExpression().getType());
		cg.store(read.getExpression().getType());

		return null;
	}

	@Override
	public Object visit(Arithmetic arithmetic, Object object) {

		arithmetic.getLeft().accept(valueCgVisitor, object);
		arithmetic.getLeft().accept(valueCgVisitor, object);

		if (arithmetic.getOperator() == "+") {
			cg.add(arithmetic.getType());
		}

		if (arithmetic.getOperator() == "-") {
			cg.sub(arithmetic.getType());
		}

		if (arithmetic.getOperator() == "*") {
			cg.mul(arithmetic.getType());
		}

		if (arithmetic.getOperator() == "/") {
			cg.div(arithmetic.getType());
		}

		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement, Object o) {

		ifStatement.getCondition().accept(valueCgVisitor, o);
		cg.jz("cuerpoElse");
		for (Statement s : ifStatement.getIfBody()) {
			s.accept(valueCgVisitor, o);
		}
		cg.jmp("cuerpoElse");
		cg.etiqueta("cuerpoElse");
		for (Statement s : ifStatement.getElseBody()) {
			s.accept(valueCgVisitor, o);
		}
		cg.incrementaContador();

		return null;
	}

}
