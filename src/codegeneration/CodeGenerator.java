package codegeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import ast.Definition;
import ast.VarDefinition;
import tipo.CharType;
import tipo.IntType;
import tipo.RealType;
import tipo.Type;

public class CodeGenerator {

	private PrintWriter out;
	private int labelCont;

	public CodeGenerator(String entrada, String salida) {

		try {
			out = new PrintWriter(new File(salida));
			labelCont = 0;
			// thi.out = system.out;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void pushbp() {
		out.println("\tPUSH BP");
		out.flush();
	}

	public void load(Type type) {
		out.println("\tLOAD" + type.suffix());
		out.flush();
	}

	public void pusha(int n) {
		out.println("\tPUSHA " + n);
		out.flush();
	}

	public void push(int n) {
		out.println("\tPUSHI " + n);
		out.flush();
	}

	public void push(double n) {
		out.println("\tPUSHF " + n);
		out.flush();
	}

	public void push(char n) {
		out.println("\tPUSHB " + (int) n);
		out.flush();
	}

	public void halt() {
		out.println("\tHALT");
		out.flush();
	}

	public void out(Type type) {
		out.println("\tOUT" + type.suffix());
		out.flush();
	}

	public void store(Type type) {
		out.println("\tSTORE" + type.suffix());
		out.flush();

	}

	public void call(String nameString) {
		out.println("\tCALL " + nameString);
		out.flush();

	}

	public void aritmetic(String operator, Type type) {
		String op = "";
		if (operator.equals("+") || operator.equals("++") || operator.equals("+=") ) {
			op = "\tADD";
		}

		if (operator.equals("-") || operator.equals("--") || operator.equals("-=") ) {
			op = "\tSUB";
		}

		if (operator.equals("*") || operator.equals("*=")) {
			op = "\tMUL";
		}

		if (operator.equals("/") || operator.equals("/=")) {
			op = "\tDIV";
		}

		if (operator.equals("%")) {
			op = "\tMOD";
		}

		out.println(op + "" + type.suffix());

		out.flush();
	}

	public void add(Type type) {
		out.println("\tADD" + type.suffix());
		out.flush();
	}

	public void sub(Type type) {
		out.println("\tSUB" + type.suffix());
		out.flush();
	}

	public void mul(Type type) {
		out.println("\tMUL" + type.suffix());
		out.flush();
	}

	public void div(Type type) {
		out.println("\tDIV" + type.suffix());
		out.flush();
	}

	public void jz(int label) {
		out.println("\tJZ Label" + label);
		out.flush();
	}

	public void jmp(int label) {
		out.println("\tJMP Label" + label);
		out.flush();
	}

	public void jnz(int label) {
		out.println("\tJNZ Label" + label);
		out.flush();
	}

	public void etiqueta(String name) {
		out.println(" " + name + ":");
		out.flush();

	}

	public void enter(int locals) {
		out.println("\tenter " + locals);
		out.flush();
	}

	public void ret(int ret, int locals, int params) {
		out.println("\tret " + ret + "," + locals + "," + params);
		out.flush();

	}

	public void in(Type type) {
		out.println("\tIN" + type.suffix());
		out.flush();
	}

	public void b2i() {
		out.println("\tB2I");
		out.flush();
	}

	public void i2f() {
		out.println("\tI2F");
		out.flush();
	}

	public void f2i() {
		out.println("\tF2I");
		out.flush();
	}

	public void i2b() {
		out.println("\tI2B");
		out.flush();
	}

	public void cast(Type type, Type castType) {
		if (type.equals(CharType.getInstance()) && castType.equals(IntType.getInstance())) {
			b2i();
		}

		if (type.equals(IntType.getInstance()) && castType.equals(RealType.getInstance())) {
			i2f();
		}

		if (type.equals(RealType.getInstance()) && castType.equals(IntType.getInstance())) {
			f2i();
		}

		if (type.equals(IntType.getInstance()) && castType.equals(CharType.getInstance())) {
			i2b();
		}

		if (type.equals(CharType.getInstance()) && castType.equals(RealType.getInstance())) {
			b2i();
			i2f();
		}

		if (type.equals(RealType.getInstance()) && castType.equals(CharType.getInstance())) {
			f2i();
			i2b();
		}

	}

	public void logic(String logicalOperator) {
		String op = "";
		if (logicalOperator.equals("&&")) {
			op = "\tAND";
		}

		if (logicalOperator.equals("||")) {
			op = "\tOR";
		}

		out.println(op);
		out.flush();

	}

	public void comparison(String comparator, Type type) {
		String op = "";
		if (comparator.equals(">")) {
			op = "\tGT";
		}

		if (comparator.equals("<")) {
			op = "\tLT";
		}

		if (comparator.equals(">=")) {
			op = "\tGE";
		}

		if (comparator.equals("<=")) {
			op = "\tLE";
		}

		if (comparator.equals("==")) {
			op = "\tEQ";
		}

		if (comparator.equals("!=")) {
			op = "\tNE";
		}

		out.println(op + "" + type.suffix());
		out.flush();
	}

	public void not() {
		out.println("\tNOT");
		out.flush();

	}

	public void label(int num) {
		out.println("Label" + num + ":");
		out.flush();
	}

	public int getLabels(int i) {
		labelCont += i;
		return labelCont - i;
	}

	public void pop(char suffix) {
		out.println("\tPOP" + suffix);
		out.flush();
	}

	public void convert(Type t1, Type t2) {
		switch (t1.suffix()) {
		case 'I':
			if (t2.suffix() == 'B') {
				i2b();
			} else if (t2.suffix() == 'F') {
				i2f();
			}
			break;
		case 'B':
			if (t2.suffix() == 'F') {
				b2i();
				i2f();
			} else if (t2.suffix() == 'I') {
				b2i();
			}
			break;
		}
	}

	public void alter(String operator, Type type) {
		String op = "";
		if (operator.equals("++")) {
			op = "\tADD";
		}

		if (operator.equals("--")) {
			op = "\tSUB";
		}

		out.println(op + type.suffix());
		out.flush();

	}

	public void alterAssig(String operator, Type superType) {
		String op = "";
		if (operator.equals("+=")) {
			op = "\tADD";
		}

		if (operator.equals("-=")) {
			op = "\tSUB";
		}

		out.println(op + superType.suffix());
		out.flush();
	}

	// ####################### COMMENTS ############################

	public void varComment(Definition d) {
		out.println("\t' * " + d.getType() + " " + d.getName() + " (offset " + ((VarDefinition) d).getOffset() + ")");
		out.flush();
	}

	public void paramComment() {
		out.println("\t' * Parameters");
		out.flush();
	}

	public void localComment() {
		out.println("\t' * Local variables");
		out.flush();
	}

	public void functionBodyComment() {
		out.println("\t' * Function body");
		out.flush();
	}

	public void mainComment() {
		out.println(" ' Invocation to the main function");
		out.flush();
	}

	public void sourceComment(String constante) {
		out.println("#source \"" + constante + "\"");
		out.println();
		out.flush();
	}

	public void lineComment(int num) {
		out.println();
		out.println("#line\t" + num);
		out.println();
		out.flush();
	}

	public void white() {
		out.println();
		out.flush();
	}

}
