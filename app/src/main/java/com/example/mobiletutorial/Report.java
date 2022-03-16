package com.example.mobiletutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mobiletutorial.R;
import com.example.models.Donation;

public class Report extends Base {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        listView = (ListView) findViewById(R.id.reportList);

        DonationAdapter adapter = new DonationAdapter(this, donationApp.dbManager.getAll());
        listView.setAdapter(adapter);
    }

    public void reset(MenuItem menuItem) {
        donationApp.dbManager.reset();
        DonationAdapter adapter = new DonationAdapter(this, donationApp.dbManager.getAll());
        listView.setAdapter(adapter);
    }
}
