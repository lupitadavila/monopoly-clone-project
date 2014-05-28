package g2.MonopolyTerminal;
import g2.MonopolyPlayer.*;
import g2.MonopolyPlace.*;
import java.util.ArrayList;
import java.io.*;
/**
 * The class that performs all of the command line prompts and actions
 * @author Daniel Mayorga @author Thomas Pilkington @author Oscar Del Pilar
 * @version 1st iteration
 */
public class Idle {
    //state
    private ArrayList<Player> players= new ArrayList<Player>();
    private int indexOfPlayer;
    private Board board=new Board();
    private int checker;
	
    /**
     * Method that creates all the users
     */
    public void createUsers(){

	//Opening phrases
	String opening[]={"Welcome to Monopoly","Please insert number of Players (2-4 required)"};

	//Enter the number of players
	System.out.println(opening[0]);

	//Infinite loop until user presses right input
	while(true){
	    System.out.println(opening[1]);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String answer="";
	    try{
		answer = br.readLine();
	    }
	    catch(Exception ex){
		System.out.println("ioexception");
		System.exit(1);
	    }
	    int holder;
	    try{
		holder=(Integer.parseInt(answer));
		checker=holder;
	    }catch(Exception ex){
		holder=0;
	    }
	    if (2==holder||3==holder||4==holder){
		System.out.println("Please input the names of each player (One name per line):");
		for(int i=0;i<holder;i++){
		    String Name="";
		    try{
			Name+=br.readLine();
		    }
		    catch(Exception ex){
			System.exit(1);
		    }
		    players.add(new Player(Name, 5000));
		}
		return ;
	    }
	}
    }
    
    /**
     *menuSelect is the main hub where all the other private methods are called into
     *This is where the "gameplay really happens" 
     * @param p -The player who is in the main hub
     */
    public void menuSelect(Player p){
	while (true){
	    String answer="";
	    System.out.println("Type in command or Help");	
	    //String answer=System.console().readLine();
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    try{
		answer=br.readLine();
	    }
	    catch(Exception ex){
		System.out.println("ioexception");
		System.exit(1);
	    }
	    if (answer.equals("roll")||answer.equals("Roll")){
		roll(p);
		forcedMortgage(p);
		return ;
	    }
	    else if(answer.equals("trade")||answer.equals("Trade")){
		trade(p);
	    }
	    else if(answer.equals("Check")||answer.equals("check")){
		checkPlayer(p);
	    }
	    else if(answer.equals("House")||answer.equals("house")){
		HousingOptions(p);
	    }
	    else if(answer.equals("Leave")||answer.equals("leave")){
		LeaveGame(p);
		p=players.get(indexOfPlayer);
		if (p==null){
		    return ;
		}
	    }
	    else if(answer.equals("Quit")||answer.equals("quit")){
		Quit();
		if (players.size()==0){
		    return ;}
	    }
	    else help();
	}	
    }
		
    /**
     * checkPlayer Prints out the player's status
     * @param p - the player who's status we are checking
     */
    private void checkPlayer(Player p){
	System.out.println(p.getName()+"'s turn");
	String jail="";
	if(p.getJail()){
	    jail+="Yes";
	}
	else{
	    jail+="No";}
	System.out.println("Am I in Jail? "+jail);
	System.out.println("My current location: "+board.places[p.getLocation()].getName());
	System.out.println("Money: "+p.getMoney());
	if (p.ownership.size()==0)
	    System.out.println("You own no properties");
	else displayPropertiesOwned(p);
		
    }
		
