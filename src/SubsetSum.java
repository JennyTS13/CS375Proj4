/*
 * File:    SubsetSum.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.List;

/**
 * An interface used for the Subset Sum problem
 */
public interface SubsetSum {

    /**
     * Returns boolean indicating if there exists a subset of the set S where
     * the sum of its elements is equal to a specified sum k.
     *
     * @param multiset List of intergers in the multiset S
     * @param sum int indicating the specified sum k
     *
     * @return boolean indicating if such a subset exists
     */
    boolean subsetExists(List<Integer> multiset, int sum);
}
