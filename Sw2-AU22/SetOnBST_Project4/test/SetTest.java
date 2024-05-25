import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Aaron Heishman, Sam Espanioly
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Test the constructor.
     */
    @Test
    public void testConstructor() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest();
        Set<String> expectedSet = this.createFromArgsRef();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
    }

    //---------------------------Test add------------------------------------//

    /**
     * Test add method with blank set.
     */
    @Test
    public void addToEmptySet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest();
        Set<String> expectedSet = this.createFromArgsRef("Thomas");
        /*
         * Call method under test
         */
        testSet.add("Thomas");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
    }

    /**
     * Test add with set that contains one element.
     */
    @Test
    public void addToNonEmptySet1() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Thomas");
        Set<String> expectedSet = this.createFromArgsRef("Thomas", "Marcell");
        /*
         * Call method under test
         */
        testSet.add("Marcell");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
    }

    /**
     * Test add with a set that has multiple elements.
     */
    @Test
    public void addToNonEmptySet2() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Thomas", "Marcell",
                "Kai", "Nate", "Sienna", "Aaron", "Armany", "Jeff", "Fanta");
        Set<String> expectedSet = this.createFromArgsRef("Thomas", "Marcell",
                "Kai", "Nate", "Sienna", "Aaron", "Armany", "Jeff", "Fanta",
                "Lemon");
        /*
         * Call method under test
         */
        testSet.add("Lemon");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
    }

    /**
     * Test add with a set that has multiple elements.
     */
    @Test
    public void addToNonEmptySet3() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Adam", "Charlie", "Zeke",
                "Thomas");
        Set<String> expectedSet = this.createFromArgsRef("Adam", "Charlie",
                "Zeke", "Thomas", "Mike");

        /*
         * Call method under test
         */
        testSet.add("Mike");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
    }

    /**
     * Test add with a set that has multiple elements.
     */
    @Test
    public void addToNonEmptySet4() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Adam", "Charlie", "Zeke",
                "Thomas");
        Set<String> expectedSet = this.createFromArgsRef("Adam", "Charlie",
                "Zeke", "Thomas", "Abraham");
        /*
         * Call method under test
         */
        testSet.add("Abraham");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
    }

    //-------------------------Test remove-----------------------------------//

    /**
     * Test remove on a set with one element making it empty.
     */
    @Test
    public void removeFromSetMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Thomas");
        Set<String> expectedSet = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String value = testSet.remove("Thomas");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Thomas", value);
    }

    /**
     * Test remove with a set that is not empty.
     */
    @Test
    public void removeFromSetMakeNonEmpty1() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Thomas", "Marco",
                "Sanji", "Luffy");
        Set<String> expectedSet = this.createFromArgsRef("Thomas", "Sanji",
                "Luffy");
        /*
         * Call method under test
         */
        String value = testSet.remove("Marco");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Marco", value);
    }

    /**
     * Test remove with a set that is not empty.
     */
    @Test
    public void removeFromSetMakeNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Thomas", "Marco",
                "Sanji", "Luffy");
        Set<String> expectedSet = this.createFromArgsRef("Marco", "Sanji",
                "Luffy");
        /*
         * Call method under test
         */
        String value = testSet.remove("Thomas");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Thomas", value);
    }

    /**
     * Test remove with a set that is not empty.
     */
    @Test
    public void removeFromSetMakeNonEmpty3() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Aaron", "Diego", "Sam",
                "Mark");
        Set<String> expectedSet = this.createFromArgsRef("Aaron", "Sam",
                "Mark");
        /*
         * Call method under test
         */
        String value = testSet.remove("Diego");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Diego", value);
    }

    /**
     * Test remove from set. Specifically testing removing the root of a bst.
     */
    @Test
    public void removeFromSetMakeNonEmpty4() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Adam", "Charlie",
                "Diego", "Santiago", "Reno");
        Set<String> expectedSet = this.createFromArgsRef("Charlie", "Diego",
                "Santiago", "Reno");
        /*
         * Call method under test
         */
        String value = testSet.remove("Adam");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Adam", value);
    }

    /**
     * Test remove from non empty set that contain entries starting with the
     * letter A.
     */
    @Test
    public void removeFromSetMakeNonEmpty5() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Adam", "Aleksi",
                "Arnold", "Antonio", "Anthony", "Albert", "Alivia", "Alice",
                "Alissa");
        Set<String> expectedSet = this.createFromArgsRef("Adam", "Aleksi",
                "Arnold", "Antonio", "Anthony", "Alivia", "Alice", "Alissa");
        /*
         * Call method under test
         */
        String value = testSet.remove("Albert");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Albert", value);
    }

    //------------------------Test removeAny----------------------------------//

    /**
     * Test removeAny with a set that only has one element, making the set
     * empty.
     */
    @Test
    public void removeAnyMakeEmptySet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Jack");
        Set<String> expectedSet = this.createFromArgsRef("Jack");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
    }

    /**
     * Test removeAny with a non empty set.
     */
    @Test
    public void removeAnyNonEmpty1() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("a", "aa", "ab", "ac",
                "ad");
        Set<String> expectedSet = this.createFromArgsRef("a", "aa", "ab", "ac",
                "ad");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(testSet, expectedSet);
        assertEquals(expectedValue, value);
    }

    /**
     * Test removeAny with a non empty set.
     */
    @Test
    public void removeAnyNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Travis", "Arnold",
                "Bert", "Blink", "Savior", "Alice", "Night", "a", "twentyfour");
        Set<String> expectedSet = this.createFromArgsRef("Travis", "Arnold",
                "Bert", "Blink", "Savior", "Alice", "Night", "a", "twentyfour");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
    }

    /**
     * Test removeAny with a set that contains only elements that start with A.
     */
    @Test
    public void removeAnyNonEmpty3() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Adam", "Aleksi",
                "Arnold", "Antonio", "Anthony", "Albert", "Alivia", "Alice",
                "Alissa");
        Set<String> expectedSet = this.createFromArgsRef("Adam", "Aleksi",
                "Arnold", "Antonio", "Anthony", "Albert", "Alivia", "Alice",
                "Alissa");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
    }

    //------------------------Test contains-----------------------------------//

    /**
     * Test contains with a blank set.
     */
    @Test
    public void testContainsBlankSet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest();
        Set<String> expectedSet = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean contains = testSet.contains("Tom");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(false, contains);
    }

    /**
     * Test contains with a non-empty set.
     */
    @Test
    public void testContainsNonEmptySet1() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Tom");
        Set<String> expectedSet = this.createFromArgsRef("Tom");
        /*
         * Call method under test
         */
        boolean contains = testSet.contains("Tom");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(true, contains);
    }

    /**
     * Test contains with a non-empty set that does not contain the value being
     * looked for.
     */
    @Test
    public void testContainsNonEmptySet2() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("One", "Six", "Nine",
                "Four", "Ten", "Fifteen");
        Set<String> expectedSet = this.createFromArgsRef("One", "Six", "Nine",
                "Four", "Ten", "Fifteen");
        /*
         * Call method under test
         */
        boolean contains = testSet.contains("Five");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(false, contains);
    }

    /**
     * Test contains with a non-empty set.
     */
    @Test
    public void testContainsNonEmptySet3() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("One", "Six", "Nine",
                "Four", "Ten", "Fifteen");
        Set<String> expectedSet = this.createFromArgsRef("One", "Six", "Nine",
                "Four", "Ten", "Fifteen");
        /*
         * Call method under test
         */
        boolean contains = testSet.contains("Nine");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(true, contains);
    }

    /**
     * Test contains with a non-empty set.
     */
    @Test
    public void testContainsNonEmptySet4() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("12345", "1234", "123",
                "12", "124", "123456");
        Set<String> expectedSet = this.createFromArgsRef("12345", "1234", "123",
                "12", "124", "123456");
        /*
         * Call method under test
         */
        boolean contains = testSet.contains("124");
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(true, contains);
    }

    /**
     * Test add to set and contains with a blank set.
     */
    @Test
    public void addToEmptySetAndContains() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest();
        Set<String> expectedSet = this.createFromArgsRef("Draedon");
        /*
         * Call method under test
         */
        testSet.add("Draedon");
        boolean contains = testSet.contains("Draedon");
        assertEquals(expectedSet, testSet);
        assertEquals(true, contains);
    }

    /**
     * Test add with a non-empty set and contains.
     */
    @Test
    public void addToNonEmptySetAndContains1() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Draedon", "Reggedit",
                "Calamity", "DoG", "Megashark");
        Set<String> expectedSet = this.createFromArgsRef("Draedon", "Reggedit",
                "Lord", "Calamity", "DoG", "Megashark");
        /*
         * Call method under test
         */
        testSet.add("Lord");
        boolean contains = testSet.contains("Lord");
        assertEquals(expectedSet, testSet);
        assertEquals(true, contains);
    }

    /**
     * Test add to non-empty set and contains with a value that is not in the
     * set.
     */
    @Test
    public void addToNonEmptySetAndContains2() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Draedon", "Reggedit",
                "Calamity", "DoG", "Megashark");
        Set<String> expectedSet = this.createFromArgsRef("Draedon", "Reggedit",
                "Lord", "Calamity", "DoG", "Megashark");
        /*
         * Call method under test
         */
        testSet.add("Lord");
        boolean contains = testSet.contains("Skeletron");
        assertEquals(expectedSet, testSet);
        assertEquals(false, contains);
    }

    /**
     * Test remove and contains on a set with a single value. Making the set
     * empty and verifying that contains returns false.
     */
    @Test
    public void testRemoveMakeEmptySetContains() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Valkyrie");
        Set<String> expectedSet = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String value = testSet.remove("Valkyrie");
        boolean contains = testSet.contains(value);
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Valkyrie", value);
        assertEquals(false, contains);
    }

    /**
     * Test remove and contains on non-empty set.
     */
    @Test
    public void testRemoveAndContainsNonEmptySet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("12345", "1234", "123",
                "12", "124", "123456");
        Set<String> expectedSet = this.createFromArgsRef("12345", "1234", "123",
                "12", "123456");
        /*
         * Call method under test
         */
        String value = testSet.remove("124");
        boolean contains = testSet.contains(value);
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("124", value);
        assertEquals(false, contains);
    }

    /**
     * Test remove and contains on non-empty set.
     */
    @Test
    public void testRemoveAndContainsNonEmptySet2() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("12345", "1234", "123",
                "12", "124", "123456");
        Set<String> expectedSet = this.createFromArgsRef("12345", "1234", "123",
                "12", "123456");
        /*
         * Call method under test
         */
        String value = testSet.remove("124");
        boolean contains = testSet.contains(value);
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("124", value);
        assertEquals(false, contains);
    }

    /**
     * Test removeAny and contains on a set with a single value. Making the set
     * empty and verifying that contains returns false.
     */
    @Test
    public void testRemoveAnyMakeEmptySetAndContains() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Valkyrie");
        Set<String> expectedSet = this.createFromArgsRef("Valkyrie");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        boolean contains = testSet.contains(value);
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
        assertEquals(false, contains);
    }

    /**
     * Test removeAny and contains on a non-empty set.
     */
    @Test
    public void testRemoveAnyNonEmptySetAndContains() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("12345", "1234", "123",
                "12", "124", "123456");
        Set<String> expectedSet = this.createFromArgsRef("12345", "1234", "123",
                "12", "124", "123456");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        boolean contains = testSet.contains(value);
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
        assertEquals(false, contains);
    }

    //---------------------------Test size------------------------------------//

    /**
     * Test size with an empty set.
     */
    @Test
    public void testSizeEmptySet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest();
        Set<String> expectedSet = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(0, size);
    }

    /**
     * Test size with a non empty set.
     */
    @Test
    public void testSizeNonEmptySet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("This", "That", "Them",
                "Those", "These");
        Set<String> expectedSet = this.createFromArgsRef("This", "That", "Them",
                "Those", "These");
        /*
         * Call method under test
         */
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(5, size);
    }

    /**
     * Test size with a blank set that has a value added to it.
     */
    @Test
    public void testSizeAddToBlankSet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest();
        Set<String> expectedSet = this.createFromArgsRef("One");
        /*
         * Call method under test
         */
        testSet.add("One");
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(1, size);
    }

    /**
     * Test size of a non-empty set with a value added to it.
     */
    @Test
    public void testSizeAddToNonEmptySet() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("One", "Two", "Three",
                "Four", "Five", "Six", "Seven");
        Set<String> expectedSet = this.createFromArgsRef("One", "Two", "Three",
                "Five", "Four", "Six", "Seven", "Eight");
        /*
         * Call method under test
         */
        testSet.add("Eight");
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals(8, size);
    }

    /**
     * Test remove with set making it empty and size on the empty set.
     */
    @Test
    public void testRemoveMakeEmptySetAndSize() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Carbon Monoxide");
        Set<String> expectedSet = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String value = testSet.remove("Carbon Monoxide");
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Carbon Monoxide", value);
        assertEquals(0, size);
    }

    /**
     * Test remove with set making it non-empty and size on the non-empty set.
     */
    @Test
    public void testRemoveNonEmptySetAndSize() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Alcohol",
                "Berries and Seeds", "Carbon Monoxide", "Food", "Inhalants");
        Set<String> expectedSet = this.createFromArgsRef("Alcohol",
                "Carbon Monoxide", "Food", "Inhalants");
        /*
         * Call methods under test
         */
        String value = testSet.remove("Berries and Seeds");
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertEquals(expectedSet, testSet);
        assertEquals("Berries and Seeds", value);
        assertEquals(4, size);
    }

    /**
     * Test removeAny with set making it empty and size on the empty set.
     */
    @Test
    public void testRemoveAnyMakeEmptySetAndSize() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Carbon Monoxide");
        Set<String> expectedSet = this.createFromArgsRef("Carbon Monoxide");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
        assertEquals(0, size);
    }

    /**
     * Test remove with set making it non-empty and size on the non-empty set.
     */
    @Test
    public void testRemoveAnyNonEmptySetAndSize() {
        /*
         * Set up variables
         */
        Set<String> testSet = this.createFromArgsTest("Alcohol",
                "Berries and Seeds", "Carbon Monoxide", "Food", "Inhalants");
        Set<String> expectedSet = this.createFromArgsRef("Alcohol",
                "Berries and Seeds", "Carbon Monoxide", "Food", "Inhalants");
        /*
         * Call method under test
         */
        String value = testSet.removeAny();
        int size = testSet.size();
        /*
         * Assert expected values
         */
        assertTrue(expectedSet.contains(value));
        String expectedValue = expectedSet.remove(value);
        assertEquals(expectedSet, testSet);
        assertEquals(expectedValue, value);
        assertEquals(4, size);
    }

}
