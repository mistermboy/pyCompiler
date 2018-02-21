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
    0,    1,    1,    2,    2,    4,    6,    6,    5,    5,
   10,   10,   11,    7,    7,    3,   12,   12,    9,    9,
    9,    9,    9,   13,   13,   14,    8,    8,   15,   15,
   15,   15,   15,   15,   15,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   16,   16,
   18,   20,   19,   23,   23,   22,   21,   24,   24,   25,
   25,
};
final static short yylen[] = {                            2,
    9,    2,    0,    2,    1,   11,    1,    1,    0,    1,
    1,    3,    3,    0,    3,    3,    1,    3,    1,    1,
    1,    4,    4,    1,    2,    4,    0,    2,    3,    3,
    3,    2,    1,    1,    2,    1,    1,    1,    1,    3,
    4,    3,    4,    2,    2,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    1,    3,
    3,    6,    5,    0,    2,    3,    4,    0,    1,    1,
    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   17,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   19,   20,   21,    0,    0,
   16,   18,    0,    0,    0,   11,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   24,    0,   13,    0,   12,
    0,    0,   23,   25,   22,    8,    0,    7,    0,    0,
   14,    1,   26,    0,    0,    0,   15,   37,    0,    0,
    0,    0,    0,    0,   39,   38,    0,    0,    0,    6,
   28,    0,    0,   33,   34,    0,   36,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   32,   35,   30,    0,   29,    0,
    0,   31,   70,    0,    0,    0,   40,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   27,   27,    0,   67,    0,    0,
   41,    0,    0,    0,   63,   71,   62,   66,   65,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,   24,   47,   54,   56,   21,   25,
   26,    8,   35,   36,   71,   78,   72,   73,   74,   75,
   76,  137,  145,  114,  115,
};
final static short yysindex[] = {                         0,
    0, -225, -233,    0,    0,  -17,    0,  -42,   14,   15,
    0,  -74, -205, -199,   38,    0,    0,    0,  -33, -176,
    0,    0,   33,   51,   56,    0,   43, -167,   22,  -74,
   49, -199, -153,   -6, -114,    0,  -74,    0,   47,    0,
    1,  -74,    0,    0,    0,    0,    3,    0,   -8,   68,
    0,    0,    0, -167,   69,  277,    0,    0,  184,  184,
  184,  184,  184,   90,    0,    0,  184,  184,  320,    0,
    0,   97,   76,    0,    0,   77,    0,  -41,  405,  -32,
  118,  125,  152, -133,  -45,  -45,  100,  159,  184,  184,
  184,  184,  184,  184,  184,  184,  184,  184,  184,  184,
  184,  184,  184,  184,    0,    0,    0,  184,    0,   28,
   29,    0,    0,  112,  122,  184,    0,  785,  752,  466,
  724,  431,  455,  405,  759,  238,  809,  296,  -27,   -3,
  -45,   78,  185,  405,    0,    0,  -90,    0,  -96,  -45,
    0,  311,  341,   29,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  134,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  136,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  492,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  211,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -31,    0,
    0,    0,    0,  138,  -11,   16,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  141,    0,    0,  606,  629,  659,
  650,  675,  654,  127,  599,  500,  593,  563,   88,   61,
   25,  -37,    0,  -30,    0,    0,  374,    0,    0,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  130,    0,    0,    0,    0,  -95,  240,    0,
  171,   45,    0,  172,    0,  148,  880,    0,    0,    0,
    0,   66,    0,    0,    0,
};
final static int YYTABLESIZE=1032;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
  103,   13,  108,   42,   42,   42,   42,   42,   42,   42,
   43,  108,   59,   60,  102,   12,   20,  107,  103,  101,
   42,   42,   42,   42,   42,   44,  109,   59,   60,   44,
   44,   44,   44,   44,    3,   44,    9,   13,  102,  142,
  143,   11,  103,   10,    4,  104,   44,   44,   44,   44,
   44,   42,   45,   14,   15,   42,   45,   45,   45,   45,
   45,   46,   45,  104,   22,   46,   46,   46,   46,   46,
   23,   46,   34,   45,   45,   45,   45,   45,   27,   34,
   29,   44,   46,   46,   46,   46,   46,  104,   43,   28,
   30,   31,   43,   43,   43,   43,   43,   47,   43,   32,
   33,   47,    4,   47,   47,   47,   39,   47,   45,   43,
   43,   43,   43,   43,   37,   41,   52,   46,   47,   47,
   47,   47,   47,   49,   48,   51,   53,   57,   48,   84,
   48,   48,   48,  100,  105,  106,  113,   20,  102,   99,
  116,   98,  103,  101,   43,   48,   48,   48,   48,   48,
  135,  136,  138,   47,  100,    4,   96,   95,   97,  102,
   99,  100,   98,  103,  101,  139,  102,   99,  104,   98,
  103,  101,  144,  146,    9,  110,   10,   96,   68,   97,
   48,   69,  111,   55,   96,   61,   97,  104,  100,   16,
   17,   18,   19,  102,   99,  100,   98,  103,  101,  117,
  102,   99,   40,   98,  103,  101,   44,   80,  104,  149,
  112,   96,    0,   97,    0,  104,   68,    0,   96,    0,
   97,  100,    0,   69,    0,    0,  102,   99,   67,   98,
  103,  101,    0,    0,    0,   42,   42,   42,   42,    0,
   42,   42,  104,    0,   96,    0,   97,   36,    0,  104,
    0,    0,   36,   36,    0,   36,   36,   36,    0,    0,
    0,   44,   44,   44,   44,    0,   44,   44,    0,   38,
   36,   36,   36,    0,  100,  104,   45,  141,   48,  102,
   99,   50,   98,  103,  101,    0,    0,    0,   45,   45,
   45,   45,    0,   45,   45,    0,    0,   46,   46,   46,
   46,   36,   46,   46,    0,    0,    0,    0,   87,   68,
   16,   17,   18,   19,    0,   46,   69,    0,    0,    0,
    0,   67,    0,    0,   43,   43,   43,   43,  104,   43,
   43,    0,  100,   47,   47,   47,   47,  102,   47,   47,
    0,  103,  101,   68,    0,    0,    0,    0,    0,    0,
   69,    0,   68,    0,    0,   67,    0,    0,    0,   69,
   48,   48,   48,   48,   67,   48,   48,    0,    0,   89,
   90,   91,   92,   68,   93,   94,    0,    0,    0,    0,
   69,    0,    0,    0,    0,   67,  104,    0,    0,    0,
   89,   90,   91,   92,    0,   93,   94,   89,   90,   91,
   92,   70,   93,   94,    0,    0,   64,    0,    0,    0,
   20,    0,    0,   64,    0,    0,    0,    0,   64,    0,
    0,    0,    0,    0,   89,   90,   91,   92,    0,   93,
   94,   89,   90,   91,   92,  147,   93,   94,    0,    0,
   58,  100,    0,    0,    0,    0,  102,   99,    0,   98,
  103,  101,    0,   77,   65,   66,    0,   89,   90,   91,
   92,    0,   93,   94,   96,  148,   97,  100,    0,    0,
    0,    0,  102,   99,    0,   98,  103,  101,    0,    0,
    0,    0,    0,   36,   36,   36,   36,    0,   36,   36,
   96,  100,   97,    0,    0,  104,  102,   99,   64,   98,
  103,  101,  100,    0,    0,    0,    0,  102,   99,    0,
   98,  103,  101,    0,   96,    0,   97,    0,    0,    0,
    0,  104,    0,    0,   27,   96,    0,   97,    0,    0,
    0,   27,    0,   58,   59,   60,   27,   61,   62,    0,
   51,    0,    0,   51,   63,  104,   64,   65,   66,    0,
    0,    0,    0,    0,    0,    0,  104,   51,   51,   51,
   51,   51,    0,    0,    0,    0,    0,   58,   59,   60,
    0,   61,   62,    0,    0,    0,   58,    0,   63,    0,
   64,   65,   66,   16,   17,   18,   19,    0,    0,   77,
   65,   66,   51,    0,    0,    0,    0,   58,   59,   60,
    0,   61,   62,   49,    0,   49,   49,   49,   63,    0,
   64,   65,   66,    0,    0,    0,   27,    0,    0,    0,
   49,   49,   49,   49,   49,    0,    0,    0,    0,    0,
   64,   64,   64,   50,   64,   64,   50,   50,    0,   53,
    0,   64,   53,   64,   64,   64,   52,    0,    0,   52,
   50,   50,   50,   50,   50,   49,   53,   53,   53,   53,
    0,    0,    0,   52,   52,   52,   52,    0,    0,   54,
    0,    0,   54,    0,    0,    0,    0,   89,   90,   91,
   92,    0,   93,   94,    0,   50,   54,   54,    0,   54,
   55,   53,    0,   55,   57,    0,    0,   57,   52,   56,
    0,    0,   56,   89,   90,   91,   92,   55,   55,   94,
   55,   57,   57,    0,   57,   58,   56,   56,   58,   56,
    0,   54,    0,    0,    0,    0,    0,   89,   90,   91,
   92,    0,   58,   58,    0,   58,    0,    0,   89,   90,
    0,   92,   55,    0,    0,    0,   57,    0,   27,   27,
   27,   56,   27,   27,    0,    0,    0,    0,    0,   27,
  100,    0,   27,   27,    0,  102,   99,   58,   98,  103,
  101,    0,   51,   51,   51,   51,    0,   51,   51,    0,
    0,    0,    0,   96,    0,   97,    0,    0,  100,    0,
    0,    0,    0,  102,   99,  100,   98,  103,  101,    0,
  102,   99,    0,   98,  103,  101,    0,    0,    0,    0,
    0,   96,    0,   97,  104,    0,    0,    0,    0,    0,
   97,  100,    0,    0,    0,    0,  102,   99,    0,   98,
  103,  101,    0,    0,    0,   49,   49,   49,   49,    0,
   49,   49,  104,    0,    0,  100,   97,    0,    0,  104,
  102,   99,    0,    0,  103,  101,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,   50,   50,   50,    0,
   50,   50,   53,   53,   53,  104,   53,   53,   52,   52,
   52,   52,    0,   52,   52,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  104,
    0,    0,   54,   54,   54,    0,   54,   54,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   55,   55,    0,   55,   55,    0,
    0,   57,   57,   56,    0,    0,   56,   56,   79,   79,
   81,   82,   83,    0,    0,    0,   85,   86,   88,    0,
    0,    0,   58,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  118,  119,
  120,  121,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,    0,    0,    0,  134,    0,    0,
    0,    0,    0,    0,    0,  140,   89,   90,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   89,    0,    0,    0,    0,    0,
    0,   89,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   46,   44,   44,   41,   42,   43,   44,   45,   46,   47,
  125,   44,   44,   44,   42,   58,   91,   59,   46,   47,
   58,   59,   60,   61,   62,   37,   59,   59,   59,   41,
   42,   43,   44,   45,  260,   47,  270,   44,   42,  135,
  136,   59,   46,  277,  270,   91,   58,   59,   60,   61,
   62,   58,   37,   40,   40,   93,   41,   42,   43,   44,
   45,   37,   47,   91,  270,   41,   42,   43,   44,   45,
  270,   47,   28,   58,   59,   60,   61,   62,   41,   35,
  257,   93,   58,   59,   60,   61,   62,   91,   37,  123,
   58,   41,   41,   42,   43,   44,   45,   37,   47,   44,
   58,   41,  270,   43,   44,   45,   58,   47,   93,   58,
   59,   60,   61,   62,   93,  269,  125,   93,   58,   59,
   60,   61,   62,  123,   37,  123,   59,   59,   41,   40,
   43,   44,   45,   37,   59,   59,  270,   91,   42,   43,
   41,   45,   46,   47,   93,   58,   59,   60,   61,   62,
  123,  123,   41,   93,   37,  270,   60,   61,   62,   42,
   43,   37,   45,   46,   47,   44,   42,   43,   91,   45,
   46,   47,  263,  270,   41,   58,   41,   60,   41,   62,
   93,   41,   58,   54,   60,   59,   62,   91,   37,  264,
  265,  266,  267,   42,   43,   37,   45,   46,   47,   41,
   42,   43,   32,   45,   46,   47,   35,   60,   91,  144,
   59,   60,   -1,   62,   -1,   91,   33,   -1,   60,   -1,
   62,   37,   -1,   40,   -1,   -1,   42,   43,   45,   45,
   46,   47,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   91,   -1,   60,   -1,   62,   37,   -1,   91,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,   30,
   60,   61,   62,   -1,   37,   91,   37,   93,   39,   42,
   43,   42,   45,   46,   47,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,  273,  274,  275,
  276,   91,  278,  279,   -1,   -1,   -1,   -1,   69,   33,
  264,  265,  266,  267,   -1,  269,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,  273,  274,  275,  276,   91,  278,
  279,   -1,   37,  273,  274,  275,  276,   42,  278,  279,
   -1,   46,   47,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   33,   -1,   -1,   45,   -1,   -1,   -1,   40,
  273,  274,  275,  276,   45,  278,  279,   -1,   -1,  273,
  274,  275,  276,   33,  278,  279,   -1,   -1,   -1,   -1,
   40,   -1,   -1,   -1,   -1,   45,   91,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,  273,  274,  275,
  276,  125,  278,  279,   -1,   -1,   33,   -1,   -1,   -1,
   91,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,
  279,  273,  274,  275,  276,  125,  278,  279,   -1,   -1,
  257,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,  270,  271,  272,   -1,  273,  274,  275,
  276,   -1,  278,  279,   60,  125,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   60,   37,   62,   -1,   -1,   91,   42,   43,  125,   45,
   46,   47,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   60,   -1,   62,   -1,   -1,   -1,
   -1,   91,   -1,   -1,   33,   60,   -1,   62,   -1,   -1,
   -1,   40,   -1,  257,  258,  259,   45,  261,  262,   -1,
   41,   -1,   -1,   44,  268,   91,  270,  271,  272,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,  261,  262,   -1,   -1,   -1,  257,   -1,  268,   -1,
  270,  271,  272,  264,  265,  266,  267,   -1,   -1,  270,
  271,  272,   93,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,  261,  262,   41,   -1,   43,   44,   45,  268,   -1,
  270,  271,  272,   -1,   -1,   -1,  125,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,   41,  261,  262,   44,   45,   -1,   41,
   -1,  268,   44,  270,  271,  272,   41,   -1,   -1,   44,
   58,   59,   60,   61,   62,   93,   58,   59,   60,   61,
   -1,   -1,   -1,   58,   59,   60,   61,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,  278,  279,   -1,   93,   58,   59,   -1,   61,
   41,   93,   -1,   44,   41,   -1,   -1,   44,   93,   41,
   -1,   -1,   44,  273,  274,  275,  276,   58,   59,  279,
   61,   58,   59,   -1,   61,   41,   58,   59,   44,   61,
   -1,   93,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,   58,   59,   -1,   61,   -1,   -1,  273,  274,
   -1,  276,   93,   -1,   -1,   -1,   93,   -1,  257,  258,
  259,   93,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,
   37,   -1,  271,  272,   -1,   42,   43,   93,   45,   46,
   47,   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,
   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,   37,   -1,
   -1,   -1,   -1,   42,   43,   37,   45,   46,   47,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   60,   -1,   62,   91,   -1,   -1,   -1,   -1,   -1,
   62,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   91,   -1,   -1,   37,   62,   -1,   -1,   91,
   42,   43,   -1,   -1,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,  274,  275,  276,   91,  278,  279,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   -1,  274,  275,  276,   -1,  278,  279,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  275,  276,   -1,  278,  279,   -1,
   -1,  278,  279,  275,   -1,   -1,  278,  279,   59,   60,
   61,   62,   63,   -1,   -1,   -1,   67,   68,   69,   -1,
   -1,   -1,  278,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   89,   90,
   91,   92,   93,   94,   95,   96,   97,   98,   99,  100,
  101,  102,  103,  104,   -1,   -1,   -1,  108,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  116,  273,  274,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,   -1,   -1,
   -1,  273,
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
"sentencia : RETURN expresion ';'",
"sentencia : asignacion ';'",
"sentencia : condicionales",
"sentencia : while",
"sentencia : invocacion ';'",
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
"list : expresion",
"list : list ',' expresion",
"asignacion : expresion '=' expresion",
"while : WHILE expresion ':' '{' sentencias '}'",
"condicionales : IF expresion ':' cuerpo else",
"else :",
"else : ELSE cuerpo",
"cuerpo : '{' sentencias '}'",
"invocacion : ID '(' args ')'",
"args :",
"args : arg",
"arg : ID",
"arg : arg ',' ID",
};

//#line 200 "../../src/parser/parser.y"
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
//#line 554 "Parser.java"
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
