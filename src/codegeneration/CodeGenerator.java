package codegeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
		out.println("PUSH BP");
		out.flush();
	}

	public void load(Type type) {
		out.println("LOAD" + type.suffix());
		out.flush();
	}

	public void pusha(int n) {
		out.println("PUSHA " + n);
		out.flush();
	}

	public void push(int n) {
		out.println("PUSHI " + n);
		out.flush();
	}

	public void push(double n) {
		out.println("PUSHF " + n);
		out.flush();
	}

	public void push(char n) {
		out.println("PUSHB " + (int) n);
		out.flush();
	}

	public void halt() {
		out.println("HALT");
		out.flush();
	}

	public void out(Type type) {
		out.println("OUT" + type.suffix());
		out.flush();
	}

	public void store(Type type) {
		out.println("STORE" + type.suffix());
		out.flush();

	}

	public void call(String nameString) {
		out.println("CALL " + nameString);
		out.flush();

	}

	public void convert(Type type, Type type2) {
		out.println(type.suffix() + "2" + type2.suffix());
		out.flush();
	}

	public void aritmetic(String operator, Type type) {
		String op = "";
		if (operator.equals("+")) {
			op = "ADD";
		}

		if (operator.equals("-")) {
			op = "SUB";
		}

		if (operator.equals("*")) {
			op = "MUL";
		}

		if (operator.equals("/")) {
			op = "DIV";
		}

		if (operator.equals("%")) {
			op = "MOD";
		}

		out.println(op + "" + type.suffix());

		out.flush();
	}

	public void add(Type type) {
		out.println("ADD" + type.suffix());
		out.flush();
	}

	public void sub(Type type) {
		out.println("SUB" + type.suffix());
		out.flush();
	}

	public void mul(Type type) {
		out.println("MUL" + type.suffix());
		out.flush();
	}

	public void div(Type type) {
		out.println("DIV" + type.suffix());
		out.flush();
	}

	public void jz(int label) {
		out.println("JZ Label" + label);
		out.flush();
	}

	public void jmp(int label) {
		out.println("JMP Label" + label);
		out.flush();
	}

	// public void jnz(int label) {
	// out.println("JNZ Label" + label);
	// out.flush();
	// }

	public void lt() {
		out.println("LT");
		out.flush();
	}

	public void le() {
		out.println("LE");
		out.flush();
	}

	public void gt() {
		out.println("GT");
		out.flush();
	}

	public void ge() {
		out.println("GE");
		out.flush();

	}

	public void etiqueta(String name) {
		out.println(" " + name + ":");
		out.flush();

	}

	public void enter(int locals) {
		out.println("enter " + locals);
		out.flush();
	}

	public void ret(int ret, int locals, int params) {
		out.println("ret " + ret + "," + locals + "," + params);
		out.flush();

	}

	public void in(Type type) {
		out.println("IN" + type.suffix());
		out.flush();
	}

	public void b2i() {
		out.println("B2I");
		out.flush();
	}

	public void i2f() {
		out.println("I2F");
		out.flush();
	}

	public void f2i() {
		out.println("F2I");
		out.flush();
	}

	public void i2b() {
		out.println("I2B");
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
			op = "AND";
		}

		if (logicalOperator.equals("||")) {
			op = "OR";
		}

		out.println(op);
		out.flush();

	}

	public void comparison(String comparator, Type type) {
		String op = "";
		if (comparator.equals(">")) {
			op = "GT";
		}

		if (comparator.equals("<")) {
			op = "LT";
		}

		if (comparator.equals(">=")) {
			op = "GE";
		}

		if (comparator.equals("<=")) {
			op = "LE";
		}

		if (comparator.equals("==")) {
			op = "EQ";
		}

		if (comparator.equals("!=")) {
			op = "NE";
		}

		out.println(op + "" + type.suffix());
		out.flush();
	}

	public void not() {
		out.println("NOT");
		out.flush();

	}

	public void etiqueta(int num) {
		out.println(" Label" + num + ":");
		out.flush();
	}

	public int getLabels(int i) {
		labelCont += i;
		return labelCont - i;
	}

	public void pop(char suffix) {
		out.println("POP" + suffix);
		out.flush();
	}

}
