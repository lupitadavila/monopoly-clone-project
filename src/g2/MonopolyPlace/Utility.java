package g2.MonopolyPlace;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import g2.MonopolyPlayer.Player;

/**
 * Utility is a subclass of Property
 * @author Thomas Pilkington
 *
 */

public class Utility extends Property{

	/**
	 * Constructor
	 * @param name -Name of Utility
	 * @param r -Rent
	 * @param v	-The value of the Utility
	 * @param priceOfHouse -The price for a house
	 */
	
	public Utility(String name, int r, int v, int priceOfHouse){
		super(name, r, v, priceOfHouse);
	}
	
/**
 * Action method that 
 * @param p- Player that will perform action
 */
	  public void action(Player p){
		  	System.out.println("you landed on " + getName());
		  	String answer="";
		  	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  	//check if property is not owned
			if(getOwner()==null){
			    while(true){
			    	//prompts user to purchase the property
			    	System.out.println("do you wish to purchase this property? enter y or n");
			    	try{
			    		answer=br.readLine();
			    	}
			    	catch(Exception ex){
			    		System.exit(1);
			    	}
			    	//If user presses "y"
			    	if(answer.equals("y")){
			    		if(p.getMoney() < getValue()){
			    			System.out.println("not enough money");
			    			return ;
			    		}
			    	else{
					setOwner(p);
					p.setMoney(-getValue());
					System.out.println("purchased property");
					p.ownership.add(this);
					return ;
				    }
				}
					//if user presses "n"
					else if (answer.equals("n")){
					// leave
						return ;
					}
				//If user presses anything other than y and n
					else {
						System.out.println("Invalid Entry");
					}
			    }
			}
			//if the house is owned pay rent
			else{
				
			    if(!getIsMortgaged()){
				p.setMoney(-getRent() * p.Dice.totalDice());
				getOwner().setMoney(getRent() * p.Dice.totalDice());
					if (p!=getOwner()){
						System.out.println(p.getName()+" paid $" + getRent()*p.Dice.totalDice() + " to " + getOwner().getName());
					}

			    }
			}
	  }
}