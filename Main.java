import java.util.Scanner;

import GraphPackage.DirectedGraph;
import GraphPackage.GraphInterface;

public class Main {

	public static void main(String[] args) {
		char decision;
		
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
		System.out.println("D Find the minimum distance between two cities");
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
			case 'D':
			case 'd':
					return 'D';
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
