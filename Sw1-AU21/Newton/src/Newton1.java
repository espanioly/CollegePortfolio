import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Sam Espanioly
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double r) {
        double e = 0.0001;
        while ((Math.abs(r - (x / r)) > e)) {
            r = r + e;
        }
        x = .5 * (r + (x / r));
        return x;
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        double root0 = 0; //defining x number
        double root1 = 1.0; // this is r
        String or = "y"; //yes or no variable
        while (or.equals("y")) {
            out.println("Enter a number to be square-rooted: ");
            root0 = in.nextDouble(); //user enters number x here
            out.println(sqrt(root0, root1));
            out.println("Please enter 'y' to continue ");
            or = in.nextLine();

        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
