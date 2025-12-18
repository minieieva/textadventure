package edu.grinnell.csc207.textadventure;
import edu.grinnell.csc207.textadventure.TextAdventure;

public class FloorThree extends Floor {
    boolean talkedToProfessor;
    boolean reachedClassroom;

    public FloorThree(Inventory inventory){
        super(inventory);
        this.talkedToProfessor = false;
        this.reachedClassroom = false;
    }

    public void waiting(){
        System.out.println("\nWaiting will not help you on the third floor.\n");
    }

    public void go(String direction){
        if(talkedToProfessor && direction.equals("left")){
            System.out.println("\nOh no! You're now late to class. Professor looks at you with a hint of dissatisfaction on his face.\n" 
                + "\"Do you have a token?\", he asks.\n"
                + "You may try to use it.\n");
            this.reachedClassroom = true;
        }
        else if (!talkedToProfessor && direction.equals("left")){
                System.out.println("\nCongratulations! You got to the class on time! That was close.\n"+
                "Huuuuh, try to wake earlier next time.\n");
                this.finished = true;
            }
        else if(direction.equals("right") || direction.equals("forward") || direction.equals("back")){
            wrongDirection();
        }
        else{
            unreasonableNoun();

        }
    }

    public void talkto(String person){
         if (person.toLowerCase().contains("osera") || person.toLowerCase().contains("professor")) {
            System.out.println(
                "\nYou talked to professor Osera and got really great insights into CS curriculum!\n"
                + "You now have a great connection in the department!\n"
                + "You've never been more excited about CS!\n"
            );
            this.talkedToProfessor = true;
        } 
        else {
            System.out.println("\nYou have to specify who you want to talk to.");
        }
    }

    public void pickup(String object){
        System.out.println("\nYou were not supposed to pick that up! Put it back and focus on your mission!");
    }

    public void use(String object){
        if(object.equals("token") && reachedClassroom){
            if(inventory.hasItem("token")){
                inventory.removeItem("token");
                System.out.println("\nYou used token to get into the class on time! Professor is impressed by your punctuality and dedication to studies."
                + "\nYou made a great first impression! Well done!");
                this.finished = true;
            }
            else{
                System.out.println("\nYou don't have a token. You should have picked it up. Professor looked at you and coldly said you to sit down.\n"
                + "Your classmate said that you missed an initial announcement. You are only allowed 2 late arrivals before it affects your grade. You're down to 1 now.\n"
                + "Better luck next time!\n");
                this.finished = true;
            }
        }
        else{
            System.out.println("\nYou don't have such item or it's not the right time to use it.\n");
        }
    }

    public Floor nextFloor(Inventory inventory){
        return null;
    }

    
}
