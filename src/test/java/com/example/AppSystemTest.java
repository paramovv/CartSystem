package com.example;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppSystemTest {
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut;
    protected final PrintStream originalErr;
    public static final String EXPECTED_NAME = "HOT JAVA";
    public static final String EXPECTED_ITEM_DESC = "VERY ADDICTIVE DRINK";
    public static final Double EXPECTED_ITEM_PRICE = 4.2D;
    public static final Integer EXPECTED_QUANTITY = 69;
    public static final Integer EXPECTED_AVAILABLE_QUANTITY = 1337;

    public AppSystemTest() {
        this.originalOut = System.out;
        this.originalErr = System.err;
    }

    @Before
    public void setUpStreams() {
        System.setOut(AppSystemTest.FormatRemover.INSTANCE);
        System.setOut(new AppSystemTest.FormatRemover(this.outContent));
        System.setErr(new PrintStream(this.errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(this.originalOut);
        System.setErr(this.originalErr);
    }

    @Test
    public void testAdd() {
        Item testItem = null;
        AppSystem testAppSystem = new AppSystem();
        Assert.assertEquals("Checks if an item is null: ", false, testAppSystem.add(testItem));
        testItem = new Item("HOT JAVA", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
        Assert.assertEquals("Checks if an item is already in the collection: ", true, testAppSystem.add(testItem));
        Assert.assertEquals("Checks if an item is already in the collection: ", false, testAppSystem.add(testItem));
    }

    @Test
    public void testReduceAvailableQuantity() {
        AppSystem testAppSystem = new AppSystem();
        Item testItem = new Item("HOT JAVA", "VERY ADDICTIVE DRINK", EXPECTED_ITEM_PRICE, EXPECTED_AVAILABLE_QUANTITY);
        Assert.assertEquals("Checks if an item is in the collection: ", (Object)null, testAppSystem.reduceAvailableQuantity("HOT JAVA"));
        testAppSystem.add(testItem);
        Assert.assertEquals("Checks if an item in the collection: ", testItem, testAppSystem.reduceAvailableQuantity("HOT JAVA"));
        Integer reduced_EXPECTED_AVAILABLE_QUANTITY = EXPECTED_AVAILABLE_QUANTITY - 1;
        Assert.assertEquals("Checks if the available quantity of the item was decreased by 1: ", (long)reduced_EXPECTED_AVAILABLE_QUANTITY, testItem.getAvailableQuantity());
        testItem.setAvailableQuantity(1);
        testAppSystem.reduceAvailableQuantity("HOT JAVA");
        Assert.assertEquals("Checks if an item was removed when its available quantity reached 0: ", (Object)null, testAppSystem.getItemCollection().get("HOT JAVA"));
    }

    @Test
    public void testDisplay() {
        AppSystem testAppSystem = new AppSystem();
        testAppSystem.display();
        Assert.assertEquals("Checks Display: ", "AppSystem Inventory:\nName                 Description          Price      Available Quantity\npizza                very cheesy          12.30      3         \nsalad                cobb salad           15.50      12        \nhunger burger        huge patty           9.49       10        \nfried chicken        so crispy            18.99      5         \n", this.outContent.toString());
    }

    static class FormatRemover extends PrintStream {
        static final AppSystemTest.FormatRemover INSTANCE = new AppSystemTest.FormatRemover();

        private FormatRemover() {
            super(System.out);
        }

        private FormatRemover(OutputStream out) {
            super(out);
        }
    }
}
