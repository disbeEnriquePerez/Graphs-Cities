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
			
			String line = CityScann.next();
			map.addVertex(line);
			CityScann.nextLine();
		
		}
		while(RoadScann.hasNext()) 
		{
			map.addEdge(RoadScann.next(), RoadScann.next(), RoadScann.nextDouble());
			
		}
	}
	
}
