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
public final static short DECREMENT=281;
public final static short CAST=282;
public final static short UNARIO=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    5,    7,    7,    2,    2,
    2,    2,    6,    6,   11,   11,   12,    9,    9,    4,
   13,   13,    8,    8,    8,    8,    8,   14,   14,   15,
   10,   10,   16,   16,   16,   16,   16,   16,   16,   16,
   16,   16,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   17,   17,   22,   23,
   21,   19,   20,   26,   25,   25,   24,   24,   27,   27,
};
final static short yylen[] = {                            2,
   10,    2,    0,    2,    1,   10,    1,    1,    1,    1,
    2,    0,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    1,    1,    1,    2,    2,
    3,    3,    1,    1,    1,    1,    3,    4,    3,    4,
    2,    2,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    4,    1,    3,    3,    4,
    6,    4,    5,    2,    1,    3,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,    8,    0,    7,    0,    0,
    0,   44,    0,    0,    0,    0,    0,    0,   46,   45,
    0,    0,    0,    0,    0,    0,    0,   31,    0,   36,
   37,   38,    0,    0,   30,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,   18,
    0,    0,    0,   32,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   39,   40,    6,    0,   34,    0,   33,    0,
    0,   35,    0,    0,    0,    0,   47,   19,    0,    0,
    0,    0,    0,    0,   41,   42,    0,    0,    0,    0,
    0,    0,    0,    0,   49,    0,    0,    0,    0,    0,
   75,    0,    0,    0,    0,   48,   66,    0,    0,    0,
   73,    0,   71,   76,   74,
};
final static short yydgoto[] = {                          1,
    2,   64,    5,   65,    7,   24,   47,   21,   66,   67,
   25,   26,    8,   35,   36,   68,   78,   69,   70,   71,
   72,   73,   74,  124,  152,  161,  125,
};
final static short yysindex[] = {                         0,
    0, -231, -233,    0,    0,  -45,    0,   -6,  -24,  -20,
    0,  -49, -235, -230,    4,    0,    0,    0,  -77, -192,
    0,    0,   23,   30,   46,    0,   34, -170,    8,  -49,
   49, -230, -154,   -3, -114,    0,  -49,    0,  -90,    0,
   -7,  -49,    0,    0,    0,    0,    1,    0,  776,   58,
  776,    0,  646,  646,  646,  646,  646,   96,    0,    0,
  646,  646,  795,    3,   78,  776,  811,    0,  149,    0,
    0,    0,   79,   92,    0,   27,  113,  -42,  459,  -41,
  187,  409,  346,  646,  -27,  -27,  121,  211,    0,    0,
  104,  811,   96,    0,  646,  646,  646,  646,  646,  646,
  108,  112,  646,  646,  646,  646,  646,  646,  646,  646,
  -98,  646,    0,    0,    0,  646,    0,  646,    0,   50,
  636,    0,  459,  137,  136,  646,    0,    0,  238,  238,
  238,  238,  653,  653,    0,    0,  459,  238,  238,  309,
  309,  -27,  -27,  -27,    0,  431,  146,  459,  811,  811,
    0,  -74,    0,  646,  -27,    0,    0,  678,  716,  636,
    0,  459,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  152,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  157,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   65,    0,
   65,    0,    0,    0,    0,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,   74,   75,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,  -32,    0,
    0,    0,    0,  163,  -11,   16,    0,    0,    0,    0,
    0,   76,  160,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  163,    0,    0,    0,    0,
    0,    0,   47,    0,  167,    0,    0,    0,  503,  515,
  539,  551,  474,  609,    0,    0,  153,  587,  596,  467,
  493,   25,   52,   61,    0,    0,    0,  -31,    0,    0,
    0,  759,  123,    0,   88,    0,    0,    0,    0,    0,
    0,  120,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  162,    0,   13,    0,    0,    0,  282,    0,  -23,
    0,  191,   45,    0,  184,  158,  172, 1031,    0,    0,
    0,    0,    0,  111,   68,    0,    0,
};
final static int YYTABLESIZE=1185;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   20,  118,  118,   43,   43,   43,   43,   43,   43,   43,
   43,   67,   68,   11,    6,   14,  117,  119,  111,   15,
   43,   43,   43,   43,   43,   51,   67,   68,    3,   51,
   51,   51,   51,   51,   22,   51,    9,   13,    4,   23,
   13,   20,   92,   10,   27,   28,   51,   51,   51,   51,
   51,   12,   52,   43,   42,   43,   52,   52,   52,   52,
   52,   53,   52,  112,   29,   53,   53,   53,   53,   53,
   31,   53,   34,   52,   52,   52,   52,   52,   91,   34,
   30,   51,   53,   53,   53,   53,   53,   79,   54,   32,
   79,   33,   54,   54,   54,   54,   54,   55,   54,    4,
   37,   55,   55,   55,   55,   55,   39,   55,   52,   54,
   54,   54,   54,   54,   41,   49,   75,   53,   55,   55,
   55,   55,   55,   51,   50,  158,  159,   89,   50,   50,
   50,   50,   50,   43,   50,   84,   90,  113,   43,   43,
   21,   43,   43,   43,   54,   50,   50,   50,   50,   50,
  114,  115,  116,   55,   21,    4,   43,   43,   43,   66,
   80,  126,  128,   80,   66,   66,  135,   66,   66,   66,
  136,  145,  149,   16,   17,   18,   19,  153,   46,  154,
   50,   70,   66,   66,   66,  110,  157,   43,  160,   12,
  108,  107,   13,  106,  111,  109,   43,   14,    9,   10,
   11,   43,   43,   77,   43,   43,   43,   78,  104,  103,
  105,   69,   76,   66,   16,   17,   18,   19,   44,   43,
   43,   43,   40,  110,   94,   80,  147,  165,  108,  107,
    0,  106,  111,  109,    0,   43,   43,   43,   43,  112,
   43,   43,   43,   43,  120,    0,  104,  110,  105,   94,
   43,  127,  108,  107,    0,  106,  111,  109,    0,    0,
    0,   51,   51,   51,   51,    0,   51,   51,   51,   51,
  104,    0,  105,    0,  110,    0,    0,  112,  151,  108,
  107,    0,  106,  111,  109,    0,    0,    0,   52,   52,
   52,   52,    0,   52,   52,   52,   52,   53,   53,   53,
   53,  112,   53,   53,   53,   53,    0,    0,    0,    0,
    0,   38,    0,    0,    0,   94,   94,  151,   45,    0,
   48,    0,    0,   50,   54,   54,   54,   54,  112,   54,
   54,   54,   54,   55,   55,   55,   55,    0,   55,   55,
   55,   55,    0,    0,   87,  110,    0,    0,    0,    0,
  108,    0,    0,    0,  111,  109,    0,    0,    0,    0,
   50,   50,   50,   50,    0,   50,   50,   50,   50,   43,
   43,   43,   43,    0,   43,   43,   43,   43,    0,    0,
    0,    0,  110,    0,    0,    0,    0,  108,  107,    0,
  106,  111,  109,    0,    0,   66,   66,   66,   66,  112,
   66,   66,   66,   66,  122,  104,    0,  105,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   95,   96,   97,   98,    0,   99,  100,  101,  102,
    0,    0,   43,   43,   43,   43,  112,   43,   43,   43,
   43,    0,    0,    0,    0,  110,    0,    0,    0,    0,
  108,  107,    0,  106,  111,  109,    0,    0,    0,   95,
   96,   97,   98,    0,   99,  100,  121,  110,  104,    0,
  105,    0,  108,  107,    0,  106,  111,  109,    0,    0,
    0,    0,    0,   95,   96,   97,   98,    0,   99,  100,
  104,    0,  105,    0,    0,  110,    0,    0,    0,  112,
  108,  107,    0,  106,  111,  109,    0,   57,    0,   57,
   57,   57,    0,    0,   65,    0,    0,   65,  104,    0,
  105,  112,    0,  156,   57,   57,   57,   57,   57,    0,
    0,   65,   65,   56,   65,   56,   56,   56,    0,    0,
    0,    0,    0,   59,    0,    0,   59,    0,    0,  112,
   56,   56,   56,   56,   56,   61,    0,    0,   61,   57,
   59,   59,   59,   59,   59,    0,   65,    0,    0,    0,
    0,    0,   61,   61,   61,   61,   61,    0,    0,   63,
    0,    0,   63,    0,    0,   56,    0,    0,    0,    0,
    0,   62,    0,    0,   62,   59,   63,   63,   63,   63,
   63,    0,    0,    0,    0,    0,    0,   61,   62,   62,
   62,   62,   62,    0,    0,    0,    0,    0,   95,   96,
   97,   98,    0,   99,  100,    0,    0,   60,    0,    0,
   60,   63,    0,    0,    0,    0,   58,    0,    0,   58,
    0,    0,    0,   62,   60,   60,   60,   60,   60,   64,
    0,    0,   64,   58,   58,   58,   58,   58,    0,    0,
    0,    0,    0,    0,    0,    0,   64,   64,   62,   64,
    0,    0,    0,    0,    0,   63,    0,    0,   62,   60,
   61,   95,   96,   97,   98,   63,   99,  100,   58,  110,
   61,    0,    0,    0,  108,  107,    0,  106,  111,  109,
    0,   64,    0,   95,   96,   97,   98,    0,   99,  100,
   62,    0,  104,    0,  105,    0,    0,   63,    0,    0,
    0,    0,   61,    0,    0,    0,    0,    0,    0,    0,
    0,   95,   96,   97,   98,    0,   99,  100,    0,   57,
   57,   57,   57,  112,   57,   57,   57,   57,   62,    0,
    0,   65,   65,   65,   65,   63,    0,    0,  150,    0,
   61,    0,    0,    0,    0,   56,   56,   56,   56,    0,
   56,   56,   56,   56,    0,   59,   59,   59,   59,    0,
   59,   59,   59,   59,    0,    0,    0,   61,   61,   61,
   61,   72,   61,   61,   61,   61,    0,    0,   72,    0,
    0,    0,  163,   72,    0,    0,    0,    0,   62,    0,
    0,   63,   63,   63,   63,   63,   63,   63,   63,   63,
   61,    0,    0,   62,   62,   62,   62,   62,   62,   62,
   62,   62,    0,    0,   63,    0,    0,    0,    0,   61,
  164,    0,    0,   62,    0,    0,    0,    0,    0,    0,
   63,    0,    0,    0,    0,   61,    0,    0,    0,   60,
   60,   60,   60,    0,   60,   60,   60,   60,   58,   58,
   58,   58,    0,   58,   58,   58,   58,    0,    0,    0,
    0,    0,    0,   72,    0,   20,   64,   64,   64,   64,
    0,    0,   52,   53,   54,    0,   55,   56,    0,    0,
    0,    0,   52,   57,    0,   93,   59,   60,    0,    0,
    0,    0,    0,    0,    0,   77,   59,   60,    0,    0,
    0,    0,    0,    0,    0,   95,   96,   97,   98,    0,
    0,    0,    0,    0,   52,   53,   54,    0,   55,   56,
    0,    0,    0,    0,    0,   57,    0,   93,   59,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,   53,   54,    0,   55,   56,    0,    0,
    0,    0,    0,   57,    0,   93,   59,   60,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   72,   72,   72,    0,   72,
   72,    0,    0,    0,    0,    0,   72,    0,   72,   72,
   72,    0,   52,   53,   54,    0,   55,   56,    0,    0,
    0,    0,    0,   57,    0,   58,   59,   60,    0,    0,
    0,   52,    0,    0,    0,    0,    0,    0,   16,   17,
   18,   19,    0,    0,   77,   59,   60,   52,   53,   54,
    0,   55,   56,    0,    0,    0,    0,    0,   57,    0,
   93,   59,   60,   79,   79,   81,   82,   83,    0,    0,
    0,   85,   86,   88,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  123,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  129,  130,  131,  132,  133,
  134,    0,    0,  137,  138,  139,  140,  141,  142,  143,
  144,    0,  146,    0,    0,    0,  123,    0,  148,    0,
    0,    0,    0,    0,    0,    0,  155,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  162,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   44,   44,   41,   42,   43,   44,   45,   46,   47,
  125,   44,   44,   59,    2,   40,   59,   59,   46,   40,
   58,   59,   60,   61,   62,   37,   59,   59,  260,   41,
   42,   43,   44,   45,  270,   47,  270,   44,  270,  270,
   44,   91,   66,  277,   41,  123,   58,   59,   60,   61,
   62,   58,   37,   91,   58,   93,   41,   42,   43,   44,
   45,   37,   47,   91,  257,   41,   42,   43,   44,   45,
   41,   47,   28,   58,   59,   60,   61,   62,   66,   35,
   58,   93,   58,   59,   60,   61,   62,   41,   37,   44,
   44,   58,   41,   42,   43,   44,   45,   37,   47,  270,
   93,   41,   42,   43,   44,   45,   58,   47,   93,   58,
   59,   60,   61,   62,  269,  123,   59,   93,   58,   59,
   60,   61,   62,  123,   37,  149,  150,  125,   41,   42,
   43,   44,   45,   37,   47,   40,   59,   59,   42,   43,
   44,   45,   46,   47,   93,   58,   59,   60,   61,   62,
   59,  125,   40,   93,   58,  270,   60,   61,   62,   37,
   41,   41,   59,   44,   42,   43,   59,   45,   46,   47,
   59,  270,  123,  264,  265,  266,  267,   41,  269,   44,
   93,   59,   60,   61,   62,   37,   41,   91,  263,  125,
   42,   43,   41,   45,   46,   47,   37,   41,  125,  125,
  125,   42,   43,   41,   45,   46,   47,   41,   60,   61,
   62,   59,   51,   91,  264,  265,  266,  267,   35,   60,
   61,   62,   32,   37,   67,   54,  116,  160,   42,   43,
   -1,   45,   46,   47,   -1,  273,  274,  275,  276,   91,
  278,  279,  280,  281,   58,   -1,   60,   37,   62,   92,
   91,   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,  280,  281,
   60,   -1,   62,   -1,   37,   -1,   -1,   91,  121,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,  280,  281,  273,  274,  275,
  276,   91,  278,  279,  280,  281,   -1,   -1,   -1,   -1,
   -1,   30,   -1,   -1,   -1,  158,  159,  160,   37,   -1,
   39,   -1,   -1,   42,  273,  274,  275,  276,   91,  278,
  279,  280,  281,  273,  274,  275,  276,   -1,  278,  279,
  280,  281,   -1,   -1,   63,   37,   -1,   -1,   -1,   -1,
   42,   -1,   -1,   -1,   46,   47,   -1,   -1,   -1,   -1,
  273,  274,  275,  276,   -1,  278,  279,  280,  281,  273,
  274,  275,  276,   -1,  278,  279,  280,  281,   -1,   -1,
   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,  273,  274,  275,  276,   91,
  278,  279,  280,  281,   59,   60,   -1,   62,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,  280,  281,
   -1,   -1,  273,  274,  275,  276,   91,  278,  279,  280,
  281,   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   58,   37,   60,   -1,
   62,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   60,   -1,   62,   -1,   -1,   37,   -1,   -1,   -1,   91,
   42,   43,   -1,   45,   46,   47,   -1,   41,   -1,   43,
   44,   45,   -1,   -1,   41,   -1,   -1,   44,   60,   -1,
   62,   91,   -1,   93,   58,   59,   60,   61,   62,   -1,
   -1,   58,   59,   41,   61,   43,   44,   45,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   91,
   58,   59,   60,   61,   62,   41,   -1,   -1,   44,   93,
   58,   59,   60,   61,   62,   -1,   93,   -1,   -1,   -1,
   -1,   -1,   58,   59,   60,   61,   62,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   93,   58,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   93,   58,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,   41,   -1,   -1,
   44,   93,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   93,   58,   59,   60,   61,   62,   41,
   -1,   -1,   44,   58,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   33,   61,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   33,   93,
   45,  273,  274,  275,  276,   40,  278,  279,   93,   37,
   45,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   93,   -1,  273,  274,  275,  276,   -1,  278,  279,
   33,   -1,   60,   -1,   62,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,  273,
  274,  275,  276,   91,  278,  279,  280,  281,   33,   -1,
   -1,  278,  279,  280,  281,   40,   -1,   -1,  123,   -1,
   45,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,  280,  281,   -1,  273,  274,  275,  276,   -1,
  278,  279,  280,  281,   -1,   -1,   -1,  273,  274,  275,
  276,   33,  278,  279,  280,  281,   -1,   -1,   40,   -1,
   -1,   -1,  125,   45,   -1,   -1,   -1,   -1,   33,   -1,
   -1,  273,  274,  275,  276,   40,  278,  279,  280,  281,
   45,   -1,   -1,  273,  274,  275,  276,   33,  278,  279,
  280,  281,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
  125,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,  280,  281,  273,  274,
  275,  276,   -1,  278,  279,  280,  281,   -1,   -1,   -1,
   -1,   -1,   -1,  125,   -1,   91,  278,  279,  280,  281,
   -1,   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,
   -1,   -1,  257,  268,   -1,  270,  271,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  270,  271,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,   -1,  261,  262,
   -1,   -1,   -1,   -1,   -1,  268,   -1,  270,  271,  272,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,
   -1,   -1,   -1,  268,   -1,  270,  271,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,  261,
  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,  270,  271,
  272,   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,
   -1,   -1,   -1,  268,   -1,  270,  271,  272,   -1,   -1,
   -1,  257,   -1,   -1,   -1,   -1,   -1,   -1,  264,  265,
  266,  267,   -1,   -1,  270,  271,  272,  257,  258,  259,
   -1,  261,  262,   -1,   -1,   -1,   -1,   -1,  268,   -1,
  270,  271,  272,   53,   54,   55,   56,   57,   -1,   -1,
   -1,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   84,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   95,   96,   97,   98,   99,
  100,   -1,   -1,  103,  104,  105,  106,  107,  108,  109,
  110,   -1,  112,   -1,   -1,   -1,  116,   -1,  118,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  126,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  154,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
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
"OR","AND","INCREMENT","DECREMENT","CAST","UNARIO",
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
"sentencia : expresion INCREMENT ';'",
"sentencia : expresion DECREMENT ';'",
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

