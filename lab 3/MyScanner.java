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
            List.of("[", "]", "{", "}", ".", ",", ":", " ", "\t", "\n", "'", "\"", "$")

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

        while(scanner.hasNextLine())
            content.append(scanner.nextLine()).append("\n");

        return content.toString().replace("\t", "");
    }

    private List<Couple<String, Couple<Integer, Integer>>> ListOfElements(){
        String content = this.readFromFile();
        String separatorsString = this.separators.toString();
        StringTokenizer tokenizer = new StringTokenizer(content, separatorsString, true);
        List<String> tokens;

        return tokenize(tokens);


        return null;
    }

    private List<Couple<String, Couple<Integer, Integer>>> tokenize(List<String> tokens) {

        List<Couple<String, Couple<Integer, Integer>>> resultedTokens = new ArrayList<>();

        // To Do complete implementation for tokenize
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
                this.pif.add(new Couple<>("RESERVED WORD:  '" + token + "' ", new Couple<>(-1, -1)));
            }
            else if(this.operators.contains(token))
            {
                this.pif.add(new Couple<>("OPERATOR:  '" + token + "' ", new Couple<>(-1, -1)));
            }
            else if(Objects.equals(token, ":"))
            {
                this.pif.add(new Couple<>("SEPARATOR:  '" + token + "' ",  new Couple<>(-1, -1)));
            }
            else if(this.separators.contains(token))
            {
                this.pif.add(new Couple<>("SEPARATOR:  '" + token + "' ", new Couple<>(-1, -1)));
            }
            else if(Pattern.compile("^0|[-|+]?[1-9][0-9]*").matcher(token).matches())
            {
                this.ConstantsSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("CONSTANT: '" + token + "' ", ConstantsSymbolTable.findPosition(token)));
            }
            else if(Pattern.compile("^'[a-zA-Z0-9]'").matcher(token).matches())
            {
                this.ConstantsSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("CONSTANT: '" + token + "' ", ConstantsSymbolTable.findPosition(token)));
            }
            else if(Pattern.compile("^\"[0-9a-zA-Z]*\"").matcher(token).matches())
            {
                this.ConstantsSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("CONSTANT: '" + token + "' ", ConstantsSymbolTable.findPosition(token)));
            }
            else if(Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*").matcher(token).matches())
                    {
                this.IdentifiersSymbolTable.add(token);
                this.pif.add(new Couple<String, Couple<Integer,Integer>>("IDENTIFIER: '" + token + "' ", IdentifiersSymbolTable.findPosition(token)));
            }
            else
            {
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

    public SymbolTable getIdentifiersSymbolTable(){
        return this.IdentifiersSymbolTable;
    }
    public SymbolTable getConstantsSymbolTable(){
        return this.ConstantsSymbolTable;
    }
}