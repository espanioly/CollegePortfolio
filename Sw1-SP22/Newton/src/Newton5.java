//import components.simplereader.SimpleReader;
//import components.simplereader.SimpleReader1L;
//import components.simplewriter.SimpleWriter;
//import components.simplewriter.SimpleWriter1L;
//
///**
// * Put a short phrase describing the program here.
// *
// * @author Sam Espanioly
// *
// */
//public final class Newton5 {
//
//    /**
//     * Private constructor so this utility class cannot be instantiated.
//     */
//    private Newton5() {
//    }
//
//    /**
//     * Computes estimate of square root of x to within relative error e.
//     *
//     * @param x
//     *            positive number to compute square root of
//     * @param e
//     *            variable that represents the percentage of error
//     * @return estimate of square root
//     */
//    private static double sqrt(double x, double e) {
//        double r = 1.0; // r is 1 as a start
//        while ((Math.abs(r * r - x) / x) >= e * e) {
//            r = (r + x / r) / 2;
//        }
//        if (x == 0) {
//            return 0;
//        } else {
//            return r;
//        }
//
//    }
//
//    /**
//     * Main method.
//     *
//     * @param args
//     *            the command line arguments
//     */
//    public static void main(String[] args) {
//        SimpleReader in = new SimpleReader1L();
//        SimpleWriter out = new SimpleWriter1L();
//        /*
//         * Put your main program code here; it may call myMethod as shown
//         */
//        final double i100 = 100; // final value for 100
//        // prompting the user to enter a value for e/error
//        out.println("Enter percentage of error: ");
//        double e = in.nextDouble(); //user enters number here
//        e = e / i100; // to make the value into a percentage
//        // prompting for the value of x to be iterated
//        out.println("Enter a number to be square-rooted: ");
//        double root0 = in.nextDouble(); //user enters the number to be computed here
//        while (root0 >= 0) {
//            out.println(sqrt(root0, e)); // method is called
//            // ask user for repeating or not
//            out.print(
//                    "Please enter another number to be computed, if the value "
//                            + "is negative the program will quit. ");
//            root0 = in.nextDouble();
//        }
//        /*
//         * Close input and output streams
//         */
//        in.close();
//        out.close();
//    }
//
//}
