import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Phoebe Hughes on 4/18/2017.
 */
public class SubsetSimulatedAnnealing implements SubsetSumApprox {
    private Random r = new Random();

    public List<Integer> getNeighbor(List<Integer> originalSet, List<Integer> s){
        List<Integer> t = new ArrayList<>();
        t.addAll(s);

        int i = r.nextInt(originalSet.size());
        int j = r.nextInt(originalSet.size());
        while (j == i){
            j = r.nextInt(originalSet.size());
        }

        if (s.contains(originalSet.get(i))){
            t.remove(originalSet.get(i));
        }
        else{
            t.add(originalSet.get(i));
        }

        if(s.contains(originalSet.get(j))){
            if (r.nextInt(100)<50){
                t.remove(originalSet.remove(j));
            }
        }
        else{
            if (r.nextInt(100)<50){
                t.add(originalSet.remove(j));
            }
        }

        return t;

    }

    public int subsetResidue(List<Integer> multiset, int sum){

        return 0;
    }

    public static void main(String[] args){
        SubsetSimulatedAnnealing subsetSimulatedAnnealing = new SubsetSimulatedAnnealing();
        List<Integer> S = Arrays.asList(1, 3, 9, 2);
        int residue = subsetSimulatedAnnealing.subsetResidue(S, 5);
        System.out.println("Residue: " + residue);
    }

}
