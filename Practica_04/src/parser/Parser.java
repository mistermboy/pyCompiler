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
    0,    1,    1,    3,    3,    5,    7,    7,    2,    2,
    2,    2,    6,    6,   11,   11,   12,    9,    9,    4,
   13,   13,    8,    8,    8,    8,    8,   14,   14,   15,
   10,   10,   16,   16,   16,   16,   16,   16,   16,   16,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   17,   17,   22,   23,   21,
   19,   20,   26,   25,   25,   24,   24,   27,   27,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    1,    1,
    2,    0,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    1,    1,    1,    2,    2,
    1,    1,    1,    1,    3,    4,    3,    4,    2,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    1,    3,    3,    4,    6,
    4,    5,    2,    1,    3,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,    8,    0,    7,    0,    0,
    0,   42,    0,    0,    0,    0,    0,    0,   44,   43,
    0,    0,    0,    0,    0,    0,    0,   31,    0,   36,
   37,   38,    0,    0,   30,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,   18,
    0,    0,    0,   32,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   39,   40,    6,    0,   34,    0,    0,   33,    0,    0,
   35,    0,    0,    0,    0,   45,   19,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   47,    0,    0,    0,    0,    0,    0,   74,    0,
    0,    0,    0,   46,   65,    0,    0,    0,   72,    0,
   70,   75,   73,
};
final static short yydgoto[] = {                          1,
    2,   64,    5,   65,    7,   24,   47,   21,   66,   67,
   25,   26,    8,   35,   36,   68,   78,   69,   70,   71,
   72,   73,   74,  123,  150,  159,  124,
};
final static short yysindex[] = {                         0,
    0, -225, -257,    0,    0,  -44,    0,   -6,  -24,    1,
    0,  -20, -230, -206,   38,    0,    0,    0,  -43, -176,
    0,    0,   30,   49,   47,    0,   34, -163,   22,  -20,
   58, -206, -152,   80, -114,    0,  -20,    0,  -36,    0,
    3,  -20,    0,    0,    0,    0,    4,    0,  436,   69,
  436,    0,  312,  312,  312,  312,  312,   96,    0,    0,
  312,  312,  496,   12,   92,  436,  512,    0,  411,    0,
    0,    0,   93,  103,    0,   39,  123,  -42,  437,  -41,
  124,  131,  157,  312,  -18,  -18,  134,  164,    0,    0,
  113,  512,   96,    0,  312,  312,  312,  312,  312,  312,
  312,  312,  312,  312,  312,  312,  312,  312,  -91,  312,
    0,    0,    0,  312,    0,  312,  312,    0,   64,  120,
    0,  437,  149,  151,  312,    0,    0,  277,  277,  277,
  277,  470,  470,  437,  277,  277,  304,  304,  -18,  -18,
  -18,    0,  214,  167,  437,  437,  512,  512,    0,  -51,
    0,  312,  -18,    0,    0,  276,  309,  120,    0,  437,
    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  172,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  173,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
   95,    0,    0,    0,    0,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,   98,  102,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,  -32,    0,
    0,    0,    0,  193,  -11,   16,    0,    0,    0,    0,
    0,  110,  463,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  193,    0,    0,    0,    0,    0,    0,
    0,   -2,    0,  199,    0,    0,    0,  561,  612,  659,
  731,  758,  806,  191,  753,  800,  531,  554,   25,   52,
   61,    0,    0,    0,  -30,  139,    0,    0,    0,  378,
  404,    0,   88,    0,    0,    0,    0,    0,    0,    2,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  201,    0,   -1,    0,    0,    0,  382,    0,  -47,
    0,  221,    9,    0,  219,  129,  204,  847,    0,    0,
    0,    0,    0,  152,  111,    0,    0,
};
final static int YYTABLESIZE=1085;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         41,
    6,  116,  116,   41,   41,   41,   41,   41,   41,   41,
   43,   66,    9,   67,   11,   14,  115,  118,   92,   10,
   41,   41,   41,   41,   41,   49,   66,  109,   67,   49,
   49,   49,   49,   49,    3,   49,   34,   13,   78,   22,
   15,   78,   79,   34,    4,   79,   49,   49,   49,   49,
   49,   12,   50,   41,   20,   41,   50,   50,   50,   50,
   50,   51,   50,   23,   91,   51,   51,   51,   51,   51,
   20,   51,  110,   50,   50,   50,   50,   50,   27,   28,
   29,   49,   51,   51,   51,   51,   51,   30,   52,   31,
   32,   33,   52,   52,   52,   52,   52,   53,   52,  156,
  157,   53,   53,   53,   53,   53,    4,   53,   50,   52,
   52,   52,   52,   52,   37,   39,   41,   51,   53,   53,
   53,   53,   53,   13,   48,   49,   51,   75,   48,   48,
   48,   48,   48,   41,   48,   84,   89,   42,   41,   41,
   21,   41,   41,   41,   52,   48,   48,   48,   48,   48,
   90,  111,   62,   53,   21,    4,   41,   41,   41,   63,
  108,  112,  114,  113,   61,  106,  105,  108,  104,  109,
  107,  127,  106,  105,  125,  104,  109,  107,  142,   64,
   48,  119,   64,  102,  117,  103,  147,   41,  120,  151,
  102,  117,  103,  108,  152,   94,   64,   64,  106,  105,
  108,  104,  109,  107,  126,  106,  105,  155,  104,  109,
  107,  158,   13,   14,  110,  121,  102,  117,  103,   12,
   94,  110,    9,  102,  117,  103,   10,   16,   17,   18,
   19,   64,   46,   76,   11,   41,   41,   41,   41,   77,
   41,   41,  148,   16,   17,   18,   19,  110,  149,   68,
  108,   76,   40,   44,  110,  106,  105,   80,  104,  109,
  107,   49,   49,   49,   49,  144,   49,   49,  163,    0,
    0,    0,    0,  102,  117,  103,    0,    0,    0,    0,
    0,    0,    0,    0,   94,   94,  149,    0,   50,   50,
   50,   50,    0,   50,   50,    0,    0,   51,   51,   51,
   51,    0,   51,   51,  110,    0,  154,    0,   62,    0,
    0,    0,    0,  108,    0,   63,    0,    0,  106,  105,
   61,  104,  109,  107,   52,   52,   52,   52,    0,   52,
   52,    0,    0,   53,   53,   53,   53,    0,   53,   53,
  108,   62,    0,    0,   62,  106,    0,    0,   63,  109,
  107,   63,    0,   61,    0,    0,   61,    0,    0,    0,
   48,   48,   48,   48,    0,   48,   48,  110,    0,   41,
   41,   41,   41,    0,   41,   41,   52,   53,   54,    0,
   55,   56,    0,    0,    0,    0,    0,   57,    0,   93,
   59,   60,    0,    0,  110,    0,   95,   96,   97,   98,
  161,   99,  100,   95,   96,   97,   98,    0,   99,  100,
   71,   38,    0,    0,    0,    0,    0,   71,   45,    0,
   48,    0,   71,   50,    0,    0,    0,    0,    0,   95,
   96,   97,   98,  162,   99,  100,   95,   96,   97,   98,
   65,   99,  100,    0,   87,   65,   65,  108,   65,   65,
   65,    0,  106,  105,    0,  104,  109,  107,    0,    0,
    0,    0,   69,   65,   65,   65,    0,    0,   62,    0,
  102,  101,  103,  108,    0,   63,    0,    0,  106,  105,
   61,  104,  109,  107,    0,    0,   95,   96,   97,   98,
    0,   99,  100,    0,   65,    0,  102,  117,  103,   41,
    0,  110,   71,    0,   41,   41,  108,   41,   41,   41,
    0,  106,  105,    0,  104,  109,  107,    0,    0,    0,
    0,    0,   41,   41,   41,    0,    0,  110,   62,  102,
    0,  103,   52,   53,   54,   63,   55,   56,    0,    0,
   61,    0,    0,   57,   62,   93,   59,   60,    0,    0,
    0,   63,    0,   41,    0,    0,   61,    0,    0,    0,
  110,    0,    0,    0,    0,   52,   53,   54,   52,   55,
   56,   55,    0,   55,   55,   55,   57,    0,   93,   59,
   60,   77,   59,   60,    0,    0,   20,    0,   55,   55,
   55,   55,   55,    0,   54,    0,   54,   54,   54,    0,
    0,   57,    0,    0,   57,    0,    0,    0,    0,    0,
    0,   54,   54,   54,   54,   54,    0,    0,   57,   57,
   57,   57,   57,   55,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   71,   71,   71,    0,   71,   71,
    0,    0,    0,    0,    0,   71,   54,   71,   71,   71,
    0,    0,   59,   57,    0,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   59,
   59,   59,   59,   59,    0,    0,   65,   65,   65,   65,
    0,   65,   65,   95,   96,   97,   98,    0,   99,  100,
    0,    0,   52,   53,   54,    0,   55,   56,    0,   61,
    0,    0,   61,   57,   59,   58,   59,   60,    0,   95,
   96,   97,   98,    0,   99,  100,   61,   61,   61,   61,
   61,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   41,   41,   41,   41,    0,
   41,   41,   95,   96,   97,   98,    0,    0,    0,    0,
    0,   61,   52,    0,    0,    0,    0,    0,    0,   16,
   17,   18,   19,    0,    0,   77,   59,   60,   52,   53,
   54,   60,   55,   56,   60,    0,    0,    0,    0,   57,
    0,   93,   59,   60,    0,    0,    0,    0,   60,   60,
   60,   60,   60,   58,    0,    0,   58,    0,   63,    0,
    0,   63,    0,   55,   55,   55,   55,    0,   55,   55,
   58,   58,   58,   58,   58,   63,   63,    0,   63,    0,
    0,    0,    0,   60,    0,    0,   54,   54,   54,   54,
    0,   54,   54,   57,   57,   57,   57,    0,   57,   57,
   56,    0,    0,   56,    0,   58,   62,    0,    0,   62,
   63,    0,    0,    0,    0,    0,    0,   56,   56,   56,
   56,   56,    0,   62,   62,    0,   62,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   59,   59,   59,   59,    0,   59,
   59,    0,   56,    0,    0,    0,    0,    0,   62,   79,
   79,   81,   82,   83,    0,    0,    0,   85,   86,   88,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  122,   61,   61,   61,   61,    0,   61,   61,    0,    0,
    0,  128,  129,  130,  131,  132,  133,  134,  135,  136,
  137,  138,  139,  140,  141,    0,  143,    0,    0,    0,
  122,    0,  145,  146,    0,    0,    0,    0,    0,    0,
    0,  153,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  160,    0,
    0,    0,    0,   60,   60,   60,   60,    0,   60,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   58,   58,   58,   58,    0,
   58,   58,    0,    0,    0,   63,   63,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   56,   56,   56,   56,    0,   56,   56,    0,
    0,    0,    0,   62,   62,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    2,   44,   44,   41,   42,   43,   44,   45,   46,   47,
  125,   44,  270,   44,   59,   40,   59,   59,   66,  277,
   58,   59,   60,   61,   62,   37,   59,   46,   59,   41,
   42,   43,   44,   45,  260,   47,   28,   44,   41,  270,
   40,   44,   41,   35,  270,   44,   58,   59,   60,   61,
   62,   58,   37,   91,   91,   93,   41,   42,   43,   44,
   45,   37,   47,  270,   66,   41,   42,   43,   44,   45,
   91,   47,   91,   58,   59,   60,   61,   62,   41,  123,
  257,   93,   58,   59,   60,   61,   62,   58,   37,   41,
   44,   58,   41,   42,   43,   44,   45,   37,   47,  147,
  148,   41,   42,   43,   44,   45,  270,   47,   93,   58,
   59,   60,   61,   62,   93,   58,  269,   93,   58,   59,
   60,   61,   62,   44,   37,  123,  123,   59,   41,   42,
   43,   44,   45,   37,   47,   40,  125,   58,   42,   43,
   44,   45,   46,   47,   93,   58,   59,   60,   61,   62,
   59,   59,   33,   93,   58,  270,   60,   61,   62,   40,
   37,   59,   40,  125,   45,   42,   43,   37,   45,   46,
   47,   59,   42,   43,   41,   45,   46,   47,  270,   41,
   93,   58,   44,   60,   61,   62,  123,   91,   58,   41,
   60,   61,   62,   37,   44,   67,   58,   59,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   41,   45,   46,
   47,  263,   41,   41,   91,   59,   60,   61,   62,  125,
   92,   91,  125,   60,   61,   62,  125,  264,  265,  266,
  267,   93,  269,   41,  125,  273,  274,  275,  276,   41,
  278,  279,  123,  264,  265,  266,  267,   91,  120,   59,
   37,   51,   32,   35,   91,   42,   43,   54,   45,   46,
   47,  273,  274,  275,  276,  114,  278,  279,  158,   -1,
   -1,   -1,   -1,   60,   61,   62,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  156,  157,  158,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,  273,  274,  275,
  276,   -1,  278,  279,   91,   -1,   93,   -1,   33,   -1,
   -1,   -1,   -1,   37,   -1,   40,   -1,   -1,   42,   43,
   45,   45,   46,   47,  273,  274,  275,  276,   -1,  278,
  279,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   37,   33,   -1,   -1,   33,   42,   -1,   -1,   40,   46,
   47,   40,   -1,   45,   -1,   -1,   45,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,   91,   -1,  273,
  274,  275,  276,   -1,  278,  279,  257,  258,  259,   -1,
  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,  270,
  271,  272,   -1,   -1,   91,   -1,  273,  274,  275,  276,
  125,  278,  279,  273,  274,  275,  276,   -1,  278,  279,
   33,   30,   -1,   -1,   -1,   -1,   -1,   40,   37,   -1,
   39,   -1,   45,   42,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  125,  278,  279,  273,  274,  275,  276,
   37,  278,  279,   -1,   63,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   -1,   -1,   33,   -1,
   60,   61,   62,   37,   -1,   40,   -1,   -1,   42,   43,
   45,   45,   46,   47,   -1,   -1,  273,  274,  275,  276,
   -1,  278,  279,   -1,   91,   -1,   60,   61,   62,   37,
   -1,   91,  125,   -1,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   60,   61,   62,   -1,   -1,   91,   33,   60,
   -1,   62,  257,  258,  259,   40,  261,  262,   -1,   -1,
   45,   -1,   -1,  268,   33,  270,  271,  272,   -1,   -1,
   -1,   40,   -1,   91,   -1,   -1,   45,   -1,   -1,   -1,
   91,   -1,   -1,   -1,   -1,  257,  258,  259,  257,  261,
  262,   41,   -1,   43,   44,   45,  268,   -1,  270,  271,
  272,  270,  271,  272,   -1,   -1,   91,   -1,   58,   59,
   60,   61,   62,   -1,   41,   -1,   43,   44,   45,   -1,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   60,   61,   62,   -1,   -1,   58,   59,
   60,   61,   62,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,   -1,  261,  262,
   -1,   -1,   -1,   -1,   -1,  268,   93,  270,  271,  272,
   -1,   -1,   41,   93,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   60,   61,   62,   -1,   -1,  273,  274,  275,  276,
   -1,  278,  279,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,  257,  258,  259,   -1,  261,  262,   -1,   41,
   -1,   -1,   44,  268,   93,  270,  271,  272,   -1,  273,
  274,  275,  276,   -1,  278,  279,   58,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,  273,  274,  275,  276,   -1,   -1,   -1,   -1,
   -1,   93,  257,   -1,   -1,   -1,   -1,   -1,   -1,  264,
  265,  266,  267,   -1,   -1,  270,  271,  272,  257,  258,
  259,   41,  261,  262,   44,   -1,   -1,   -1,   -1,  268,
   -1,  270,  271,  272,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   41,   -1,   -1,   44,   -1,   41,   -1,
   -1,   44,   -1,  273,  274,  275,  276,   -1,  278,  279,
   58,   59,   60,   61,   62,   58,   59,   -1,   61,   -1,
   -1,   -1,   -1,   93,   -1,   -1,  273,  274,  275,  276,
   -1,  278,  279,  273,  274,  275,  276,   -1,  278,  279,
   41,   -1,   -1,   44,   -1,   93,   41,   -1,   -1,   44,
   93,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   61,   62,   -1,   58,   59,   -1,   61,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,
  279,   -1,   93,   -1,   -1,   -1,   -1,   -1,   93,   53,
   54,   55,   56,   57,   -1,   -1,   -1,   61,   62,   63,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   84,  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,
   -1,   95,   96,   97,   98,   99,  100,  101,  102,  103,
  104,  105,  106,  107,  108,   -1,  110,   -1,   -1,   -1,
  114,   -1,  116,  117,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  152,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   -1,   -1,  278,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,
   -1,   -1,   -1,  278,  279,
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
"retorno : tipo",
"retorno : VOID",
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
"sentencia : asignacion ';'",
"sentencia : invocacion ';'",
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
"asignacion : expresion '=' expresion",
"invocacion : ID '(' args ')'",
"while : WHILE expresion ':' '{' sentencias '}'",
"condicionalSimple : IF expresion ':' cuerpo",
"condicionalComplejo : IF expresion ':' cuerpo else",
"else : ELSE cuerpo",
"cuerpo : sentencia",
"cuerpo : '{' sentencias '}'",
"args :",
"args : arg",
"arg : expresion",
"arg : arg ',' expresion",
};

