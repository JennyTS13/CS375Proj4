/*
 * File:    RepApproxSubsetSum.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.List;

/**
 * Interface for approximation algorithms that solve the subset sum problem
 */
public interface RepApproxSubsetSum {
    public long subsetResidue(List<Long> multiset, long sum, int numRepetitions);
}
