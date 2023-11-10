package myScanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;
import symbolTable.SymbolTable;
import programInternalForm.ProgramInternalForm;
import couple.Couple;
public class MyScanner {
    private final ArrayList<String> operators = new ArrayList<>(
            List.of("+", "-", ":=" ,"*", "/", "mod", "<=", ">=", "=", "!=", "<", ">", "|", "&")
    );

    private final ArrayList<String> separators = new ArrayList<>(
            List.of("[", "]", "{", "}", ".", ",", " ", "\t", "\n", "'", "\"", "$")
            // excludes the : separator because of an error when reading the assignment := operator
            // the case of : is treated separately when writing to the PIF
    );

    private final ArrayList<String> reservedWords = new ArrayList<>(
            List.of("read", "print", "if", "else", "during", "loop", "integer", "string", "char", "break")
    );

    private final String filePath;
    private final SymbolTable IdentifiersSymbolTable;
    private final SymbolTable ConstantsSymbolTable;
    private final ProgramInternalForm pif;

    public MyScanner(String filePath) {
        this.filePath = filePath;
        this.IdentifiersSymbolTable = new SymbolTable(100);
        this.ConstantsSymbolTable = new SymbolTable(100);
        this.pif = new ProgramInternalForm();
    }

    private String readFromFile() throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        Scanner scanner = new Scanner(new File(this.filePath));

        //Read the file with the Code and save the content to a String
        while(scanner.hasNextLine())
            content.append(scanner.nextLine()).append("\n");

        return content.toString().replace("\t", "");
    }

    private List<Couple<String, Couple<Integer, Integer>>> ListOfElements(){
        try{
            String content = this.readFromFile();

            String separatorsString = "[]{}., \t\n'\"$";

            StringTokenizer tokenizer = new StringTokenizer(content, separatorsString, true);
            List<String> tokens = new ArrayList<>();

            //Creating Tokens using the Separators
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                tokens.add(token);
            }

            return Tokenize(tokens);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private List<Couple<String, Couple<Integer, Integer>>> Tokenize(List<String> tokens){

        List<Couple<String, Couple<Integer, Integer>>> resultedTokens = new ArrayList<>();

        boolean isStringConstant = false;
        boolean isCharConstant = false;

        StringBuilder createdString = new StringBuilder();

        int numberLine = 1;
        int numberColumn = 1;

        for (String token : tokens)
        {
            if ("\"".equals(token))
            {
                //If we have a String constant
                createdString.append(token);
                if(isStringConstant)
                {
                    //If the String Constant is complete( "abc" )
                    resultedTokens.add(new Couple<>(createdString.toString(), new Couple<>(numberLine, numberColumn)));
                    createdString = new StringBuilder();
                }
                isStringConstant = !isStringConstant;
            }
            else if ("'".equals(token))
            {
                //If we have a Char constant
                createdString.append(token);
                if (isCharConstant)
                {
                    //If the Char Constant is complete( 'a' )
                    resultedTokens.add(new Couple<>(createdString.toString(), new Couple<>(numberLine, numberColumn)));
                    createdString = new StringBuilder();
                }

                isCharConstant = !isCharConstant;
            }
            else if ("\n".equals(token))
            {
                //If we have a new line
                numberLine++;
                numberColumn = 1;
            }
            else
            {
                //If we are in an Incomplete String Constant( "abc )
                if (isStringConstant)
                    createdString.append(token);
                //If we are in an Incomplete Char Constant( 'c )
                else if (isCharConstant)
                    createdString.append(token);
                //If we have a Complete Token, then we increase the column number
                else if (!" ".equals(token))
                {
                    resultedTokens.add(new Couple<>(token, new Couple<>(numberLine, numberColumn)));
                    numberColumn++;
                }
            }
        }
        return resultedTokens;
    }

    public void scan(){

        List<Couple<String, Couple<Integer, Integer>>> tokens = ListOfElements();
        Boolean lexicalErrorExists = false;

        if(tokens == null){
            return;
        }

        for(Couple<String, Couple<Integer,Integer>> t: tokens)
        {
            String token = t.getFirst();
            if(this.reservedWords.contains(token))
            {
                //If the token is a Reserved Word
                this.pif.add(new Couple<>("RESERVED WORD:  '" + token + "' ", new Couple<>(-1, -1)));
            }

            else if(this.operators.contains(token))
            {
                //If the token is an Operator
                this.pif.add(new Couple<>("OPERATOR:  '" + token + "' ", new Couple<>(-1, -1)));
            }

            else if(Objects.equals(token, ":"))
            {
                //If the token is :
                this.pif.add(new Couple<>("SEPARATOR:  '" + token + "' ",  new Couple<>(-1, -1)));
            }

            else if(this.separators.contains(token))
            {
                //If the token is a Separator
                this.pif.add(new Couple<>("SEPARATOR:  '" + token + "' ", new Couple<>(-1, -1)));
            }

            else if(Pattern.compile("^0|[-|+]?[1-9][0-9]*").matcher(token).matches())
            {
                //If the token is an integer
                this.ConstantsSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("CONSTANT: '" + token + "' ", ConstantsSymbolTable.findPosition(token)));
            }
            else if(Pattern.compile("^'[a-zA-Z0-9]'").matcher(token).matches())
            {
                //If the token is a Char Constant
                this.ConstantsSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("CONSTANT: '" + token + "' ", ConstantsSymbolTable.findPosition(token)));
            }

            else if(Pattern.compile("^\"[a-zA-Z0-9]*\"").matcher(token).matches())
            {
                //If the token is a String Constant
                this.ConstantsSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("CONSTANT: '" + token + "' ", ConstantsSymbolTable.findPosition(token)));
            }

            else if(Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*").matcher(token).matches())
            {
                //If the token is an Identifier
                this.IdentifiersSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("IDENTIFIER: '" + token + "' ", IdentifiersSymbolTable.findPosition(token)));
            }

            else
            {
                //We have a Lexical Error
                Couple<Integer, Integer> CoupleLineColumn = t.getSecond();
                System.out.println("Lexical Error at line: " + CoupleLineColumn.getFirst() + " and column: " + CoupleLineColumn.getSecond() + ", invalid token: " + t.getFirst());
                lexicalErrorExists = true;
            }

        }

        if(!lexicalErrorExists){
            System.out.println("Program is lexically correct!");
            System.out.println();
        }
        else
        {
            System.out.println("Program is lexically incorrect!");
            System.out.println();
        }


    }

    public ProgramInternalForm getPif()
    {
        return this.pif;
    }

    public SymbolTable getIdentifiersSymbolTable()
    {
        return this.IdentifiersSymbolTable;
    }
    public SymbolTable getConstantsSymbolTable()
    {
        return this.ConstantsSymbolTable;
    }
}