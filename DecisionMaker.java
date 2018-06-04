import GraphPackage.DirectedGraph;
import GraphPackage.GraphInterface;

public class DecisionMaker {

	private FileActivities file;
	private CityRoads Roads;
	private LoadGraph load;
	private GraphInterface<String> map;
	
	public DecisionMaker(GraphInterface<String> map)
	{
		// WE WANT THE MAP SO THAT WAY WE CAN MOVE IT AROUND TO OTHER FILES THAT NEED IT
		// WE HAVE THE CITYROADS THAT IS IN CHARGE OF MODIFYING ROADS AND FIGURING OUT THE MINIMUM ROUTE ETC.
		// LOAD GRAPH IS USED SO THAT WAY EVERY TIME WE RUN THE PROGRAM WE LOAD BACK INTO MEMORY ALL THE VERTICIES AND ROADS
		this.map = map;
		// AT SOME POINT EVERYTHING REVOVLES AROUND THE FILES SO WE USE A CLASS SPECIFICALLY FOR FILES
		file = new FileActivities();
		Roads = new CityRoads(map, file);
		load = new LoadGraph(map, file);
	}
	public void Decision(char Letter)
	{
		// THIS IS OUR DECISION MAKER CALL THE FUNCTIONS BASED OFF WHAT THE USER ENTERED
		switch(Letter) {
		
		case 'Q':
			// SIMPLE FUNCTION THAT PRINTS OUT THE CITIES INFO
				file.Citycode();
				break;
		case 'Z':
				// WE NEED TO ADD IN A NEW CITY BUT WE NEED TO KNOW HOW MANY VERTICES WE CURRRENTLY HAVE SO WE CAN MODIFY IT
				file.newCity(map.getNumberOfVertices());
				this.map = new DirectedGraph<>();
				this.load.load();
				break;
		case 'S':
			    // in order for us to figure out if two cities are connected WE NEED THE FILES 
				file.isConnected();
				break;
		case 'D':
				// ROADS CLASS HELPS US FIND THE MINIMUM COST ROUTE
				Roads.getMinimumCostRoute();
				break;
		case 'T':
				// ROADS CLASS HELPS FIND US THE MINIMUM ROUTE
				Roads.minimum_route();
				break;
		case 'I':
				// in order to add a new road we use the add road method since that class 
				// already has the file variable we can modify the file in there
			    Roads.addRoad();
			    // create a new directedGraph<>() then reload the DirectedGraph<> anew
			    this.map = new DirectedGraph<>();
			    this.load.load();
				break;
		case 'R': 
				// to remove a road we use the roads class to remove the roads 
				// and use the file variable in there to modify the roads 
				Roads.removeRoad();
				this.map = new DirectedGraph<>();
				this.load.load();
				break;
		}
	}
}
