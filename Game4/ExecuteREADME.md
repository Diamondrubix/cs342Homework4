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

To Test the networking element, run the program, type 1, then run a second instnace of the program,
type localhost, then switch back to the first program and start moveing. You will see it broadcast to
the second running program. There is no error checking in this process and the program will crash
if either port 3001 is occupied or if you do not give it a proper ip address.
