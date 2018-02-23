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

import ast.*;

//#line 26 "Parser.java"




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
    6,    6,   10,   10,   11,    8,    8,    4,   12,   12,
    7,    7,    7,    7,    7,    7,   13,   13,   14,    9,
    9,   15,   15,   15,   15,   15,   15,   15,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   16,   16,   20,   18,   19,   23,   22,
   22,   24,   25,   21,   21,   26,   26,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    2,    0,
    0,    1,    1,    3,    3,    2,    3,    3,    1,    3,
    1,    1,    1,    4,    4,    1,    1,    2,    4,    1,
    2,    3,    3,    3,    1,    1,    1,    2,    1,    1,
    1,    1,    3,    4,    3,    4,    2,    2,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    4,    1,    3,    6,    4,    5,    2,    1,
    1,    1,    3,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   19,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   21,   22,   23,    0,   26,
    0,   18,   20,    0,    0,    0,   13,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   27,    0,   15,    0,
   14,    0,    0,   25,   28,   24,    0,    0,    0,    0,
   40,    0,    0,    0,    0,    0,    0,   42,   41,    0,
    0,    0,    0,    0,    0,    0,   30,    0,   35,   36,
   37,   29,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,   16,    0,    0,   31,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   38,    6,   33,    0,
   32,    0,    0,   34,    0,    0,    0,    0,   43,   17,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   72,
    0,   70,   71,   63,    0,    0,   44,    0,    0,    0,
   68,    0,   66,   73,   69,
};
final static short yydgoto[] = {                          1,
    2,   63,    5,   64,    7,   25,   22,   65,   66,   26,
   27,    8,   36,   37,   67,   75,   68,   69,   70,   71,
  116,  141,  151,  142,  143,  117,
};
final static short yysindex[] = {                         0,
    0, -192, -233,    0,    0,  -46,    0,  -42,  -25,  -21,
    0,  108, -229, -201,   30,    0,    0,    0,  -51,    0,
 -176,    0,    0,   32,   50,   53,    0,   41, -165,   14,
  108,   56, -201, -161,   22, -108,    0,  108,    0,  108,
    0,   -5,  108,    0,    0,    0,    3,  485,   68,  485,
    0,  173,  173,  173,  173,  173,   93,    0,    0,  173,
  173,  267,   16,   83,  485,  502,    0,  149,    0,    0,
    0,    0,   18,   93,  -41,  431,  -32,  185,  211,  237,
  173,  -26,  -26,  109,  371,    0,    0,   94,  502,    0,
  173,  173,  173,  173,  173,  173,  173,  173,  173,  173,
  173,  173,  173,  173,  173,  173,    0,    0,    0,  173,
    0,   29,  298,    0,  431,  113,  119,  173,    0,    0,
  303,  303,  303,  303,  457,  457,  431,  303,  303,  187,
  187,  -26,  -26,  -26,   86,  405,  431,  502,  502,    0,
  -94,    0,    0,    0,  173,  -26,    0,  331,  370,  298,
    0,  431,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  138,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  146,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   63,    0,   63,
    0,    0,    0,    0,    0,    0,   15,    0,    0,    0,
    0,    0,    0,    0,   64,   65,    0,    0,    0,    0,
    0,    0,    0,  -37,    0,   -6,    0,    0,    0,    0,
  152,   42,   51,    0,    0,    0,    0,    0,   73,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,    0,  160,    0,    0,    0,
  628,  641,  678,  707,  156,  633,  -30,  747,  760,  520,
  563,   78,   87,  114,  -11,    0,   -4,    0,    0,    0,
  396,    0,    0,    0,    0,  123,    0,    0,    0,    0,
    0,   26,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  128,    0,   -1,    0,    0,  270,    0,  -22,    0,
  169,   10,    0,  168,  137,  159,  773,    0,    0,    0,
    0,   55,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1039;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
    6,   13,  110,   39,   39,   39,   39,   39,   39,   39,
   62,  110,   11,   62,   14,   12,   44,  109,   15,  105,
   39,   39,   39,   39,   39,   45,  111,   62,   62,   45,
   45,   45,   45,   45,   45,   45,    9,   64,   35,   65,
   23,   76,   89,   10,   76,   35,   45,   45,   45,   45,
   45,   39,   64,   39,   65,   39,   39,   39,   19,   39,
   39,   39,   62,   88,  106,   13,   77,    3,   24,   77,
   28,   29,   19,   39,   39,   39,   39,    4,   47,   43,
   30,   45,   47,   47,   47,   47,   47,   48,   47,   31,
   32,   48,   48,   48,   48,   48,   33,   48,   34,   47,
   47,   47,   47,   47,    4,   39,   38,   42,   48,   48,
   48,   48,   48,   40,   49,  148,  149,   48,   49,   49,
   49,   49,   49,   50,   49,   50,   72,   50,   50,   50,
   50,   50,   81,   50,   47,   49,   49,   49,   49,   49,
   86,   87,  108,   48,   50,   50,   50,   50,   50,  118,
   51,  138,  120,  144,   51,   51,   51,   51,   51,   46,
   51,    4,  145,   46,   46,   46,   46,   46,  150,   46,
   49,   51,   51,   51,   51,   51,  106,   73,   11,   50,
   46,   46,   46,   46,   46,  104,   12,   10,    7,    8,
  102,  101,   74,  100,  105,  103,   61,    9,   21,   61,
   75,   41,   90,   45,  155,   61,   51,  107,   98,   97,
   99,   77,   62,   61,   61,   46,   61,   60,    0,    0,
    0,  104,    0,  104,    0,   90,  102,  101,  102,  100,
  105,  103,  105,  103,    0,   39,   39,   39,   39,  106,
   39,   39,  112,    0,   98,   97,   99,  104,   61,  140,
    0,    0,  102,  101,    0,  100,  105,  103,    0,    0,
    0,   45,   45,   45,   45,    0,   45,   45,  113,    0,
   98,   97,   99,  104,    0,  106,    0,  106,  102,  101,
    0,  100,  105,  103,   90,   90,  140,   39,   39,   39,
   39,    0,   39,   39,    0,  114,   98,   97,   99,   61,
   39,  106,    0,    0,    0,    0,   62,   46,    0,   47,
    0,   60,   49,    0,   47,   47,   47,   47,    0,   47,
   47,    0,    0,   48,   48,   48,   48,  106,   48,   48,
   61,   84,    0,    0,    0,    0,    0,   62,    0,  104,
    0,    0,   60,    0,  102,  101,    0,  100,  105,  103,
   49,   49,   49,   49,    0,   49,   49,   21,    0,   50,
   50,   50,   50,   61,   50,   50,    0,    0,    0,    0,
   62,   16,   17,   18,   19,   60,   20,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   51,   51,   51,   51,
    0,   51,   51,  106,    0,   46,   46,   46,   46,    0,
   46,   46,   61,    0,    0,    0,    0,  104,    0,   62,
    0,  119,  102,  101,   60,  100,  105,  103,    0,    0,
  139,   91,   92,   93,   94,    0,   95,   96,   67,   51,
   98,   97,   99,   61,   61,   67,    0,    0,    0,    0,
   67,  104,   74,   58,   59,    0,  102,  101,    0,  100,
  105,  103,    0,    0,    0,  153,    0,   91,   92,   93,
   94,  106,   95,   96,   98,   97,   99,  104,    0,    0,
    0,    0,  102,  101,    0,  100,  105,  103,    0,    0,
    0,    0,    0,   91,   92,   93,   94,    0,   95,   96,
   98,   97,   99,  104,  154,  106,    0,  147,  102,  101,
    0,  100,  105,  103,    0,    0,    0,    0,    0,   91,
   92,   93,   94,    0,   95,   96,   98,   61,   99,    0,
   67,  106,    0,   51,   62,    0,    0,    0,    0,   60,
   16,   17,   18,   19,   61,   20,   74,   58,   59,    0,
    0,   62,    0,    0,    0,    0,   60,  106,    0,    0,
    0,    0,    0,    0,   51,   52,   53,    0,   54,   55,
   53,    0,   53,   53,   53,   56,    0,   74,   58,   59,
    0,    0,    0,    0,    0,    0,    0,   53,   53,   53,
   53,   53,    0,    0,    0,    0,    0,   51,   52,   53,
    0,   54,   55,    0,    0,    0,    0,    0,   56,    0,
   74,   58,   59,   52,    0,   52,   52,   52,    0,    0,
    0,    0,   53,    0,    0,    0,    0,    0,    0,    0,
   52,   52,   52,   52,   52,    0,   51,   52,   53,    0,
   54,   55,    0,    0,    0,    0,    0,   56,    0,   74,
   58,   59,    0,   91,   92,   93,   94,    0,   95,   96,
    0,    0,   67,   67,   67,   52,   67,   67,    0,    0,
    0,    0,    0,   67,    0,   67,   67,   67,   55,    0,
    0,   55,    0,   60,    0,    0,   60,   91,   92,   93,
   94,   57,   95,   96,   57,   55,   55,   55,   55,   55,
   60,   60,    0,   60,    0,    0,    0,    0,   57,   57,
   57,   57,   57,   91,   92,   93,   94,    0,   95,   96,
    0,    0,    0,    0,    0,    0,    0,    0,   59,    0,
   55,   59,    0,    0,    0,   60,    0,    0,    0,   91,
   92,   93,   94,   57,    0,   59,   59,   59,   59,   59,
    0,   51,   52,   53,    0,   54,   55,   58,    0,    0,
   58,    0,   56,    0,   57,   58,   59,    0,   51,   52,
   53,    0,   54,   55,   58,   58,   58,   58,   58,   56,
   59,   74,   58,   59,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   56,    0,    0,
   56,    0,   53,   53,   53,   53,    0,   53,   53,   58,
   54,    0,    0,   54,   56,   56,   56,   56,   56,    0,
    0,    0,    0,    0,    0,    0,    0,   54,   54,   54,
   54,   54,    0,    0,   76,   76,   78,   79,   80,    0,
    0,    0,   82,   83,   85,   52,   52,   52,   52,   56,
   52,   52,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   54,  115,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  121,  122,  123,  124,  125,  126,  127,
  128,  129,  130,  131,  132,  133,  134,  135,  136,    0,
    0,    0,  137,    0,    0,    0,    0,    0,    0,    0,
  146,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   55,   55,   55,   55,    0,   55,   55,    0,    0,    0,
   60,   60,    0,   57,   57,   57,   57,  152,   57,   57,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   59,   59,   59,   59,    0,   59,   59,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   58,
   58,   58,   58,    0,   58,   58,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   56,
   56,   56,   56,    0,   56,   56,    0,    0,    0,    0,
    0,    0,   54,   54,   54,   54,    0,   54,   54,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    2,   44,   44,   41,   42,   43,   44,   45,   46,   47,
   41,   44,   59,   44,   40,   58,  125,   59,   40,   46,
   58,   59,   60,   61,   62,   37,   59,   58,   59,   41,
   42,   43,   44,   45,   46,   47,  270,   44,   29,   44,
  270,   41,   65,  277,   44,   36,   58,   59,   60,   61,
   62,   37,   59,   91,   59,   93,   42,   43,   44,   45,
   46,   47,   93,   65,   91,   44,   41,  260,  270,   44,
   41,  123,   58,   59,   60,   61,   62,  270,   37,   58,
  257,   93,   41,   42,   43,   44,   45,   37,   47,   58,
   41,   41,   42,   43,   44,   45,   44,   47,   58,   58,
   59,   60,   61,   62,  270,   91,   93,  269,   58,   59,
   60,   61,   62,   58,   37,  138,  139,  123,   41,   42,
   43,   44,   45,   37,   47,  123,   59,   41,   42,   43,
   44,   45,   40,   47,   93,   58,   59,   60,   61,   62,
  125,   59,  125,   93,   58,   59,   60,   61,   62,   41,
   37,  123,   59,   41,   41,   42,   43,   44,   45,   37,
   47,  270,   44,   41,   42,   43,   44,   45,  263,   47,
   93,   58,   59,   60,   61,   62,   91,   50,   41,   93,
   58,   59,   60,   61,   62,   37,   41,  125,  125,  125,
   42,   43,   41,   45,   46,   47,   41,  125,   91,   44,
   41,   33,   66,   36,  150,   33,   93,   59,   60,   61,
   62,   53,   40,   58,   59,   93,   61,   45,   -1,   -1,
   -1,   37,   -1,   37,   -1,   89,   42,   43,   42,   45,
   46,   47,   46,   47,   -1,  273,  274,  275,  276,   91,
  278,  279,   58,   -1,   60,   61,   62,   37,   93,  113,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   58,   -1,
   60,   61,   62,   37,   -1,   91,   -1,   91,   42,   43,
   -1,   45,   46,   47,  148,  149,  150,  273,  274,  275,
  276,   -1,  278,  279,   -1,   59,   60,   61,   62,   33,
   31,   91,   -1,   -1,   -1,   -1,   40,   38,   -1,   40,
   -1,   45,   43,   -1,  273,  274,  275,  276,   -1,  278,
  279,   -1,   -1,  273,  274,  275,  276,   91,  278,  279,
   33,   62,   -1,   -1,   -1,   -1,   -1,   40,   -1,   37,
   -1,   -1,   45,   -1,   42,   43,   -1,   45,   46,   47,
  273,  274,  275,  276,   -1,  278,  279,   91,   -1,  273,
  274,  275,  276,   33,  278,  279,   -1,   -1,   -1,   -1,
   40,  264,  265,  266,  267,   45,  269,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
   -1,  278,  279,   91,   -1,  273,  274,  275,  276,   -1,
  278,  279,   33,   -1,   -1,   -1,   -1,   37,   -1,   40,
   -1,   41,   42,   43,   45,   45,   46,   47,   -1,   -1,
  123,  273,  274,  275,  276,   -1,  278,  279,   33,  257,
   60,   61,   62,  278,  279,   40,   -1,   -1,   -1,   -1,
   45,   37,  270,  271,  272,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  125,   -1,  273,  274,  275,
  276,   91,  278,  279,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   60,   61,   62,   37,  125,   91,   -1,   93,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   60,   33,   62,   -1,
  125,   91,   -1,  257,   40,   -1,   -1,   -1,   -1,   45,
  264,  265,  266,  267,   33,  269,  270,  271,  272,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   45,   91,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,   -1,  261,  262,
   41,   -1,   43,   44,   45,  268,   -1,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,
  270,  271,  272,   41,   -1,   43,   44,   45,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   -1,  257,  258,  259,   -1,
  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,  270,
  271,  272,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,  257,  258,  259,   93,  261,  262,   -1,   -1,
   -1,   -1,   -1,  268,   -1,  270,  271,  272,   41,   -1,
   -1,   44,   -1,   41,   -1,   -1,   44,  273,  274,  275,
  276,   41,  278,  279,   44,   58,   59,   60,   61,   62,
   58,   59,   -1,   61,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,
   93,   44,   -1,   -1,   -1,   93,   -1,   -1,   -1,  273,
  274,  275,  276,   93,   -1,   58,   59,   60,   61,   62,
   -1,  257,  258,  259,   -1,  261,  262,   41,   -1,   -1,
   44,   -1,  268,   -1,  270,  271,  272,   -1,  257,  258,
  259,   -1,  261,  262,   58,   59,   60,   61,   62,  268,
   93,  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,
   44,   -1,  273,  274,  275,  276,   -1,  278,  279,   93,
   41,   -1,   -1,   44,   58,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   -1,   -1,   52,   53,   54,   55,   56,   -1,
   -1,   -1,   60,   61,   62,  273,  274,  275,  276,   93,
  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   81,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   92,   93,   94,   95,   96,   97,
   98,   99,  100,  101,  102,  103,  104,  105,  106,   -1,
   -1,   -1,  110,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  118,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,
  278,  279,   -1,  273,  274,  275,  276,  145,  278,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
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
"funcion : DEF ID '(' params ')' ':' tipo '{' body '}'",
"body : defs",
"body : sentencias",
"body : defs sentencias",
"body :",
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
"tipo : VOID",
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
"while : WHILE expresion ':' '{' sentencias '}'",
"condicionalSimple : IF expresion ':' cuerpo",
"condicionalComplejo : IF expresion ':' cuerpo else",
"else : ELSE cuerpo",
"cuerpo : cuerpoSimple",
"cuerpo : cuerpoComplejo",
"cuerpoSimple : sentencia",
"cuerpoComplejo : '{' sentencias '}'",
"args :",
"args : arg",
"arg : expresion",
"arg : arg ',' expresion",
};

//#line 205 "../../src/parser/parser.y"
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


private ASTNode ast;

public ASTNode getAST(){
	return ast;
}


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
//#line 575 "Parser.java"
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
