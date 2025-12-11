package edu.grinnell.csc207.textadventure;
import edu.grinnell.csc207.textadventure.TextAdventure;

public interface Floor {

    default void unreasonableNoun(){
        System.out.println("That's not an option. Can you read simple instructions? You don't have time for this! Try again.");
    };

    default void reasonableNounGo(){
        System.out.println("Going this way leads you to a dead end. Frustrated, you return to your initial place and try to take a differnt direction");
    };

    public void waiting();

    public void go(String direction);

    public void talkto(String person);

    public void pickup(String object);

    public void use(String object);

    public boolean isFinished();

    public Floor nextFloor();
}