package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem {

    private HashMap<String, Item> itemCollection = new HashMap<>();



    TheSystem() {
        if(getClass().getSimpleName().equals("AppSystem")){
            try {
                File path = new File("resources/sample.txt");
                Scanner input = new Scanner(path);


                while (input.hasNextLine()){
                    String line = input.nextLine();
                    String[] itemInfo = line.split("\\s ");
                    itemCollection.put(itemInfo[0].trim(), new Item(itemInfo[0].trim(), itemInfo[1].trim(),
                            Double.parseDouble(itemInfo[2].trim()), Integer.parseInt(itemInfo[3].trim())));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void setItemCollection(HashMap<String, Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public HashMap<String, Item> getItemCollection() {
        return itemCollection;
    }

    public Boolean checkAvailability(Item item) {

        return item.getQuantity() < item.getAvailableQuantity();  // проверить исключения
    }

    public Boolean add(Item item) {
        if (item == null) return false;
        else if (getItemCollection().containsKey(item.getItemName()) && checkAvailability(item))
        {
            item.setQuantity(item.getQuantity() + 1);
            return true;
        } else if (!getItemCollection().containsKey(item.getItemName())) {
            this.itemCollection.put(item.getItemName(), new Item(item.getItemName(), item.getItemDesc(),
                    item.getItemPrice(), item.getQuantity()));
            return true;
        }
        return false;
    }

    public Item remove(String itemName) {
        if (itemCollection.containsKey(itemName)) {
            Item temp = itemCollection.get(itemName);
            itemCollection.remove(itemName);
            return temp;
        }
        else return null;
    }

    public abstract void display();
}
