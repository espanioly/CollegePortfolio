import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * program that writes down and creates an HTML page of the input's components
 *
 * @author Sam Espanioly
 */
public final class Gloss {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Gloss() {
    }

    /**
     * adds terms and its definitions into a Map variable termsAndDef
     *
     * @param input
     *            the input stream
     * @param termsAndDef
     *            Map pairs that holds the terms and definitions
     * @return Queue that includes the terms from the input
     * @updates input
     * @requires <pre>
     *  termsAndDef not empty;
     *  !input.isEmpty();
     *  input includes the terms and definitions
     *  </pre>
     * @ensures <pre>
     *  return = termsAndDef from input
     *  </pre>
     */
    public static Queue<String> termsAndDefinitions(SimpleReader input,
            Map<String, String> termsAndDef) {
        Queue<String> terms = new Queue1L<>();
        /* While loop to go through entire input. */
        while (!input.atEOS()) {
            // Gets the next "term".
            String term = input.nextLine();
            StringBuilder definition = new StringBuilder();
            boolean nextTerm = false;
            // While loop to find and get all the definition of the term
            while (!nextTerm && !input.atEOS()) {
                String line = input.nextLine();
                if (!line.equals("")) {
                    definition.append(line + " ");
                } else {
                    // Removes trailing space at end of definition
                    definition.deleteCharAt(definition.length() - 1);
                    nextTerm = true;
                }
            }
            /*
             * Adds the term and its corresponding definition to {@code
             * termsAndDef}, as well as to {@code terms}
             */
            termsAndDef.add(term, definition.toString());
            terms.enqueue(term);
        }
        //Sorts the terms according to the case letter each term starts with
        terms.sort(String.CASE_INSENSITIVE_ORDER);
        return terms;
    }

    /**
     * Generates the title into each page
     *
     * @param feedFile
     *            String that includes the file info
     * @param terms
     *            Queue of all the terms
     * @updates feedFile
     * @requires <pre>
     *  terms not empty
     *  </pre>
     * @ensures <pre>
     *  feedFile includes title
     *  </pre>
     */
    public static void generateTitlePage(SimpleWriter feedFile,
            Queue<String> terms) {
        //skeleton of html header
        feedFile.println("<html>");
        feedFile.println("<head>");
        feedFile.println("<title>Glossary</title>");
        feedFile.println("</head>");
        feedFile.println("<body>");
        feedFile.println("<h2>Glossary</h2>");
        feedFile.println("<hr>");
        feedFile.println("<h3>Index</h3>");
        feedFile.println("<ul>");
        // adding a hyperlink for each page
        for (int i = 0; i < terms.length(); i++) {
            feedFile.println("<li>");
            //linking here
            feedFile.println("<a href=\"" + terms.front() + ".html\">"
                    + terms.front() + "</a>");
            feedFile.println("</li>");
            terms.rotate(1);
        }
        //closing html body
        feedFile.println("</ul>");
        feedFile.println("</body>");
        feedFile.println("</html>");

    }

    /**
     * Generates the definition/information about he title of the page
     *
     * @param feedFile
     *            String that includes the file info
     * @param terms
     *            Queue of all the terms
     * @param termsAndDef
     *            Map pairs that holds the terms and definitions
     * @updates feedFile
     * @requires <pre>
     *  termsAndDef not empty;
     *  term not empty;
     *  </pre>
     * @ensures <pre>
     *  feedFile includes definition of the title in the page
     *  </pre>
     */
    public static void generateTermPage(SimpleWriter feedFile, String term,
            Map<String, String> termsAndDef) {
        feedFile.println("<html>");
        feedFile.println("<head>");
        feedFile.println("<title>" + term + "</title>");
        feedFile.println("</head>");
        feedFile.println("<body>");
        //making the font red italic and bold
        feedFile.println("<h2><b><i><font color=\"red\">" + term
                + "</font></i></b></h2>");
        /*
         * prints to {@code feedFile aka output file} the term and its
         * corresponding definition, formatted in html style.
         */
        feedFile.println(
                "<blockquote>" + termsAndDef.value(term) + "</blockquote>");
        feedFile.println("<hr>");
        feedFile.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        feedFile.println("</body>");
        feedFile.println("</html>");
    }

    /**
     * adds hyperlinks to the terms and definitions on page
     *
     * @param terms
     *            Queue of all the terms
     * @param termsAndDef
     *            Map pairs that holds the terms and definitions
     * @updates termsAndDef
     * @requires <pre>
     *  termsAndDef not empty;
     *  terms not empty;
     *  </pre>
     * @ensures <pre>
     *  termsAndDef value = definition;
     *  outputs hyperlink in page
     *  </pre>
     */
    public static void allSeperators(Queue<String> terms,
            Map<String, String> termsAndDef) {
        // making a set of all separators
        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add(' ');
        separators.add('\t');
        separators.add(';');
        separators.add('.');
        //nested for loops to check for any definitions that contains a term
        for (int i = 0; i < terms.length(); i++) {
            String term = terms.front();
            for (int w = 0; w < terms.length(); w++) {
                String curTerm = terms.front();
                String definition = termsAndDef.value(term);
                int position = 0;
                while (position < definition.length()) {
                    String curWord = nextWordOrSeparator(definition, position,
                            separators);
                    /*
                     * if a term is found then update definition to contain the
                     * html hyperlinked equivalent therefore linking to the same
                     * page.
                     */
                    if (curWord.equals(curTerm)) {
                        definition = definition.substring(0, position)
                                + "<a href=\"" + curWord + ".html\">" + curWord
                                + "</a>"
                                + definition.substring(
                                        position + curWord.length(),
                                        definition.length());
                    }
                    position += curWord.length();
                }
                termsAndDef.replaceValue(term, definition);
                terms.rotate(1);
            }
            terms.rotate(1);
        }
    }

    /**
     * adds terms and its definitions into a Map variable termAndDef
     *
     * @param text
     *            String includes text
     * @param position
     *            int includes the position to separate the text at
     * @param separators
     *            Set of all seperators
     * @return String includes next word or a separator
     * @requires <pre>
     *  position >=0
     *  separators not empty && includes the separators
     *  </pre>
     * @ensures <pre>
     *  return = word or separator
     *  </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        StringBuilder returnText = new StringBuilder();
        if (separators.contains(text.charAt(position))) {
            for (int i = position; i < text.length()
                    && separators.contains(text.charAt(i)); i++) {
                returnText.append(text.charAt(i));
            }
        } else {
            for (int i = position; i < text.length()
                    && !separators.contains(text.charAt(i)); i++) {
                returnText.append(text.charAt(i));
            }
        }
        return returnText.toString();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //finding input file path and output path
        out.println("Enter input file location: ");
        String inputFile = in.nextLine();
        out.println("Enter folder to save output files: ");
        String outputSaveTo = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFile);

        Map<String, String> termsAndDef = new Map1L<>();
        Queue<String> terms = termsAndDefinitions(input, termsAndDef);
        allSeperators(terms, termsAndDef);

        //generates page title
        SimpleWriter output = new SimpleWriter1L(outputSaveTo + "\\index.html");
        generateTitlePage(output, terms);

        //generates term page for each term.
        int length = terms.length();
        for (int i = 0; i < length; i++) {
            String term = terms.dequeue();
            output = new SimpleWriter1L(outputSaveTo + "\\" + term + ".html");
            generateTermPage(output, term, termsAndDef);
        }

        in.close();
        out.close();
        input.close();
        output.close();
    }
}