package com.example.seanreddy.calculatorassignment;


import android.text.TextUtils;
import android.util.Log;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class CaluculationLogicModel {

    //private final Symbols symbols;
    private CalculationResult calculationResult;
    private static String currentExpression;
    private String lastOperator;

    public void performEvaluation() {

        performCaluculation("");
        lastOperator="";
        calculationResult.onExpressionChanged(currentExpression,true);
    }

    interface CalculationResult {
        void onExpressionChanged(String result, boolean successful);
    }


    public CaluculationLogicModel(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        currentExpression = "";
        lastOperator = "";
       // symbols = new Symbols();
    }

    /**
     * Delete a single character from currentExpression, unless empty
     * "" - invalid
     * 543 - valid
     * 45*65 - valid
     */
    public void deleteCharacter() {
        if (currentExpression.length() > 0) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResult.onExpressionChanged(currentExpression, true);
        } else {
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
    }

    /**
     * Delete entire expression unless empty
     * "" - invalid
     */
    public void deleteExpression() {
        if (currentExpression.equals("")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
        }
        currentExpression = "";
        calculationResult.onExpressionChanged(currentExpression, true);
    }

    /**
     * Append number to currentExpression if valid:
     * "0" & number is 0 - invalid
     * "12345678909876543" - invalid
     *
     * @param number
     */
    public void appendNumber(String number) {
        if (currentExpression.startsWith("0") && number.equals("0")) {
            calculationResult.onExpressionChanged("Invalid Input", false);
        } else {
            if (currentExpression.length() <= 16) {
                currentExpression += number;
                calculationResult.onExpressionChanged(currentExpression, true);
            } else {
                calculationResult.onExpressionChanged("Expression Too Long", false);
            }
        }
    }

    /**
     * Append an Operator to currentExpression, if valid:
     * 56 - valid
     * 56* - invalid
     * 56*2 - valid
     * "" - invalid
     *
     * @param operator one of:
     *                 - "*"
     *                 - "/"
     *                 - "-"
     *                 - "+"
     */
    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)) {
            if(TextUtils.isEmpty(lastOperator)){
                lastOperator = operator;
                currentExpression += lastOperator;
                calculationResult.onExpressionChanged(currentExpression, true);
            }else {
               validateExpressionWithOperator(operator);
                performCaluculation(operator);
                lastOperator = operator;
            }
        }
    }

    private void validateExpressionWithOperator(String operator) {
        String lastChar = currentExpression.substring(currentExpression.length()-1);
        if(lastChar.equalsIgnoreCase("+")||lastChar.equalsIgnoreCase("-")
                ||lastChar.equalsIgnoreCase("/")||lastChar.equalsIgnoreCase("*")){
            currentExpression = currentExpression.replace(lastOperator,operator);
            lastOperator = operator;
        }
    }

    private void performCaluculation(String operator) {
        String[] splitNumber  =  currentExpression.split(Pattern.quote(lastOperator));
        String firstNumber = splitNumber[0];
        String secondNumber = splitNumber[1];
        switch (lastOperator){
            case "+":
                if(firstNumber.contains(".")||secondNumber.contains(".")){
                    double fNumber = getDoubleFromString(firstNumber);
                    double sNumber = getDoubleFromString(secondNumber);
                    BigDecimal db = new BigDecimal(fNumber+sNumber).setScale(2,BigDecimal.ROUND_CEILING);
                    currentExpression = (db.doubleValue())+operator;
                }else {
                    currentExpression = (getNumberFromString(firstNumber)+getNumberFromString(secondNumber))+operator;
                }
                calculationResult.onExpressionChanged(currentExpression,true);
                return;
            case "-":
                if(firstNumber.contains(".")||secondNumber.contains(".")){
                    double fNumber = getDoubleFromString(firstNumber);
                    double sNumber = getDoubleFromString(secondNumber);
                    BigDecimal db = new BigDecimal(fNumber-sNumber).setScale(2,BigDecimal.ROUND_CEILING);
                    currentExpression = (db.doubleValue())+operator;
                }else {
                    currentExpression = (getNumberFromString(firstNumber)-getNumberFromString(secondNumber))+operator;
                }
                calculationResult.onExpressionChanged(currentExpression,true);
                return;
            case "/":
                    if(getDoubleFromString(secondNumber)!=0){
                        double fNumber = getDoubleFromString(firstNumber);
                        double sNumber = getDoubleFromString(secondNumber);
                        BigDecimal db = new BigDecimal(fNumber/sNumber).setScale(2,BigDecimal.ROUND_CEILING);
                        currentExpression = (db.doubleValue())+operator;
                    }else {
                        calculationResult.onExpressionChanged("inValid format",false);
                        return;
                    }
                calculationResult.onExpressionChanged(currentExpression,true);
                return;
            case "*":
                if(firstNumber.contains(".")||secondNumber.contains(".")){
                    double fNumber = getDoubleFromString(firstNumber);
                    double sNumber = getDoubleFromString(secondNumber);
                    BigDecimal db = new BigDecimal(fNumber*sNumber).setScale(2,BigDecimal.ROUND_CEILING);
                    currentExpression = (db.doubleValue())+operator;
                }else {
                    currentExpression = (getNumberFromString(firstNumber)*getNumberFromString(secondNumber))+operator;
                }
                calculationResult.onExpressionChanged(currentExpression,true);
                return;
        }
    }

    private double getDoubleFromString(String doubleNumber) {
        double number ;
        number =Double.parseDouble(doubleNumber);
        BigDecimal db = new BigDecimal(number).setScale(2,BigDecimal.ROUND_CEILING);
        Log.d("bigDecimal check",db.doubleValue()+"value check");
        return db.doubleValue();
    }

    /*private String getRoundedDecimalString(String s) {
        if(s.contains(".")){
            String afterDecimals[] = s.split(Pattern.quote("."));
            String decimal;
            if(afterDecimals[1].length()>2) {

                decimal = s;
            }else {
                decimal =s;
            }
            return decimal;
        }else {
            return s;
        }

    }
*/
    private long getNumberFromString(String number) {
        long num = Integer.parseInt(number);
        return num;
    }

    /**
     * See type comment for appendOperator
     */
    public void appendDecimal() {
        if (validateExpression(currentExpression)) {
            currentExpression += ".";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }


    /**
     * If currentExpression passes checks, pass currentExpression to symbols object, \
     * then return the result.
     */
/*
    public void performEvaluation() {
        if (validateExpression(currentExpression)) {
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentExpression, true);
            } catch (SyntaxException e) {
                calculationResult.onExpressionChanged("Invalid Input", false);
                e.printStackTrace();
            }
        }
    }
*/

    /**
     * Helper function to validate expressions against the following checks:
     * "" - invalid;
     * 8765 - valid
     *
     * @param expression
     * @return
     */
    public boolean validateExpression(String expression) {
        if (expression.endsWith("*") ||
                expression.endsWith("/") ||
                expression.endsWith("-") ||
                expression.endsWith("+")
                ) {
            calculationResult.onExpressionChanged("Invalid Input", false);
            return false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChanged("Empty Expression", false);
            return false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChanged("Expression Too Long", false);
            return false;
        } else {
            return true;
        }
    }
}
