import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Aaron Heishman, Sam Espanioly
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */
    /**
     * Testing constructor.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * Testing add with an empty sorting machine.
     */
    @Test
    public final void testAddEmpty1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /*
     * Test cases
     */

    /**
     * Testing add with an empty sorting machine.
     */
    @Test
    public final void testAddEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                ".............................................................."
                        + "...................................................."
                        + "...............................................42");
        m.add("..............................................................."
                + "..........................................................."
                + ".......................................42");
        assertEquals(mExpected, m);
    }

    /**
     * Test add with a non-empty sorting machine filled with one string.
     */
    @Test
    public final void testAddNonEmpty1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Mellon");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Mellon", "Grape");
        m.add("Grape");
        assertEquals(mExpected, m);
    }

    /**
     * Testing add with a non-empty sorting machine filled with multiple
     * strings.
     */
    @Test
    public final void testAddNonEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Mellon", "Kolita", "Apple", "Fruit Punch", "Manga", "Cherry",
                "Toronja", "Peach");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Mellon", "Kolita", "Apple", "Fruit Punch", "Manga", "Cherry",
                "Toronja", "Peach", "Grape");
        m.add("Grape");
        assertEquals(mExpected, m);
    }

    /**
     * Test changeToExtractionMode with empty sorting machine.
     */
    @Test
    public final void testChangeToExtractionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with a sorting machine that has one
     * string.
     */
    @Test
    public final void testChangeToExtractionModeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with a sorting machine with two strings.
     */
    @Test
    public final void testChangeToExtractionModeNonEmptyMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Mellon", "Kolita");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Mellon", "Kolita");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with a sorting machine with three strings.
     */
    @Test
    public final void testChangeToExtractionModeNonEmptyMultiple2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Kai",
                "Aren", "Fanta");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Kai", "Aren", "Fanta");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with a sorting machine that holds multiple
     * strings.
     */
    @Test
    public final void testChangeToExtractionModeNonEmptyMultiple3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Kai",
                "Aren", "Fanta", "Nate", "Marcell", "Origami", "Sienna");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Kai", "Aren", "Fanta", "Nate", "Marcell", "Origami", "Sienna");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with a sorting machine that holds multiple
     * strings.
     */
    @Test
    public final void testChangeToExtractionModeNonEmptyMultiple4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2", "500", "0", "-85516.5&*^%", "$$$", "[");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "$$$", "-85516.5&*^%", "1", "0", "2", "500", "[");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with a sorting machine that holds multiple
     * strings that have multiple words in the strings.
     */
    @Test
    public final void testChangeToExtractionModeNonEmptyMultiple5() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "A Link to the Past", "Majora's Mask", "Ocarina Of Time",
                "Skyward Sword", "Oracle of Ages", "Oracle of Season",
                "Wind Waker", "Twilight Princess", "Breath of the Wild",
                "Tears of the Kingdom", "The Minish Cap");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A Link to the Past", "Majora's Mask", "Ocarina Of Time",
                "Skyward Sword", "Oracle of Ages", "Oracle of Season",
                "Wind Waker", "Twilight Princess", "Breath of the Wild",
                "Tears of the Kingdom", "The Minish Cap");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing changeToExtractionMode with descending alphabet characters.
     */
    @Test
    public final void testChangeToExtractionDescendingAlphabet() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "z",
                "y", "x", "v", "u", "t", "s", "r", "q", "p", "o", "m", "m", "l",
                "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "z", "y", "x", "v", "u", "t", "s", "r", "q", "p", "o", "m", "m",
                "l", "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test changeToExtractionMode with a sorting machine that holds randomly
     * selected strings.
     */
    @Test
    public final void testChangeToExtractionModeRandom() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "invention", "mother", "locket", "porter", "cap", "rabbits",
                "order", "hand", "doll", "tin", "north", "brain", "bed",
                "direction", "country", "toothbrush", "bucket", "join",
                "nation", "net");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "invention", "mother", "locket", "porter", "cap", "rabbits",
                "order", "hand", "doll", "tin", "north", "brain", "bed",
                "direction", "country", "toothbrush", "bucket", "join",
                "nation", "net");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test changeToExtractionMode with a non-empty sorting machine that holds
     * multiple of the same strings.
     */
    @Test
    public final void testChangeToExtractionModeNonEmptySameStrings() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "Dad",
                "mom", "Son", "Daughter", "Son", "Daughter", "Son", "Cousin",
                "Cousin", "Brother", "Mother", "Mom", "Father", "GrandFather");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Dad", "mom", "Son", "Daughter", "Son", "Daughter", "Son",
                "Cousin", "Cousin", "Brother", "Mother", "Mom", "Father",
                "GrandFather");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst with a sorting machine with string making the sorting
     * machine empty.
     */
    @Test
    public final void testRemoveFirstMakeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "First");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "First");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst with a non-empty sorting machine.
     */
    @Test
    public final void testRemoveFirstMakeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "b",
                "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "b", "a");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst with a non-empty sorting machine.
     */
    @Test
    public final void testRemoveFirstMakeNonEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red", "blue", "yellow", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "red", "blue", "yellow", "orange");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst with a non-empty sorting machine with randomly chosen
     * strings.
     */
    @Test
    public final void testRemoveFirstMakeNonEmpty3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Charmander", "mew", "Dialga", "pikachu", "evee", "Snorlax",
                "garchomp", "Lucario", "bulbasaur", "Red", "Oak", "blue",
                "Gary");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Charmander", "mew", "Dialga", "pikachu", "evee", "Snorlax",
                "garchomp", "Lucario", "bulbasaur", "Red", "Oak", "blue",
                "Gary");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst with a non-empty sorting machine with strings that
     * contain more than one word in them.
     */
    @Test
    public final void testRemoveFirstMakeNonEmpty4() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "A Link to the Past", "Majora's Mask", "Ocarina Of Time",
                "Skyward Sword", "Oracle of Ages", "Oracle of Season",
                "Wind Waker", "Twilight Princess", "Breath of the Wild",
                "Tears of the Kingdom", "The Minish Cap");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "A Link to the Past", "Majora's Mask", "Ocarina Of Time",
                "Skyward Sword", "Oracle of Ages", "Oracle of Season",
                "Wind Waker", "Twilight Princess", "Breath of the Wild",
                "Tears of the Kingdom", "The Minish Cap");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Testing removeFirst with a sorting machine consisting of descending
     * letters.
     */
    @Test
    public final void testRemoveFirstDescendingLetters() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "z",
                "y", "x", "v", "u", "t", "s", "r", "q", "p", "o", "m", "m", "l",
                "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "z", "y", "x", "v", "u", "t", "s", "r", "q", "p", "o", "m", "m",
                "l", "k", "j", "i", "h", "g", "f", "e", "d", "c", "b", "a");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Test order with an empty sorting machine that is in insertion mode.
     */
    @Test
    public final void testOrderEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(ORDER, m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Test order with a non-empty sorting machine that is in insertionMode.
     */
    @Test
    public final void testOrderNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "red", "blue", "yellow", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "blue", "yellow", "orange");
        assertEquals(ORDER, m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Test order after changing from insertion mode to extraction mode.
     */
    @Test
    public final void testOrderChangeToExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Edward");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Edward");
        assertEquals(ORDER, m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Test order after removing the first value in the sorting machine.
     */
    @Test
    public final void testOrderRemoveFirst() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Edward", "Elric");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Edward", "Elric");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(ORDER, m.order());
        assertEquals(valExpected, val);
        assertEquals(mExpected, m);
    }

    /**
     * Test isInInsertion mode with an empty sorting machine.
     */
    @Test
    public final void testIsInInsertionModeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(true, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Test isInInsertionMode on a non-empty sorting machine.
     */
    @Test
    public final void testIsInInsertionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Triangle", "Square", "Cube");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Triangle", "Square", "Cube");
        assertEquals(true, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Test isInInsertionMode with an empty sorting machine in extraction mode.
     */
    @Test
    public final void testIsInInsertionModeEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Test isInInertionMode with a non-empty sorting machine in extraction
     * mode.
     */
    @Test
    public final void testIsInInsertionModeNonEmptyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Cube",
                "Square", "Triangle");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Triangle", "Cube", "Square");
        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Test isInInsertionMode after changing to Extraction mode and removing the
     * only string to make the Sorting Machine empty.
     */
    @Test
    public final void testIsInInsertionModeRemoveFirstMakeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Cube");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Cube");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Test isInInsertionMode after changing to Extraction mode and removing the
     * first string.
     */
    @Test
    public final void testIsInInsertionModeRemoveFirstMakeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "Cube",
                "Strand", "Expect", "Loss", "Sora", "Darkness", "Key",
                "Thomas");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Thomas", "Expect", "Loss", "Cube", "Strand", "Sora", "Key",
                "Darkness");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        assertEquals(valExpected, val);
        assertEquals(false, m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Test size with an empty sorting machine.
     */
    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        int size = m.size();
        assertEquals(0, size);
        assertEquals(mExpected, m);
    }

    /**
     * Test size after adding a string to an empty sorting machine.
     */
    @Test
    public final void testSizeAddToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Voight");
        m.add("Voight");
        int size = m.size();
        assertEquals(1, size);
        assertEquals(mExpected, m);
    }

    /**
     * Test size after adding a string to a non-empty sorting machine.
     */
    @Test
    public final void testSizeAddToNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "b",
                "c", "d", "k", "z", "d", "y", "z", "pin");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "b", "c", "d", "k", "z", "d", "head", "y", "z", "pin");
        m.add("head");
        int size = m.size();
        assertEquals(10, size);
        assertEquals(mExpected, m);
    }

    /**
     * Test size of an empty sorting machine already in extraction mode.
     */
    @Test
    public final void testSizeEmptyExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        int size = m.size();
        assertEquals(0, size);
        assertEquals(mExpected, m);

    }

    /**
     * Test size of sorting machine after removing the first entry and making
     * the sorting machine empty.
     */
    @Test
    public final void testSizeRemoveFirstMakeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Voight");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Voight");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        int size = m.size();
        assertEquals(valExpected, val);
        assertEquals(0, size);
        assertEquals(mExpected, m);
    }

    /**
     * Test size of non-empty sorting machine after removing the first entry.
     */
    @Test
    public final void testSizeRemoveFirstNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Voight", "pay", "Green", "summer", "yellow", "test", "Large",
                "Zoom");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Voight", "pay", "Green", "summer", "yellow", "test", "Large",
                "Zoom");
        String val = m.removeFirst();
        String valExpected = mExpected.removeFirst();
        int size = m.size();
        assertEquals(valExpected, val);
        assertEquals(7, size);
        assertEquals(mExpected, m);
    }

}
