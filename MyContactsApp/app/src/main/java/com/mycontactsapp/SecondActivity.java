package com.mycontactsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Name = findViewById(R.id.userN);
    }

    public void toastOut(View v){
        String gotten = Name.getText().toString();
        int g = gotten.length();
        String gs = Integer.toString(g);
        Toast.makeText(getApplicationContext(), gs, Toast.LENGTH_SHORT).show();
    }

}
