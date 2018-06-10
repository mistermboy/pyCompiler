package py.tipo;

import java.util.List;

import py.ast.ASTNode;
import py.ast.RecordField;

public interface Type extends ASTNode {

	boolean isLogical();

	Type arithmetic(Type type);

	Type arithmetic();

	Type comparison(Type type);

	Type logical(Type type);

	Type logical();

	Type dot(String s);

	Type squareBrackets(Type type);

	Type canBeCast(Type type);

	Type promotesTo(Type type);

	Type parentesis(List<Type> types);

	boolean isBuildingType();

	int numberOfBytes();

	char suffix();

	Type superType(Type type);

	public RecordField get(String identificador);
}
