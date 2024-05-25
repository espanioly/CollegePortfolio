import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Sam Espanioly
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        String errorSubtract = "ERROR: Cannot subtract by larger number.";
        String errorDivide = "ERROR: Cannot divide by zero.";
        int n0 = 0;
        int n1 = 0;
        int solution;
        //checks if the tree includes only a number
        if (!exp.label().equals("number")) {

            // checking for the first number
            if (!exp.child(0).hasAttribute("value")) {
                int numberTemp = (evaluate(exp.child(0)));
                n0 = (numberTemp);
            } else {
                //else its a number
                n0 = (Integer.parseInt(exp.child(0).attributeValue("value")));
            }

            // checking for the second number
            if (!exp.child(1).hasAttribute("value")) {
                int numberTemp = (evaluate(exp.child(1)));
                n1 = (numberTemp);
            } else {
                // otherwise it is a number
                n1 = (Integer.parseInt(exp.child(1).attributeValue("value")));
            }

            //checking for the operator to use
            String operator = exp.label();
            if (operator.equals("plus")) {
                n0 = +(n1);
            } else if (operator.equals("minus")) {
                // check if n1 >= n0 and report
                if (n1 > n0) {
                    //you could have a negative solution but not with naturalnumbers
                    Reporter.fatalErrorToConsole(errorSubtract);
                }
                n0 = -(n1);
            } else if (operator.equals("times")) {
                n0 = n0 * (n1);
            } else if (operator.equals("divide")) {
                //no zero allowed to divide by
                if (n1 == 0) {
                    //notifying user for a mistake everyone knows you can't divide by zero
                    Reporter.fatalErrorToConsole(errorDivide);
                }
                n0 = n0 / (n1);
            } //returning the solution
            solution = n0;
            //this case is only if the tree includes a number with no other operators
        } else {
            solution = Integer.parseInt(exp.attributeValue("value"));
        }
        return solution;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
