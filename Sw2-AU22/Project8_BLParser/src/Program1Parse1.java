import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Aaron Heishman, Sam Espanioly
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";
        tokens.dequeue(); // remove instruction
        String inst = tokens.dequeue(); // get instruction name to be returned
        if (!Tokenizer.isIdentifier(inst)) {
            // token was not a valid identifier
            Reporter.assertElseFatalError(false,
                    "Error: expected IDENTIFIER, found: " + tokens.front());
        }
        // next token is "IS"
        if (!tokens.front().equals("IS")) {
            // token was not "IS"
            Reporter.assertElseFatalError(false,
                    "Error: expected \"IS\" , found: " + tokens.front());
        } else {
            tokens.dequeue(); // remove is
            body.parseBlock(tokens); // parse tokens into body
        }
        // next token is "END"
        if (!tokens.front().equals("END")) {
            // token was not "END"
            Reporter.assertElseFatalError(false,
                    "Error: expected \"END\" , found: " + tokens.front());
        } else {
            tokens.dequeue(); // remove "END"
        }
        if (!tokens.front().equals(inst)) {
            // Syntax case: identifiers did not match
            Reporter.assertElseFatalError(false,
                    "Error: Identifier at end of instruction"
                            + " must match, found: " + tokens.front());
        } else {
            tokens.dequeue(); // remove identifier
        }
        return inst;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";
        // first token should be PROGRAM
        if (!tokens.front().equals("PROGRAM")) {
            // Syntax error case:  token is not the keyword "PROGRAM"
            Reporter.assertElseFatalError(false,
                    "Error: keyword \"PROGRAM\" expected, found: "
                            + tokens.front());
        } else {
            tokens.dequeue(); // remove PROGRAM
        }
        // next token is an identifier
        if (!Tokenizer.isIdentifier(tokens.front())) {
            // Syntax error case: token is not a valid identifier
            Reporter.assertElseFatalError(false,
                    "Error: IDENTIFIER expected, found: \"" + tokens.front()
                            + "\"");
        } else {
            this.setName(tokens.dequeue()); // remove and set the name
        }
        // next token in line is "IS"
        if (!tokens.front().equals("IS")) {
            // Syntax error case: token is not the keyword IS
            Reporter.assertElseFatalError(false,
                    "Error: keyword \"IS\" expected, found: \"" + tokens.front()
                            + "\"");
        } else {
            tokens.dequeue(); // remove token
        }
        // may or may not have INSTRUCTION
        while (tokens.front().equals("INSTRUCTION")) {
            // parse INSTRUCTION(s)
            Map<String, Statement> nContext = this.newContext();
            while (tokens.front().equals("INSTRUCTION")) {
                Statement body = this.newBody(); // statement for instruction
                // parse instruction block - return name
                String instName = parseInstruction(tokens, body);
                // instruction identifier must be unique
                if (!nContext.hasKey(instName)) {
                    // unique instruction id - add to context
                    nContext.add(instName, body);
                } else {
                    // instruction ID must be unique
                    Reporter.assertElseFatalError(false,
                            "ERROR: INSTRUCTION identifier already defined: "
                                    + instName);
                }
            }
            // swap the new context into this
            this.swapContext(nContext);
        }

        if (!tokens.front().equals("BEGIN")) {
            // Syntax case: token was not "BEGIN"
            Reporter.assertElseFatalError(false,
                    "Error: keywords \"BEGIN\" or \"INSTRUCTION\" expected, "
                            + "found: \"" + tokens.front() + "\"");
        } else {
            tokens.dequeue(); // remove begin
            Statement nbody = this.newBody(); // new body for this
            nbody.parseBlock(tokens); // parse tokens into block
            this.swapBody(nbody); // swap the tokens into body
        }

        if (!tokens.front().equals("END")) {
            // END wasn't found
            Reporter.assertElseFatalError(false,
                    "Error: Keyword \"END\" expected, found : "
                            + tokens.front());
        } else {
            tokens.dequeue(); // remove "END"
        }

        if (!tokens.front().equals(this.name())) {
            // identifier doesn't match
            Reporter.assertElseFatalError(false,
                    "Error: IDENTIFIER must match \"" + this.name()
                            + "\" at end of program, found: " + tokens.front());
        } else {
            tokens.dequeue(); // remove identifier
        }

        // Check that tokens = <### END OF INPUT ###>
        if (tokens.length() > 1) {
            // something is beyond program source
            Reporter.assertElseFatalError(false,
                    "Found " + tokens.front() + " beyond program source");
        }
        // done
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }
}
