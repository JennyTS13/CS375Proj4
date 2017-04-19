/*
 * File:    SubsetSumHillClimb.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 * Greedy approach.
 */
public class SubsetSumHillClimb implements SubsetSumApprox {

    /** number of repetitions **/
    private int reps;

    /**
     * Constructor
     *
     * @param reps number to times to compare with neighbors
     */
    public SubsetSumHillClimb(int reps){
        this.reps = reps;
    }

    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    public int subsetResidue(List<Integer> multiset, int sum){
        List<Integer> currSubset = SubsetUtil.genRandomSubset(multiset);
        int currResidue = SubsetUtil.getResidue(currSubset, sum);
        List<Integer> neighbor;
        int neighborResidue;

        for(int i = 0; i < reps; i++){
            neighbor = SubsetUtil.genNeighbor(multiset, currSubset);
            neighborResidue = SubsetUtil.getResidue(neighbor, sum);
            //If the neighbor has smaller residue, then make it the current subset
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
    public static void main(String[] args){
        SubsetSumHillClimb subsetSumHillClimb = new SubsetSumHillClimb(20);
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 3, 9, 2, 7, 34, 8, 2, 45, 4));
        int residue = subsetSumHillClimb.subsetResidue(intList, 15);
        System.out.println(residue);
    }
}
