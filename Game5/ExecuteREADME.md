Authors: 
Alexander Oey (NetID: aoey2)
Adam Arato (NetID: aarato2)
Zain Zahran (Netid: zzahra2)


~~~~~~~~~~~~~~~~~ Setting Up ~~~~~~~~~~~~~~~~~
********** Compilation **********
## To build class files:
	make build

## To build jar file:
	make 

********** Cleaning **********
## To remove jar file:
	make cjar
## To remove class files:
	make cbuild
## To remove both jar and class files:
	make clean
	
********** Running Program **********
## Running: 
	java -jar Game.jar <filename>
## Command line options:
	filename is a GDF file specifying the layout of the game. If it is not provided
	or not found by the program, the user will be prompted to specify the filename 
	once the program runs.

once the program is running you will be asked to type 0 1 or an ip. 0 will run it like a normal game
Press 1 to start hosting multiplayer. From your prespective the game will still run normally.
If there is a server running and you know the ip address type the ip address for it.

To Test the networking element, run the program, type 1, then run a second instance of the program,
type localhost, then switch back to the first program and start moving. You will see it broadcast to
the second running program. There is no error checking in this process and the program will crash
if either port 3001 is occupied or if you do not give it a proper ip address.

The names of each player will be printed on the console so the input following the name
will be the action of that player. 
Valid Commands list (case insensitive):
	Exit - Exits the game
	Look - Prints out information about the current player location and player health and equipment
	Inventory OR Inve - Prints out the player inventory
	Get <name> - Picks up an artifact specified by the argument <name> from the player location
	Drop <name> - Drops an artifact specified by the argument <name> from the player inventory
	Use <name> - Use an artifact in player inventory specified by <name>
	Equip <name> - Equip an artifact in player inventory. Only works for Armor types.
	Go <dir...> - Move to another place with <dir> as the move direction from current location.
	             Supports sending multiple directions in one command which will move the character
							 through multiple rooms.
							 Example: Go North -> Moves the character to the place north of the current place.
							          Go N E -> Moves the character to the place north of the current place and 
												          moves the character to the east of that place.
	
 