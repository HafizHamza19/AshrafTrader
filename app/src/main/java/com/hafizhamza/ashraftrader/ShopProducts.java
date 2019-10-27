package com.hafizhamza.ashraftrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShopProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
    }

    public void EndCut(View view) {
        Intent i=new Intent(this,EndCut.class);
        startActivity(i);
    }
}