//#line 216 "../../src/parser/parser.y"
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
//#line 614 "Parser.java"
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
//#line 65 "../../src/parser/parser.y"
{ ast = new Program(0,0,(List<Definition>) val_peek(9),(List<Statement>) val_peek(1));}
break;
case 2:
//#line 67 "../../src/parser/parser.y"
{List<Definition> defs = (List<Definition>)val_peek(1);List<Definition> def = (List<Definition>)val_peek(0);for(Definition d:def){defs.add(d);}yyval=defs;}
break;
case 3:
//#line 68 "../../src/parser/parser.y"
{yyval = new ArrayList<Definition>();}
break;
case 4:
//#line 72 "../../src/parser/parser.y"
{yyval = val_peek(1);}
break;
case 5:
//#line 73 "../../src/parser/parser.y"
{List<FunDefinition> fd = new ArrayList<FunDefinition>(); fd.add((FunDefinition) val_peek(0));yyval=fd;}
break;
case 6:
//#line 78 "../../src/parser/parser.y"
{ FunctionType ft = new FunctionType(scanner.getLine(),scanner.getColumn(),(Type) val_peek(3),(List<VarDefinition>)val_peek(6));yyval = new FunDefinition(scanner.getLine(),scanner.getColumn(), (String) val_peek(8),ft,(List<Statement>) val_peek(1));}
break;
case 7:
//#line 80 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 8:
//#line 81 "../../src/parser/parser.y"
{ yyval = VoidType.getInstance();}
break;
case 9:
//#line 86 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 10:
//#line 87 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 11:
//#line 88 "../../src/parser/parser.y"
{ List<Statement> st = (List<Statement>) val_peek(1);List<Statement> sent = (List<Statement>) val_peek(0);for(Statement s: sent){st.add(s);}yyval=st;}
break;
case 12:
//#line 89 "../../src/parser/parser.y"
{ yyval = new ArrayList<Statement>();}
break;
case 13:
//#line 93 "../../src/parser/parser.y"
{ yyval = new ArrayList<VarDefinition>();}
break;
case 14:
//#line 94 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 15:
//#line 97 "../../src/parser/parser.y"
{ List<VarDefinition> par = new ArrayList<VarDefinition>();par.add((VarDefinition)val_peek(0));yyval=par;}
break;
case 16:
//#line 98 "../../src/parser/parser.y"
{ List<VarDefinition> pars = (List<VarDefinition>) val_peek(2);pars.add((VarDefinition)val_peek(0));yyval=pars;}
break;
case 17:
//#line 100 "../../src/parser/parser.y"
{ yyval = new VarDefinition(scanner.getLine(),scanner.getColumn(),(String) val_peek(2), (Type) val_peek(0));}
break;
case 18:
//#line 105 "../../src/parser/parser.y"
{ yyval = val_peek(1);}
break;
case 19:
//#line 106 "../../src/parser/parser.y"
{ List<Definition> defs = (List<Definition>)val_peek(2); List<VarDefinition> def = (List<VarDefinition>) val_peek(1); for(VarDefinition var:def){defs.add(var);}yyval=defs;}
break;
case 20:
//#line 110 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(2); List<VarDefinition> def = new ArrayList<VarDefinition>();for(String id:ids){def.add(new VarDefinition(scanner.getLine(),scanner.getColumn(),id, (Type) val_peek(0)));}yyval=def;}
break;
case 21:
//#line 113 "../../src/parser/parser.y"
{ List<String> ids = new ArrayList<String>(); ids.add((String) val_peek(0)); yyval=ids;}
break;
case 22:
//#line 114 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(2); if(!ids.contains((String) val_peek(0))){ids.add((String) val_peek(0));}else{new ErrorType(scanner.getLine(),scanner.getColumn(),"No puedes definir dos variables con el mismo nombre");}yyval=ids;}
break;
case 23:
//#line 117 "../../src/parser/parser.y"
{ yyval = IntType.getInstance();}
break;
case 24:
//#line 118 "../../src/parser/parser.y"
{ yyval = RealType.getInstance();}
break;
case 25:
//#line 119 "../../src/parser/parser.y"
{ yyval = CharType.getInstance();}
break;
case 26:
//#line 120 "../../src/parser/parser.y"
{ yyval = new ArrayType(scanner.getLine(),scanner.getColumn(),(int) val_peek(2), (Type) val_peek(0));}
break;
case 27:
//#line 121 "../../src/parser/parser.y"
{ yyval = new RecordType(scanner.getLine(),scanner.getColumn(),(List<RecordField>)val_peek(1));}
break;
case 28:
//#line 125 "../../src/parser/parser.y"
{yyval=val_peek(0);}
break;
case 29:
//#line 126 "../../src/parser/parser.y"
{List<RecordField> camps = (List<RecordField>)val_peek(1); List<RecordField> def = (List<RecordField>) val_peek(0);for(RecordField r:def){if(camps.contains(r)){r.setType(new ErrorType(r,"No puedes definir dos variables con el mismo nombre dentro de un struct"));}else{camps.add(r);}}yyval=camps;}
break;
case 30:
//#line 129 "../../src/parser/parser.y"
{ List<String> ids = (List<String>) val_peek(3);List<RecordField> def = new ArrayList<RecordField>();for(String id:ids){def.add(new RecordField(scanner.getLine(),scanner.getColumn(),id,(Type) val_peek(1),0));}yyval=def;}
break;
case 31:
//#line 133 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 32:
//#line 134 "../../src/parser/parser.y"
{ List<Statement> states = (List<Statement>)val_peek(1);List<Statement> st = (List<Statement>)val_peek(0);for(Statement s:st){states.add(s);}yyval=states;}
break;
case 33:
//#line 138 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>();List<Expression> exps = (List<Expression>) val_peek(1); for(Expression e:exps){states.add(new Write(scanner.getLine(),scanner.getColumn(),e));}yyval=states;}
break;
case 34:
//#line 139 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>();List<Expression> exps = (List<Expression>) val_peek(1); for(Expression e:exps){states.add(new Read(scanner.getLine(),scanner.getColumn(),e));}yyval=states;}
break;
case 35:
//#line 140 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Expression e = (Expression) val_peek(1);states.add(new Return(scanner.getLine(),scanner.getColumn(),e));yyval=states;}
break;
case 36:
//#line 141 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); IfStatement ifs = (IfStatement) val_peek(0); states.add(ifs);yyval=states;}
break;
case 37:
//#line 142 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); IfStatement ifs = (IfStatement) val_peek(0); states.add(ifs);yyval=states;}
break;
case 38:
//#line 143 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); WhileStatement wS = (WhileStatement) val_peek(0); states.add(wS);yyval=states;}
break;
case 39:
//#line 144 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Assignment aS = (Assignment) val_peek(1); states.add(aS);yyval=states;}
break;
case 40:
//#line 145 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); Invocation iS = (Invocation) val_peek(1); states.add(iS);yyval=states;}
break;
case 41:
//#line 146 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); AlterVal iN = new AlterVal(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1)) ; states.add(iN);yyval=states;}
break;
case 42:
//#line 147 "../../src/parser/parser.y"
{ List<Statement> states = new ArrayList<Statement>(); AlterVal dN = new AlterVal(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1)) ; states.add(dN);yyval=states;}
break;
case 43:
//#line 151 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(),scanner.getColumn(),(String) val_peek(0));}
break;
case 44:
//#line 152 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(),scanner.getColumn(),(int) val_peek(0));}
break;
case 45:
//#line 153 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(),scanner.getColumn(),(char) val_peek(0));}
break;
case 46:
//#line 154 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(),scanner.getColumn(),(double) val_peek(0));}
break;
case 47:
//#line 155 "../../src/parser/parser.y"
{ yyval = val_peek(1);}
break;
case 48:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Indexing(scanner.getLine(),scanner.getColumn(),(Expression)val_peek(3),(Expression)val_peek(1));}
break;
case 49:
//#line 157 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(0));}
break;
case 50:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(0),(Type) val_peek(2));}
break;
case 51:
//#line 159 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(0));}
break;
case 52:
//#line 160 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(0));}
break;
case 53:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 54:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 55:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 56:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 57:
//#line 165 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 58:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 59:
//#line 167 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 60:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 61:
//#line 169 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 62:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 63:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String) val_peek(1),(Expression)val_peek(0));}
break;
case 64:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 65:
//#line 173 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(),scanner.getColumn(),(Expression) val_peek(2),(String)val_peek(1),(Expression)val_peek(0));}
break;
case 66:
//#line 174 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(3)),(List<Expression>) val_peek(1));}
break;
case 67:
//#line 178 "../../src/parser/parser.y"
{ List<Expression> exp = new ArrayList<Expression>();exp.add((Expression)val_peek(0));yyval=exp;}
break;
case 68:
//#line 179 "../../src/parser/parser.y"
{ List<Expression> exps = (List<Expression>) val_peek(2);exps.add((Expression)val_peek(0));yyval=exps;}
break;
case 69:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(),scanner.getColumn(),(Expression)val_peek(2),(Expression)val_peek(0));}
break;
case 70:
//#line 184 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(),scanner.getColumn(),new Variable(scanner.getLine(),scanner.getColumn(),(String)val_peek(3)),(List<Expression>) val_peek(1));}
break;
case 71:
//#line 189 "../../src/parser/parser.y"
{ yyval = new WhileStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(1),(Expression) val_peek(4));}
break;
case 72:
//#line 194 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(0),null,(Expression) val_peek(2));}
break;
case 73:
//#line 195 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(),scanner.getColumn(),(List<Statement>) val_peek(1),(List<Statement>) val_peek(0),(Expression) val_peek(3));}
break;
case 74:
//#line 197 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 75:
//#line 199 "../../src/parser/parser.y"
{ yyval=val_peek(0);}
break;
case 76:
//#line 200 "../../src/parser/parser.y"
{ yyval=val_peek(1);}
break;
case 77:
//#line 206 "../../src/parser/parser.y"
{ yyval = new ArrayList<Expression>();}
break;
case 78:
//#line 207 "../../src/parser/parser.y"
{ yyval = val_peek(0);}
break;
case 79:
//#line 210 "../../src/parser/parser.y"
{ List<Expression> exp = new ArrayList<Expression>();exp.add((Expression)val_peek(0));yyval=exp;}
break;
case 80:
//#line 211 "../../src/parser/parser.y"
{ List<Expression> exps = (List<Expression>) val_peek(2);exps.add((Expression)val_peek(0));yyval=exps;}
break;
//#line 1083 "Parser.java"
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
