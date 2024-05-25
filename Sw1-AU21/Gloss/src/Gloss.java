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
 *
 *
 * @author Sam Espanioly
 */
public final class Gloss {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Gloss() {
    }

    public static Queue<String> getTermsAndDef(SimpleReader input,
            Map<String, String> termAndDef) {
        Queue<String> terms = new Queue1L<>();
        /* While loop to go through entire input. */
        while (!input.atEOS()) {
            /* Gets the next "term". */
            String term = input.nextLine();
            StringBuilder definition = new StringBuilder();
            boolean nextTerm = false;
            /* While loop to get entire definition of corresponding term. */
            while (!nextTerm && !input.atEOS()) {
                String line = input.nextLine();
                if (!line.equals("")) {
                    definition.append(line + " ");
                } else {
                    /* Removes trailing space at end of definition. */
                    definition.deleteCharAt(definition.length() - 1);
                    nextTerm = true;
                }
            }
            /*
             * Adds the term and its corresponding definition to {@code
             * termAndDef}, as well as to {@code terms}.
             */
            termAndDef.add(term, definition.toString());
            terms.enqueue(term);
        }
        /* Sorts {@code terms}. */
        terms.sort(String.CASE_INSENSITIVE_ORDER);
        return terms;
    }

    public static void generateTitlePage(SimpleWriter feedFile,
            Queue<String> terms) {
        feedFile.println("<html>");
        feedFile.println(" <head>");
        feedFile.println("      <title>Glossary</title>");
        feedFile.println(" </head>");
        feedFile.println(" <body>");
        feedFile.println("      <h2>Glossary</h2>");
        feedFile.println("      <hr>");
        feedFile.println("      <h3>Index</h3>");
        feedFile.println("      <ul>");
        /* For loop to add a hyperlink for each term's page. */
        for (int i = 0; i < terms.length(); i++) {
            feedFile.println("          <li>");
            feedFile.println("              <a href=\"" + terms.front()
                    + ".html\">" + terms.front() + "</a>");
            feedFile.println("          </li>");
            terms.rotate(1);
        }

        feedFile.println("      </ul>");
        feedFile.println(" </body>");
        feedFile.println("</html>");

    }

    public static void generateTermPage(SimpleWriter feedFile, String term,
            Map<String, String> termsAndDef) {
        feedFile.println("<html>");
        feedFile.println(" <head>");
        feedFile.println("      <title>" + term + "</title>");
        feedFile.println(" </head>");
        feedFile.println(" <body>");
        feedFile.println("<h2><b><i><font color=\"red\">" + term
                + "</font></i></b></h2>");
        /*
         * Prints to {@code feedFile} the term and its corresponding definition,
         * formatted in html style.
         */
        feedFile.println(
                "<blockquote>" + termsAndDef.value(term) + "</blockquote>");
        feedFile.println("      <hr>");
        feedFile.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
        feedFile.println(" </body>");
        feedFile.println("</html>");
    }

    public static void updateDefinition(Queue<String> terms,
            Map<String, String> termsAndDef) {
        /* Set of separators. */
        Set<Character> separators = new Set1L<>();
        separators.add(',');
        separators.add(' ');
        separators.add('\t');
        separators.add(';');
        separators.add('.');
        /* Nested for loops to check for any definitions that contain a term. */
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
                     * If a term is found, update definition to contain the html
                     * hyperlinked equivalent, linking to its respective page.
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

        /*
         * Gets the location of the input file and the desired location for the
         * output.
         */
        out.println("Enter input file location: ");
        String inputFile = in.nextLine();
        out.println("Enter folding to save output files: ");
        String outputLoc = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFile);

        Map<String, String> termsAndDef = new Map1L<>();
        Queue<String> terms = getTermsAndDef(input, termsAndDef);
        updateDefinition(terms, termsAndDef);

        /* Generates title page. */
        SimpleWriter output = new SimpleWriter1L(outputLoc + "\\index.html");
        generateTitlePage(output, terms);

        /* Generates term page for each term. */
        int length = terms.length();
        for (int i = 0; i < length; i++) {
            String term = terms.dequeue();
            output = new SimpleWriter1L(outputLoc + "\\" + term + ".html");
            generateTermPage(output, term, termsAndDef);
        }

        in.close();
        out.close();
        input.close();
        output.close();
    }
}