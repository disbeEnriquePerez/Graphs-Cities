import GraphPackage.*;
import java.util.*;
import StackPackage.*;
public class CityRoads {
	
	private FileActivities file;
	private GraphInterface<String> map;
	private String CityCode1, CityCode2;
	private Scanner keyboard;
	public CityRoads(GraphInterface<String> map, FileActivities file)
	{
		this.file = file;
		this.map = map;
		keyboard = new Scanner(System.in);
	}
	
	public void minimum_route()
	{
		
		System.out.println("City codes (Ex. AN BK): ");
		String citycodes = keyboard.nextLine();
		String[] parts = citycodes.split(" ");
		String CityAbrre1 = parts[0];
		String CityAbree2 = parts[1];
		StackInterface<String> stack = new ArrayStack();
		
		String CityCode= file.getCityCode(CityAbrre1);
		String CityCode2 = file.getCityCode(CityAbree2);
		
		if(CityCode == null || CityCode2 == null)
		{
			System.out.println("Error with City code back to Menu");
			return;
		}
		
		int pathValue = map.getShortestPath(CityCode, CityCode2, stack);
		
		String returnString = "The Minimum distance between " + file.getCityName(CityAbrre1) + " and " + file.getCityName(CityAbree2) + " is " + pathValue + " through ";
		
		while (!stack.isEmpty())
		{	String code = stack.pop().toString();
			if(stack.isEmpty())	
				returnString += file.getCityAbbree(code) + ". ";
			else
				returnString += file.getCityAbbree(code) + ", ";
		}
		
		System.out.println(returnString);
		
	}
	
	public void getMinimumCostRoute()
	{
		System.out.println("City codes (Ex. AN BK): ");
		String citycodes = keyboard.nextLine();
		String[] parts = citycodes.split(" ");
		String CityAbrre1 = parts[0];
		String CityAbree2 = parts[1];
		StackInterface<String> stack = new ArrayStack();
		
		String CityCode= file.getCityCode(CityAbrre1);
		String CityCode2 = file.getCityCode(CityAbree2);
		
		if(CityCode == null || CityCode2 == null)
		{
			System.out.println("Error with City code back to Menu");
			return;
		}
		double pathValue = map.getCheapestPath(CityCode, CityCode2, stack);
		
		String returnString = "The Minimum distance between " + file.getCityName(CityAbrre1) + " and " + file.getCityName(CityAbree2) + " is " + pathValue + " through ";
		
		while (!stack.isEmpty())
		{
			String code = stack.pop().toString();
			if(stack.isEmpty())	
				returnString += file.getCityAbbree(code) + ". ";
			else
			   returnString += file.getCityAbbree(code) + ", ";
		}
		
		System.out.println(returnString + "\n");
	}
	
	public void removeRoad()
	{	
		System.out.println("City Abbrevations to remove a road (Ex. KV MP): ");
		String Abrev = keyboard.next();
		String Abrev2 = keyboard.next();
		
		String code = file.getCityCode(Abrev);
		String code2 = file.getCityCode(Abrev2);
		file.removeRoad(code,code2 ,map.getNumberOfEdges());
		
	}
		
	public void addRoad()
	{
		System.out.print("Enter City Abbreviations and distance (EX. CH PM 100) : ");
		String CityAbree = keyboard.next();
		String CityAbree2 = keyboard.next();
		String Weight = keyboard.next();
		Double weight = Double.parseDouble(Weight);
		
		String Citycode = file.getCityCode(CityAbree);
		String Citycode2 = file.getCityCode(CityAbree2);
		
		file.addNewRoad(Citycode, Citycode2, Weight, map.getNumberOfEdges());
		map.addEdge(Citycode, Citycode2, weight);
		
	}
}
