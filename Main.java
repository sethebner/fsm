package finiteStateMachine;

import java.util.ArrayList;

public class Main {

	public static void main(String args[])
	{
		ArrayList<Character> terminals = new ArrayList<Character>();
		terminals.add('a');
		terminals.add('b');
		terminals.add('\0');
		LinkedFSM fsm = new LinkedFSM(false, terminals);
		
		String inputString = "aaa";
		char[] input = inputString.toCharArray(); //represent String an as array of characters to feed into the FSM one at a time
		
		//Create the states in the FSM
		State q1 = fsm.createState("q1", true);

		// Define the transition function
		// fsm.connect(A, B, 'x') creates a transition from State A to State B by consuming the character 'x'
		fsm.connect(fsm.getStart(), fsm.getStart(), 'a');
		fsm.connect(fsm.getStart(), q1, 'b');
		fsm.connect(fsm.getStart(), q1, '\0');
		fsm.connect(q1, q1, 'a');
		fsm.connect(q1, q1, 'b');
		
		for(char c : input)
		{
			System.out.print("Processing '" + c + "'. ");
			fsm.processCharacter(c);
			System.out.print("Now in state(s): ");

			for(State s : fsm.getInhabitedStates())
			{
				s.printNode();
			}
			
			System.out.print("\t");
			System.out.println(fsm.inAcceptingState() ? "We're accepting at this point." : "We're not accepting at this point.");
		}
		
		System.out.println(fsm.inAcceptingState() ? "INPUT ACCEPTED" : "INPUT REJECTED");
		
	}
	
}
