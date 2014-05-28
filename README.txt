README.txt
Monopoly Game
Group ID: G02
Authors: Tom Pilkington, Daniel Mayorga, Oscar De Pilar, Lupita Davila

Instructions:

  I. Building & Executing the system:
  	
	Operating System: Linux
	Language: Java

	== Libraries ==

	- java.util.ArrayList
	- java.io*
	- java.lang.Math

	== Compile & Run ==
	! Do this on terminal because this is a terminal game

	1. Go inside "g2Program" directory
	2. Compile: javac -d classes/ src/g2/Monopoly*/*.java
	3. Go inside "classes" directory 
	4. Run: java g2/MonopolyTerminal/MainGame


II. How to play the game:

	- Goal: Make the most money or be the last player standing after a
	  certain amount of turns
	- Precondition: You need 2-4 Players - Type in name for each
	- Each player gets $5,000 from the bank
	
	==RULES OF THE GAME==

	- Every player gets a turn
	- Follow directions of the game, type in 'help' if you do not know 
	   what to do and program will tell you your options

III. Modifications to Monopoly Rules

	- There is no Community Chest, it is all Chance
	- It is possible to build houses/hotels on Railroads and Utilities
	- You do not get an extra roll if you roll a double, and you don't 
	   go to jail if you roll 3 doubles in a row
	- If you decline to purchase a property it doesn't go up for auction
	- If you go bankrupt by landing on another players property the other
	   player does not get your property, rather your property goes back 
	   in the market
	- If you are in jail, you must wait two turns to get out; you cannot 
	   pay or roll doubles to get out early
	- You do not have to build houses evenly on a set
	- There is no limit to how many houses and/or hotels there are on the 
	   board at the same time


