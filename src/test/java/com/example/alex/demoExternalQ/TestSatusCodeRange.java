package com.example.alex.demoExternalQ;

import org.junit.Test;

public class TestSatusCodeRange {

    @Test
    public void testRange() {
        int x = 60;

        if (x >= 50 && x <= 59) {
            System.out.println("TRUE");
        }else {
            System.out.println("FALSE");
        }
    }

    @Test
    public void testConvertAndCompare() {
        String value = "59";

        if (Integer.valueOf(value) >= 50 && Integer.valueOf(value) <= 59) {
            System.out.println("TRUE");
        }else {
            System.out.println("FALSE");
        }
    }

    @Test
    public void testIfStatements() {
        boolean first = true;
        boolean second = true;


        if (first) {
            System.out.println("first if");
        }else if (second) {
            System.out.println("second if else");
        }else {
            System.out.println("final");
        }
    }
}
