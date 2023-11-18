import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import couple.Couple;
public class FiniteAutomaton {
    private final String separator = ";";
    private boolean isDeterministic;
    private String initialState;
    private List<String> states, alphabet, finalStates;
    private Map<Couple<String, String>, Set<String>> transitions;
    
    public FiniteAutomaton(String file){
        this.transitions = new HashMap<>();
        
    }
    public FiniteAutomaton() {
        this.transitions = new HashMap<>();

    }
    public List<String> getStates()
    {
        return this.states;
    }

    public String readFile(String file)
    {
        return file;
    }
    public String getInitialState()
    {
        return this.initialState;
    }

    public List<String> getAlphabet()
    {
        return this.alphabet;
    }

    public List<String> getFinalStates()
    {
        return this.getFinalStates();
    }
}
