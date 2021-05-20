package com.example.harmanpreet_c0765590_mt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


    double[] carRents = {20.5, 18.8, 100, 102, 115.5, 150.6, 90.6, 180, 175.6};
    double amount;

    double curAmount;
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

        tvRentDays.setText(String.valueOf(seekBar.getProgress()));

        carsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0){
                    etRent.setText(String.valueOf(carRents[i-1]));
                    calculateAmount();
//                    tvAmount.setText(String.valueOf(carRents[i-1]));
                } else{
                    etRent.setText("");
                    tvAmount.setText("");
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

//                double amt = Double.valueOf(etRent.getText().toString());
//                curAmount = amt * progress;
//                tvAmount.setText(String.valueOf(curAmount));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void selectAge(View view){

//        double amt = curAmount;
//
//        switch (view.getId()){
//
//            case R.id.rb_20:
//                amt += 5.0;
//                break;
//            case R.id.rb_21:
//                amt += 0.0;
//                break;
//            case R.id.rb_60:
//                amt -= 10;
//                break;
//
//        }
//        tvAmount.setText(String.valueOf(amt));


        switch (view.getId()){

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

        Log.i("AGE", "selectAge: " + age);
        calculateAmount();
    }

    public void selectOptions(View view){

        double amt = Double.valueOf(tvAmount.getText().toString());

        CheckBox cb1, cb2, cb3;
        cb1 = findViewById(R.id.cb_gps);
        cb2 = findViewById(R.id.cb_child);
        cb3 = findViewById(R.id.cb_unlimited);

//        switch (view.getId()){
//
//            case R.id.cb_gps:
//                if (cb1.isChecked()){
//                    amt += 5;
//                } else{
//                    amt -= 5;
//                }
//
//                break;
//            case R.id.cb_child:
//                if (cb2.isChecked()){
//                    amt += 7;
//                } else{
//                    amt -= 7;
//                }
//
//                break;
//            case R.id.cb_unlimited:
//                if (cb3.isChecked()){
//                    amt += 10;
//                } else{
//                    amt -= 10;
//                }
//
//                break;
//        }
//        tvAmount.setText(String.valueOf(amt));


        switch (view.getId()){

            case R.id.cb_gps:
                if (cb1.isChecked()){
                    options += 5;
                } else{
                    options -= 5;
                }

                break;
            case R.id.cb_child:
                if (cb2.isChecked()){
                    options += 7;
                } else{
                    options -= 7;
                }

                break;
            case R.id.cb_unlimited:
                if (cb3.isChecked()){
                    options += 10;
                } else{
                    options -= 10;
                }

                break;
        }

        calculateAmount();
    }


    private void calculateAmount(){
        double am = Double.valueOf(etRent.getText().toString());
        int days = Integer.valueOf(tvRentDays.getText().toString());

        am *= days;

        am += age;
        am += options;

        tvAmount.setText(String.format("%.2f", am));

        tvTotalAmount.setText(String.format("%.2f", totalAmount(am)));

    }


    private double totalAmount(double amount){
        return amount + ( amount * 13.0 / 100);
    }


    private boolean validations(){

        if (carsSpinner.getSelectedItemPosition() > 0){
            if (seekBar.getProgress() > 0){
                if (rgAge.getCheckedRadioButtonId() != -1){
                    return true;
                }
                else{
                    Toast.makeText(this, "Please select age.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else{
                Toast.makeText(this, "Please chose number of days for rent.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else{
            Toast.makeText(this, "Please select a car from the list.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
























