package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;
import g2.MonopolyCard.*;

public class Chance extends Square{
	public Chance(){
		super("Chance");
	}
//action calls upon CardCollection and performs cardAction on player
    public void action(Player p){
    	System.out.println("you landed on " + getName());
    	CardCollection current=new CardCollection();
    	current.cardAction(p);
    }
}