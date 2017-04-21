/*
 * File:    SubsetSumGreedy.java
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
public class SubsetSumGreedy {

    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    public static long subsetResidue(List<Long> multiset, long sum) {
        // Start with an empty subset of multiset S
        List<Long> intSubset = new ArrayList<>();
        int subsetSum = 0;

        // Making copy of list to prevent side effects
        List<Long> multiSetS = new ArrayList<>(multiset);

        // Sort the multiset S from largest down to smallest.
        Collections.sort(multiSetS, Collections.reverseOrder());

        // Add next element in the sorted list to the subset if
        // the integer will not give the subset too large a sum
        // Otherwise, ignore that integer and move on to the next integer in the list
        for(long val : multiSetS){
            if(subsetSum + val <= sum){
                intSubset.add(val);
                subsetSum += val;

                // return 0 as residue if current sum is equal to the target sum
                if(subsetSum == sum){
                    return 0;
                }
            }
        }
        return sum - subsetSum;
    }

    /**
     * Used to test Subset Sum (Greedy approach)
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Long> S = Arrays.asList(1L, 2L, 3L, 9L);
        SubsetSumExhaustive subsetSumExhaustive = new SubsetSumExhaustive();
        int k = 4;
        long residue = SubsetSumGreedy.subsetResidue(S, k);
        System.out.println(residue);

        k = 8;
        residue = SubsetSumGreedy.subsetResidue(S, k);
        System.out.println(residue);
    }

}
