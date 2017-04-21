/*
 * File:    Driver.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

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
     * Tests a SubsetSum - for Exact algorithms
     */
    private static void testExact(String name, ExactSubsetSum exactSubsetSum) {
        //run the algo 5 times for warm up
        for(int i = 0; i < 5; i++) {
            exactSubsetSum.subsetExists(S, k);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        boolean result = exactSubsetSum.subsetExists(S, k);
        Timer.stop();

        // output the results
        System.out.println("--------" + name+ "----------");
        System.out.println("Subset found: " + result);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }

    /**
     * Tests a SubsetSum - Greedy approach
     */
    private static void testGreedy() {
        // run greedy algo 5 times for warm up
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
     * Tests a SubsetSum - that uses a repetitive approximate approach
     *
     * @param reps number to times to compare with neighbors
     */
    private static void testRepApprox(String name, RepApproxSubsetSum algo, int reps) {
        // run the algo 5 times for warm up
        for(int i = 0; i < 5; i++){
            algo.subsetResidue(S, k, reps);
        }

        // finds if there exists a subset of the set S
        // where the sum of its elements is equal to a specified sum k.
        Timer.start();
        long totalResidue = algo.subsetResidue(S, k, reps);
        Timer.stop();


        // output the results
        System.out.println("--------" + name +"----------");
        System.out.println("Residue: " + totalResidue);

        // output the time needed to find the product
        System.out.println("Time: " + Timer.getRuntime() + "ms");
    }


    public static void main(String[] args){
        int[] numElements = {10, 100, 1_000, 10_000};
        int[] repsOpts = {100, 1_000, 5_000, 10_000, 50_000, 100_000};

        int maxSum = 100_000;
        int max = 10_000;
        //do not use max size greater than int max size, you will lose data in
        //dynamic programing with casting long to int

        SubsetSumDynamicProg dynamicProgramming = new SubsetSumDynamicProg();
        SubsetSumExhaustive exhaustive = new SubsetSumExhaustive();
        SubsetSumExhaustiveFaster exhaustiveFaster = new SubsetSumExhaustiveFaster();
        SubsetSumHillClimb hillClimb = new SubsetSumHillClimb();
        SubsetSumSimAnnealing annealing = new SubsetSumSimAnnealing();
        SubsetSumRandom random = new SubsetSumRandom();

        for (int i = 0; i < numElements.length; i++) {
            initialize(numElements[i], max, maxSum);
            // won't work past 20 elements (too slow)
            testExact("Exhaustive", exhaustive);
            // won't work past 10,000 elements (too slow)
            testExact("Exhaustive Fast", exhaustiveFaster);
            testExact("Dynamic Programming", dynamicProgramming);
            testGreedy();
            for (int j = 0; j < repsOpts.length; j++) {
                testRepApprox("Random", random, repsOpts[j]);
                testRepApprox("Hill Climb", hillClimb, repsOpts[j]);
                testRepApprox("Simulated Annealing", annealing, repsOpts[j]);
            }
        }
    }
}
