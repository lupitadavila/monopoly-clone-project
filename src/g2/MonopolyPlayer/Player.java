package g2.MonopolyPlayer;
import java.util.ArrayList;
import g2.MonopolyPlace.Property;
/**
 * Player class-This holds most of the important values for player
 * @author Daniel Mayorga @author Thomas Pilkington
 *
 */
public class Player {
    private String Name;
    private int Money;
    private int location;
    private boolean Jail;
    public Die Dice= new Die();
    public ArrayList<Property> ownership= new ArrayList<Property>();
    private int jailtime;
    //Constructor
    public Player(String name, int money){
	location=0;
	Money=money;
	Name=name; 
    }

    public String getName(){
	return Name;
    }

    public void setMoney(int mon){
	Money+=mon;
    }

    public int getMoney(){
	return Money;
    }

    public int getLocation(){
	return location;
    }
    //setLocation increments your location value
    public void setLocation(int val){
	location+=val;
	if (location>39){
	    //if you loop around the board add 200 dollars
	    Money+=200;
	    location=location%40;
	}
    }

    public void setJail(boolean val){
	Jail=val;
    }
    public boolean getJail(){
	return Jail;
    }
    //Checks if everything is Mortgaged
    public boolean isEverthingMortgaged(){
	boolean over=true;
	for (int i=0; i<ownership.size(); i++){
	    if (!ownership.get(i).getIsMortgaged()){
		over=false;
	    }
	}
	return over;
    }
    //setJailtime() makes you wait in jail for 2 turns if you are in jail
    public void setJailtime(){
	jailtime++;
	if (jailtime>=2){
	    setJail(false);
	    jailtime=0;
	}
    }
}