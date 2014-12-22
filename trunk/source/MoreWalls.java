
public class MoreWalls {

	private GameManager gManager;
	public MoreWalls(GameManager gManager)
	{
		this.gManager = gManager;
	}
	public void applySpeciality()
	{
		gManager.addWalls();
	}
}