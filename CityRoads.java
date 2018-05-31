import GraphPackage.*;
import java.util.*;
public class CityRoads {
	String CityCode1, CityCode2;
	Scanner keyboard;
	public CityRoads()
	{
		keyboard = new Scanner(System.in);
	}
		
	public void addRoad()
	{
		System.out.print("Enter City Code 1: ");
		CityCode1 = keyboard.next();
		System.out.println("\nEnter City Code 2: ");
		CityCode2 = keyboard.next();
		
		
	}
}
