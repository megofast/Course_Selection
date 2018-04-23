package com.example.drosi.course_selection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ClassList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        // Declare all the widgets
        final Switch swCert = (Switch) findViewById(R.id.swCert);
        final Spinner dropAssoc = (Spinner) findViewById(R.id.dropAssoc);
        final Spinner dropCert = (Spinner) findViewById(R.id.dropCert);
        final TextView lblCert = (TextView) findViewById(R.id.lblCert2);
        final TextView lblMajor = (TextView) findViewById(R.id.lblMajor);
        final Button btnNext = (Button) findViewById(R.id.btnNext);
        final EditText firstName = (EditText) findViewById(R.id.txtFirst);
        final EditText lastName = (EditText) findViewById(R.id.txtLast);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        final EditText day = (EditText) findViewById(R.id.txtDay);
        final EditText year = (EditText) findViewById(R.id.txtYear);
        final Spinner month = (Spinner) findViewById(R.id.spMonth);

        firstName.requestFocus();

        // Setup the switch to change the visibility of the spinners
        swCert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dropCert.setVisibility(View.INVISIBLE);
                    lblCert.setVisibility(View.INVISIBLE);
                    dropAssoc.setVisibility(View.VISIBLE);
                    lblMajor.setVisibility(View.VISIBLE);
                } else {
                    dropCert.setVisibility(View.VISIBLE);
                    lblCert.setVisibility(View.VISIBLE);
                    dropAssoc.setVisibility(View.INVISIBLE);
                    lblMajor.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check to make sure there is values entered in all the fields
                if (check_valid(firstName) == false ||
                        check_valid(lastName) == false ||
                        check_valid(phone) == false ||
                        check_valid(day) == false ||
                        check_valid(year) == false) {
                    // No value entered, make toast
                    Toast.makeText(ClassList.this, "Invalid Entry, Please Fill Out All Fields.", Toast.LENGTH_LONG).show();
                } else {
                    // Combine the date into a string
                    String dob = month.getSelectedItem().toString() + "/" + day.getText().toString() + "/" + year.getText().toString();

                    // Submit the data!
                    Intent nextScreen = new Intent(ClassList.this, landing.class);
                    nextScreen.putExtra("firstName", firstName.getText().toString());
                    nextScreen.putExtra("lastName", lastName.getText().toString());
                    nextScreen.putExtra("phone", phone.getText().toString());
                    nextScreen.putExtra("dob", dob);

                    // Determine whether the user has selected certificate or degree
                    if (dropAssoc.getVisibility() == View.VISIBLE) {
                        // The user has selected degree
                        nextScreen.putExtra("type", "Associates Degree");
                        nextScreen.putExtra("degree", dropAssoc.getSelectedItem().toString());
                    } else {
                        // The user selected certificate
                        nextScreen.putExtra("type", "Certificate");
                        nextScreen.putExtra("degree", dropCert.getSelectedItem().toString());
                    }

                    // Open the next activity
                    startActivity(nextScreen);
                }
            }
        });

    }

    private boolean check_valid(EditText widget) {
        if (widget.getText().toString().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
