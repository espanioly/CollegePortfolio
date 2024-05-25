import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";
        //too high == n+1
        NaturalNumber high = new NaturalNumber2(n);
        high.increment();// makes it n+1
        //low starts as 0
        NaturalNumber low = new NaturalNumber2();
        NaturalNumber avg = new NaturalNumber2();
        // low(0)+high/2
        avg.add(high);
        avg.divide(new NaturalNumber2(2));
        //guess now has the average of the numbers
        NaturalNumber guess = new NaturalNumber2(avg);
        //power(guess,r)
        NaturalNumber pow = new NaturalNumber2(guess);
        pow.power(r);
        //comparing the value of (pow) with (n)
        int com = pow.compareTo(n);
        //testing the difference between low and high if its within 1
        NaturalNumber test = new NaturalNumber2(low);
        test.increment();
        // if it is within 1 then if we add one
        // it shall return 0 here for com1
        int com1 = test.compareTo(high);
        if (com < 0 && com1 != 0) {
            //replacing low value
            low = new NaturalNumber2(guess);
        }
        if (com > 0) {
            // replacing high value
            high = new NaturalNumber2(guess);
        }
        // checking if its the guess is correct or its within 1
        if (com == 0 || com1 == 0) {
            n.transferFrom(guess);
        }
        //testing method
//        System.out.print(com);
//        System.out.println(com1);
        //while loop that repeats what was made above and uses interval halving method
        while (com != 0 && com1 != 0) {
            avg = new NaturalNumber2();
            // low+high/2
            avg.add(high);
            avg.add(low);
            avg.divide(new NaturalNumber2(2));
            // average has a new value now that was changed because low or high changed
            guess = new NaturalNumber2(avg);
            //power(guess,r)
            pow = new NaturalNumber2(guess);
            pow.power(r);
            //comparing the result with n
            com = pow.compareTo(n);
            //testing the difference between low and high
            test = new NaturalNumber2(low);
            test.increment();
            com1 = test.compareTo(high);
            if (com < 0 && com1 != 0) {
                low = new NaturalNumber2(guess);
            }
            if (com > 0) {
                high = new NaturalNumber2(guess);
            }
            if (com == 0 || com1 == 0) {
                n.transferFrom(guess);
//                System.out.print(com);
//                System.out.println(com1);
            }
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }
        //extra hehe
        out.println("GoodBye!");

        out.close();
    }

}