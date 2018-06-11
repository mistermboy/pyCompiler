# SYNTAX
```
A program is a sequence of variable and function  definitions.

The syntax of a variable definition is a non-empty enumeration of comma-separated identifiers followed by a ":" and a type.  Variable definitions must end with the ";" character.

Functions are defined using the def keyword, the function identifier and a list of coma-separated parameters between ( and ) followed by ":" and the return type or the "void" keyword. The return type and parameter types must be built-in (i.e., no arrays or records). The function body goes between { and }.

The bodies of functions are sequences of variable definitions followed by sequences of statements. 

Both must end with the ";" character.

The last and mandatory function is "main", which returns void and receives no parameters. Built-in types are "int", "double" and "char". 

Array types can be created with the "[]" type constructor, specifying the size of the array with an integer constant (like C) followed
by any type.

The "struct" type constructor is added for specifying record types. Records have no type identifier, and fields are declared as var definitions between { and }. 

Type definition (i.e., typedef) is not supported.

The syntax of a write statement is the "print" keyword followed by a non-empty comma-separated list of expressions. The read statement
is similar, but using the "input" keyword. 

Assignments are built with two expressions separated by the "=" operator. 

If / else and while statements follow the Python syntax. The statement body goes between { and }. 

The return <expression> statement is also supported (the expression is mandatory). 
	
A function invocation may be an expression or a statement. A procedure invocation is always a statement.

The cast expression follows the C syntax.

Expressions are built with:

- Integer, real and character constants without sign.
- Identifiers.
- The following operators applied to one or two expressions (descending order of precedence):
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
	> >= < <= != ==			Left associative
		&& ||			Left associative
		= 			Right associative
		
```		
