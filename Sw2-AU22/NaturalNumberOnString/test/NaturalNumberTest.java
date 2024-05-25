import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Aaron Heishman, Sam Espanioly
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     *
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     *
     * @return the new number
     *
     * @requires i >= 0
     *
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     *
     * @return the new number
     *
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     *
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     *
     * @return the new number
     *
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     *
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     *
     * @return the new number
     *
     * @requires i >= 0
     *
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     *
     * @return the new number
     *
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     *
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     *
     * @return the new number
     *
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    //TESTS

    /**
     * Testing the constructor with an empty argument.
     *
     * @Challenging empty entry
     */
    @Test
    public void testConstructor() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(nExpected, n);
    }

    /**
     * Testing constructor with a String.
     *
     * @Boundary
     */
    @Test
    public void testConstructorWithString() {
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber nExpected = this.constructorRef("1");
        assertEquals(nExpected, n);
    }

    /**
     * Testing the constructor with a string of large numbers.
     *
     * @Boundary
     */
    @Test
    public void testConstructorWithLargeString() {
        NaturalNumber n = this
                .constructorTest("9999999999999999999999999999999999999999");
        NaturalNumber nExpected = this
                .constructorRef("9999999999999999999999999999999999999999");
        assertEquals(nExpected, n);
    }

    /**
     * Testing constructor with an int.
     *
     * @Boundary
     */
    @Test
    public void testConstructorWithInt() {
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);
        assertEquals(nExpected, n);
    }

    /**
     * Test constructor with multiple 0 ints.
     *
     * @Challenging trying to break it with a bunch of zeros
     */
    @Test
    public void testConstructorWithInt0000000() {
        NaturalNumber n = this.constructorTest(0000000);
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(nExpected, n);
    }

    /**
     * Test constructor with an argument that is a String containing the value 0
     *
     * @Routine
     */
    @Test
    public void testConstructorWithStrings() {
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef();
        assertEquals(nExpected, n);
    }

    /**
     * Test constructor with the max int value.
     *
     * @Boundary
     */
    @Test
    public void testConstructorWithMaxInt() {
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);
        assertEquals(nExpected, n);
    }

    /**
     * Testing constructor with a natural number.
     *
     * @Routine
     */
    @Test
    public void testConstructorWithNaturalNumber() {
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber n2 = this.constructorRef(n);
        NaturalNumber nExpected = this.constructorRef(1);
        assertEquals(nExpected, n2);
    }

    /**
     * Testing multiplyBy10 with an empty constructor.
     *
     * @Routine
     */
    @Test
    public void testEmptyNNMultiplyBy10() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(1);
        n.multiplyBy10(1);
        assertEquals(nExpected, n);
    }

    /**
     * Testing multiplyBy10 with empty test constructor.
     *
     * @Routine
     */
    @Test
    public void testEmptyNNMultiplyBy10WithString() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef("1");
        n.multiplyBy10(1);
        assertEquals(nExpected, n);
    }

    /**
     * Test multiplyBy10 with String.
     *
     * @Routine
     */
    @Test
    public void testNNMultiplyBy10String() {
        NaturalNumber n = this.constructorTest("10");
        NaturalNumber nExpected = this.constructorRef("105");
        n.multiplyBy10(5);
        assertEquals(nExpected, n);
    }

    /**
     * Test multiplyBy10 with a large string.
     *
     * @Boundary
     */
    @Test
    public void testNNMultiplyBy10WithLargeString() {
        NaturalNumber n = this.constructorTest("123456789123456789123456789");
        NaturalNumber nExpected = this
                .constructorRef("1234567891234567891234567891");
        n.multiplyBy10(1);
        assertEquals(nExpected, n);
    }

    /**
     * Test multiplyBy10 using natural number on a new instance of NaturalNumber
     * that takes another NaturalNumber in as an argument.
     *
     * @Boundary
     */
    @Test
    public void testEmptyNNMultiplyBy10WithNN() {
        NaturalNumber n = this.constructorTest();
        n.multiplyBy10(1);
        NaturalNumber n1 = this.constructorRef(n);
        NaturalNumber nExpected = this.constructorRef(1);
        assertEquals(nExpected, n1);
    }

    /**
     * Tests multiplyBy10 with a NaturalNumber value of 1.
     *
     * @Routine
     */
    @Test
    public void testNNMultiplyBy10WithInteger() {
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(15);
        n.multiplyBy10(5);
        assertEquals(nExpected, n);
    }

    /**
     * tests multiplyBy10 with a NaturalNumber of value 12345678.
     *
     * @Routine
     */
    @Test
    public void testNNMultiplyBy10WithBigInteger() {
        NaturalNumber n = this.constructorTest(1234578);
        NaturalNumber nExpected = this.constructorRef(12345789);
        n.multiplyBy10(9);
        assertEquals(nExpected, n);
    }

    /**
     * Tests multiplyBy10 with a NaturalNumber value of Integer.MAX_VALUE.
     *
     * @Boundary
     */
    @Test
    public void testNNMultiplyBy10WithMaxInteger() {
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this
                .constructorRef(Integer.toString(Integer.MAX_VALUE) + "1");
        n.multiplyBy10(1);
        assertEquals(nExpected, n);
    }

    /**
     * Tests multiplyBy10 with a NaturalNumber that has been created with the
     * value of 50 in another NaturalNumber and testing that the original
     * NaturalNumber is not updated.
     *
     * @Routine
     */
    @Test
    public void testNNMultiplyBy10WithNN() {
        NaturalNumber n = this.constructorTest(50);
        NaturalNumber n1 = this.constructorTest(n);
        NaturalNumber nExpected = this.constructorRef(50);
        NaturalNumber n1Expected = this.constructorTest(500);
        n1.multiplyBy10(0);
        assertEquals(nExpected, n);
        assertEquals(n1Expected, n1);
    }

    /**
     * Test multiplyBy10 with a NaturalNumber that holds a large value.
     *
     * @Boundary + Challenging
     */
    @Test
    public void testNNMultiplyBy10WithLargeNN() {
        NaturalNumber n = this.constructorTest("10000000000000000000000");
        NaturalNumber n1 = this.constructorTest(n);
        NaturalNumber nExpected = this
                .constructorTest("100000000000000000000001");
        n1.multiplyBy10(1);
        assertEquals(nExpected, n1);
    }

    /**
     * Test multiplyBy10 with a loop.
     *
     * @Routine + @Challenging using a loop
     */
    @Test
    public void testMultiplyBy10WithLoop() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef("1234567890");
        int i = 1;
        while (n.compareTo(nExpected) != 0) {
            if (i < 10) {
                n.multiplyBy10(i);
                i++;
            } else {
                n.multiplyBy10(0);
            }
        }
        assertEquals(nExpected, n);
    }

    /**
     * Test multiplyBy10 with a loop and uses compareTo with the ref natural
     * number to make sure all the right numbers are being multiplied.
     *
     * @Challenging using a loop
     */
    @Test
    public void testMultiplyBy10Loop() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nLoopVal = this.constructorRef();
        NaturalNumber nExpected = this.constructorRef("1234567890");
        boolean nLoopValIsSame = true;
        boolean nLoopValExpected = true;
        int i = 1;
        while (nLoopValIsSame && n.compareTo(nExpected) != 0) {
            n.multiplyBy10(i);
            nLoopVal.multiplyBy10(i);
            i++;
            if (i > 9) {
                i = 0;
            }
            if (nLoopVal.compareTo(n) != 0) {
                nLoopValIsSame = false;
            }
        }
        assertEquals(nExpected, n);
        assertEquals(nLoopVal, n);
        assertEquals(nLoopValExpected, nLoopValExpected);
    }

    /**
     * Test divideBy10 with an empty NaturalNumber.
     *
     * @Routine
     */
    @Test
    public void testDivideBy10EmptyNN() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        int r = n.divideBy10();
        int rExpected = 0;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
    }

    /**
     * Test divideBy10 with a Natural Number of 1 digit.
     *
     * @Routine
     */
    @Test
    public void testNNDivideBy10Int() {
        NaturalNumber n = this.constructorTest(5);
        NaturalNumber nExpected = this.constructorRef();
        int r = n.divideBy10();
        int rExpected = 5;
        assertEquals(rExpected, r);
        assertEquals(nExpected, n);
    }

    /**
     * Test divideBy10 with a Natural Number holding 2 digits.
     *
     * @Routine
     */
    @Test
    public void testNNDivideBy10String() {
        NaturalNumber n = this.constructorTest("50");
        NaturalNumber nExpected = this.constructorRef(5);
        int r = n.divideBy10();
        int xExpected = 0;
        assertEquals(xExpected, r);
        assertEquals(nExpected, n);
    }

    /**
     * Test multiplyBy10 in succession and divideBy10.
     *
     * @Routine
     */
    @Test
    public void testDivideAndMultiplyBy10() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef("10");
        n.multiplyBy10(1);
        n.multiplyBy10(0);
        n.multiplyBy10(5);
        int r = n.divideBy10();
        int rExpected = 5;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);

    }

    /**
     * Test isZero with an empty NaturalNumber.
     *
     * @Boundary empty entry
     */
    @Test
    public void testIsZero() {
        NaturalNumber n = this.constructorTest();
        boolean isZero = n.isZero();
        boolean isZeroExpected = true;
        assertEquals(isZeroExpected, isZero);
    }

    /**
     * Test isZero with a NaturalNumber with a value that is not zero.
     *
     * @Routine
     */
    @Test
    public void testIsZeroNonZeroNN() {
        NaturalNumber n = this.constructorTest(123456);
        Boolean isZero = n.isZero();
        Boolean isZeroExpected = false;
        assertEquals(isZeroExpected, isZero);
    }

    /**
     * Test isZero and divideBy10.
     *
     * @Routine
     */
    @Test
    public void testIsZero2AnddivideBy10() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef();
        int r = n.divideBy10();
        int rExpected = 5;
        boolean isZero = n.isZero();
        boolean isZeroExpected = true;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
        assertEquals(isZeroExpected, isZero);
    }

    /**
     * Test nDivideBy10 and isZero with a loop.
     *
     * @Challenging Testing two methods in one test
     */
    @Test
    public void testIsZeroLoopAndDivideByTen() {
        NaturalNumber n = this.constructorTest("15050");
        NaturalNumber nExpected = this.constructorTest();
        int r = 0;
        int rExpected = 1;
        final int[] rExpectedArr = { 0, 5, 0, 5, 1 };
        boolean correctRemainder = true;
        int i = 0;
        while (!n.isZero()) {
            r = n.divideBy10();
            if (r != rExpectedArr[i]) {
                correctRemainder = false;
            }
            i++;
        }
        boolean isZero = n.isZero();
        boolean isZeroExpected = true;
        boolean correctRemainderExpected = true;
        assertEquals(nExpected, n);
        assertEquals(correctRemainderExpected, correctRemainder);
        assertEquals(isZeroExpected, isZero);
        assertEquals(rExpected, r);
    }

    /**
     * Test isZero and divideBy10.
     *
     * @Challenging using multiple methods trying to break
     */
    @Test
    public void testIsZero3() {
        NaturalNumber n = this.constructorTest("5");
        NaturalNumber nExpected = this.constructorRef();
        int r = n.divideBy10();
        int rExpected = 5;
        n.multiplyBy10(0000000000);
        boolean isZero = n.isZero();
        boolean isZeroExpected = true;
        assertEquals(nExpected, n);
        assertEquals(rExpected, r);
        assertEquals(isZeroExpected, isZero);
    }

}
