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
   15,   16,   16,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,
};
final static short yylen[] = {                            2,
    9,    2,    0,    2,    1,   11,    1,    1,    0,    1,
    1,    3,    3,    0,    3,    3,    1,    3,    1,    1,
    1,    4,    4,    1,    2,    4,    0,    2,    3,    3,
    2,    1,    3,    1,    1,    1,    1,    3,    4,    3,
    4,    2,    2,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   17,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   19,   20,   21,    0,    0,
   16,   18,    0,    0,    0,   11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   24,    0,   13,    0,   12,
    0,    0,   23,   25,   22,    8,    0,    7,    0,    0,
   14,    1,   26,    0,    0,    0,   15,   35,    0,    0,
   34,   37,   36,    0,    0,    0,    6,   28,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,   30,    0,   29,    0,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   41,   39,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,   24,   47,   54,   56,   21,   25,
   26,    8,   35,   36,   68,   70,   71,
};
final static short yysindex[] = {                         0,
    0, -240, -200,    0,    0,  -56,    0,    7,  -28,   -1,
    0,    4, -230, -204,   28,    0,    0,    0,  -34, -160,
    0,    0,   51,   69,   84,    0,   66, -136,   55,    4,
   96, -204, -114,   20, -124,    0,    4,    0,   -5,    0,
   33,    4,    0,    0,    0,    0,   36,    0,   44,  104,
    0,    0,    0, -136,  111,  177,    0,    0,  200,  200,
    0,    0,    0,  200,  200,  160,    0,    0,   12,  -31,
   80,  -30,  355,  -44,  131,   38,  200,  200,  200,  200,
  200,  200,  200,  200,  200,  200,  200,  200,  200,  200,
  200,  200,    0,    0,  200,    0,  200,    0,  550,  503,
  166,  471,  115,  136,   80,  527,  561,  355,  391,  -15,
   93,  -44,   83,   45,   80,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  135,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  144,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  147,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -18,    0,  248,  -26,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,   52,
  422,  281,  450,  443,    9,  414,  400,  307,  222,  106,
   71,    1,  -37,    0,   -7,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  132,    0,    0,    0,    0,    0,  165,    0,
  156,  109,    0,  154,    0,  130,  545,
};
final static int YYTABLESIZE=800;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
   43,   91,   11,   40,   40,   40,   40,   40,   40,   40,
   43,   14,   95,   95,   43,   43,   43,   43,   43,    3,
   43,   40,   40,   40,   40,   32,   90,   94,   96,    4,
   91,   89,   43,   43,   43,   43,   33,   44,   15,   22,
   32,   44,   44,   44,   44,   44,   92,   44,   88,   57,
   13,   33,   57,   90,   87,   40,   86,   91,   89,   44,
   44,   44,   44,   13,   12,   23,   43,   57,   27,    9,
   93,   84,   83,   85,   88,   92,   10,   42,   98,   90,
   87,   88,   86,   91,   89,   20,   90,   87,   28,   86,
   91,   89,   52,   44,   20,   52,   29,   84,   83,   85,
   50,   57,   92,   50,   84,   83,   85,   45,   30,   31,
   52,   45,   52,   45,   45,   45,   88,   45,   50,   50,
   50,   90,   87,   33,   86,   91,   89,   32,   92,   45,
   45,   45,   45,    4,   90,   92,   34,  117,   91,   84,
   83,   85,   46,   34,   52,    4,   46,   37,   46,   46,
   46,   88,   50,   39,   41,   49,   90,   87,   51,   86,
   91,   89,   53,   45,   46,   46,   46,   46,   52,   57,
   92,   97,   88,   92,   84,    9,   85,   90,   87,   27,
   86,   91,   89,   92,   10,   55,   27,   40,   44,   72,
    0,   27,   65,    0,   38,   84,    0,   85,   46,   66,
    0,   45,   88,   48,   64,   92,   50,   90,   87,   65,
   86,   91,   89,    0,    0,    0,   66,    0,    0,    0,
    0,   64,    0,    0,    0,   84,   92,   85,    0,    0,
   75,    0,   65,    0,    0,   40,   40,   40,   40,   66,
   40,   40,    0,    0,   64,    0,   43,   43,   43,   43,
   20,   43,   43,    0,    0,    0,   92,    0,   16,   17,
   18,   19,   47,   46,   47,   47,   47,   16,   17,   18,
   19,   27,    0,   44,   44,   44,   44,    0,   44,   44,
   47,   47,   47,   47,   77,   78,   79,   80,   42,   81,
   82,   42,   42,    0,    0,    0,    0,    0,    0,    0,
    0,   67,    0,    0,    0,    0,   42,   42,   42,   42,
   77,   78,   79,   80,   47,   81,   82,   77,   78,   79,
   80,   53,   81,   82,   53,   52,   52,   52,    0,   52,
   52,    0,   50,   50,   50,   50,    0,   50,   50,   53,
   42,   53,    0,   45,   45,   45,   45,   48,   45,   45,
   48,   48,   77,   78,   79,   80,    0,   81,   82,    0,
    0,    0,    0,    0,    0,   48,   48,   48,   48,    0,
    0,    0,    0,   53,    0,    0,    0,    0,   46,   46,
   46,   46,    0,   46,   46,    0,    0,   77,   78,   79,
   80,   88,    0,   82,    0,    0,   90,   87,    0,   48,
   91,   89,    0,   27,   27,   27,    0,    0,   77,   78,
   79,   80,    0,    0,    0,    0,   58,   27,   27,    0,
    0,    0,    0,   16,   17,   18,   19,   88,    0,   61,
   62,   63,   90,   58,   59,   60,   91,   89,   77,   78,
   49,   80,    0,   49,    0,   92,   61,   62,   63,    0,
    0,    0,    0,    0,   51,    0,   58,   51,   49,   49,
   49,   49,   54,    0,    0,   54,    0,    0,    0,   61,
   62,   63,   51,   51,   51,    0,    0,    0,    0,    0,
   54,   92,   54,   55,    0,    0,   55,    0,    0,    0,
   56,    0,   49,   56,   47,   47,   47,   47,    0,   47,
   47,   55,    0,   55,    0,    0,   51,   88,   56,    0,
   56,    0,   90,   87,   54,   86,   91,   89,    0,    0,
   42,   42,   42,   42,    0,   42,   42,    0,    0,    0,
   84,    0,   85,    0,    0,   55,    0,    0,    0,   88,
    0,    0,   56,    0,   90,   87,    0,   86,   91,   89,
    0,    0,    0,    0,    0,   53,   53,    0,   53,   53,
    0,   92,   84,   88,   85,    0,    0,    0,   90,   87,
    0,   86,   91,   89,    0,    0,    0,    0,    0,   48,
   48,   48,   48,    0,   48,   48,   88,    0,   85,    0,
    0,   90,   87,   92,   86,   91,   89,   88,    0,    0,
   69,    0,   90,   87,    0,   86,   91,   89,   73,   74,
   76,   85,    0,    0,    0,    0,    0,   92,    0,    0,
    0,   99,  100,  101,  102,  103,  104,  105,  106,  107,
  108,  109,  110,  111,  112,  113,  114,    0,    0,  115,
   92,  116,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   92,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   49,   49,   49,   49,    0,   49,   49,    0,
    0,    0,    0,    0,    0,    0,    0,   51,   51,   51,
    0,   51,   51,    0,    0,    0,   54,    0,    0,   54,
   54,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,   55,    0,    0,    0,    0,    0,   56,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   77,   78,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   77,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   77,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  125,   46,   59,   41,   42,   43,   44,   45,   46,   47,
   37,   40,   44,   44,   41,   42,   43,   44,   45,  260,
   47,   59,   60,   61,   62,   44,   42,   59,   59,  270,
   46,   47,   59,   60,   61,   62,   44,   37,   40,  270,
   59,   41,   42,   43,   44,   45,   91,   47,   37,   41,
   44,   59,   44,   42,   43,   93,   45,   46,   47,   59,
   60,   61,   62,   44,   58,  270,   93,   59,   41,  270,
   59,   60,   61,   62,   37,   91,  277,   58,   41,   42,
   43,   37,   45,   46,   47,   91,   42,   43,  123,   45,
   46,   47,   41,   93,   91,   44,  257,   60,   61,   62,
   41,   93,   91,   44,   60,   61,   62,   37,   58,   41,
   59,   41,   61,   43,   44,   45,   37,   47,   59,   60,
   61,   42,   43,   58,   45,   46,   47,   44,   91,   59,
   60,   61,   62,  270,   42,   91,   28,   93,   46,   60,
   61,   62,   37,   35,   93,  270,   41,   93,   43,   44,
   45,   37,   93,   58,  269,  123,   42,   43,  123,   45,
   46,   47,   59,   93,   59,   60,   61,   62,  125,   59,
   91,   41,   37,   91,   60,   41,   62,   42,   43,   33,
   45,   46,   47,   91,   41,   54,   40,   32,   35,   60,
   -1,   45,   33,   -1,   30,   60,   -1,   62,   93,   40,
   -1,   37,   37,   39,   45,   91,   42,   42,   43,   33,
   45,   46,   47,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,   -1,   60,   91,   62,   -1,   -1,
   66,   -1,   33,   -1,   -1,  273,  274,  275,  276,   40,
  278,  279,   -1,   -1,   45,   -1,  273,  274,  275,  276,
   91,  278,  279,   -1,   -1,   -1,   91,   -1,  264,  265,
  266,  267,   41,  269,   43,   44,   45,  264,  265,  266,
  267,  125,   -1,  273,  274,  275,  276,   -1,  278,  279,
   59,   60,   61,   62,  273,  274,  275,  276,   41,  278,
  279,   44,   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   59,   60,   61,   62,
  273,  274,  275,  276,   93,  278,  279,  273,  274,  275,
  276,   41,  278,  279,   44,  274,  275,  276,   -1,  278,
  279,   -1,  273,  274,  275,  276,   -1,  278,  279,   59,
   93,   61,   -1,  273,  274,  275,  276,   41,  278,  279,
   44,   45,  273,  274,  275,  276,   -1,  278,  279,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,  273,  274,  275,
  276,   37,   -1,  279,   -1,   -1,   42,   43,   -1,   93,
   46,   47,   -1,  257,  258,  259,   -1,   -1,  273,  274,
  275,  276,   -1,   -1,   -1,   -1,  257,  271,  272,   -1,
   -1,   -1,   -1,  264,  265,  266,  267,   37,   -1,  270,
  271,  272,   42,  257,  258,  259,   46,   47,  273,  274,
   41,  276,   -1,   44,   -1,   91,  270,  271,  272,   -1,
   -1,   -1,   -1,   -1,   41,   -1,  257,   44,   59,   60,
   61,   62,   41,   -1,   -1,   44,   -1,   -1,   -1,  270,
  271,  272,   59,   60,   61,   -1,   -1,   -1,   -1,   -1,
   59,   91,   61,   41,   -1,   -1,   44,   -1,   -1,   -1,
   41,   -1,   93,   44,  273,  274,  275,  276,   -1,  278,
  279,   59,   -1,   61,   -1,   -1,   93,   37,   59,   -1,
   61,   -1,   42,   43,   93,   45,   46,   47,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,
   60,   -1,   62,   -1,   -1,   93,   -1,   -1,   -1,   37,
   -1,   -1,   93,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,  275,  276,   -1,  278,  279,
   -1,   91,   60,   37,   62,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   37,   -1,   62,   -1,
   -1,   42,   43,   91,   45,   46,   47,   37,   -1,   -1,
   56,   -1,   42,   43,   -1,   45,   46,   47,   64,   65,
   66,   62,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
   -1,   77,   78,   79,   80,   81,   82,   83,   84,   85,
   86,   87,   88,   89,   90,   91,   92,   -1,   -1,   95,
   91,   97,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,
   -1,  278,  279,   -1,   -1,   -1,  275,   -1,   -1,  278,
  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  278,  279,   -1,   -1,   -1,   -1,   -1,  278,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
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
"sentencias : sentencias sentencia",
"sentencia : PRINT list ';'",
"sentencia : INPUT list ';'",
"sentencia : expresion ';'",
"list : expresion",
"list : list ',' expresion",
"expresion : ID",
"expresion : INT_CONSTANT",
"expresion : CHAR_CONSTANT",
"expresion : REAL_CONSTANT",
"expresion : '(' expresion ')'",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' expresion",
"expresion : '(' tipo ')' expresion",
"expresion : '-' expresion",
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

//#line 163 "../../src/parser/parser.y"
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
//#line 475 "Parser.java"
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
