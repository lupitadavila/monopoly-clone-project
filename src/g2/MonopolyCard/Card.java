package g2.MonopolyCard;
import g2.MonopolyPlayer.Player;
/**
 * the class Card, which is used by Chance
 * @author Daniel Mayorga
 *
 */
public abstract class Card {
	
//instance variable Name
public String Name="";
String explanation="";
//abstract classes that need to be defined in other subclasses
public abstract void cardAction(Player p);

public String getExplanation(){
	return explanation;
}

public void setExplanation(String ex){
	explanation=ex;
}

//Name mutator and accessor
public String getName(){
	return Name;
}

public void setName(String name){
Name=name;	
}

}