//#line 210 "../../src/parser/parser.y"
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
//#line 591 "Parser.java"
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
case 8:
//#line 77 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance();}
break;
case 13:
//#line 88 "../../src/parser/parser.y"
{yyval = val_peek(-1);}
break;
case 14:
//#line 89 "../../src/parser/parser.y"
{yyval = val_peek(0);}
break;
case 15:
//#line 92 "../../src/parser/parser.y"
{ List<VarDefinition> par = new ArrayList<VarDefinition>();par.add((VarDefinition)val_peek(0));yyval=par;}
break;
case 16:
//#line 93 "../../src/parser/parser.y"
{ List<VarDefinition> pars = (List<VarDefinition>) val_peek(2);pars.add((VarDefinition)val_peek(0));yyval=pars;}
break;
case 17:
//#line 95 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(),scanner.getColumn(),(String) val_peek(2), (Type) val_peek(0));}
break;
case 18:
//#line 100 "../../src/parser/parser.y"
{ yyval = val_peek(1);}
break;
case 19:
//#line 101 "../../src/parser/parser.y"
{ List<Definition> defs = (List<Definition>)val_peek(2); List<VarDefinition> def = (List<VarDefinition>) val_peek(1); for(VarDefinition var:def){defs.add(var);}yyval=defs;}
break;
case 20:
//#line 105 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(2); List<VarDefinition> def = new ArrayList<VarDefinition>();for(String id:ids){def.add(new VarDefinition(scanner.getLine(),scanner.getColumn(),id, (Type) val_peek(0)));}yyval=def;}
break;
case 21:
//#line 108 "../../src/parser/parser.y"
{ List<String> ids = new ArrayList<String>(); ids.add((String) val_peek(0)); yyval=ids;}
break;
case 22:
//#line 109 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(2); ids.add((String) val_peek(0)); yyval=ids;}
break;
case 23:
//#line 112 "../../src/parser/parser.y"
{ yyval = IntType.getInstance();}
break;
case 24:
//#line 113 "../../src/parser/parser.y"
{ yyval = RealType.getInstance();}
break;
case 25:
//#line 114 "../../src/parser/parser.y"
{ yyval = CharType.getInstance();}
break;
case 26:
//#line 115 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(),scanner.getColumn(),(int) val_peek(2), (Type) val_peek(0));}
break;
case 27:
//#line 116 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(),scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 35:
//#line 135 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(1));}
break;
case 51:
//#line 154 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 52:
//#line 155 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 53:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 54:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 55:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 56:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 57:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 58:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 59:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 60:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 61:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 62:
//#line 165 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 63:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Arithmetic) val_peek(2),(String)val_peek(1),(Arithmetic)val_peek(0));}
break;
case 71:
//#line 188 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(0),null,(Expression) val_peek(2));}
break;
case 72:
//#line 189 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(1),(List<Statement>) val_peek(0),(Expression) val_peek(3));}
break;
case 73:
//#line 191 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 74:
//#line 193 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 75:
//#line 194 "../../src/parser/parser.y"
{ yyval=val_peek(1);}
break;
//#line 884 "Parser.java"
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
