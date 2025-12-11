package edu.grinnell.csc207.textadventure;
import edu.grinnell.csc207.textadventure.TextAdventure;

public class Parser {

    public void parse(String verb, String noun, Floor currentFloor) {
        switch (verb) {
            case "wait":
                currentFloor.waiting();
                break;

            case "go":
                currentFloor.go(noun);
                break;

            case "talk to":
                currentFloor.talkto(noun);
                break;

            case "pick up":
                currentFloor.pickup(noun);
                break;

            case "use":
                currentFloor.use(noun);
                break;

            default:
                System.out.println("Hmm... weird command. Try wait, go, talk to, pick up, or use.");
        }
    }
}
