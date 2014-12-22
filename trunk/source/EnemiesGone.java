public class EnemiesGone extends Bonus
{
	private GameManager gManager;
	public EnemiesGone(GameManager gManager)
	{
		this.gManager= gManager;
	}
	 public void applySpeciality()
	 {
		gManager.deleteAllEnemies();
	 }
}
