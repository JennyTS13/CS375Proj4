/*
 * File:    SubsetSumDynamicProgramming.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.Arrays;
import java.util.List;

/**
 * Determines if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 */
public class SubsetSumDynamicProgramming {


    /**
     * Determines if there exists a subset of the multiset in which the sum is the
     * indicated sum using dynamic programming.
     *
     * @param multiset List of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return boolean indicating if such a subset exists
     */
    public static boolean subsetExists(List<Long> multiset, int sum){
        Long multiSetSize = SubsetUtil.getSum(multiset);

        //if creating matrix n x p
        //Long numCols = multiSetSize + 1;

        //if trying to save space
        Long numCols = (long)sum + 1;

        if (multiSetSize < sum){
            return false;
        }

        boolean[][] q = new boolean[multiset.size()][numCols.intValue()];

        // q[0][s] = (x1 == s)
        for (int j = 0; j< numCols; j++) {
            q[0][j] = (multiset.get(0) == j);
        }

        // for i > 0, q[i][s] = q[i-1][s] or (xi==s) or q[i-1][s-xi]
        for (int i = 1; i<multiset.size(); i ++){
            for (int s = 0; s< numCols; s++) {
                // true if set without the current value, xi, has the sum s or xi == s
                q[i][s] = ((q[i - 1][s]) || (multiset.get(i) == s));

                if (s - multiset.get(i) >= 0) { // if s-xi > 0
                    //true if calculated value above is true or
                    // the set without the current value, xi, has a sum of s - xi
                    q[i][s] = q[i][s] || (q[i - 1][s - multiset.get(i).intValue()]);
                }
            }
        }

        return  q[multiset.size()-1][sum];
    }

    /**
     * Used to test the Dynamic programing method of finding whether a subset exists
     * with a given sum
     * @param args
     */
    public static void main(String[] args){
        List<Long> S = Arrays.asList(1L, 3L, 9L, 2L);
        System.out.println("Result: " + SubsetSumDynamicProgramming.subsetExists(S, 4));
    }
}