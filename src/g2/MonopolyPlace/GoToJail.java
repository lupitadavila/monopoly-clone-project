package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;

public class GoToJail extends Square{
    public GoToJail(){
	super("Go To Jail");
    }
    //action sends you to jail, and modifies state of Player
    //setting it to true
    public void action(Player p){
	System.out.println("You landed on Go To Jail");
	System.out.println("You are now in Jail");
	p.setLocation(-20);
	p.setJail(true);
	//go to jail
    }
}