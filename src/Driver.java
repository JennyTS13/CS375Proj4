/*
 * File:    Driver.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */


/**
 * A driver for testing different algorithms of multiplying two matrices.
 */
public class Driver {

    /**
     * Tests a SubsetSum, finding the time and speed ups
     * @param version the name of the SubsetSum being tested
     * @param s the SubsetSum
     */
    private static void test(String version, SubsetSum s) {

        //
        Timer.start();

        Timer.stop();

        // output the results
        System.out.println("--------" + version + "----------");

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    public static void main(String[] args){

    }
}
