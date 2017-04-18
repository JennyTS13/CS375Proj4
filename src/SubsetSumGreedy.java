/*
 * File:    SubsetSumExhaustive.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.*;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 * Greedy approach.
 */
public class SubsetSumGreedy implements SubsetSumApprox {

    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset List of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    public double subsetResidue(List<Integer> multiset, int sum){
        Collections.sort(multiset);
        return 0;
    }

    public static void main(String[] args){
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println(intList.toString());
    }

}
