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

My responsibility for this project was to get my end of the GUI working for implementation of the final homework
along with completing the UML diagram. For my GUI, I added all typical functions plus buttons for the character 
to see their inventory, quit, or exit the whole entire game. The GUI was linked to Alex and Adam's GUI 
with the UserInterface. Additionally, like in HW 4, the 2nd task I worked on was the complete UML diagram for HW 5. 
Anything that was missed or incorrect from the last diagram has been updated for the finished program. 
Additions include new interface classes, armor/weapons/equippable classes (alex actually developed this, see his 
readme), and fixed some inheritance connections that were wrong previously. Everything in HW 4 for places has been 
transferred over to this new HW. Alex oey took care of the actual implementation and got it to work with his Armor injection.
Lastly, I helped find compilation errors and bugs with Adam and Alex and helped whenever applicable.


