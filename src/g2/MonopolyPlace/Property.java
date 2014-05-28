package g2.MonopolyPlace;
import java.io.*;

import g2.MonopolyPlayer.Player;

public class Property extends Square{
    private Set s;
    private Player owner;
    private final int rent;
    private final int value;
    private int houses;
    private final int priceOfHouse;
    private int hotels;
    private boolean isMortgaged;
//constructor 
    public Property(String name, int r, int v, int priceOfHouse){
	super(name);
    rent=r;
	value=v;
	houses=0;
	hotels=0;
	this.priceOfHouse=priceOfHouse;
	owner=null;
	setIsMortgaged(false);
    }
    //Modifies set within the property
    public void Monopolized(Set s){
    	this.s = s;
    }
//clears property of ownership, houses, and mortgaging,and hotels
    public void setNomal(){
    	houses=0;hotels=0;
    	owner=null;
    	setIsMortgaged(false);
    }
   //gets rent
    public int getRent(){
    	if(hotels==0 && houses == 0){
    		if(s.ownedBy(owner)){
    			return rent * 2;
    		}else{
    			return rent + houses*rent;
    		}
    	}
    	else if(hotels==0)
    		return rent + houses*rent;
    	else 
    		return rent * 6;
	    }

    public int getValue(){
	return value;
    }

    public Player getPlayer(){
	return owner;
    }

    public void setOwner(Player p){
	owner=p;
    }
    
    public Player getOwner(){
    	return owner;
    }
    
    public int getHouses(){
    	return houses;
    }

    public int getHotels(){
    	return hotels;
    }
    
    public Set getSet(){
    	return s;
    }
    //creates a house or hotel on your property
    //subtracts money from player after building the house
    public void buildHouse(){
	if(s.ownedBy(owner) == false){
	    System.out.println("you do not own the set");
	}
	else{
	    if(owner.getMoney() < priceOfHouse){
		System.out.println("Not enough money");
	    }
	    else{
		if(houses == 4){
		    houses=0;
		    hotels=1;
		    owner.setMoney(-priceOfHouse);
		}
		else{
		    owner.setMoney(-priceOfHouse);
		    houses++;
		}
		System.out.println("Built house in "+getName());
	    }
	}
    }
    //removes house or hotel on your property
    //adds money to player after selling
	public void sellHouse(){
	    if(hotels == 1){
		houses=4;
		hotels=0;
		owner.setMoney(priceOfHouse/2);
	    }
	    else{
		houses--;
		owner.setMoney(priceOfHouse/2);
	    }
	}
    //Mortgages your property and gives your money
    public void Mortgage(){
	if(getIsMortgaged()){
	    System.out.println("property already mortgaged");
	}
	else{
	    setIsMortgaged(true);
	    owner.setMoney(value/2);
	}
    }
    //Unmortgages your property and takes your money
    public void unMortgage(){
	if(!getIsMortgaged()){
	    System.out.println("property is not mortgaged");
	}
	else{
	    setIsMortgaged(false);
	    owner.setMoney((int)(1.1*-(value/2)));
	}
    }
    //Lets you buy the property if it isn't owned, otherwise pay rent unless it is mortgaged
    public void action(Player p){
	System.out.println("you landed on "+getName());
	String answer="";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	if(owner==null){ //property is not owned
	    while(true){
		System.out.println("do you wish to purchase this property? enter y or n");
		try{
			answer=br.readLine();
		}
		catch(Exception ex){
			System.exit(1);
		}
		if(answer.equals("y")){
		    if(p.getMoney() < value){
			System.out.println("not enough money");
			return ;
		    }
		    else{
			owner=p;
			p.setMoney(-value);
			System.out.println("purchased property");
			p.ownership.add(this);
			return ;
		    }
		}
		else if(answer.equals("n")){
			return ;
		}
		else{
		   System.out.println("Invalid Entry");
		}
	    }
	}
	else{ //owned
	    if(!getIsMortgaged()){
		p.setMoney(-getRent());
		owner.setMoney(getRent());
		if(p!=owner)
			System.out.println(p.getName() + " paid $" + getRent() + " to " + owner.getName());
	    }
	}
    }

	public boolean getIsMortgaged() {
		return isMortgaged;
	}

	public void setIsMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}
}