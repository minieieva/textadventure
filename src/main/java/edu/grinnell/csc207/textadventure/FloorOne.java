package edu.grinnell.csc207.textadventure;
import edu.grinnell.csc207.textadventure.Floor;

public class FloorOne extends Floor {

    boolean inside;
    boolean metStudent;
    boolean talkedToStudent;

    public FloorOne(Inventory inventory) {
        super(inventory);
        this.inside = false;
        this.metStudent = false;
        this.talkedToStudent = false;
    }

    public void waiting() {
        if (!inside) {
            System.out.println("\nA group of hurrying-to-class students open the door for you.\n"
                    + "You enter the building and appear in a hallway.\n");
            this.inside = true;
        } else {
            System.out.println("\nYou're just wasting your time. Try something else.\n");
        }
    }

    @Override
    public void go(String direction) {
        direction = direction.toLowerCase();

        // CASE 1: OUTSIDE
        if (!inside && !metStudent) {
            switch (direction) {
                case "forward":
                    System.out.println("\nYou've entered Noyce.\n");
                    this.inside = true;
                    break;

                case "back":
                case "right":
                case "left":
                    System.out.println("\nYou're still outside. Just one step further from Noyce. Hurry up!\n");
                    break;

                default:
                    unreasonableNoun();
            }
            return;
        }

        // CASE 2: INSIDE & STUDENT MET BUT NOT TALKED
        if (inside && metStudent && !talkedToStudent) {
            switch (direction) {
                case "forward":
                case "back":
                case "right":
                case "left":
                    System.out.println("\nWalking is not going to help here. You need to talk to them.\n");
                    break;

                default:
                    unreasonableNoun();
            }
            return;
        }

        // CASE 3: INSIDE & TALKED TO STUDENT
        if (inside && talkedToStudent) {
            switch (direction) {
                case "right":
                    System.out.println("\nYou are going up the stairs to the second floor!\n"
                                      + "Closer and closer to your destination!\n");
                    this.finished = true;
                    break;

                case "back":
                    System.out.println("\nYou exit Noyce. Why would you do that now? Get back inside and go right!\n"
                                    + "You step right back into the hallway.\n");
                    this.inside = true;
                    break;

                case "left":
                case "forward":
                    System.out.println("\nThat direction won't help you now. Try going right.\n");
                    break;

                default:
                    unreasonableNoun();
            }
            return;
        }

        // CASE 4: INSIDE & HAVEN'T MET STUDENT YET
        if (inside && !metStudent) {
            switch (direction) {
                case "forward":
                    System.out.println(
                        "\nYou enter a small, empty room. The air is stale, and the vents buzz softly.\n"
                      + "You wait.\n"
                      + "A student slips in, coffee in hand.\n"
                    );
                    this.metStudent = true;
                    break;

                case "back":
                    System.out.println("\nYou just came back outside. Stop wasting time and go forward to enter Noyce again.\n");
                    this.inside = false;
                    break;

                case "right":
                    System.out.println("\nYou are going up the stairs to the second floor!\n"
                                      + "Closer and closer to your destination! ...\n"
                                      + "You overcame the first set of stairs. Now what?\n");
                    this.finished = true;
                    break;

                case "left":
                    reasonableNounGo();
                    break;

                default:
                    unreasonableNoun();
            }
            return;
        }
        unreasonableNoun();
    }

    @Override
    public void talkto(String person) {

        if (!metStudent) {
            System.out.println("\nYou haven't met anyone yet.\n");
            return;
        }

        if (person.toLowerCase().contains("student")) {
            System.out.println(
                "\nThe student looks at you and smiles weakly.\n"
              + "\"Oh yeah, good luck man. I wanted to be a CS major my first year,\n"
              + "but I left after taking 151. Still gives me goosebumps.\n"
              + "Anyway, getting to the second floor will get you closer to the CS rooms.\n "
              + "I'll walk you to the main hallway.\"\n"
              + "....\n"
              + "\"Okay, now you just need to go right\"\n"
            );
            this.talkedToStudent = true;
        } 
        else {
            System.out.println("\nYou have to specify who you want to talk to.\n");
        }
    }

    @Override
    public void pickup(String object) {
        System.out.println("\nThere is nothing to pick up here.\n");
    }

    @Override
    public void use(String object) {
        System.out.println("\nYou can't use that right now.\n");
    }

    public boolean isFinished() {
        return this.finished;
    }

    public Floor nextFloor(Inventory inventory){
        return new FloorTwo(inventory);
    }
}
