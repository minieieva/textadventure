package edu.grinnell.csc207.textadventure;

public class FloorOne implements Floor {

    boolean inside;
    boolean metStudent;
    boolean talkedToStudent;
    boolean finished;

    public FloorOne() {
        this.inside = false;
        this.metStudent = false;
        this.talkedToStudent = false;
        this.finished = false;
    }

    public void waiting() {
        if (!inside) {
            System.out.println("A group of hurrying-to-class students open the door for you. "
                    + "You enter the building and appear in a hallway.");
            this.inside = true;
        } else {
            System.out.println("You're just wasting your time. Try something else.");
        }
    }

    @Override
    public void go(String direction) {
        direction = direction.toLowerCase();

        // CASE 1: OUTSIDE
        if (!inside && !metStudent) {
            switch (direction) {
                case "forward":
                    System.out.println("You've entered Noyce.");
                    this.inside = true;
                    break;

                case "back":
                case "right":
                case "left":
                    System.out.println("You're still outside. Just one step further from Noyce. Hurry up!");
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
                    System.out.println("Walking is not going to help here. You need to talk to them.");
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
                    System.out.println("You are going up the stairs to the second floor! "
                                      + "Closer and closer to your destination!");
                    this.finished = true;
                    break;

                case "back":
                    System.out.println("You exit Noyce. Why would you do that now? Get back inside and go right!\n"
                                    + "You step right back into the hallway.");
                    this.inside = true;   // ← DO NOT allow leaving after talking
                    break;

                case "left":
                case "forward":
                    System.out.println("That direction won't help you now. Try going right.");
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
                        "You enter a small, empty room. The air is stale, and the vents buzz softly.\n"
                      + "You wait.\n"
                      + "A student slips in, coffee in hand.\n"
                    );
                    this.metStudent = true;
                    break;

                case "back":
                    System.out.println("You just came back outside. Stop wasting time and go forward to enter Noyce again.");
                    this.inside = false;
                    break;

                case "right":
                    System.out.println("You are going up the stairs to the second floor! "
                                      + "Closer and closer to your destination! ... "
                                      + "You overcame the first set of stairs. Now what?");
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

        // FALLBACK SAFETY – should never trigger but prevents silent failure
        unreasonableNoun();
    }

    @Override
    public void talkto(String person) {

        if (!metStudent) {
            System.out.println("You haven't met anyone yet.");
            return;
        }

        // VERY SMALL FIX: accept anything containing "student"
        if (person.toLowerCase().contains("student")) {
            System.out.println(
                "The student looks at you and smiles weakly.\n"
              + "\"Oh yeah, good luck man. I wanted to be a CS major my first year, "
              + "but I left after taking 151. Still gives me goosebumps.\n"
              + "Anyway, getting to the second floor will get you closer to the CS rooms.\n "
              + "I'll walk you to the main hallway.\"\n"
              + "....\n"
              + "\"Okay, now you just need to go right\"\n"
            );
            this.talkedToStudent = true;   // <-- This is the missing piece
        } 
        else {
            System.out.println("You have to specify who you want to talk to.");
        }
    }

    @Override
    public void pickup(String object) {
        System.out.println("There is nothing to pick up here.");
    }

    @Override
    public void use(String object) {
        System.out.println("You can't use that right now.");
    }

    public boolean isFinished() {
        return this.finished;
    }

    public Floor nextFloor(){
        return new FloorTwo();
    }
}
