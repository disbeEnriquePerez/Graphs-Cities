
public class Main {

	public static void main(String[] args) {
		char decision;
		Main_menu key = new Main_menu();
		DecisionMaker Decision = new DecisionMaker();
		do{
			
			decision = key.menu();
			Decision.Decision(decision);
			
		}while(decision != 'E');
		System.out.println("Thank you for Using our Services\nGood Bye!");
		
		
	}

}
