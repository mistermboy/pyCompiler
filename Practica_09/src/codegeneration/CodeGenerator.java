package codegeneration;

import java.io.FileWriter;
import java.io.IOException;

import tipo.Type;

public class CodeGenerator {

	private FileWriter out;

	public CodeGenerator(String entrada, String salida) {
		// TODO Auto-generated constructor stub
	}

	public void pushbp() {

	}

	public void load(Type type) {
		// TODO Auto-generated method stub

	}

	public void pusha(int n) {
		// out.write("PUSHA" + n);
		// out.flush();
	}

	public void push(int n) {
		// out.write("PUSH" + n);
		// out.flush();
	}

	public void push(double n) {
		// out.write("PUSHF" + n);
		// out.flush();
	}

	public void push(char n) {
		// out.write("PUSHB" + n);
		// out.flush();
	}

	public void callMain() {
		// TODO Auto-generated method stub

	}

	public void halt() {
		// TODO Auto-generated method stub

	}

	public void out(Type type) {
		// TODO Auto-generated method stub

	}

	public void in(Type type) {
		// TODO Auto-generated method stub

	}

	public void store(Type type) {
		// TODO Auto-generated method stub

	}

	public void call(String nameString) {
		// TODO Auto-generated method stub

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

	// public void add(Type type) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void sub(Type type) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void mul(Type type) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void div(Type type) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void jz(String string) {
	//
	// }
	//
	// public void jmp(String string) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void lt() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void le() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void gt() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// public void ge() {
	// // TODO Auto-generated method stub
	//
	// }

}
