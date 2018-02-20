//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import scanner.Scanner;
import java.io.Reader;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short INPUT=258;
public final static short PRINT=259;
public final static short DEF=260;
public final static short WHILE=261;
public final static short IF=262;
public final static short ELSE=263;
public final static short INT=264;
public final static short DOUBLE=265;
public final static short CHAR=266;
public final static short STRUCT=267;
public final static short RETURN=268;
public final static short VOID=269;
public final static short ID=270;
public final static short REAL_CONSTANT=271;
public final static short CHAR_CONSTANT=272;
public final static short GREATER=273;
public final static short SMALLER=274;
public final static short EQUALS=275;
public final static short NEGATION=276;
public final static short MAIN=277;
public final static short OR=278;
public final static short AND=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    4,    6,    6,    5,    5,
   10,   10,   11,    7,    7,    3,   12,   12,    9,    9,
    9,    9,    9,   13,   13,   14,    8,    8,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,
};
final static short yylen[] = {                            2,
    9,    2,    0,    2,    1,   11,    1,    1,    0,    1,
    1,    3,    3,    0,    3,    3,    1,    3,    1,    1,
    1,    4,    4,    1,    2,    4,    0,    3,    1,    1,
    1,    1,    3,    2,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   17,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   19,   20,   21,    0,    0,
   16,   18,    0,    0,    0,   11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   24,    0,   13,    0,   12,
    0,    0,   23,   25,   22,    8,    0,    7,    0,    0,
   14,    1,   26,    0,   30,    0,   32,   31,    0,    0,
    0,    0,    0,   29,    0,    0,   15,    6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   33,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   48,   28,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,   24,   47,   54,   62,   21,   25,
   26,    8,   35,   36,   63,
};
final static short yysindex[] = {                         0,
    0, -176, -144,    0,    0,  -46,    0,  -14,  -12,    9,
    0,  -54, -219, -217,   13,    0,    0,    0,  -68, -200,
    0,    0,    8,   40,   38,    0,   27, -173,   17,  -54,
   43, -217, -167,   92,  -99,    0,  -54,    0,   70,    0,
    7,  -54,    0,    0,    0,    0,   29,    0,    3,   52,
    0,    0,    0,  -13,    0,    0,    0,    0,   -4,   -4,
   72,   42,  -28,    0,   -2,   26,    0,    0,   -4,   -4,
   -4,   -4,   -4,   -4,   -4,   -4,   -4,   -4,   -4,   -4,
   -4,   -4,   -4,    0,   85,   16,  -45,  -57,  102,  -43,
   53,   46,   95,  129,  -59,  -49,   11,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  131,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  132,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,    0,  -37,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   -3,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   55,    0,  141,  106,   75,   82,    5,   33,
   20,  128,  223,   62,  341,  350,  117,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  127,    0,    0,    0,    0,  110,  155,    0,
  163,  104,    0,  161,  351,
};
final static int YYTABLESIZE=433;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         29,
   81,   82,   80,   82,   29,   29,   17,   29,   77,   29,
   81,   82,   11,   75,   78,   82,   79,   82,   76,   60,
   17,   29,   29,   29,   29,   43,   59,   14,   60,   13,
   83,   81,   82,   80,   77,   59,   20,   34,   84,   75,
   78,   47,   79,   12,   76,   47,   47,   47,   15,   47,
   22,   47,   23,   27,   28,   34,   29,   81,   82,   80,
   35,   35,   77,   47,   47,   30,   47,   75,   78,   46,
   79,   82,   76,   46,   46,   46,   82,   46,   35,   46,
   31,   32,   77,    3,   33,   81,   82,   80,   78,   77,
   79,   46,   46,    4,   46,   78,    4,   79,   38,   76,
   39,   41,   38,   38,   38,   81,   82,   80,   38,   37,
   53,   45,   81,   82,   80,   45,   45,   45,   44,   45,
   38,   45,   44,   44,   44,    9,   44,   52,   44,   49,
   67,   34,   10,   45,   45,   13,   45,   78,   34,   79,
   44,   44,   43,   44,   81,   82,   43,   43,   43,   42,
   43,   51,   43,   42,   81,   82,   80,   42,   42,   42,
   20,   42,   82,   42,   43,   43,   68,   43,   36,   36,
    4,    9,   10,   79,   36,   42,   42,   41,   42,   27,
   61,   41,   41,   41,   38,   41,   36,   41,   81,   82,
   80,   45,   99,   48,   40,   44,   50,    0,    0,   41,
    0,    0,   41,    0,    0,    0,    0,    0,    0,   16,
   17,   18,   19,   69,   70,   71,   72,   71,   73,   74,
   73,   74,    0,   69,   70,   71,   72,    0,   73,   74,
    0,    0,   73,   74,   73,   29,   29,   29,   29,    0,
   29,   29,    0,   55,   69,   70,   71,   72,    0,   73,
   74,    0,   55,    0,    0,    0,   56,   57,   58,   37,
    0,    0,    0,   37,   37,   64,   57,   58,    0,   37,
   69,   70,   71,   72,    0,   73,   74,   47,   47,   47,
   47,   37,   47,   47,   70,   71,   72,    0,   73,   74,
   71,   72,    0,   73,   74,    0,    0,    0,   69,   70,
   71,   72,    0,   73,   74,   46,   46,   46,   46,    0,
    0,   46,    0,    0,    0,    0,    0,    0,   69,   70,
   71,   72,    0,   73,   74,   69,   70,   71,   72,    0,
   73,   74,    0,   16,   17,   18,   19,    0,   46,    0,
    0,    0,    0,    0,    0,    0,    0,   45,   45,   45,
   45,    0,    0,    0,   44,   44,    0,   44,   70,   71,
   72,    0,   73,   74,    0,    0,    0,   69,   70,   71,
   72,    0,   73,   74,    0,    0,    0,   39,   43,   43,
    0,   39,   39,   39,    0,   39,   40,   39,    0,   42,
   40,   40,   40,    0,   40,    0,   40,    0,    0,   39,
    0,   69,   70,   71,   72,    0,   73,   74,   40,   65,
   66,   40,    0,   41,    0,    0,    0,    0,    0,   85,
   86,   87,   88,   89,   90,   91,   92,   93,   94,   95,
   96,   97,   98,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   60,   61,   62,   61,   42,   43,   44,   45,   37,   47,
   60,   61,   59,   42,   43,   61,   45,   61,   47,   33,
   58,   59,   60,   61,   62,  125,   40,   40,   33,   44,
   59,   60,   61,   62,   37,   40,   91,   41,   41,   42,
   43,   37,   45,   58,   47,   41,   42,   43,   40,   45,
  270,   47,  270,   41,  123,   59,  257,   60,   61,   62,
   41,   42,   37,   59,   60,   58,   62,   42,   43,   37,
   45,   61,   47,   41,   42,   43,   61,   45,   59,   47,
   41,   44,   37,  260,   58,   60,   61,   62,   43,   37,
   45,   59,   60,  270,   62,   43,  270,   45,   37,   47,
   58,  269,   41,   42,   43,   60,   61,   62,   47,   93,
   59,   37,   60,   61,   62,   41,   42,   43,   37,   45,
   59,   47,   41,   42,   43,  270,   45,  125,   47,  123,
   59,   28,  277,   59,   60,   44,   62,   43,   35,   45,
   59,   60,   37,   62,   60,   61,   41,   42,   43,   58,
   45,  123,   47,   37,   60,   61,   62,   41,   42,   43,
   91,   45,   61,   47,   59,   60,  125,   62,   41,   42,
  270,   41,   41,   45,   47,   59,   60,   37,   62,  125,
   54,   41,   42,   43,   30,   45,   59,   47,   60,   61,
   62,   37,   83,   39,   32,   35,   42,   -1,   -1,   59,
   -1,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,  264,
  265,  266,  267,  273,  274,  275,  276,  275,  278,  279,
  278,  279,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,  278,  279,  278,  273,  274,  275,  276,   -1,
  278,  279,   -1,  257,  273,  274,  275,  276,   -1,  278,
  279,   -1,  257,   -1,   -1,   -1,  270,  271,  272,   37,
   -1,   -1,   -1,   41,   42,  270,  271,  272,   -1,   47,
  273,  274,  275,  276,   -1,  278,  279,  273,  274,  275,
  276,   59,  278,  279,  274,  275,  276,   -1,  278,  279,
  275,  276,   -1,  278,  279,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,  273,  274,  275,  276,   -1,
   -1,  279,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,  273,  274,  275,  276,   -1,
  278,  279,   -1,  264,  265,  266,  267,   -1,  269,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,   -1,   -1,  273,  274,   -1,  276,  274,  275,
  276,   -1,  278,  279,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,  278,  279,   -1,   -1,   -1,   37,  273,  274,
   -1,   41,   42,   43,   -1,   45,   37,   47,   -1,  273,
   41,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   59,
   -1,  273,  274,  275,  276,   -1,  278,  279,   59,   59,
   60,   62,   -1,  273,   -1,   -1,   -1,   -1,   -1,   69,
   70,   71,   72,   73,   74,   75,   76,   77,   78,   79,
   80,   81,   82,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","INPUT","PRINT",
