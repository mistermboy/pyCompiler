package codegeneration;

import ast.Arithmetic;
import ast.Assignment;
import ast.Definition;
import ast.Expression;
import ast.FunDefinition;
import ast.IfStatement;
import ast.Invocation;
import ast.Logical;
import ast.Program;
import ast.Read;
import ast.Statement;
import ast.VarDefinition;
import ast.WhileStatement;
import ast.Write;
import tipo.FunctionType;

public class ExecuteCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {
	ValueCodeGeneratorVisitor valueCgVisitor;
	AdressCodeGeneratorVisitor adressCgVisitor;

	public ExecuteCodeGeneratorVisitor(String entrada, String salida) {
		super(new CodeGenerator(entrada, salida));
		adressCgVisitor = new AdressCodeGeneratorVisitor(this.cg);
		valueCgVisitor = new ValueCodeGeneratorVisitor(this.cg, adressCgVisitor);

	}

	@Override
	public Object visit(Program program, Object o) {
		for (Definition def : program.getDefinitions()) {
			if (def instanceof VarDefinition) {
				def.accept(this, o);
			}
		}

		cg.call("MAIN");
		cg.halt();

		for (Definition def : program.getDefinitions()) {
			if (def instanceof FunDefinition) {
				def.accept(this, o);
			}
		}

		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {

		// cg.etiqueta(funDefinition.getName());

		for (Statement d : funDefinition.getStatements()) {
			d.accept(this, o);
		}
		// Como accedo a los parámetros???
		// cg.enter(funDefinition.get)

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
	public Object visit(Assignment assignment, Object o) {

		assignment.getLeft().accept(adressCgVisitor, o);
		assignment.getRight().accept(valueCgVisitor, o);
		// CONVERSIÓN IMPLÍCITA RELLENAR
		cg.store(assignment.getLeft().getType());

		return null;
	}

	// @Override
	// public Object visit(Arithmetic arithmetic, Object object) {
	//
	// arithmetic.getLeft().accept(valueCgVisitor, object);
	// arithmetic.getLeft().accept(valueCgVisitor, object);
	//
	// if (arithmetic.getOperator() == "+") {
	// cg.add(arithmetic.getType());
	// }
	//
	// if (arithmetic.getOperator() == "-") {
	// cg.sub(arithmetic.getType());
	// }
	//
	// if (arithmetic.getOperator() == "*") {
	// cg.mul(arithmetic.getType());
	// }
	//
	// if (arithmetic.getOperator() == "/") {
	// cg.div(arithmetic.getType());
	// }
	//
	// return null;
	// }
	//
	// @Override
	// public Object visit(IfStatement ifStatement, Object o) {
	//
	// ifStatement.getCondition().accept(valueCgVisitor, o);
	// cg.jz("cuerpoElse");
	// for (Statement s : ifStatement.getIfBody()) {
	// s.accept(valueCgVisitor, o);
	// }
	// cg.jmp("cuerpoElse");
	// cg.etiqueta("cuerpoElse");
	// for (Statement s : ifStatement.getElseBody()) {
	// s.accept(valueCgVisitor, o);
	// }
	// cg.incrementaContador();
	//
	// return null;
	// }
	//
	// @Override
	// public Object visit(WhileStatement whileStatement, Object o) {
	//
	// cg.etiqueta("bucleWhile");
	// whileStatement.getCondition().accept(valueCgVisitor, o);
	// cg.jz("finBucle");
	// for (Statement s : whileStatement.getBody()) {
	// s.accept(valueCgVisitor, o);
	// }
	// cg.jmp("bucleWhile");
	// cg.incrementaContador();
	//
	// return null;
	// }
	//
	// @Override
	// public Object visit(Logical logical, Object o) {
	//
	// logical.getLeft().accept(valueCgVisitor, o);
	// logical.getRight().accept(valueCgVisitor, o);
	//
	// if (logical.getLogicalOperator() == "<") {
	// cg.lt();
	// }
	//
	// if (logical.getLogicalOperator() == "<=") {
	// cg.le();
	// }
	//
	// if (logical.getLogicalOperator() == ">") {
	// cg.gt();
	// }
	//
	// if (logical.getLogicalOperator() == ">=") {
	// cg.ge();
	// }
	//
	// return null;
	//
	// }
	//
	// @Override
	// public Object visit(Invocation invocation, Object o) {
	//
	// for (Expression s : invocation.getArguments()) {
	// s.accept(valueCgVisitor, o);
	// }
	// cg.call(invocation.getFuncion().getNameString());
	// // if(invocation.getFuncion().getVarDefinition())
	//
	// // COMO ACCEDO A EL RETORNO PA SABER SI HAY QUE HACER POP
	// return null;
	//
	// }

}
