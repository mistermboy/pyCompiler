%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import scanner.Scanner;
import java.io.Reader;

import ast.*;
import tipo.*;
import java.util.*;

%}

// * Declaraciones Yacc
%token INT_CONSTANT
%token INPUT
%token PRINT
%token DEF
%token WHILE
%token IF
%token ELSE
%token INT
%token DOUBLE
%token CHAR
%token STRUCT
%token RETURN
%token VOID
%token ID
%token REAL_CONSTANT
%token CHAR_CONSTANT
%token GREATER
%token SMALLER
%token EQUALS
%token NEGATION
%token MAIN
%token OR
%token AND
%token INCREMENT
%token DECREMENT
%token INCREMENT_ASSIGMENT
%token DECREMENT_ASSIGMENT	
%token MUL_ASSIGMENT
%token DIV_ASSIGMENT

%right '='
%left OR AND

%left EQUALS NEGATION SMALLER '<' GREATER '>'

%left '-' '+'
%left '*' '/' '%'

%nonassoc CAST
%right UNARIO
%nonassoc '!'
%left '.'
%nonassoc '[' ']'
%nonassoc '(' ')'

%nonassoc ':'
%nonassoc ELSE



%%
// * Gramática y acciones Yacc

programa : definiciones DEF MAIN '(' ')'':'VOID '{' body '}';	{ ast = new Program(0,0,(List<Definition>) $1,(List<Statement>) $9);}

definiciones: definiciones definicion 							{List<Definition> defs = (List<Definition>)$1;List<Definition> def = (List<Definition>)$2;for(Definition d:def){defs.add(d);}$$=defs;} 	
	 | /* empty */												{$$ = new ArrayList<Definition>();}
	 ;


definicion: def ';'												{$$ = $1;}		
			| funcion											{List<FunDefinition> fd = new ArrayList<FunDefinition>(); fd.add((FunDefinition) $1);$$=fd;} 				
			;

// *********  FUNCIONES  *********

funcion: DEF ID '(' params ')' ':' retorno '{' body '}';     	{ FunctionType ft = new FunctionType(scanner.getLine(),scanner.getColumn(),(Type) $7,(List<VarDefinition>)$4);$$ = new FunDefinition(scanner.getLine(),scanner.getColumn(), (String) $2,ft,(List<Statement>) $9);}

retorno: tipo 													{ $$ = $1;}
		| VOID 													{ $$ = VoidType.getInstance();}
		; 											



body: defs														{ $$ = $1;}
	| sentencias												{ $$ = $1;}
	| defs sentencias											{ List<Statement> st = (List<Statement>) $1;List<Statement> sent = (List<Statement>) $2;for(Statement s: sent){st.add(s);}$$=st;}	
	|															{ $$ = new ArrayList<Statement>();}
	;


params:  /* empty */											{ $$ = new ArrayList<VarDefinition>();}
		| param													{ $$ = $1;}
		;

param: par														{ List<VarDefinition> par = new ArrayList<VarDefinition>();par.add((VarDefinition)$1);$$=par;}
	| param ',' par												{ List<VarDefinition> pars = (List<VarDefinition>) $1;pars.add((VarDefinition)$3);$$=pars;}

par:  ID ':' tipo;												{ $$ = new VarDefinition(scanner.getLine(),scanner.getColumn(),(String) $1, (Type) $3);}


// *********  DEFINICIONES  *********

defs: def ';'													{ $$ = $1;}
	| defs def ';'												{ List<Definition> defs = (List<Definition>)$1; List<VarDefinition> def = (List<VarDefinition>) $2; for(VarDefinition var:def){defs.add(var);}$$=defs;}
	;
				
	
def: ids ':' tipo												{ List<String> ids = (List<String>) $1; List<VarDefinition> def = new ArrayList<VarDefinition>();for(String id:ids){def.add(new VarDefinition(scanner.getLine(),scanner.getColumn(),id, (Type) $3));}$$=def;}


ids: ID															{ List<String> ids = new ArrayList<String>(); ids.add((String) $1); $$=ids;}
	| ids ',' ID												{ List<String> ids = (List<String>) $1; if(!ids.contains((String) $3)){ids.add((String) $3);}else{new ErrorType(scanner.getLine(),scanner.getColumn(),"No puedes definir dos variables con el mismo nombre");}$$=ids;}
	;
								   
