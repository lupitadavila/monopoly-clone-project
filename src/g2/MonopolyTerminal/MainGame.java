package g2.MonopolyTerminal;
/**
 * MainGame is the class with the main method. You run the game from here
 * 
 *
 */
public class MainGame {
//main method
	public static void main(String[] args){
		//Create Idle and use the turns
		Idle Game=new Idle();
		Game.createUsers();
		Game.turns();
	}
}
