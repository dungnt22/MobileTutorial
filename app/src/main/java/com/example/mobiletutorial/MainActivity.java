package com.example.mobiletutorial;

import android.os.Bundle;

import com.example.main.DonationApp;
import com.example.mobiletutorial.databinding.ActivityMainBinding;
import com.example.models.Donation;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Base {

    private ActivityMainBinding binding;
    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private EditText amountText;
    private TextView amountTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        donateButton = (Button) findViewById(R.id.donateButton);
        if (donateButton != null) {
            Log.v("Donate", "Really got the donate button");
        }

        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        amountText = (EditText) findViewById(R.id.paymentAmount);
        amountTotal = (TextView) findViewById(R.id.total);

        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
        amountTotal.setText("$0");
    }


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    public void donateButtonPressed(View view) {
        int donateAmount = amountPicker.getValue();
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal ? "PayPal" : "Direct";
        String text = amountText.getText().toString();

        if (!text.equals("")) {
            donateAmount = Integer.parseInt(text);
            amountPicker.setValue(0);
        }

        if (donateAmount > 0) {
            donationApp.newDonation(new Donation(donateAmount, method));
            progressBar.setProgress(donationApp.getTotalDonated());
            String str = "$" + donationApp.getTotalDonated();
            amountTotal.setText(str);
        }
    }

    public void reset(MenuItem menuItem) {
        donationApp.dbManager.reset();
        donationApp.setTotalDonated(0);
        progressBar.setProgress(donationApp.getTotalDonated());
        amountText.setText("");
        String str = "$" + donationApp.getTotalDonated();
        amountTotal.setText(str);
    }
}