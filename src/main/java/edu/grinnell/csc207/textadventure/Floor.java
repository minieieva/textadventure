package edu.grinnell.csc207.textadventure;
import edu.grinnell.csc207.textadventure.TextAdventure;

public abstract class Floor {
    public boolean finished;
    protected Inventory inventory;

    Floor(Inventory inventory) {
        this.finished = false;
        this.inventory = inventory;
    }

    public void unreasonableNoun(){
        System.out.println("That's not an option. Can you read simple instructions? You don't have time for this! Try again.");
    };

    public void wrongDirection(){
        System.out.println("Oh no! You got yourself into Noyce mysterious loop. It feels like you've crossed 7 corridors trying to find the way out, but at the end, you ended up where you started on the second floor." 
                    +  "Try a different direction.");
    };

    public void reasonableNounGo(){
        System.out.println("Going this way leads you to a dead end. Frustrated, you return to your initial place and try to take a differnt direction");
    };

    public abstract void waiting();

    public abstract void go(String direction);

    public abstract void talkto(String person);

    public abstract void pickup(String object);

    public abstract void use(String object);

    boolean isFinished() {
        return finished;
    }

    public abstract Floor nextFloor(Inventory inventory);
}