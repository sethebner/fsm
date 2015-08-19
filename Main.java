package finiteStateMachine;

import java.util.ArrayList;

public class Main {

	public static void main(String args[])
	{
		ArrayList<Character> terminals = new ArrayList<Character>();
		terminals.add('h');
		terminals.add('e');
		terminals.add('l');
		terminals.add('o');
		terminals.add('\0');
	//	terminals.add('a');
		LinkedFSM fsm = new LinkedFSM(false, terminals);
		
		String inputString = "hellllllllo";
		char[] input = inputString.toCharArray(); //represent String an as array of characters to feed into the FSM one at a time
		
		//Create the states in the FSM
		State q1 = fsm.createState("q1", false);		
		State q2 = fsm.createState("q2", false);		
		State q3 = fsm.createState("q3", false);		
		State q4 = fsm.createState("q4", false);
		State q5 = fsm.createState("q5", true);
		
		State q6 = fsm.createState("q6", false);
		
		// Define the transition function
		// fsm.connect(A, B, 'x') creates a transition from State A to State B by consuming the character 'x'
		fsm.connect(fsm.getStart(), q1, 'h');
		fsm.connect(q1, q2, 'e');
		fsm.connect(q2, q3, 'l');
		fsm.connect(q3, q4, 'l');
		fsm.connect(q4, q5, 'o');	
		fsm.connect(q3, q3, 'l');
		
		fsm.connect(q1, q6, '\0');
		
		
		for(char c : input)
		{
			System.out.print("Processing '" + c + "'. ");
			fsm.processCharacter(c);
			//System.out.println();
			for(State s : fsm.getInhabitedStates())
			{
				System.out.print("Now in state ");
				s.printNode();
			}
			System.out.print("\t");
			if (fsm.inAcceptingState())
			{
				System.out.println("We're accepting at this point.");
			}
			else
			{
				System.out.println("We're not accepting at this point.");
			}
		}
		
		if (fsm.inAcceptingState())
		{
			System.out.println("INPUT ACCEPTED");
		}
		else
		{
			System.out.println("INPUT REJECTED");
		}
		
		
		
	}
	
}
