package py.visitor;

import py.ast.FunDefinition;
import py.ast.Statement;
import py.ast.VarDefinition;
import py.ast.Variable;
import py.symboltable.SymbolTable;
import py.tipo.ErrorType;

public class IdentificationVisitor extends AbstractVisitor {

	private SymbolTable symboltable;

	public IdentificationVisitor() {
		symboltable = new SymbolTable();
	}

	@Override
	public Object visit(Variable v, Object object) {
		if (symboltable.find(v.getNameString()) == null) {
			new ErrorType(v,
					"ERROR: No puedes utilizar una variable que no ha sido previamente definida en " + v.toString());
		}
		v.setVarDefinition(symboltable.find(v.getNameString()));
		return null;
	}

	@Override
	public Object visit(FunDefinition funDefinition, Object o) {
		if (!symboltable.insert(funDefinition)) {
			new ErrorType(funDefinition, "ERROR: No puedes definir una función que ya ha sido previamente definida en "
					+ funDefinition.getName().toString());
		}

		symboltable.set();
		funDefinition.getType().accept(this, o);
		if (funDefinition.getStatements() != null) {
			for (Statement statement : funDefinition.getStatements()) {
				statement.accept(this, o);
			}
		}
		symboltable.reset();
		return null;

	}

	@Override
	public Object visit(VarDefinition varDefinition, Object o) {
		if (!symboltable.insert(varDefinition)) {
			new ErrorType(varDefinition, "ERROR: No puedes definir una variable que ya ha sido previamente definida en "
					+ varDefinition.toString());
		}
		varDefinition.getType().accept(this, o);
		return null;
	}

}
