public class IncreaseLife extends Bonus{
	private GameManager gManager;
	public IncreaseLife(GameManager gManager)
	{
		this.gManager =gManager;
	}
	public void applySpeciality()
	{
		gManager.lifePlus();
	}

}