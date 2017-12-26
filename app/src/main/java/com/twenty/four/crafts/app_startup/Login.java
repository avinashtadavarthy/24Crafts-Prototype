package com.twenty.four.crafts.app_startup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twenty.four.crafts.ForgotPassword;
import com.twenty.four.crafts.Main2Activity;
import com.twenty.four.crafts.Main3Activity;
import com.twenty.four.crafts.R;

public class Login extends AppCompatActivity {

    Button btnCraftsmen, btnClients;
    TextView forgotPassword;
    RelativeLayout parent;
    EditText email;
    TextInputEditText password;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCraftsmen = (Button) findViewById(R.id.btnCraftsmen);
        btnClients = (Button) findViewById(R.id.btnClients);
        forgotPassword = findViewById(R.id.forgot_password);

        parent = findViewById(R.id.parentLayout);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        email.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        animationDrawable = (AnimationDrawable) parent.getBackground();

        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);



        animationDrawable.start();

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    password.setHint("");
                else
                    password.setHint("Password");
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });

        btnCraftsmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Main2Activity.class);
                startActivity(i);
            }
        });

        btnClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Main3Activity.class);
                startActivity(i);
            }
        });

    }

    //keyboard disappears when you click outside
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public void login(View view) {
        Intent i = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(i);
    }
}
