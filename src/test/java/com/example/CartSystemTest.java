package com.example;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartSystemTest {
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut;
    protected final PrintStream originalErr;
    public static final String EXPECTED_NAME = "HOT JAVA";
    public static final String EXPECTED_ITEM_DESC = "VERY ADDICTIVE DRINK";
    public static final Double EXPECTED_ITEM_PRICE = 4.2D;
    public static final Integer EXPECTED_QUANTITY = 69;
    public static final Integer EXPECTED_AVAILABLE_QUANTITY = 1337;

    public CartSystemTest() {
        this.originalOut = System.out;
        this.originalErr = System.err;
    }

    @Before
    public void setUpStreams() {
        System.setOut(CartSystemTest.FormatRemover.INSTANCE);
        System.setOut(new CartSystemTest.FormatRemover(this.outContent));
        System.setErr(new PrintStream(this.errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(this.originalOut);
        System.setErr(this.originalErr);
    }

    @Test
    public void testDisplay() {
        CartSystem testCartSystem = new CartSystem();
        testCartSystem.display();
        Assert.assertEquals("Checks Display: ", "Cart:\nName                 Description          Price      Quantity   Sub Total \nPre-tax Total        0.00                \nTax                  0.00                \nTotal                0.00                \n", this.outContent.toString());
    }

    static class FormatRemover extends PrintStream {
        static final CartSystemTest.FormatRemover INSTANCE = new CartSystemTest.FormatRemover();

        private FormatRemover() {
            super(System.out);
        }

        private FormatRemover(OutputStream out) {
            super(out);
        }
    }
}
