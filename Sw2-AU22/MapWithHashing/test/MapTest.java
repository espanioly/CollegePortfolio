import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Sam Espanioly, Aaron Heishman.
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Routine test with constructor.
     */
    @Test
    public void testConstructor() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
    }

    /**
     * Routine test adding a single element to map.
     */
    @Test
    public void testAddToBlankMap() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("Subject",
                "Math");
        /*
         * Call method under test
         */
        testMap.add("Subject", "Math");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
    }

    /**
     * Challenging using same entry from an integer
     */
    @Test
    public void testAddToBlankMap1() {
        /*
         * Set up the variables
         */
        int string = 200;
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef(
                Integer.toString(string), Integer.toString(string));
        /*
         * Call method under test
         */
        testMap.add("200", "200");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
    }

    /**
     * Routine test adding a single element to a map that has elements.
     */
    @Test
    public void testAddToMapWithElements() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE2221",
                "Software I");
        Map<String, String> mapExpected = this.createFromArgsRef("CSE2221",
                "Software I", "CSE2231", "Software II");
        /*
         * Call method under test
         */
        testMap.add("CSE2231", "Software II");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
    }

    /**
     * Routine test adding a single element to a map that has elements.
     */
    @Test
    public void testAddToMapWithElements1() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest("0", "");
        Map<String, String> mapExpected = this.createFromArgsRef("0", "", "1",
                "I");
        /*
         * Call method under test
         */
        testMap.add("1", "I");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
    }

    /**
     * Routine test adding a single element to a map that has elements.
     */
    @Test
    public void testAddToMapWithElements2() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest("I", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("I", "1", "II",
                "2");
        /*
         * Call method under test
         */
        testMap.add("II", "2");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
    }

    /**
     * Boundary/Challenging test adding multiple entries to a map.
     */
    @Test
    public void testAddToBlankMapLoopLength8() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/length8.txt");
        /*
         * call method under test.
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        lineReader.close();
    }

    /**
     * Boundary/Challenging test adding multiple entries to a map.
     */
    @Test
    public void testAddToBlankMapLoopRandom() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/random.txt");

        /*
         * call method under test.
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        lineReader.close();
    }

    /**
     * Routine test removing an entry and making the map empty.
     */
    @Test
    public void testRemoveMakeEmpty() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE2221",
                "Software I");
        Map<String, String> mapExpected = this.createFromArgsRef("CSE2221",
                "Software I");
        /*
         * Call method under test
         */
        Pair<String, String> p = testMap.remove("CSE2221");
        Pair<String, String> pExpected = mapExpected.remove("CSE2221");

        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
    }

    /**
     * Routine test removing a single entry from a map with multiple entries.
     */
    @Test
    public void removeFilledMakeNonEmpty() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/random.txt");

        /*
         * Call method under test
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }

        Pair<String, String> p = testMap.remove("buck");
        Pair<String, String> pExpected = mapExpected.remove("buck");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
        lineReader.close();
    }

    /**
     * Routing test testing the size of an empty map.
     */
    @Test
    public void testSizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int size = testMap.size();
        /*
         * Assert the values to check if they meet the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(0, size);
    }

    /**
     * Routine test removing an entry and checking the size that map is empty.
     */
    @Test
    public void testRemoveMakeEmptySize() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE2221",
                "Software I");
        Map<String, String> mapExpected = this.createFromArgsRef("CSE2221",
                "Software I");
        /*
         * Call method under test
         */
        Pair<String, String> p = testMap.remove("CSE2221");
        Pair<String, String> pExpected = mapExpected.remove("CSE2221");
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
    }

    /**
     * Routine test removing an entry and checking size of map. Non-Empty.
     */
    @Test
    public void testRemoveFilledMakeNonEmptySize() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE2221",
                "Software I", "CSE2231", "Software II");
        Map<String, String> mapExpected = this.createFromArgsRef("CSE2221",
                "Software I", "CSE2231", "Software II");
        /*
         * Call method under test
         */
        Pair<String, String> p = testMap.remove("CSE2221");
        Pair<String, String> pExpected = mapExpected.remove("CSE2221");

        int size = testMap.size();
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
        assertEquals(1, size);
    }

    /**
     * Routine test with removeAny and a single element making map empty.
     */
    @Test
    public void testRemoveAnyMakeMapEmptySize() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Silicon",
                "Valley");
        Map<String, String> mapExpected = this.createFromArgsRef("Silicon",
                "Valley", "Remove", "This");
        /*
         * Call methods under test
         */
        Pair<String, String> p = testMap.removeAny();
        Pair<String, String> pExpected = mapExpected.remove("Remove");
        if (mapExpected.hasKey(p.key())) {
            pExpected = mapExpected.remove(p.key());
        } else {
            pExpected = p;
        }
        int size = testMap.size();
        /*
         * Assert the values to check if its meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
        assertEquals(0, size);
    }

    /**
     * Routine test with removeAny that does not make the map empty.
     */
    @Test
    public void testRemoveAnyMakeNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Silicon",
                "Valley");
        Map<String, String> mapExpected = this.createFromArgsRef("Silicon",
                "Valley", "Remove", "This");
        /*
         * Call methods under test
         */
        Pair<String, String> p = testMap.removeAny();
        Pair<String, String> pExpected = mapExpected.remove("Remove");
        if (mapExpected.hasKey(p.key())) {
            pExpected = mapExpected.remove(p.key());
        } else {
            pExpected = p;
        }
        /*
         * Assert the values to check if its meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
    }

    /**
     * Challenging/Boundary test with removeAny with a map that has a large
     * amount of + using an external file as an input elements.
     */
    @Test
    public void testRemoveAnyMapWithElements() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("A", "B");
        SimpleReader lineReader = new SimpleReader1L("data/random.txt");

        /*
         * Call method under test
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }

        Pair<String, String> p = testMap.removeAny();
        Pair<String, String> pExpected = mapExpected.remove("A");
        if (mapExpected.hasKey(p.key())) {
            pExpected = mapExpected.remove(p.key());
        } else {
            pExpected = p;
        }
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
        lineReader.close();
    }

    /**
     * Routine test with value and map that has a few elements.
     */
    @Test
    public void testValueWithSmallMap() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Dell", "Desktop",
                "Apple", "iPhone", "Android", "Galaxy");
        Map<String, String> mapExpected = this.createFromArgsTest("Dell",
                "Desktop", "Apple", "iPhone", "Android", "Galaxy");
        /*
         * Call method under test
         */
        String value = testMap.value("Apple");
        String valueExpected = "iPhone";
        assertEquals(mapExpected, testMap);
        assertEquals(valueExpected, value);
    }

    /**
     * Boundary test with value and map that has a large amount of elements.
     */
    @Test
    public void testValueLargeMap() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/startend.txt");

        /*
         * Call method under test
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }

        String value = testMap.value("rheum");
        String valueExpected = "quintic";

        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(valueExpected, value);
        lineReader.close();
    }

    /**
     * Routine test with hasKey with a map that has few elements.
     */
    @Test
    public void testHasKeyWithSmallMap() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Dell", "Desktop",
                "Apple", "iPhone", "Android", "Galaxy");
        Map<String, String> mapExpected = this.createFromArgsRef("Dell",
                "Desktop", "Apple", "iPhone", "Android", "Galaxy");
        /*
         * Call the methods under test
         */
        boolean hasKey = testMap.hasKey("Apple");
        boolean hasKeyExpected = true;
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(hasKeyExpected, hasKey);
    }

    /**
     * Routine test with hasKey with a map that has few elements.
     */
    @Test
    public void testHasKeyWithSmallMap1() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Dell", "Desktop",
                "Apple", "iPhone", "Android", "Galaxy");
        Map<String, String> mapExpected = this.createFromArgsRef("Dell",
                "Desktop", "Apple", "iPhone", "Android", "Galaxy");
        /*
         * Call the methods under test
         */
        boolean hasKey = testMap.hasKey("DellDesktop");
        boolean hasKeyExpected = false;
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(hasKeyExpected, hasKey);
    }

    /**
     * Challenging test with hasKey and a map that has a lot of elements.
     */
    @Test
    public void testHasKeyLargeMap() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/random.txt");

        /*
         * Call method under test
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }
        boolean hasKey = testMap.hasKey("sidestep");
        boolean hasKeyExpected = true;
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(hasKeyExpected, hasKey);
        lineReader.close();
    }

    /**
     * Challenging test with hasKey and a map that has a lot of elements.
     */
    @Test
    public void testHasKeyLargeMap1() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/random.txt");

        /*
         * Call method under test
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }
        boolean hasKey = testMap.hasKey("side step");
        boolean hasKeyExpected = false;
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(hasKeyExpected, hasKey);
        lineReader.close();
    }

    /**
     * Challenging test with hasKey and an element that has been removed from
     * the map.
     */
    @Test
    public void testHasKeyWithSmallMapRemove() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Dell", "Desktop",
                "Apple", "iPhone", "Android", "Galaxy");
        Map<String, String> mapExpected = this.createFromArgsTest("Dell",
                "Desktop", "Apple", "iPhone", "Android", "Galaxy");
        /*
         * Call methods under test
         */
        Pair<String, String> p = testMap.remove("Apple");
        Pair<String, String> pExpected = mapExpected.remove("Apple");
        boolean hasKey = testMap.hasKey("Apple");
        boolean hasKeyExpected = false;
        /*
         * assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(hasKeyExpected, hasKey);
        assertEquals(pExpected, p);
    }

    /**
     * Routine test with size and remove on map with elements that becomes
     * empty.
     */
    @Test
    public void testSizeMapRemoveEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Pied", "Piper");
        Map<String, String> mapExpected = this.createFromArgsRef("Pied",
                "Piper");
        /*
         * Call methods under test
         */
        Pair<String, String> p = testMap.remove("Pied");
        Pair<String, String> pExpected = mapExpected.remove("Pied");
        int size = testMap.size();
        /*
         * Assert the values to check if it meets the expectations
         */
        assertEquals(mapExpected, testMap);
        assertEquals(pExpected, p);
        assertEquals(0, size);

    }

    /**
     * Routine testing size of map with a few elements.
     */
    @Test
    public void testSizeMap3() {
        /*
         * Set up variables
         */
        Map<String, String> testMap = this.createFromArgsTest("Dell", "Desktop",
                "Apple", "iPhone", "Android", "Galaxy");
        Map<String, String> mapExpected = this.createFromArgsTest("Dell",
                "Desktop", "Apple", "iPhone", "Android", "Galaxy");
        /*
         * Call test under test
         */
        int size = testMap.size();
        final int expectedSize = 3;
        /*
         * Assert the values to check if it meets the expectations
         */
        assertEquals(mapExpected, testMap);
        assertEquals(expectedSize, size);
    }

    /**
     * Challenging test with adding a large amount of entries from a file input
     * to map and testing size.
     */
    @Test
    public void testSizeMap350() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/random.txt");

        /*
         * call method under test.
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }

        int size = testMap.size();
        final int expectedSize = 350;
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(expectedSize, size);
        lineReader.close();
    }

    /**
     * Challenging test with adding a large amount of entries from a file input
     * to map and testing size.
     */
    @Test
    public void testSizeMap200() {
        /*
         * Set up the variables
         */
        Map<String, String> testMap = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        SimpleReader lineReader = new SimpleReader1L("data/startEnd400.txt");

        /*
         * call method under test.
         */
        while (!lineReader.atEOS()) {
            String line = lineReader.nextLine();
            String line2 = lineReader.nextLine();
            testMap.add(line, line2);
            mapExpected.add(line, line2);
        }

        int size = testMap.size();
        final int expectedSize = 200;
        /*
         * Assert the values to check if it meets the expectation
         */
        assertEquals(mapExpected, testMap);
        assertEquals(expectedSize, size);
        lineReader.close();
    }

}
