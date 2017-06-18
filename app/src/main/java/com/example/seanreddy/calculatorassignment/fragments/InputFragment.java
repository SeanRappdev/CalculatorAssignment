package com.example.seanreddy.calculatorassignment.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.seanreddy.calculatorassignment.CaluculatorContorllerInterace;
import com.example.seanreddy.calculatorassignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment implements View.OnClickListener {

    CaluculatorContorllerInterace.InputListener inputListener;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        bindNumbers(view);
        bindOperations(view);
        Button decimal = (Button) view.findViewById(R.id.btn_decimal);
        decimal.setOnClickListener(this);
        Button equal = (Button) view.findViewById(R.id.btn_evaluate);
        equal.setOnClickListener(this);
        return view;
    }

    private void bindOperations(View view) {
        Button plus = (Button) view.findViewById(R.id.btn_operator_add);
        plus.setOnClickListener(this);
        Button sub = (Button) view.findViewById(R.id.btn_operator_subtract);
        sub.setOnClickListener(this);
        Button multiply = (Button) view.findViewById(R.id.btn_operator_multiply);
        multiply.setOnClickListener(this);
        Button divide = (Button) view.findViewById(R.id.btn_operator_divide);
        divide.setOnClickListener(this);
    }

    private void bindNumbers(View view) {
        Button one = (Button) view.findViewById(R.id.btn_number_one);
        one.setOnClickListener(this);
        Button two =(Button) view.findViewById(R.id.btn_number_two);
        two.setOnClickListener(this);
        Button three = (Button) view.findViewById(R.id.btn_number_three);
        three.setOnClickListener(this);
        Button four = (Button) view.findViewById(R.id.btn_number_four);
        four.setOnClickListener(this);
        Button five = (Button) view.findViewById(R.id.btn_number_five);
        five.setOnClickListener(this);
        Button six = (Button) view.findViewById(R.id.btn_number_six);
        six.setOnClickListener(this);
        Button seven = (Button) view.findViewById(R.id.btn_number_seven);
        seven.setOnClickListener(this);
        Button eight = (Button) view.findViewById(R.id.btn_number_eight);
        eight.setOnClickListener(this);
        Button nine  = (Button) view.findViewById(R.id.btn_number_nine);
        nine.setOnClickListener(this);
        Button zero = (Button) view.findViewById(R.id.btn_number_zero);
        zero.setOnClickListener(this);
    }

    public void setPresenter(CaluculatorContorllerInterace.InputListener inputListener) {
        this.inputListener = inputListener;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_number_one:
                inputListener.onNumberClick(1);
                return;
            case R.id.btn_number_two :
                inputListener.onNumberClick(2);
                return;
            case R.id.btn_number_three :
                inputListener.onNumberClick(3);
                return;
            case R.id.btn_number_four :
                inputListener.onNumberClick(4);
                return;
            case R.id.btn_number_five :
                inputListener.onNumberClick(5);
                return;
            case R.id.btn_number_six :
                inputListener.onNumberClick(6);
                return;
            case R.id.btn_number_seven :
                inputListener.onNumberClick(7);
                return;
            case R.id.btn_number_eight :
                inputListener.onNumberClick(8);
                return;
            case R.id.btn_number_nine :
                inputListener.onNumberClick(9);
                return;
            case R.id.btn_number_zero :
                inputListener.onNumberClick(0);
                return;
            case R.id.btn_operator_add:
                inputListener.onOperationClick("+");
                return;
            case R.id.btn_operator_divide :
                inputListener.onOperationClick("/");
                return;
            case R.id.btn_operator_multiply :
                inputListener.onOperationClick("*");
                return;
            case R.id.btn_operator_subtract :
                inputListener.onOperationClick("-");
                return;
            case R.id.btn_evaluate :
                inputListener.onEqualClick();
                return;
            case R.id.btn_decimal :
                inputListener.onDecimalClick();
                return;
            default:
                return;
        }

    }
}
