package com.demo.indiamoneydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import sharukh.sliderdtpicker.SliderDateTimePicker;

public class RemindersActivity extends AppCompatActivity {

    private TextView reminder_text;
    private SimpleDateFormat sdf = new SimpleDateFormat("d MMM, h aa", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_reminder);
        Button btn_set_reminder = findViewById(R.id.btn_set_reminder);
        reminder_text = findViewById(R.id.reminder_text);
        btn_set_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SliderDateTimePicker.newInstance()
                        .setOnDateTimeSetListener((new SliderDateTimePicker.OnDateTimeSetListener() {
                            @Override
                            public void onDateTimeSelected(final Calendar startTime) {
                                reminder_text.setText("Reminder set at "+sdf.format(startTime.getTime()));
                                Toast.makeText(RemindersActivity.this, "Alarm set at "+sdf.format(startTime.getTime()), Toast.LENGTH_SHORT).show();
                            }
                        }))
                        .setStartLabel("Set Time")
                        .show(getSupportFragmentManager(), "Your wish");
            }
        });
    }
}
