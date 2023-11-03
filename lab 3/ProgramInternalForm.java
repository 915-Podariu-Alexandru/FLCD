package programInternalForm;
import java.util.ArrayList;
import java.util.List;
import couple.Couple;
public class ProgramInternalForm {
    private List< Couple <String, Couple<Integer, Integer>>> tokenPosition;
    public ProgramInternalForm()
    {
        this.tokenPosition = new ArrayList<>();
    }
    public void add(Couple<String, Couple<Integer, Integer>> couple){
        this.tokenPosition.add(couple);
    }
    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder();

        for(int i = 0; i < this.tokenPosition.size(); i++)
            out.append(this.tokenPosition.get(i).getFirst())
                    .append(" AT POSITION: ")
                    .append(this.tokenPosition.get(i).getSecond())
                    .append("\n");

        return out.toString();
    }

}
