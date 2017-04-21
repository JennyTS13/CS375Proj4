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
public class SubsetSumExhaustiveFaster implements ExactSubsetSum{

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
        List<Long> newMultiset = new ArrayList<>();

        // remove values greater than the sum
        for (int i = 0; i < multiset.size(); i++) {
            Long item = multiset.get(i);
            if (item < sum) {
                newMultiset.add(item);
            }
            else if (item == sum){
                return true;
            }
        }


        long multisetSum = SubsetUtil.getSum(newMultiset);
        if (sum == 0 || multisetSum == sum){
            return true;
        }
        else if (multisetSum < sum){
            return false;
        }

        return getSubsets(newMultiset, sum).getKey();
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
        List<Long> S = Arrays.asList(1L, 8L, 3L, 9L, 10L, 32L, 42L, 5L,
                12L, 14L, 24L, 5L, 7L, 6L, 78L);
        List<Long> S2 = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            S2.add(8L);
        }

        SubsetSumExhaustive exhaustive = new SubsetSumExhaustive();
        SubsetSumExhaustiveFaster exhaustiveFaster = new SubsetSumExhaustiveFaster();

        int k = 24;
        boolean result = exhaustiveFaster.subsetExists(S, k);
        System.out.println(result);

        k = 84;
        Timer.start();
        result = exhaustiveFaster.subsetExists(S, k);
        Timer.stop();
        System.out.println("\nFast Version: " + result);
        System.out.println("Time: " + Timer.getRuntime());

        Timer.start();
        result = exhaustive.subsetExists(S, k);
        Timer.stop();
        System.out.println("Slow Version: " + result);
        System.out.println("Time: " + Timer.getRuntime());

        k = 55;
        Timer.start();
        result = exhaustiveFaster.subsetExists(S2, k);
        Timer.stop();
        System.out.println("\nFast Version: " + result);
        System.out.println("Time: " + Timer.getRuntime());

        Timer.start();
        result = exhaustive.subsetExists(S2, k);
        Timer.stop();
        System.out.println("Slow Version: " + result);
        System.out.println("Time: " + Timer.getRuntime());
    }
}
