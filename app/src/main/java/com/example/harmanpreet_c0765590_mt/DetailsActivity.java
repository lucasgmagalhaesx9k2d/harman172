package com.example.harmanpreet_c0765590_mt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView tvName, tvRent, tvDays, tvAmount, tvTotalAmount, tvAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getIDs();

        Intent intent = getIntent();
        CarInfo info = (CarInfo) intent.getSerializableExtra("details");





        tvName.setText("Car: " + info.getCarName());
        tvRent.setText("Daily Rent: " + info.getCarRent());
        tvDays.setText("Car is rented for " + info.getDays() + " days");
        tvAmount.setText("Amount: " + info.getAmount());
        tvTotalAmount.setText("Total amount (tax included): " + info.getTotalAmount());
        tvAge.setText(info.getAge());


    }


    private void getIDs(){
        tvName = findViewById(R.id.tv_car_name);
        tvRent = findViewById(R.id.tv_car_rent);
        tvDays = findViewById(R.id.tv_rent_days);
        tvAmount = findViewById(R.id.tv_det_amt);
        tvTotalAmount = findViewById(R.id.tv_total_amt);
        tvAge = findViewById(R.id.tv_age);
    }
}
