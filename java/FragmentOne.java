package com.example.asus.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ASUS on 12/12/2015.
 */
public class FragmentOne extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=  inflater.inflate(R.layout.fragment_one, container, false);

        Button button = (Button) view.findViewById(R.id.buttonlogin);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                buttonClick(v);
            }
        });


        Button button1 = (Button) view.findViewById(R.id.buttonreg);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                button1Click(v);
            }
        });

        return view;
    }

    public void buttonClick(View v) {

        Intent intent;
        intent = new Intent(getActivity().getApplicationContext(),C_Clogin.class);
        startActivity(intent);


    }

    public void button1Click(View v) {

        Intent intent1;
        intent1 = new Intent(getActivity().getApplicationContext(),C_Cregister.class);
        startActivity(intent1);


    }
}
