%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import scanner.Scanner;
import java.io.Reader;

import ast.*;
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

%nonassoc CUERPO
%nonassoc ELSE



%%
// * Gramática y acciones Yacc

programa : definiciones DEF MAIN '(' ')'':'VOID '{' body '}';		{ ast = new Program(0,0,(List<Definition>) $1);}

definiciones: definiciones definicion 								
	 | /* empty */
	 ;


definicion: def ';'													
			| funcion										
			;

// *********  FUNCIONES  *********

funcion: DEF ID '(' params ')' ':' tipo '{' body '}'; //	************** NIPU	{ $$ = new FunDefinition(scanner.getLine(),scanner.getColumn(), (String) $1,(Type) $7);}


body: defs
	| sentencias
	| defs sentencias
	|
	;


params:  /* empty */
		| param
		;

param: par
	| param ',' par

par:  ID ':' tipo;


// *********  DEFINICIONES  *********

defs: def ';'
	| defs def ';'
	;
				
	
def: ids ':' tipo												{ $$ = new VarDefinition(scanner.getLine(),scanner.getColumn(), (String) $1, (Type) $3);}


ids: ID
	| ids ',' ID
	;
								   
tipo: INT 														{ $$ = IntType.getInstance();}
	| DOUBLE 													{ $$ = RealType.getInstance();}
	| CHAR														{ $$ = CharType.getInstance();}
	|'['INT_CONSTANT']' tipo									{ $$ = new ArrayType(scanner.getLine(),scanner.getColumn(),(int) $2, (Type) $4);} 
	| STRUCT '{' campos '}'										{ $$ = new RecordType(scanner.getLine(),scanner.getColumn(),(List<RecordField>)$3);}
	| VOID														{ $$ = VoidType.getInstance();}
	;


campos: campo
		|campos campo 
		;
		
campo:ids ':' tipo ';';	

// *********  SENTENCIAS  *********

sentencias: sentencia
		| sentencias sentencia
		;


sentencia: PRINT list ';'										// ********************** COMO LE PASO VARIAS EXPRESIONES?????
		| INPUT list ';'	
		| RETURN expresion ';'									{ $$ = new Return(scanner.getLine(),scanner.getColumn(),(Expression) $2);}
		| condicionalSimple
		| condicionalComplejo
		| while
		| expresion ';'
		;
	

expresion: ID 
		| INT_CONSTANT
		| CHAR_CONSTANT
		| REAL_CONSTANT
		| '(' expresion ')'
		| expresion '[' expresion ']'
		|  expresion '.' ID
		| '(' tipo ')' expresion  %prec CAST
		| '-' expresion %prec UNARIO
		| '!' expresion
		|  expresion '*' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		|  expresion '/' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		|  expresion '%' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		|  expresion '+' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		|  expresion '-' expresion	 							{ $$ = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion '>' expresion	 							{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion GREATER expresion 							{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion '<' expresion								{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion SMALLER expresion							{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion NEGATION expresion							{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion EQUALS expresion							{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion AND expresion								{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion OR expresion								{ $$ = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) $1,(String)$2,(Arithmetic)$3);}
		| expresion '=' expresion
		| ID '(' args ')'
		;
		
		
list: expresion
	| list ',' expresion
	;


// *********  WHILE  *********

while: WHILE expresion ':' '{' sentencias '}' ;

// *********  IF-ELSE  *********


condicionalSimple: IF expresion ':' cuerpo %prec CUERPO; 		{ $$ = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) $4,null,(Expression) $2);}
condicionalComplejo: IF expresion ':' cuerpo else;			//	{ $$ = new IfStatement(scanner.getLine(),scanner.getColumn(),$4,,(Expression) $2);}  // *******************  COMO COÑO LE PASO EL CUERPO DEL ELSE??????

else: ELSE cuerpo ;

cuerpo: cuerpoSimple
		| cuerpoComplejo  
		;

cuerpoSimple: sentencia;
cuerpoComplejo: '{' sentencias '}' 
		
		
// *********  INVOCACIÓN DE FUNCIONES  *********

args:  /* empty */
		| arg
		;

arg: expresion
	| arg ',' expresion


		         
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
