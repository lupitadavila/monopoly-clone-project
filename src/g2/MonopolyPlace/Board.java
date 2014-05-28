package g2.MonopolyPlace;
import g2.MonopolyPlayer.Player;

public class Board {
		
    // New array created with 40 spaces for for the board

    public Square places[]= new Square[40];

    // Sets on the board created to be able to use in constructors below

    Set purple,lightBlue, pink, orange, red, yellow, green, blue, railroad, utilities; 

    // Initializing the places on the board
    public Board(){
	places[0] = new NoActionSquare("Go");
	places[1] = new Property("Mediterranean Avenue", 2, 60, 50);
	places[2] = new Chance();
	places[3] = new Property("Baltic Avenue", 4, 60, 50);
	places[4] = new Tax("Income Tax", 200);
	places[5] = new Property("Reading Railroad", 25, 200, 100);
	places[6] = new Property("Oriental Avenue", 6, 100,50);
	places[7] = new Chance();
	places[8] = new Property("Vermont Avenue", 6, 120, 50);
	places[9] = new Property("Connecticut Avenue", 8, 120, 50);
	places[10] = new NoActionSquare("In Jail/Just Visiting");
	places[11] = new Property("St. Charles Place", 10, 140, 100);
	places[12] = new Utility("Electric Company", 4, 150, 100);
	places[13] = new Property("States Avenue", 10, 140, 100);
	places[14] = new Property("Virginia Avenue", 12, 160, 100);
	places[15] = new Property("Pennsylvania Railroad", 25, 200, 100);
	places[16] = new Property("St. James Place", 14, 180, 100);
	places[17] = new Chance();
	places[18] = new Property("Tennessee Avenue", 14, 180, 100);
	places[19] = new Property("New York Avenue", 16, 200, 100);
	places[20] = new NoActionSquare("Free Parking");
	places[21] = new Property("Kentucky Avenue", 18, 220, 150);
	places[22] = new Chance();
	places[23] = new Property("Indiana Avenue", 18, 220, 150);
	places[24] = new Property("Illinois Avenue", 20, 240, 150);
	places[25] = new Property("B&O Railroad", 25, 200, 100);
	places[26] = new Property("Atlantic Avenue", 22, 260, 150);
	places[27] = new Property("Ventnor Avenue", 22, 260, 150);
	places[28] = new Utility("Water Works", 4, 150, 100);
	places[29] = new Property("Marvin Gardens", 24, 280, 150);
	places[30] = new GoToJail();
	places[31] = new Property("Pacific Avenue", 26, 300, 200);
	places[32] = new Property("North Carolina Avenue", 26, 300, 200);
	places[33] = new Chance();
	places[34] = new Property("Pennsylvania Avenue", 28, 320, 200);
	places[35] = new Property("Short Line", 25, 200, 100);
	places[36] = new Chance();
	places[37] = new Property("Park Place", 35, 350, 200);
	places[38] = new Tax("Luxury Tax", 75);
	places[39] = new Property("Boardwalk", 50, 400, 200);

	// Sets on the board are initialized

	purple = 
	    new Set((Property)places[1],(Property)places[3]);
	lightBlue =
	    new Set((Property)places[6], (Property)places[8], (Property)places[9]);
	pink = 
	    new Set((Property)places[11], (Property)places[13], (Property)places[14]);
	orange =
	    new Set((Property)places[16], (Property)places[18], (Property)places[19]);
	red = 
	    new Set((Property)places[21], (Property)places[23], (Property)places[24]);
	yellow =
	    new Set((Property)places[26], (Property)places[27], (Property)places[29]);
	green = 
	    new Set((Property)places[31], (Property)places[32], (Property)places[34]);
	blue = 
	    new Set((Property)places[37], (Property)places[39]);
	railroad =
	    new Set((Property)places[5], (Property)places[15], (Property)places[25], (Property)places[35]);
	utilities =
	    new Set((Property)places[12], (Property)places[28]);
	
	((Property)places[1]).Monopolized(purple); 
	((Property) places[3]).Monopolized(purple); 
	((Property) places[5] ).Monopolized(railroad); 
	((Property) places[6] ).Monopolized(lightBlue); 
	((Property) places[8] ).Monopolized(lightBlue); 
	((Property) places[9] ).Monopolized(lightBlue); 
	((Property) places[11] ).Monopolized(pink); 
	((Utility) places[12] ).Monopolized(utilities); 
	((Property) places[13] ).Monopolized(pink); 
	((Property) places[14] ).Monopolized(pink); 
	((Property) places[15] ).Monopolized(railroad); 
	((Property) places[16] ).Monopolized(orange); 
	((Property) places[18] ).Monopolized(orange); 
	((Property) places[19] ).Monopolized(orange); 
	((Property) places[21] ).Monopolized(red); 
	((Property) places[23] ).Monopolized(red); 
	((Property) places[24]).Monopolized(red); 
	((Property) places[25] ).Monopolized(railroad); 
	((Property) places[26] ).Monopolized(yellow); 
	((Property) places[27]) .Monopolized(yellow); 
	((Utility) places[28]).Monopolized(utilities); 
	((Property) places[29] ).Monopolized(yellow); 
	((Property) places[31] ).Monopolized(green); 
	((Property) places[32]).Monopolized(green); 
	((Property) places[34]).Monopolized(green); 
	((Property) places[35]).Monopolized(railroad); 
	((Property) places[37]).Monopolized(blue); 
	((Property) places[39]).Monopolized(blue);
    }
    //perform the action of the Square subclass you landed on
    public void squareAction(Player p){
    	places[p.getLocation()].action(p);
    }

}