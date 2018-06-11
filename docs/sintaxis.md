# Sintaxis

```
Un programa es una secuencia de variables y definiciones de funciones.

La sintaxis de la definición de una variable es una lista, con al menos un elemento, 
de identificadores separados por comas seguido de ':' y un tipo. 
Las definiciones de variables terminan mediante el caracter ';'

Las funciones se definen mediante la palabra reservada "def" el identificador 
de la función, una lista de parametros separados por comas entre '(' y ')' seguido
 de ':' y el tipo de retorno o la palabra reservada "void". 
El tipo de retorno y de los parámetros debe ser de tipo simple (no se permiten 
ni arrays ni registros). El cuerpo de la funcion va entre '{' y '}'.
El cuerpo de las funciones son secuencias de definiciones de variables seguidos 
por una secuencia de sentencias. En ambos casos terminan con el caracter ';'
La funcion "main" debe estar la última, retornando void y sin parámetros.
Los tipos simples son "int", "double" y "char".

Los arrays se pueden crear con el constructor de tipos "[]", especificando el 
tamaño con un entero (como en C) seguido de cualquier tipo.

Se usa el constructor de tipos "struct" para la creacion de registros. 
Los registros no tienen identificador, y los campos son declarados como variables 
dentro de '{' '}'. No se permite la definicion de tipos (p.e. typedef).


Una escritura es la palabra reservada "print" seguida de una lista de expresiones
 separadas por comas.
Una lectura es la palabra reservada "input" seguida de una lista de expresiones 
separadas por comas.
Una asignación está compuesta por dos expresiones separadas por el operador '='.
La sentencia condicional "if"-"else" y la sentencia iterativa "while" siguen la 
sintaxis del lenguaje Python (sin parentesis y con ':' despues de la condicion).
Se permite la sentencia 'return' <expresion> (la expresion es obligatoria).
La invocación a una función será una nueva expresión cuando retorne un valor 
(distinto de void). Un procedimiento (retorno void) será siempre sentencia.
La conversión explícita a tipos simples (cast) se realizará utilizando la sintaxis
 del lenguaje C. 
Las expresiones están formadas por:
- Constantes enteras, reales y caracter sin signo.
- Identificadores
- Los siguientes operadores aplicados a una o dos expresiones (por orden de precedencia descendente):
		( )			Non associative
		[]			Non associative
 		.			Left associative
        	CAST     		Non associative
		- (unary)		Non associative
      		!			Non associative
		* / %			Left associative
		+ -			Left associative
		++ --			Left associative			
	    += -= *= /=			Left associative
	> >= < <= != ==	Left associative
		&& ||		Left associative
		= 			Right associative
		
```		
