package couple;
import java.util.Objects;

public class Couple <First,Second> {
    private final First first;
    private final Second second;

    public Couple(First first, Second second)
    {
        this.first = first;
        this.second = second;
    }

    public First getFirst()
    {
        return this.first;
    }

    public Second getSecond()
    {
        return this.second;
    }
    @Override
    public boolean equals(Object object) {

        if (this == object) return true;

        if (object == null || getClass() != object.getClass()) return false;

        Couple<?, ?> other = (Couple<?, ?>) object;

        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
    @Override
    public String toString() {
        return "(" + this.first.toString() + "," + this.second.toString() + ")";
    }
}
