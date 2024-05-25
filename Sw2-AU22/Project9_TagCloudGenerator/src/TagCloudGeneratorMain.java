import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;
import components.utilities.FormatChecker;

/**
 * Generates a tag cloud based off the incoming .txt file. WORK IN PROGRESS
 *
 * @author Aaron Heishman, Sam Espanioly
 */
public final class TagCloudGeneratorMain {

    /**
     * Private classes --------------------------------------------------------
     */

    /**
     * Nested comparator class. Used to sort the incoming words by their word
     * count in descending order. Order is TOTAL_POSTORDER
     */
    private static class IntegerLT
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o2.value().compareTo(o1.value());
        }

    }

    /**
     * Nested comparator class. Used to sort the top n words chosen by the user
     * alphabetically. Order is TOTAL_PREORDER
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }

    }

    /*
     * private final variables-------------------------------------------------
     */

    /**
     * Whitespace separators used to figure out what is a word and what is not.
     */
    private static final String SEPARATORS = " \t\n\r\"\"“”`~,!@#$%^&*()[]{}"
            + ";:,<>./?\\\\|'’-_123456789";
    /**
     * Sorting Machine Order for Map.Pair: TOTAL_POSTORDER. Not sure how to make
     * this less than 90 words - only real option is to add this into the method
     * that uses it.
     */
    private static final Comparator<Map.Pair<String, Integer>> COUNTORDER = new IntegerLT();
    /**
     * Sorting Machine Order for string: TOTAL_PREORDER.
     */
    private static final Comparator<String> WORDORDER = new StringLT();

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloudGeneratorMain() {
        // no code needed here
    }

    /*
     * Public methods-----------------------------------------------------------
     */

    /**
     * Place the top counted words up to from {@code tagCloud} into
     * {@code countSM} that sorts each word in descending order based on the
     * sorting order of {@code COUNTORDER}. Then removes and places n amount of
     * words into {@code alphabetSM} and sorts the words based on the sorting
     * order {@code WORDORDER}.
     *
     * @param map
     *            - All words counted
     * @param n
     *            - The number of words to be processed
     * @updates tagCloud
     * @return [A Sorting Machine containing the top n words sorted
     *         alphabetically]
     * @requires |tagCloud| > 0
     * @requires 0 < n <= |map|
     * @ensures <pre>[{@code alpabetSM] contains the first n
     * words with the highest count sorted alphabetically and {@code tagCloud}
     * contains the same words found in {@code AlphabetSM} but also including
     * their counts] </pre>
     */
    public static SortingMachine<String> createSM(Map<String, Integer> map,
            int n) {
        assert n > 0 : "Violation of n > 0";
        assert map.size() > 0 : "Violation of |tagCloud| > 0";
        assert n <= map.size() : "Violation of n <= |map|";
        // Sorting machine to sort the words by count
        SortingMachine<Pair<String, Integer>> countSM = new SortingMachine1L<>(
                COUNTORDER);
        // Sorting machine to sort the top n words alphabetically
        SortingMachine<String> alphabetSM = new SortingMachine1L<>(WORDORDER);
        // add all entries from map to count sorting machine
        while (map.size() > 0) {
            countSM.add(map.removeAny());
        }
        // sort word count in descending order
        countSM.changeToExtractionMode();
        /*
         * add the top n words to {@code alphabetSM} and the top n words and
         * their word count to {@code map}
         */
        for (int i = 0; i < n; i++) {
            Pair<String, Integer> wordPair = countSM.removeFirst();
            map.add(wordPair.key(), wordPair.value());
            alphabetSM.add(wordPair.key());
            i++;
        }
        // sort words alphabetically
        alphabetSM.changeToExtractionMode();
        // return the sorting machine
        return alphabetSM;
    }

    /**
     * Creates a map that will contain all words from the input file and their
     * total count of appearance in the file excluding any white space
     * characters or numbers.
     *
     * @param fileIn
     *            input stream for reading the file
     * @return A map containing all words found in {@Code fileIn}
     * @requires fileIn.isOpen
     * @ensures <pre>
     *          [{@code tagMap} contains each word found in the file to be
     *          read and the corresponding word count for each word]
     *          </pre>
     */
    public static Map<String, Integer> createMap(SimpleReader fileIn) {
        assert fileIn.isOpen() : "Violation of fileIn.isOpen";
        // map that will hold all words and their counts
        Map<String, Integer> tagMap = new Map1L<>();
        // loop until end of file
        while (!fileIn.atEOS()) {
            // the line being read
            String inLine = fileIn.nextLine();
            // starting position for finding a word
            int pos = 0;
            // loop until the string has been processed
            while (pos < inLine.length()) {
                // the word to add to the map if it is not a separator
                String word = nextWordOrSeparator(inLine, pos);
                // check to make sure the word is not a separator
                if (!Character.isWhitespace(word.charAt(0))
                        && SEPARATORS.indexOf(word) < 0) {
                    // check if word is already in map or not
                    if (!tagMap.hasKey(word)) {
                        // add word to map
                        tagMap.add(word, 1);
                    } else {
                        /*
                         * the word is in the map already - update its count by
                         * one
                         */
                        Pair<String, Integer> mapPair = tagMap.remove(word);
                        tagMap.add(mapPair.key(), mapPair.value() + 1);
                    }
                    // update position to check next word or separator
                    pos += word.length();
                } else {
                    // the word was a separator update pos by one
                    pos++;
                }
            }
        }
        // return the map
        return tagMap;
    }

    /**
     * Returns the first "word" or a whitespace in the given {@code text} not
     * found in {@code separators} starting at the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or white space.
     * @param position
     *            the starting index
     * @return the first word, or white space string found in {@code text}
     *         starting at index {@code position}
     * @requires 0 <= position < |text|
     * @requires text is not null;
     * @ensures <pre>
     *          [nextWordOrSeparator will either be the word found in
     *          {@code text} or a separator found in {@code SEPARATOR}]
     *          </pre>
     */
    public static String nextWordOrSeparator(String text, int position) {
        assert text != null : "Violation of: text is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        /*
         * Checks to make sure if the first character at the given position is a
         * separator character or not, if it is not then iterate through the
         * string character by character adding to nextWordORseparator until a
         * separator character is found.
         */
        StringBuilder wordOrSeparator = new StringBuilder();
        int i = position;
        if (SEPARATORS.indexOf(text.charAt(i)) < 0
                && !Character.isWhitespace(text.charAt(i))) {
            while (i < text.length() && SEPARATORS.indexOf(text.charAt(i)) < 0
                    && !Character.isWhitespace(text.charAt(i))) {
                // convert character to lowercase and append to wordOrSeparator
                wordOrSeparator.append(Character.toLowerCase(text.charAt(i)));
                i++;
            }
        } else {
            // character was a separator - return the separator
            wordOrSeparator.append(text.charAt(i));
        }
        return wordOrSeparator.toString();
    }

    /**
     * Return a string of text containing the font code identifier based on how
     * many times a word was found in {@code fileIn{}. The more words counted,
     * the bigger the font size.
     *
     * @param n - the count of each individual word
     *
     * @return result = a code correlating to the amount of times each word has
     *         been counted
     * @requires n > 0
     * @ensures <pre>
     *          [{@code fontCode] is a string corresponding to how large n is.]
     *          </pre>
     */
    public static String getFontSize(int n) {
        assert n > 0 : "Violation of n > 0";
        // fontCode to be returned
        String fontCode = "";
        // font code representations. the bigger n is, the bigger the font size
        final int f11 = 50, f12 = 75, f13 = 100, f14 = 125, f15 = 150,
                f16 = 175, f17 = 200, f18 = 225, f19 = 250, f20 = 275,
                f23 = 300, f25 = 350, f27 = 420, f30 = 500, f35 = 600;
        // find fond size
        if (n <= f11) {
            fontCode = "f11";
        } else if (n <= f12) {
            fontCode = "f12";
        } else if (n <= f13) {
            fontCode = "f13";
        } else if (n <= f14) {
            fontCode = "f14";
        } else if (n <= f15) {
            fontCode = "f15";
        } else if (n <= f16) {
            fontCode = "f16";
        } else if (n <= f17) {
            fontCode = "f17";
        } else if (n <= f18) {
            fontCode = "f18";
        } else if (n <= f19) {
            fontCode = "f19";
        } else if (n <= f20) {
            fontCode = "f20";
        } else if (n <= f23) {
            fontCode = "f23";
        } else if (n <= f25) {
            fontCode = "f25";
        } else if (n <= f27) {
            fontCode = "f27";
        } else if (n <= f30) {
            fontCode = "f30";
        } else if (n <= f35) {
            fontCode = "f35";
        } else {
            fontCode = "f38";
        }
        // return the font code related to the size n
        return fontCode;
    }

    /**
     * Output to file the Header of the web page.
     *
     * @param fileOut
     *            - output stream
     * @param fileName
     *            - name of the output file
     * @param n
     *            - number of words processed
     * @ensures <pre>
     * [{{@code fileOut} prints the corresponding html text to display
     * the header]
     * </pre>
     */
    public static void outputHeader(SimpleWriter fileOut, String fileName,
            int n) {
        fileOut.println("<html>");
        fileOut.println("<head>");
        fileOut.println(
                "<title>Top " + n + " words in " + fileName + "</title>");
        fileOut.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/223"
                        + "1/web-sw2/assignments/projects/tag-cloud-generator/data/tagcl"
                        + "oud.css\" rel=\"stylesheet\" type=\"text/css\">");
        fileOut.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        fileOut.println("</head>");

    }

    /**
     * Output to file the corresponding html to create the body.
     *
     * @param fileOut
     *            - output stream
     * @param sm
     *            - Top counted words sorted alphabetically
     * @param map
     *            - Map containing the top counted words and their corresponding
     *            word count
     * @param fileName
     *            - the name of the output file
     * @clears {@code map}
     * @requires fileOut.isOpen
     * @requires |alphabetSM| > 0
     * @requires |tagCloud| == |alphabetSM|
     * @requires fileName is not null
     * @requires n > 0
     * @ensures <pre> [{@code fileOut} prints the corresponding HTML text needed
     *          to create the body for the output file]
     *          </pre>
     */
    public static void outputBody(SimpleWriter fileOut,
            SortingMachine<String> sm, Map<String, Integer> map,
            String fileName) {
        assert fileOut.isOpen() : "Violation of fileOut.isOpen";
        assert sm.size() > 0 : "Violation of |alphabetSM > 0|";
        assert map.size() == sm.size() : ""
                + "Violation of |tagCloud| == |alphabetSM|";
        assert fileName != null : "Violation of fileName is null";
        // initial body set up
        fileOut.println(
                "<h2>Top " + map.size() + " words in " + fileName + "</h2>");
        fileOut.println("<body>");
        fileOut.println("<hr>");
        fileOut.println("<div class =\"cdiv\">");
        fileOut.println("<p class =\"cbox\">");
        // output html used to create tag cloud
        while (sm.size() > 0) {
            // get word and count pair
            Pair<String, Integer> wordPair = map.remove(sm.removeFirst());
            // get expected font size of the word based on count
            String fontSize = getFontSize(wordPair.value());
            // output to file the html code
            fileOut.println("<span style=\"cursor:default\" class=\"" + fontSize
                    + "\" title=\"count: " + wordPair.value() + "\">"
                    + wordPair.key() + "</span>");
        }
    }

    /**
     * Output to file the footer.
     *
     * @param fileOut
     *            - output stream
     * @requires fileOut.isOpen
     * @ensures <pre> [{@code fileOut} prints to the output file the required
     *          footer html text]
     *          </pre>
     */
    public static void outputFooter(SimpleWriter fileOut) {
        fileOut.println("</p>");
        fileOut.println("</dive>");
        fileOut.println("</body>");
        fileOut.println("</html>");
    }

    /**
     * Requests file used to process the tag cloud, the output destination of
     * the generated tag cloud and the number of words to be processed.
     *
     * @param in
     *            input stream
     * @param out
     *            output stream
     * @return An array that will hold the input file name and destination, the
     *         output file name and destination and the number of words to be
     *         processed into the tag cloud
     * @requires in.isOpen
     * @requires out.isOpen
     * @ensures <pre> [{@code inAndOut} contains the file to be processed at
     *          inAndOut[0], the file destination for the processed tagCloud in
     *          inAndOut[1] and the number (n) of words to be generated in
     *          inAndOut[2}]
     *          </pre>
     */
    public static String[] getIO(SimpleReader in, SimpleWriter out) {
        assert in.isOpen() : "Violation of: in is open";
        assert out.isOpen() : "Violation of: out is open";
        // Length of the array
        final int arrayLength = 3;
        // Array that will hold the input from the user
        String[] inAndOut = new String[arrayLength];
        // ask user for input destination and file type
        out.print("Enter the name of the file containing the terms: ");
        // add the input destination and file name to position 0
        inAndOut[0] = in.nextLine();
        // Repeatedly ask for a non-empty input file name and destination
        while (inAndOut[0].length() == 0) {
            out.print(
                    "Enter a non-empty name for the file containing the terms: ");
            // add the input destination and file name to position 0
            inAndOut[0] = in.nextLine();
        }
        // ask user for output destination and file type
        out.print("Enter the name of the desired folder for output: ");
        // add the output destination string to position 1
        inAndOut[1] = in.nextLine();
        // repeatedly ask for a non-empty output file name and destination
        while (inAndOut[1].length() == 0) {
            out.print(
                    "Enter a non-empty name for the desired folder for output: ");
            // add the output destination string to position 1
            inAndOut[1] = in.nextLine();
        }

        // ask the user for the amount of words to generate
        out.print(
                "Enter the number of words to be included in the tag cloud: ");
        String validNumber = in.nextLine();
        /*
         * loop check to verify that the value entered is a valid integer, and
         * that valid integer is greater than 0
         */
        while (!FormatChecker.canParseInt(validNumber)
                || Integer.parseInt(validNumber) == 0) {
            // error message if the value is not valid
            out.print("Invalid entry " + validNumber
                    + " must be a valid integer greater than 0.");
            out.print(
                    "Enter the number of words to be included in the tag cloud: ");
            validNumber = in.nextLine();
        }
        // add the entry into the last position in inAndOut
        inAndOut[2] = validNumber;
        // return the array
        return inAndOut;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file to be processed, output destination for processed
         * file, and amount of words to process. [i]nput, [o]put
         */
        String[] ioArray = getIO(in, out);
        // input stream used to read from file
        SimpleReader fileIn = new SimpleReader1L(ioArray[0]);
        // output stream used to generate the tag cloud
        SimpleWriter fileOut = new SimpleWriter1L(ioArray[1]);
        // update user that file is being processed
        out.println("Processing the file found at: " + ioArray[0]);
        // Create the tag cloud map
        Map<String, Integer> tagCloudMap = createMap(fileIn);
        /*
         * Sort each word from greatest to smallest count and then sort the top
         * n words alphabetically and place back into @{tagCloudMap}
         */
        SortingMachine<String> alphabetSM = createSM(tagCloudMap,
                Integer.parseInt(ioArray[2]));
        // output the header to file
        outputHeader(fileOut, ioArray[1], Integer.parseInt(ioArray[2]));
        // output the body to file
        outputBody(fileOut, alphabetSM, tagCloudMap, ioArray[1]);
        // output the footer to body
        outputFooter(fileOut);
        // update user that the file is done processing and where it can be found
        out.println("The file has been processed: " + ioArray[1]);
        // close input/output streams
        fileIn.close();
        fileOut.close();
        in.close();
        out.close();
    }

}