"DEF","WHILE","IF","ELSE","INT","DOUBLE","CHAR","STRUCT","RETURN","VOID","ID",
"REAL_CONSTANT","CHAR_CONSTANT","GREATER","SMALLER","EQUALS","NEGATION","MAIN",
"OR","AND",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones DEF MAIN '(' ')' ':' VOID '{' '}'",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : def ';'",
"definicion : funcion",
"funcion : DEF ID '(' params ')' ':' retorno '{' defs sentencias '}'",
"retorno : tipo",
"retorno : VOID",
"params :",
"params : param",
"param : par",
"param : param ',' par",
"par : ID ':' tipo",
"defs :",
"defs : defs def ';'",
"def : ids ':' tipo",
"ids : ID",
"ids : ids ',' ID",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : '[' INT_CONSTANT ']' tipo",
"tipo : STRUCT '{' campos '}'",
"campos : campo",
"campos : campos campo",
"campo : ids ':' tipo ';'",
"sentencias :",
"sentencias : expresion ';' sentencias",
"expresion : ID",
"expresion : INT_CONSTANT",
"expresion : CHAR_CONSTANT",
"expresion : REAL_CONSTANT",
"expresion : '(' expresion ')'",
"expresion : '!' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion GREATER expresion",
"expresion : expresion '<' expresion",
"expresion : expresion SMALLER expresion",
"expresion : expresion NEGATION expresion",
"expresion : expresion EQUALS expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : expresion '=' expresion",
};

//#line 149 "../../src/parser/parser.y"
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
//#line 386 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
