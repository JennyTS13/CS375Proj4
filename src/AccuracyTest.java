/*
 * File:    AccuracyTest.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.List;

/**
 * Tests the accuracy of different subset sum algorithms:
 * Greedy, hill climb, random, and simulated annealing.
 */
public class AccuracyTest {

    /**
     * Calculates the sum of an array of longs
     * @param array the given array
     * @return the sum of the array
     */
    private static long sum(long[] array){
        long sum = 0;
        for (int i = 0; i<array.length; i++){
            sum+= array[i];
        }
        return sum;
    }

    /**
     * Finds the the sum of each row in the array of outputs
     * @param outputs the array
     * @return the sum of each row
     */
    private static long[][] getAverage(long[][][] outputs){
        long[][] average = new long[outputs.length][outputs[0].length];
        for (int i = 0; i< outputs.length; i++){
            for (int j = 0; j < outputs[0].length; j++){
                average[i][j] = sum(outputs[i][j])/outputs[0][0].length;
            }
        }

        return average;
    }

    /**
     * Calculates the residues for multiple random multiset when using 4 different methods
     * of finding the subset sum.
     *
     * @param size the size of the multi set
     * @param maxSize the max size of the numbers in the multiset
     * @param k the target sum
     * @param numSets the number of times to create a set and calculate the residues
     * @param repsOpts the number of reps the algos
     * @return the residues
     */
    private static long[][][] getResults(int size, long maxSize, long k,
                                         int numSets, int[] repsOpts) {
        long[][][] output = new long[repsOpts.length][4][numSets];
        for (int j = 0; j < repsOpts.length; j++) {
            int numReps = repsOpts[j];
            for (int i = 0; i < numSets; i++) {
                List<Long> multiSet = SubsetUtil.genRandomMultiSet(size, maxSize);
                output[j][0][i] = SubsetSumGreedy.subsetResidue(multiSet, k);
                output[j][1][i] = SubsetSumHillClimb.subsetResidue(multiSet, k, numReps);
                output[j][2][i] = SubsetSumRandom.subsetResidue(multiSet, k, numReps);
                output[j][3][i] =
                        SubsetSumSimulatedAnnealing.subsetResidue(multiSet, k, numReps);
            }
        }
        return output;
    }

    /**
     * Finds the accuracy of the four different algorithms for finding the
     * subset sum.
     * @param args
     */
    public static void main(String[] args){
        int size = 100;
        long maxSize = (long)Math.pow(10, 12);
        long k = 25* maxSize;
        int numSets = 50;

        int[] repsOpts = {100, 1_000, 5_000, 10_000, 50_000, 100_000};
        String[] algos = {"Greedy:     ", "Hill Climb: ", "Random:     ", "Annealing:  " };

        long[][][] output = getResults(size, maxSize, k, numSets, repsOpts);
        //[repOpt][algo#][numSet]

        long[][] averages = getAverage(output);

        //print the outputs
        /*
        for (int i =0; i < repsOpts.length; i++){
            System.out.println("\n\nNumber of Reps: " + repsOpts[i]);
            for (int j = 0; j< 4; j ++){
                System.out.println("\n" + algos[j]);
                for (int l = 0; l< numSets; l++){
                    System.out.print(output[i][j][l] + " ");
                }
            }
        }*/

        //print the averages
        for (int i =0; i < repsOpts.length; i++){
            System.out.println("\n\nNumber of Reps: " + repsOpts[i]);
            for (int j = 0; j< 4; j ++){
                System.out.println(algos[j] + averages[i][j]);
            }
        }
    }
}
