package codegeneration;

import java.io.FileWriter;
import java.io.IOException;

import tipo.Type;

public class CodeGenerator {

	private FileWriter out;
	private int contadorEtiquetas = 1;

	public CodeGenerator(String entrada, String salida) {
		// TODO Auto-generated constructor stub
	}

	public void pushbp() {

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

	public void add(Type type) {
		// TODO Auto-generated method stub

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

	public void incrementaContador() {
		this.contadorEtiquetas++;
	}

	public void etiqueta(String string) {
		// TODO Auto-generated method stub
		
	}
}
