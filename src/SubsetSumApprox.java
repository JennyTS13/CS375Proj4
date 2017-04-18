/*
 * File:    SubsetSumExact.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.List;

/**
 * An interface used for the Subset Sum problem (approximation approach)
 */
public interface SubsetSumApprox {


    /**
     * Returns the residue, the absolute value of the difference between
     * the sum of the subset and the desired sum.
     *
     * @param multiset List of integers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return double - the residue
     */
    double subsetResidue(List<Integer> multiset, int sum);
}
