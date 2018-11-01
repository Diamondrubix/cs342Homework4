JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        GameTester.java \
        Game.java \
        Place.java \
        Direction.java \
	Artifact.java \
	Character.java \
	Move.java \
	Player.java \
	NPC.java \
	UI.java \
	DecisionMaker.java

default: classes

classes: $(CLASSES:.java=.class)

run:
	java GameTester

clean:
	$(RM) *.class

