/*
 * File:    SubsetSumExhaustive.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import sun.jvm.hotspot.oops.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 */
public class SubsetSumExhaustive implements SubsetSumExact {

    /**
     * Checks all possible subsets of S until it finds a subset (if any)
     * with the appropriate sum (k).
     *
     * @param multiset List of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return boolean indicating if such a subset exists
     */
    public boolean subsetExists(List<Integer> multiset, int sum) {
        List<List<Integer>> subsets = getSubsets(multiset);
        for (List<Integer> subset : subsets) {
            int k = 0;
            for (int val : subset) {
                k += val;
            }
            if (k == sum) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds all subsets of a given multiset S
     *
     * @param multiset List of integers in the multiset S
     *
     * @return A list of all subsets
     */
    public List<List<Integer>> getSubsets(List<Integer> multiset) {
        ArrayList<List<Integer>> subsetsList = new ArrayList<>();
        // base case
        if (multiset.size() == 0) {
            subsetsList.add(new ArrayList<>());
        }
        else {
            List<Integer> remainingSet = new ArrayList<>();
            remainingSet.addAll(multiset);

            // recursively removes first element and finds subsets
            int firstElement = remainingSet.remove(0);
            List<List<Integer>> subsets = getSubsets(remainingSet);
            subsetsList.addAll(subsets);

            // subsets adding the first element back in
            subsets = getSubsets(remainingSet);
            for (List<Integer> subset : subsets) {
                subset.add(0, firstElement);
            }

            subsetsList.addAll(subsets);
        }
        return subsetsList;
    }

    /**
     * Used to test Subset Sum Exhaustive Search method
     *
     * @param args
     */
    public static void main(String[] args) {
        SubsetSumExhaustive s = new SubsetSumExhaustive();
        List<Integer> S = Arrays.asList(1, 2, 3, 9);
        int k = 4;
        boolean result = s.subsetExists(S, k);
        System.out.println(result);

        k = 8;
        result = s.subsetExists(S, k);
        System.out.println(result);
    }
}
