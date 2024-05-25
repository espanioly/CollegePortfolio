import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Sam Espanioly
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        // making aliases as natural numbers
        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();
        // Condition for subtraction: top must be smaller than bottom
        if (bottom.compareTo(top) > 0) {
            view.updateSubtractAllowed(false);
        } else {
            view.updateSubtractAllowed(true);
        }
        // Condition for division : bottom is not zero
        if (bottom.isZero()) {
            view.updateDivideAllowed(false);
        } else {
            view.updateDivideAllowed(true);
        }
        // Condition for power : bottom must be less or equal to max int value
        if (bottom.compareTo(INT_LIMIT) <= 0) {
            view.updatePowerAllowed(true);
        } else {
            view.updatePowerAllowed(false);
        }
        // Condition for root : bottom can not be less than 2
        //                      bottom can not be les or equal to max int value
        if (bottom.compareTo(TWO) >= 0 && bottom.compareTo(INT_LIMIT) <= 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }
        // Update display for top and bottom
        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();

        // Getting the result into the top box
        top.copyFrom(bottom);
        // update view and model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // add top to bottom then clear top
        bottom.add(top);
        top.clear();
        // Update view and model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // subtract bottom from top then transfer top to bottom which clears top
        top.subtract(bottom);
        bottom.transferFrom(top);
        // Update view and model
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // multiply bottom with top then transfer the value to bottom and clear top
        top.multiply(bottom);
        bottom.transferFrom(top);
        // Update model and view
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // remainder r, comes from dividing bottom from top
        NaturalNumber r = top.divide(bottom);
        // transfer top to bottom
        bottom.transferFrom(top);
        // transfer the remainder back in top box
        top.transferFrom(r);
        // Update the values for model and view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // turn bottom to int then proceed to power top with bottom
        top.power(bottom.toInt());
        // transfer the value to bottom then clear top
        bottom.transferFrom(top);
        // Update view and model
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        // Aliases for top and bottom as natural numbers
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // turn bottom to int then proceed to get the (bottom) root of top
        top.root(bottom.toInt());
        // transfer value to bottom then clear top
        bottom.transferFrom(top);
        // Update model and view
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // Alias for bottom as natural number
        NaturalNumber bottom = this.model.bottom();
        // Adding a digit to bottom by multiplying it by 10 then adding the
        //digit on the right side
        bottom.multiplyBy10(digit);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);

    }

}
