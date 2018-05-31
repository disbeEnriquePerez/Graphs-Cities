import GraphPackage.DirectedGraph;
import GraphPackage.GraphInterface;

public class DecisionMaker {

	FileActivities file;
	CityRoads Roads;
	LoadGraph load;
	
	public DecisionMaker(GraphInterface<String> map)
	{
		file = new FileActivities();
		Roads = new CityRoads();
		load = new LoadGraph(map, file);
	}
	public void Decision(char Letter)
	{
		switch(Letter) {
		
		case 'Q':
				file.Citycode();
				break;
		case 'D':
				break;
		case 'I':
			    Roads.addRoad();
				break;
		case 'R': 
				break;
		}
	}
}
