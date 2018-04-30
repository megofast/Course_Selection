package com.example.drosi.course_selection;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ClassSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    RadioButton rdbC1R1,rdbC1R2, rdbC2R1, rdbC2R2;
    RadioButton rdbC3R1, rdbC3R2,rdbC4R1, rdbC4R2;
    RadioGroup rg1, rg2, rg3, rg4;
    CheckedTextView ctv1, ctv2, ctv3, ctv4;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_selection);

        rdbC1R1 = findViewById(R.id.rdbC1R1);
        rdbC1R2 = findViewById(R.id.rdbC1R2);
        rdbC2R1 = findViewById(R.id.rdbC2R1);
        rdbC2R2 = findViewById(R.id.rdbC2R2);
        rdbC3R1 = findViewById(R.id.rdbC3R1);
        rdbC3R2 = findViewById(R.id.rdbC3R2);
        rdbC4R1 = findViewById(R.id.rdbC4R1);
        rdbC4R2 = findViewById(R.id.rdbC4R2);

        ctv1 = findViewById(R.id.cTxtClass1);
        ctv2 = findViewById(R.id.cTxtClass2);
        ctv3 = findViewById(R.id.cTxtClass3);
        ctv4 = findViewById(R.id.cTxtClass4);

        btnSubmit = findViewById(R.id.btnSubmit);

        rdbC1R1.setOnClickListener(this);
        rdbC1R2.setOnClickListener(this);
        rdbC2R1.setOnClickListener(this);
        rdbC2R2.setOnClickListener(this);
        rdbC3R1.setOnClickListener(this);
        rdbC3R2.setOnClickListener(this);
        rdbC4R1.setOnClickListener(this);
        rdbC4R2.setOnClickListener(this);

        btnSubmit.setOnClickListener(this);

        rg1 = findViewById(R.id.radioGroup);
        rg2 = findViewById(R.id.radioGroup2);
        rg3 = findViewById(R.id.radioGroup3);
        rg4 = findViewById(R.id.radioGroup4);

        ctv1.setOnClickListener(this);
        ctv2.setOnClickListener(this);
        ctv3.setOnClickListener(this);
        ctv4.setOnClickListener(this);

        // disable all the radiobuttons
        rdbC1R1.setEnabled(false);
        rdbC1R2.setEnabled(false);
        rdbC2R1.setEnabled(false);
        rdbC2R2.setEnabled(false);
        rdbC3R1.setEnabled(false);
        rdbC3R2.setEnabled(false);
        rdbC4R1.setEnabled(false);
        rdbC4R2.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cTxtClass1:
                changeVisibility(rdbC1R1, rdbC1R2, ctv1);
                break;
            case R.id.cTxtClass2:
                changeVisibility(rdbC2R1, rdbC2R2, ctv2);
                break;
            case R.id.cTxtClass3:
                changeVisibility(rdbC3R1, rdbC3R2, ctv3);
                break;
            case R.id.cTxtClass4:
                changeVisibility(rdbC4R1, rdbC4R2, ctv4);
                break;
            case R.id.rdbC1R1:
                checkConflict();
                break;
            case R.id.rdbC1R2:
                checkConflict();
                break;
            case R.id.rdbC2R1:
                checkConflict();
                break;
            case R.id.rdbC2R2:
                checkConflict();
                break;
            case R.id.rdbC3R1:
                checkConflict();
                break;
            case R.id.rdbC3R2:
                checkConflict();
                break;
            case R.id.rdbC4R1:
                checkConflict();
                break;
            case R.id.rdbC4R2:
                checkConflict();
                break;
            case R.id.btnSubmit:
                sendExtras();
                break;
        }
    }

    private void checkConflict() {
        // Check for schedule conflicts
        ArrayList<RadioButton> checked = new ArrayList<>();
        boolean duplicate = false;

        if (rdbC1R1.isChecked() || rdbC1R2.isChecked()) {
            checked.add((RadioButton)findViewById(rg1.getCheckedRadioButtonId()));
        }
        if (rdbC2R1.isChecked() || rdbC2R2.isChecked()) {
            checked.add((RadioButton)findViewById(rg2.getCheckedRadioButtonId()));
        }
        if (rdbC3R1.isChecked() || rdbC3R2.isChecked()) {
            checked.add((RadioButton)findViewById(rg3.getCheckedRadioButtonId()));
        }
        if (rdbC4R1.isChecked() || rdbC4R2.isChecked()) {
            checked.add((RadioButton)findViewById(rg4.getCheckedRadioButtonId()));
        }

        for (int x = 0; x < checked.size(); x++) {
            if (checked.get(0).getText().toString().equals(checked.get(x).getText().toString())) {
                if (x == 0) {
                    // Ignore this case, false positive for duplicate
                } else {
                    checked.get(x).setChecked(false);
                    duplicate = true;
                    break;
                }
            } else {
                duplicate = false;
            }
        }

        if (duplicate) {
            Toast.makeText(this, "There is a schedule conflict", Toast.LENGTH_LONG).show();
        }
    }

    private void sendExtras() {
        Boolean isComplete = true;
        RadioButton selected;
        Bundle extras = getIntent().getExtras();

        Intent nextScreen = new Intent(ClassSelectionActivity.this, landing.class);
        nextScreen.putExtra("firstName", extras.getString("firstName"));
        nextScreen.putExtra("lastName", extras.getString("lastName"));
        nextScreen.putExtra("phone", extras.getString("phone"));
        nextScreen.putExtra("dob", extras.getString("dob"));
        nextScreen.putExtra("type", extras.getString("type"));
        nextScreen.putExtra("degree", extras.getString("degree"));

        // GO through and get the classes and selected time
        if (ctv1.isChecked()) {
            if (rdbC1R1.isChecked() || rdbC1R2.isChecked()) {
                selected = findViewById(rg1.getCheckedRadioButtonId());
                nextScreen.putExtra("class1", ctv1.getText().toString());
                nextScreen.putExtra("schedule1", selected.getText().toString());
            } else {
                isComplete = false;
            }
        }
        if (ctv2.isChecked()) {
            if (rdbC2R1.isChecked() || rdbC2R2.isChecked()) {
                selected = findViewById(rg2.getCheckedRadioButtonId());
                nextScreen.putExtra("class2", ctv2.getText().toString());
                nextScreen.putExtra("schedule2", selected.getText().toString());
            } else {
                isComplete = false;
            }
        }
        if (ctv3.isChecked()) {
            if (rdbC3R1.isChecked() || rdbC3R2.isChecked()) {
                selected = findViewById(rg3.getCheckedRadioButtonId());
                nextScreen.putExtra("class3", ctv3.getText().toString());
                nextScreen.putExtra("schedule3", selected.getText().toString());
            } else {
                isComplete = false;
            }
        }
        if (ctv4.isChecked()) {
            if (rdbC4R1.isChecked() || rdbC4R2.isChecked()) {
                selected = findViewById(rg4.getCheckedRadioButtonId());
                nextScreen.putExtra("class4", ctv4.getText().toString());
                nextScreen.putExtra("schedule4", selected.getText().toString());
            } else {
                isComplete = false;
            }
        }

        if (isComplete) {
            startActivity(nextScreen);
        } else {
            Toast.makeText(this, "Make sure a time is selected for all selected classes.", Toast.LENGTH_LONG).show();
        }

    }

    private void changeVisibility(RadioButton rdb1, RadioButton rdb2, CheckedTextView ctv) {
        // Check whether the radiobutton is enabled or not
        if (rdb1.isEnabled()) {
            // The radiobutton is enabled, disable it
            rdb1.setEnabled(false);
            rdb2.setEnabled(false);

            // Uncheck any radio button that is checked
            rdb1.setChecked(false);
            rdb2.setChecked(false);

            ctv.setBackgroundColor(Color.WHITE);
            ctv.setChecked(false);
        } else {
            // The radiobutton is disabled, enable it
            rdb1.setEnabled(true);
            rdb2.setEnabled(true);

            // Change the background color of the checked text
            ctv.setBackgroundColor(Color.LTGRAY);
            ctv.setChecked(true);
        }
    }
}
