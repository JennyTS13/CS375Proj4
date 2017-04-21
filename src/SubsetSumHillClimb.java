/*
 * File:    SubsetSumHillClimb.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 * Hill-Climbing approach.
 */
public class SubsetSumHillClimb implements RepApproxSubsetSum {

    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     * @param reps number to times to compare with neighbors
     *
     * @return double - the residue
     */
    @Override
    public long subsetResidue(List<Long> multiset, long sum, int reps) {
        List<Long> currSubset = SubsetUtil.genRandomSubset(multiset);
        long currResidue = SubsetUtil.getResidue(currSubset, sum);
        List<Long> neighbor;
        long neighborResidue;

        for(int i = 0; i < reps; i++){
            neighbor = SubsetUtil.genNeighbor(multiset, currSubset);
            neighborResidue = SubsetUtil.getResidue(neighbor, sum);
            // If the neighbor has smaller residue, then make it the current subset
            if(neighborResidue < currResidue){
                currSubset = neighbor;
                currResidue = neighborResidue;
            }
        }
        return currResidue;
    }

    /**
     * Used to test Subset Sum (Greedy approach)
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Long> intList = new ArrayList<>(Arrays.asList(1L, 3L, 9L, 2L, 7L, 34L, 8L, 2L, 45L, 4L));
        SubsetSumHillClimb subsetSumHillClimb = new SubsetSumHillClimb();
        long residue = subsetSumHillClimb.subsetResidue(intList, 15, 20);
        System.out.println(residue);
    }
}
