package g2.MonopolyCard;
import g2.MonopolyPlayer.Player;

//TeleportCard doesn't teleport you, it merely moves you the amount of spaces in
// the Location variable
public class TeleportCard extends Card{
	int Location;
	public TeleportCard(String Name, int location){
		this.Name=Name;
		Location=location;
	}
	//calls setLocation, and adds it's value to your current location
	public void cardAction(Player p){
	System.out.println("teleported " + Location + " spaces");
	p.setLocation(Location);
	}

}