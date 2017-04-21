/*
 * File:    SubsetSumExhaustive.java
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
 */
public class SubsetSumExhaustive {

    /**
     * Checks all possible subsets of S until it finds a subset (if any)
     * with the appropriate sum (k).
     *
     * @param multiset List of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return boolean indicating if such a subset exists
     */
    public static boolean subsetExists(List<Long> multiset, int sum) {
        List<Long> newMultiset = new ArrayList<>();

        // remove values greater than the sum
        for (int i = 0; i < multiset.size(); i++) {
            Long item = multiset.get(i);
            if (item <= sum) {
                newMultiset.add(item);
            }
        }

        // get all subsets of the feasible multiset
        List<List<Long>> subsets = getSubsets(newMultiset, sum);
        // for each possible subset, check to see if its sum = target sum
        for (List<Long> subset : subsets) {
            if (SubsetUtil.getSum(subset) == sum) {
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
    public static List<List<Long>> getSubsets(List<Long> multiset, int sum) {
        ArrayList<List<Long>> subsetsList = new ArrayList<>();
        // base case
        if (multiset.size() == 0) {
            subsetsList.add(new ArrayList<>());
        }
        else {
            List<Long> remainingSet = new ArrayList<>();
            remainingSet.addAll(multiset);

            // recursively removes first element and finds subsets
            long firstElement = remainingSet.remove(0);
            List<List<Long>> subsets = getSubsets(remainingSet, sum);
            subsetsList.addAll(subsets);

            // subsets adding the first element back in
            subsets = getSubsets(remainingSet, sum);
            for (List<Long> subset : subsets) {
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
        List<Long> S = Arrays.asList(1L, 2L, 3L, 9L);
        int k = 8;
        boolean result = subsetExists(S, k);
        System.out.println(result);

        k = 11;
        result = subsetExists(S, k);
        System.out.println(result);
    }
}
