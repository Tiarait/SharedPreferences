package com.example.tiar.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiar.frtest.R;

public class ThirdScreen extends AppCompatActivity {
    Button logout;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);


        final TextView user = (TextView) findViewById(R.id.tvEmail);
        logout = (Button) findViewById(R.id.btnLogout);

        preferences  = getSharedPreferences("USERSINF", MODE_PRIVATE);
        String bEmail = preferences.getString("user", "");
        user.setText(bEmail);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("login", false);
                editor.commit();
                Intent intent = new Intent(ThirdScreen.this, FirstScreen.class);
                ThirdScreen.this.startActivity(intent);
            }
        });
    }
}
