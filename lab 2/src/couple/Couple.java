package couple;

public class Couple {
    private Integer first;
    private Integer second;

    public Couple(Integer first, Integer second)
    {
        this.first = first;
        this.second = second;
    }

    public Integer getFirst()
    {
        return this.first;
    }

    public Integer getSecond()
    {
        return this.second;
    }
    @Override
    public String toString() {
        return " the Hash Couple is: { " + first + " , " + second + " }";
    }
}
