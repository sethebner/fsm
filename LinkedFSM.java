package finiteStateMachine;

import java.util.ArrayList;

public class LinkedFSM {

	private State start;
	private ArrayList<State> allStates;
	private ArrayList<State> inhabitedStates;
	private State deadState;
	private ArrayList<Character> terminals;
	//TODO: add in terminal alphabet and a check to make sure the input contains symbols only from that alphabet
	//TODO: add in transition table and method to automatically create transitions from it
	//TODO: figure out how to implement lambda moves (perhaps with space characters?)

	public LinkedFSM(boolean startAccepting, ArrayList<Character> terminals)
	{
		this.start = new State("START", startAccepting);
		this.allStates = new ArrayList<State>(); //list of all the states in the FSM
		this.allStates.add(this.start);
		this.inhabitedStates = new ArrayList<State>(); //list of all the states in the FSM that are currently occupied
		this.inhabitedStates.add(this.start);
		this.terminals = terminals;
		
		this.deadState = new State("DEAD", false);
		for (char c : terminals)
		{
			connect(deadState, deadState, c);
		}
	}

	public State getStart() { return this.start; }
	public ArrayList<State> getAllStates() { return this.allStates; }
	public ArrayList<State> getInhabitedStates() { return this.inhabitedStates; }

	/**
	 * Determines if there are no states in the FSM
	 */
	public boolean isEmpty()
	{
		return this.allStates.isEmpty();
	}

	/**
	 * Create a new state to use in the FSM
	 */
	public State createState(String id, boolean accepting)
	{
		State state = new State(id, accepting);
		this.allStates.add(state);
		return state;
	}

	/**
	 * Connects State A to State B with a transition that consumes character 'c'
	 */
	public boolean connect(State a, State b, char c)
	{
		if (this.terminals.contains(c))
		{
			return a.setTransition(b, c);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Given a list of states we are in, determines where we could be after consuming character 'c'
	 */
	public void processCharacter(char c)
	{
		if (!this.terminals.contains(c) && !this.inhabitedStates.contains(deadState))
		{
			this.inhabitedStates.clear();
			this.inhabitedStates.add(deadState);
		//	System.out.println("We hit an unexpected character. Entering dead state...");
			//return this.inhabitedStates;
		}
		
		ArrayList<State> destinations = new ArrayList<State>();
		for(State state : this.inhabitedStates)
		{
			if(state.getTransitions().containsKey(c)) //there exists a transition to some state on character 'c'
			{
				for(State d : state.getNext(c))
				{
					destinations.add(d);
				}
			}
			if(state.getTransitions().containsKey('\0'))
			{
				for(State d : state.getNext('\0'))
				{
					destinations.add(d);
				}
			}
		}

		this.inhabitedStates = destinations;
		//return destinations; //all the states we can be in after consuming a character 'c'
	}

	/**
	 * Given a list of states we are in, determines if the amount of the string processed so far is accepted
	 */
	public boolean inAcceptingState()
	{
		for(State state : this.inhabitedStates)
		{
			if(state.isAccepting())
			{
				//System.out.println("We're accepting at this point.");
				return true;
			}
		}
		//System.out.println("We're not accepting at this point.");
		return false;
	}

}