    /**
     * roll moves the player through the map, and performs any actions that should occur 
     * when the player moves (Chance, buy, pay rent, go to jail, ect)
     * @param p-Player who is rolling 
     */
    private void roll(Player p){
	//checks if you are in jail, if you are in jail, then you can't move
	if (p.getJail()){
	    System.out.println("You are in Jail, you can not move this turn");
	    p.setJailtime();
	}
	//if you are NOT in jail, then move and perform the actions that occur
	//when you you land on a square on the board
	else{
	    p.Dice.RollDice();
	    System.out.println("You rolled "+p.Dice.getdie1()+" and "+p.Dice.getdie2());
	    p.setLocation(p.Dice.totalDice());
	    board.squareAction(p);
	}
	//go on the board and do the action
    }
    /**
     * trade allows the player to initiate a trade with other players
     * @param p-Player who is initiating trade
     */
    private void trade(Player p){
	//checks if you even have property to trade
	if (p.ownership.size()==0){
	    System.out.println("You do not have property to trade");
	    System.out.println("Returned to main menu");
	    return ;
	}
	//you have property, then just print the other player's property
	String tradeCommands[]={"Trade will begin", "Type in Player's number or Enter \"q\" to Quit: "};
	Player p2;
	for (int i=0; i<2; i++){
	    System.out.println(tradeCommands[i]);
	}
	for (int i=0; i<players.size(); i++){
	    if (indexOfPlayer!=i){
		if (players.get(i)!=null){
		    System.out.println("Player "+(i+1)+" "+players.get(i).getName());
		}
	    }
	}
	//receives players input on the command line
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String answer="";
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	int current;
	try{
	    current=(Integer.parseInt(answer)-1);
	}catch(Exception ex){
	    current=-1;
	}
	//if you pressed q, then you quit
	if (answer.equals("q")){
	    return ;
	}
	//else check if the player you chose is right
	else if(current!=indexOfPlayer && current<players.size() && current>-1 && players.get(current)!=null){
	    p2=players.get(current);
	    if (p2.ownership.size()==0){
		System.out.println("Player you chose did not have property");
		System.out.println("Returned to main menu");
		return ;
	    }
	}
	else {
	    //the user typed in invalid command
	    System.out.println("Invalid Entry: Returned to main menu");
	    return ;
	}
	int you;
	int them;
	//displays initiated player's and selected player's property 
	System.out.println("Your properties are");
	displayPropertiesOwned(p);		
	System.out.println(p2.getName()+"'s properties are:");
	displayPropertiesOwned(p2);
	System.out.println("Enter your Property number");
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	try{
	    you=(Integer.parseInt(answer)-1);
	}catch(Exception ex){
	    you=-1;
	}
	if (!(you>=0 && you<p.ownership.size() && p.ownership.get(you).getHouses()==0 && p.ownership.get(you).getHotels() == 0 )){
	    System.out.println("Invalid Entry: Return to main menu");
	    return ;
	}
	System.out.println("Enter "+p2.getName()+"'s Property");
			
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	try{
	    them=(Integer.parseInt(answer)-1);
	}catch(Exception ex){
	    them=-1;
	}
	if (!(them>=0 && them<p2.ownership.size() && p2.ownership.get(them).getHouses()==0 && p2.ownership.get(them).getHotels() == 0 )){
	    System.out.println("Invalid Entry: Return to main menu");
	    return ;
	}
	//Actual trading commencing
	p2.ownership.get(them).setOwner(p);
	p.ownership.get(you).setOwner(p2);
	p2.ownership.add(p.ownership.get(you));
	p.ownership.add(p2.ownership.get(them));
	p2.ownership.remove(them);
	p.ownership.remove(you);
    }
    /**
     * prints out what user can do in the game
     */
    private void help(){
	//Options are that the player can trade, check status, mortgage, build houses, sell houses at all times
	//Condition, the player can roll if not in jail, then player can't roll and has to end turn
	//if player can roll then he has to roll before ending his turn
	String list[]={"You can do the following actions by typing in the command","Check (to check status/location)","Roll (to move and end turn)",
		       "Trade (To trade with other players)","House (Housing/Morgaging Options)", "Leave (for the player to leave)",
		       "Quit (for the player to quit the game)"};
	for(int i=0; i<list.length; i++){
	    System.out.println(list[i]);
	}
    }
    /**
     * 
     * @param p -player who chose to change 
     */
    private void HousingOptions(Player p){
	// Player can mortgage property, unmortgage property
	// Buy and Sell houses
	// also leave after action is performed or q is pressed
	if(p.ownership.size()==0){
	    System.out.println("You do not have property at the moment");
	    return ;
	}
	String Options[]={"These are your options press the number","1: Buy houses","2: Sell houses","3: Mortgage property","4: UnMortgage property"};
	for(int i=0; i<5;i++){
	    System.out.println(Options[i]);
	}
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String answer="";
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	if (answer.equals("1")){
	    buyHouse(p);
	}
	else if(answer.equals("2")){
	    sellHouse(p);
	}
	else if(answer.equals("3")){
	    MortgageProperty(p);
	}
	else if(answer.equals("4")){
	    UnMortgageProperty(p);
	}
	else{
	    System.out.println("Invalid Entry: Returned to main menu");
	}
    }
		
    //prints out all the properties owned by all the players
    private void displayPropertiesOwned(Player p){
	System.out.println("Properties Owned: ");
	for(int i=0; i<p.ownership.size(); i++){
	    if (players.get(i)!=null){
		System.out.println( (i+1) + " " + p.ownership.get(i).getName());
		System.out.println("These are houses owned: " + p.ownership.get(i).getHouses());
		System.out.println("These are hotels owned: " + p.ownership.get(i).getHotels());
	    }
	}
    }
		
