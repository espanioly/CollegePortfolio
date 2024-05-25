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
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double r, double e) {
        //double e = 0.0001; this is no longer used in here
        if (x != 0) {// added an if statement that will skip the code
            while ((Math.abs(r - (x / r)) > e)) {
                r = r + e;
            }
            x = .5 * (r + (x / r));
        } else {
            x = 0; // and give the answer as 0
        }
        return x; // then return 0 as an answer for this method
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
        out.println(
                "In this program we are going to compute and square-root a number using the Newton Iteration method and see if it's accurate");
        double root0 = 0; //defining x number
        double root1 = 1.0; // this is r
        out.print("Enter a number for e: ");
        double root2 = in.nextDouble(); //user enters number e here
        out.println(); // new line
        String or = "y"; //yes or no variable
        while (or.equals("y")) { // checks if user wants to use the program
            out.print("Enter a number to be square-rooted: ");
            root0 = in.nextDouble(); //user enters number x here
            out.println(); // new line in a neater way

            out.println(sqrt(root0, root1, root2)); // e gets computed in the method
            out.print("Please enter 'y' to continue "); // confirms that the users wants to keep going
            or = in.nextLine();
            out.println();

        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
