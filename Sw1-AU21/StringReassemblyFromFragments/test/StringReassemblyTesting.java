import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class StringReassemblyTesting {

    @Test
    public void testOfCombiningMethod1() {
        String str1 = "a";
        String str2 = "bcdefg";
        String str1Exp = "a";
        String str2Exp = "bcdefg";
        int over = 0;
        int overExp = 0;
        String res = StringReassembly.combination(str1, str2, over);
        assertEquals(str1Exp, str1);
        assertEquals(str2Exp, str2);
        assertEquals(overExp, over);
        assertEquals("abcdefg", res);
        //testing boundary small string
    }

    @Test
    public void testOfCombiningMethod2() {
        String str1 = "5";
        String str2 = "457864155";
        int over = 0;
        String str1Exp = "5";
        String str2Exp = "457864155";
        int overExp = 0;
        String res = StringReassembly.combination(str1, str2, over);
        assertEquals(str1Exp, str1);
        assertEquals(str2Exp, str2);
        assertEquals(overExp, over);
        assertEquals("5457864155", res);
        //special challenging testing with numbers
    }

    @Test
    public void testOfCombiningMethod21() {
        String str2 = "55";
        String str1 = "457864155";
        int over = 2;
        String str2Exp = "55";
        String str1Exp = "457864155";
        int overExp = 2;
        String res = StringReassembly.combination(str1, str2, over);
        assertEquals(str1Exp, str1);
        assertEquals(str2Exp, str2);
        assertEquals(overExp, over);
        assertEquals("457864155", res);
    }
    //Routine testing something regular

    @Test
    public void testOfCombiningMethod22() {
        String str1 = "milkkkkkk";
        String str2 = "kkkkkk shake";
        int over = 6;
        String str1Exp = "milkkkkkk";
        String str2Exp = "kkkkkk shake";
        int overExp = 6;
        String res = StringReassembly.combination(str1, str2, over);
        assertEquals(str1Exp, str1);
        assertEquals(str2Exp, str2);
        assertEquals(overExp, over);
        assertEquals("milkkkkkk shake", res);
    }
    //Routine testing something regular and boundary since testing high value for overlap

    @Test
    public void testOfCombiningMethod3() {
        String str1 = "*/8*-543tyre7h8g9f";
        String str2 = "fggggg45";
        int over = 1;
        String str1Exp = "*/8*-543tyre7h8g9f";
        String str2Exp = "fggggg45";
        int overExp = 1;
        String res = StringReassembly.combination(str1, str2, over);
        assertEquals(str1Exp, str1);
        assertEquals(str2Exp, str2);
        assertEquals(overExp, over);
        assertEquals("*/8*-543tyre7h8g9fggggg45", res);
        //special challenging testing with strange numbers and marks could also be boundary testing since its a big string
    }

    @Test
    public void testOfCombiningMethod4() {
        String str1 = "";
        String str1Exp = "";
        String str2 = "fggggg45";
        String str2Exp = "fggggg45";
        int over = 0;
        int overExp = 0;
        String res = StringReassembly.combination(str1, str2, over);
        assertEquals(str1Exp, str1);
        assertEquals(str2Exp, str2);
        assertEquals(overExp, over);
        assertEquals("fggggg45", res);
        //testing with empty string this is boundary testing
    }

    /**
     * Routine test of combination.
     */
    @Test
    public void testCombinations() {
        String str1 = "ronitkumarhasswag";
        String str2 = "swagsoreal";
        int overlap = 4;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("ronitkumarhasswagsoreal", result);
    }

    /**
     * Routine test of combination.
     */
    @Test
    public void testCombinations2() {
        String str1 = "removethis";
        String str2 = "thisshouldberemoved";
        int overlap = 4;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("removethisshouldberemoved", result);
    }

    /**
     * Routine and boundary for the strings and overlap being big test of
     * combination.
     */
    @Test
    public void testCombinations3() {
        String str1 = "letstrybothstringsbeingthesame";
        String str2 = "letstrybothstringsbeingthesame";
        int overlap = 30;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("letstrybothstringsbeingthesame", result);
    }

    /**
     * Routine test of combination.
     */
    @Test
    public void testCombinations4() {
        String str1 = "letstryoneletter";
        String str2 = "rfromthequeenAgain";
        int overlap = 1;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals("letstryoneletterfromthequeenAgain", result);
    }

    /**
     * Routine + Challenging because it's in the middle of
     * addToSetAvoidingSubstrings.
     */
    @Test
    public void testaddToSetAvoidingSubstrings() {
        Set<String> strset = new Set1L<String>();
        strset.add("Heyyo");
        strset.add("Hey Mannn?");
        strset.add("Chillin");
        Set<String> strset2 = new Set1L<String>();
        strset2.add("Heyyo");
        strset2.add("Hey Mannn?");
        strset2.add("Chillin");
        String str = "Mannn";
        StringReassembly.addToSetAvoidingSubstrings(strset, str);
        assertEquals(strset, strset2);
    }

    /**
     * Routine and Boundary since there's more than two in the set test of
     * addToSetAvoidingSubstrings.
     */
    @Test
    public void testaddToSetAvoidingSubstrings2() {
        Set<String> strset = new Set1L<String>();
        strset.add("Heyyo");
        strset.add("Hey Mannn?");
        strset.add("Chillin");
        Set<String> strset2 = new Set1L<String>();
        strset2.add("Nussa");
        strset2.add("Heyyo");
        strset2.add("Hey Mannn?");
        strset2.add("Chillin");
        String str = "Nussa";
        StringReassembly.addToSetAvoidingSubstrings(strset, str);
        assertEquals(strset, strset2);
    }

    @Test
    public void testCombinationEmpty() {
        String str1 = "";
        String str2 = "";
        String result = StringReassembly.combination(str1, str2, 0);
        assertTrue(result.isEmpty());
        assertTrue(str1.equals(""));
    }

    //Boundary for both strings being empty + challenging since theres nothing
    @Test
    //Routine + Boundary for one of the strings
    public void testCombinationOneEmpty() {
        String str1 = "first";
        String str2 = "";
        String result = StringReassembly.combination(str1, str2, 0);
        String result2 = StringReassembly.combination(str2, str1, 0);
        assertTrue(result.equals("first"));
        assertTrue(result2.equals("first"));
        assertTrue(str1.equals("first"));
        assertTrue(str2.equals(""));
    } // boundary one empty

    @Test
    //Routine
    public void testCombinationNoOverlap() {
        String str1 = "first";
        String str2 = "second";
        String result = StringReassembly.combination(str1, str2, 0);
        String result2 = StringReassembly.combination(str2, str1, 0);
        assertTrue(result.equals("firstsecond"));
        assertTrue(result2.equals("secondfirst"));
        assertTrue(str1.equals("first"));
        assertTrue(str2.equals("second"));
    }

    @Test
    //Routine + Boundary since I used a low number on the overlapping
    public void testCombinationOneOverlap() {
        String str1 = "firs";
        String str2 = "second";
        String result = StringReassembly.combination(str1, str2, 1);
        String result2 = StringReassembly.combination(str2, str1, 0);
        assertTrue(result.equals("firsecond"));
        assertTrue(result2.equals("secondfirs"));
        assertTrue(str1.equals("firs"));
        assertTrue(str2.equals("second"));
    }

    @Test
    //Routine
    public void testCombinationTwoOverlap() {
        String str1 = "chase";
        String str2 = "second";
        String result = StringReassembly.combination(str1, str2, 2);
        assertTrue(result.equals("chasecond"));
        assertTrue(str1.equals("chase"));
        assertTrue(str2.equals("second"));
    }

    @Test
    //Routine + challenging since theres a mix of numbers and letters
    public void testCombinationTenOverlap() {
        String str1 = "12345678910";
        String str2 = "2345678910a";
        String result = StringReassembly.combination(str1, str2, 10);
        assertTrue(result.equals("12345678910a"));
        assertTrue(str1.equals("12345678910"));
        assertTrue(str2.equals("2345678910a"));
    }

    @Test
    //Routine
    public void testaddToSetAvoidingSubstringsNotContained() {
        //test the method when the string is not contained
        String str1 = "123";
        String str2 = "456";
        Set<String> test = new Set1L<String>();
        test.add(str1);
        test.add(str2);
        String str3 = "64";
        StringReassembly.addToSetAvoidingSubstrings(test, str3);

        assertTrue(test.contains(str3));
        assertTrue(test.size() == 3);
        assertTrue(test.contains(str1));
        assertTrue(test.contains(str2));

    }

    @Test
    //Routine
    public void testaddToSetAvoidingSubstringsNotContained2() {
        //test the method when the string is not contained
        String str1 = "making tests";
        String str2 = " equals.boring";
        Set<String> test = new Set1L<String>();
        test.add(str1);
        test.add(str2);
        String str3 = "I want to play MineCraft";
        StringReassembly.addToSetAvoidingSubstrings(test, str3);

        assertTrue(test.contains(str3));
        assertTrue(test.size() == 3);
        assertTrue(test.contains(str1));
        assertTrue(test.contains(str2));

    }

    @Test
    //Routine
    public void testaddToSetAvoidingSubstringsContained() {
        //test when the string is contained
        String str1 = "123";
        String str2 = "456";
        Set<String> test = new Set1L<String>();
        test.add(str1);
        test.add(str2);
        String str3 = "5";
        StringReassembly.addToSetAvoidingSubstrings(test, str3);
        assertTrue(test.size() == 2);
        assertTrue(test.contains(str1));
        assertTrue(test.contains(str2));

    }

    @Test
    //Routine
    public void testaddToSetAvoidingSubstringsContained2() {
        //test when the string is a copy of another
        String str1 = "this is a test";
        String str2 = "this is also a test";
        Set<String> test = new Set1L<String>();
        test.add(str1);
        test.add(str2);
        String str3 = "this is also a test";
        StringReassembly.addToSetAvoidingSubstrings(test, str3);
        assertTrue(test.size() == 2);
        assertTrue(test.contains(str1));
        assertTrue(test.contains(str2));

    }

    @Test
    //Routine
    public void testaddToSetAvoidingSubstringsContained3() {
        //test when a string is a substring of multiple
        String str1 = "this is a test";
        String str2 = "this is also a test";
        Set<String> test = new Set1L<String>();
        test.add(str1);
        test.add(str2);
        String str3 = "this is";
        StringReassembly.addToSetAvoidingSubstrings(test, str3);
        assertTrue(test.size() == 2);
        assertTrue(test.contains(str1));
        assertTrue(test.contains(str2));

    }

}
