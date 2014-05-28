package g2.MonopolyCard;
import g2.MonopolyPlayer.Player;
import java.lang.Math;
/**
 * Class Card Collection, it's a collection of cards that chance calls upon
 * @author Daniel Mayorga
 *
 */
public class CardCollection {
//private Card Collection[];
private Card Collection[]=new Card[18];
//Setting values, in arrays so we can input it later
public CardCollection(){

String titles[]={"Beauty Contest","Speeding Ticket", "Good Day", "Lucky Day","Okay Day","Terrible Day",
		"Stock Market Crashed","Awesome Day","Lottery","You ","You ","You ",
		"You ","You ","You ","You ","You ","You "};

int array[]={200,-50,100,300,50,-100,-200,400,1000,30,20,25,10,5,6,7,28,19};

for(int i=0; i<9; i++){
	Collection[i]= new MoneyChangeCard(titles[i], array[i]);
}

for(int i=9; i<18; i++){
	Collection[i]= new TeleportCard(titles[i], array[i]);
}

}
//randomly picks a card
public Card chooseCard(){
	int ran = (int) (Math.random() * 18);
	return Collection[ran];
	
}
//cardAction inputs player, and does the wanted action of the card
public void cardAction(Player p){
	Card thiscard=chooseCard();
	System.out.print(thiscard.getName());
	thiscard.cardAction(p);
}
}