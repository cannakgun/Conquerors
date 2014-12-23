//
//import java.util.ArrayList;
//import java.util.Random;
//import java.util.Timer;
//
//public class BonusManager {
//	
//	//properties
//	private ArrayList<Bonus> powerups;
//	private ArrayList<Bonus> badSurprises;
//	private GameManager gManager;
//	int remaining = gManager.getRemainigTime();
//	//methods
//	/* This is the constructor of the BonusManager class.
//	* This will create instances of powerups and bad-surprises and
//	* instantiate gManager and finally call the method 
//* fillBonusList().
//	*/
//	public BonusManager(GameManager gManager){
//            
//            this.gManager = gManager;
//            ArrayList<Bonus> powerups = new ArrayList<Bonus>();
//            ArrayList<Bonus> badSurprises = new ArrayList<Bonus>();
//            
//            EnemiesGone eGone = new EnemiesGone();
//            PointPlus pPlus =  new PointPlus();
//            TimePlus tPlus = new TimePlus();
//            LifeBonus lBonus = new LifeBonus();
//            //BadSuprises
//
//            TimeMinus   tMinus = new TimeMinus();
//            PointMinus  pMinus = new PointMinus();
//            MoreWalls   mWalls = new MoreWalls();
//	
//            fillBonusLists();
//	}
///* This method will randomly create and add bonus objects to 
//* the power-ups and bad-Surprises ArrayLists.
//*/
//	public void fillBonusLists(){
//            ArrayList<Bonus> bonuslistup = new ArrayList<Bonus>();
//            ArrayList<Bonus> bonuslistdown = new ArrayList<Bonus>();
//            
//            Random rand = new Random();
//            bonuslistup.add(0,eGone);
//            bonuslistup.add(1,pPlus);
//            bonuslistup.add(2,tPlus);
//            bonuslistup.add(3,fHero);
//            bonuslistup.add(4,bHero);
//            bonuslistup.add(5,lBonus);
//            
//
//            
//           for(int i = 0; i<6; i++){
//               int  n = rand.nextInt(5);
//               powerups.add(i,bonuslistup.get(n));
//               
//               
//           }
//  
//            bonuslistdown.add(0,sHero);
//            bonuslistdown.add(0,tMinus);
//            bonuslistdown.add(0,pMinus);
//            bonuslistdown.add(0,mWalls);
//            
//             for(int i = 0; i<4; i++){
//               int  n = rand.nextInt(3);
//               badSurprises.add(i,bonuslistdown.get(n));
//           }
//            
//            
//}           
///* This method randomly selects and returns a bonus object from 
//* the powerups and badSurprises ArrayLists.
//*/
//	public Bonus selectBonus(){
//            Random rand = new Random();
//            Bonus bonus = null;
//            int  selection =0;
//            int  bonusSelection=0;
//            int countbad =0;
//            
//            
//            int Level = gManager.getLevel();
//            
//            if(Level == 1){
//                selection = rand.nextInt(1)+1;
//                if(countbad==2){
//                selection =1;
//                }
//                
//                
//                if(selection==1){
//                    bonusSelection =rand.nextInt(5);
//                    bonus = powerups.get(bonusSelection);
//                }
//                if(selection==2){
//                    bonusSelection = rand.nextInt(3);
//                    bonus = badSurprises.get(bonusSelection);
//                    countbad++;
//                }
//            }
//            if(Level == 2){
//                selection = rand.nextInt(1)+1;
//                  if(countbad==3){
//                selection =1;
//                }
//                
//                
//                if(selection==1){
//                    bonusSelection =rand.nextInt(5);
//                     bonus = powerups.get(bonusSelection);
//                    
//                }
//                if(selection==2){
//                    bonusSelection = rand.nextInt(3);
//                     bonus =badSurprises.get(bonusSelection);
//                    countbad++;
//                }
//            }
//            if(Level == 3){
//                 selection = rand.nextInt(1)+1;
//                if(countbad==4){
//                selection =1;
//                }
//                
//                if(selection==1){
//                    bonusSelection =rand.nextInt(5);
//                    bonus = powerups.get(bonusSelection);
//                }
//                if(selection==2){
//                    bonusSelection = rand.nextInt(3);
//                    bonus = badSurprises.get(bonusSelection);
//                    countbad++;
//                }
//            }
//            if(Level == 4){
//                selection = rand.nextInt(1)+1;
//                if(countbad==5){
//                selection =1;
//                }
//                
//                if(selection==1){
//                    bonusSelection =rand.nextInt(5);
//                     bonus = powerups.get(bonusSelection);
//                }
//                if(selection==2){
//                    bonusSelection = rand.nextInt(3);
//                     bonus = badSurprises.get(bonusSelection);
//                    countbad++;
//                }
//            }
//            if(Level == 5){
//               selection = rand.nextInt(1)+1;
//                if(countbad==6){
//                selection =1;
//                }
//                
//                if(selection==1){
//                    bonusSelection =rand.nextInt(5);
//                    bonus = powerups.get(bonusSelection);
//                }
//                if(selection==2){
//                    bonusSelection = rand.nextInt(3);
//                    bonus = badSurprises.get(bonusSelection);
//                    countbad++;
//                }
//            }
//     
//        return bonus;
//            
//}
//
//	
//	/* This method will check the time of the creation of the 
//* bonus on the screen. It informs the GameManager class
//*/
//	public boolean checkBonusTime(){
//            int remainingin = gManager.getRemaining();
//            
//            if(remainingin == remaining - 30){
//                remaining.getRemaining();
//                return true;
//            }
//            return false;
//}
//	
//	
//
//	/* This method will check the time of the destruction of the 
//* bonus on the screen. It informs the GameManager class.
//	*/
//	public boolean checkBonusDestructionTime() throws InterruptedException{
//            int remainingin = gManager.getRemaining();
//                     
//            if(remainingin == remaining - 10){  
//                remaining.getRemaining();
//                return true;
//            }
//            
//            return false;
//            
//        }
//	
//}