tipo: INT 														{ $$ = IntType.getInstance();}
	| DOUBLE 													{ $$ = RealType.getInstance();}
	| CHAR														{ $$ = CharType.getInstance();}
	|'['INT_CONSTANT']' tipo									{ $$ = new ArrayType(scanner.getLine(),scanner.getColumn(),(int) $2, (Type) $4);} 
	| STRUCT '{' campos '}'										{ $$ = new RecordType(scanner.getLine(),scanner.getColumn(),(List<RecordField>)$3);}
	;


campos: campo													{$$=$1;}
		|campos campo 											{List<RecordField> camps = (List<RecordField>)$1; List<RecordField> def = (List<RecordField>) $2;for(RecordField r:def){if(camps.contains(r)){r.setType(new ErrorType(r,"No puedes definir dos variables con el mismo nombre dentro de un struct"));}else{camps.add(r);}}$$=camps;}
		;
		
campo: ids ':' tipo ';';										{ List<String> ids = (List<String>) $1;List<RecordField> def = new ArrayList<RecordField>();for(String id:ids){def.add(new RecordField(scanner.getLine(),scanner.getColumn(),id,(Type) $3,0));}$$=def;}

// *********  SENTENCIAS  *********

sentencias: sentencia											{ $$=$1;}
		| sentencias sentencia									{ List<Statement> states = (List<Statement>)$1;List<Statement> st = (List<Statement>)$2;for(Statement s:st){states.add(s);}$$=states;}
		;


sentencia: PRINT list ';'										{ List<Statement> states = new ArrayList<Statement>();List<Expression> exps = (List<Expression>) $2; for(Expression e:exps){states.add(new Write(scanner.getLine(),scanner.getColumn(),e));}$$=states;}
		| INPUT list ';'										{ List<Statement> states = new ArrayList<Statement>();List<Expression> exps = (List<Expression>) $2; for(Expression e:exps){states.add(new Read(scanner.getLine(),scanner.getColumn(),e));}$$=states;}
		| RETURN expresion ';'									{ List<Statement> states = new ArrayList<Statement>(); Expression e = (Expression) $2;states.add(new Return(scanner.getLine(),scanner.getColumn(),e));$$=states;}
		| condicionalSimple										{ List<Statement> states = new ArrayList<Statement>(); IfStatement ifs = (IfStatement) $1; states.add(ifs);$$=states;}
		| condicionalComplejo									{ List<Statement> states = new ArrayList<Statement>(); IfStatement ifs = (IfStatement) $1; states.add(ifs);$$=states;}
		| while													{ List<Statement> states = new ArrayList<Statement>(); WhileStatement wS = (WhileStatement) $1; states.add(wS);$$=states;}
		| asignacion ';'										{ List<Statement> states = new ArrayList<Statement>(); Assignment aS = (Assignment) $1; states.add(aS);$$=states;}
		| invocacion ';'										{ List<Statement> states = new ArrayList<Statement>(); Invocation iS = (Invocation) $1; states.add(iS);$$=states;}
		| expresion INCREMENT ';'								{ List<Statement> states = new ArrayList<Statement>(); Assignment Ai = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression) $1,new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,new IntLiteral(scanner.getLine(),scanner.getColumn(),1)));states.add(Ai);$$=states;}
		| expresion DECREMENT ';'								{ List<Statement> states = new ArrayList<Statement>(); Assignment Ai = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression) $1,new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,new IntLiteral(scanner.getLine(),scanner.getColumn(),1)));states.add(Ai);$$=states;}
		| expresion INCREMENT_ASSIGMENT expresion ';'			{ List<Statement> states = new ArrayList<Statement>(); Assignment Ai = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression) $1,new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression) $3));states.add(Ai);$$=states;}
		| expresion DECREMENT_ASSIGMENT expresion ';'			{ List<Statement> states = new ArrayList<Statement>(); Assignment Ai = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression) $1,new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression) $3));states.add(Ai);$$=states;}
		| expresion MUL_ASSIGMENT expresion ';'					{ List<Statement> states = new ArrayList<Statement>(); Assignment Ai = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression) $1,new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression) $3));states.add(Ai);$$=states;}
		| expresion DIV_ASSIGMENT expresion ';'					{ List<Statement> states = new ArrayList<Statement>(); Assignment Ai = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression) $1,new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression) $3));states.add(Ai);$$=states;}
		;
	

