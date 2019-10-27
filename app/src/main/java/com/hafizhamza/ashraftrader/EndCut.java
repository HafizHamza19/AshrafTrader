package com.hafizhamza.ashraftrader;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hafizhamza.ashraftrader.SQLite.Database;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EndCut extends AppCompatActivity {
EditText Cus_Name,Weight,Rate,TakeAmount,PaidAmount;
TextView Amount,ReturnAmount,billC_name,bill_Sname,bill_Date,bill_weight,bill_Rate,bill_Amount,bill_Time,bill_Paid,bill_Credit,bill_Bal;
Spinner Sell_Name;
LinearLayout Bill,Record;
    double Total_amount;
    Database db;
    String formattedDate;
    String Time;
    Double Paid;
    Double Credit;
    Double Balance;
    Double Return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_cut);
        Cus_Name=(EditText)findViewById(R.id.id_customername);
        Weight=(EditText)findViewById(R.id.id_weight);
        Rate=(EditText)findViewById(R.id.id_rate);
        Amount=(TextView)findViewById(R.id.id_amount);
        TakeAmount=(EditText)findViewById(R.id.id_takeAmount);
        PaidAmount=(EditText)findViewById(R.id.id_paidAmount);
        ReturnAmount=(TextView)findViewById(R.id.id_returnamount);
        Sell_Name=(Spinner)findViewById(R.id.id_sellername);
        Bill=(LinearLayout)findViewById(R.id.bill);
        Record=(LinearLayout)findViewById(R.id.recordsave);
        bill_Date=(TextView)findViewById(R.id.date);
        billC_name=(TextView)findViewById(R.id.custom_name);
        bill_Sname=(TextView)findViewById(R.id.sellername);
        bill_weight=(TextView)findViewById(R.id.weight);
        bill_Rate=(TextView)findViewById(R.id.rate);
        bill_Amount=(TextView)findViewById(R.id.amount);
        bill_Time=(TextView)findViewById(R.id.time);
        bill_Paid=(TextView)findViewById(R.id.paid);
        bill_Credit=(TextView)findViewById(R.id.credit);
        bill_Bal=(TextView)findViewById(R.id.balance);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);

        Time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        db=new Database(this);
        ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.seller_name));
        Sell_Name.setAdapter(adapter);

        Sell_Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    try {
                        Total_amount = Double.parseDouble(String.valueOf(Weight.getText())) * Double.parseDouble(String.valueOf(Rate.getText()));
                        Amount.setText(String.valueOf("Total Amount : " + Total_amount));
                        TakeAmount.setVisibility(View.VISIBLE);
                        PaidAmount.setVisibility(View.VISIBLE);
                    }
                    catch (Exception io)
                    {
                        Toast.makeText(EndCut.this, io.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });    }


    public void Calculation(View view) {

        try {
            Return = Double.parseDouble(String.valueOf(TakeAmount.getText())) - Total_amount;
            ReturnAmount.setText("Return Amount : " + Return);
        }catch (Exception io){

        }
    }

    public void Save(View view) {
        Paid=Double.parseDouble(PaidAmount.getText().toString());
        if(Paid<Total_amount) {
            Credit = Total_amount - Paid;
        }
        else {
            Credit=0.0;
        }
        if(Paid>Total_amount) {
            Balance = Paid - Total_amount;
        }
        else {
            Balance=0.0;
        }
        try {

            db.DataEnter(formattedDate+":"+Time,Cus_Name.getText().toString(),Sell_Name.getSelectedItem().toString(),"End Cut", Double.parseDouble(Weight.getText().toString()),Double.parseDouble(Rate.getText().toString()),Total_amount,Paid,Credit,Balance);

            Toast.makeText(this, "Record Save", Toast.LENGTH_LONG).show();
            Record.setVisibility(View.GONE);
            Bill.setVisibility(View.VISIBLE);
            bill_Amount.setText(String.valueOf(Total_amount));
            bill_Rate.setText(Rate.getText());
            bill_weight.setText(Weight.getText());
            bill_Sname.setText(Sell_Name.getSelectedItem().toString());
            billC_name.setText(Cus_Name.getText());
            bill_Date.setText(formattedDate);
            bill_Time.setText(Time);
            bill_Bal.setText(Balance.toString());
            bill_Credit.setText(Credit.toString());
            bill_Paid.setText(Paid.toString());
        } catch (Exception e) {
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void back(View view) {
        Bill.setVisibility(View.GONE);
        Record.setVisibility(View.VISIBLE);
    }
}
