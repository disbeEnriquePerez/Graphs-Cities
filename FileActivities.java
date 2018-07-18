import java.io.*;
import java.util.*;
public class FileActivities {

	private File city;
	private File road;
	private Scanner CityScann, RoadScann, UserInput;
	
	
	
	/*
	 * NOTE: WHEN USING THE ADD ROAD OR ADD CITY THE MAIN IDEA IS THAT WE ARE USING THE AMOUNT OF EDGES/VERTICES
	 * THEN ADDING ONE TO IT... WE THEN CREATE AN ARRAY OF STRINGS AND READ FROM THAT FILE FROM THE STRINGS 
	 * WE OBTAIN FROM READING THE FILE WE FIGURE OUT WHERE TO PUT IT THE INSERT THAT NEWLY MADE STRING INTO THE ARRAY 
	 * OF STRING WHICH EACH LINE IS A LINE IN THE FILE.
	 * */
	
	public FileActivities()
	{
		city = new File("Graphs-Cities/city.dat");
		road = new File("Graphs-Cities/road.dat");
		UserInput = new Scanner(System.in);
		
		// THIS IS TO FIGURE OUT IF THE CITY.DAT EXISTS OR NOT 
		// IF IT DOESNT TRY TO MAKE A FILE THEN CLOSE IT
		// THEN HAVE CITY POINT TO THAT NEWLY CREATED FILE
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
		// TO INITALLY CREATE A SCANNER TO THE CITY FILE
		try {
			CityScann = new Scanner(city);
		}catch(Exception ex) {
			System.out.println("Error reading city.dat: ");
		}
		
		// THIS IS TO FIGURE OUT IF ROADS.DAT EXISTS OR NOT 
		// IF IT DOESNT TRY TO MAKE A FILE THEN CLOSE IT
		// THEN HAVE ROAD POINT TO THAT NEWLY CREATED FILE
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
		//TO INTIALLY CREATE A SCANNER TO THE ROAD FILE
		try {
			RoadScann = new Scanner(road);
		}catch(Exception ex) {
			System.out.println("Error reading city.dat: ");
		}
	
	}
	
	// TO GET THE CITY SCANNER
	public Scanner getCityScanner()
	{
		return CityScann;
	}
	
	// TO GET THE ROAD SCANNER
	public Scanner getRoadScanner() {
		return RoadScann;
	}
	
	//WE USE THE FILES SO MUCH THAT WE NEED TO RESET THE LINE READER AND SET IT BACK TO THE BEGINNING 
	// SO AT THE ENDING OF ABOUT EVERY FUNCTION WE RESET THE SCANNERS
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
	
	// WHEN TRYING TO ADD IN A NEW CITY 
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
				
				// SOME TIMES WHEN WE ARE CREATING A CITY TO ADD IN 
				// WE GET A CITY WITH A SPACE IN BETWEEN THE TWO WORDS
				// Ex. NEW YORK THIS FOR LOOP WILL HELP US FIGURE THIS OUT
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
	// WHEN ADDING A CITY WE NEED TO KNOW IF THAT CITY ALREADY EXISTS OR NOT 
	// THIS FUNCTION WILL FIGURE OUT IF THAT NEW CITY WE JUST ADDED ALREADY EXISTS OR NOT
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
	// WHEN ADDING A CITY WE NEED TO KNOW IF THAT CITY ALREAD EXISTS OR NOT
	// THIS FUNCTION WILL FIGURE OUT IF THAT NEW CITY WE JUST ADDED ALREADY EXISTS OR NOT
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
	
	// THIS FUNCTION WILL MODIFY THE ROAD FILE AND FIGURE OUT WHERE TO PUT THE NEW STRING INTO 
	// THE EDGES ARRAY WHICH EACH INDEX CONTAINS A LINE FROM THE ROADS FILE
	// ONCE WE FIGURE OUT WHERE TO PUT IT WE PLACE IT IN AND CONTINUE TO READ FROM THE FILE
	// ONCE WE ARE DONE WE CREATE A PRINTWRITER TO READ FROM THE ARRAY AND WRITE INTO THE NEW FILE
	// THEN WE HAVE ROADS POINT TO THAT NEW FILE
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
				
				System.out.println("You have inserted a road from " +   getCityName(getCityAbbree(code1)) + " to " + getCityName(getCityAbbree(code2)) + " with a distance of " + weight + "\n");
					
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
		//we use this function when reading in from the user
		// sometimes we get a city name with a space in it 
		// EX. NEW YORK.... NEW is read nicely but YORK?
		// how do we know if its a string or an integer 
		// this will help us
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
	public void CityNames()
	{
		String output = "";
		while(CityScann.hasNext())
		{
			CityScann.next();
			output += CityScann.next() + "  " + CityScann.next() + "\n";
			CityScann.nextLine();
		}
		System.out.println(output);
		resetScanner();
		
	}
	// this function helps us figure out what the city name is by entering the city abbreviation 
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
	// this function helps us get the city code by enter the city abbreviation
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
	// this function helps us figure out the city abbreviation by entering the city code
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
		String Abbreviation, City_Name, Population = "", Elevation;
		
		System.out.print("Please Enter the City code for the City info (Ex. AN) : ");
		String input = UserInput.nextLine();
		String code = getCityCode(input);
		while(CityScann.hasNext()) {
			String CityCode = CityScann.next();
			
			if(code.equals(CityCode))
			{
				Abbreviation = CityScann.next();
				City_Name = CityScann.next();
				
				for(int times = 0 ; times < 2 ; ++times) {
					String variable = CityScann.next();
				
					if(!isInteger(variable))
						City_Name += " " + variable;
					else {
						Population = variable;
						times = 1;
					}
				}
				
				Elevation = CityScann.next();
				
				System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "CityCode:","Abbreviation:","City Name:","Population:","Elevation:");
				System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", CityCode,Abbreviation,City_Name,Population,Elevation);
				resetScanner();
				return;
			}
			CityScann.nextLine();
		}
		resetScanner();
		System.out.println("\nCity with code: " + input + " Not Found\n");
		
		
	}
}
