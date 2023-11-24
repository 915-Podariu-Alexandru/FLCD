import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import finiteAutomaton.FiniteAutomaton;
import myScanner.MyScanner;


public class Main {
    private static void printToFile(String filePath, Object object)
    {
        //Print to the File
        try(PrintStream printStream = new PrintStream(filePath))
        {
            printStream.println(object);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static void run(String filePath)
    {
        MyScanner scanner = new MyScanner(filePath);
        scanner.scan();
        printToFile(filePath.replace(".txt", "CST.txt"), scanner.getConstantsSymbolTable());

        printToFile(filePath.replace(".txt", "IST.txt"), scanner.getIdentifiersSymbolTable());

        printToFile(filePath.replace(".txt", "PIF.txt"), scanner.getPif());
    }
    public static void printMenu()
    {
        System.out.println("1. States");
        System.out.println("2. Initial State");
        System.out.println("3. Final States");
        System.out.println("4. Alphabet");
        System.out.println("5. Transitions");
        System.out.println("6. Is Deterministic");
        System.out.println("7. Is Sequence Accepted by the DFA");
        System.out.println("0. Exit");
    }

    public static void DFA()
    {
        FiniteAutomaton fa = new FiniteAutomaton("IO/FA.txt");
        System.out.println("FA has been read from the file");
        printMenu();

        System.out.println("Option: ");
        Scanner optionScanner = new Scanner(System.in);
        int option = optionScanner.nextInt();

        while(option != 0)
        {
            switch(option)
            {
                case 1:
                    System.out.println("States:");
                    System.out.println(fa.getStates());
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Initial State:");
                    System.out.println(fa.getInitialState());
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Final States:");
                    System.out.println(fa.getFinalStates());
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Alphabet:");
                    System.out.println(fa.getAlphabet());
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Transitions:");
                    System.out.println(fa.getTransitions());
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Is Deterministic?");
                    System.out.println(fa.isDeterministic());
                    System.out.println();
                    break;
                case 7:
                    {
                        System.out.println("Your sequence:");
                        Scanner sequenceScanner = new Scanner(System.in);
                        String sequence = sequenceScanner.nextLine();
                        if (fa.acceptsSequence(sequence))
                            System.out.println("Sequence is Valid");
                        else
                            System.out.println("Sequence is Invalid");
                    }
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
            System.out.println();
            printMenu();
            System.out.println("Your option:");
            option = optionScanner.nextInt();
        }
    }
    public static void MyScanner()
    {
        System.out.println("P1:");
        run("IO/p1.txt");

        System.out.println("P2:");
        run("IO/p2.txt");

        System.out.println("P3:");
        run("IO/p3.txt");

        System.out.println("P1ERR:");
        run("IO/p1err.txt");
    }
    public static void main(String[] args)
    {
        System.out.println("1. Finite Automaton");
        System.out.println("2. Scanner");
        System.out.println("0. Exit");
        System.out.println("Option: ");

        Scanner optionScanner = new Scanner(System.in);
        int option = optionScanner.nextInt();

        while(option != 0)
        {
            switch(option)
            {
                case 1:
                    DFA();
                    break;
                case 2:
                    MyScanner();
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
            System.out.println("1. Finite Automaton");
            System.out.println("2. Scanner");
            System.out.println("0. Exit");
            System.out.println("Option: ");
            option = optionScanner.nextInt();
        }
    }
}