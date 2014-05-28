package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;
/**
 * Set establishes Monopolies
 *  takes in property and links them together as 
 * a region
 */
public class Set{
    private Property p1, p2, p3, p4;

    public Set(Property p1, Property p2){
	this.p1=p1;
	this.p2=p2;
	p3=null;
	p4=null;
    }
    
    public Set(Property p1, Property p2, Property p3){
	this.p1=p1;
	this.p2=p2;
	this.p3=p3;
	p4=null;
    }

    public Set(Property p1, Property p2, Property p3, Property p4){
	this.p1=p1;
	this.p2=p2;
	this.p3=p3;
	this.p4=p4;
    }

    public boolean ownedBy(Player p){
	if(p4!=null){ //railroad
	    if(p1.getOwner() == p && p2.getOwner() == p && p3.getOwner()==p && p4.getOwner() == p){
		return true;
	    }
	}
	else if(p4==null && p3!=null){//set of 3
	    if(p1.getOwner() == p && p2.getOwner() == p && p3.getOwner() == p){
		return true;
	    }
	}
	else{//set of 2
	    if(p1.getOwner() == p && p2.getOwner() == p){
		return true;
	    }
	}
	return false;
    }
}