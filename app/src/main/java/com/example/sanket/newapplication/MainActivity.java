package com.example.sanket.newapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {

    public static final String NAME_MESSAGE = "com.example.BlueLogin.NameMessage";
    public static final String PASSWORD_MESSAGE = "com.example.BlueLogin.PasswordMessage";

    private EditText userNameEditText;
    private EditText passwordField;
    private Button button;


    private Integer windowWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        final RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setBackgroundColor(Color.GRAY);
        relativeLayout.setBackgroundResource(R.drawable.img_login_splash);

        setupPasswordField(relativeLayout);
        setupUserNameField(relativeLayout);
        setupSignInButton(relativeLayout);

        setContentView(relativeLayout);

    }

    private void setupUserNameField(RelativeLayout relativeLayout) {
        userNameEditText = new EditText(this);
        userNameEditText.setId(12345);
        userNameEditText.setHint("User Name");

        GradientDrawable editTextDrawable = new GradientDrawable();
        editTextDrawable.setColor(Color.WHITE);
        editTextDrawable.setStroke(1, Color.GRAY);
        userNameEditText.setBackground(editTextDrawable);


        int widthPxValue = (int) (windowWidth() * 0.98);

        RelativeLayout.LayoutParams userNameTextViewParams = new RelativeLayout.LayoutParams(widthPxValue, 180);
        userNameTextViewParams.addRule(RelativeLayout.ABOVE, passwordField.getId());
        userNameTextViewParams.addRule(RelativeLayout.ALIGN_LEFT, passwordField.getId());
        relativeLayout.addView(userNameEditText, userNameTextViewParams);
    }

    private void setupPasswordField(RelativeLayout relativeLayout) {
        passwordField = new EditText(this);
        passwordField.setId(67890);
        passwordField.setHint("Password");
        passwordField.setBackgroundColor(Color.WHITE);

        GradientDrawable editTextDrawable = new GradientDrawable();
        editTextDrawable.setColor(Color.WHITE);
        editTextDrawable.setStroke(1, Color.GRAY);
        passwordField.setBackground(editTextDrawable);

        int passwordFieldWidth = (int) (windowWidth() * 0.98);

        RelativeLayout.LayoutParams passwordFieldParams = new RelativeLayout.LayoutParams(passwordFieldWidth, 180);
        passwordFieldParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        relativeLayout.addView(passwordField, passwordFieldParams);
    }

    private void setupSignInButton(final RelativeLayout relativeLayout) {
        button = new Button(this);
        button.setText("Sign In");

        RelativeLayout.LayoutParams buttonLayoutParams = new RelativeLayout.LayoutParams(windowWidth(), 150);
        buttonLayoutParams.addRule(RelativeLayout.BELOW, passwordField.getId());
        buttonLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            button.setOnClickListener(
                    new Button.OnClickListener() {
                        public void onClick(View view) {
                            performOnClickAction(relativeLayout);
                        }
                    }
            );

        relativeLayout.addView(button, buttonLayoutParams);
    }

    private void performOnClickAction(RelativeLayout relativeLayout) {
        Intent intent = new Intent(this, SecondScreen.class);
        String nameMessage = userNameEditText.getText().toString();
        String passwordMessage = passwordField.getText().toString();

        intent.putExtra(NAME_MESSAGE, nameMessage);
        intent.putExtra(PASSWORD_MESSAGE, passwordMessage);
        startActivity(intent);
    }
}
