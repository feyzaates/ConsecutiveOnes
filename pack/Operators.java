package pack;
import java.util.ArrayList;
import java.util.Random;
public class Operators {

    private static ArrayList<Double> rw;

    public static ArrayList<Genome> OPC(Genome parent1, Genome parent2){
        Random rnd=new Random();
        int point= rnd.nextInt(parent1.getBitSize());
        ArrayList<Genome> children = new ArrayList<>();
        Genome offspring = new Genome(parent1.getBitSize());

        for (int i = 0; i < point; i++) {
            offspring.getIndividual().add(parent1.getIndividual().get(i));
        }
        for (int i = point; i < parent1.getBitSize(); i++) {
            offspring.getIndividual().add(parent2.getIndividual().get(i));
        }
        offspring.calculateFitness();
        children.add(offspring);

        offspring=new Genome(parent1.getBitSize());

        for (int i = 0; i < point; i++) {
            offspring.getIndividual().add(parent2.getIndividual().get(i));
        }
        for (int i = point; i < parent1.getBitSize(); i++) {
            offspring.getIndividual().add(parent1.getIndividual().get(i));
        }
        offspring.calculateFitness();
        children.add(offspring);

        return children;
    }

    public static Genome Bitflip(Genome child){
        Random rnd=new Random();
        int bit;
        int randomIndex;
        for (int i = 0; i < child.getBitSize()/3; i++) {
            randomIndex=rnd.nextInt(child.getBitSize());
            bit=child.getIndividual().get(randomIndex);
            if (bit==0){
                child.getIndividual().set(randomIndex,1);
            }else{
                child.getIndividual().set(randomIndex,0);
            }
            child.calculateFitness();
        }
        return child;

    }

    public static void constructRW(Population population){
        int sum=0;
        rw=new ArrayList<>();
        for (int i = 0; i < population.getFitness().size(); i++) {
            sum=sum+population.getFitness().get(i);
        }
        rw.add((population.getFitness().get(0)/(double)sum));
        for (int i = 1; i < population.getFitness().size(); i++) {
            rw.add((population.getFitness().get(i)/(double)sum)+rw.get(i-1));
        }
    }

    public static int selectByRouletteWheel(){
        Random rnd=new Random();
        int index=0;
        double number= rnd.nextDouble();
        while (true){
            if (number<rw.get(index)){
                break;
            }
            index++;
        }
        return index;
    }

    public static Population survivalSelection(Population population){
        Population afterSelection=new Population(population.getPopulationSize());
        // elitism
        int best;
        int index;
        for (int k = 0; k < population.getPopulationSize()/10; k++) {
            best=population.getParent(0).getFitness();
            index=0;
            for (int i = 1; i < population.getIndividuals().size(); i++) {
                if (population.getParent(i).getFitness() > best){
                    best=population.getParent(i).getFitness();
                    index=i;
                }
            }
            afterSelection.setIndividuals(population.getParent(index));
            afterSelection.getFitness().add(population.getParent(index).getFitness());
            population.getIndividuals().remove(index);
            population.getFitness().remove(index);
        }

        //roulette wheel
        for (int k = 0; k < (population.getPopulationSize()*9)/10; k++) {
            Operators.constructRW(population);
            index=selectByRouletteWheel();
            afterSelection.setIndividuals(population.getParent(index));
            afterSelection.getFitness().add(population.getParent(index).getFitness());
            population.getIndividuals().remove(index);
            population.getFitness().remove(index);
        }
        return afterSelection;
    }

}
