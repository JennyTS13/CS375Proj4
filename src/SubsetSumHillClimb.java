/*
 * File:    SubsetSumHillClimb.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Finds if there exists a subset of the set S where
 * the sum of its elements is equal to a specified sum k.
 * Greedy approach.
 */
public class SubsetSumHillClimb implements SubsetSumApprox {

    /** number of repetitions **/
    private int reps;

    /**
     * Constructor
     *
     * @param reps number to times to compare with neighbors
     */
    public SubsetSumHillClimb(int reps){
        this.reps = reps;
    }

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
        List<Integer> currSubset = genRandomSubset(multiset);
        int currResidue = getResidue(currSubset, sum);
        List<Integer> neighbor;
        int neighborResidue;

        for(int i = 0; i < reps; i++){
            neighbor = genNeighborSet(multiset, currSubset);
            neighborResidue = getResidue(neighbor, sum);
            //If the neighbor has smaller residue, then make it the current subset
            if(neighborResidue < currResidue){
                currSubset = neighbor;
                currResidue = neighborResidue;
            }
        }

        return currResidue;
    }

    /**
     * Returns integer residue of the provided set
     *
     * @param multiset list of integers in the multiset
     * @param sum int indicating the specified sum k
     * @return residue of the provided set
     */
    public int getResidue(List<Integer> multiset, int sum){
        int residue = sum;
        for(int val: multiset){
            residue -= val;
        }
        return Math.abs(residue);
    }

    /**
     * Returns a random subset of the  provided set
     *
     * @param multiset list of integers in the multiset S
     * @return random list of integers (subset) of multiset S
     */
    public List<Integer> genRandomSubset(List<Integer> multiset){
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
     * Returns a list of integers that is a neighbor of the provided set
     *
     * @param multiset list of integers in the multiset S
     * @param subset list of integers in the set to generate a neighbor for
     *
     * @return list of integers that is a neighbor of the provided set
     */
    private List<Integer> genNeighborSet(List<Integer> multiset, List<Integer> subset){
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
     * Used to test Subset Sum (Greedy approach)
     *
     * @param args
     */
    public static void main(String[] args){
        SubsetSumHillClimb subsetSumHillClimb = new SubsetSumHillClimb(20);
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 3, 9, 2, 7, 34, 8, 2, 45, 4));
        int residue = subsetSumHillClimb.subsetResidue(intList, 15);
        System.out.println(residue);
    }
}
