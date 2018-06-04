import GraphPackage.GraphInterface;
import java.util.*;
public class LoadGraph {
	private FileActivities file;
	private GraphInterface<String> map;
	private Scanner CityScann, RoadScann;
	public LoadGraph(GraphInterface<String> map, FileActivities file)
	{
		this.file = file;
		this.map = map;
		file.resetScanner();
		CityScann = file.getCityScanner();
		RoadScann = file.getRoadScanner();
		load();
		file.resetScanner();
	}
	
	
	public void load() {
		
		while (CityScann.hasNext())
		{
			// we first read from the cities... the vertices must exist first before we add in the roads
			// CityScann is just reading the first string it comes to read
			// so it is just reading the City code 
			String line = CityScann.next();
			map.addVertex(line);
			CityScann.nextLine();
		
		}
		while(RoadScann.hasNext()) 
		{	
			// RoadScann is just reading code1 code2 and the weight of the edge between two cities
			map.addEdge(RoadScann.next(), RoadScann.next(), RoadScann.nextDouble());
			
		}
	}
	
}
