import java.io.FileNotFoundException;
import java.io.PrintStream;
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

    public static void main(String[] args)
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
}