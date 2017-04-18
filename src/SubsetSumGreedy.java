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
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    public int subsetResidue(List<Integer> multiset, int sum){
        //Start with an empty subset of multiset S
        List<Integer> intSubset = new ArrayList<>();
        int subsetSum = 0;

        //Making copy of list to prevent side effects
        List<Integer> multiSetS = new ArrayList<>(multiset);

        //Sort the multiset S from largest down to smallest.
        Collections.sort(multiSetS, Collections.reverseOrder());

        //Add next element in the sorted list to the subset if
        //the integer will not give the subset too large a sum
        //Otherwise, ignore that integer and move on to the next integer in the list
        for(int i = 0; i < multiSetS.size(); i++){
            Integer currElement = multiSetS.get(i);
            if(subsetSum + currElement <= sum){
                intSubset.add(currElement);
                subsetSum += currElement;
            }
        }

        return sum - subsetSum;
    }

    /**
     * Used to test Subset Sum (Greedy approach)
     *
     * @param args
     */
    public static void main(String[] args){
        SubsetSumGreedy subsetSumGreedy = new SubsetSumGreedy();
        List<Integer> intList = new ArrayList<>(Arrays.asList(2, 1, 2, 3));
        int residue = subsetSumGreedy.subsetResidue(intList, 2);
        System.out.println(residue);
    }

}
