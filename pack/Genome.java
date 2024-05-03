package pack;

import java.util.ArrayList;
import java.util.Random;

public class Genome {
    private ArrayList<Integer> individual;
    private int fitness;
    private int bitSize;

    public Genome(int numberOfBits) {
        bitSize=numberOfBits;
        individual=new ArrayList<>();
    }

    public int getBitSize() {
        return bitSize;
    }

    public ArrayList<Integer> getIndividual() {
        return individual;
    }

    public int getFitness() {
        return fitness;
    }

    public void fillIndividualRandomly(){
        Random rnd = new Random();
        for (int i = 0; i < bitSize; i++) {
            individual.add(rnd.nextInt(2));
        }
    }

    public int calculateFitness(){
        int fitnessVal=0;
        int count=0;
        int k;
        for (int i = 0; i < bitSize; i++) {
            if (individual.get(i)==1){
                count=1;
                k=i+1;
                while (k<bitSize){
                    if (individual.get(k)==1){
                        k++;
                        count++;
                    }else{
                        break;
                    }
                }
            }
            if (fitnessVal<count){
                fitnessVal=count;
            }
        }
        this.fitness=fitnessVal;
        return fitnessVal;
    }
}
