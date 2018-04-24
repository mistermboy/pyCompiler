package codegeneration;

import java.io.FileWriter;
import java.io.IOException;

import tipo.Type;

public class CodeGenerator {

	private FileWriter out;

	public CodeGenerator(String entrada, String salida) {
		// TODO Auto-generated constructor stub
	}

	public void pushb() {

	}

	public void pusha(int n) throws IOException {
		out.write("PUSHA" + n);
		out.flush();
	}

	public void push(int n) throws IOException {
		out.write("PUSH" + n);
		out.flush();
	}

	public void push(double n) throws IOException {
		out.write("PUSHF" + n);
		out.flush();
	}

	public void push(char n) throws IOException {
		out.write("PUSHB" + n);
		out.flush();
	}

	public void push(Type type) throws IOException {
		out.write("LOAD" + type.suffix());
		out.flush();
	}

}
