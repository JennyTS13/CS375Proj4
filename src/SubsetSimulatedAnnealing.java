import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Phoebe Hughes on 4/18/2017.
 */
public class SubsetSimulatedAnnealing {
    private Random r = new Random();
    private int numRepetitions;

    public SubsetSimulatedAnnealing(int numRepetitions){
        this.numRepetitions = numRepetitions;
    }

    public void setNumRepetitions(int numRepetitions) {
        this.numRepetitions = numRepetitions;
    }

    public int subsetResidue(List<Integer> multiset, int sum){
        List<Integer> randomSubset = SubsetUtil.genRandomSubset(multiset);
        System.out.println(randomSubset);
        int smallestResidue = -1;
        for (int i = 0; i< this.numRepetitions; i++) {
            List<Integer> neighbor = SubsetUtil.genNeighbor(multiset, randomSubset);
            int neighborResidue = Math.abs(SubsetUtil.getSum(neighbor) - sum);
            int residue = Math.abs(SubsetUtil.getSum(randomSubset) - sum);
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
