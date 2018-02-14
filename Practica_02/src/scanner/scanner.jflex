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
ConstanteEntera = [0-9]*
Rubbish = [ \t\n\r]
CommentV1 = #~\n
CommentV2 = \"""~\"""	



%%
// ************  Acciones ********************

// * THINGS TO IGNORE

{Rubbish}			{}
{CommentV1}			{}
{CommentV2}			{}

// * RESERVATED WORDS

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
					

 
// * Constants

{ConstanteEntera}	{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }
  			  
         		
		  
// * Other
.			{ System.err.println ("Lexical error at line " 	+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'."); }		
				
			
			