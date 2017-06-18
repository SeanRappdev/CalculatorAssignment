package com.example.seanreddy.calculatorassignment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.seanreddy.calculatorassignment.fragments.DisplayFragmnet;
import com.example.seanreddy.calculatorassignment.fragments.InputFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayFragmnet displayFragmnet = new DisplayFragmnet();
        InputFragment inputFragment = new InputFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.display_container,displayFragmnet);
        fragmentTransaction.replace(R.id.input_container,inputFragment);

        fragmentTransaction.commit();

        CalculatorDisplayPresenter presenter = new CalculatorDisplayPresenter(displayFragmnet);
        displayFragmnet.setPresenter(presenter);
        inputFragment.setPresenter(presenter);

    }
}
