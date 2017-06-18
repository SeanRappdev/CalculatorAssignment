package com.example.seanreddy.calculatorassignment;


public interface CaluculatorContorllerInterace {

    interface ShowViewData{
        void displayResult(String result);

        void displayError(String result);
    }

    interface DisplayListener{
        void onClearClick();
        void onClearLongClick();
    }

    interface InputListener{
        void onNumberClick( int number);
        void onDecimalClick();
        void onOperationClick(String s);
        void onEqualClick();
    }
}
