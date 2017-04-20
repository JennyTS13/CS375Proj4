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
        List<List<Long>> subsets = getSubsets(multiset, sum);
        for (List<Long> subset : subsets) {
            int k = 0;
            for (long val : subset) {
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
    public static List<List<Long>> getSubsets(List<Long> multiset, int sum) {
        ArrayList<List<Long>> subsetsList = new ArrayList<>();
        // base case
        if (multiset.size() == 0) {
            subsetsList.add(new ArrayList<>());
        }
        else {
            List<Long> remainingSet = new ArrayList<>();
            List<Long> feasibleMultiset = new ArrayList<>(multiset);

            // remove values greater than the sum
            for (int i = 0; i < feasibleMultiset.size(); i++) {
                if (feasibleMultiset.get(i) > sum) {
                    feasibleMultiset.remove(i);
                }
            }
            remainingSet.addAll(feasibleMultiset);

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
        int k = 4;
        boolean result = subsetExists(S, k);
        System.out.println(result);

        k = 11;
        result = subsetExists(S, k);
        System.out.println(result);
    }
}
