/*
 * File:    SubsetSumExhaustiveFaster.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 */
public class SubsetSumExhaustiveFaster implements ExactSubsetSum {

    /**
     * Checks all possible subsets of S until it finds a subset (if any)
     * with the appropriate sum (k).
     *
     * @param multiset List of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return boolean indicating if such a subset exists
     */
    @Override
    public boolean subsetExists(List<Long> multiset, int sum) {
        List<Long> feasibleMultiset = new ArrayList<>();

        // remove values greater than the sum
        for (int i = 0; i < multiset.size(); i++) {
            Long val = multiset.get(i);
            if (val < sum) {
                feasibleMultiset.add(val);
            }
            else if (val == sum) {
                return true;
            }
        }

        long multisetSum = SubsetUtil.getSum(feasibleMultiset);
        if (sum == 0 || multisetSum == sum){
            return true;
        }
        else if (multisetSum < sum) {
            return false;
        }
        return getSubsets(feasibleMultiset, sum).getKey();
    }

    /**
     * Finds subsets of a given multiset S, with a sum < target sum
     * until you find a subset whose sum = target sum
     *
     * @param multiset List of integers in the multiset S
     *
     * @return A list of all subsets
     */
    public Pair<Boolean, List<Pair<Long, List<Long>>>>
                                getSubsets(List<Long> multiset, int sum) {
        List<Pair<Long, List<Long>>> subsetsList = new ArrayList<>();
        // base case
        if (multiset.size() == 0) {
            subsetsList.add(new Pair<>(0L, new ArrayList<>()));
        }
        else {
            List<Long> remainingSet = new ArrayList<>();
            List<Long> set = new ArrayList<>(multiset);

            remainingSet.addAll(set);

            // recursively removes first element and finds subsets
            long firstElement = remainingSet.remove(0);
            Pair<Boolean, List<Pair<Long, List<Long>>>> result =
                    getSubsets(remainingSet, sum);
            //if one of the subsets without the first item has the correct sum, return
            if(result.getKey()){
                return result;
            }
            subsetsList.addAll(result.getValue());

            // subsets adding the first element back in
            Pair<Boolean, List<Pair<Long, List<Long>>>> subsetsOriginal =
                    getSubsets(remainingSet, sum);
            List<Pair<Long, List<Long>>> subsets = subsetsOriginal.getValue();
            List<Pair<Long, List<Long>>> updatedSubsets = new ArrayList<>();
            for (int i = 0; i < subsets.size(); i++) {
                Pair<Long, List<Long>> originalPair = subsets.get(i);

                //updating the sum of the subset
                Long sumSubSet = originalPair.getKey() + firstElement;
                //adding the value to the subset
                originalPair.getValue().add(firstElement);

                //rebuilding the pair
                Pair<Long, List<Long>> pairIncludingFirst =
                        new Pair<>(sumSubSet, originalPair.getValue());

                //if subset has correct sum, return
                if (sumSubSet == sum){
                    return new Pair<>(true, subsetsList);
                }
                //only subsets will sum < target sum, continue adding numbers
                else if (sumSubSet < sum){
                    updatedSubsets.add(pairIncludingFirst);
                }
            }

            subsetsList.addAll(updatedSubsets);
        }

        return new Pair<>(false, subsetsList);
    }

    /**
     * Used to test Subset Sum Exhaustive Search method
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Long> S = Arrays.asList(1L, 2L, 3L, 9L);
        SubsetSumExhaustiveFaster subsetSumExhaustiveFaster = new SubsetSumExhaustiveFaster();
        int k = 4;
        boolean result = subsetSumExhaustiveFaster.subsetExists(S, k);
        System.out.println(result);

        k = 8;
        result = subsetSumExhaustiveFaster.subsetExists(S, k);
        System.out.println(result);
    }
}
