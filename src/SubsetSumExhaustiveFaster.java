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
public class SubsetSumExhaustiveFaster {

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

        if (sum==0 || SubsetUtil.getSum(newMultiset) == sum){
            return true;
        }

        return getSubsets(newMultiset, sum).getKey();
    }

    /**
     * Finds all subsets of a given multiset S,
     * until you find a subset whose sum = target sum
     *
     * @param multiset List of integers in the multiset S
     *
     * @return A list of all subsets
     */
    public static Pair<Boolean, List<Pair<Long, List<Long>>>> getSubsets(List<Long> multiset, int sum) {
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
            Pair<Boolean, List<Pair<Long, List<Long>>>> result = getSubsets(remainingSet, sum);
            if(result.getKey()){
                return result;
            }
            subsetsList.addAll(result.getValue());

            // subsets adding the first element back in
            Pair<Boolean, List<Pair<Long, List<Long>>>> subsets = getSubsets(remainingSet, sum);
            List<Pair<Long, List<Long>>> opts = subsets.getValue();
            for (int i = 0; i < opts.size(); i++) {
                Pair<Long, List<Long>> originalPair = opts.get(i);
                Long sumSubSet = originalPair.getKey() + firstElement;
                originalPair.getValue().add(firstElement);
                Pair<Long, List<Long>> pairIncludingFirst = new Pair<>(sumSubSet, originalPair.getValue());
                opts.set(i, pairIncludingFirst );
                if (sumSubSet == sum){
                    return new Pair<>(true, subsetsList);
                }
            }

            subsetsList.addAll(opts);
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
        int k = 4;
        boolean result = subsetExists(S, k);
        System.out.println(result);

        k = 7;
        result = subsetExists(S, k);
        System.out.println(result);
    }
}
