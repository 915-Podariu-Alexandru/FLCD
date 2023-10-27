package hashTable;
import couple.Couple;
import java.util.ArrayList;

public class HashTable {
    private Integer size;
    private ArrayList<ArrayList<String>> table;
    public HashTable(Integer size)
    {
        this.size = size;
        this.table = new ArrayList<>();

        for(int i = 0; i < size; i++)
            this.table.add(new ArrayList<>());
    }

    public boolean add(String term)
    {
        if(containsTerm(term))
            return false;

        Integer position = createHash(term);

        ArrayList<String> elements = this.table.get(position);
        elements.add(term);

        return true;
    }


    public Integer getSize()
    {
        return size;
    }

    public Couple findPosition(String term)
    {
        int position = createHash(term);

        if(!table.get(position).isEmpty())
        {
            ArrayList<String> elements = this.table.get(position);

            for(int i = 0; i < elements.size(); i++)
                if( elements.get(i).equals(term) )
                    return new Couple(position, i);

        }
        return null;
    }

    private Integer createHash(String key)
    {
        int sum = 0;
        char[] keyChar = key.toCharArray();

        for(char c: keyChar)
            sum += c;

        return sum % size;
    }

    public boolean containsTerm(String term)
    {
        if(this.findPosition(term) == null)
            return false;
        return true;
    }


    @Override
    public String toString()
    {
        return "SymbolTable: " + table ;
    }

}