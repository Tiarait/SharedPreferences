package com.example.tiar.task;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.tiar.frtest.R;

public class FirstScreen extends AppCompatActivity {
    Button register, login;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        final Context context = this;

        final AutoCompleteTextView etEmail = (AutoCompleteTextView) findViewById(R.id.etEmail);
        final AutoCompleteTextView etPassword = (AutoCompleteTextView) findViewById(R.id.etPassword);
        register = (Button) findViewById(R.id.btnRegister);
        login = (Button) findViewById(R.id.btnLogin);

        preferences  = getSharedPreferences("USERSINF", MODE_PRIVATE);
        final boolean uLogin = preferences.getBoolean("login", false);
        if (uLogin){
            Intent intent = new Intent(FirstScreen.this, ThirdScreen.class);
            FirstScreen.this.startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uEmail = etEmail.getText().toString();
                String uPass = etPassword.getText().toString();
                String bPass = preferences.getString(uEmail, "-");

                if (!uEmail.contains("@") || uEmail.length() < 6)
                    Toast.makeText(context, getString(R.string.error_email), Toast.LENGTH_LONG).show();
                else if (uPass.length() < 4)
                    Toast.makeText(context, getString(R.string.error_password), Toast.LENGTH_LONG).show();
                else if (!Utils.md5Custom(uPass).equals(bPass))
                    Toast.makeText(context, getString(R.string.error_incorrect_password), Toast.LENGTH_LONG).show();
                else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("user", uEmail);
                    editor.putBoolean("login", true);
                    editor.commit();

                    Intent intent = new Intent(FirstScreen.this, ThirdScreen.class);
                    FirstScreen.this.startActivity(intent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstScreen.this, SecondScreen.class);
                FirstScreen.this.startActivity(intent);
            }
        });
    }
}
