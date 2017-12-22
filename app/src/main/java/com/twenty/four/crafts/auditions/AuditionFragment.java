package com.twenty.four.crafts.auditions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twenty.four.crafts.R;

/**
 * Created by Avinash Tadavarthy on 04-Nov-17.
 */

public class AuditionFragment extends android.support.v4.app.Fragment {

    View myView;


    EditText e1, e2, e3, e4, e5, e6, e7, e8, e9;
    TextView errmsg;
    Audition c = new Audition();


    public void err_msg_disp(int i)
    {
        if(i==1)
            errmsg.setVisibility(View.VISIBLE);
        else
            errmsg.setVisibility(View.GONE);
    }

    public void restore(EditText e) {
        e.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.box));
        err_msg_disp(-1);
    }

    private TextWatcher filterTextWatchere1 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e1);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher filterTextWatchere2 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e2);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere3 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e3);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere4 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e4);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere5 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e5);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere6 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e6);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere7 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e7);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere8 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e8);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher filterTextWatchere9 = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            restore(e9);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_audition,container,false);

        getActivity().setTitle("Auditions");

        e1 = (EditText) myView.findViewById(R.id.e1);
        errmsg = (TextView) myView.findViewById(R.id.errmsg);
        errmsg.setVisibility(View.GONE);
        e2 = (EditText) myView.findViewById(R.id.e2);
        e3 = (EditText) myView.findViewById(R.id.e3);
        e4 = (EditText) myView.findViewById(R.id.e4);
        e5 = (EditText) myView.findViewById(R.id.e5);
        e6 = (EditText) myView.findViewById(R.id.e6);
        e7 = (EditText) myView.findViewById(R.id.e7);
        e8 = (EditText) myView.findViewById(R.id.e8);
        e9 = (EditText) myView.findViewById(R.id.e9);
        e1.addTextChangedListener(filterTextWatchere1);
        e2.addTextChangedListener(filterTextWatchere2);
        e3.addTextChangedListener(filterTextWatchere3);
        e4.addTextChangedListener(filterTextWatchere4);
        e5.addTextChangedListener(filterTextWatchere5);
        e6.addTextChangedListener(filterTextWatchere6);
        e7.addTextChangedListener(filterTextWatchere7);
        e8.addTextChangedListener(filterTextWatchere8);
        e9.addTextChangedListener(filterTextWatchere9);


        return myView;
    }


    boolean check(EditText e) {

        if (e.getText().toString().equals(""))
        {

            e.setBackground(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.error_box));
            return false;

        }
        else
        {
            restore(e);
            return true;
        }


    }

    public void post(View view) {
        Log.i("here","hi");
        boolean i1,i2,i3,i4,i5,i6,i7,i8,i9;
        i1=check(e1);
        i2=check(e2);
        i3=check(e3);
        i4=check(e4);
        i5=check(e5);
        i6=check(e6);
        i7=check(e7);
        i8=check(e8);
        i9=check(e9);
        if(i1&&i2&&i3&&i4&&i5&&i6&&i7&&i8&&i9)
        {
            Toast.makeText(getActivity().getApplicationContext(), "Posted!",
                    Toast.LENGTH_SHORT).show();//post it
            c.setProj_name(e1.getText().toString());
            c.setProj_type(e2.getText().toString());
            c.setProj_desc(e3.getText().toString());
            c.setProj_features(e4.getText().toString());
            c.setProj_shotloc(e5.getText().toString());
            c.setProj_audiloc(e6.getText().toString());
            c.setProj_validfrom(e7.getText().toString());
            c.setProj_validto(e8.getText().toString());
            c.setProj_contact(e9.getText().toString());
            Log.i("Details:",c.getProj_audiloc()+c.getProj_contact()+c.getProj_desc()+c.getProj_features());

        }
        else
            err_msg_disp(1);

    }


}
