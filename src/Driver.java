/*
 * File:    Driver.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.Arrays;
import java.util.List;

/**
 * A driver for testing different algorithms of multiplying two matrices.
 */
public class Driver {

    /**
     * The set S
     */
    final static List<Integer> S = Arrays.asList(1, 2, 3, 9);

    /**
     * The sum k
     */
    final static int k = 4;

    /**
     * The time to find if there exists a subset of the set S
     * where the sum of its elements is equal to a specified sum k.
     */
    static long runtime = 0;

    /**
     * Tests a SubsetSum, finding the time and speed ups
     * @param version the name of the SubsetSum being tested
     * @param s the SubsetSum
     */
    private static void test(String version, SubsetSum s) {
        // warm up
        s.subsetExists(S, k);

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        boolean result = s.subsetExists(S, k);
        Timer.stop();

        // output the results
        System.out.println("--------" + version + "----------");

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    public static void main(String[] args){

    }
}
