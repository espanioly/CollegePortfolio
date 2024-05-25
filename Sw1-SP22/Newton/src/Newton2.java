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
public final class Newton2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton2() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        final double e = 0.0001;
        double r = 1.0; // r is 1 as a start
        while ((Math.abs(r * r - x) / x) >= e * e) {
            r = (r + x / r) / 2;
        }
        if (x == 0) {
            return 0;
        } else {
            return r;
        }

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
        String yes = "y"; //yes or no variable
        while (yes.equals("y")) {
            out.println("Enter a number to be square-rooted: ");
            root0 = in.nextDouble(); //user enters number here
            out.println(sqrt(root0)); // method is called
            // ask user for repeating or not
            out.print("Please enter 'y' to continue ");
            yes = in.nextLine();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
