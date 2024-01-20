%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1 
%}

%token INTEGER
%token STRING
%token CHAR
%token READ
%token PRINT
%token IF
%token ELSE
%token LOOP
%token DURING
%token ARRAY
%token START
%token BREAK

%token plus
%token minus
%token mul
%token division
%token mod
%token lessOrEqual
%token moreOrEqual
%token less
%token more
%token equal
%token different
%token eq
%token and
%token or

%token leftCurlyBracket
%token rightCurlyBracket
%token leftRoundBracket
%token rightRoundBracket
%token leftBracket
%token rightBracket
%token colon
%token point
%token comma
%token apostrophe
%token quote

%token IDENTIFIER
%token NUMBER_CONST
%token STRING_CONST
%token CHAR_CONST

%start program

%%

program : START cmpdstmt

stmt : declaration point | assignstmt | iostmt point | ifstmt | duringstmt | loopstmt

stmtlist : stmt | stmt stmtlist

cmpdstmt : leftRoundBracket stmtlist rightRoundBracket

expression : expression plus term | expression minus term | term

term : term mul factor | term division factor | term mod factor | factor 

factor : leftBracket expression rightBracket | IDENTIFIER | constant

constant : NUMBER_CONST | STRING_CONST | CHAR_CONST 

iostmt : READ leftBracket IDENTIFIER rightBracket | PRINT leftBracket IDENTIFIER rightBracket | PRINT leftBracket constant rightBracket

simpletype : INTEGER | STRING | CHAR

arraydeclaration : ARRAY simpletype IDENTIFIER leftCurlyBracket NUMBER_CONST rightCurlyBracket

declaration : simpletype IDENTIFIER | arraydeclaration 

assignstmt : IDENTIFIER eq expression point

ifstmt : IF leftBracket condition rightBracket cmpdstmt | IF leftBracket condition rightBracket cmpdstmt ELSE cmpdstmt

duringstmt : DURING leftBracket condition rightBracket cmpdstmt

loopstmt : LOOP loopcond cmpdstmt 

loopcond : leftBracket INTEGER assignstmt condition assignstmt
rightBracket

condition : expression relation expression

relation : less | lessOrEqual | equal | different | moreOrEqual | more | or | and


%%

yyerror(char *s)
{	
	printf("%s\n",s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
	if(argc>1) yyin :  fopen(argv[1],"r");
	if(argc>2 && !strcmp(argv[2],"-d")) yydebug: 1;
	if(!yyparse()) fprintf(stderr, "\tProgram is syntactically correct.\n");
}