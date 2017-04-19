import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Phoebe Hughes on 4/18/2017.
 */
public class SubsetUtil {

    public static List<Integer> getNeighbor(List<Integer> originalMultiSet, List<Integer> subset){
        Random r = new Random();
        List<Integer> t = new ArrayList<>();
        t.addAll(subset);

        int i = r.nextInt(originalMultiSet.size());
        int j = r.nextInt(originalMultiSet.size());
        while (j == i){
            j = r.nextInt(originalMultiSet.size());
        }

        if (subset.contains(originalMultiSet.get(i))){
            t.remove(originalMultiSet.get(i));
        }
        else{
            t.add(originalMultiSet.get(i));
        }

        if(subset.contains(originalMultiSet.get(j))){
            if (r.nextInt(100)<50){
                t.remove(originalMultiSet.get(j));
            }
        }
        else{
            if (r.nextInt(100)<50){
                t.add(originalMultiSet.get(j));
            }
        }

        return t;

    }

    public static List<Integer> getRandomSubset(List<Integer> multiset){
        Random r = new Random();

        List<Integer> subset = new ArrayList<>();
        for (Integer item: multiset){
            if (r.nextInt(100) < r.nextInt(100)){
                subset.add(item);
            }
        }

        return subset;
    }

    public static int getSum(List<Integer> multiset){
        int sum = 0;
        for (Integer i : multiset){
            sum += i;
        }
        return sum;
    }
}
