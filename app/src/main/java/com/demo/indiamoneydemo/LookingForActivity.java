package com.demo.indiamoneydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LookingForActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.looking_for_act);
        TextView Personal_loan = findViewById(R.id.personal_loan);
        Personal_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LookingForActivity.this, WebActivity.class);
                /*WebView mywebview = (findViewById(R.id.webview));
                mywebview.loadUrl("http://www.javatpoint.com/");*/
                startActivity(i);
            }
        });

        TextView Education_Loan = findViewById(R.id.education_loan);
        Education_Loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LookingForActivity.this, WebActivity.class);
                startActivity(i);
            }
        });
    }
}
