package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

public class TheSystemTest {
    public TheSystemTest() {
    }

    @Test(
            expected = IndexOutOfBoundsException.class
    )
    public void testNoPublicField() {
        Field firstField = TheSystem.class.getFields()[0];
        Assert.fail("TheSystem should only contain the private instance variables");
    }

    @Test
    public void testInventoryFile() {
        String inventoryFile = "resources//sample.txt";
        File file = new File(inventoryFile);
        Assert.assertEquals("Checks if resources//sample.txt exists: ", false, !file.exists());

        try {
            Scanner sc = new Scanner(file);
            Assert.assertEquals("Checks if resources//sample.txt is empty: ", false, !sc.hasNextLine());
        } catch (FileNotFoundException var5) {
            var5.printStackTrace();
        }

    }

    @Test
    public void testTheSystemConstructorMakeItemCollectionEmpty() {
        TheSystemTest.TSystem TSystem = new TheSystemTest.TSystem();
        Assert.assertEquals("This should constructor initialize the itemCollection variable with an empty hashmap: ", 0L, (long)TSystem.getItemCollection().size());
    }

    @Test
    public void testTheSystemConstructorIsAppSystem() {
        AppSystem TSystem = new AppSystem();
        Assert.assertEquals(" checks if the AppSystem is invoking the constructor, if so, it adds the items from the sample.txt file to the itemCollection: ", "items added to ItemCollection is > 0", TSystem.getItemCollection().size() > 0 ? "items added to ItemCollection is > 0" : "No items added to ItemCollection");
    }

    @Test
    public void testCheckAvailability() {
        String EXPECTED_NAME = "HOT JAVA";
        String EXPECTED_ITEM_DESC = "VERY ADDICTIVE DRINK";
        Double EXPECTED_ITEM_PRICE = 4.2D;
        Integer EXPECTED_QUANTITY = 1;
        Integer EXPECTED_AVAILABLE_QUANTITY = 1337;
        Item testItem = new Item("HOT JAVA", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
        TheSystemTest.TSystem TSystem = new TheSystemTest.TSystem();
        Assert.assertEquals("Checks if an item's Quantity is greater or equal than the AvailableQuatity: ", true, TSystem.checkAvailability(testItem));
        testItem.setQuantity(6969);
        Assert.assertEquals("Checks if an item's Quantity is greater or equal than the AvailableQuatity: ", false, TSystem.checkAvailability(testItem));
    }

    @Test
    public void testAdd() {
        String EXPECTED_NAME = "HOT COCO";
        String EXPECTED_ITEM_DESC = "VERY ADDICTIVE DRINK";
        Double EXPECTED_ITEM_PRICE = 4.2D;
        Integer EXPECTED_QUANTITY = 69;
        Integer EXPECTED_AVAILABLE_QUANTITY = 1337;
        Item testItem = null;
        TheSystemTest.TSystem TSystem = new TheSystemTest.TSystem();
        Assert.assertEquals("Checks if an item is null: ", false, TSystem.add(testItem));
        testItem = new Item("HOT COCO", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
        Assert.assertEquals("Checks if an item is already in the collection: ", true, TSystem.add(testItem));
        Assert.assertEquals("Checks if an item was added to the the collection: ", true, TSystem.getItemCollection().size() > 0);
        TSystem.add(testItem);
        Assert.assertEquals("Checks if an item's quantity increased by one after it was added again to the the collection: ", true, testItem.getQuantity() == 2);
    }

    @Test
    public void testRemove() {
        String EXPECTED_NAME = "HOT COCO";
        String EXPECTED_ITEM_DESC = "VERY ADDICTIVE DRINK";
        Double EXPECTED_ITEM_PRICE = 4.2D;
        Integer EXPECTED_QUANTITY = 69;
        Integer EXPECTED_AVAILABLE_QUANTITY = 1337;
        Item testItem = new Item("HOT COCO", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
        TheSystemTest.TSystem TSystem = new TheSystemTest.TSystem();
        TSystem.add(testItem);
        Assert.assertEquals("Checks if an item was removed from the collection: ", testItem.getItemName(), TSystem.remove("HOT COCO").getItemName());
        Assert.assertEquals("Checks if an item is not in the collection: ", (Object)null, TSystem.remove("Happiness"));
    }

    class TSystem extends TheSystem {
        TSystem() {
        }

        public void display() {
        }
    }
}
