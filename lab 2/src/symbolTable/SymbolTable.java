package symbolTable;

import hashTable.HashTable;
import couple.Couple;
public class SymbolTable {

    private Integer size;
    private HashTable hashTable;


    public SymbolTable(Integer size)
    {
        hashTable = new HashTable(size);
    }

    public HashTable getHashTable()
    {
        return hashTable;
    }

    public Integer getSize()
    {
        return hashTable.getSize();
    }

    public String findPosition(String term)
    {
        return "For "+ term + hashTable.findPosition(term);
    }

    public boolean add(String term){
        return hashTable.add(term);
    }

}