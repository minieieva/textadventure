package edu.grinnell.csc207.textadventure;

import java.util.Scanner;

public class TextAdventure {

    static Floor floor;
    static Scanner scanner;
    static Parser parser;

    public TextAdventure() {
        floor = new FloorOne();
        scanner = new Scanner(System.in);
        parser = new Parser();
    }

    public static void printcommands() {
        System.out.println("wait, go (left, right, forward, back), talk to (someone), pick up (something), use (something).\n");
    }

    public static String getVerb(String command) {
        String[] words = command.split(" ");
        if (words.length >= 3) {
            return words[0] + " " + words[1];
        } else if (words.length == 2) {
            return words[0];
        } else {
            return "";
        }
    }

    public static String getNoun(String command) {
        String[] words = command.split(" ");
        if (words.length >= 3) {
            return words[2];
        } else if (words.length == 2) {
            return words[1];
        } else {
            return "";
        }
    }

    public static void main(String[] args) {

        TextAdventure game = new TextAdventure();
        boolean playing = true;

        System.out.println(
            "It's your first semester at Grinnell College. You signed up for your first computer science class CSC151.\n"
            + "You're very excited. Self-service portal showed that class located in so called Noyce, Room 3815.\n"
            + "You're standing in front of Noyce entrance and your class starts in 5 minutes. You cannot be late!\n"
            + "But here is a problem: you need to find your way to your classroom... it's harder than you expect.\n"
            + "What will you do first?\n"
        );

        while (playing) {
            printcommands();
            String command = scanner.nextLine();

            String verb = getVerb(command);
            String noun = getNoun(command);

            parser.parse(verb, noun, floor);

            if (floor.isFinished()) {
                Floor next = floor.nextFloor();
                if (next == null) {
                    System.out.println("You reached the ending of the game!");
                    break;
                }
                floor = next;
                System.out.println("You reached the next floor!");
            }
        }
    }
}
