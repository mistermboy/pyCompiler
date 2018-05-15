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
import tipo.*;
import java.util.*;

//#line 28 "Parser.java"




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
public final static short INCREMENT=280;
public final static short CAST=281;
public final static short UNARIO=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    7,    7,    2,    2,
    2,    2,    6,    6,   11,   11,   12,    9,    9,    4,
   13,   13,    8,    8,    8,    8,    8,   14,   14,   15,
   10,   10,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   17,   17,   22,   23,   21,
   19,   20,   26,   25,   25,   24,   24,   27,   27,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    1,    1,
    2,    0,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    1,    1,    1,    2,    2,
    3,    1,    1,    1,    1,    3,    4,    3,    4,    2,
    2,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    4,    1,    3,    3,    4,    6,
    4,    5,    2,    1,    3,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,    8,    0,    7,    0,    0,
    0,   43,    0,    0,    0,    0,    0,    0,   45,   44,
    0,    0,    0,    0,    0,    0,    0,   31,    0,   36,
   37,   38,    0,    0,   30,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
   18,    0,    0,    0,   32,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   39,   40,    6,    0,   34,    0,   33,    0,    0,
   35,   41,    0,    0,    0,    0,   46,   19,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   48,    0,    0,    0,    0,    0,   74,    0,
    0,    0,    0,   47,   65,    0,    0,    0,   72,    0,
   70,   75,   73,
};
final static short yydgoto[] = {                          1,
    2,   64,    5,   65,    7,   24,   47,   21,   66,   67,
   25,   26,    8,   35,   36,   68,   78,   69,   70,   71,
   72,   73,   74,  124,  150,  159,  125,
};
final static short yysindex[] = {                         0,
    0, -180, -233,    0,    0,  -44,    0,  -31,    3,   24,
    0,  -20, -254, -230,   38,    0,    0,    0,  -82, -176,
    0,    0,   34,   66,   71,    0,   58, -153,   31,  -20,
   68, -230, -142,   -6, -114,    0,  -20,    0,  -36,    0,
    5,  -20,    0,    0,    0,    0,   13,    0,  510,   78,
  510,    0,  347,  347,  347,  347,  347,  -28,    0,    0,
  347,  347,  519,   26,   79,  510,  542,    0,  130,    0,
    0,    0,   93,   94,    0,   36,  122,  -42,  463,  -41,
  157,  164,  214,  104,  347,  -18,  -18,  133,  404,    0,
    0,  105,  542,  -28,    0,  347,  347,  347,  347,  347,
  347,  347,  347,  347,  347,  347,  347,  347,  347,  -99,
  347,    0,    0,    0,  347,    0,  347,    0,   55,  277,
    0,    0,  463,  138,  136,  347,    0,    0,  269,  269,
  269,  269,  470,  470,  463,  269,  269,  304,  304,  -18,
  -18,  -18,    0,  411,  146,  463,  542,  542,    0,  -74,
    0,  347,  -18,    0,    0,  153,  309,  277,    0,  463,
    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  154,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  155,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   72,    0,
   72,    0,    0,    0,    0,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,   80,   83,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,  -30,    0,
    0,    0,    0,    0,  171,  -11,   16,    0,    0,    0,
    0,    0,   91,  437,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  171,    0,    0,    0,    0,    0,
    0,    0,    1,    0,  172,    0,    0,    0,  601,  647,
  659,  767,  483,  716,  159,  775,  837,  579,  588,   25,
   52,   61,    0,    0,    0,  -24,    0,    0,    0,  344,
  123,    0,   88,    0,    0,    0,    0,    0,    0,   47,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  169,    0,   -1,    0,    0,    0,  245,    0,  -47,
    0,  191,   11,    0,  190,  187,  173,  851,    0,    0,
    0,    0,    0,  117,   76,    0,    0,
};
final static int YYTABLESIZE=1116;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         42,
    6,  117,  117,   42,   42,   42,   42,   42,   42,   42,
   43,   85,   13,   66,   11,   22,  116,  118,   93,   67,
   42,   42,   42,   42,   42,   50,   12,  110,   66,   50,
   50,   50,   50,   50,   67,   50,    9,   13,   34,   23,
   28,   78,   14,   10,   78,   34,   50,   50,   50,   50,
   50,   42,   51,   42,   20,   42,   51,   51,   51,   51,
   51,   52,   51,   15,   92,   52,   52,   52,   52,   52,
   20,   52,  111,   51,   51,   51,   51,   51,   27,    3,
   29,   50,   52,   52,   52,   52,   52,   79,   53,    4,
   79,   30,   53,   53,   53,   53,   53,   54,   53,  156,
  157,   54,   54,   54,   54,   54,   31,   54,   51,   53,
   53,   53,   53,   53,   32,   33,    4,   52,   54,   54,
   54,   54,   54,   37,   49,   39,   41,   49,   49,   49,
   49,   49,   49,   42,   49,   51,   75,   91,   42,   42,
   21,   42,   42,   42,   53,   49,   49,   49,   49,   49,
   90,  112,  113,   54,   21,    4,   42,   42,   42,   65,
  114,  115,  122,  128,   65,   65,  109,   65,   65,   65,
  143,  107,  106,  126,  105,  110,  108,  147,  151,  152,
   49,   69,   65,   65,   65,   62,  155,   42,  158,  103,
  102,  104,   63,  109,   13,   14,   12,   61,  107,  106,
  109,  105,  110,  108,    9,  107,  106,   10,  105,  110,
  108,   76,   77,   65,  119,   11,  103,   68,  104,   76,
  111,  120,   40,  103,   44,  104,   80,   16,   17,   18,
   19,  145,   46,  163,    0,   42,   42,   42,   42,    0,
   42,   42,    0,   16,   17,   18,   19,  111,    0,    0,
  109,   84,    0,   95,  111,  107,  106,    0,  105,  110,
  108,   50,   50,   50,   50,    0,   50,   50,    0,    0,
    0,    0,  121,  103,   38,  104,    0,  161,    0,   95,
    0,   45,    0,   48,    0,    0,   50,    0,   51,   51,
   51,   51,    0,   51,   51,    0,    0,   52,   52,   52,
   52,    0,   52,   52,  111,  109,  149,   88,    0,   62,
  107,  106,    0,  105,  110,  108,   63,    0,    0,    0,
    0,   61,    0,    0,   53,   53,   53,   53,    0,   53,
   53,    0,    0,   54,   54,   54,   54,    0,   54,   54,
  109,   62,   95,   95,  149,  107,    0,    0,   63,  110,
  108,    0,    0,   61,    0,    0,    0,    0,    0,  111,
   49,   49,   49,   49,    0,   49,   49,    0,    0,   42,
   42,   42,   42,    0,   42,   42,   71,    0,    0,   62,
    0,    0,    0,   71,    0,    0,   63,    0,   71,    0,
    0,   61,    0,    0,  111,   65,   65,   65,   65,  148,
   65,   65,   96,   97,   98,   99,    0,  100,  101,   52,
   53,   54,    0,   55,   56,    0,    0,    0,    0,    0,
   57,    0,   94,   59,   60,    0,    0,    0,    0,   96,
   97,   98,   99,  162,  100,  101,   96,   97,   98,   99,
  109,  100,  101,    0,  127,  107,  106,  109,  105,  110,
  108,    0,  107,  106,    0,  105,  110,  108,    0,    0,
    0,    0,    0,  103,    0,  104,    0,    0,   71,    0,
  103,    0,  104,   42,    0,    0,    0,    0,   42,   42,
    0,   42,   42,   42,    0,    0,   96,   97,   98,   99,
    0,  100,  101,    0,  111,    0,   42,   42,   42,  109,
    0,  111,    0,  154,  107,  106,  109,  105,  110,  108,
    0,  107,  106,    0,  105,  110,  108,    0,    0,    0,
    0,    0,  103,   64,  104,    0,   64,   42,    0,  103,
    0,  104,    0,   52,   53,   54,    0,   55,   56,    0,
   64,   64,   62,   64,   57,    0,   94,   59,   60,   63,
    0,   62,    0,  111,   61,    0,    0,    0,   63,    0,
  111,    0,    0,   61,    0,   52,   53,   54,    0,   55,
   56,    0,    0,    0,   62,   64,   57,    0,   94,   59,
   60,   63,    0,    0,    0,    0,   61,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   71,   71,   71,   52,   71,   71,    0,    0,    0,   20,
    0,   71,    0,   71,   71,   71,   77,   59,   60,   56,
    0,   56,   56,   56,    0,    0,    0,    0,   55,    0,
   55,   55,   55,    0,    0,    0,   56,   56,   56,   56,
   56,   58,    0,    0,   58,   55,   55,   55,   55,   55,
    0,    0,    0,    0,    0,    0,    0,    0,   58,   58,
   58,   58,   58,    0,    0,    0,    0,    0,    0,    0,
    0,   56,    0,    0,    0,    0,   96,   97,   98,   99,
   55,  100,  101,   96,   97,   98,   99,   60,  100,  101,
   60,    0,    0,   58,    0,    0,    0,    0,    0,   62,
    0,    0,   62,    0,   60,   60,   60,   60,   60,   42,
   42,   42,   42,    0,   42,   42,   62,   62,   62,   62,
   62,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   96,   97,   98,   99,   60,
  100,  101,   96,   97,   98,   99,    0,    0,    0,    0,
    0,   62,    0,    0,    0,    0,   63,    0,    0,   63,
   64,   64,    0,    0,    0,    0,   52,   53,   54,    0,
   55,   56,    0,   63,   63,   52,   63,   57,    0,   58,
   59,   60,   16,   17,   18,   19,    0,    0,   77,   59,
   60,    0,    0,    0,    0,    0,    0,    0,   52,   53,
   54,    0,   55,   56,    0,    0,    0,   61,   63,   57,
   61,   94,   59,   60,    0,   59,    0,    0,   59,    0,
    0,    0,    0,    0,   61,   61,   61,   61,   61,    0,
    0,    0,   59,   59,   59,   59,   59,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   56,   56,   56,   56,    0,   56,   56,    0,   61,
   55,   55,   55,   55,    0,   55,   55,   59,    0,    0,
    0,    0,    0,   58,   58,   58,   58,   57,   58,   58,
   57,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   57,   57,   57,   57,   57,    0,
    0,    0,    0,   79,   79,   81,   82,   83,    0,    0,
    0,   86,   87,   89,    0,    0,    0,    0,    0,   60,
   60,   60,   60,    0,   60,   60,    0,    0,    0,   57,
    0,   62,   62,   62,   62,  123,   62,   62,    0,    0,
    0,    0,    0,    0,    0,    0,  129,  130,  131,  132,
  133,  134,  135,  136,  137,  138,  139,  140,  141,  142,
    0,  144,    0,    0,    0,  123,    0,  146,    0,    0,
    0,    0,    0,    0,    0,    0,  153,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   63,   63,    0,    0,    0,    0,    0,
    0,    0,  160,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   61,
   61,   61,   61,    0,   61,   61,    0,   59,   59,   59,
   59,    0,   59,   59,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   57,   57,   57,    0,   57,   57,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
    2,   44,   44,   41,   42,   43,   44,   45,   46,   47,
  125,   40,   44,   44,   59,  270,   59,   59,   66,   44,
   58,   59,   60,   61,   62,   37,   58,   46,   59,   41,
   42,   43,   44,   45,   59,   47,  270,   44,   28,  270,
  123,   41,   40,  277,   44,   35,   58,   59,   60,   61,
   62,   58,   37,   91,   91,   93,   41,   42,   43,   44,
   45,   37,   47,   40,   66,   41,   42,   43,   44,   45,
   91,   47,   91,   58,   59,   60,   61,   62,   41,  260,
  257,   93,   58,   59,   60,   61,   62,   41,   37,  270,
   44,   58,   41,   42,   43,   44,   45,   37,   47,  147,
  148,   41,   42,   43,   44,   45,   41,   47,   93,   58,
   59,   60,   61,   62,   44,   58,  270,   93,   58,   59,
   60,   61,   62,   93,   37,   58,  269,  123,   41,   42,
   43,   44,   45,   37,   47,  123,   59,   59,   42,   43,
   44,   45,   46,   47,   93,   58,   59,   60,   61,   62,
  125,   59,   59,   93,   58,  270,   60,   61,   62,   37,
  125,   40,   59,   59,   42,   43,   37,   45,   46,   47,
  270,   42,   43,   41,   45,   46,   47,  123,   41,   44,
   93,   59,   60,   61,   62,   33,   41,   91,  263,   60,
   61,   62,   40,   37,   41,   41,  125,   45,   42,   43,
   37,   45,   46,   47,  125,   42,   43,  125,   45,   46,
   47,   41,   41,   91,   58,  125,   60,   59,   62,   51,
   91,   58,   32,   60,   35,   62,   54,  264,  265,  266,
  267,  115,  269,  158,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,  264,  265,  266,  267,   91,   -1,   -1,
   37,  280,   -1,   67,   91,   42,   43,   -1,   45,   46,
   47,  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,
   -1,   -1,   59,   60,   30,   62,   -1,  125,   -1,   93,
   -1,   37,   -1,   39,   -1,   -1,   42,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,  273,  274,  275,
  276,   -1,  278,  279,   91,   37,  120,   63,   -1,   33,
   42,   43,   -1,   45,   46,   47,   40,   -1,   -1,   -1,
   -1,   45,   -1,   -1,  273,  274,  275,  276,   -1,  278,
  279,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   37,   33,  156,  157,  158,   42,   -1,   -1,   40,   46,
   47,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,   91,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   33,   -1,   -1,   33,
   -1,   -1,   -1,   40,   -1,   -1,   40,   -1,   45,   -1,
   -1,   45,   -1,   -1,   91,  273,  274,  275,  276,  123,
  278,  279,  273,  274,  275,  276,   -1,  278,  279,  257,
  258,  259,   -1,  261,  262,   -1,   -1,   -1,   -1,   -1,
  268,   -1,  270,  271,  272,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,  125,  278,  279,  273,  274,  275,  276,
   37,  278,  279,   -1,   41,   42,   43,   37,   45,   46,
   47,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,   60,   -1,   62,   -1,   -1,  125,   -1,
   60,   -1,   62,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,  273,  274,  275,  276,
   -1,  278,  279,   -1,   91,   -1,   60,   61,   62,   37,
   -1,   91,   -1,   93,   42,   43,   37,   45,   46,   47,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   60,   41,   62,   -1,   44,   91,   -1,   60,
   -1,   62,   -1,  257,  258,  259,   -1,  261,  262,   -1,
   58,   59,   33,   61,  268,   -1,  270,  271,  272,   40,
   -1,   33,   -1,   91,   45,   -1,   -1,   -1,   40,   -1,
   91,   -1,   -1,   45,   -1,  257,  258,  259,   -1,  261,
  262,   -1,   -1,   -1,   33,   93,  268,   -1,  270,  271,
  272,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,  257,  261,  262,   -1,   -1,   -1,   91,
   -1,  268,   -1,  270,  271,  272,  270,  271,  272,   41,
   -1,   43,   44,   45,   -1,   -1,   -1,   -1,   41,   -1,
   43,   44,   45,   -1,   -1,   -1,   58,   59,   60,   61,
   62,   41,   -1,   -1,   44,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,  273,  274,  275,  276,
   93,  278,  279,  273,  274,  275,  276,   41,  278,  279,
   44,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   58,   59,   60,   61,   62,  273,
  274,  275,  276,   -1,  278,  279,   58,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   93,
  278,  279,  273,  274,  275,  276,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
  278,  279,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,
  261,  262,   -1,   58,   59,  257,   61,  268,   -1,  270,
  271,  272,  264,  265,  266,  267,   -1,   -1,  270,  271,
  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,
  259,   -1,  261,  262,   -1,   -1,   -1,   41,   93,  268,
   44,  270,  271,  272,   -1,   41,   -1,   -1,   44,   -1,
   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,   -1,
   -1,   -1,   58,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,   93,
  273,  274,  275,  276,   -1,  278,  279,   93,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   41,  278,  279,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   53,   54,   55,   56,   57,   -1,   -1,
   -1,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   93,
   -1,  273,  274,  275,  276,   85,  278,  279,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   96,   97,   98,   99,
  100,  101,  102,  103,  104,  105,  106,  107,  108,  109,
   -1,  111,   -1,   -1,   -1,  115,   -1,  117,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  126,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  152,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,  273,  274,  275,
  276,   -1,  278,  279,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,
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
"OR","AND","INCREMENT","CAST","UNARIO",
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
"sentencia : ID INCREMENT ';'",
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
//#line 598 "Parser.java"
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
//#line 64 "../../src/parser/parser.y"
{ ast = new Program(0,0,(List<Definition>) val_peek(9),(List<Statement>) val_peek(1));}
break;
case 2:
//#line 66 "../../src/parser/parser.y"
{List<Definition> defs = (List<Definition>)val_peek(1);List<Definition> def = (List<Definition>)val_peek(0);for(Definition d:def){defs.add(d);}yyval=defs;}
break;
case 3:
//#line 67 "../../src/parser/parser.y"
{yyval = new ArrayList<Definition>();}
break;
case 4:
//#line 71 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 5:
//#line 72 "../../src/parser/parser.y"
{List<FunDefinition> fd = new ArrayList<FunDefinition>(); fd.add((FunDefinition) val_peek(0));yyval=fd;}
break;
case 6:
//#line 77 "../../src/parser/parser.y"
{ FunctionType ft = new FunctionType(scanner.getLine(),scanner.getColumn(),(Type) val_peek(3),(List<VarDefinition>)val_peek(6));yyval = new FunDefinition(scanner.getLine(),scanner.getColumn(), (String) val_peek(8),ft,(List<Statement>) val_peek(1));}
break;
case 7:
//#line 79 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 8:
//#line 80 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance();}
break;
case 9:
//#line 85 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 10:
//#line 86 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 11:
//#line 87 "../../src/parser/parser.y"
{ List<Statement> st = (List<Statement>) val_peek(1);List<Statement> sent = (List<Statement>) val_peek(0);for(Statement s: sent){st.add(s);}yyval=st;}
break;
case 12:
//#line 88 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>();}
break;
case 13:
//#line 92 "../../src/parser/parser.y"
{ yyval = new ArrayList<VarDefinition>();}
break;
case 14:
//#line 93 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 15:
//#line 96 "../../src/parser/parser.y"
{ List<VarDefinition> par = new ArrayList<VarDefinition>();par.add((VarDefinition)val_peek(0));yyval=par;}
break;
case 16:
//#line 97 "../../src/parser/parser.y"
{ List<VarDefinition> pars = (List<VarDefinition>) val_peek(2);pars.add((VarDefinition)val_peek(0));yyval=pars;}
break;
case 17:
//#line 99 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(),scanner.getColumn(),(String) val_peek(2), (Type) val_peek(0));}
break;
case 18:
//#line 104 "../../src/parser/parser.y"
{ yyval = val_peek(1);}
break;
case 19:
//#line 105 "../../src/parser/parser.y"
{ List<Definition> defs = (List<Definition>)val_peek(2); List<VarDefinition> def = (List<VarDefinition>) val_peek(1); for(VarDefinition var:def){defs.add(var);}yyval=defs;}
break;
case 20:
//#line 109 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(2); List<VarDefinition> def = new ArrayList<VarDefinition>();for(String id:ids){def.add(new VarDefinition(scanner.getLine(),scanner.getColumn(),id, (Type) val_peek(0)));}yyval=def;}
break;
case 21:
//#line 112 "../../src/parser/parser.y"
{ List<String> ids = new ArrayList<String>(); ids.add((String) val_peek(0)); yyval=ids;}
break;
case 22:
//#line 113 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(2); if(!ids.contains((String) val_peek(0))){ids.add((String) val_peek(0));}else{new ErrorType(scanner.getLine(),scanner.getColumn(),"No puedes definir dos variables con el mismo nombre");}yyval=ids;}
break;
case 23:
//#line 116 "../../src/parser/parser.y"
{ yyval = IntType.getInstance();}
break;
case 24:
//#line 117 "../../src/parser/parser.y"
{ yyval = RealType.getInstance();}
break;
case 25:
//#line 118 "../../src/parser/parser.y"
{ yyval = CharType.getInstance();}
break;
case 26:
//#line 119 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(),scanner.getColumn(),(int) val_peek(2), (Type) val_peek(0));}
break;
case 27:
//#line 120 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(),scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 28:
//#line 124 "../../src/parser/parser.y"
{yyval=val_peek(0);}
break;
case 29:
//#line 125 "../../src/parser/parser.y"
{List<RecordField> camps = (List<RecordField>)val_peek(1); List<RecordField> def = (List<RecordField>) val_peek(0);for(RecordField r:def){if(camps.contains(r)){r.setType(new ErrorType(r,"No puedes definir dos variables con el mismo nombre dentro de un struct"));}else{camps.add(r);}}yyval=camps;}
break;
case 30:
//#line 128 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(3);List<RecordField> def = new ArrayList<RecordField>();for(String id:ids){def.add(new RecordField(scanner.getLine(),scanner.getColumn(),id,(Type) val_peek(1),0));}yyval=def;}
break;
case 31:
//#line 132 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 32:
//#line 133 "../../src/parser/parser.y"
{ List<Statement> states = (List<Statement>)val_peek(1);List<Statement> st = (List<Statement>)val_peek(0);for(Statement s:st){states.add(s);}yyval=states;}
break;
case 33:
//#line 137 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>();List<Expression> exps = (List<Expression>) val_peek(1); for(Expression e:exps){states.add(new Write(scanner.getLine(),scanner.getColumn(),e));}yyval=states;}
break;
case 34:
//#line 138 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>();List<Expression> exps = (List<Expression>) val_peek(1); for(Expression e:exps){states.add(new Read(scanner.getLine(),scanner.getColumn(),e));}yyval=states;}
break;
case 35:
//#line 139 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Expression e = (Expression) val_peek(1);states.add(new Return(scanner.getLine(),scanner.getColumn(),e));yyval=states;}
break;
case 36:
//#line 140 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); IfStatement ifs = (IfStatement) val_peek(0); states.add(ifs);yyval=states;}
break;
case 37:
//#line 141 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); IfStatement ifs = (IfStatement) val_peek(0); states.add(ifs);yyval=states;}
break;
case 38:
//#line 142 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); WhileStatement wS = (WhileStatement) val_peek(0); states.add(wS);yyval=states;}
break;
case 39:
//#line 143 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Assignment aS = (Assignment) val_peek(1); states.add(aS);yyval=states;}
break;
case 40:
//#line 144 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Invocation iS = (Invocation) val_peek(1); states.add(iS);yyval=states;}
break;
case 41:
//#line 145 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Increment iN = new Increment(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String) val_peek(2))) ; states.add(iN);yyval=states;}
break;
case 42:
//#line 149 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(),scanner.getColumn(),(String) val_peek(0));}
break;
case 43:
//#line 150 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(),scanner.getColumn(),(int) val_peek(0));}
break;
case 44:
//#line 151 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(),scanner.getColumn(),(char) val_peek(0));}
break;
case 45:
//#line 152 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(),scanner.getColumn(),(double) val_peek(0));}
break;
case 46:
//#line 153 "../../src/parser/parser.y"
{ yyval = val_peek(1);}
break;
case 47:
//#line 154 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(),scanner.getColumn(),(Expression)val_peek(3),(Expression)val_peek(1));}
break;
case 48:
//#line 155 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(0));}
break;
case 49:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(0),(Type) val_peek(2));}
break;
case 50:
//#line 157 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(0));}
break;
case 51:
//#line 158 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(0));}
break;
case 52:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 53:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 54:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 55:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 56:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 57:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 58:
//#line 165 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 59:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 60:
//#line 167 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 61:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 62:
//#line 169 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 63:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 64:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 65:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(3)),(List<Expression>) val_peek(1));}
break;
case 66:
//#line 176 "../../src/parser/parser.y"
{ List<Expression> exp = new ArrayList<Expression>();exp.add((Expression)val_peek(0));yyval=exp;}
break;
case 67:
//#line 177 "../../src/parser/parser.y"
{ List<Expression> exps = (List<Expression>) val_peek(2);exps.add((Expression)val_peek(0));yyval=exps;}
break;
case 68:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression)val_peek(2),(Expression)val_peek(0));}
break;
case 69:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(3)),(List<Expression>) val_peek(1));}
break;
case 70:
//#line 187 "../../src/parser/parser.y"
{ yyval = new WhileStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(1),(Expression) val_peek(4));}
break;
case 71:
//#line 192 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(0),null,(Expression) val_peek(2));}
break;
case 72:
//#line 193 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(1),(List<Statement>) val_peek(0),(Expression) val_peek(3));}
break;
case 73:
//#line 195 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 74:
//#line 197 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 75:
//#line 198 "../../src/parser/parser.y"
{ yyval=val_peek(1);}
break;
case 76:
//#line 204 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>();}
break;
case 77:
//#line 205 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 78:
//#line 208 "../../src/parser/parser.y"
{ List<Expression> exp = new ArrayList<Expression>();exp.add((Expression)val_peek(0));yyval=exp;}
break;
case 79:
//#line 209 "../../src/parser/parser.y"
{ List<Expression> exps = (List<Expression>) val_peek(2);exps.add((Expression)val_peek(0));yyval=exps;}
break;
//#line 1063 "Parser.java"
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
