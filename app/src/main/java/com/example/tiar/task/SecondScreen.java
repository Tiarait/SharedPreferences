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

public class SecondScreen extends AppCompatActivity {
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        final Context context = this;

        final AutoCompleteTextView email = (AutoCompleteTextView) findViewById(R.id.etEmail_r);
        final AutoCompleteTextView password = (AutoCompleteTextView) findViewById(R.id.etPassword_r);
        final AutoCompleteTextView repassword = (AutoCompleteTextView) findViewById(R.id.etRepassword_r);
        register = (Button) findViewById(R.id.btnRecord);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences  = getSharedPreferences("USERSINF", MODE_PRIVATE);
                String uEmail = email.getText().toString();
                String uPass = password.getText().toString();
                String uRepass = repassword.getText().toString();

                String bPass = preferences.getString(uEmail, "");

                if (!Utils.valid(uEmail))
                    Toast.makeText(context, getString(R.string.error_email), Toast.LENGTH_LONG).show();
                else if (uPass.length() < 4)
                    Toast.makeText(context, getString(R.string.error_password), Toast.LENGTH_LONG).show();
                else if (bPass.length() > 0) {
                    Toast.makeText(context, getString(R.string.error_invalid_email), Toast.LENGTH_LONG).show();
                } else if (!uPass.equals(uRepass)) {
                    Toast.makeText(context, getString(R.string.error_invalid_password), Toast.LENGTH_LONG).show();
                } else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(uEmail, Utils.md5Custom(uPass));
                    editor.commit();

                    Intent intent = new Intent(SecondScreen.this, FirstScreen.class);
                    SecondScreen.this.startActivity(intent);
                }
            }
        });
    }


}
