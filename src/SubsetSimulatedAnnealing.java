import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Phoebe Hughes on 4/18/2017.
 */
public class SubsetSimulatedAnnealing implements SubsetSumApprox {
    private Random r = new Random();
    private int numRepetitions;

    public SubsetSimulatedAnnealing(int numRepetitions){
        this.numRepetitions = numRepetitions;
    }

    public void setNumRepetitions(int numRepetitions){
        this.numRepetitions = numRepetitions;
    }


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
                t.remove(originalSet.get(j));
            }
        }
        else{
            if (r.nextInt(100)<50){
                t.add(originalSet.get(j));
            }
        }

        return t;

    }

    public List<Integer> getRandomSubset(List<Integer> multiset){
        List<Integer> subset = new ArrayList<>();
        for (Integer item: multiset){
            if (r.nextInt(100) < r.nextInt(100)){
                subset.add(item);
            }
        }

        return subset;
    }

    public int getSum(List<Integer> subset){
        int sum = 0;
        for (Integer i : subset){
            sum += i;
        }
        return sum;
    }

    public int subsetResidue(List<Integer> multiset, int sum){
        List<Integer> randomSubset = this.getRandomSubset(multiset);
        System.out.println(randomSubset);
        int smallestResidue = -1;
        for (int i = 0; i< this.numRepetitions; i++) {
            List<Integer> neighbor = this.getNeighbor(multiset, randomSubset);
            int neighborResidue = Math.abs(this.getSum(neighbor) - sum);
            int residue = Math.abs(this.getSum(randomSubset) - sum);
            if (neighborResidue >= residue){  //TODO: 10000000000 is too large an int... ask Dale
                double T = (neighborResidue - residue)/ (1000000000 * Math.pow(0.8,(i/300)));
                if ( r.nextDouble() > Math.pow(Math.E, -T)){
                    randomSubset = neighbor;
                }
            }
            else{ // neighborResidue < residue
                randomSubset = neighbor;
            }

            if (smallestResidue < 0 ){
                if (neighborResidue < residue){
                    smallestResidue = neighborResidue;
                }
                else{
                    smallestResidue = residue;
                }
            }
            else if (neighborResidue < smallestResidue){
                smallestResidue = neighborResidue;
            }
        }

        return smallestResidue;
    }

    public static void main(String[] args){
        SubsetSimulatedAnnealing subsetSimulatedAnnealing = new SubsetSimulatedAnnealing(100);
        List<Integer> S = Arrays.asList(1, 3, 9, 2, 7, 34, 8, 2, 45, 4);
        int residue = subsetSimulatedAnnealing.subsetResidue(S, 11);
        System.out.println("Residue: " + residue);
    }

}
