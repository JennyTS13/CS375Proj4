/*
 * File:    SubsetSimulatedAnnealing.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */


import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 * Greedy approach.
 */
public class SubsetSimulatedAnnealing {


    /**
     * Tries to find a subset of a multiset with a given sum and determines the residue,
     * the absolute value of the difference between them.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    public static int subsetResidue(List<Integer> multiset, int sum, int numRepetitions){
        Random r = new Random();
        List<Integer> subset = SubsetUtil.genRandomSubset(multiset);
        int smallestResidue = -1;

        for (int i = 0; i< numRepetitions; i++) {
            List<Integer> neighbor = SubsetUtil.genNeighbor(multiset, subset);
            int neighborResidue = SubsetUtil.getResidue(neighbor, sum);
            int residue = SubsetUtil.getResidue(subset, sum);

            //if neighbor has a larger or equal residue then it becomes the
            //subset with a probability of e^-T
            if (neighborResidue >= residue){
                //calculates T using the given formula
                double T = (neighborResidue - residue)/
                        (10000000000L  * Math.pow(0.8,(i/300)));
                if ( r.nextDouble() > Math.pow(Math.E, -T)){
                    subset = neighbor;
                }
            }
            else{ // neighborResidue < residue, then neighbor becomes subset
                subset = neighbor;
            }

            //updates the smallest residue
            if (smallestResidue < 0 ){
                if (neighborResidue < residue){
                    smallestResidue = neighborResidue;
                }
                else{
                    smallestResidue = residue;
                }
            }
            else if (neighborResidue < smallestResidue){
                smallestResidue = neighborResidue;
            }
        }

        return smallestResidue;
    }

    /**
     * Used to test the Simulated Annealing method of finding whether a subset exists
     * with a given sum
     * @param args
     */
    public static void main(String[] args){
        List<Integer> S = Arrays.asList(1, 3, 9, 2, 7, 34);
        int residue = SubsetSimulatedAnnealing.subsetResidue(S, 11, 100);
        System.out.println("Residue: " + residue);
    }

}
