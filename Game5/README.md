# cs342Homework4
group project for cs 342

We all sat together and peer programed the beginning and for variouse reasons had to make changes to
the Game, and Game Tester class

Adam Arato (aarato2):
//HOMEWORK 4
My third of the project that we merged was all the things in the Character package.
I one by one copy pasted the functions from my gmae 3 into those classes as needed, then made
very heavy modifications to all of it in order to fit with the groups design. One of the bigger
changes was that we implemented the command design pattern for move So I had to change much of how the MakeMove,
and GetMove worked in order to work with this new design pattern. Various other functions had to be added or changed
in order to support things the other 2 members of my group had to add.

Another change was the need to change my constructors and the way I parsed everything. I originally created all
characters in one go with the one constructor but now the constructor with the scanner is called multiple times and
creates the objects directly instead.

The thing that I added to the project was the ability to do some multiplayer. I added the code in the Network package.
It implements the singleton design pattern for the client so that there is only one client per program sending 
data to the server. Lack of time did not allow me to do proper multiplayer. While my 3 classes do allow for two way 
communication between two seperatly running programs, I only had time to send the servers movements to the client to
make a sort of "spectator mode" for other people connecting.

//HOMEWORK 5
I added a gui (gui 2) that has a small textbox where you enter the commands as you would in the terminal version. You hit send
in order to actually have the command go through. the larger text box below is where everything is displayed. you only see
things releated to what your player did or can see. It will display "your turn" when it is your turn to move. every player has
their own gui. The gui also has a smaller box below showing the players inventory. Player's name will be in the JFrame.

I also changed up how the network code works in order to support the new bridge interface. The netprintln was replaced with two
new types of prints in the IO class. regular print automatically sends to the network is network is enabled. nonetprint does not.

I added a static member to the character class that holds the characters whose turn it is at the moment. This way when somethings
needs to be displayed to a specific character we can use the static character print statement to make sure it goes only to that 
character gui.

Network stuff was reworked to work with a spectator gui. I added a gui for the spectator. Now the spectator can see what every player
sees combined. Even displays that go only to specific players will be visable to the spectator.

Game class was modified not to crash when given an invalid ip address.

I added the win condition of after all players have exited the total value of their inventory is added up and the player with the highest
value wins the game. If there is a tie everybody is a loser.


Alexander Oey (aoey2):

// HOMEWORK 4
My part of the project that was included in the merger were all classes in the artifact,
game and move package. In this homework, I modified the implementation of the Move class
from using enumeration MoveType to command design pattern. In addition, I modified some 
methods in my Artifact class to interface better with the other 2 members' classes.

I added the Armor functionality to the game and some the supporting functionalities such
as modifications to the GDF file format, the parser in the Game class for both special
Artifact and Place types, and added some methods in the Character class to 
support equipping the armor. Lastly, I added javadoc documentation for some classes.

// HOMEWORK 5

I added GUI 1 that has a textfield accept commands to the character that selects the 
GUI. The command is submitted to the game by pressing the end turn button.
This GUI has a dialog from the game that is located in the center of the screen
and two side panels that shows the current status of the character (such as damage and
health) and the contents of the character's inventory. After switching for the first 
time, the values in these side panels will not be available. 
In addition, I implemented the IO class, the interface to the GUI(s) and TextInterface. 

Next, I enhanced the gameplay by adding combat features that allows combat between 
player characters. The combat is turn-based and players who have health below zero
will die at the end of the round (not immediately). To make combat more interesting,
I added weapons as artifacts to the game that enhances the combat abilities of the 
player.

To accomodate the addition of weapons and armors, I added EQUIP command and list of 
equipped items to each character. The bonuses from a weapon will not be activated
if it is not equipped. Also, I modified the GDF file format and added various 
methods to the existing classes to support the combat mechanics.

Zain Zahran (zzahra2) :

// HOMEWORK 4

My part of the project was the same as everyone else for the merge step. We all sat together to
attempt to get all of our classes together to compile. This was challenging since we had to
consistently modify each of our classes, rename functions, etc. 

My primary responsibility was the Place class. Along with the addition of new functions
the additional features were to create different kinds of places. I added about 6 new
places that all inherit from Place, but have their own functionalities. We called this,
the ambientFunction() - each place has its own ambient function. For the normal place,
the vanilla function did nothing. But the new kinds of places that extend Place overrided this
to change functionalities. For example, one of the new places is a Dark Room. The player must have
a flashlight in his/her inventory to get an output. If no flashlight exists, inventory cannot be displayed
along with the artifacts in the room. Another room, FountainRoom, is just a add-on room that
heals the player 10 hp per turn. These are just some samples of the new kinds of places we implemented
to HW 4.

The 2nd main thing I worked on, was the UML diagram. For that, I was able to create the diagram structure
of our program and give a better understanding of the fundamentals occuring within. Everyone chipped in here
to make sure that their new features were included in the UML diagram. Everything was confirmed and verified
by all group members for changes/adjustments.

// HOMEWORK 5

For this project I had to get my end of the GUI working for implementation of the final homework
along with the completion of the UML diagram. For my GUI, I added all typical functions plus buttons for the character 
to see their inventory, quit, or exit the whole entire game. The GUI was linked to Alex and Adam's GUI 
with the UserInterface. 

Additionally, like in HW 4, the 2nd task I worked on was the complete UML diagram for HW 5. 
Anything that was missed or incorrect from the last diagram has been updated for the finished program. 
Additions include new interface classes, armor/weapons/equippable classes (alex made armor equippables, see his 
readme), and fixed some inheritance connections that were wrong previously.

Lastly, I added a major expansion to the old MysticCity that incorporates the places that I added last HW (version 512). 
I drew up a picture that outlines all new additions and changes to the map. See MysticCity_1.png and MysticCity_2.png, 
both show the working version when MysticCity512.gdf is ran. All directions are working and their respective artifacts
required to retrieve the ambient function benefits (See HW4 readme for more information on Place details).


