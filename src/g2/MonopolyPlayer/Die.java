package g2.MonopolyPlayer;

import java.lang.Math;

public class Die{

private int die1,die2;//the two dice values


//The getter functions for the dice

public int getdie1(){return die1;}
public int getdie2(){return die2;}



//Checks if the dice are equal in value, returns boolean value if true

public boolean isDoubles(){ 
if (die1==die2){
	return true;
	}
else return false;
}//Closes isDoubles


//Set the values for the dice

public void RollDice(){
die1=(int)((Math.random()*6)+1);
die2=(int)((Math.random()*6)+1);
}


//totalDice() returns the dice roll number

public int totalDice(){
return(die1+die2);
}



}