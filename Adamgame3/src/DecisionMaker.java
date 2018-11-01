/*
it figures out how variouse characters should move
 */
public interface DecisionMaker {

    Move getMove(Character c, Place p);

}
