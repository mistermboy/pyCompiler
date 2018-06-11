# GLC (context-free grammar)

```
programa : definiciones DEF MAIN '(' ')'':'VOID '{' body '}';


definiciones: definiciones definicion
	 | /* empty */
	 ;


definicion: def ';'
	| funcion
	;
	
```

### Functions

```
funcion: DEF ID '(' params ')' ':' retorno '{' body '}';

retorno: tipo
	| VOID
	; 

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
	;

par:  ID ':' tipo;
```

### Definitions
```
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
		
campo: ids ':' tipo ';';
```

### Sentences

```
sentencias: sentencia
	| sentencias sentencia
	;


sentencia: PRINT list ';'
		| INPUT list ';'
		| RETURN expresion ';'
		| condicionalSimple
		| condicionalComplejo
		| while
		| asignacion ';'
		| invocacion ';'
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
	| ID '(' args ')'
	;
		
		
list: expresion
	| list ',' expresion
	;
	
asignacion: expresion '=' expresion ;

invocacion: ID '(' args ')'


### While

while: WHILE expresion ':' '{' sentencias '}' ;

### IF-ELSE  


condicionalSimple: IF expresion ':' cuerpo;
condicionalComplejo: IF expresion ':' cuerpo else;

else: ELSE cuerpo ;

cuerpo: sentencia
	| '{' sentencias '}'
	;
		
```

### Function invocation
```
args:  /* empty */
	| arg
	;

arg: expresion
   | arg ',' expresion
   ;
```   
