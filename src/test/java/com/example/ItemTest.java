package com.example;

import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Test;

public class ItemTest {
    public static final String EXPECTED_NAME = "HOT JAVA";
    public static final String EXPECTED_ITEM_DESC = "VERY ADDICTIVE DRINK";
    public static final Double EXPECTED_ITEM_PRICE = 4.2D;
    public static final Integer EXPECTED_QUANTITY = 1;
    public static final Integer EXPECTED_AVAILABLE_QUANTITY = 1337;
    Item tester;

    public ItemTest() {
        this.tester = new Item("HOT JAVA", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
    }

    @Test(
            expected = IndexOutOfBoundsException.class
    )
    public void testNoPublicField() {
        Field firstField = Item.class.getFields()[0];
        Assert.fail("Item should only contain the private instance variables");
    }

    @Test
    public void testGetItemName() {
        Assert.assertEquals("Name of the item: ", "HOT JAVA", this.tester.getItemName());
    }

    @Test
    public void testSetItemName() {
        this.tester.setItemName("Red Bull");
        Assert.assertEquals("Name of the item: ", "Red Bull", this.tester.getItemName());
    }

    @Test
    public void testGetItemDesc() {
        Assert.assertEquals("The item's description: ", "VERY ADDICTIVE DRINK", this.tester.getItemDesc());
    }

    @Test
    public void testSetItemDesc() {
        this.tester.setItemDesc("Even more addictive");
        Assert.assertEquals("The item's description: ", "Even more addictive", this.tester.getItemDesc());
    }

    @Test
    public void testGetItemPrice() {
        Assert.assertEquals("A Double value which represents the item’s price: ", EXPECTED_ITEM_PRICE, Double.valueOf(this.tester.getItemPrice()));
    }

    @Test
    public void testSetItemPrice() {
        this.tester.setItemPrice(77.77D);
        Assert.assertEquals("A Double value which represents the item’s price: ", Double.valueOf(77.77D), Double.valueOf(this.tester.getItemPrice()));
    }

    @Test
    public void testGetQuantity() {
        Assert.assertEquals("The number of items the user has added to the cart: ", (int)EXPECTED_QUANTITY, this.tester.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        this.tester.setQuantity(33);
        Assert.assertEquals("The number of items the user has added to the cart: ", (33), this.tester.getQuantity());
    }

    @Test
    public void testGetAvailableQuantity() {
        Assert.assertEquals("the number of items with that name  available in the system: ", (int)EXPECTED_AVAILABLE_QUANTITY, this.tester.getAvailableQuantity());
    }

    @Test
    public void testSetAvailableQuantity() {
        this.tester.setAvailableQuantity(66);
        Assert.assertEquals("the number of items with that name  available in the system: ", (66), this.tester.getAvailableQuantity());
    }

    @Test
    public void testItemNoArgumentConstructor() {
        this.tester = new Item();
        Assert.assertEquals("This constructor should initializes quantity to 1: ", (1), this.tester.getQuantity());
    }

    @Test
    public void testItemConstructor() {
        this.tester = new Item("HOT JAVA", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
        Assert.assertEquals("Name of the item: ", "HOT JAVA", this.tester.getItemName());
        Assert.assertEquals("The item's description: ", "VERY ADDICTIVE DRINK", this.tester.getItemDesc());
        Assert.assertEquals("A Double value which represents the item’s price: ", EXPECTED_ITEM_PRICE,  Double.valueOf(this.tester.getItemPrice()));
        Assert.assertEquals("The number of items the user has added to the cart: ", (int)EXPECTED_QUANTITY, this.tester.getQuantity());
        Assert.assertEquals("the number of items with that name  available in the system: ", (int)EXPECTED_AVAILABLE_QUANTITY, this.tester.getAvailableQuantity());
    }
}
