package pack;

import java.util.ArrayList;
import java.util.Random;

public class GA {

    private int numberOfIteration;
    private int bitSize; // n
    private int populationSize; // m
    private Genome best;


    public GA(int numberOfIteration, int bitSize, int populationSize) {
        this.numberOfIteration = numberOfIteration;
        this.bitSize = bitSize;
        this.populationSize = populationSize;
    }

    public void run(){
        Random rnd = new Random();
        int random;

        // initialize the random population
        Population population=new Population(populationSize);
        population.randomFilling(bitSize);
        population.fillingFitness();
        System.out.println("\nBEFORE GA");
        //population.print();

        // update the best solution
        best=new Genome(bitSize);
        best=population.getParent(0);
        for (int i = 1; i < populationSize; i++) {
            if (best.getFitness()<population.getParent(i).getFitness()){
                best=population.getParent(i);
            }
        }
        System.out.println("best fitness for the initial population: "+best.getFitness()
                +" and the genome is: "+best.getIndividual());

        // Iteration
        for (int i = 0; i < numberOfIteration; i++) {
            Operators.constructRW(population);
            ArrayList<Genome> offspring=new ArrayList<>();
            for (int j = 0; j < populationSize/2; j++) { //

                // Mating Selection
                Genome parent1=population.getParent(Operators.selectByRouletteWheel());
                Genome parent2=population.getParent(Operators.selectByRouletteWheel());
                ArrayList<Genome> children;

                // Crossover probability
                random=rnd.nextInt(100);
                if (random<70){
                    children = Operators.OPC(parent1,parent2);
                }else{
                    children=new ArrayList<>();
                    children.add(parent1);
                    children.add(parent2);
                }

                // Mutation probability
                random= rnd.nextInt(100);
                if (random<30){
                    Operators.Bitflip(children.get(0));
                    Operators.Bitflip(children.get(1));
                }

                // Update the best and extend the offspring
                for (int k = 0; k < children.size(); k++) {
                    offspring.add(children.get(k));
                    offspring.get(k).calculateFitness();
                    if (children.get(k).getFitness() > best.getFitness()){
                        best=children.get(k);
                    }
                }
            }

            //combine all
            population.setChild(offspring);
            for (int p = 0; p < offspring.size() ; p++) {
                population.setFitness(offspring.size()+p);
            }

            //survival selection
            population=Operators.survivalSelection(population);

        }
        System.out.println("\nAFTER GA");
        //population.print();
        System.out.println("best fitness for the population: "+best.getFitness()
                +" and the genome is: "+best.getIndividual());

    }

}
