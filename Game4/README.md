# cs342Homework4
group project for cs 342

We all sat together and peer programed the beginning and for variouse reasons had to make changes to
the Game, and Game Tester class

Adam Arato:

My third of the project that we merged was all the things in the Character package.
I one by one copy pasted the functions from my gmae 3 into those classes as needed, then made
very heavy modifications to all of it in order to fit with the groups design. One of the bigger
changes was that we implemented the command design pattern for move So I had to change much of how the MakeMove,
and GetMove worked in order to work with this new design pattern. Variouse other functions had to be added or changed
in order to support things the other 2 members of my group had to add.

Another change was the need to change my constructors and the way I parsed everything. I originally created all
characters in one go with the one constructor but now the constructor with the scanner is called multiple times and
creates the objects directly instead.

The thing that I added to the project was the ability to do some multiplayer. I added the code in the Network package.
It implements the singleton design pattern for the client so that there is only one client per program sending 
data to the server. Lack of time did not allow me to do proper multiplayer. While my 3 classes do allow for two way 
communication between two seperatly running programs, I only had time to send the servers movements to the client to
make a sort of "spectator mode" for other people connecting.