expresion: ID 													{ $$ = new Variable(scanner.getLine(),scanner.getColumn(),(String) $1);}
		| INT_CONSTANT											{ $$ = new IntLiteral(scanner.getLine(),scanner.getColumn(),(int) $1);}
		| CHAR_CONSTANT											{ $$ = new CharLiteral(scanner.getLine(),scanner.getColumn(),(char) $1);}
		| REAL_CONSTANT											{ $$ = new RealLiteral(scanner.getLine(),scanner.getColumn(),(double) $1);}
		| '(' expresion ')'										{ $$ = $2;}
		| expresion '[' expresion ']'							{ $$ = new Indexing(scanner.getLine(),scanner.getColumn(),(Expression)$1,(Expression)$3);}
		|  expresion '.' ID										{ $$ = new FieldAccess(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $3);}
		| '(' tipo ')' expresion  %prec CAST					{ $$ = new Cast(scanner.getLine(),scanner.getColumn(),(Expression) $4,(Type) $2);}
		| '-' expresion %prec UNARIO							{ $$ = new UnaryMinus(scanner.getLine(),scanner.getColumn(),(Expression) $2);}
		| '!' expresion											{ $$ = new UnaryNot(scanner.getLine(),scanner.getColumn(),(Expression) $2);}
		|  expresion '*' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		|  expresion '/' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		|  expresion '%' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		|  expresion '+' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		|  expresion '-' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		| expresion '>' expresion	 							{ $$ = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression)$3);}
		| expresion GREATER expresion 							{ $$ = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression)$3);}
		| expresion '<' expresion								{ $$ = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression)$3);}
		| expresion SMALLER expresion							{ $$ = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression)$3);}
		| expresion NEGATION expresion							{ $$ = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression)$3);}
		| expresion EQUALS expresion							{ $$ = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String) $2,(Expression)$3);}
		| expresion AND expresion								{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		| expresion OR expresion								{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Expression) $1,(String)$2,(Expression)$3);}
		| ID '(' args ')'										{ $$ = new Invocation(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String)$1),(List<Expression>) $3);}
		;
		
		
list: expresion													{ List<Expression> exp = new ArrayList<Expression>();exp.add((Expression)$1);$$=exp;}
	| list ',' expresion										{ List<Expression> exps = (List<Expression>) $1;exps.add((Expression)$3);$$=exps;}
	;
	
asignacion: expresion '=' expresion ;							{ $$ = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression)$1,(Expression)$3);}

invocacion: ID '(' args ')'										{ $$ = new Invocation(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String)$1),(List<Expression>) $3);}


// *********  WHILE  *********

while: WHILE expresion ':' '{' sentencias '}' ;					{ $$ = new WhileStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) $5,(Expression) $2);}

// *********  IF-ELSE  *********


condicionalSimple: IF expresion ':' cuerpo; 		{ $$ = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) $4,null,(Expression) $2);}
condicionalComplejo: IF expresion ':' cuerpo else;				{ $$ = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) $4,(List<Statement>) $5,(Expression) $2);}

else: ELSE cuerpo ;												{ $$=$2;}

cuerpo: sentencia												{ $$=$1;}
		| '{' sentencias '}'   									{ $$=$2;}
		;
		
				
// *********  INVOCACIÓN DE FUNCIONES  *********

args:  /* empty */												{ $$ = new ArrayList<Expression>();} 
		| arg													{ $$ = $1;}
		;

arg: expresion													{ List<Expression> exp = new ArrayList<Expression>();exp.add((Expression)$1);$$=exp;}
	| arg ',' expresion											{ List<Expression> exps = (List<Expression>) $1;exps.add((Expression)$3);$$=exps;}


		         
%%
// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Scanner scanner;


private ASTNode ast;

public ASTNode getAST(){
	return ast;
}


// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex(); 	
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Syntactical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Scanner scanner) {
	this.scanner = scanner;
}
