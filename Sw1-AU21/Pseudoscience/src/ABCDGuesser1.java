import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This script is a simulator of the Charming Theory
 *
 * @author Sam Espanioly
 *
 */
public final class ABCDGuesser1 {

	/**
	 * Repeatedly asks the user for a positive real number until the user enters
	 * one. Returns the positive real number.
	 *
	 * @param in
	 *            the input stream
	 * @param out
	 *            the output stream
	 * @return a positive real number entered by the user
	 */
	private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
		out.println("Please enter a positive real number: ");
		String number = in.nextLine();
		double i =  0;
		if (FormatChecker.canParseDouble(number)) {
		i = Double.parseDouble(number);
		}
		while (i <= 0) {
			out.println("Please enter a positive real number: ");
			number = in.nextLine();
			i =  0;
			if (FormatChecker.canParseDouble(number)) {
			i = Double.parseDouble(number);
			}
		}
	return i;
	}
	
	/**
	 * Repeatedly asks the user for a positive real number not equal to 1.0
	 * until the user enters one. Returns the positive real number.
	 *
	 * @param in
	 *            the input stream
	 * @param out
	 *            the output stream
	 * @return a positive real number not equal to 1.0 entered by the user
	 */
	private static double getPositiveDoubleNotOne(SimpleReader in, SimpleWriter out) {
		out.println("Please enter a value for mu (positive real number that is not 1): ");
		String number = in.nextLine();
		double i =  0;
		if (FormatChecker.canParseDouble(number)) {
		i = Double.parseDouble(number);
		}
		while (i <= 0 && i != 1) {
			out.println("Please enter a positive real number that is NOT 1: ");
			number = in.nextLine();
			i =  0;
			if (FormatChecker.canParseDouble(number)) {
			i = Double.parseDouble(number);
			}
		}
		return i;
	}


    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        double [] charming = {-5, -4, -3, -2, -1, -1/2, -1/3, -1/4, 0, 1/4, 1/3, 1/2, 1, 2, 3, 4, 5};
        int i = 0;
        double w = getPositiveDoubleNotOne(in, out); // value for w
        double x = getPositiveDoubleNotOne(in, out); // value for x
        double y = getPositiveDoubleNotOne(in, out); // value for y
        double z = getPositiveDoubleNotOne(in, out); // value for z
        double u = getPositiveDouble(in, out); // value for mu
        double a = 0.0;
        while (i < charming.length) {
        	a = charming[i];
        	double n1 = (Math.pow(w, a));
        	++i;
        	
        	int k = 0;
        	double b = 0.0;
        	while (k < charming.length) {
        		b = charming[k];
        		double n2 = (Math.pow(y, b));
        		++k;
        		
        		int j = 0;
        		double c = 0.0;
        		while (j < charming.length) {
        			c = charming[j];
        			double n3 = (Math.pow(x, c));
        			++j;
        			
        			int l = 0;
        			double d = 0.0;
        			while (l < charming.length) {
        				d = charming[l];
        				double n4 = (Math.pow(z, d));
        				++l;
        			}
        		}
        	}
        }
        double finalValue = n1 * n2 * n3 * n4;
        double percentValue = (finalValue / u) % 1;
        out.println("The code got the value as " + finalValue
        + " which is within "
        + percentValue + " of the actual number you picked!");
        // PS i couldn't run the program because I kept getting Error: Could
        //not find or load main class ABCDGuesser1
        //Caused by: java.lang.ClassNotFoundException:
        //ABCDGuesser1
        //so i filled this up while making sure everything is working
        //according to my logic and eclipse
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
