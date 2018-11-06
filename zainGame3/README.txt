Name: Zain Zahran
Netid: zzahra2

=== *Whats New in 4.0?* ===
Player vs NPC
More classes
DecisionMaker interface
Move class to carry out actions

== Coming soon ==
Random features no one cares about...
Graphical User Interface
Cosmetics
Multiplayer (cross-platform)
DLC + Lootboxes + Expansion Passes

== Overview ==
This program is a system for an exploration type game, with an
interconnected network of places. The user may navigate through
different rooms to find the exit. Currently this game is in
Alpha, so nothing is official. However, we are currently accepting
pre-orders, along with DLC payments. It is also a P2W game. But,
do not let that stop you from buying our lootboxes (we have
tons of cool skins). Stay tuned for early access. Thanks for playing.

== HOW TO RUN v4.0==
Where all java files are located, type in "make" to compile all of them together.
Run by typing :
java GameTester "MystiCity 4.gdf"
Of course, if you have a different GDF file, you may run that aswell
Just type:
Java GameTester "<Filename>.gdf" // (Don't forget the quotes when entering filename)

Screenshots are provided as an example in "/Screenshots/4.0/".

All functionality from 3.0 is supported along with the following:
Characters take turns doing movement/action commands. Users may decide
to exit (when there are no more players, the game will end). With the new
introduction of NPCs, they will have their own inventory and actions.
NPCs roam the dungeon and do whatever they want. For now, there is no
interaction, but there will be soon!

NEW COMMANDS:
'EXITALL' or 'QUITALL' - Quits the entire game, regardless of how many players exist
'NONE' - Skips your turn


== HOW TO RUN v3.0==
Where all java files are located, type in "make" to compile all of them together.
Run by typing :
java GameTester "MystiCity 31.gdf"
Of course, if you have a different GDF file, you may run that aswell
Just type:
Java GameTester "<Filename>.gdf" // (Don't forget the quotes when entering filename)

Screenshots are provided as an example in "/Screenshots/3.0/".

Once the program is running, the user is presented with a description of their current Place.
Then, the user is prompted to enter what they would like to do.
At this point, the user can say things like:
	- "EXIT" - Exits the game
	- "LOOK" - To display their current place
	Sample movement commands:
	- "GO north" - Go north
	- "go s" - Go south
	- "E" - Go east
	- "go D" - Go down
	- "go north-EAST" - User must include hyphen to go northeast (In future versions, this will be patched)
	Currently the program can even avoid mispells
	- "owjdpoajs go north" - Goes north
	- "asdfgh N ojsdo" - Goes north

== Explanation of how my program works ==
For the file input, I decided to first filter out all comments.
Once I did that, I passed it into the scanner for parsing of the
actual data. This happens in the Game(Scanner s) constructor.

	- Game - Container for the whole game. It contains the play() method.
			Allows each character to do something.

	- Place - Room that contains important information (description, id, etc.)
		Contains a hashmap of known places.
		Functions were created as per the logistics of the assignment.

	- Direction - This is used to link our FROM Place to our TO Place. We know support
			Locking/unlocking of doors. Find those artifacts. Follow us on twitter for special updates.
	- GameTester - The rooms + directions are no longer hardcoded based on the GDF. After creating them,
		GameTester calls the play method from the Game object.
	- Artifact - Players are able to get, hold, drop, use artifacts. They are found throughout
		the dungeon.
	- Move - Object that holds decisionmaker action
	- Character - Every character has a name, ID, description, inventory, etc.
		-> Player - inherits from character
		-> NPC    - inherits from character
	- UI/AI - Decide WHAT move the character makes
		-> UI - asks user what they want to do
		-> AI - generates a command for AI to do. Sometimes, they may do "NONE" and skip a turn


== Screenshots ==
See the screenshots folder in this zip file (/Screenshots/4.0). It will contain 3 screenshot for how to compile (With the makefile).
They are just sample screenshots and do not fully represent my program. More testing required.
For 3.0 and below, see (/Screenshots/4.0)

== License ==
This game is licensed for use on a single PC. it cannot be installed,
accessed, or displayed on more than one PC without purchasing additional
licenses.

Please keep in mind this product is serviced by subscription. Your 
automatic renewal for this product is based on the original invoice
date.

zzahra2-2018 and Co. reserves all rights.

== Credits ==
I hope this documentation was useful. Thank you for reading/playing/buying!
