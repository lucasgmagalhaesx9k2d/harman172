package com.example.harmanpreet_c0765590_mt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner carsSpinner;
    EditText etRent;
    TextView tvRentDays, tvAmount, tvTotalAmount;
    SeekBar seekBar;
    RadioGroup rgAge;
    Button btnDetails;
    CheckBox cb1, cb2, cb3;

    double[] carRents = {20.5, 18.8, 100, 102, 115.5, 150.6, 90.6, 180, 175.6};
    double age = 0.0;
    int options = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carsSpinner = findViewById(R.id.car_spinner);
        etRent = findViewById(R.id.et_rent);
        tvRentDays = findViewById(R.id.tv_days);
        seekBar = findViewById(R.id.seekbar_days);
        tvAmount = findViewById(R.id.tv_amount);
        tvTotalAmount = findViewById(R.id.tv_total_amount);
        rgAge = findViewById(R.id.rg_age);
        btnDetails = findViewById(R.id.btn_details);

        cb1 = findViewById(R.id.cb_gps);
        cb2 = findViewById(R.id.cb_child);
        cb3 = findViewById(R.id.cb_unlimited);

        tvRentDays.setText(String.valueOf(seekBar.getProgress()));

        carsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    etRent.setText(String.valueOf(carRents[i - 1]));
                    calculateAmount();
                } else {
                    clearFields();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                tvRentDays.setText(String.valueOf(progress));
                calculateAmount();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()) {
                    CarInfo info = new CarInfo();

                    info.setCarName(carsSpinner.getSelectedItem().toString());
                    info.setCarRent(etRent.getText().toString());
                    info.setDays(tvRentDays.getText().toString());

                    if (rgAge.getCheckedRadioButtonId() == R.id.rb_20) {
                        info.setAge("Person's age is Less than 20");
                    } else if (rgAge.getCheckedRadioButtonId() == R.id.rb_21) {
                        info.setAge("Person's age is between 21 and 60");
                    } else if (rgAge.getCheckedRadioButtonId() == R.id.rb_60) {
                        info.setAge("Person ages above 60");
                    }

                    info.setAmount(tvAmount.getText().toString());
                    info.setTotalAmount(tvTotalAmount.getText().toString());


                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("details", info);
                    startActivity(intent);


                    clearFields();


                }
            }
        });

    }

    public void selectAge(View view) {

        switch (view.getId()) {

            case R.id.rb_20:
                age = 5.0;
                break;
            case R.id.rb_21:
                age = 0.0;
                break;
            case R.id.rb_60:
                age = -10.0;
                break;

        }

        calculateAmount();
    }

    public void selectOptions(View view) {

        switch (view.getId()) {

            case R.id.cb_gps:
                if (cb1.isChecked()) {
                    options += 5;
                } else {
                    options -= 5;
                }

                break;
            case R.id.cb_child:
                if (cb2.isChecked()) {
                    options += 7;
                } else {
                    options -= 7;
                }

                break;
            case R.id.cb_unlimited:
                if (cb3.isChecked()) {
                    options += 10;
                } else {
                    options -= 10;
                }

                break;
        }

        calculateAmount();
    }


    private void calculateAmount() {
        double amount = Double.valueOf(etRent.getText().toString());
        int days = Integer.valueOf(tvRentDays.getText().toString());

        amount *= days;

        amount += age;
        amount += options;

        tvAmount.setText(String.format("%.2f", amount));

        tvTotalAmount.setText(String.format("%.2f", totalAmount(amount)));

    }


    private double totalAmount(double amount) {
        return amount + (amount * (13.0 / 100));
    }


    private boolean validations() {

        if (carsSpinner.getSelectedItemPosition() > 0) {
            if (seekBar.getProgress() > 0) {
                if (rgAge.getCheckedRadioButtonId() != -1) {
                    return true;
                } else {
                    Toast.makeText(this, "Please select age.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(this, "Please chose number of days for rent.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Please select a car from the list.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void clearFields() {
        carsSpinner.setSelection(0);
        etRent.setText(String.valueOf(0.0));
        seekBar.setProgress(1);
        rgAge.clearCheck();
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        tvAmount.setText("Amount");
        tvTotalAmount.setText("Total Payment");
    }
}
























