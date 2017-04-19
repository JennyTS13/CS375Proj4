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
    static List<Integer> S;

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
        S = genRandomMultiSet(numElements, max);
        k = (new Random()).nextInt(maxSum);
    }


    /**
     * Generates a multiset such that it has the specified number of elements
     * within the specified range
     * @param numElements number of elements that will be in multiset S
     * @param max the max of the range of added elements
     * @return generated multiset
     */
    private static List<Integer> genRandomMultiSet(int numElements, int max) {
        List<Integer> multiset = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < numElements; i++) {
            multiset.add(rand.nextInt(max));
        }
        return multiset;
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
        int residue = SubsetSumGreedy.subsetResidue(S, k);
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
     * @param algoReps number of times to run algorithm for average residue
     * @param reps number to times to compare with neighbors
     */
    private static void testHillClimb(int algoReps, int reps) {
        //run hill climb algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            SubsetSumHillClimb.subsetResidue(S, k, reps);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        int totalResidue = SubsetSumHillClimb.subsetResidue(S, k, reps);
        Timer.stop();

        //repeat to calculate average residue
        for(int i = 0; i < algoReps-1; i++){
            totalResidue += SubsetSumHillClimb.subsetResidue(S, k, reps);
        }

        // output the results
        System.out.println("--------Hill Climb----------");
        System.out.println("Average Residue: " + ((double)totalResidue)/algoReps);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    public static void main(String[] args){
        int numElements = 10_000;
        int max = 10_000;
        int maxSum = 100_000;
        initialize(numElements, max, maxSum);
        // test exhaustive with 20, 50, 200 as params ~2 seconds
        // testExhaustive();
        testGreedy();
        testHillClimb(10, 100_000);
    }
}
