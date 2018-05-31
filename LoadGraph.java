import GraphPackage.GraphInterface;
import java.util.*;
public class LoadGraph {
	FileActivities file;
	GraphInterface<String> map;
	Scanner CityScann;
	public LoadGraph(GraphInterface<String> map, FileActivities file)
	{
		this.file = file;
		this.map = map;
		file.resetScanner();
		CityScann = file.getScanner();
	}
	
}
