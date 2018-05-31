
public class DecisionMaker {

	FileActivities file;
	CityRoads Roads;
	public DecisionMaker()
	{
		file = new FileActivities();
		Roads = new CityRoads();
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
				break;
		case 'R': 
				break;
		}
	}
}
