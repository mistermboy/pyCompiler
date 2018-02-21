%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import scanner.Scanner;
import java.io.Reader;
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
%left OR
%left AND

%left EQUALS
%left NEGATION
%left SMALLER
%left '<'
%left GREATER
%left '>'

%left '-'
%left '+'
%left '%'
%left '/'
%left '*'

%nonassoc CAST
%right UNARIO
%nonassoc '!'
%left '.'
%nonassoc '[' ']'
%nonassoc '(' ')'



%%
// * Gramática y acciones Yacc

programa : definiciones DEF MAIN '(' ')'':'VOID '{' '}';

definiciones: definiciones definicion 
	 | /* empty */
	 ;


definicion: def ';'
			| funcion
			;

// *********  FUNCIONES  *********

funcion: DEF ID '(' params ')' ':' retorno '{' body'}';


body: defs
	| sentencias
	| defs sentencias
	|
	;

retorno:  tipo 
		|  VOID
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
				
	
def: ids ':' tipo


ids: ID
	| ids ',' ID
	;
								   
tipo: INT 
	| DOUBLE 
	| CHAR
	|'['INT_CONSTANT']' tipo
	| STRUCT '{' campos '}'
	;


campos: campo
		|campos campo 
		;
		
campo:ids ':' tipo ';';	

// *********  SENTENCIAS  *********

sentencias: sentencia
		| sentencias sentencia;


sentencia: PRINT list ';'	
		| INPUT list ';'	
		| RETURN expresion ';'	
		| asignacion ';'	
		| condicionales
		| while
		| invocacion ';'
		;
	

expresion: ID 
		| INT_CONSTANT
		| CHAR_CONSTANT
		| REAL_CONSTANT
		| '(' expresion ')'
		| expresion '[' expresion ']'
		|  expresion '.' expresion
		| '(' tipo ')' expresion %prec CAST
		| '-' expresion %prec UNARIO
		| '!' expresion
		|  expresion '*' expresion
		|  expresion '/' expresion
		|  expresion '%' expresion
		|  expresion '+' expresion
		|  expresion '-' expresion
		| expresion '>' expresion
		| expresion GREATER expresion
		| expresion '<' expresion
		| expresion SMALLER expresion
		| expresion NEGATION expresion
		| expresion EQUALS expresion
		| expresion AND expresion
		| expresion OR expresion
		;
		
		
list: expresion
	| list ',' expresion
	;

// *********  ASINACIÓN  *********

asignacion: expresion '=' expresion	

// *********  WHILE  *********

while: WHILE expresion ':' '{' sentencias '}' ;

// *********  IF-ELSE  *********

condicionales: IF expresion ':' cuerpo else;

else: /*empty*/
	| ELSE cuerpo;


cuerpo: '{' sentencias '}' 
		// | sentencia  IMPLEMENTAR PARA CUANDO NO TENGA LLAVES
		;
		
// *********  INVOCACIÓN DE FUNCIONES  *********

invocacion: ID '(' args ')'

args:  /* empty */
		| arg
		;

arg: ID
	| arg ',' ID

		         
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
