/*
 * File:    AccuracyTest.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import javafx.util.Pair;

import java.util.List;

/**
 * Tests the accuracy of different subset sum algorithms:
 * Greedy, hill climb, random, and simulated annealing.
 */
public class AccuracyTest {

    /**
     * Calculates the sum of an array of longs
     *
     * @param array the given array
     *
     * @return the sum of the array
     */
    private static long sum(long[] array) {
        long sum = 0;
        for (int i = 0; i<array.length; i++) {
            sum+= array[i];
        }
        return sum;
    }

    /**
     * Calculates the averages over all runs with a given algorithm and repetition number.
     *
     * @param output the residues [numSets][algo#][repsOpt]
     *
     * @return the averages [algo#][repsOpt]
     */
    private static double[][] getAverageOverSets(double[][][] output) {
        double[][] average = new double[3][output[0][0].length];
        //[algo#][repsOpt]

        for (int algo = 0; algo <3; algo++ ){
            for (int rep = 0; rep < output[0][0].length; rep++) {
                //calculates the average over all runs with a given algo and rep num
                double sum = 0;
                for (int set = 0; set < output[0].length; set++) {
                    sum += output[set][algo][rep];
                }

                average[algo][rep] = sum/average[0].length;
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
     * @param repsPerSet the number of times the algo is run on one multiset
     *
     * @return the residues [numSets][algo#][repsOpt]
     */
    private static Pair<long[], double[][][]> getResults(int size, long maxSize, long k,
                                           int numSets, int[] repsOpts, int repsPerSet) {

       double[][][] outputRepetitive = new double[numSets][4][repsOpts.length];
       long[] outputGreedy = new long[numSets];
        for (int set = 0; set < numSets; set ++){
            List<Long> multiSet = SubsetUtil.genRandomMultiSet(size, maxSize);

            //finds the (average)residues
            outputGreedy[set] = SubsetSumGreedy.subsetResidue(multiSet, k);
            outputRepetitive[set] = outputsRepetitive(k, repsOpts, repsPerSet, multiSet);
        }

        return new Pair<>(outputGreedy, outputRepetitive);
    }

    /**
     * Finds the average residue when running hill climb, random, and simulated annealing
     * algorithms on a given multiset
     *
     * @param k the target sum
     * @param repsOpts the number of reps for the algorithm
     * @param repsPerSet the number of times to run the algorithm
     * @param multiSet the multiset to run it on
     *
     * @return the residues [algo#][repOpt]
     */
    private static double[][] outputsRepetitive(long k, int[] repsOpts, int repsPerSet,
                                                List<Long> multiSet) {
        double[][] outputs = new double[3][repsOpts.length];
        RepApproxSubsetSum[] algos = {new SubsetSumHillClimb(),
                new SubsetSumRandom(), new SubsetSumSimAnnealing()};

        //repetitive aprox algos
        for (int algo = 0; algo < 3; algo++) {
            for (int rep = 0; rep < repsOpts.length; rep++) {
                long[] runs = new long[repsPerSet];

                //runs the algo on the multiset repPerSet num times
                for (int m = 0; m < repsPerSet; m++) {
                    runs[m] = algos[algo].subsetResidue(multiSet, k, repsOpts[rep]);
                }

                //takes the average of the residues
                double average = sum(runs)/ (double)repsPerSet;
                outputs[algo][rep] = average;
            }
        }

        return  outputs;
    }


    /**
     * Finds the accuracy of the four different algorithms for finding the
     * subset sum.
     *
     * @param args
     */
    public static void main(String[] args){
        int size = 100;
        long maxSize = (long)Math.pow(10, 12);
        long k = 25* maxSize;
        int numSets = 50;
        //testing values
//        int size = 10;
//        long maxSize = 10;
//        long k = 5;
//        int numSets = 3;

        int numRums = 20;
        //the number of times an algo is run on a multiset

        int[] repsOpts = {100, 1_000, 5_000, 10_000, 50_000, 100_000};
        String[] algos = {"Hill Climb: ", "Random: ", "Annealing: " };


        Pair<long[], double[][][]> output = getResults(size, maxSize, k, numSets,
                repsOpts, numRums);
        //key: greedy outputs
        //val: repetitive outputs [numSets][algo#][repsOpt]

        double greedyOut = sum(output.getKey())/ (double)numSets;
        double[][] repOuts = getAverageOverSets(output.getValue());

        System.out.println("Greedy: " + greedyOut);

        for (int algo = 0; algo < 3; algo++){
            System.out.println("\n" + algos[algo]);
            for (int rep =0; rep < repsOpts.length; rep++){
                System.out.println("Number of Reps: " + repsOpts[rep] +
                        " Result: " + repOuts[algo][rep]);
            }
        }
    }
}
