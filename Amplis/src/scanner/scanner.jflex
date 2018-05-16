// ************  Código a incluir ********************

package scanner;
import parser.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************

// * Para acceder al número de línea (yyline es package)
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}

%}

// ************  Patrones (macros) ********************

Rubbish = [ \t\n\r]
CommentV1 = #.*
CommentV2 = \"""~\"""

Letter = [a-zA-Z]
Digit  = [0-9]
Ident = [_a-zA-Z][a-zA-Z_0-9]*

IntConstant = [0-9]*


RealType = [0-9]+[.][0-9]* | [.][0-9]+
RealConstant = {RealType}|{RealType}E[+|-][0-9]+|[0-9]+e[+|-][0-9]+


Character = \'.\'
CharacterASCII = [']\\[0-9]*[']




%%
// ************  Acciones ********************

// * THINGS TO IGNORE

{Rubbish}			{}
{CommentV1}			{}
{CommentV2}			{}

// * RESERVED WORDS

input				{this.yylval = yytext();
						return Parser.INPUT;}
print				{this.yylval = yytext();
						return Parser.PRINT;}
def					{this.yylval = yytext();
						return Parser.DEF;}
while				{this.yylval = yytext();
						return Parser.WHILE;}
if					{this.yylval = yytext();
						return Parser.IF;}
else				{this.yylval = yytext();
						return Parser.ELSE;}
int					{this.yylval = yytext();
						return Parser.INT;}
double				{this.yylval = yytext();
						return Parser.DOUBLE;}
char				{this.yylval = yytext();
						return Parser.CHAR;}
struct				{this.yylval = yytext();
						return Parser.STRUCT;}
return				{this.yylval = yytext();
						return Parser.RETURN;}
void				{this.yylval = yytext();
						return Parser.VOID;}
main				{this.yylval = yytext();
						return Parser.MAIN;}	
																
// * OPERATORS		

"+" 					{this.yylval = yytext();
							return '+';}
"-" 					{this.yylval = yytext();
							return '-';}
"*" 					{this.yylval = yytext();
							return '*';}
"/" 					{this.yylval = yytext();
							return '/';}
"%" 					{this.yylval = yytext();
							return '%';}
">" 					{this.yylval = yytext();
							return '>';}
"<" 					{this.yylval = yytext();
							return '<';}
">=" 					{this.yylval = yytext();
							return Parser.GREATER;}	
"<=" 					{this.yylval = yytext();
							return Parser.SMALLER;}				
"=" 					{this.yylval = yytext();
							return '=';}	
== 						{this.yylval = yytext();
							return Parser.EQUALS;}
"!=" 					{this.yylval = yytext();
							return Parser.NEGATION;}			
"!" 					{this.yylval = yytext();
							return '!';}
"||" 					{this.yylval = yytext();
							return Parser.OR;}
"&&" 					{this.yylval = yytext();
							return Parser.AND;}							

"++"					{this.yylval = yytext();
						return Parser.INCREMENT;}
																								
"--"					{this.yylval = yytext();
						return Parser.DECREMENT;}
						
"+="					{this.yylval = yytext();
						return Parser.INCREMENT_ASSIGMENT;}
"-="					{this.yylval = yytext();
						return Parser.DECREMENT_ASSIGMENT;}
"*="					{this.yylval = yytext();
						return Parser.MUL_ASSIGMENT;}
"/="					{this.yylval = yytext();
						return Parser.DIV_ASSIGMENT;}																																																												
 
// * CONSTANTS

{IntConstant}	{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }


{RealConstant}	{ this.yylval = new Double(yytext());
         			  return Parser.REAL_CONSTANT;  }

         			  
{Ident}			{ this.yylval = yytext();
         			  return Parser.ID;  }    
         			  
'\\n'			{ this.yylval = new Character('\n');
         			  return Parser.CHAR_CONSTANT;  }
         			  
'\\t'			{ this.yylval = new Character('\t');
         			  return Parser.CHAR_CONSTANT;  }
         			  
{CharacterASCII} 	{  this.yylval = (char) Integer.parseInt(yytext().replace("'","").replace("\\",""));
         			  return Parser.CHAR_CONSTANT;  }
         			        			  
{Character}			{ this.yylval = (char) yytext().charAt(1);
         			  return Parser.CHAR_CONSTANT;  }                    			          			    		
 
// * DELIMITERS 

"{"						{  this.yylval =  yytext();
         			  		return '{'; }    
"}"						{  this.yylval =  yytext();
         			  		return '}'; }
"(" 					{this.yylval = yytext();
							return '(';}
")" 					{this.yylval = yytext();
							return ')';}
"[" 					{this.yylval = yytext();
							return '[';}
"]" 					{this.yylval = yytext();
							return ']';}		  			  
";"						{  this.yylval =  yytext();
         			  		return ';'; }    
","						{  this.yylval =  yytext();
         			  		return ','; }
":"						{  this.yylval =  yytext();
         			  		return ':'; }    
"."						{  this.yylval =  yytext();
         			  		return '.'; }           			  		    
// * Other
.			{ System.err.println ("Lexical error at line " 	+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'."); }		
				
			
			