package finiteStateMachine;

import java.util.ArrayList;
import java.util.HashMap;

public class State {

    private String identifier; //name of state, e.g., q0, q1, ...
    private HashMap<Character, ArrayList<State>> transitions; //transition table is stored as (char, list of states)
    private boolean isAccepting; //is this an accepting state?

    public State(String id, boolean b)
    {
        this.identifier = id;
        this.transitions = new HashMap<Character, ArrayList<State>>();
        this.isAccepting = b;
    }

    public String getIdentifier() { return this.identifier; }
    public HashMap<Character, ArrayList<State>> getTransitions() { return this.transitions; }
    public boolean isAccepting() { return this.isAccepting; }

    /**
     * Determines if there is a transition to State S upon the consumption of the character 'c'
     */
    public boolean hasTransition(State s, char c)
    {
        if(this.transitions.containsKey(c))
        {
            return this.transitions.get(c).contains(s);
        }
        else
        {
            return false;
        }	
    }

    public void setIdentifier(String id) { this.identifier = id; }
    public void setAccepting(boolean b) { this.isAccepting = b; }

    public boolean setTransition(State s, char c)
    {
        //states already connected by character 'c'
        if(this.hasTransition(s, c))
        {
            System.out.println("Transition already exists.");
            return false;
        }
        //state is connected to a different state by the character 'c'
        else if(this.transitions.containsKey(c))
        {
            this.transitions.get(c).add(s);
            return true;
        }
        //state has no transitions on the character 'c'
        else
        {
            ArrayList<State> temp = new ArrayList<State>();
            temp.add(s);
            this.transitions.put(c, temp);
            return true;
        }
    }

    public ArrayList<State> getNext(char c)
    {
        return this.transitions.get(c);
    }

    public void printNode()
    {
        System.out.print("{" + this.identifier + ", " + this.isAccepting + "}");
    }
}
