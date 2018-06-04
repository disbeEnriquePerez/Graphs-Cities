import java.io.*;
import java.util.*;
public class FileActivities {

	private File city;
	private File road;
	private Scanner CityScann, RoadScann, UserInput;
	
	public FileActivities()
	{
		city = new File("Graphs-Cities/city.dat");
		road = new File("Graphs-Cities/road.dat");
		UserInput = new Scanner(System.in);
		
		if(!city.exists())
		{
			try {
				PrintWriter file = new PrintWriter("/Graphs-Cities/city.dat");
				file.close();	
				city = new File("/Graphs-Cities/city.dat");
			}catch(Exception ex) {
				System.out.println("Error Creating city.dat: ");
			}
		}
		try {
			CityScann = new Scanner(city);
		}catch(Exception ex) {
			System.out.println("Error reading city.dat: ");
		}
		if(!road.exists())
		{
			try {
				PrintWriter file = new PrintWriter("/Graphs-Cities/road.dat");
				file.close();
				road = new File("/Graphs-Cities/road.dat");
			}catch(Exception ex) {
				System.out.println("Error Creating City.dat: ");
			}
		}
		try {
			RoadScann = new Scanner(road);
		}catch(Exception ex) {
			System.out.println("Error reading city.dat: ");
		}
	
	}
	public Scanner getCityScanner()
	{
		return CityScann;
	}
	public Scanner getRoadScanner() {
		return RoadScann;
	}
	public void resetScanner()
	{	try {
			CityScann = new Scanner(city);
		}catch(Exception ex) {
			System.out.println("Error reading city.dat: ");
		}
		
		try {
			
			RoadScann = new Scanner(road);	
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Error reading road.dat: ");
		}
	}
	
	public void newCity(int Vertice) {
		
		try {
			String CityCode = String.valueOf(Vertice + 1 );
			String Abbreviation, CityName, Population = "", Elevation;
			boolean status , status2 ;
			
			
			do {
		
				System.out.println("Enter the new information for the City (Ex. NY NEW YORK Population Elevation): ");
				Abbreviation = UserInput.next();
				CityName = UserInput.next();
				
				Abbreviation = Abbreviation.toUpperCase();
						
				for(int times = 0 ; times < 2 ; ++times) {
					String variable = UserInput.next();
				
					if(!isInteger(variable))
						CityName += " " + variable;
					else {
						Population = variable;
						times = 1;
					}
				}
				
				CityName = CityName.toUpperCase();
				Elevation = UserInput.next();
				
				status = ExistsAbbre(Abbreviation);
				status2 = ExistsCityName(CityName);
				
				if(status|| status2) 
				{
					if(status) 
					{
						System.out.println("That City Abbreviation already exists");
					}
					if(status2)
					{
							System.out.println("That City Name Already Exists");
							
					}
					status2 = true;
					status = true;		
				}
			}while(status && status2);
		
			int index = 0;
			String WRITE = String.format("%-4s%-6s%-17s%10s%7s", CityCode,Abbreviation,CityName,Population,Elevation);
			
			System.out.println(Vertice + 1 );
			
			String[] Cities = new String[Vertice +1 ];
		
			Scanner Scann = new Scanner(city);
			while(Scann.hasNext())
				{
					String line = Scann.nextLine();
					System.out.println(index);
					System.out.println(line);
					Cities[index] = line ;
					++index; 
				}
			Scann.close();
			Cities[index] = WRITE;
		
		
			PrintWriter file2 = new PrintWriter("Graphs-Cities/city.dat");
			for( index = 0 ; index < Vertice + 1; ++ index)
				{
					file2 .println(Cities[index]);
				}
		
			file2.close();
			city = new File("Graphs-Cities/city.dat");
			
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			System.out.println("Error adding a new city") ;
		}
		
		resetScanner();
	}
	private boolean ExistsCityName(String CityName)
	{
		while(CityScann.hasNext())
		{
			CityScann.next();
			CityScann.next();
			String CityName2 =CityScann.next();
			if(CityName.equals(CityName2))
			{
				resetScanner();
				return true;
			}
			CityScann.nextLine();
		}
		resetScanner();
		return false;
	}
	private boolean ExistsAbbre(String Abbreviation)
	{
		while(CityScann.hasNext())
		{
			CityScann.next();
			String CityAbbre = CityScann.next();
			if(Abbreviation.equals(CityAbbre))
			{
				resetScanner();
				return true;
			}
			CityScann.nextLine();
		}
		resetScanner();
		return false;
	}
	public void addNewRoad(String code1, String code2, String weight, int numberofEdges)
	{
		if(!isConnected(code1, code2)) {
			try {
					int temp = 1 ;
					
					boolean once = true;
					String WRITE =  code1 + "   " + code2 + "     " + weight ;
					String[] edges = new String[numberofEdges + 1];
					int index = 0;
					Scanner Scanner2 = new Scanner(road);
					
					while(RoadScann.hasNext() && Scanner2.hasNext())
					{  
						String Citycode = RoadScann.next();
						int Citycode1 = Integer.parseInt(Citycode);
						int Citycode2 = Integer.parseInt(code1);
						if(Citycode1 == Citycode2 && once)
						{
							edges[index] = WRITE;
							++index;
							once = false;
						}else if(Citycode2 < Citycode1 &&  Citycode2 > temp && once){
							edges[index] = WRITE;
							++index ;
							once = false;
						}
						temp = Citycode1;
						
						/**
						if(code1.equals(Citycode) && once)
							{
								edges[index] = WRITE;
								++index;
								once = false;
							}*/
						
						edges[index] = Scanner2.nextLine();
						++index;
						RoadScann.nextLine();
					}
				if(once) {
					edges[index] = WRITE;
				}
				
				PrintWriter file2 = new PrintWriter("Graphs-Cities/road.dat");
				
				for( index = 0 ; index < numberofEdges + 1; ++ index)
				{
					file2 .println(edges[index]);
				}
				Scanner2.close();
				file2.close();
				road = new File("Graphs-Cities/road.dat");
					
					
				}catch(Exception ex){
					//FIX
					ex.printStackTrace();
					System.out.println("Error adding a new road") ;
				}
			resetScanner();
		}else {
			System.out.println("The two Cities are already Connected You Must Delete the road first to add in a new road");
		}
	}
	
