import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Sam Espanioly
 */

public class WordCounter {

    /**
     * sorts Queue<String> alphabetically
     *
     * @return sorted Queue
     * @requires <pre>
     * Queue<> != <>;
     * </pre>
     * @ensures <pre> sorting alphabetically </pre>
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }
    }

    /**
     * Adds a sorted table to fileOut
     *
     * @param wordCounts
     *            Map includes separated words <String> and the value refers to
     *            how often it repeats inside the input file
     * @param fileOut
     *            html file
     * @return sorted table inside fileOut
     * @updates fileOut
     * @requires <pre>
     * wordCounts.size > 0;
     * </pre>
     * @ensures <pre>
     * sorted table inside of fileOut
     * </pre>
     */
    private static void table(Map<String, Integer> wordCounts,
            SimpleWriter fileOut) {
        Map<String, Integer> tempMaps = new Map1L<>();
        Queue<String> sorter = new Queue1L<>();
        while (wordCounts.size() > 0) {
            Pair<String, Integer> word = wordCounts.removeAny();
            tempMaps.add(word.key(), word.value());
            sorter.enqueue(word.key());
        }
        // these two lines are supposed to sort the queue alphabetically only if
        // I did the method correctly
        Comparator<String> sorta = new StringLT();
        sorter.sort(sorta);
        wordCounts.transferFrom(tempMaps);
        while (sorter.length() != 0) {
            String temporaryWordInsideQueueandInsideMapsToo = sorter.dequeue();
            fileOut.println("<tr><th>"
                    + temporaryWordInsideQueueandInsideMapsToo + "</td><td>"
                    + wordCounts.value(temporaryWordInsideQueueandInsideMapsToo)
                    + "</td></tr>");
        }

    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        out.print("Name of the input file: ");
        String inputF = in.nextLine();

        out.print("Name of the output file: ");
        String outF = in.nextLine();

        SimpleReader fileIn = new SimpleReader1L(inputF);
        String line;
        Map<String, Integer> wordCounts = new Map1L<>();
        while (!fileIn.atEOS()) {
            line = fileIn.nextLine();
            // splits words here
            String[] words = line.split("[^A-Za-z0-9]+");
            for (String temporaryWord : words) {
                if (!temporaryWord.equals("")) {
                    // if word is not inside wordCounts then adds it
                    if (!wordCounts.hasKey(temporaryWord)) {
                        //adds a new word
                        wordCounts.add(temporaryWord, 1);
                        //else update the value inside the key
                    } else {
                        //adds 1 to the count
                        Integer w = wordCounts.value(temporaryWord);
                        w++;
                        // remove the pair
                        wordCounts.remove(temporaryWord);
                        /**
                         * adds the pair back with the new value this works
                         * because order does not matter in Map [(thank god)or
                         * whoever made Maps]
                         */
                        wordCounts.add(temporaryWord, w);
                    }

                }
            }
        }
        SimpleWriter fileOut = new SimpleWriter1L(outF);
        fileOut.println("<html>");
        fileOut.println("<head>Words Counted in " + inputF);
        fileOut.println("<body>");
        fileOut.println("<h2>Words Counted in " + inputF + "</h2>");
        fileOut.println("<hr>");
        // insert table here
        fileOut.println("<table border=\"1\"><tbody>");
        fileOut.println("<tr>\r\n" + "<th>Words</th>\r\n"
                + "<th>Counts</th>\r\n" + "</tr>");
        table(wordCounts, fileOut);
        fileOut.println("</tbody></table>");
        fileOut.println("</body>");
        fileOut.println("</head>");
        fileOut.println("</html>");
        fileOut.close();
        fileIn.close();
        in.close();
        out.close();
    }

}
