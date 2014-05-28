package g2.MonopolyCard;

import g2.MonopolyPlayer.Player;
/**
 * The class MoneyChangeCard, uses the Player and modifies his money.
 * @author Daniel Mayorga
 *
 */
public class MoneyChangeCard extends Card{
	//Instance variables 
	int Money;
	/**
	 * Constructor 
	 * @param Name -the name of the Card
	 * @param Money -the amount of money you want to change
	 */
	public MoneyChangeCard(String Name,int Money){
		this.Name=Name;
		this.Money=Money;
	}
	
	/**
	 * Redifining The Abstract methods
	 */
	//the card changes player's money
	public void cardAction(Player p){
		System.out.println(" " + p.getName() + " gets $" + Money);
		p.setMoney(Money);
	}
}