	public void isConnected() 
	{
		System.out.println("Please Enter two City Abbreviations (Ex. LI LV  NOTE: we are referring \"FROM CITY TO CITY\") \nto see if they are connected: ");
		String cityAbbre1 = UserInput.next();
		String cityAbbre2 = UserInput.next();
		String cityCode1 = getCityCode(cityAbbre1);
		String cityCode2 = getCityCode(cityAbbre2);
 
		resetScanner();
		if(isConnected(cityCode1, cityCode2))
		{
			System.out.println("The two Cities " + getCityName(cityAbbre1) + " Abbreviation: " + cityAbbre1 + " and " + getCityName(cityAbbre2) + " Abbreviation: " + cityAbbre2 + " are connected " );
		}else
		{
			System.out.println("The two Cities "  + getCityName(cityAbbre1) + " Abbreviation: " + cityAbbre1 + " and " + getCityName(cityAbbre2) + " Abbreviation: " + cityAbbre2 + " are NOT connected " );
		}
	}
	//use for the is CONNECTED METHOD 
	private boolean isConnected(String code1, String code2) {	
		try {
				
				while(RoadScann.hasNext())
				{
					String CityCode1 = RoadScann.next();
					String CityCode2 = RoadScann.next();
					if(code1.equals(CityCode1) && code2.equals(CityCode2)) {
						resetScanner();
						return true;
					}
					RoadScann.nextLine();
				}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error reading from file road.dat");
		}
		resetScanner();
		return false;
	}
	
	public void removeRoad(String code1, String code2, int numberofEdges)
	{	
		
		if(isConnected(code1, code2)){
			try {
			
					String[] edges = new String[numberofEdges - 1];
					int index = 0 ; 
					Scanner Scanner2 = new Scanner(road);
				
				while(RoadScann.hasNext() && Scanner2.hasNext())
					{
						String CityCode1 = RoadScann.next();
						String CityCode2 = RoadScann.next();
						if(!(code1.equals(CityCode1) && code2.equals(CityCode2))) {
							edges[index] = Scanner2.nextLine();
							++index;
						}else {
							Scanner2.nextLine();
						}
						RoadScann.nextLine();
				}
				
				PrintWriter file2 = new PrintWriter("Graphs-Cities/road.dat");
				for(index = 0; index < numberofEdges -1 ; ++index)
				{
					file2.println(edges[index]);
				}
				Scanner2.close();
				file2.close();
				road = new File("Graphs-Cities/road.dat");

		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Error reading from file road.dat");
		}
		resetScanner();
		}else {
			System.out.println("The road from " +  getCityName( getCityAbbree(code1)) + " and " + getCityName( getCityAbbree(code2)) + " does not Exist");
		}
	}
	
	private boolean isInteger(String s)
	{
		boolean isValidInteger = false;
		try
	      {
	         Integer.parseInt(s);
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	      }
	 
	      return isValidInteger;
	}
	public String getCityName(String CityAbrre)
	{
		String CityName;
		while(CityScann.hasNext())
		{
			CityScann.next();
			String CityAbbree = CityScann.next();
			if(CityAbrre.equals(CityAbbree))
			{
				CityName = CityScann.next();
				String CityName2 = CityScann.next();
				if(!isInteger(CityName2))
					CityName += " " + CityName2;
				resetScanner();
				return CityName;
			}
			CityScann.nextLine();
		}
		return "ERROR";
	}

	public String getCityCode(String CityAbbreviation)
		{		
			String code ;
			
			while(CityScann.hasNext())
			{
				code = CityScann.next();
				String CityAbbre = CityScann.next();
				if(CityAbbreviation.equals(CityAbbre))
				{
					resetScanner();
					return code;
				}
				CityScann.nextLine();
			}
			System.out.print("City with Abrreviation " + CityAbbreviation + "Not Found");
			resetScanner();
			return null;
		}
	public String getCityAbbree(String CityCode)
	{
		String CityAbbre;
		while(CityScann.hasNext())
		{
			String CityCode2 = CityScann.next();
			CityAbbre = CityScann.next();
			if(CityCode.equals(CityCode2))
			{
				resetScanner();
				return CityAbbre;
			}
			CityScann.nextLine();
		}
		resetScanner();
		return null;
		
	}
	public void Citycode() 
	{
		
		System.out.print("Please Enter the City code for the City info (Ex. AN) : ");
		String input = UserInput.nextLine();
		String code = getCityCode(input);
		while(CityScann.hasNext()) {
			String CityCode = CityScann.next();
			
			if(code.equals(CityCode))
			{
				System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "CityCode:","Abbreviation:","City Name:","Population:","Elevation:");
				System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", CityCode,CityScann.next(),CityScann.next(),CityScann.next(),CityScann.next());
				resetScanner();
				return;
			}
			CityScann.nextLine();
		}
		resetScanner();
		System.out.println("\nCity with code: " + input + " Not Found\n");
		
		
	}
}
