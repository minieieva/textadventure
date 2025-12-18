package edu.grinnell.csc207.textadventure;
import edu.grinnell.csc207.textadventure.TextAdventure;
import edu.grinnell.csc207.textadventure.Floor;

public class FloorTwo extends Floor {
    private boolean foundMap;
    private boolean foundStairs;
    private boolean hasUseStairs;

    public FloorTwo(Inventory inventory){
        super(inventory);
        this.foundMap = false;
        this.foundStairs = false;
        this.hasUseStairs = false;
    }

    @Override
    public void waiting(){
        System.out.println("\nWaiting will not help you on the second floor.\n");
    }

    public void go(String direction){
            if(this.hasUseStairs){
                switch (direction) {
                case "forward":
                    System.out.println("\nOkay, you have 30 seconds left.\n" 
                    + "You see your room, you just have to go left to get to it, but professor Osera is approaching you.\n"
                    + "Hey! I haven't seen you around. Are you new?\n");
                    this.finished = true;
                    break;
                case "back", "left", "right":
                    wrongDirection();
                    break;
                default:
                    unreasonableNoun();
            }
            return;
            }

        if(this.foundMap && this.foundStairs){
            switch (direction) {
                case "right":
                    System.out.println("\nYou are getting closer!\n" 
                    + "You see the stairs forward (lead upstairs) and the weird coin on the floor?\n"
                    + "What will you do?\n");
                    this.hasUseStairs = true;
                    break;
                case "back", "left", "forward":
                    wrongDirection();
                    break;
                default:
                    unreasonableNoun();
            }
            return;
        }

        if(!this.foundMap){
            switch (direction) {
                case "left":
                    System.out.println("\nYou made a couple of steps left, turned your head left, and saw a map on the wall.\n"
                    + "Maybe it can help you find the way to your class?\n");
                    this.foundMap = true;
                    inventory.addItem("map");
                    break;
                case "back", "forward", "right":
                    wrongDirection();
                    break;
                default:
                    unreasonableNoun();
            }
            return;
        }

        if(this.foundMap){
            switch (direction) {
                case "right":
                    System.out.println("\nYou are on the right way!\n");
                    this.foundStairs = true;
                    break;
                case "back", "left", "forward":
                    wrongDirection();
                    break;
                default:
                    unreasonableNoun();
            }
            return;
        }
        
        
    }

    public void talkto(String person){
        if(!foundMap){
            System.out.println("\nThere is no one to talk to. Apparently, everyone is already in class.\n");
        }
    }

    public void pickup(String object){
        if(object.equals("coin")){
            inventory.addItem("token");
            System.out.println("\nCS professor saw you holding a golden coin in your hand.\n"
            + "He said, \"you got yourself a token on a first day! Lucky you!\"\n"
            + "That was weird, but whatever, maybe you'll need it later\n");
        }
        else{
            System.out.println("\nYou were not supposed to pick that up! Put it back and focus on your mission!\n");
        }
    }

public void use(String object) {
    switch (object) {
        case "map":
            if (inventory.hasItem("map")) {
                inventory.removeItem("map");
                System.out.println(
                    "\nFrom map you discover that you need to go right two times to get to the place where you might find stairs.\n"
                );
            } else {
                System.out.println("\nYou don't have a map.\n");
            }
            break;

        case "token":
            System.out.println("\nYou cannot use token on the second floor.\n");
            break;

        default:
            System.out.println("\nYou don't have such item.\n");
            break;
    }
}

    public Floor nextFloor(Inventory inventory){
        return new FloorThree(inventory);
    }

    
}
