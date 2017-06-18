package com.example.seanreddy.calculatorassignment;


public class CalculatorDisplayPresenter implements
        CaluculatorContorllerInterace.DisplayListener,
        CaluculatorContorllerInterace.InputListener,
        CaluculationLogicModel.CalculationResult
{
    CaluculatorContorllerInterace.ShowViewData showViewData;
    private CaluculationLogicModel calculation;
    public CalculatorDisplayPresenter(CaluculatorContorllerInterace.ShowViewData showViewData) {
        this.showViewData = showViewData;
        calculation = new CaluculationLogicModel(this);
    }

    @Override
    public void onClearClick() {
        calculation.deleteCharacter();
    }

    @Override
    public void onClearLongClick() {
        calculation.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calculation.appendNumber(Integer.toString(number));
    }

    @Override
    public void onDecimalClick() {
        calculation.appendDecimal();
    }

    @Override
    public void onOperationClick(String s) {
        calculation.appendOperator(s);
    }

    @Override
    public void onEqualClick() {
        calculation.performEvaluation();
    }

    @Override
    public void onExpressionChanged(String result, boolean successful) {
        if(successful){
            showViewData.displayResult(result);
        }else {
            showViewData.displayError(result);
        }
    }
}
