package py.codegeneration;

import py.ast.Assignment;
import py.ast.Definition;
import py.ast.FunDefinition;
import py.ast.IfStatement;
import py.ast.Invocation;
import py.ast.Program;
import py.ast.Read;
import py.ast.Return;
import py.ast.Statement;
import py.ast.VarDefinition;
import py.ast.WhileStatement;
import py.ast.Write;
import py.tipo.FunctionType;
import py.tipo.Type;
import py.tipo.VoidType;

public class ExecuteCodeGeneratorVisitor extends AbstractCodeGeneratorVisitor {
	ValueCodeGeneratorVisitor valueCgVisitor;
	AdressCodeGeneratorVisitor adressCgVisitor;

	private String inputName;

	public ExecuteCodeGeneratorVisitor(String entrada, String salida) {
		super(new CodeGenerator(entrada, salida));
		adressCgVisitor = new AdressCodeGeneratorVisitor(this.cg);
		valueCgVisitor = new ValueCodeGeneratorVisitor(this.cg, adressCgVisitor);
		adressCgVisitor.setValueVisitor(valueCgVisitor);

		this.inputName = entrada;
	}

	@Override
	public Object visit(Program program, Object o) {

		cg.sourceComment(inputName);

		for (Definition def : program.getDefinitions()) {
			if (def instanceof VarDefinition) {
				def.accept(this, o);
				cg.varComment(def);
			}
		}
		cg.white();
		cg.mainComment();
		cg.call("main");
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

		cg.etiqueta(funDefinition.getName());

		////////////////////////////////////////////////////////////////////////////////////////////////////

		// Comentarios parámetros
		cg.paramComment();
		for (VarDefinition v : ((FunctionType) funDefinition.getType()).getParameters()) {
			cg.varComment(v);
		}
		cg.white();

		// Comentarios locales
		cg.localComment();
		for (Statement d : funDefinition.getStatements()) {
			if (d instanceof VarDefinition) {
				cg.varComment((VarDefinition) d);
			}
		}
		cg.white();
		////////////////////////////////////////////////////////////////////////////////////////////////////

		cg.enter(funDefinition.localBytes());

		for (Statement d : funDefinition.getStatements()) {
			if (!(d instanceof VarDefinition)) {
				cg.lineComment(d.getLine());
				d.accept(this, funDefinition);
			}
		}

		Type ret = ((FunctionType) funDefinition.getType()).getReturnType();
		if (ret == VoidType.getInstance()) {
			cg.ret(0, funDefinition.localBytes(), funDefinition.paramBytes());
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
	public Object visit(Assignment assignment, Object o) {

		assignment.getLeft().accept(adressCgVisitor, o);
		assignment.getRight().accept(valueCgVisitor, o);
		cg.convert(assignment.getRight().getType(), assignment.getLeft().getType());
		cg.store(assignment.getLeft().getType());

		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement, Object o) {
		int label = cg.getLabels(2);
		ifStatement.getCondition().accept(valueCgVisitor, o);
		cg.jz(label);
		for (Statement s : ifStatement.getIfBody()) {
			cg.lineComment(s.getLine());
			s.accept(this, o);
		}
		cg.jmp(label + 1);
		cg.label(label);
		if (ifStatement.getElseBody() != null) {
			for (Statement s : ifStatement.getElseBody()) {
				cg.lineComment(s.getLine());
				s.accept(this, o);
			}
		}
		cg.label(label + 1);

		return null;
	}

	@Override
	public Object visit(WhileStatement whileStatement, Object o) {
		int label = cg.getLabels(2);
		cg.label(label);
		whileStatement.getCondition().accept(valueCgVisitor, o);
		cg.jz(label + 1);
		for (Statement s : whileStatement.getBody()) {
			cg.lineComment(s.getLine());
			s.accept(this, o);
		}
		cg.jmp(label);
		cg.label(label + 1);

		return null;
	}

	@Override
	public Object visit(Invocation invocation, Object o) {
		invocation.accept(valueCgVisitor, o);
		if (((FunctionType) invocation.getFuncion().getType()).getReturnType() != VoidType.getInstance()) {
			cg.pop(((FunctionType) invocation.getFuncion().getType()).getReturnType().suffix());
		}

		return null;

	}

	@Override
	public Object visit(Return return1, Object o) {
		return1.getExpression().accept(valueCgVisitor, o);

		FunDefinition f = (FunDefinition) o;
		cg.convert(return1.getExpression().getType(), ((FunctionType) f.getType()).getReturnType());
		cg.ret(((FunctionType) f.getType()).getReturnType().numberOfBytes(), f.localBytes(), f.paramBytes());

		return null;
	}

//	@Override
//	public Object visit(AlterVal i, Object o) {
//
//		i.getExpr().accept(adressCgVisitor, o);
//		i.getExpr().accept(valueCgVisitor, o);
//
//		//char++ || char--
//		if (i.getExpr().getType().suffix() == 'B') {
//			cg.b2i();
//			cg.push(1);
//			cg.alter(i.getOperator(),IntType.getInstance());
//			cg.i2b();
//			cg.store(CharType.getInstance());
//		} else {
//			cg.push(1);
//			cg.convert(IntType.getInstance(), i.getExpr().getType());
//			cg.alter(i.getOperator(), i.getExpr().getType());
//			cg.store(i.getExpr().getType());
//		}
//
//		return null;
//	}
//
//	@Override
//	public Object visit(AlterAssigVal a, Object o) {
//
//		Type superType = a.getLeft().getType().superType(a.getRight().getType());
//
//		a.getLeft().accept(adressCgVisitor, o);
//		a.getLeft().accept(valueCgVisitor, o);
//		cg.convert(a.getLeft().getType(), superType);
//		a.getRight().accept(valueCgVisitor, o);
//		cg.convert(a.getRight().getType(), superType);
//
//		cg.alterAssig(a.getOperator(), superType);
//
//		if (a.getLeft().getType().suffix() == 'B' && a.getRight().getType().suffix() == 'B') {
//			cg.convert(superType, a.getRight().getType());
//			cg.store(CharType.getInstance());
//		} else {
//			cg.store(superType);
//		}
//
//		return null;
//	}

}
