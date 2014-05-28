package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;

public abstract class Square{
	private String name;
	//constructor, gets and sets the name of the square
	public Square(String name){
		this.name=name;
	}
	//no arg constructor
	public Square(){
		name="";
	}
	
	public String getName(){
		return name;
	}
	//action needs to be implemented in subclasses of Square
	//In the game, action is invoked one player when player lands on the square 
	public abstract void action(Player p);
}