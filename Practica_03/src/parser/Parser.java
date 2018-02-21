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
    9,    9,   16,   16,   16,   16,   16,   16,   16,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   17,   17,   19,   21,   20,   25,   25,   24,
   24,   26,   27,   23,   23,   22,   28,   28,   29,   29,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    2,    0,
    1,    1,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    2,    1,    1,    2,    1,
    1,    1,    1,    3,    4,    3,    4,    2,    2,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    1,    3,    3,    6,    5,    0,    2,    1,
    1,    1,    3,    0,    1,    4,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,   12,    0,   11,    0,    0,
    0,   41,    0,    0,    0,    0,    0,    0,   43,   42,
    0,    0,    0,    0,    0,    0,    0,   31,    0,    0,
   37,   38,    0,   30,    0,   40,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    1,   18,    0,
    0,    0,   32,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   36,
   39,    6,   34,    0,   33,    0,    0,   35,   79,    0,
    0,    0,   44,   19,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   72,    0,   70,   71,   76,    0,    0,
   45,    0,    0,    0,    0,   67,   80,   66,   73,   69,
};
final static short yydgoto[] = {                          1,
    2,   64,    5,   65,    7,   24,   47,   66,   67,   21,
   25,   26,    8,   35,   36,   68,   77,   69,   70,   71,
   72,   73,  153,  145,  156,  146,  147,  120,  121,
};
final static short yysindex[] = {                         0,
    0, -225, -259,    0,    0,  -57,    0,   57,   -2,    1,
    0,   87, -257, -228,    3,    0,    0,    0,  -77, -203,
    0,    0,    7,   30,   46,    0,   33, -178,   27,   87,
   42, -228, -143,   58, -109,    0,   87,    0,  -90,    0,
    4,   87,    0,    0,    0,    0,    5,    0,  367,   71,
  367,    0,  275,  275,  275,  275,  275,   96,    0,    0,
  275,  275,  315,   12,   80,  367,  396,    0,  123,   84,
    0,    0,   85,    0,   26,    0,  -32,  431,  -30,  149,
  185,  211, -117,  -18,  -18,  113,  343,    0,    0,  104,
  396,   96,    0,  275,  275,  275,  275,  275,  275,  275,
  275,  275,  275,  275,  275,  275,  275,  275,  275,    0,
    0,    0,    0,  275,    0,   41,  272,    0,    0,  139,
  129,  275,    0,    0,  800,  774,  551,  753,  457,  464,
  431,  786,  232,  814,  125,  -27,   -3,  -18,   91,  405,
  431,  396,  396,    0,  -76,    0,    0,    0,  -82,  -18,
    0,  396,   64,   68,  272,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  156,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  157,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   77,    0,
   77,    0,    0,    0,    0,    0,    0,   61,    0,    0,
    0,    0,    0,    0,    0,   79,   81,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -7,    0,    0,
    0,    0,  164,  -11,   16,    0,    0,    0,    0,    0,
   90,  371,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  178,    0,    0,    0,  641,  510,  713,  704,  548,  159,
  153,  683,  477,  653,  628,   97,   88,   25,  -37,    0,
   -4,   99,   99,    0,  305,    0,    0,    0,    0,   52,
    0,  100,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  170,    0,   15,    0,    0,    0,    0,  -63,  171,
    0,  194,   89,    0,  198,  132,  175,  910,    0,    0,
    0,    0,   92,   95,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1059;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         46,
   20,   11,   91,   46,   46,   46,   46,   46,   46,   46,
    9,  114,   22,  114,  107,   43,    6,   10,  108,  106,
   46,   46,   46,   46,   46,   48,  113,  108,  115,   48,
   48,   48,   48,   48,    3,   48,   63,   14,  107,   64,
   15,   23,  108,   27,    4,   28,   48,   48,   48,   48,
   48,   63,   49,   29,   64,   46,   49,   49,   49,   49,
   49,   50,   49,  109,   30,   50,   50,   50,   50,   50,
   31,   50,  109,   49,   49,   49,   49,   49,  152,  152,
   90,   48,   50,   50,   50,   50,   50,  109,   47,   32,
   33,    4,   47,   47,   47,   47,   47,   40,   47,   39,
   13,   13,   40,   40,   21,   40,   40,   40,   49,   47,
   47,   47,   47,   47,   12,   42,   34,   50,   21,   37,
   40,   40,   40,   34,   51,   41,   49,   51,   51,   74,
   51,   51,   51,   52,   51,   83,   88,   52,   89,   52,
   52,   52,  110,  111,   47,   51,   51,   51,   51,   51,
  112,   40,  119,  122,   52,   52,   52,   52,   52,  105,
    4,  105,  124,  142,  107,  104,  107,  103,  108,  106,
  108,  106,  149,   16,   17,   18,   19,   20,   46,  148,
   51,  109,  101,  100,  102,  105,  155,  157,  158,   52,
  107,  104,  159,  103,  108,  106,   13,   14,   93,   61,
   38,   10,   61,    7,   77,    8,  116,   45,  101,   48,
  102,   65,   50,  109,    9,  109,   61,   61,   78,   61,
   75,  105,   93,   74,   75,   40,  107,  104,   79,  103,
  108,  106,   44,   86,  154,   46,   46,   46,   46,  109,
   46,   46,  117,    0,  101,    0,  102,  105,  144,  160,
    0,   61,  107,  104,    0,  103,  108,  106,    0,    0,
    0,   48,   48,   48,   48,    0,   48,   48,  105,  118,
  101,    0,  102,  107,  104,  109,  103,  108,  106,    0,
    0,    0,    0,   93,    0,    0,  144,    0,   49,   49,
   49,   49,    0,   49,   49,    0,    0,   50,   50,   50,
   50,  109,   50,   50,   62,    0,    0,   62,    0,    0,
    0,   63,    0,    0,   63,    0,   61,    0,    0,   61,
    0,    0,  109,    0,   47,   47,   47,   47,    0,   47,
   47,    0,    0,   40,   40,   40,   40,   68,   40,   40,
    0,    0,    0,    0,   68,    0,    0,   62,    0,   68,
   16,   17,   18,   19,   63,    0,    0,    0,    0,   61,
   51,   51,   51,   51,    0,   51,   51,    0,    0,   52,
   52,   52,   52,    0,   52,   52,    0,    0,    0,  105,
    0,    0,    0,  123,  107,  104,    0,  103,  108,  106,
    0,    0,    0,    0,  143,   94,   95,   96,   97,   62,
   98,   99,  101,    0,  102,   20,   63,   40,    0,    0,
    0,   61,   40,   40,    0,   40,   40,   40,    0,    0,
    0,   94,   95,   96,   97,    0,   98,   99,   62,   68,
   40,   40,   40,  109,    0,   63,   61,   61,    0,    0,
   61,  105,    0,    0,    0,    0,  107,  104,    0,  103,
  108,  106,    0,    0,    0,    0,    0,   94,   95,   96,
   97,   40,   98,   99,  101,    0,  102,  105,    0,    0,
    0,    0,  107,  104,    0,  103,  108,  106,    0,    0,
    0,    0,    0,   94,   95,   96,   97,    0,   98,   99,
  101,    0,  102,  105,    0,  109,    0,  151,  107,  104,
  105,  103,  108,  106,    0,  107,  104,    0,  103,  108,
  106,    0,    0,    0,    0,    0,  101,   55,  102,    0,
   55,  109,    0,  101,    0,  102,    0,    0,   52,   53,
   54,   52,   55,   56,   55,   55,   55,   55,   55,   57,
    0,   92,   59,   60,   76,   59,   60,  109,    0,    0,
   58,    0,    0,   58,  109,    0,    0,    0,    0,    0,
    0,   68,   68,   68,    0,   68,   68,   58,   58,   55,
   58,   52,   68,    0,   68,   68,   68,    0,   16,   17,
   18,   19,    0,    0,   76,   59,   60,  105,   62,    0,
    0,   62,  107,  104,    0,  103,  108,  106,    0,    0,
    0,    0,   58,    0,    0,   62,   62,    0,   62,    0,
  101,    0,  102,    0,    0,   94,   95,   96,   97,    0,
   98,   99,    0,   52,   53,   54,    0,   55,   56,    0,
    0,    0,    0,    0,   57,    0,   58,   59,   60,    0,
   62,  109,    0,   40,   40,   40,   40,    0,   40,   40,
    0,    0,   52,   53,   54,    0,   55,   56,    0,    0,
    0,    0,    0,   57,    0,   92,   59,   60,   53,    0,
   53,   53,   53,    0,    0,    0,    0,   94,   95,   96,
   97,   56,   98,   99,   56,   53,   53,   53,   53,   53,
    0,    0,    0,   54,    0,    0,   54,   54,   56,   56,
   56,   56,    0,   94,   95,   96,   97,    0,   98,   99,
   54,   54,   54,   54,   54,    0,    0,    0,    0,    0,
   53,    0,    0,   57,    0,    0,   57,    0,    0,   94,
   95,   96,   97,   56,    0,   99,   94,   95,   96,   97,
   57,   57,   57,   57,   59,   54,    0,   59,    0,   55,
   55,   55,   55,   60,   55,   55,   60,    0,    0,    0,
    0,   59,   59,    0,   59,    0,    0,    0,    0,    0,
   60,   60,    0,   60,    0,   57,    0,    0,    0,    0,
    0,    0,    0,   58,   58,   58,    0,   58,   58,  105,
    0,    0,    0,    0,  107,  104,   59,  103,  108,  106,
    0,    0,    0,    0,    0,   60,    0,    0,    0,    0,
  105,    0,  101,    0,  102,  107,  104,    0,  103,  108,
  106,    0,  105,   94,   95,   62,   97,  107,  104,    0,
  103,  108,  106,  101,    0,  102,  105,    0,    0,    0,
    0,  107,  104,  109,  103,  108,  106,  102,    0,    0,
  105,    0,    0,    0,    0,  107,  104,    0,    0,  108,
  106,  102,    0,    0,  109,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  109,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  109,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   53,   53,   53,   53,  109,   53,   53,    0,    0,    0,
    0,    0,    0,   56,   56,   56,   56,    0,   56,   56,
    0,    0,    0,    0,    0,   54,   54,   54,   54,    0,
   54,   54,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   57,   57,   57,    0,
   57,   57,   78,   78,   80,   81,   82,    0,    0,    0,
   84,   85,   87,    0,    0,    0,    0,    0,   59,   59,
    0,   59,   59,    0,    0,    0,    0,   60,    0,    0,
   60,   60,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  125,  126,  127,  128,  129,  130,  131,
  132,  133,  134,  135,  136,  137,  138,  139,  140,    0,
    0,    0,    0,  141,    0,   94,   95,    0,    0,    0,
    0,  150,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   94,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   94,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   59,   66,   41,   42,   43,   44,   45,   46,   47,
  270,   44,  270,   44,   42,  125,    2,  277,   46,   47,
   58,   59,   60,   61,   62,   37,   59,   46,   59,   41,
   42,   43,   44,   45,  260,   47,   44,   40,   42,   44,
   40,  270,   46,   41,  270,  123,   58,   59,   60,   61,
   62,   59,   37,  257,   59,   93,   41,   42,   43,   44,
   45,   37,   47,   91,   58,   41,   42,   43,   44,   45,
   41,   47,   91,   58,   59,   60,   61,   62,  142,  143,
   66,   93,   58,   59,   60,   61,   62,   91,   37,   44,
   58,  270,   41,   42,   43,   44,   45,   37,   47,   58,
   44,   44,   42,   43,   44,   45,   46,   47,   93,   58,
   59,   60,   61,   62,   58,   58,   28,   93,   58,   93,
   60,   61,   62,   35,   37,  269,  123,  123,   41,   59,
   43,   44,   45,   37,   47,   40,  125,   41,   59,   43,
   44,   45,   59,   59,   93,   58,   59,   60,   61,   62,
  125,   91,  270,   41,   58,   59,   60,   61,   62,   37,
  270,   37,   59,  123,   42,   43,   42,   45,   46,   47,
   46,   47,   44,  264,  265,  266,  267,   91,  269,   41,
   93,   91,   60,   61,   62,   37,  263,  270,  125,   93,
   42,   43,  125,   45,   46,   47,   41,   41,   67,   41,
   30,  125,   44,  125,   41,  125,   58,   37,   60,   39,
   62,   59,   42,   91,  125,   91,   58,   59,   41,   61,
   51,   37,   91,  125,  125,   32,   42,   43,   54,   45,
   46,   47,   35,   63,  143,  273,  274,  275,  276,   91,
  278,  279,   58,   -1,   60,   -1,   62,   37,  117,  155,
   -1,   93,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   37,   59,
   60,   -1,   62,   42,   43,   91,   45,   46,   47,   -1,
   -1,   -1,   -1,  152,   -1,   -1,  155,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,  273,  274,  275,
  276,   91,  278,  279,   33,   -1,   -1,   33,   -1,   -1,
   -1,   40,   -1,   -1,   40,   -1,   45,   -1,   -1,   45,
   -1,   -1,   91,   -1,  273,  274,  275,  276,   -1,  278,
  279,   -1,   -1,  273,  274,  275,  276,   33,  278,  279,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   33,   -1,   45,
  264,  265,  266,  267,   40,   -1,   -1,   -1,   -1,   45,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,  123,  273,  274,  275,  276,   33,
  278,  279,   60,   -1,   62,   91,   40,   37,   -1,   -1,
   -1,   45,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   33,  125,
   60,   61,   62,   91,   -1,   40,  278,  279,   -1,   -1,
   45,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   91,  278,  279,   60,   -1,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   60,   -1,   62,   37,   -1,   91,   -1,   93,   42,   43,
   37,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   41,   62,   -1,
   44,   91,   -1,   60,   -1,   62,   -1,   -1,  257,  258,
  259,  257,  261,  262,   58,   59,   60,   61,   62,  268,
   -1,  270,  271,  272,  270,  271,  272,   91,   -1,   -1,
   41,   -1,   -1,   44,   91,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   -1,  261,  262,   58,   59,   93,
   61,  257,  268,   -1,  270,  271,  272,   -1,  264,  265,
  266,  267,   -1,   -1,  270,  271,  272,   37,   41,   -1,
   -1,   44,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   58,   59,   -1,   61,   -1,
   60,   -1,   62,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,  257,  258,  259,   -1,  261,  262,   -1,
   -1,   -1,   -1,   -1,  268,   -1,  270,  271,  272,   -1,
   93,   91,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,
   -1,   -1,   -1,  268,   -1,  270,  271,  272,   41,   -1,
   43,   44,   45,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   41,   -1,   -1,   44,   45,   58,   59,
   60,   61,   -1,  273,  274,  275,  276,   -1,  278,  279,
   58,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,  273,
  274,  275,  276,   93,   -1,  279,  273,  274,  275,  276,
   58,   59,   60,   61,   41,   93,   -1,   44,   -1,  273,
  274,  275,  276,   41,  278,  279,   44,   -1,   -1,   -1,
   -1,   58,   59,   -1,   61,   -1,   -1,   -1,   -1,   -1,
   58,   59,   -1,   61,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  274,  275,  276,   -1,  278,  279,   37,
   -1,   -1,   -1,   -1,   42,   43,   93,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   37,   -1,   60,   -1,   62,   42,   43,   -1,   45,   46,
   47,   -1,   37,  273,  274,  278,  276,   42,   43,   -1,
   45,   46,   47,   60,   -1,   62,   37,   -1,   -1,   -1,
   -1,   42,   43,   91,   45,   46,   47,   62,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   -1,   46,
   47,   62,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   91,  278,  279,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  274,  275,  276,   -1,
  278,  279,   53,   54,   55,   56,   57,   -1,   -1,   -1,
   61,   62,   63,   -1,   -1,   -1,   -1,   -1,  275,  276,
   -1,  278,  279,   -1,   -1,   -1,   -1,  275,   -1,   -1,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   94,   95,   96,   97,   98,   99,  100,
  101,  102,  103,  104,  105,  106,  107,  108,  109,   -1,
   -1,   -1,   -1,  114,   -1,  273,  274,   -1,   -1,   -1,
   -1,  122,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
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
"while : WHILE expresion ':' '{' sents '}'",
"condicionales : IF expresion ':' cuerpo else",
"else :",
"else : ELSE cuerpo",
"cuerpo : cuerpoSimple",
"cuerpo : cuerpoComplejo",
"cuerpoSimple : sentencia",
"cuerpoComplejo : '{' sents '}'",
"sents :",
"sents : sentencias",
"invocacion : ID '(' args ')'",
"args :",
"args : arg",
"arg : ID",
"arg : arg ',' ID",
};

//#line 214 "../../src/parser/parser.y"
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
//#line 570 "Parser.java"
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
