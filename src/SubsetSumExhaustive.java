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
//    public static boolean subsetExists(List<Integer> multiset, int sum) {
//        List<List<Integer>> subsets = getSubsets(multiset, sum);
//        for (List<Integer> subset : subsets) {
//            int k = 0;
//            for (int val : subset) {
//                k += val;
//            }
//            if (k == sum) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static boolean subsetExists(List<Long> multiset, int sum) {
        //empty set is a subset if sum = 0
        if (sum == 0){
            return true;
        }

        //Create a multiset and add all elements of the input multiset if
        //its element are less than or equal to the sum
        List<Long> feasibleMultiset = new ArrayList<>();
        for (int i = 0; i < multiset.size(); i++) {
            long val = multiset.get(i);
            if (val < sum) {
                feasibleMultiset.add(val);
            }
            //return true if an element == sum
            else if (val == sum) {
                return true;
            }
        }

        //Create list of subsets of feasibleMultiset
        List<List<Long>> subsets = new ArrayList<>();
        for(int i = 0; i < feasibleMultiset.size(); i++){
            //add each individual element as a subset of feasibleMultiset
            subsets.add(Arrays.asList(feasibleMultiset.get(i)));
        }

        //Add an element of feasibleMultiset to all subsets and
        //check if their sum matches the target sum
        List<Long> currSubset;
        for(int i = 0; i < feasibleMultiset.size(); i++){
            for(int j = 0; j < feasibleMultiset.size(); j++){
                //Checking that we're not adding to subset that already holds
                //the same element
                if(i != j) {
                    currSubset = subsets.get(j);
                    currSubset.add(feasibleMultiset.get(i));

                    //Return true, we've found matching subset
                    if(SubsetUtil.getSum(currSubset) == sum){
                        return true;
                    }
                }
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
    public static List<List<Integer>> getSubsets(List<Integer> multiset, int sum) {
        ArrayList<List<Integer>> subsetsList = new ArrayList<>();
        // base case
        if (multiset.size() == 0) {
            subsetsList.add(new ArrayList<>());
        }
        else {
            List<Integer> remainingSet = new ArrayList<>();
            List<Integer> set = new ArrayList<>(multiset);

            // remove values greater than the sum
            for (int i = 0; i < set.size(); i++) {
                if (set.get(i) > sum) {
                    set.remove(i);
                }
            }
            remainingSet.addAll(set);

            // recursively removes first element and finds subsets
            int firstElement = remainingSet.remove(0);
            List<List<Integer>> subsets = getSubsets(remainingSet, sum);
            subsetsList.addAll(subsets);

            // subsets adding the first element back in
            subsets = getSubsets(remainingSet, sum);
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
        List<Long> S = Arrays.asList(1L, 2L, 3L, 9L);
        int k = 4;
        boolean result = subsetExists(S, k);
        System.out.println(result);

        k = 8;
        result = subsetExists(S, k);
        System.out.println(result);
    }
}
