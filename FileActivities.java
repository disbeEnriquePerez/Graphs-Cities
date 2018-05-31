import java.io.*;
import java.util.*;
public class FileActivities {

	File city;
	Scanner CityScann, UserInput;
	
	public FileActivities()
	{
		city = new File("Graphs-Cities/city.dat");
		UserInput = new Scanner(System.in);
		if(!city.exists())
		{
			try {
				PrintWriter file = new PrintWriter("/Graphs-Cities/city.dat");
				file.close();	
				city = new File("city.dat");
			}catch(Exception ex) {
				System.out.println("Error Creating city.dat: ");
			}
		}

		try {
			CityScann = new Scanner(city);
		}catch(Exception ex) {
			System.out.println("Error reading city.dat: ");
		}
	}
	
	public void Citycode() 
	{
		System.out.print("Please Enter the City code for the City info: ");
		String input = UserInput.nextLine();
		
		while(CityScann.hasNext()) {
			String CityCode = CityScann.next();
			
			if(CityCode.equals(input))
			{
				System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", "CityCode:","Abbreviation:","City Name:","Population:","Elevation:");
				System.out.printf("%-15s%-15s%-15s%-15s%-15s\n", CityCode,CityScann.next(),CityScann.next(),CityScann.next(),CityScann.next());
				return;
			}
		}
		
		System.out.println("\nCity with code: " + input + " Not Found\n");
		
		
	}
}
