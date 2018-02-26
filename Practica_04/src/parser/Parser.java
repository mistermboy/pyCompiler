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
import java.util.*;

//#line 27 "Parser.java"




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
    0,    0,    0,    0,   45,    0,    0,    0,    0,   72,
    0,   70,   71,   63,    0,    0,   44,    0,    0,    0,
   68,    0,   66,   73,   69,
};
final static short yydgoto[] = {                          1,
    2,   63,    5,   64,    7,   25,   22,   65,   66,   26,
   27,    8,   36,   37,   67,   75,   68,   69,   70,   71,
  116,  141,  151,  142,  143,  117,
};
final static short yysindex[] = {                         0,
    0, -189, -232,    0,    0,  -57,    0,   -3,    4,   33,
    0,  -90, -191, -182,   51,    0,    0,    0,  -23,    0,
 -150,    0,    0,   58,   76,   80,    0,   68, -143,   35,
  -90,   78, -182, -132,   57, -109,    0,  -90,    0,  -90,
    0,   20,  -90,    0,    0,    0,   28,  455,   93,  455,
    0,  248,  248,  248,  248,  248,  113,    0,    0,  248,
  248,  242,   38,  105,  455,  483,    0,  123,    0,    0,
    0,    0,   48,  113,  -41,  371,  -31,  149,  185,  211,
  248,  -26,  -26,  137,  343,    0,    0,  121,  483,    0,
  248,  248,  248,  248,  248,  248,  248,  248,  248,  248,
  248,  248,  248,  248,  -83,  248,    0,    0,    0,  248,
    0,   65,  272,    0,  371,  148,  153,  248,    0,    0,
  277,  277,  277,  277,  431,  431,  371,  277,  277,  125,
  125,  -26,  -26,  -26,    0,  405,  371,  483,  483,    0,
  -70,    0,    0,    0,  248,  -26,    0,  305,  367,  272,
    0,  371,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  157,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  158,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   77,    0,   77,
    0,    0,    0,    0,    0,    0,  -11,    0,    0,    0,
    0,    0,    0,    0,   79,   81,    0,    0,    0,    0,
    0,    0,    0,  -37,    0,  -17,    0,    0,    0,    0,
  160,   16,   25,    0,    0,    0,    0,    0,   83,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -30,    0,  164,    0,    0,    0,
  530,  615,  628,  641,  159,  225,  -29,  688,  730,  494,
  537,   52,   61,   88,    0,    0,   -7,    0,    0,    0,
  396,    0,    0,    0,    0,   97,    0,    0,    0,    0,
    0,   -1,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  162,    0,   17,    0,    0,  569,    0,  -48,    0,
  180,   10,    0,  179,  208,  166,  765,    0,    0,    0,
    0,   71,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1009;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         39,
   21,   11,  110,   39,   39,   39,   39,   39,   39,   39,
   76,   62,  110,   76,   62,   44,   89,  109,    6,  105,
   39,   39,   39,   39,   39,   39,   64,  111,   62,   62,
   39,   39,   19,   39,   39,   39,   65,    9,   35,   77,
   13,   64,   77,   14,   10,   35,   19,   39,   39,   39,
   39,   65,   47,   39,   12,   39,   47,   47,   47,   47,
   47,   48,   47,   62,  106,   48,   48,   48,   48,   48,
    3,   48,   15,   47,   47,   47,   47,   47,   23,   39,
    4,   88,   48,   48,   48,   48,   48,   24,   49,  148,
  149,   28,   49,   49,   49,   49,   49,   50,   49,   29,
   13,   50,   50,   50,   50,   50,   30,   50,   47,   49,
   49,   49,   49,   49,   43,   31,   32,   48,   50,   50,
   50,   50,   50,   33,   51,   34,    4,   38,   51,   51,
   51,   51,   51,   46,   51,   40,   42,   46,   46,   46,
   46,   46,   48,   46,   49,   51,   51,   51,   51,   51,
   50,   72,   81,   50,   46,   46,   46,   46,   46,  104,
    4,  104,   86,   87,  102,  101,  102,  100,  105,  103,
  105,  103,  108,   16,   17,   18,   19,  118,   20,  120,
   51,  107,   98,   97,   99,  104,  135,  138,  144,   46,
  102,  101,  150,  100,  105,  103,  145,   11,   12,   61,
   74,   10,   61,    7,   75,    8,  112,    9,   98,   97,
   99,   73,   41,  106,   45,  106,   61,   61,   77,   61,
  155,  104,    0,    0,    0,    0,  102,  101,    0,  100,
  105,  103,    0,    0,    0,   39,   39,   39,   39,  106,
   39,   39,  113,    0,   98,   97,   99,  104,    0,    0,
    0,   61,  102,  101,    0,  100,  105,  103,    0,    0,
    0,   39,   39,   39,   39,   60,   39,   39,   60,  114,
   98,   97,   99,   90,   61,  106,    0,    0,    0,    0,
   61,   62,   60,   60,    0,   60,   60,   62,   47,   47,
   47,   47,   60,   47,   47,    0,   90,   48,   48,   48,
   48,  106,   48,   48,   61,    0,    0,    0,    0,    0,
    0,   62,    0,  104,    0,    0,   60,   60,  102,  101,
  140,  100,  105,  103,   49,   49,   49,   49,    0,   49,
   49,    0,   21,   50,   50,   50,   50,   61,   50,   50,
    0,    0,    0,    0,   62,    0,    0,    0,    0,   60,
    0,    0,    0,    0,    0,   90,   90,  140,    0,    0,
   51,   51,   51,   51,    0,   51,   51,  106,    0,   46,
   46,   46,   46,    0,   46,   46,    0,    0,    0,  104,
    0,    0,    0,  119,  102,  101,    0,  100,  105,  103,
    0,    0,    0,    0,  139,   91,   92,   93,   94,   61,
   95,   96,   98,   97,   99,    0,   62,  104,    0,    0,
    0,   60,  102,  101,    0,  100,  105,  103,    0,    0,
    0,   91,   92,   93,   94,    0,   95,   96,   67,  153,
   98,   97,   99,  106,    0,   67,   61,   61,    0,    0,
   67,  104,    0,    0,    0,    0,  102,  101,    0,  100,
  105,  103,    0,    0,    0,    0,    0,   91,   92,   93,
   94,  106,   95,   96,   98,   97,   99,  104,    0,    0,
    0,    0,  102,  101,    0,  100,  105,  103,    0,    0,
    0,    0,    0,   91,   92,   93,   94,   61,   95,   96,
   98,  154,   99,    0,   62,  106,    0,  147,   51,   60,
    0,    0,   60,   60,   51,   16,   17,   18,   19,    0,
   20,   74,   58,   59,    0,   61,    0,   74,   58,   59,
   67,  106,   62,    0,    0,    0,    0,   60,   51,   52,
   53,    0,   54,   55,   53,    0,   53,   53,   53,   56,
    0,   74,   58,   59,    0,    0,    0,    0,    0,    0,
    0,   53,   53,   53,   53,   53,    0,    0,    0,    0,
    0,   51,   52,   53,    0,   54,   55,    0,    0,    0,
   55,    0,   56,   55,   74,   58,   59,   52,    0,   52,
   52,   52,    0,    0,    0,    0,   53,   55,   55,   55,
   55,   55,    0,    0,   52,   52,   52,   52,   52,   39,
    0,    0,    0,    0,    0,    0,   46,    0,   47,    0,
    0,   49,    0,    0,    0,   91,   92,   93,   94,    0,
   95,   96,   55,   51,   52,   53,    0,   54,   55,   52,
   84,    0,    0,    0,   56,    0,   74,   58,   59,    0,
    0,    0,    0,   91,   92,   93,   94,    0,   95,   96,
    0,    0,   67,   67,   67,   57,   67,   67,   57,    0,
    0,    0,    0,   67,    0,   67,   67,   67,   59,    0,
    0,   59,   57,   57,   57,   57,   57,   91,   92,   93,
   94,   58,   95,   96,   58,   59,   59,   59,   59,   59,
    0,    0,    0,    0,    0,    0,    0,    0,   58,   58,
   58,   58,   58,   91,   92,   93,   94,   57,    0,    0,
    0,   51,   52,   53,    0,   54,   55,    0,    0,    0,
   59,    0,   56,    0,   57,   58,   59,    0,   56,    0,
    0,   56,    0,   58,    0,    0,    0,    0,    0,   51,
   52,   53,    0,   54,   55,   56,   56,   56,   56,   56,
   56,    0,   74,   58,   59,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   53,   53,   53,   53,
   54,   53,   53,   54,    0,    0,    0,    0,    0,    0,
   56,    0,    0,    0,    0,    0,    0,   54,   54,   54,
   54,   54,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   55,   55,   55,   55,    0,   55,   55,   52,
   52,   52,   52,    0,   52,   52,   76,   76,   78,   79,
   80,    0,   54,    0,   82,   83,   85,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  115,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  121,  122,  123,  124,  125,
  126,  127,  128,  129,  130,  131,  132,  133,  134,    0,
  136,    0,    0,    0,  137,    0,    0,    0,    0,    0,
    0,    0,  146,    0,    0,    0,    0,   57,   57,   57,
   57,    0,   57,   57,    0,    0,    0,    0,    0,    0,
   59,   59,   59,   59,    0,   59,   59,    0,    0,  152,
    0,    0,    0,   58,   58,   58,   58,    0,   58,   58,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   56,   56,   56,   56,    0,   56,   56,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   54,   54,   54,   54,    0,   54,   54,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   59,   44,   41,   42,   43,   44,   45,   46,   47,
   41,   41,   44,   44,   44,  125,   65,   59,    2,   46,
   58,   59,   60,   61,   62,   37,   44,   59,   58,   59,
   42,   43,   44,   45,   46,   47,   44,  270,   29,   41,
   44,   59,   44,   40,  277,   36,   58,   59,   60,   61,
   62,   59,   37,   91,   58,   93,   41,   42,   43,   44,
   45,   37,   47,   93,   91,   41,   42,   43,   44,   45,
  260,   47,   40,   58,   59,   60,   61,   62,  270,   91,
  270,   65,   58,   59,   60,   61,   62,  270,   37,  138,
  139,   41,   41,   42,   43,   44,   45,   37,   47,  123,
   44,   41,   42,   43,   44,   45,  257,   47,   93,   58,
   59,   60,   61,   62,   58,   58,   41,   93,   58,   59,
   60,   61,   62,   44,   37,   58,  270,   93,   41,   42,
   43,   44,   45,   37,   47,   58,  269,   41,   42,   43,
   44,   45,  123,   47,   93,   58,   59,   60,   61,   62,
  123,   59,   40,   93,   58,   59,   60,   61,   62,   37,
  270,   37,  125,   59,   42,   43,   42,   45,   46,   47,
   46,   47,  125,  264,  265,  266,  267,   41,  269,   59,
   93,   59,   60,   61,   62,   37,  270,  123,   41,   93,
   42,   43,  263,   45,   46,   47,   44,   41,   41,   41,
   41,  125,   44,  125,   41,  125,   58,  125,   60,   61,
   62,   50,   33,   91,   36,   91,   58,   59,   53,   61,
  150,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  273,  274,  275,  276,   91,
  278,  279,   58,   -1,   60,   61,   62,   37,   -1,   -1,
   -1,   93,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   41,  278,  279,   44,   59,
   60,   61,   62,   66,   33,   91,   -1,   -1,   -1,   -1,
   33,   40,   58,   59,   -1,   61,   45,   40,  273,  274,
  275,  276,   45,  278,  279,   -1,   89,  273,  274,  275,
  276,   91,  278,  279,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   37,   -1,   -1,   45,   93,   42,   43,
  113,   45,   46,   47,  273,  274,  275,  276,   -1,  278,
  279,   -1,   91,  273,  274,  275,  276,   33,  278,  279,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
   -1,   -1,   -1,   -1,   -1,  148,  149,  150,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   91,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,  123,  273,  274,  275,  276,   33,
  278,  279,   60,   61,   62,   -1,   40,   37,   -1,   -1,
   -1,   45,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   33,  125,
   60,   61,   62,   91,   -1,   40,  278,  279,   -1,   -1,
   45,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   91,  278,  279,   60,   61,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   33,  278,  279,
   60,  125,   62,   -1,   40,   91,   -1,   93,  257,   45,
   -1,   -1,  278,  279,  257,  264,  265,  266,  267,   -1,
  269,  270,  271,  272,   -1,   33,   -1,  270,  271,  272,
  125,   91,   40,   -1,   -1,   -1,   -1,   45,  257,  258,
  259,   -1,  261,  262,   41,   -1,   43,   44,   45,  268,
   -1,  270,  271,  272,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,   -1,
   41,   -1,  268,   44,  270,  271,  272,   41,   -1,   43,
   44,   45,   -1,   -1,   -1,   -1,   93,   58,   59,   60,
   61,   62,   -1,   -1,   58,   59,   60,   61,   62,   31,
   -1,   -1,   -1,   -1,   -1,   -1,   38,   -1,   40,   -1,
   -1,   43,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   93,  257,  258,  259,   -1,  261,  262,   93,
   62,   -1,   -1,   -1,  268,   -1,  270,  271,  272,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,  257,  258,  259,   41,  261,  262,   44,   -1,
   -1,   -1,   -1,  268,   -1,  270,  271,  272,   41,   -1,
   -1,   44,   58,   59,   60,   61,   62,  273,  274,  275,
  276,   41,  278,  279,   44,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,  273,  274,  275,  276,   93,   -1,   -1,
   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,   -1,
   93,   -1,  268,   -1,  270,  271,  272,   -1,   41,   -1,
   -1,   44,   -1,   93,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,   -1,  261,  262,   58,   59,   60,   61,   62,
  268,   -1,  270,  271,  272,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
   41,  278,  279,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,  273,
  274,  275,  276,   -1,  278,  279,   52,   53,   54,   55,
   56,   -1,   93,   -1,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   81,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   91,   92,   93,   94,   95,
   96,   97,   98,   99,  100,  101,  102,  103,  104,   -1,
  106,   -1,   -1,   -1,  110,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  118,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,  145,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
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
"expresion : expresion '.' ID",
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
case 1:
//#line 62 "../../src/parser/parser.y"
{ ast = new Program(0,0,(List<Definition>) val_peek(9));}
break;
case 18:
//#line 102 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(),scanner.getColumn(), (String) val_peek(2), (Type) val_peek(0));}
break;
case 21:
//#line 109 "../../src/parser/parser.y"
{ yyval = IntType.getInstance();}
break;
case 22:
//#line 110 "../../src/parser/parser.y"
{ yyval = RealType.getInstance();}
break;
case 23:
//#line 111 "../../src/parser/parser.y"
{ yyval = CharType.getInstance();}
break;
case 24:
//#line 112 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(),scanner.getColumn(),(int) val_peek(2), (Type) val_peek(0));}
break;
case 25:
//#line 113 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(),scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 26:
//#line 114 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance();}
break;
case 34:
//#line 133 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(1));}
break;
//#line 755 "Parser.java"
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
