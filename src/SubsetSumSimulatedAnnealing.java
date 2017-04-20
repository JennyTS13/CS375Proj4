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
public class SubsetSumSimulatedAnnealing {


    /**
     * Tries to find a subset of a multiset with a given sum and determines the residue,
     * the absolute value of the difference between them.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    public static long subsetResidue(List<Long> multiset, long sum, int numRepetitions){
        Random r = new Random();
        List<Long> subset = SubsetUtil.genRandomSubset(multiset);
        long smallestResidue = SubsetUtil.getResidue(subset, sum);
        long residue = smallestResidue;
        long neighborResidue = 0;

        for (int i = 0; i< numRepetitions; i++) {
            List<Long> neighbor = SubsetUtil.genNeighbor(multiset, subset);
            neighborResidue = SubsetUtil.getResidue(neighbor, sum);

            //if neighbor has a larger or equal residue then it becomes the
            //subset with a probability of e^-T
            if (neighborResidue >= residue){
                //calculates T using the given formula
                double T = (neighborResidue - residue)/
                        (10000000000L  * Math.pow(0.8,(i/300)));
                double eT = Math.pow(Math.E, -T);
                if ( r.nextDouble() < eT){
                    subset = neighbor;
                    residue = neighborResidue;
                }
            }
            else{ // neighborResidue < residue, then neighbor becomes subset
                subset = neighbor;
                residue = neighborResidue;
            }

            //update residues
            if (neighborResidue < smallestResidue){
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
        List<Long> S = Arrays.asList(1L, 3L, 9L, 2L, 7L, 34L);
        long residue = SubsetSumSimulatedAnnealing.subsetResidue(S, 11, 100);
        System.out.println("Residue: " + residue);
    }

}
