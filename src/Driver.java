/*
 * File:    Driver.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A driver for testing different algorithms of multiplying two matrices.
 */
public class Driver {

    /**
     * The multiset S
     */
    static List<Long> S;

    /**
     * The sum k
     */
    static int k;

    /**
     * The time to find if there exists a subset of the set S
     * where the sum of its elements is equal to a specified sum k.
     */
    static long runtime = 0;

    /**
     * Initialize the multiset and set a random sum
     *
     * @param numElements number of elements that will be in multiset S
     * @param max the max of the range of added elements
     * @param maxSum max of the range of the sum
     */
    private static void initialize(int numElements, int max, int maxSum) {
        S = SubsetUtil.genRandomMultiSet(numElements, max);
        k = (new Random()).nextInt(maxSum);
    }

    /**
     * Tests a SubsetSum - Exhaustive approach
     */
    private static void testExhaustive() {
        //run exhaustive algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumExhaustive.subsetExists(S, k);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        boolean result = SubsetSumExhaustive.subsetExists(S, k);
        Timer.stop();

        // output the results
        System.out.println("--------Exhaustive----------");
        System.out.println("Subset found: " + result);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    /**
     * Tests a SubsetSum - Dynamic Programming
     */
    private static void testDynamic() {
        //run dynamic programming algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumDynamicProgramming.subsetExists(S, k);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        boolean result = SubsetSumDynamicProgramming.subsetExists(S, k);
        Timer.stop();

        // output the results
        System.out.println("--------Dynamic Programming----------");
        System.out.println("Subset found: " + result);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    /**
     * Tests a SubsetSum - Greedy approach
     */
    private static void testGreedy() {
        //run greedy algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumGreedy.subsetResidue(S, k);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        long residue = SubsetSumGreedy.subsetResidue(S, k);
        Timer.stop();

        // output the results
        System.out.println("--------Greedy----------");
        System.out.println("Residue: " + residue);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    /**
     * Tests a SubsetSum - Hill Climb approach
     *
     * @param reps number to times to compare with neighbors
     */
    private static void testHillClimb(int reps) {
        //run hill climb algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumHillClimb.subsetResidue(S, k, reps);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        long totalResidue = SubsetSumHillClimb.subsetResidue(S, k, reps);
        Timer.stop();


        // output the results
        System.out.println("--------Hill Climb----------");
        System.out.println("Residue: " + totalResidue);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    /**
     * Tests a SubsetSum - Random approach
     *
     * @param reps number to times to compare with neighbors
     */
    private static void testRandom(int reps) {
        //run random algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumRandom.subsetResidue(S, k, reps);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        long totalResidue = SubsetSumRandom.subsetResidue(S, k, reps);
        Timer.stop();

        // output the results
        System.out.println("--------Random----------");
        System.out.println("Residue: " + totalResidue);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    /**
     * Tests a SubsetSum - Simulated Annealing approach
     *
     * @param reps number to times to compare with neighbors
     */
    private static void testSimulatedAnnealing(int reps) {
        //run simulated annealing algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumSimulatedAnnealing.subsetResidue(S, k, reps);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        long totalResidue = SubsetSumSimulatedAnnealing.subsetResidue(S, k, reps);
        Timer.stop();

        // output the results
        System.out.println("--------Simulated Annealing----------");
        System.out.println("Residue: " + totalResidue);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    public static void main(String[] args){
        int[] numElements = {10, 100, 1_000, 10_000};
        int max = 10_000;
        //do not use max size greater than int max size, you will lose data in
        //dynamic programing with casting long to int

        int maxSum = 100_000;
        initialize(numElements[1], max, maxSum);
        // test exhaustive with 20, 50, 200 as params ~2 seconds
        // testExhaustive();
        testGreedy();
        testHillClimb(100_000);
        testDynamic();
        testSimulatedAnnealing(100_000);
    }
}
