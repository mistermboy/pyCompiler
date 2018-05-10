package codegeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import tipo.Type;

public class CodeGenerator {

	private PrintWriter out;

	public CodeGenerator(String entrada, String salida) {

		try {
			out = new PrintWriter(new File(salida));
			// thi.out = system.out;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void pushabp() {
		out.println("PUSHA BP");
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
		out.println("PUSH " + n);
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
		out.println("STORE");
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
		if (operator == "+") {
			op = "ADD";
		}

		if (operator == "-") {
			op = "SUB";
		}

		if (operator == "*") {
			op = "MUL";
		}

		if (operator == "/") {
			op = "DIV";
		}

		out.println(op + "" + type.suffix());
		out.flush();
	}

	public void etiqueta(int num) {
		out.println(" LABEL " + num + ":");
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

	public void jz(String string) {
		out.println("JZ " + string);
		out.flush();
	}

	public void jmp(String string) {
		out.println("JMP " + string);
		out.flush();
	}

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
		out.println("IN " + type.suffix());
		out.flush();
	}

}
