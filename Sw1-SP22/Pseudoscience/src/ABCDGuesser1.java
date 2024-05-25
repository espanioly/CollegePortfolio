import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Program that can be used to disprove the "charming theory" by finding μ, w,
 * x, y, and z such that the best approximation of μ results in a relative error
 * that is greater than 1%.
 *
 * @author Sam Espanioly
 */
public final class ABCDGuesser1 {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private ABCDGuesser1() {
        // no code needed here
    }

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
        double pDouble = 0;
        out.print("Please enter a positive number that is not equal to one: ");
        String temp = in.nextLine();
        // check if its a double
        boolean testTemp = FormatChecker.canParseDouble(temp);
        if (testTemp) {
            // put it into a double variable
            pDouble = Double.parseDouble(temp);
            // in case that number is a negative number
            if (pDouble < 0) {
                out.println("Try again!");
                pDouble = getPositiveDoubleNotOne(in, out);
            }
            // else recall method
        } else {
            out.println("Try again!");
            pDouble = getPositiveDoubleNotOne(in, out);
        }
        return pDouble;
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
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double notOne = 0;
        out.print("Please enter a positive number that is not equal to one: ");
        String temp = in.nextLine();
        // check if its a double
        boolean testTemp = FormatChecker.canParseDouble(temp);
        if (testTemp) {
            // put it into a double variable
            notOne = Double.parseDouble(temp);
            // in case that number is 1 or a negative number
            if (notOne == 1.0 || notOne < 0) {
                out.println("Try again!");
                notOne = getPositiveDoubleNotOne(in, out);
            }
        } else { //recalls method
            out.println("Try again!");
            notOne = getPositiveDoubleNotOne(in, out);
        }

        return notOne;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        //adding the values for the charm theory
        final double[] charmDigits = { -5, -4, -3, -2, -1, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1,
                2, 3, 4, 5 };
        // final length for the array
        final int charmLength = 17;
        out.println("Welcome to the anti-charm-theory program. Let's start with"
                + " a value for μ then after that the 4 crazy numbers. ");
        // μ value
        double u = getPositiveDouble(in, out);
        // the four numbers that we need
        out.println("Be creative with your 4 numbers! ");
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);
        // 4 values for each iteration to find the best estimate
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;

        //starting estimate
        double bestEstimation = 0;
        // took me a while to find all of the logical errors
        // this would have been easier if we used for loops
        while (a < charmLength) {
            //w^a
            double num1 = Math.pow(w, charmDigits[a]);
            a++;
            // reset value for b
            b = 0;
            while (b < charmLength) {
                //x^b
                double num2 = Math.pow(x, charmDigits[b]);
                b++;
                // reset value for c
                c = 0;
                while (c < charmLength) {
                    //y^c
                    double num3 = Math.pow(y, charmDigits[c]);
                    c++;
                    // reset value for d
                    d = 0;
                    while (d < charmLength) {
                        //z^d
                        double num4 = Math.pow(z, charmDigits[d]);
                        // now we multiply wxyz
                        double newU = num1 * num2 * num3 * num4;
                        // here we check for the best value after every 4th
                        //iteration and if the previous value is closer then
                        // the program doesn't save the value but if it is closer
                        // then this iff statement does save the value.
                        if (Math.abs(u - newU) < Math.abs(u - bestEstimation)) {
                            bestEstimation = newU;
                        }
                        d++;
                    }
                }
            }
        }
        out.println("The best estimate this program came up with is "
                + bestEstimation);

        final double percent = 100.0;
        final int hundrethOfOnePercent = 5;
        double error = bestEstimation / u * percent;
        out.print("Margin of error is approximatly ");
        // then write it in the nearest hundredth of one percent format
        out.print((error - percent), hundrethOfOnePercent, false);
        out.println("%.");

        out.println();
        out.close();
    }

}
