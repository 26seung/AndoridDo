package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity2 extends AppCompatActivity implements View.OnClickListener {

    private TextView mMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");

        mMessageTextView = findViewById(R.id.message_edit_text);
        mMessageTextView.setText(age + "살" + name);


        findViewById(R.id.result_button).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("result",mMessageTextView.getText().toString());

        setResult(RESULT_OK, intent);

        finish();
    }
}