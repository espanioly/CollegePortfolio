import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.list.List;

/**
 * JUnit test fixture for {@code List<String>}'s constructor and kernel methods.
 *
 * @author Aaron Heishman, Sam Espanioly
 *
 */
public abstract class ListTest {

    /**
     * Invokes the appropriate {@code List} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new list
     * @ensures constructorTest = (<>, <>)
     */
    protected abstract List<String> constructorTest();

    /**
     * Invokes the appropriate {@code List} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new list
     * @ensures constructorRef = (<>, <>)
     */
    protected abstract List<String> constructorRef();

    /**
     * Constructs a {@code List<String>} with the entries in {@code args} and
     * length of the left string equal to {@code leftLength}.
     *
     * @param list
     *            the {@code List} to construct
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @updates list
     * @requires list = (<>, <>) and 0 <= leftLength <= args.length
     * @ensures <pre>
     * list = ([first leftLength entries in args], [remaining entries in args])
     * </pre>
     */
    private void createFromArgsHelper(List<String> list, int leftLength,
            String... args) {
        for (String s : args) {
            list.addRightFront(s);
            list.advance();
        }
        list.moveToStart();
        for (int i = 0; i < leftLength; i++) {
            list.advance();
        }
    }

    /**
     * Creates and returns a {@code List<String>} of the implementation under
     * test type with the given entries.
     *
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @return the constructed list
     * @requires 0 <= leftLength <= args.length
     * @ensures <pre>
     * createFromArgs =
     *   ([first leftLength entries in args], [remaining entries in args])
     * </pre>
     */
    protected final List<String> createFromArgsTest(int leftLength,
            String... args) {
        assert 0 <= leftLength : "Violation of: 0 <= leftLength";
        assert leftLength <= args.length : "Violation of: leftLength <= args.length";
        List<String> list = this.constructorTest();
        this.createFromArgsHelper(list, leftLength, args);
        return list;
    }

    /**
     * Creates and returns a {@code List<String>} of the reference
     * implementation type with the given entries.
     *
     * @param leftLength
     *            the length of the left string in the constructed {@code List}
     * @param args
     *            the entries for the list
     * @return the constructed list
     * @requires 0 <= leftLength <= args.length
     * @ensures <pre>
     * createFromArgs =
     *   ([first leftLength entries in args], [remaining entries in args])
     * </pre>
     */
    protected final List<String> createFromArgsRef(int leftLength,
            String... args) {
        assert 0 <= leftLength : "Violation of: 0 <= leftLength";
        assert leftLength <= args.length : "Violation of: leftLength <= args.length";
        List<String> list = this.constructorRef();
        this.createFromArgsHelper(list, leftLength, args);
        return list;
    }

