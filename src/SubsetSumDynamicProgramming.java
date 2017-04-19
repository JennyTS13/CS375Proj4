import java.util.Arrays;
import java.util.List;

/**
 * Created by Phoebe Hughes on 4/18/2017.
 */
public class SubsetSumDynamicProgramming implements SubsetSumExact {

    public boolean subsetExists(List<Integer> multiset, int sum){
        int sumMultiset = SubsetUtil.getSum(multiset);
        boolean[][] q = new boolean[multiset.size()][sumMultiset +1];
        for (int j = 0; j< sumMultiset +1; j++) {
            q[0][j] = (multiset.get(0) == j);
        }

        for (int i = 1; i<multiset.size(); i ++){
            for (int j = 0; j< sumMultiset +1 ; j++) {
                q[i][j] = ((q[i - 1][j]) || (multiset.get(i) == j));
                if (j - multiset.get(i) >= 0) {
                    q[i][j] = q[i][j] || (q[i - 1][j - multiset.get(i)]);
                }
            }
        }

        return  q[multiset.size()-1][sum];
    }

    public static void main(String[] args){
        SubsetSumDynamicProgramming subsetSumDynamicProgramming = new SubsetSumDynamicProgramming();
        List<Integer> S = Arrays.asList(1, 3, 9, 2);
        System.out.println("Result: " + subsetSumDynamicProgramming.subsetExists(S, 5));
    }
}