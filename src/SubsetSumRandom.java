import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * File:    SubsetSumRandom.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 * Random approach.
 */
public class SubsetSumRandom {

    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return lowestRes - the residue
     */
    public static long subsetResidue(List<Integer> multiset, long sum, int numReps){
        List<Integer> current;
        long curRes = sum;
        long lowestRes = sum;

        for (int i = 0; i < numReps; i++){
            current = SubsetUtil.genRandomSubset(multiset);
            curRes = SubsetUtil.getResidue(current, sum);
            if (curRes <= lowestRes){
                lowestRes = curRes;
            }
        }
        return lowestRes;
    }

    /**
     * Used to test Subset Sum (Random approach)
     *
     * @param args
     */
    public static void main(String[] args){
        List<Integer> intList = new ArrayList<>(Arrays.asList(2, 1, 2, 3));
        long residue = SubsetSumRandom.subsetResidue(intList, 7, 3);
        System.out.println(residue);
    }
}
