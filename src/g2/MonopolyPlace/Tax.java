package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;
//Class subtracts money player has
public class Tax extends Square{
	private int tax;
	public Tax(String Name ,int t){
		super(Name);
		tax=t;
	}
//the action subtracts money from the player in the parameter
public void action(Player p){
	System.out.println("you landed on "+ getName());
	p.setMoney(-tax);
	System.out.println(getName() + " of $" + tax);
}
}