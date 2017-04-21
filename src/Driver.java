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
     * Tests a SubsetSum - for Exact algorithms
     */
    private static void testExact(String name, ExactSubsetSum exactSubsetSum) {
        //run the algo 5 times for warm up
        for(int i = 0; i < 5; i++){
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
     * Tests a SubsetSum - that uses a repetitive approximate approach
     *
     * @param reps number to times to compare with neighbors
     */
    private static void testRepetitiveApproximate(String name, RepetitiveApproximateSubsetSum algo, int reps) {
        //run the algo 5 times for warm up
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

        SubsetSumDynamicProgramming dynamicProgramming = new SubsetSumDynamicProgramming();
        SubsetSumExhaustive exhaustive = new SubsetSumExhaustive();
        SubsetSumExhaustiveFaster exhaustiveFaster = new SubsetSumExhaustiveFaster();
        SubsetSumHillClimb hillClimb = new SubsetSumHillClimb();
        SubsetSumSimulatedAnnealing annealing = new SubsetSumSimulatedAnnealing();
        SubsetSumRandom random = new SubsetSumRandom();

        for (int i = 0; i < numElements.length; i++) {
            for (int j = 0; j < repsOpts.length; j++) {
                initialize(numElements[i], max, maxSum);
                // test exhaustive with 20, 50, 200 as params ~2 seconds
                if(j == 0) { // there are not different rep vals => run only once
                    //testExact("Exhaustive", exhaustive);
                    //testExact("Exhaustive Fast", exhaustiveFaster);
                    testExact("Dynamic Programming", dynamicProgramming);

                    testGreedy();
                }
                testRepetitiveApproximate("Random", random, repsOpts[j]);
                testRepetitiveApproximate("Hill Climb", hillClimb, repsOpts[j]);
                testRepetitiveApproximate("Simulated Annealing", annealing, repsOpts[j]);
            }
        }
    }
}
