/*
 * File:    ExactSubsetSum.java
 * Authors: Charlie Beck, Phoebe Hughes, Tiffany Lam, Jenny Lin
 * Date:    April 21, 2017
 * Project: 4
 */

import java.util.List;

/**
 * Interface for algorithms that solve the subset sum problem exactly
 */
public interface ExactSubsetSum {
    public boolean subsetExists(List<Long> multiset, int sum);
}
