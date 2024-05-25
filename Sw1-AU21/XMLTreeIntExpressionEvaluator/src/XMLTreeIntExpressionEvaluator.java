import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
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

    /**
     * 22 * Evaluate the given expression. 23 * 24 * @param exp 25 * the
     * {@code XMLTree} representing the expression 26 * @return the total of the
     * expression 27 * @requires <pre>
    28  * [exp is a subtree of a well-formed XML arithmetic expression]  and
    29  *  [the label of the root of exp is not "expression"]
    30  * </pre> 31 * @ensures evaluate = [the total of the expression] 32
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        // this variable is for the total for the evaluation
        NaturalNumber total = new NaturalNumber2(0);
        // first child in integer format
        int firstChild = 0;
        // second child in integer format
        int secondChild = 0;
        while (exp.numberOfChildren() != 0 && exp.isTag() == true) {
            if (exp.label() == "plus") { // first we start with a plus possibility
                if (exp.child(0).hasAttribute("number")) {

                    // child1tmp is a temporarily value for the "number"
                    String child1tmp = exp.child(0).attributeValue("number");
                    // value is in int
                    firstChild = Integer.parseInt(child1tmp);
                } else {
                    evaluate(exp.child(0)); //child one
                } // this checks for possible attributes with the value "number"
                if (exp.child(1).hasAttribute("number")) {
                    // child2tmp is another temporarily value for the "number"
                    String child2tmp = exp.child(0).attributeValue("number"); // value is now in int
                    secondChild = Integer.parseInt(child2tmp);
                } else {
                    evaluate(exp.child(1)); //child two
                    total = new NaturalNumber2(firstChild + secondChild); // process adding
                }
            } else if (exp.label() == "minus") { // then a minus possibility
                // this checks for possible attributes with the value "number"
                if (exp.child(0).hasAttribute("number")) {
                    String child1tmp = exp.child(0).attributeValue("number");
                    firstChild = Integer.parseInt(child1tmp);
                } else {
                    evaluate(exp.child(0)); //child one repeats in the recursive method
                }
                if (exp.child(1).hasAttribute("number")) {
                    String child2tmp = exp.child(0).attributeValue("number");
                    secondChild = Integer.parseInt(child2tmp);
                } else {
                    evaluate(exp.child(1)); //child two same thing
                }
                total = new NaturalNumber2(firstChild - secondChild); // processing adding again
            } else if (exp.label() == "times") { // then multiplication
                if (exp.child(0).hasAttribute("number")) {
                    String child1tmp = exp.child(0).attributeValue("number");
                    firstChild = Integer.parseInt(child1tmp); //turns to int
                } else { // else recursive method
                    evaluate(exp.child(0)); //child one goes again in the loop method
                }
                if (exp.child(1).hasAttribute("number")) {
                    String child2tmp = exp.child(0).attributeValue("number");
                    secondChild = Integer.parseInt(child2tmp);
                } else { //freelaunch? recursive method
                    evaluate(exp.child(1)); //child two goes in recursive mode method
                }
                total = new NaturalNumber2(firstChild * secondChild);
            } else if (exp.label() == "divide") { // and last is dividing if there's any
                // checking for attribute "number" in child one
                if (exp.child(0).hasAttribute("number")) {
                    String child1tmp = exp.child(0).attributeValue("number");
                    firstChild = Integer.parseInt(child1tmp); //turning to int
                } else { //paidlaunch recursive method
                    evaluate(exp.child(0)); //child one goes back to be evaluated
                }
                if (exp.child(1).hasAttribute("number")) {
                    String child2tmp = exp.child(0).attributeValue("number");
                    secondChild = Integer.parseInt(child2tmp);
                } else {
                    evaluate(exp.child(1)); //child two
                }
                if (secondChild > 0) {
                    total = new NaturalNumber2(firstChild / secondChild); // final process in the loop
                }
            }
        }
        // returning NN
        return total;
    }

    private XMLTreeIntExpressionEvaluator() {
    }

    /**
     * Main method. 116 * 117 * @param args 118 * the command line arguments
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