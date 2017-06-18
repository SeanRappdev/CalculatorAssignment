package com.example.seanreddy.calculatorassignment.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seanreddy.calculatorassignment.CaluculatorContorllerInterace;
import com.example.seanreddy.calculatorassignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragmnet extends Fragment implements CaluculatorContorllerInterace.ShowViewData {


    private CaluculatorContorllerInterace.DisplayListener displayListener;
    TextView resultDisplayView;
    public DisplayFragmnet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display_fragmnet, container, false);
        resultDisplayView = (TextView) view.findViewById(R.id.input_display);
        ImageView oneClick = (ImageView) view.findViewById(R.id.clear_display);
        oneClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayListener.onClearClick();
            }
        });
        oneClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                displayListener.onClearLongClick();
                return false;
            }
        });
        return view;
    }

    @Override
    public void displayResult(String result) {
        resultDisplayView.setText(result);
    }

    @Override
    public void displayError(String result) {
        Toast.makeText(getContext(),result,Toast.LENGTH_LONG).show();
    }

    public void setPresenter(CaluculatorContorllerInterace.DisplayListener displayListener) {
        this.displayListener = displayListener;
    }

}
