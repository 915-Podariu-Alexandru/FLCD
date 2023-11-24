package finiteAutomaton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import couple.Couple;
public class FiniteAutomaton {
    private final String separator = ",";
    private boolean isDeterministic;
    private String initialState;
    private List<String> states, alphabet, finalStates;
    //Map that holds a couple ( start state and alphabet symbol) and a set of end states, forming the transitions
    // Format: Start State - Alphabet Symbol - End State
    private Map<Couple<String, String>, Set<String>> transitions;

    public FiniteAutomaton(String file){
        this.transitions = new HashMap<>();
        this.readFile(file);

    }
    public FiniteAutomaton() {
        this.transitions = new HashMap<>();

    }
    public void readFile(String file)
    {
        try(Scanner scanner = new Scanner(new File(file))){
            //add to the lists the possible states, alphabet and final States
            this.states = new ArrayList<>(List.of(scanner.nextLine().split(this.separator)));
            this.initialState = scanner.nextLine();
            this.finalStates = new ArrayList<>(List.of(scanner.nextLine().split(this.separator)));
            this.alphabet = new ArrayList<>(List.of(scanner.nextLine().split(this.separator)));

            while(scanner.hasNextLine())
            {
                //Add to the map of transitions the corresponding Key - Value combinations for each transition
                String transitionLine = scanner.nextLine();
                String[] transitionElements = transitionLine.split(" ");

                // Format: Start State - Alphabet Symbol - End State
                if( states.contains(transitionElements[0]) && alphabet.contains(transitionElements[1]) && states.contains(transitionElements[2]) )
                {
                    Couple<String,String> transitionStates = new Couple<> (transitionElements[0], transitionElements[1]);
                    //Check if the Key Couple Start State - Alphabet Symbol exists and add to its set another End State
                    if(!transitions.containsKey(transitionStates))
                    {
                        Set<String> transitionStatesSet = new HashSet<>();
                        transitionStatesSet.add(transitionElements[2]);
                        transitions.put(transitionStates, transitionStatesSet);
                    }
                    else
                        transitions.get(transitionStates).add(transitionElements[2]);
                }
            }
        }
        catch (FileNotFoundException exception)
        {
            exception.printStackTrace();
        }
        this.isDeterministic = isDeterministic();
    }
    public boolean isDeterministic() {
        return this.transitions.values().stream().allMatch(list -> list.size() <= 1);
    }

    public boolean acceptsSequence(String sequence)
    {
        //If sequence is not Deterministic then return false
        if(!this.isDeterministic)
            return false;

        if(sequence.isEmpty())
            return finalStates.contains(initialState);

        String currentState = this.initialState;

        for (int i = 0; i < sequence.length(); i++)
        {
            //Go through the sequence of symbols and check if the Key ( Start State - symbol ) exists
            String currentSymbol = sequence.substring(i, i + 1);
            Couple<String, String> transition = new Couple<>(currentState, currentSymbol);

            if (!this.transitions.containsKey(transition))
                return false;
            else
                currentState = this.transitions.get(transition).iterator().next();
        }
        //Check that the sequence finishes at the correct Final State
        return this.finalStates.contains(currentState);
    }
    public String getInitialState()
    {
        return this.initialState;
    }
    public List<String> getAlphabet()
    {
        return this.alphabet;
    }
    public List<String> getStates()
    {
        return this.states;
    }

    public List<String> getFinalStates()
    {
        return this.finalStates;
    }
    public Map<Couple<String,String>, Set<String>> getTransitions()
    {
        return this.transitions;
    }
}

