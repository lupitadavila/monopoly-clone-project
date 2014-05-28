package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;

public class NoActionSquare extends Square{
    public NoActionSquare(String Name){
	super(Name);
    }
    //no action tells you what square you are on
    //but otherwise does nothing
    public void action(Player p){
    	System.out.println("you landed on "+ getName());
    }
}