
public class Main {

	public static void main(String[] args) {
		char decision;
		Main_menu key = new Main_menu();
		
		do{
			decision = key.menu();
			System.out.println(decision);
			
		}while(decision != 'E');
		
		
	}

}
