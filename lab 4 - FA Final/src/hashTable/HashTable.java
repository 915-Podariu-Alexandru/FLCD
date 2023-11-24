package hashTable;
import couple.Couple;
import java.util.ArrayList;

public class HashTable {
    private final Integer size;
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
        //Add an element to the Hash Table
        Integer position = createHash(term);

        ArrayList<String> elements = this.table.get(position);
        elements.add(term);

        return true;
    }


    public Integer getSize()
    {
        return size;
    }

    public Couple<Integer, Integer> findPosition(String term)
    {
        int position = createHash(term);

        //Find the Position of an element from Hash Table
        if(!table.get(position).isEmpty())
        {
            ArrayList<String> elements = this.table.get(position);

            for(int i = 0; i < elements.size(); i++)
                if( elements.get(i).equals(term) )
                    return new Couple<>(position, i);

        }
        return null;
    }

    private Integer createHash(String key)
    {
        int sum = 0;
        char[] keyChar = key.toCharArray();
        //Create the Hash for a new element
        for(char c: keyChar)
            sum += c;

        return sum % size;
    }

    public boolean containsTerm(String term)
    {
        return this.findPosition(term) != null;
    }


    @Override
    public String toString()
    {
        //Output for the Hash Table
        StringBuilder computedString = new StringBuilder();
        for(int i = 0; i < this.table.size(); i++){
            if(!this.table.get(i).isEmpty()){
                computedString.append(i);
                computedString.append(": ");
                computedString.append(this.table.get(i));
                computedString.append("\n");
            }
        }
        return computedString.toString();
    }

}