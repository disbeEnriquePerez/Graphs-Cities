import java.util.Scanner;

import GraphPackage.DirectedGraph;
import GraphPackage.GraphInterface;

public class Main {

/*
 * NOTE: DEBUGING HAS NOT BEEN FULLY DONE YET THERE ARE BUGS THAT CAN BE ENCOUNTERED WHILE USING THIS PROGRAM.
 * BECAREFUL AND MAKE SURE TO ENTER CODE PROPERLY OR ELSE UNPREDICATABLE ERRORS COULD OCCUR AND BE FATAL
 */
	
	public static void main(String[] args) {
		char decision;
		// THIS CLASS IN CHARGE OF THE INTERFACE
		// AND CREATING THE DECISION MAKER AND DIRECTED GRAPH
		GraphInterface<String> map = new DirectedGraph<>();
		DecisionMaker Decision = new DecisionMaker(map);
		
		
		do{
			
			decision = menu();
			Decision.Decision(decision);
			
		}while(decision != 'E');
		System.out.println("Thank you for Using our Services\nGood Bye!");
		
		
	}
	
	public static char menu()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Q Display city information by entering a city code");
		System.out.println("Z Enter a new City to the Graph");
		System.out.println("S Is Connected? Are two Cities Connected?");
		System.out.println("D Find the minimum distance between two cities");
		System.out.println("T Find the the Shortest path between two cities" );
		System.out.println("I Insert a road by entering two city codes and a distance");
		System.out.println("R Remove an existing road by entering two city codes");
		System.out.println("E Exit");
		System.out.print("Command?: ");
		String string = keyboard.nextLine();
		string.toUpperCase();
		
		char Letter = (string.toCharArray())[0];
		
		switch(Letter){
			case 'Q':
			case 'q':
					return 'Q'; 
			case 'Z':
			case 'z':
					return 'Z';
			case 'S':
			case 's':
					return 'S';
			case 'D':
			case 'd':
					return 'D';
			case 'T':
			case 't':
					return 'T';
			case 'I':
			case 'i':
					return 'I';
			case 'R':
			case 'r':
					return 'R';
			case 'E':
			case 'e':
					return 'E';
			default:
					System.out.println("Please Enter from the selection");
					return menu();
					
		}
	}

}
