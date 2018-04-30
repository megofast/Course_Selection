package com.example.drosi.course_selection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class landing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Create the logout button and the click listener for it
        final Button logout = (Button)findViewById(R.id.btnLogout);
        TextView name = findViewById(R.id.name);
        TextView phone = findViewById(R.id.phone);
        TextView dob = findViewById(R.id.dob);
        TextView degree = findViewById(R.id.degree);
        TextView major = findViewById(R.id.major);
        EditText next = findViewById(R.id.nextYear);

        next.setEnabled(false);

        Bundle extras = getIntent().getExtras();

        name.setText(extras.getString("firstName") + " " + extras.getString("lastName"));
        phone.setText(extras.getString("phone"));
        dob.setText(extras.getString("dob"));
        degree.setText(extras.getString("type"));
        major.setText(extras.getString("degree"));

        if (extras.getString("class1") != null) {
            next.setText(extras.getString("class1") + "\n" + extras.getString("schedule1"));
        }
        if (extras.getString("class2") != null) {
            next.append("\n" + extras.getString("class2") + "\n" + extras.getString("schedule2"));
        }
        if (extras.getString("class3") != null) {
            next.append("\n" + extras.getString("class3") + "\n" + extras.getString("schedule3"));
        }
        if (extras.getString("class4") != null) {
            next.append("\n" + extras.getString("class4") + "\n" + extras.getString("schedule4"));
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(landing.this, MainActivity.class));
            }
        });
    }
}
