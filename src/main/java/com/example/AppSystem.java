package com.example;

public class AppSystem<tempItem> extends TheSystem {
    AppSystem() {
        super();
    }

    @Override
    public void display() {
        System.out.println("AppSystem Inventory:");
        System.out.printf("%-20s %-20s %-10s %-10s%n",
                "Name", "Description", "Price", "Available Quantity");
        for (Item displayItem : getItemCollection().values()) {
            System.out.printf("%-20s %-20s %-10.2f %-10d%n",
                    displayItem.getItemName(),
                    displayItem.getItemDesc(),
                    displayItem.getItemPrice(),
                    displayItem.getAvailableQuantity());
        }
    }

    @Override      // this overwrites the parents class add method 
    public Boolean add(Item item) {
        if (item == null) return false;
        else if (getItemCollection().containsKey(item.getItemName())) {
            System.out.println(item.getItemName() + "is already in the App System");
            return false;
        } else {
            getItemCollection().put(item.getItemName(), item);
            return true;
        }
    }

    public Item reduceAvailableQuantity(String item_name) {
        Item tempItem = getItemCollection().get(item_name);
        if (getItemCollection().containsKey(item_name)) {
            if(tempItem.getAvailableQuantity() > 1)
            tempItem.setAvailableQuantity(tempItem.getAvailableQuantity() - 1);
            else getItemCollection().remove(tempItem.getItemName());
        } else return null;
        return tempItem;
    }
}
