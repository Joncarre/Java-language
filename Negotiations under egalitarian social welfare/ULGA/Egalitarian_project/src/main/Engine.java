package main;

import java.io.IOException;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.ThreadInfo;
import java.util.Scanner;
import java.util.Vector;

import javax.management.openmbean.OpenDataException;

public class Engine {
	private final int avgIterations = 80; // Average Iterations
	private final int generations = 8000; // Evolutions of population
	private final int popSize = 40; // Population Size
	private final int numUsers = 10; // Number of agents
	private int num_apply = 100; // Iterations to calculate the average
	private Vector<Double> vec = new Vector<Double>(num_apply);
	private FitnessCalc fitness = new FitnessCalc(this.numUsers);
	private Population myPop;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * This method controls the flow of execution
	 * @throws IOException
	 */
	public void start() throws IOException {	
		System.out.println("    (1) Read data \n" + "    (2) Gerenate new data");
		int op = this.sc.nextInt();
		if(op == 1) {
			System.out.println("Do you wanna analyze the data? \n" +  "    (1) Yes \n" +  "    (2) No");
			op = this.sc.nextInt();
			if(op == 1)
				readPopulation();
			else
				System.out.println("Bye!");
		}else 
			System.out.println("No puede generarse población.");
	}
	
	/**
	 * Read population from file and execute IGA
	 * @throws IOException
	 */
	public void readPopulation() throws IOException {
		double finalFitness = 0.0;
		for(int i = 0; i < num_apply; i++) { 
			this.fitness.resetValues();
			this.fitness.readOPreferences();
			this.fitness.readPlusMPreferences(i); // Lee las instancias de las preferencias ya desviadas
			
			//this.fitness.readMPreferences();
			//double deviation = 0.0;
			//this.fitness.applyNormal(deviation);
			//this.fitness.writePreferences(i);
			
			
			for(int j = 0; j < avgIterations; j++) { // Loop to select our best egalitarian result
		    	this.myPop = new Population(this.popSize, this.numUsers, false);
		    	int generationCount = 0;
				while (generationCount < this.generations) {
			        generationCount++;
					//System.out.println("Iteration: " + generationCount + " Fitness(lowest): " + myPop.getIndividual(0).getOnlyFitness()  + " Genes: " + myPop.getIndividual(0).toString());
			        this.myPop = Algorithm.evolvePopulation(this.myPop, this.popSize);
				}
				this.fitness.saveIndividuals(myPop.getIndividual(0));
			} 
			double bestFitness = this.fitness.updateBestFitness();
			vec.add(bestFitness);
		    //System.out.println("Ite: " + i + " Utility: " + bestFitness + " Genes: " + this.fitness.getBestIndividual().toString());
			System.out.println(bestFitness + " " + this.fitness.getBestIndividual().toString());
	    	finalFitness += bestFitness;
	    	
		}
		double max = 0.0;
		for(int i = 0; i < vec.size(); i++){
			if(vec.get(i) > max)
				max = vec.get(i);
		}
    	System.out.println("---------- Max ----------");
    	System.out.println(max);
    	System.out.println("---------- Average ----------");
    	System.out.println(finalFitness/this.num_apply);
	}
}

