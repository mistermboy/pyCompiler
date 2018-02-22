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
public final static short CAST=280;
public final static short UNARIO=281;
public final static short CUERPO=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    2,    2,    2,    2,
    7,    7,    6,    6,   11,   11,   12,    8,    8,    4,
   13,   13,   10,   10,   10,   10,   10,   14,   14,   15,
    9,    9,   16,   16,   16,   16,   16,   16,   16,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   17,   17,   21,   19,   20,   25,
   24,   24,   26,   27,   23,   23,   22,   22,   28,   28,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    2,    0,
    1,    1,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    1,    1,    1,    2,    1,
    1,    1,    1,    3,    4,    3,    4,    2,    2,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    4,    1,    3,    6,    4,    5,    2,
    1,    1,    1,    3,    0,    1,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,   12,    0,   11,    0,    0,
    0,   41,    0,    0,    0,    0,    0,    0,   43,   42,
    0,    0,    0,    0,    0,    0,    0,   31,    0,   36,
   37,   38,   30,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    1,   18,    0,    0,
   32,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   39,    6,   34,
    0,   33,    0,    0,   35,    0,    0,    0,    0,   44,
   19,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   73,    0,   71,   72,   64,    0,    0,   45,    0,    0,
    0,    0,   69,    0,   67,   74,   70,
};
final static short yydgoto[] = {                          1,
    2,   64,    5,   65,    7,   24,   47,   66,   67,   21,
   25,   26,    8,   35,   36,   68,   76,   69,   70,   71,
   72,  117,  150,  142,  153,  143,  144,  118,
};
final static short yysindex[] = {                         0,
    0, -190, -258,    0,    0,  -56,    0,  -42,  -29,  -25,
    0,  -77, -250, -233,   26,    0,    0,    0,  -82, -189,
    0,    0,   13,   31,   37,    0,   32, -179,    4,  -77,
   41, -233, -164,   20, -108,    0,  -77,    0,  -64,    0,
  -16,  -77,    0,    0,    0,    0,   -9,    0,  331,   49,
  331,    0,  184,  184,  184,  184,  184,   86,    0,    0,
  184,  184,  404,   -7,   68,  331,  370,    0,  149,    0,
    0,    0,    0,    8,   86,  -31,  431,  -15,  185,  211,
  237,  184,  -28,  -28,  100,  371,    0,    0,   83,  370,
    0,  184,  184,  184,  184,  184,  184,  184,  184,  184,
  184,  184,  184,  184,  184,  184,  184,    0,    0,    0,
  184,    0,   27,  268,    0,  431,  102,  108,  184,    0,
    0,  568,  568,  568,  568,  457,  457,  431,  568,  568,
  213,  213,  -28,  -28,  -28,   62,  405,  431,  370,  370,
    0, -109,    0,    0,    0,  184,  -28,    0,  370,   38,
   44,  268,    0,  431,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  136,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  137,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   54,    0,
   54,    0,    0,    0,    0,    0,    0,   15,    0,    0,
    0,    0,    0,    0,    0,   72,   73,    0,    0,    0,
    0,    0,    0,    0,  -37,    0,   -6,    0,    0,    0,
    0,  152,   42,   51,    0,    0,    0,    0,    0,   74,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,    0,  163,    0,    0,
    0,  493,  593,  641,  653,  683,  721,  251,  677,  699,
  523,  563,   78,   87,  114,  -11,    0,   -4,   81,   81,
    0,  301,    0,    0,    0,    0,  123,    0,   88,    0,
    0,    0,    0,   25,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  161,    0,   -1,    0,    0,    0,    0,  -23,  306,
    0,  182,   11,    0,  180,  233,  164,  791,    0,    0,
    0,    0,   79,   69,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1000;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         40,
    6,   13,   11,   40,   40,   40,   40,   40,   40,   40,
   14,    9,  111,   20,   15,   12,   43,  106,   10,   22,
   40,   40,   40,   40,   40,   46,   20,  110,  111,   46,
   46,   46,   46,   46,   46,   46,   23,   65,   34,   66,
   28,   79,   90,  112,   79,   34,   46,   46,   46,   46,
   46,   40,   65,   40,   66,   40,   40,   40,   21,   40,
   40,   40,  107,   13,   89,   80,   27,   29,   80,    3,
   30,   31,   21,   40,   40,   40,   40,   42,   48,    4,
   32,   46,   48,   48,   48,   48,   48,   49,   48,   33,
    4,   49,   49,   49,   49,   49,   37,   49,   39,   48,
   48,   48,   48,   48,   41,   40,   49,   73,   49,   49,
   49,   49,   49,   51,   50,  149,  149,   87,   50,   50,
   50,   50,   50,   51,   50,   82,   88,   51,   51,   51,
   51,   51,  109,   51,   48,   50,   50,   50,   50,   50,
  119,  121,  145,   49,   51,   51,   51,   51,   51,  139,
   52,  146,  107,  152,   52,   52,   52,   52,   52,   47,
   52,    4,  155,   47,   47,   47,   47,   47,  156,   47,
   50,   52,   52,   52,   52,   52,   13,   14,   10,   51,
   47,   47,   47,   47,   47,  105,   16,   17,   18,   19,
  103,  102,   77,  101,  106,  104,    7,    8,    9,   16,
   17,   18,   19,   78,   46,   75,   52,  108,   99,   98,
  100,   74,   76,   40,   44,   47,   62,   78,  151,    0,
  157,  105,    0,   63,    0,    0,  103,  102,   61,  101,
  106,  104,    0,    0,    0,   40,   40,   40,   40,  107,
   40,   40,  113,    0,   99,   98,  100,  105,    0,  105,
    0,    0,  103,  102,  103,  101,  106,  104,  106,  104,
    0,   46,   46,   46,   46,    0,   46,   46,  114,    0,
   99,   98,  100,  105,    0,  107,    0,    0,  103,  102,
    0,  101,  106,  104,    0,    0,    0,   40,   40,   40,
   40,   63,   40,   40,   63,  115,   99,   98,  100,   91,
   62,  107,    0,  107,    0,    0,    0,   63,   63,   63,
    0,    0,   61,    0,   48,   48,   48,   48,    0,   48,
   48,    0,   91,   49,   49,   49,   49,  107,   49,   49,
    0,    0,    0,   68,    0,   38,    0,    0,    0,    0,
   68,    0,   45,   63,   48,   68,  141,   50,    0,    0,
   50,   50,   50,   50,    0,   50,   50,    0,    0,   51,
   51,   51,   51,   62,   51,   51,    0,    0,   85,    0,
   63,    0,    0,    0,    0,   61,    0,    0,    0,    0,
    0,   91,    0,    0,  141,    0,   52,   52,   52,   52,
  140,   52,   52,    0,    0,   47,   47,   47,   47,    0,
   47,   47,   62,    0,    0,    0,    0,  105,    0,   63,
    0,  120,  103,  102,   61,  101,  106,  104,    0,    0,
    0,   92,   93,   94,   95,   68,   96,   97,    0,    0,
   99,   98,  100,    0,    0,    0,   62,    0,    0,    0,
   52,  105,    0,   63,    0,    0,  103,  102,   61,  101,
  106,  104,    0,   75,   59,   60,    0,   92,   93,   94,
   95,  107,   96,   97,   99,   98,  100,  105,    0,    0,
    0,    0,  103,  102,    0,  101,  106,  104,    0,    0,
    0,    0,    0,   92,   93,   94,   95,    0,   96,   97,
   99,   98,  100,  105,   20,  107,    0,  148,  103,  102,
    0,  101,  106,  104,    0,    0,    0,    0,    0,   92,
   93,   94,   95,    0,   96,   97,   99,    0,  100,    0,
    0,  107,    0,    0,   52,   53,   54,    0,   55,   56,
    0,    0,    0,   56,    0,   57,   56,   75,   59,   60,
    0,    0,    0,    0,    0,    0,    0,  107,    0,    0,
   56,   56,   56,   56,   56,    0,    0,   68,   68,   68,
    0,   68,   68,   54,    0,   54,   54,   54,   68,    0,
   68,   68,   68,    0,    0,    0,    0,    0,    0,    0,
   54,   54,   54,   54,   54,   56,    0,   52,   53,   54,
    0,   55,   56,    0,    0,    0,    0,    0,   57,    0,
   58,   59,   60,   53,  105,   53,   53,   53,    0,  103,
  102,    0,  101,  106,  104,   54,    0,    0,    0,    0,
   53,   53,   53,   53,   53,    0,   52,   53,   54,    0,
   55,   56,    0,   58,    0,    0,   58,   57,    0,   75,
   59,   60,    0,   92,   93,   94,   95,    0,   96,   97,
   58,   58,   58,   58,   58,   53,    0,    0,  107,    0,
   52,    0,    0,    0,    0,    0,    0,   16,   17,   18,
   19,    0,    0,   75,   59,   60,    0,   92,   93,   94,
   95,   60,   96,   97,   60,   58,    0,    0,    0,    0,
    0,    0,    0,   59,    0,    0,   59,    0,   60,   60,
   60,   60,   60,   92,   93,   94,   95,    0,   96,   97,
   59,   59,   59,   59,   59,    0,    0,   57,    0,    0,
   57,    0,    0,   62,    0,    0,   62,    0,    0,   92,
   93,   94,   95,   60,   57,   57,   57,   57,   57,   55,
   62,   62,   55,   62,    0,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   55,   55,   55,   55,
   55,   61,    0,    0,   61,   56,   56,   56,   56,   57,
   56,   56,    0,    0,    0,   62,    0,    0,   61,   61,
    0,   61,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   55,    0,    0,    0,   54,   54,   54,   54,    0,
   54,   54,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   61,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   53,   53,   53,   53,    0,
   53,   53,    0,   77,   77,   79,   80,   81,    0,    0,
    0,   83,   84,   86,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   58,   58,   58,   58,    0,
   58,   58,  116,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,  134,  135,  136,  137,    0,    0,
    0,  138,    0,    0,    0,    0,    0,    0,    0,  147,
    0,    0,    0,   60,   60,   60,   60,    0,   60,   60,
    0,    0,    0,    0,    0,   59,   59,   59,   59,    0,
   59,   59,    0,    0,    0,    0,  154,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   57,   57,   57,    0,   57,   57,    0,    0,    0,    0,
   62,   62,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   55,   55,   55,   55,    0,   55,   55,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   61,   61,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    2,   44,   59,   41,   42,   43,   44,   45,   46,   47,
   40,  270,   44,   91,   40,   58,  125,   46,  277,  270,
   58,   59,   60,   61,   62,   37,   91,   59,   44,   41,
   42,   43,   44,   45,   46,   47,  270,   44,   28,   44,
  123,   41,   66,   59,   44,   35,   58,   59,   60,   61,
   62,   37,   59,   91,   59,   93,   42,   43,   44,   45,
   46,   47,   91,   44,   66,   41,   41,  257,   44,  260,
   58,   41,   58,   59,   60,   61,   62,   58,   37,  270,
   44,   93,   41,   42,   43,   44,   45,   37,   47,   58,
  270,   41,   42,   43,   44,   45,   93,   47,   58,   58,
   59,   60,   61,   62,  269,   91,  123,   59,   58,   59,
   60,   61,   62,  123,   37,  139,  140,  125,   41,   42,
   43,   44,   45,   37,   47,   40,   59,   41,   42,   43,
   44,   45,  125,   47,   93,   58,   59,   60,   61,   62,
   41,   59,   41,   93,   58,   59,   60,   61,   62,  123,
   37,   44,   91,  263,   41,   42,   43,   44,   45,   37,
   47,  270,  125,   41,   42,   43,   44,   45,  125,   47,
   93,   58,   59,   60,   61,   62,   41,   41,  125,   93,
   58,   59,   60,   61,   62,   37,  264,  265,  266,  267,
   42,   43,   41,   45,   46,   47,  125,  125,  125,  264,
  265,  266,  267,   41,  269,  125,   93,   59,   60,   61,
   62,   51,  125,   32,   35,   93,   33,   54,  140,   -1,
  152,   37,   -1,   40,   -1,   -1,   42,   43,   45,   45,
   46,   47,   -1,   -1,   -1,  273,  274,  275,  276,   91,
  278,  279,   58,   -1,   60,   61,   62,   37,   -1,   37,
   -1,   -1,   42,   43,   42,   45,   46,   47,   46,   47,
   -1,  273,  274,  275,  276,   -1,  278,  279,   58,   -1,
   60,   61,   62,   37,   -1,   91,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   59,   60,   61,   62,   67,
   33,   91,   -1,   91,   -1,   -1,   -1,   40,   58,   59,
   -1,   -1,   45,   -1,  273,  274,  275,  276,   -1,  278,
  279,   -1,   90,  273,  274,  275,  276,   91,  278,  279,
   -1,   -1,   -1,   33,   -1,   30,   -1,   -1,   -1,   -1,
   40,   -1,   37,   93,   39,   45,  114,   42,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,  273,
  274,  275,  276,   33,  278,  279,   -1,   -1,   63,   -1,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,
   -1,  149,   -1,   -1,  152,   -1,  273,  274,  275,  276,
  123,  278,  279,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   33,   -1,   -1,   -1,   -1,   37,   -1,   40,
   -1,   41,   42,   43,   45,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,  125,  278,  279,   -1,   -1,
   60,   61,   62,   -1,   -1,   -1,   33,   -1,   -1,   -1,
  257,   37,   -1,   40,   -1,   -1,   42,   43,   45,   45,
   46,   47,   -1,  270,  271,  272,   -1,  273,  274,  275,
  276,   91,  278,  279,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   60,   61,   62,   37,   91,   91,   -1,   93,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   60,   -1,   62,   -1,
   -1,   91,   -1,   -1,  257,  258,  259,   -1,  261,  262,
   -1,   -1,   -1,   41,   -1,  268,   44,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
   58,   59,   60,   61,   62,   -1,   -1,  257,  258,  259,
   -1,  261,  262,   41,   -1,   43,   44,   45,  268,   -1,
  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   93,   -1,  257,  258,  259,
   -1,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,
  270,  271,  272,   41,   37,   43,   44,   45,   -1,   42,
   43,   -1,   45,   46,   47,   93,   -1,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   -1,  257,  258,  259,   -1,
  261,  262,   -1,   41,   -1,   -1,   44,  268,   -1,  270,
  271,  272,   -1,  273,  274,  275,  276,   -1,  278,  279,
   58,   59,   60,   61,   62,   93,   -1,   -1,   91,   -1,
  257,   -1,   -1,   -1,   -1,   -1,   -1,  264,  265,  266,
  267,   -1,   -1,  270,  271,  272,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   58,   59,
   60,   61,   62,  273,  274,  275,  276,   -1,  278,  279,
   58,   59,   60,   61,   62,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,  273,
  274,  275,  276,   93,   58,   59,   60,   61,   62,   41,
   58,   59,   44,   61,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,   61,
   62,   41,   -1,   -1,   44,  273,  274,  275,  276,   93,
  278,  279,   -1,   -1,   -1,   93,   -1,   -1,   58,   59,
   -1,   61,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   53,   54,   55,   56,   57,   -1,   -1,
   -1,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   82,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   92,   93,   94,   95,   96,   97,   98,   99,
  100,  101,  102,  103,  104,  105,  106,  107,   -1,   -1,
   -1,  111,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  119,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   -1,   -1,   -1,  146,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   -1,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=282;
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
"OR","AND","CAST","UNARIO","CUERPO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones DEF MAIN '(' ')' ':' VOID '{' body '}'",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : def ';'",
"definicion : funcion",
"funcion : DEF ID '(' params ')' ':' retorno '{' body '}'",
"body : defs",
"body : sentencias",
"body : defs sentencias",
"body :",
"retorno : tipo",
"retorno : VOID",
"params :",
"params : param",
"param : par",
"param : param ',' par",
"par : ID ':' tipo",
"defs : def ';'",
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
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : PRINT list ';'",
"sentencia : INPUT list ';'",
"sentencia : RETURN expresion ';'",
"sentencia : condicionalSimple",
"sentencia : condicionalComplejo",
"sentencia : while",
"sentencia : expresion ';'",
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
"expresion : ID '(' args ')'",
"list : expresion",
"list : list ',' expresion",
"while : WHILE expresion ':' '{' sents '}'",
"condicionalSimple : IF expresion ':' cuerpo",
"condicionalComplejo : IF expresion ':' cuerpo else",
"else : ELSE cuerpo",
"cuerpo : cuerpoSimple",
"cuerpo : cuerpoComplejo",
"cuerpoSimple : sentencia",
"cuerpoComplejo : '{' sents '}'",
"sents :",
"sents : sentencias",
"args :",
"args : arg",
"arg : expresion",
"arg : arg ',' expresion",
};

//#line 206 "../../src/parser/parser.y"
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
//#line 559 "Parser.java"
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
