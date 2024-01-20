/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     INTEGER = 258,
     STRING = 259,
     CHAR = 260,
     READ = 261,
     PRINT = 262,
     IF = 263,
     ELSE = 264,
     LOOP = 265,
     DURING = 266,
     ARRAY = 267,
     START = 268,
     BREAK = 269,
     plus = 270,
     minus = 271,
     mul = 272,
     division = 273,
     mod = 274,
     lessOrEqual = 275,
     moreOrEqual = 276,
     less = 277,
     more = 278,
     equal = 279,
     different = 280,
     eq = 281,
     and = 282,
     or = 283,
     leftCurlyBracket = 284,
     rightCurlyBracket = 285,
     leftRoundBracket = 286,
     rightRoundBracket = 287,
     leftBracket = 288,
     rightBracket = 289,
     colon = 290,
     point = 291,
     comma = 292,
     apostrophe = 293,
     quote = 294,
     IDENTIFIER = 295,
     NUMBER_CONST = 296,
     STRING_CONST = 297,
     CHAR_CONST = 298
   };
#endif
/* Tokens.  */
#define INTEGER 258
#define STRING 259
#define CHAR 260
#define READ 261
#define PRINT 262
#define IF 263
#define ELSE 264
#define LOOP 265
#define DURING 266
#define ARRAY 267
#define START 268
#define BREAK 269
#define plus 270
#define minus 271
#define mul 272
#define division 273
#define mod 274
#define lessOrEqual 275
#define moreOrEqual 276
#define less 277
#define more 278
#define equal 279
#define different 280
#define eq 281
#define and 282
#define or 283
#define leftCurlyBracket 284
#define rightCurlyBracket 285
#define leftRoundBracket 286
#define rightRoundBracket 287
#define leftBracket 288
#define rightBracket 289
#define colon 290
#define point 291
#define comma 292
#define apostrophe 293
#define quote 294
#define IDENTIFIER 295
#define NUMBER_CONST 296
#define STRING_CONST 297
#define CHAR_CONST 298




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

