import symbolTable.SymbolTable;
import couple.Couple;

public class Main {
    public static void main(String[] args) {
        SymbolTable symTable = new SymbolTable(4);

        System.out.println("SymbolTable size: " + symTable.getSize());

        System.out.println();
        System.out.println("Not Added previously: " + symTable.add("1"));
        System.out.println(symTable.findPosition("1"));

        System.out.println();
        System.out.println("Not Added previously: " + symTable.add("2"));
        System.out.println(symTable.findPosition("2"));

        System.out.println();
        System.out.println("Not Added previously: " + symTable.add("1"));
        System.out.println(symTable.findPosition("1"));

        System.out.println();
        System.out.println("Not Added previously: " + symTable.add("a"));
        System.out.println(symTable.findPosition("a"));

        System.out.println();
        System.out.println("Not Added previously: " + symTable.add("b"));
        System.out.println(symTable.findPosition("b"));

        System.out.println();
        System.out.println(symTable.getHashTable());
    }
}