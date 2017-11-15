package com.example.stefantsokev.leanandrotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.stefantsokev.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get current datetime
        EditText txt = (EditText)findViewById(R.id.editText2);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        txt.setText(date);

        //set welcome msg via custom variable
        TextView txtView = (TextView)findViewById(R.id.textView2);
        txtView.setText(MainApplication.str1);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

}
