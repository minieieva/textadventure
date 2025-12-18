package edu.grinnell.csc207.textadventure;
import java.util.HashMap;
import java.util.Map;

import edu.grinnell.csc207.textadventure.TextAdventure;

public class Inventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addItem(String itemName){
        inventory.put(itemName, inventory.getOrDefault(itemName, 1));
    }

    public boolean hasItem(String itemName) {
        return inventory.containsKey(itemName);
    }

    public void removeItem(String itemName) {
        int current = inventory.getOrDefault(itemName, 0);
        if (current <= 1) {
            inventory.remove(itemName);
        } else {
            inventory.put(itemName, current - 1);
        }
    }

}
