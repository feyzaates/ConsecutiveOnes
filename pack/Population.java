package pack;

import java.util.ArrayList;

public class Population {
    private ArrayList<Genome> individuals;
    private ArrayList<Integer> fitness;
    private int populationSize;


    public Population(int population_size) {
        populationSize=population_size;
        individuals = new ArrayList<>();
        fitness = new ArrayList<>();

    }

    public void setIndividuals(Genome parent) {
        individuals.add(parent);
    }

    public ArrayList<Genome> getIndividuals() {
        return individuals;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void randomFilling(int bitSize){

        for (int i = 0; i < populationSize; i++) {
            Genome individual = new Genome(bitSize);
            individual.fillIndividualRandomly();
            individuals.add(individual);
        }
    }

    public Genome getParent(int index){
        return individuals.get(index);
    }


    public void setChild(ArrayList<Genome> offspring) {
        individuals.addAll(offspring);
    }

    public void fillingFitness(){
        for (int i = 0; i < populationSize; i++) {
            fitness.add(individuals.get(i).calculateFitness());
        }
    }

    public void setFitness(int index){
        fitness.add(individuals.get(index).calculateFitness());
    }

    public ArrayList<Integer> getFitness() {
        return fitness;
    }

    public void print(){
        System.out.println("population:");
        for (int i = 0; i < individuals.size(); i++) {
            System.out.print(individuals.get(i).getIndividual());
        }
        System.out.println("\nfitness: "+fitness);
    }
}
