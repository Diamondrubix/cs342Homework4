JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
		Game.java \
		Character.java \
		Player.java \
		NPC.java \
		UI.java \
		AI.java \
		Move.java \
		Place.java \
		Direction.java \
		Artifact.java \
		GameTester.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
