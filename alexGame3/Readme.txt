Author: Alexander Oey (NetID: aoey2)

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

********** Additional information **********
All file and folder structure should not be changed. 
Any additional source files for compilation should be added to src/game/ folder and 
the source should be added to the game package.
If an alternative main function is needed, the Main-Class attribute in the MANIFEST.txt
file should be modified.
This code is tested to run on bertvm.

~~~~~~~~~~~~~~~~~ Code Documentation ~~~~~~~~~~~~~~~~~
Javadocs are provided in the docs folder.

~~~~~~~~~~~~~~~~~ Playing the game ~~~~~~~~~~~~~~~~~
********** Acceptable inputs **********
All inputs are case-insensitive.
Complete list of acceptable inputs:
## Quitting the program: 
	QUIT  
	Q  
	EXIT 
## Redisplaying the current room: 
	LOOK 
## Moving to another room: 
	GO <direction> 
	<direction>
	<direction> <direction> ...
## Recognized direction list:
	"UP", "U", "UPSTAIRS
	"DOWN", "D", "DOWNSTAIRS"
	"NORTH","N"
	"SOUTH", "S"
	"WEST", "W"
	"EAST", "E"
	"NORTHEAST", "NE"
	"NORTHWEST", "NW"
	"SOUTHWEST", "SW"
	"SOUTHEAST", "SE"
	"NORTHNORTHWEST", "NNW"
	"WESTNORTHWEST", "WNW"
	"NORTHNORTHEAST", "NNE"
	"EASTNORTHEAST", "ENE"
	"WESTSOUTHWEST", "WSW"
	"SOUTHSOUTHWEST", "SSW"
	"EASTSOUTHEAST", "ESE"
	"SOUTHSOUTHEAST", "SSE"
	