package py.symboltable;

import java.util.*;

import py.ast.Definition;

public class SymbolTable {

	private int scope = 0;
	private List<Map<String, Definition>> table;

	public SymbolTable() {
		table = new ArrayList<Map<String, Definition>>();
		table.add(new HashMap<String, Definition>());
	}

	public void set() {
		table.add(new HashMap<String, Definition>());
		this.scope++;
	}

	public void reset() {
		table.remove(this.scope);
		this.scope--;
	}

	public boolean insert(Definition definition) {
		if (definition != null && findInCurrentScope(definition.getName()) == null) {
			this.table.get(this.scope).put(definition.getName(), definition);
			definition.setScope(this.scope);
			return true;
		}
		return false;
	}

	public Definition find(String id) {
		for (int i = this.scope; i >= 0; i--) {
			if (this.table.get(i).containsKey(id)) {
				return this.table.get(i).get(id);
			}
		}
		return null;
	}

	public Definition findInCurrentScope(String id) {
		return this.table.get(this.scope).get(id);
	}
}
