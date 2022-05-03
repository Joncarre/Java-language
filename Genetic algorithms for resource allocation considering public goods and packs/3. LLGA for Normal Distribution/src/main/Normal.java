package main;
import java.util.Random;

/**
 * Class Normal
 * @author J. Carrero
 *
 */
public class Normal {
	/**
	 * Generate a valid normal number
	 * @param devitation
	 * @param mean
	 * @return
	 */
	public Double generateValue(double deviation, double mean) {
		Random generator = new Random();
		double num = generator.nextGaussian();
		return num*deviation+mean;
	}
}
