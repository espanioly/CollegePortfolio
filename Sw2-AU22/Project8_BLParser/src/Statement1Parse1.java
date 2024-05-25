import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";
        tokens.dequeue(); // remove IF
        // check if the next token is a condition
        if (!Tokenizer.isCondition(tokens.front())) {
            Reporter.assertElseFatalError(false,
                    "Error: CONDITION expected, found: " + tokens.front());

        }
        // parse the condition - local because needed to assemble the if
        Condition c = parseCondition(tokens.dequeue());
        // check if next token is "THEN"
        if (!tokens.front().equals("THEN")) {
            // Syntax error case: "THEN" was not found
            Reporter.assertElseFatalError(false,
                    "Error: Keyword \"THEN\" expected, found: "
                            + tokens.front());
        } else {
            // remove "THEN"
            tokens.dequeue();
        }
        // Used to parse statements in THEN
        Statement tBlock = s.newInstance();
        // used to parse statements in ELSE (if it exists)
        Statement eBlock = s.newInstance();
        // boolean to determine whether to assembleIf or assembleIfElse
        boolean hasElse = false;
        // call parseBlock to parse any statements in THEN to tBlock
        tBlock.parseBlock(tokens);
        // check if the this is an IF_ELSE
        if (tokens.front().equals("ELSE")) {
            // All statements following else -> end parsed in eBlock
            tokens.dequeue();
            eBlock.parseBlock(tokens);
            // statement is IF_ELSE -> set true
            hasElse = true;
        }
        // done parsing - assemble if or ifelse
        if (!hasElse) {
            s.assembleIf(c, tBlock);
        } else {
            s.assembleIfElse(c, tBlock, eBlock);
        }
        // check if the next token is "END"
        if (!tokens.front().equals("END")) {
            // Syntax error case: "END" not found
            Reporter.assertElseFatalError(false,
                    "Error: Keyword, \"END\" expected, found: "
                            + tokens.front());

        } else {
            // remove "END"
            tokens.dequeue();
        }
        // check if the next token is "IF"
        if (!tokens.front().equals("IF")) {
            // Syntax error case: "IF" not found
            Reporter.assertElseFatalError(false,
                    "Error: Keyword, \"IF\" expected, found: "
                            + tokens.front());
        } else {
            // remove "IF"
            tokens.dequeue();
        }

    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";
        tokens.dequeue(); // dequeue "WHILE"
        // check if the next token is a valid condition
        if (!Tokenizer.isCondition(tokens.front())) {
            // Syntax error case: token was not a valid condition
            Reporter.assertElseFatalError(false,
                    "Error: CONDITION expected, found: " + tokens.front());

        }
        // condition of the while not in else because needs to be local
        Condition c = parseCondition(tokens.dequeue());
        if (!tokens.front().equals("DO")) {
            // Syntax case: token was not "DO"
            Reporter.assertElseFatalError(false,
                    "Error: Keyword, \"DO\" expected, found: "
                            + tokens.front());
        } else {
            tokens.dequeue(); // remove "DO"
        }
        // Statement for parsing
        Statement whileBlock = s.newInstance();
        // parse any statements found in the block to whileBlock
        whileBlock.parseBlock(tokens);
        // all statements should be parsed inside the block - assemble while
        s.assembleWhile(c, whileBlock);
        // check if next token is "END"
        if (!tokens.front().equals("END")) {
            // Syntax error case: "END" was not at end of statement
            Reporter.assertElseFatalError(false,
                    "Error: Keyword, \"END\" expected, found: "
                            + tokens.front());
        } else {
            tokens.dequeue(); // remove "END"
        }
        // Check if next token is "WHILE"
        if (!tokens.front().equals("WHILE")) {
            // Syntax error case: "While" not found at end of statement
            Reporter.assertElseFatalError(false,
                    "Error: Keyword, \"WHILE\" expected, found: "
                            + tokens.front());
        } else {
            tokens.dequeue(); // remove "WHILE"
        }

    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";
        // Assemble s into a call. (isTokenizer called before parseCall)
        s.assembleCall(tokens.dequeue());
        // any following syntax errors checked after parseCall returns
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        // parse a single statement into an IF OR WHILE OR call block
        String token = tokens.front();
        if (token.equals("IF")) {
            parseIf(tokens, this);
        } else if (token.equals("WHILE")) {
            parseWhile(tokens, this);
        } else if (Tokenizer.isIdentifier(token)) {
            parseCall(tokens, this);
        } else {
            // Found syntax error
            Reporter.assertElseFatalError(false,
                    "Error: Keywords \"IF\" or \"WHILE\" "
                            + "or IDENTIFIER expected, found: ");
        }

    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        /*
         * all parsed statements added to this block - {@code this} will get
         * overwritten
         */
        Statement block = this.newInstance();
        /*
         * parses all statements until a non valid token is found. If called by
         * parseIf/parseWhile only parses their tokens as needed, but if
         * parseBlock is called by a statement then parses every single token
         * found inside until "END KEYWORD" is found.
         */
        while (Tokenizer.isIdentifier(tokens.front())
                || tokens.front().equals("IF")
                || (tokens.front().equals("WHILE"))) {
            if (tokens.front().equals("IF")) {
                parseIf(tokens, this);
            } else if (tokens.front().equals("WHILE")) {
                parseWhile(tokens, this);
            } else if (Tokenizer.isIdentifier(tokens.front())) {
                parseCall(tokens, this);
            } else {
                // catch any keywords that should not parsed
                Reporter.assertElseFatalError(false,
                        "Error: Keywords \"IF\" or \"WHILE\" "
                                + "or IDENTIFIER expected, found: "
                                + tokens.front());

            }
            // add all parsed statements to block
            block.addToBlock(block.lengthOfBlock(), this);
        }
        // transfer parsed statements from block to this
        this.transferFrom(block);
    }

    /*
     * Main test method -------------------------------------------------------
     */

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
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
