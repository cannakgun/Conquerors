import java.util.ArrayList;

public class BonusManager {
	
	//properties
	private ArrayList<Bonus> powerups;
	private ArrayList<Bonus> badSurprises;
	private GameManager gManager;
	
	//methods
	public BonusManager(GameManager gManager)
	{
		this.gManager = gManager;
		powerups = new ArrayList<Bonus>();
		badSurprises = new ArrayList<Bonus>();
		fillBonusLists();
	}
	public void fillBonusLists()
	{
		//randomly create and add bonus objects to the powerups and badSurprises ArrayLists. 
	}
	public Bonus selectBonus()
	{
		//randomly select and return a bonus object from the powerups and badSurprises ArrayLists.
		return null;
		
	}
	public boolean checkBonusTime()
	{
		return false;
		
	}
	public boolean checkBonusDestructionTime()
	{
		return false;
		
	}
	

}
