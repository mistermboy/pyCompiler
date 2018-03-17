package symboltable;

import java.util.*;
import ast.Definition;

public class SymbolTable {
	
	private int scope=0;
	private List<Map<String,Definition>> table;
	public SymbolTable()  {
	}

	public void set() {
	}
	
	public void reset() {
	}
	
	public boolean insert(Definition definition) {
	}
	
	public Definition find(String id) {
	}

	public Definition findInCurrentScope(String id) {
	}
}
