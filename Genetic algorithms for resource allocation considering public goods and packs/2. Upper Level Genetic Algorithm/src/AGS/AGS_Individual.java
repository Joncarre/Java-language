package AGS;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import AGI.AGI_Engine;
import files.CustomReadFile;
import files.CustomWriteFile;

/**
 * Class AGS_Individual
 * @author J. Carrero
 *
 */
public class AGS_Individual {
    static int defaultGeneLength = 10;
    private Vector<Double> genes = new Vector<Double>(defaultGeneLength);
    private int[] solution = new int[defaultGeneLength];
    private double fitness = 0;
    private double promising = Double.MAX_VALUE;
    
	private CustomReadFile readFile;
	private CustomWriteFile writeFile;
	Scanner sc;
    
    /**
     * Create random individual
     * @param index
     * @param numUsers 
     * @throws IOException 
     */
    public void generateIndividual(int index, int numUsers) throws IOException {
		String text = "";
		this.writeFile = new CustomWriteFile("SGA_indiv" + index + ".txt");
        for (int i = 0; i < this.defaultGeneLength; i++)
            this.genes.add((Double)((Math.random() * 99) + 1));
        
        formalizePreferences();
        
        for(int j = 0; j < this.defaultGeneLength; j++) 
			text += this.genes.get(j) + " "; 
        
		text += -1;
		this.writeFile.writeVector(this.writeFile, text);
		this.writeFile.closeWriteFile(this.writeFile);
	}
    
    /**
     * Read individual from file
     * @param index
     * @param numUsers
     * @return
     * @throws IOException
     */
	public Vector<Double> readIndividual(int index, int numUsers) throws IOException {
    	this.readFile = new CustomReadFile("SGA_indiv" + index + ".txt");
    	this.sc = new Scanner(this.readFile);
    	this.genes = this.readFile.readVector(sc);
    	return this.genes;
	}
	
    /**
     * Create individuals with different gene lengths
     * @param length
     */
    public void setDefaultGeneLength(int length) {
        AGS_Individual.defaultGeneLength = length;
    }
    
    /**
     * get Gene
     * @param index
     * @return
     */
    public Double getGene(int index) {
        return this.genes.get(index);
    }
    
    /**
     * get Vector of genes
     * @return
     */
    public Vector<Double> getGenes(){
    	return this.genes;
    }
    
    public int[] getSolution() {
    	return this.solution;
    }
    
    /**
     * set Gene
     * @param index
     * @param gene
     */
    public void setGene(int index, Double gene) {
        this.genes.add(index, gene);
    }
    
    /**
     * set Promising
     * @param promising
     */
    public void setPromising(double promising) {
    	this.promising = promising;
    }
    
    /**
     * set solution
     * @param _solution
     */
    public void setSolution(int[] _solution) {
    	this.solution = _solution;
    }
    
    /**
     * set Fitness
     * @param fitness
     */
    public void setFitness(double fitness) {
    	this.fitness = fitness;
    }
    
    /**
     * get Promising
     * @return
     */
    public double getPromising() {
    	return this.promising;
    }
    
    /**
     * get solution as a string
     * @return
     */
    public String getSolutionString() {
    	String text = "";
    	for(int i = 0; i < this.solution.length; i++) {
    		text += this.solution[i] + " ";
    	}
    	return text;
    }
    
    /**
     * Change i gene
     * @param i
     * @param gene
     */
	public void changeGene(int index, Double gene) {
		this.genes.set(index, gene);
	}

    /**
     * Get genes length
     * @return
     */
    public int size() {
        return genes.size();
    }
    
    /**
     * Get Fitness value
     * @return
     * @throws IOException 
     */
    public double getFitness(int i, AGI_Engine engine) throws IOException {
        if (fitness == 0)
            fitness = AGS_FitnessCalc.getFitness(engine, this, i);
        return fitness;
    }
    
    /**
     * Returns only fitness (without calculate it)
     * @return
     */
    public double getOnlyFitness() {
    	return this.fitness;
    }
    
    /**
     * This method formalizes the preferences by making them all add a number n (in our case it will be 100)
     */
    public void formalizePreferences() {
    	double aux = 0;
    	for(int i = 0; i < this.genes.size(); i++)
    		aux += this.genes.get(i);
    	aux = 100/aux;
    	for(int j = 0; j < this.genes.size(); j++)
    		this.genes.set(j, this.getGene(j)*aux);
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i) + " ";
        }
        return geneString;
    }
}