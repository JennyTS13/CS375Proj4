/*
 * File:    SubsetUtil.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utility class for subset sum calculations
 */
public class SubsetUtil {

    /**
     * Returns a list of integers that is a neighbor of the provided set
     *
     * @param multiset list of integers in the multiset S
     * @param subset list of integers in the set to generate a neighbor for
     *
     * @return list of integers that is a neighbor of the provided set
     */
    public static List<Integer> genNeighbor(List<Integer> multiset, List<Integer> subset){
        //Initialize the neighbor set to be a clone of the subset
        List<Integer> neighborSet = new ArrayList<>(subset);

        //Selecting random i, j
        Random rand = new Random();
        int i = rand.nextInt(multiset.size());
        int j = rand.nextInt(multiset.size());

        //Making sure that i and j are distinct
        while(i == j){
            j = rand.nextInt(multiset.size());
        }

        Integer x_i = multiset.get(i);
        Integer x_j = multiset.get(j);

        //If x_i is in the subset, remove it from the neighbor set
        //Otherwise, add x_i to the neighbor set
        if(subset.contains(x_i)){
            neighborSet.remove(x_i);
        }
        else{
            neighborSet.add(x_i);
        }

        //If x_j is in the subset, then with probability 0.5, remove it from neighbor set
        //If x_j is not in the subset, then with probability 0.5, add x_j to neighbor set
        if(subset.contains(x_j)){
            if(rand.nextFloat() < 0.5) {
                neighborSet.remove(x_i);
            }
        }
        else{
            if(rand.nextFloat() < 0.5) {
                neighborSet.add(x_i);
            }
        }

        return neighborSet;
    }

    /**
     * Returns a random subset of the  provided set
     *
     * @param multiset list of integers in the multiset S
     * @return random list of integers (subset) of multiset S
     */
    public static List<Integer> genRandomSubset(List<Integer> multiset){
        Random rand = new Random();
        List<Integer> subset = new ArrayList<>();

        //randomly add elements of the multiset to the subset
        for (Integer item: multiset){
            if (rand.nextFloat() < rand.nextFloat()){
                subset.add(item);
            }
        }

        return subset;
    }

    /**
     * Returns integer residue of the provided list (multiset)
     *
     * @param multiset list of integers in the multiset
     * @param sum int indicating the specified sum k
     * @return residue of the provided list (multiset)
     */
    public static int getResidue(List<Integer> multiset, int sum){
        int residue = sum;
        for(int val: multiset){
            residue -= val;
        }
        return Math.abs(residue);
    }

    /**
     * Returns the sum of the elements in the provided list (multiset)
     *
     * @param multiset list of integers in the multiset
     * @return sum of the elements in the provided list (multiset)
     */
    public static int getSum(List<Integer> multiset){
        int sum = 0;
        for (Integer i : multiset){
            sum += i;
        }
        return sum;
    }
}
