import GraphPackage.DirectedGraph;
import GraphPackage.GraphInterface;

public class DecisionMaker {

	private FileActivities file;
	private CityRoads Roads;
	private LoadGraph load;
	private GraphInterface<String> map;
	
	public DecisionMaker(GraphInterface<String> map)
	{
		this.map = map;
		file = new FileActivities();
		Roads = new CityRoads(map, file);
		load = new LoadGraph(map, file);
	}
	public void Decision(char Letter)
	{
		switch(Letter) {
		
		case 'Q':
				file.Citycode();
				break;
		case 'Z':
				file.newCity(map.getNumberOfVertices());
				break;
		case 'S':
				file.isConnected();
				break;
		case 'D':
				Roads.getMinimumCostRoute();
				break;
		case 'T':
				Roads.minimum_route();
				break;
		case 'I':
			    Roads.addRoad();
			    this.map = new DirectedGraph<>();
			    this.load.load();
				break;
		case 'R': 
				
				Roads.removeRoad();
				this.map = new DirectedGraph<>();
				this.load.load();
				break;
		}
	}
}