    //displays property and houses/hotels that the player can sell
    private void sellHouse(Player p){
	displayPropertiesOwned(p);
	System.out.println("Choose property you want to sell at: ");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String answer="";
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	int holder;
	try{
	    holder=(Integer.parseInt(answer)-1);
	}
	catch(Exception ex){
	    holder=-1;
	}
	if (holder>=0 && holder<(p.ownership.size())){
	    if (p.ownership.get(holder).getHouses()>0||p.ownership.get(holder).getHotels()==1){
		p.ownership.get(holder).sellHouse();	
	    }
	    else {
		System.out.println("No Houses to sell");
		return ;
	    }
	}
	else 
	    System.out.println("Invalid Entry: Returned to main menu");
    }
    //displays all property player can buy
    // allows you to buy house if you have a monopoly
    // otherwise it will not allow you to build houses
    private void buyHouse(Player p){
	displayPropertiesOwned(p);
	System.out.println("Choose property you want to build at: ");
	String answer="";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	int holder=-1;
	try{
	    holder=(Integer.parseInt(answer)-1);
	}
	catch(Exception ex){
	    holder=-1;
	}
	if (holder>=0 && holder<(p.ownership.size())){
	    if (p.ownership.get(holder).getHouses()>=0 
		&& p.ownership.get(holder).getHotels()==0){
		p.ownership.get(holder).buildHouse();	
	    }
	    else {
		System.out.println("You reached the max");
		return ;
	    }
	}
	else 
	    System.out.println("Invalid Entry: Returned to main menu");
    }
    //displays all the property that the player owns
    //if the property is mortgage-able then you are allowed to mortgage it
    // calls property's mortgaging method
    private void MortgageProperty(Player p){
	displayPropertiesOwned(p);
	System.out.println("Choose property you want to Mortgage: ");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String answer="";
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	int holder=-1;
	try{
	    holder=(Integer.parseInt(answer)-1);
	}
	catch(Exception ex){
	    holder=-1;
	}
	if (holder>=0 && holder<(p.ownership.size())){
	    if (p.ownership.get(holder).getHouses()<3 && p.ownership.get(holder).getHouses()>=0 
		&& p.ownership.get(holder).getHotels()==0){
		p.ownership.get(holder).Mortgage();	
	    }
	    else {
		System.out.println("Please sell your houses before Mortgaging");
		return ;
	    }
	}
	else 
	    System.out.println("Invalid Entry: Returned to main menu");
    }
    //allows you to Unmortgage property if it is mortgaged
    private void UnMortgageProperty(Player p){
	displayPropertiesOwned(p);
	System.out.println("Choose property you want to Unmortgage: ");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String answer="";
	try{
	    answer=br.readLine();
	}
	catch(Exception ex){
	    System.exit(1);
	}
	int holder=-1;
	try{
	    holder=(Integer.parseInt(answer)-1);
	}
	catch(Exception ex){
	    holder=-1;
	}
	if (holder>=0 && holder<(p.ownership.size())){
	    p.ownership.get(holder).unMortgage();	
	}
	else 
	    System.out.println("Invalid Entry: Returned to main menu");
    }
    //quits the game gracefully
    private void Quit(){
	String QuitCommands[]={"Are you sure you want to quit? (y/n)", "Goodbye and Thank you for playing!", 
			       "You are now back to the main menu"};
	while(true){
	    System.out.println(QuitCommands[0]);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String answer="";
	    try{
		answer=br.readLine();
	    }
	    catch(Exception ex){
		System.exit(1);
	    }
	    //if user enters "y" then the game ends
	    if (answer.equals("y")){
		players.clear();
		return ;
	    }
	    if (answer.equals("n")){
		System.out.println(QuitCommands[2]);
		return ;
	    }
	}
    }
    //allows player to leave and give up all their property
    //board receives the property back
    //the property can now be purchased by any player still playing
    private void LeaveGame(Player p){
	String out="Goodbye "+p.getName();
	System.out.println(out);
	checker--;
	//return houses to the board
	for (int i=0; i<p.ownership.size(); i++){
	    p.ownership.get(i).setNomal();
	}
	p.ownership.clear();
	players.set(indexOfPlayer,null);
    }
    //forces you to morgage property if you are in debt
    private void forcedMortgage(Player p){
	while (p.getMoney()<0){
	    if(p.isEverthingMortgaged()){
		LeaveGame(p);
		return ;
	    }
	    else{
		int breakit=0;
		int i=0;
		while(breakit==0){
		    if (p.ownership.get(i).getIsMortgaged()==false){
			p.ownership.get(i).Mortgage();
			breakit++;
		    }
		    i++;
		}
	    }
	}
    }
		
    //sets the turn cap, uses menuSelect, establishes turns in game
    //main class should call this method
    public void turns(){
	int Max=0;
	String winner="";
	//Turns
	for (int i=0; i<30; i++){
	    for(int j=0; j<players.size() && checker>1; j++){
		if (players.get(j)!=null){
		    indexOfPlayer=j;
		    System.out.println(players.get(j).getName()+"'s Turn");
		    menuSelect(players.get(j));
		}
	    }
	}
	//Determine winner
	for (int j=0;j<players.size();j++){
	    if (players.get(j)!=null){
		if(players.get(j).getMoney()>Max){
		    Max=players.get(j).getMoney();
		    winner=players.get(j).getName();
		}
	    }	
	}
	if (winner.equals("")){
	    System.out.println("Nobody Won");
	}
	else System.out.println("The Winner is "+winner);
    }
}
