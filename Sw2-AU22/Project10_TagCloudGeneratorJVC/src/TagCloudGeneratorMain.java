import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Program that reads an input file and outputs a new file with the required
 * HTML tags to create an alphabetically sorted Tag Cloud. Process N amount of
 * words chosen by the user. Uses only Java Components
 *
 * @author Aaron Heishman, Sam Espanioly
 */
public final class TagCloudGeneratorMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGeneratorMain() {
    }

    /**
     * Nested comparator class. Used to sort each word based on descending order
     * of their count. Order is TOTAL_POSTORDER
     */
    private static class IntegerLT implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
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
    private static final String SEPARATORS = " \t\n\r,-.!?[]';:/()_*\"\\`#$%_”"
            + "1234567890";
    /**
     * Comparator used to sort word count in descending order.
     */
    private static final Comparator<Integer> COUNTORDER = new IntegerLT();
    /**
     * Comparator used to sort wounds alphabeticall in ascending order.
     */
    private static final Comparator<String> WORDORDER = new StringLT();

    /*
     * private methods -----------------------------------------------------
     */

    /**
     * Returns the first word in the given {@code text} not found in
     * {@code separators} starting at the given {@code position} or returns the
     * first SEPARATOR in {@code text} found in {@code SEPARATORS}.
     *
     * @param text
     *            the {@code String} from which to get the word or white space.
     * @param pos
     *            the starting index
     * @return nextWordOrSeparator = the first word, or SEPARATOR string found
     *         in {@code text} starting at index {@code position}
     * @requires 0 <= position < |text|
     * @requires text is not null;
     * @ensures <pre>
     *          [nextWordOrSeparator will either be the word found in
     *          {@code text} or a separator found in {@code SEPARATOR}]
     *          </pre>
     */
    private static String nextWordOrSeparator(String text, int pos) {
        assert text != null : "Violation of text is not null";
        assert pos <= text.length() : "Violation of position < |text|";
        // string builder holds the string to be returned
        StringBuilder sb = new StringBuilder();
        if (SEPARATORS.indexOf(text.charAt(pos)) < 0) {
            // position used to iterate over text
            int i = pos;
            /*
             * iterate over string to find first word - stop when separator is
             * found or i = |text|
             */
            while (i < text.length()
                    && SEPARATORS.indexOf(text.charAt(i)) < 0) {
                sb.append(text.charAt(i));
                i++;
            }
        } else {
            // text at pos is a separator - append to sb and return;
            sb.append(text.charAt(pos));
        }
        return sb.toString();
    }

    /**
     * Reads the incoming file creating a map that will contain the word count
     * of each individual word located in {@code fileReader}.
     *
     * @param fileReader
     *            - input stream to read file
     * @return createMap = [A map that contains individual words found in the
     *         file as the keys with the amount of times they were counted in
     *         the values]
     * @throws IOException
     *             - if the file does not exist,is a directory rather than a
     *             regular file,or for some other reason cannot be opened for
     *             reading.
     * @ensures <pre>
     *          [Map.Key = individual words found in file; map.collection =
     *          an integer value containing each words count]
     *          </pre>
     */
    private static Map<String, Integer> createMap(BufferedReader fileReader)
            throws IOException {
        // Map that will hold each word and their individual word counts
        Map<String, Integer> fqMap = new HashMap<>();
        // current line being read by input stream
        String currLine = fileReader.readLine();
        // loop until the end of the text
        while (currLine != null) {
            // Get word or separator
            int pos = 0;
            /*
             * loop over the current line finding each word and add them to the
             * map
             */
            while (pos < currLine.length()) {
                // returned word or separator
                String s = nextWordOrSeparator(currLine, pos);
                /*
                 * if word is not a separator, add word to map (or update word
                 * count), else update pos by 1
                 */
                if (SEPARATORS.indexOf(s.charAt(0)) < 0) {
                    // entry set for the map for removing/adding to map
                    Set<String> es = fqMap.keySet();
                    // convert the word to lowercase
                    String lcWord = s.toLowerCase();
                    /*
                     * if word is not in map then add it to map with count of
                     * one, else update the count of the word
                     */
                    if (!es.contains(lcWord)) {
                        fqMap.put(lcWord, 1);
                    } else {
                        // remove the key and its count
                        int oldCount = fqMap.remove(lcWord);
                        // add the key and its count + 1 back to map
                        fqMap.put(lcWord, oldCount + 1);
                    }
                    // update pos by the length of the word to get next word
                    pos += lcWord.length();
                } else {
                    // word was a separator - update pos by one
                    pos++;
                }
            }
            // update current line
            currLine = fileReader.readLine();
        }
        // return the map
        return fqMap;
    }

    /**
     * Returns a queue that contains the alphabetically sorted most frequently
     * used words (chosen by the user).
     *
     * @param m
     *            - the map containing all the read words and their counts
     * @param stdIn
     *            - input stream [read from keyboard]
     * @return the alphabetically sorted queue
     * @updates m
     * @ensures <pre>
     *          createAndSortQueue = [The top frequently used words from
     *          file in alphabetical order]]
     *          </pre>
     */
    private static Queue<String> createQueue(Map<String, Integer> m,
            Scanner stdIn) {
        // Output to console
        outputMessage("Program has found " + m.size() + " consecutive words.");
        outputMessage("Enter the amount of words to be processed:");
        // String input
        String valString = stdIn.nextLine();
        // Loop until user chooses a valid integer
        while (!isInt(valString, m.size())) {
            // caught exception - invalid entry
            outputMessage("Error: " + valString
                    + " is either not an integer or outside of range: "
                    + m.size());
            outputMessage("Enter the amount of words to be processed:");
            valString = stdIn.nextLine();
        }
        //output message to console
        outputMessage("Processing tag cloud. This may take some time...");
        // parse the integer from {@code ValString} into {@code numToProcess}
        int numToProcess = Integer.parseInt(valString);
        // Map to hold all the highest counted keys
        Map<String, Integer> scMap = new HashMap<>();
        // sorted queue that will hold the alphabetically sorted words
        Queue<String> wordQueue = new LinkedList<>();
        // loop until i = numToProcess adding highest counted words to scMap
        for (int i = 0; i < numToProcess; i++) {
            // word count of frequently used words
            int count = 0;
            // name of the word counted
            String key = "";
            // iterate through map to find the highest counts
            for (Entry<String, Integer> es : m.entrySet()) {
                // check if the current value is the largest in map
                if (COUNTORDER.compare(es.getValue(), count) < 0
                        && !scMap.containsKey(es.getKey())) {
                    // update count and key
                    count = es.getValue();
                    key = es.getKey();
                }
            }
            // add the word and count the map
            scMap.put(key, count);
            // add to queue for sorting
            wordQueue.add(key);
        }
        // Clearing the old map for scaling purposes
        m.clear();
        // put the total words being processed into the new map (all immuatable)
        m.putAll(scMap);
        // sort the queue before returning it
        sortQueue(wordQueue, scMap);
        // return the sorted queue
        return wordQueue;
    }

    /**
     * Sorts the incoming {@code q} in ascending alphabetical order.
     *
     * @param q
     *            - the queue to be sorted
     * @param m
     *            - the map containing the most frequently used words
     * @clears m
     * @updates q
     * @ensures <pre>
     *          [sortQueue = is sorted in alphabetical order]
     *          </pre>
     */
    private static void sortQueue(Queue<String> q, Map<String, Integer> m) {
        // temporary queue to hold entries
        Queue<String> sortedQ = new LinkedList<>();
        while (q.size() > 0) {
            // get first entry
            String s = q.remove();
            /*
             * check if the map contains the word - toss it if not (may already
             * have been sorted)
             */
            if (m.containsKey(s)) {
                /**
                 * iterate over the entry set of sc map to get the smallest word
                 * in the map first, largest last.
                 */
                for (Entry<String, Integer> es : m.entrySet()) {
                    // compare the entries in es.getKey() to s
                    if (!s.equals(es.getKey())
                            && WORDORDER.compare(s, es.getKey()) > 0) {
                        /*
                         * add word back to queue because a smaller word took
                         * its place
                         */
                        q.add(s);
                        // update s to hold the smaller word
                        s = es.getKey();
                    }
                }
                // add word to sorted queue
                sortedQ.add(s);
                // remove the key and value that is mapped to s from map
                m.remove(s, m.get(s));
            }
        }
        q.addAll(sortedQ);
    }

    /**
     * Checks if the string inside {@code s} is an integer that can be parsed.
     * Returning true if it is, false otherwise.
     *
     * @param s
     *            - The string to parse
     * @param tcSize
     *            - the total amount of words found in
     * @return true if the {@code S} can be parsed to an integer and is within
     *         the range of [0, tcSize], false otherwise.
     * @requires s is not null
     * @ensures <pre> isInt = [false if the interger is not parseable to an int,
     *          or less than 0 or greater than tcSize, else true]
     *          </pre>
     */
    private static boolean isInt(String s, int tcSize) {
        assert s != null : "Violation of s is not null";
        // bolean to return
        boolean isInt = true;
        // try to parse int - catch IOException if not possible
        try {
            // Check if integer is between the range of (0, tcSize]
            if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > tcSize) {
                isInt = false;
            }
        } catch (NumberFormatException e) {
            /*
             * string could not be parsed, or was negative or larger than
             * tcsSize
             */
            isInt = false;
        }
        return isInt;
    }

    /**
     * Display message to console asking for the input file to be read and
     * processed.
     *
     * @param stdIn
     *            - console input stream
     * @return [fileIn = user chosen file]
     * @ensures <pre>
     *          [File to be read exists and fileIn = user chosen file]
     *          </pre>
     */
    private static File getFileIn(Scanner stdIn) {
        // output to console
        outputMessage("Enter the file to read:");
        // File to be processed
        File fileIn = new File(stdIn.nextLine());
        // if file does not exist then repeatedly ask for an existing file
        while (!fileIn.isFile()) {
            outputMessage(fileIn + " does not exist.");
            outputMessage("Enter the file to read:");
            fileIn = new File(stdIn.nextLine());
        }
        return fileIn;
    }

    /**
     * Ask the user for the destination of the output file.
     *
     * @param stdIn
     *            - keyboard input stream
     * @return - Either the file destination * filename || filename
     * @ensures <pre>
     *          [File returned will contain the output path destination
     *          and file name]
     *          </pre>
     */
    private static File getFileOut(Scanner stdIn) {
        // Output to console
        outputMessage("Enter output file destination:");
        // returns the file;
        return new File(stdIn.nextLine());
    }

    /**
     * Prints the required HTML tags used to generate the Tag Cloud to the file
     * located at {@code f}.
     *
     * @param map
     *            - the tag cloud map
     * @param q
     *            - the alphabetically sorted queue
     * @param fileOut
     *            - the file containing output destination and file name
     * @throws IOException
     *             - Signals that an I/O exception of some sort has occurred.
     *             Thisclass is the general class of exceptions produced by
     *             failed orinterrupted I/O operations.
     * @clears q
     * @ensures processOutput = [HTML header tags * CSS style for formatting *
     *          HTML body tags [the tag cloud] * HTML footer tags]
     */
    private static void processOutput(Map<String, Integer> map, Queue<String> q,
            File fileOut) throws IOException {
        // maximum and minimum font sizes
        final int fontMax = 48, fontMin = 11;
        // variable that will hold the size of the most frequently used word
        int maxCount = 0;
        // find the most frequently used word and get its value
        for (Map.Entry<String, Integer> es : map.entrySet()) {
            maxCount = Math.max(maxCount, es.getValue());
        }
        // initialize print writer
        PrintWriter stdOut = new PrintWriter(
                new BufferedWriter(new FileWriter(fileOut)));
        // output header
        stdOut.println("<html>");
        stdOut.println("<head>");
        stdOut.println(
                "<title>Top " + q.size() + " words in " + fileOut + "</title>");
        // next two lines contain the CSS information
        stdOut.println("<link href=\"http://web.cse.ohio-state.edu/software/223"
                + "1/web-sw2/assignments/projects/tag-cloud-generator/data/tagcl"
                + "oud.css\" rel=\"stylesheet\" type=\"text/css\">");
        stdOut.println("<link href=\"tagcloud.css\" rel=\"stylesheet\" "
                + "type=\"text/css\">");
        // output body (words and counts from map)
        stdOut.println("</head>");
        stdOut.println("<body>");
        stdOut.println(
                "<h2>Top " + q.size() + " words in " + fileOut + "</h2>");
        stdOut.println("<hr>");
        stdOut.println("<div class=\"cdiv\">");
        stdOut.println("<p class=\"cbox\">");
        // prints the words, their font size, and their count
        while (q.size() > 0) {
            String s = q.remove();
            /*
             * linear formula to get the font size based on the most frequently
             * used word - [ current word count / most frequently used word
             * count) * (difference between max and min font) + min font]
             */
            double fontSize = (map.get(s) * 1.0 / maxCount)
                    * (fontMax - fontMin) + fontMin;
            stdOut.println("<span style=\"cursor:default\" class=\"" + "f"
                    + (int) fontSize + "\" title=\"count: " + map.get(s) + "\">"
                    + s + "</span>");
        }
        // output the footer to file
        stdOut.println("</p>");
        stdOut.println("</div>");
        stdOut.println("</body>");
        stdOut.println("</html>");
        stdOut.close();
        // display message that tag cloud has been generated successfully
        outputMessage("File has been generated successfuly");
    }

    /**
     * Displays to console with the appropriate message depending on if
     * {@code s} contains ':' at the end or not.
     *
     * @param s
     *            - the incoming message to display to console
     * @requires s is not null
     * @ensures <pre>
     *          outputMessage = [The message displayed without a new line if ':'
     *          is at the end of the message. If there is no ':' at the end then
     *          displays the message with a newline.]
     *          </pre>
     */
    private static void outputMessage(String s) {
        assert s != null : "Violation of s is not null";
        /*
         * messages with only ":" at the end of the message are meant for
         * getting input. All other messages output are used to notify the user
         * the progression of the program. I added this just for fun.
         */
        if (s.charAt(s.length() - 1) == ':') {
            System.out.print(s);
        } else {
            System.out.println(s);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        // keyboard scanner (closed after input from user is not needed)
        Scanner stdIn = new Scanner(System.in);
        // get file in
        File fileIn = getFileIn(stdIn);
        // get file out
        File fileOut = getFileOut(stdIn);
        // update user that the program is reading the input file
        outputMessage("Reading input from file located in " + fileIn + "....");
        // create buffered reader stream
        BufferedReader fileReader;
        // try to open the file to be read - catch file not found exception
        try {
            fileReader = new BufferedReader(new FileReader(fileIn));
        } catch (FileNotFoundException e) {
            System.err.println("Error, file not found: " + fileIn);
            return;
        }
        // create tag cloud map
        Map<String, Integer> tcMap;
        /*
         * try to read from the input and add the input to map. catch
         * IOException
         */
        try {
            tcMap = createMap(fileReader);
            // output to console that reading has finished
            outputMessage("File read successfully.");
        } catch (IOException e) {
            System.err.println("Error while reading from file: " + fileIn);
            return;
        }
        // try to close the reader input - Catch IOException
        try {
            fileReader.close();
        } catch (IOException e) {
            System.err.println("Error closing input.");
        }
        // create the queue that will contain top counted words
        Queue<String> wordQueue = createQueue(tcMap, stdIn);
        outputMessage("Tag Cloud generated successfly");
        // done with scanner - close it
        stdIn.close();
        // try to write to destination file - catch IOException e
        try {
            processOutput(tcMap, wordQueue, fileOut);
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + fileOut);
        }
    }

}
