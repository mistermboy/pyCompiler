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

	public void pushbp() {

	}

	public void load(Type type) {
		// TODO Auto-generated method stub

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
		out.println("PUSHB " + n);
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

	public void in(Type type) {
		out.println("HALT" + type.suffix());
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
		// TODO Auto-generated method stub

	}

	public void aritmetic(String operator, Type type) {
		// TODO Auto-generated method stub

	}

	public void etiqueta(String string) {
		// TODO Auto-generated method stub

	}

	public void pushi(int offset) {
		// TODO Auto-generated method stub

	}

	public void add(Type type) {

	}

	public void sub(Type type) {
		// TODO Auto-generated method stub

	}

	public void mul(Type type) {
		// TODO Auto-generated method stub

	}

	public void div(Type type) {
		// TODO Auto-generated method stub

	}

	public void jz(String string) {

	}

	public void jmp(String string) {
		// TODO Auto-generated method stub

	}

	public void lt() {
		// TODO Auto-generated method stub

	}

	public void le() {
		// TODO Auto-generated method stub

	}

	public void gt() {
		// TODO Auto-generated method stub

	}

	public void ge() {
		// TODO Auto-generated method stub

	}

}