    /*
     * Test cases for constructor, addRightFront, removeRightFront, advance,
     * moveToStart, leftLength, and rightLength.
     */

    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        List<String> list1 = this.constructorTest();
        List<String> list2 = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0, "red");
        /*
         * Call method under test
         */
        list1.addRightFront("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        /*
         * Call method under test
         */
        list1.addRightFront("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftNonEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple", "red");
        /*
         * Call method under test
         */
        list1.addRightFront("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAddRightFrontLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        /*
         * Call method under test
         */
        list1.addRightFront("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftEmptyRightOne() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "red", "blue");
        /*
         * Call method under test
         */
        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftNonEmptyRightOne() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        /*
         * Call method under test
         */
        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("red", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRemoveRightFrontLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "purple");
        /*
         * Call method under test
         */
        String s = list1.removeRightFront();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("green", s);
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftEmptyRightOne() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "red");
        List<String> list2 = this.createFromArgsRef(1, "red");
        /*
         * Call method under test
         */
        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(1, "green", "red", "blue");
        /*
         * Call method under test
         */
        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftNonEmptyRightOne() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple", "red");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "orange",
                "purple", "red");
        /*
         * Call method under test
         */
        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testAdvanceLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "green", "purple");
        /*
         * Call method under test
         */
        list1.advance();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        /*
         * Call method under test
         */
        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftNonEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "orange",
                "purple");
        /*
         * Call method under test
         */
        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToStartLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "orange",
                "green", "purple");
        list1.moveToStart();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        /*
         * Call method under test
         */
        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftNonEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        /*
         * Call method under test
         */
        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testRightLengthLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        /*
         * Call method under test
         */
        int i = list1.rightLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(0, "green", "red", "blue");
        /*
         * Call method under test
         */
        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftNonEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        /*
         * Call method under test
         */
        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, i);
        assertEquals(list2, list1);
    }

    @Test
    public final void testLeftLengthLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "orange",
                "green", "purple");
        /*
         * Call method under test
         */
        int i = list1.leftLength();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, i);
        assertEquals(list2, list1);
    }

    /*
     * Test cases for iterator.
     */

    @Test
    public final void testIteratorEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list3, list2);
    }

    @Test
    public final void testIteratorOnlyRight() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "red", "blue");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(0, "red", "blue");
        List<String> list4 = this.createFromArgsRef(0, "blue", "red");
        /*
         * Call method under test
         */
        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    @Test
    public final void testIteratorOnlyLeft() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "red", "green", "blue");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(3, "red", "green", "blue");
        List<String> list4 = this.createFromArgsRef(0, "blue", "green", "red");
        /*
         * Call method under test
         */
        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    @Test
    public final void testIteratorLeftAndRight() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "purple", "red",
                "green", "blue", "yellow");
        List<String> list2 = this.createFromArgsRef(0);
        List<String> list3 = this.createFromArgsRef(2, "purple", "red", "green",
                "blue", "yellow");
        List<String> list4 = this.createFromArgsRef(0, "yellow", "blue",
                "green", "red", "purple");
        /*
         * Call method under test
         */
        for (String s : list1) {
            list2.addRightFront(s);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list3, list1);
        assertEquals(list4, list2);
    }

    /*
     * Test cases for other methods: moveToFinish
     */

    @Test
    public final void testMoveToFinishLeftEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "green", "red", "blue");
        List<String> list2 = this.createFromArgsRef(3, "green", "red", "blue");
        /*
         * Call method under test
         */
        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftNonEmptyRightEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "orange",
                "purple");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "orange",
                "purple");
        /*
         * Call method under test
         */
        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishLeftNonEmptyRightNonEmpty() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "orange",
                "green", "purple");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "orange",
                "green", "purple");
        /*
         * Call method under test
         */
        list1.moveToFinish();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(list2, list1);
    }

    @Test
    public final void testMoveToFinishShowBug() {
        /*
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0);
        List<String> list2 = this.createFromArgsRef(0, "red");
        /*
         * Call method under test
         */
        list1.moveToFinish();
        /*
         * Evaluate the correctness of the result
         */
        list1.addRightFront("red");
        assertEquals(list2, list1);
    }

    /*
     * Start retreat tests
     */

    /**
     * Testing retreat with non-empty left and empty right making left empty.
     */
    @Test
    public final void testRetreatLeftNonEmptyRightEmptyMakeLeftEmpty() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "yellow");
        List<String> list2 = this.createFromArgsRef(0, "yellow");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        assertEquals(list2, list1);
    }

    /**
     * Testing retreat with a list where the entries on the left are not empty,
     * but the right is.
     */
    @Test
    public final void testRetreatLeftNonEmptyRightEmpty() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "blue");
        List<String> list2 = this.createFromArgsRef(1, "yellow", "blue");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        assertEquals(list2, list1);
    }

    /**
     * Testing retreat with a list that has a non-empty left and right.
     */
    @Test
    public final void testRetreatLeftNonEmptyRightNonEmpty() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "blue", "red",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        assertEquals(list2, list1);
    }

    /**
     * Testing retreat after retreating from a non empty left and then adding
     * one more to right front.
     */
    @Test
    public final void testRetreatMakeLeftEmptyRightNonEmptyAddFront() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(0, "pink", "yellow", "blue",
                "red", "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * /* Evaluate the results
         */
        list1.addRightFront("pink");
        assertEquals(list2, list1);
    }

    /**
     * Testing retreat after retreating from a non empty left and then adding
     * one more to right front.
     */
    @Test
    public final void testRetreatLeftNonEmptyRightNonEmptyAddFront() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "blue", "pink",
                "red", "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * /* Evaluate the results
         */
        list1.addRightFront("pink");
        assertEquals(list2, list1);
    }

    /**
     * Testing retreat with a non-empty left and empty-right side, retreating
     * one and then removingRightFront to make right empty.
     */
    @Test
    public final void testRetreatLeftNonEmptyRemoveRightFrontMakeRightEmpty1() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "1,2,3,4");
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        String x = list1.removeRightFront();
        assertEquals(list2, list1);
        assertEquals("1,2,3,4", x);
    }

    /**
     * Testing retreat with a non-empty left and empty-right side, retreating
     * one and then removingRightFront to make right empty.
     */
    @Test
    public final void testRetreatLeftNonEmptyRemoveRightFrontMakeRightEmpty2() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "!@#$%^&*()-_=+");
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        String x = list1.removeRightFront();
        assertEquals(list2, list1);
        assertEquals("!@#$%^&*()-_=+", x);
    }

    /**
     * Testing retreat with a non-empty left and empty-right side, retreating
     * one and then removingRightFront to make right empty.
     */
    @Test
    public final void testRetreatLeftNonEmptyRemoveRightFrontMakeRightEmpty3() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1,
                Integer.toString(Integer.MIN_VALUE));
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        String x = list1.removeRightFront();
        assertEquals(list2, list1);
        assertEquals(Integer.toString(Integer.MIN_VALUE), x);
    }

    /**
     * Testing retreat with a non-empty left and empty-right side, retreating
     * one and then removingRightFront to make right empty.
     */
    @Test
    public final void testRetreatLeftNonEmptyRemoveRightFrontMakeRightEmpty4() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "-Q " + "-$");
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        String x = list1.removeRightFront();
        assertEquals(list2, list1);
        assertEquals("-Q -$", x);
    }

    /**
     * Testing retreat with a non-empty left and empty-right side, retreating
     * one and then removingRightFront to make right empty.
     */
    @Test
    public final void testRetreatLeftNonEmptyRemoveRightFrontMakeRightEmpty5() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1,
                Double.toString(Double.MAX_VALUE));
        List<String> list2 = this.createFromArgsRef(0);
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        String x = list1.removeRightFront();
        assertEquals(list2, list1);
        assertEquals(Double.toString(Double.MAX_VALUE), x);
    }

    /**
     * Testing retreat on a list with a non-empty left and right side, then
     * removing the first entry from the right.
     */
    @Test
    public final void testRetreatLeftNonEmptyRightNonEmptyRemoveRightFront() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(2, "yellow", "blue",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        String x = list1.removeRightFront();
        assertEquals(list2, list1);
        assertEquals("red", x);
    }

    /**
     * Test retreat with non-empty left side and non-empty right side making the
     * left empty and then advancing to give the left one element.
     */
    @Test
    public final void testRetreatAdvanceNonEmptyLeftNonEmptyRight() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(1, "yellow", "blue", "red",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        list1.advance();
        assertEquals(list2, list1);
    }

    /**
     * Test retreat with non-empty left side and non-empty right side making the
     * left empty and then advancing to give the left one element.
     */
    @Test
    public final void testRetreatAdvanceNonEmptyLeftNonEmptyRight2() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(3, "yellow", "blue", "red",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        list1.advance();
        assertEquals(list2, list1);
    }

    /**
     * Test retreat with non-empty left side and non-empty right side and
     * non-empty left side then calling moveToStart.
     */
    @Test
    public final void testRetreatMoveToStartNonEmptyLeftNonEmptyRight() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "blue", "red",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        list1.moveToStart();
        assertEquals(list2, list1);
    }

    /**
     * Test retreat with non-empty left side and non-empty right side and
     * non-empty left side then calling moveToStart.
     */
    @Test
    public final void testRetreatMoveToStartNonEmptyLeftNonEmptyRight2() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(4, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(0, "yellow", "blue", "red",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        list1.moveToStart();
        assertEquals(list2, list1);
    }

    /**
     * Test retreat with empty left side and non-empty right side after
     * moveToFinish has been called.
     */
    @Test
    public final void testRetreatMoveToFinishRightNonEmptyLeftNonEmpty() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(0, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(4, "yellow", "blue", "red",
                "green", "white");
        list1.moveToFinish();
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        assertEquals(list2, list1);
    }

    /**
     * Test retreat with a non-empty left side and non-empty right side and then
     * calling moveToFinish.
     */
    @Test
    public final void testRetreatMoveToFinishRightNonEmptyLeftNonEmpty2() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "yellow", "blue", "red",
                "green", "white");
        List<String> list2 = this.createFromArgsRef(5, "yellow", "blue", "red",
                "green", "white");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        list1.moveToFinish();
        assertEquals(list2, list1);
    }

    /**
     * Test retreat retreat with empty right, non-empty left and size.
     */
    @Test
    public final void testRetreatLeftLengthMakeLeftEmpty() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "Echo", "Charlie",
                "Alpha");
        List<String> list2 = this.createFromArgsRef(0, "Echo", "Charlie",
                "Alpha");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        int leftLength = list1.leftLength();
        assertEquals(0, leftLength);
        assertEquals(list2, list1);
    }

    /**
     * Test retreat retreat with non-empty right, non-empty left and size.
     */
    @Test
    public final void testRetreatLeftLengthMakeLeftNonEmpty1() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "Delta", "Echo",
                "Charlie", "Alpha");
        List<String> list2 = this.createFromArgsRef(1, "Delta", "Echo",
                "Charlie", "Alpha");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        int leftLength = list1.leftLength();
        assertEquals(1, leftLength);
        assertEquals(list2, list1);
    }

    /**
     * Test retreat retreat with non-empty right, non-empty left and size.
     */
    @Test
    public final void testRetreatLeftLengthMakeLeftNonEmpty2() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "Delta", "Echo",
                "Charlie", "Alpha");
        List<String> list2 = this.createFromArgsRef(0, "Delta", "Echo",
                "Charlie", "Alpha");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        int leftLength = list1.leftLength();
        assertEquals(0, leftLength);
        assertEquals(list2, list1);
    }

    /**
     * Test retreat retreat with an empty right, non-empty left and size.
     */
    @Test
    public final void testRetreatRightLengthMakeRightNonEmpty() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(1, "Delta");
        List<String> list2 = this.createFromArgsRef(0, "Delta");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        int rightLength = list1.rightLength();
        assertEquals(1, rightLength);
        assertEquals(list2, list1);
    }

    /**
     * Test retreat retreat with non-empty right, non-empty left and size.
     */
    @Test
    public final void testRetreatRightLengthMakeRightNonEmpty2() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(3, "Delta", "Charlie",
                "Alpha");
        List<String> list2 = this.createFromArgsRef(2, "Delta", "Charlie",
                "Alpha");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        int rightLength = list1.rightLength();
        assertEquals(1, rightLength);
        assertEquals(list2, list1);
    }

    /**
     * Test retreat with non-empty right, non-empty left and size.
     */
    @Test
    public final void testRetreatRightLengthMakeRightNonEmpty3() {
        /**
         * Set up variables
         */
        List<String> list1 = this.createFromArgsTest(2, "Delta", "Charlie",
                "Alpha", "Seymore", "Fry");
        List<String> list2 = this.createFromArgsRef(1, "Delta", "Charlie",
                "Alpha", "Seymore", "Fry");
        /*
         * Call method under test
         */
        list1.retreat();
        /*
         * Evaluate the results
         */
        int rightLength = list1.rightLength();
        assertEquals(4, rightLength);
        assertEquals(list2, list1);
    }

}
