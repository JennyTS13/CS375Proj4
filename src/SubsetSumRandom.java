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
public class SubsetSumRandom implements SubsetSumApprox{

    private int repetitions;

    /** Constructor **/
    public SubsetSumRandom(){
        this.repetitions = 3;
    }

    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset list of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return lowestRes - the residue
     */
    public int subsetResidue(List<Integer> multiset, int sum){
        System.out.println("Number of repetitions: " + this.repetitions);
        List<Integer> current;
        int curRes = sum;
        int lowestRes = sum;

        for (int i = 0; i < this.repetitions; i++){
            current = SubsetUtil.genRandomSubset(multiset);
            curRes = SubsetUtil.getResidue(current, sum);
            if (curRes <= lowestRes){
                lowestRes = curRes;
            }
        }
        return lowestRes;
    }

    /**
     * Sets the number of repetitions needed for the Random algorithm
     *
     * @param x number of repetitions
     */
    public void setRepetitions(int x){
        this.repetitions = x;
    }


    /**
     * Returns the number of repetitions that the Random algorithm will execute
     *
     * @return number of repetitions
     */
    public int getRepetitions() { return this.repetitions; }


    /**
     * Used to test Subset Sum (Random approach)
     *
     * @param args
     */
    public static void main(String[] args){
        SubsetSumRandom subsetSumRandom = new SubsetSumRandom();
        List<Integer> intList = new ArrayList<>(Arrays.asList(2, 1, 2, 3));
        int residue = subsetSumRandom.subsetResidue(intList, 7);
        System.out.println(residue);
    }
}
