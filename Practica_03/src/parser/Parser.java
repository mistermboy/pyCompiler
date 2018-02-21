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
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    2,    2,    2,    2,
    7,    7,    6,    6,   11,   11,   12,    8,    8,    4,
   13,   13,   10,   10,   10,   10,   10,   14,   14,   15,
    9,    9,   16,   16,   16,   16,   16,   16,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   17,   17,   20,   19,   24,   23,   23,
   25,   26,   22,   22,   21,   21,   27,   27,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    2,    0,
    1,    1,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    1,    1,    2,    1,    1,
    1,    1,    3,    4,    3,    4,    2,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    1,    3,    6,    5,    2,    1,    1,
    1,    3,    0,    1,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,   12,    0,   11,    0,    0,
    0,   40,    0,    0,    0,    0,    0,    0,   42,   41,
    0,    0,    0,    0,    0,    0,    0,   31,    0,   36,
   37,   30,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,   18,    0,    0,   32,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   38,    6,   34,    0,
   33,    0,    0,   35,    0,    0,    0,    0,   43,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   71,
    0,   69,   70,   63,    0,    0,   44,    0,    0,    0,
    0,   67,    0,   66,   72,   68,
};
final static short yydgoto[] = {                          1,
    2,   64,    5,   65,    7,   24,   47,   66,   67,   21,
   25,   26,    8,   35,   36,   68,   75,   69,   70,   71,
  116,  149,  141,  152,  142,  143,  117,
};
final static short yysindex[] = {                         0,
    0, -192, -231,    0,    0,  -56,    0,  -42,  -29,  -25,
    0,  -77, -250, -227,   25,    0,    0,    0,  -54, -190,
    0,    0,   13,   31,   36,    0,   23, -173,    6,  -77,
   47, -227, -162,   -3, -108,    0,  -77,    0,  107,    0,
   -9,  -77,    0,    0,    0,    0,   -7,    0,  298,   49,
  298,    0,  173,  173,  173,  173,  173,   77,    0,    0,
  173,  173,  335,    2,   59,  298,  370,    0,  149,    0,
    0,    0,    8,   77,  -32,  431,  -31,  185,  211,  237,
  173,  -27,  -27,   85,  371,    0,    0,   82,  370,    0,
  173,  173,  173,  173,  173,  173,  173,  173,  173,  173,
  173,  173,  173,  173,  173,  173,    0,    0,    0,  173,
    0,   19,  268,    0,  431,  102,  106,  173,    0,    0,
  530,  530,  530,  530,  457,  457,  431,  530,  530,  187,
  187,  -27,  -27,  -27,   61,  405,  431,  370,  370,    0,
 -110,    0,    0,    0,  173,  -27,    0,  370,   29,   38,
  268,    0,  431,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  128,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  136,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   53,    0,
   53,    0,    0,    0,    0,    0,    0,   15,    0,    0,
    0,    0,    0,    0,    0,   54,   68,    0,    0,    0,
    0,    0,    0,  -37,    0,  -15,    0,    0,    0,    0,
  158,   42,   51,    0,    0,    0,    0,    0,   76,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   -4,    0,  161,    0,    0,    0,
  491,  550,  593,  631,  156,  678,  251,  641,  653,  395,
  520,   78,   87,  114,  -11,    0,   -6,   79,   79,    0,
    0,    0,    0,    0,    0,  123,    0,   80,    0,    0,
    0,    0,    1,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  152,    0,   -1,    0,    0,    0,    0,  -48,  556,
    0,  180,   35,    0,  184,  233,  166,  752,    0,    0,
    0,   84,   70,    0,    0,    0,    0,
};
final static int YYTABLESIZE=957;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
    6,   13,   11,   39,   39,   39,   39,   39,   39,   39,
   14,  110,  110,   20,   15,   12,   43,   89,  105,   22,
   39,   39,   39,   39,   39,   45,  109,  111,   64,   45,
   45,   45,   45,   45,   45,   45,   77,   65,    9,   77,
   13,   78,   23,   64,   78,   10,   45,   45,   45,   45,
   45,   39,   65,   39,   42,   39,   39,   39,   21,   39,
   39,   39,   34,  106,   88,   27,   29,    3,   28,   34,
   30,   31,   21,   39,   39,   39,   39,    4,   47,   32,
   33,   45,   47,   47,   47,   47,   47,   48,   47,  148,
  148,   48,   48,   48,   48,   48,    4,   48,   37,   47,
   47,   47,   47,   47,   39,   39,   41,   72,   48,   48,
   48,   48,   48,   49,   49,   51,   81,   87,   49,   49,
   49,   49,   49,   50,   49,  118,   86,   50,   50,   50,
   50,   50,  108,   50,   47,   49,   49,   49,   49,   49,
  120,  138,  144,   48,   50,   50,   50,   50,   50,  145,
   51,  106,  151,  154,   51,   51,   51,   51,   51,   46,
   51,    4,  155,   46,   46,   46,   46,   46,   13,   46,
   49,   51,   51,   51,   51,   51,   14,   10,    7,   50,
   46,   46,   46,   46,   46,  104,   16,   17,   18,   19,
  102,  101,    8,  100,  105,  103,   61,   20,   75,   61,
    9,   76,   73,   73,   74,   62,   51,  107,   98,   97,
   99,   40,   63,   61,   61,   46,   61,   61,   44,   77,
  156,  104,  150,  104,    0,    0,  102,  101,  102,  100,
  105,  103,  105,  103,    0,   39,   39,   39,   39,  106,
   39,   39,  112,    0,   98,   97,   99,  104,   61,    0,
    0,    0,  102,  101,    0,  100,  105,  103,    0,    0,
    0,   45,   45,   45,   45,    0,   45,   45,  113,    0,
   98,   97,   99,  104,    0,  106,    0,  106,  102,  101,
    0,  100,  105,  103,    0,    0,    0,   39,   39,   39,
   39,   62,   39,   39,   62,  114,   98,   97,   99,   90,
   62,  106,    0,    0,    0,    0,    0,   63,   62,   62,
    0,    0,   61,    0,   47,   47,   47,   47,    0,   47,
   47,   90,    0,   48,   48,   48,   48,  106,   48,   48,
   62,    0,    0,    0,    0,    0,    0,   63,    0,    0,
    0,    0,   61,   62,    0,  140,    0,    0,    0,    0,
   49,   49,   49,   49,    0,   49,   49,    0,    0,   50,
   50,   50,   50,    0,   50,   50,    0,   62,    0,    0,
   16,   17,   18,   19,   63,   46,    0,    0,    0,   61,
   90,    0,    0,  140,    0,    0,   51,   51,   51,   51,
  139,   51,   51,    0,    0,   46,   46,   46,   46,    0,
   46,   46,   62,    0,    0,    0,    0,  104,    0,   63,
    0,  119,  102,  101,   61,  100,  105,  103,    0,    0,
    0,   91,   92,   93,   94,   20,   95,   96,    0,   52,
   98,   97,   99,   61,   61,   53,    0,   53,   53,   53,
    0,  104,   74,   59,   60,    0,  102,  101,    0,  100,
  105,  103,   53,   53,   53,   53,   53,   91,   92,   93,
   94,  106,   95,   96,   98,   97,   99,  104,    0,    0,
    0,    0,  102,  101,    0,  100,  105,  103,    0,    0,
    0,    0,    0,   91,   92,   93,   94,   53,   95,   96,
   98,   97,   99,  104,    0,  106,    0,  147,  102,  101,
    0,  100,  105,  103,    0,    0,    0,    0,    0,   91,
   92,   93,   94,    0,   95,   96,   98,    0,   99,    0,
    0,  106,    0,    0,   52,   53,   54,    0,   55,   56,
    0,   55,    0,    0,   55,   57,    0,   74,   59,   60,
    0,    0,    0,    0,    0,    0,    0,  106,   55,   55,
   55,   55,   55,    0,   52,   53,   54,    0,   55,   56,
   52,    0,   52,   52,   52,   57,  104,   58,   59,   60,
    0,  102,  101,    0,  100,  105,  103,   52,   52,   52,
   52,   52,    0,   55,    0,   38,    0,    0,    0,    0,
   57,   52,   45,   57,   48,    0,    0,   50,   16,   17,
   18,   19,    0,    0,   74,   59,   60,   57,   57,   57,
   57,   57,   52,    0,    0,    0,    0,    0,   84,    0,
  106,    0,    0,    0,    0,    0,   52,   53,   54,    0,
   55,   56,    0,   59,    0,    0,   59,   57,    0,   74,
   59,   60,   57,   91,   92,   93,   94,    0,   95,   96,
   59,   59,   59,   59,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   53,   53,   53,
   53,   58,   53,   53,   58,    0,    0,   91,   92,   93,
   94,   56,   95,   96,   56,   59,    0,    0,   58,   58,
   58,   58,   58,   54,    0,    0,   54,    0,   56,   56,
   56,   56,   56,   91,   92,   93,   94,    0,   95,   96,
   54,   54,   54,   54,   54,    0,    0,    0,   60,    0,
    0,   60,    0,   58,    0,    0,    0,    0,    0,   91,
   92,   93,   94,   56,    0,   60,   60,    0,   60,    0,
    0,    0,    0,    0,    0,   54,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   55,   55,   55,   55,    0,   55,   55,
   60,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,   52,   52,   52,    0,   52,   52,    0,
    0,    0,    0,    0,   76,   76,   78,   79,   80,    0,
    0,    0,   82,   83,   85,    0,    0,    0,    0,    0,
    0,    0,   57,   57,   57,   57,    0,   57,   57,    0,
    0,    0,  115,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  121,  122,  123,  124,  125,  126,  127,  128,
  129,  130,  131,  132,  133,  134,  135,  136,    0,    0,
    0,  137,    0,    0,    0,   59,   59,   59,   59,  146,
   59,   59,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  153,    0,    0,    0,
    0,    0,    0,   58,   58,   58,   58,    0,   58,   58,
    0,    0,    0,   56,   56,   56,   56,    0,   56,   56,
    0,    0,    0,    0,    0,   54,   54,   54,   54,    0,
   54,   54,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   60,   60,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    2,   44,   59,   41,   42,   43,   44,   45,   46,   47,
   40,   44,   44,   91,   40,   58,  125,   66,   46,  270,
   58,   59,   60,   61,   62,   37,   59,   59,   44,   41,
   42,   43,   44,   45,   46,   47,   41,   44,  270,   44,
   44,   41,  270,   59,   44,  277,   58,   59,   60,   61,
   62,   37,   59,   91,   58,   93,   42,   43,   44,   45,
   46,   47,   28,   91,   66,   41,  257,  260,  123,   35,
   58,   41,   58,   59,   60,   61,   62,  270,   37,   44,
   58,   93,   41,   42,   43,   44,   45,   37,   47,  138,
  139,   41,   42,   43,   44,   45,  270,   47,   93,   58,
   59,   60,   61,   62,   58,   91,  269,   59,   58,   59,
   60,   61,   62,  123,   37,  123,   40,   59,   41,   42,
   43,   44,   45,   37,   47,   41,  125,   41,   42,   43,
   44,   45,  125,   47,   93,   58,   59,   60,   61,   62,
   59,  123,   41,   93,   58,   59,   60,   61,   62,   44,
   37,   91,  263,  125,   41,   42,   43,   44,   45,   37,
   47,  270,  125,   41,   42,   43,   44,   45,   41,   47,
   93,   58,   59,   60,   61,   62,   41,  125,  125,   93,
   58,   59,   60,   61,   62,   37,  264,  265,  266,  267,
   42,   43,  125,   45,   46,   47,   41,   91,   41,   44,
  125,   41,   51,  125,  125,   33,   93,   59,   60,   61,
   62,   32,   40,   58,   59,   93,   61,   45,   35,   54,
  151,   37,  139,   37,   -1,   -1,   42,   43,   42,   45,
   46,   47,   46,   47,   -1,  273,  274,  275,  276,   91,
  278,  279,   58,   -1,   60,   61,   62,   37,   93,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   58,   -1,
   60,   61,   62,   37,   -1,   91,   -1,   91,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   59,   60,   61,   62,   67,
   33,   91,   -1,   -1,   -1,   -1,   -1,   40,   58,   59,
   -1,   -1,   45,   -1,  273,  274,  275,  276,   -1,  278,
  279,   89,   -1,  273,  274,  275,  276,   91,  278,  279,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   93,   -1,  113,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   33,   -1,   -1,
  264,  265,  266,  267,   40,  269,   -1,   -1,   -1,   45,
  148,   -1,   -1,  151,   -1,   -1,  273,  274,  275,  276,
  123,  278,  279,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   33,   -1,   -1,   -1,   -1,   37,   -1,   40,
   -1,   41,   42,   43,   45,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   91,  278,  279,   -1,  257,
   60,   61,   62,  278,  279,   41,   -1,   43,   44,   45,
   -1,   37,  270,  271,  272,   -1,   42,   43,   -1,   45,
   46,   47,   58,   59,   60,   61,   62,  273,  274,  275,
  276,   91,  278,  279,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   93,  278,  279,
   60,   61,   62,   37,   -1,   91,   -1,   93,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   60,   -1,   62,   -1,
   -1,   91,   -1,   -1,  257,  258,  259,   -1,  261,  262,
   -1,   41,   -1,   -1,   44,  268,   -1,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   58,   59,
   60,   61,   62,   -1,  257,  258,  259,   -1,  261,  262,
   41,   -1,   43,   44,   45,  268,   37,  270,  271,  272,
   -1,   42,   43,   -1,   45,   46,   47,   58,   59,   60,
   61,   62,   -1,   93,   -1,   30,   -1,   -1,   -1,   -1,
   41,  257,   37,   44,   39,   -1,   -1,   42,  264,  265,
  266,  267,   -1,   -1,  270,  271,  272,   58,   59,   60,
   61,   62,   93,   -1,   -1,   -1,   -1,   -1,   63,   -1,
   91,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,
  261,  262,   -1,   41,   -1,   -1,   44,  268,   -1,  270,
  271,  272,   93,  273,  274,  275,  276,   -1,  278,  279,
   58,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   -1,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   93,   -1,   -1,   58,   59,
   60,   61,   62,   41,   -1,   -1,   44,   -1,   58,   59,
   60,   61,   62,  273,  274,  275,  276,   -1,  278,  279,
   58,   59,   60,   61,   62,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   93,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   93,   -1,   58,   59,   -1,   61,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,
   -1,   -1,   -1,   -1,   53,   54,   55,   56,   57,   -1,
   -1,   -1,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,
   -1,   -1,   81,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   92,   93,   94,   95,   96,   97,   98,
   99,  100,  101,  102,  103,  104,  105,  106,   -1,   -1,
   -1,  110,   -1,   -1,   -1,  273,  274,  275,  276,  118,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  145,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  278,  279,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
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
"OR","AND","CAST","UNARIO",
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
"sentencia : condicionales",
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
"condicionales : IF expresion ':' cuerpo else",
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

//#line 201 "../../src/parser/parser.y"
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
//#line 548 "Parser.java"
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
