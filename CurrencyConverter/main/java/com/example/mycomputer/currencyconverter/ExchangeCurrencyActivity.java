package com.example.mycomputer.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ExchangeCurrencyActivity extends AppCompatActivity {
    private Currency currency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_currency);

        currency = new Currency();
        currency.initialize();

    }

    public void convertCurrency (View view) {
       EditText origAmtEditText = (EditText) findViewById(R.id.orignalAmt);
       EditText origCurrencyEditText = (EditText)findViewById(R.id.origCurrency);
       EditText newCurrencyEditText = (EditText)findViewById(R.id.newCurrency);


       double origAmt = new  Double( origAmtEditText.getText().toString()).doubleValue();
       String origCurrency = origCurrencyEditText.getText().toString();
       String newCurrency = newCurrencyEditText.getText().toString();


       // Check the strings for a currency.
        double origValue = currency.getCurrency(origCurrency);

        double newValue = currency.getCurrency(newCurrency);

        if ( origValue < 0) {

            Toast.makeText(this,
                    "Not A known currency "+ origCurrency, Toast.LENGTH_LONG).show();
        }
        if( newValue < 0) {
            Toast.makeText(this,
                    "Not A known currency "+ newCurrency, Toast.LENGTH_LONG).show();
        }

        double newMoney = origAmt * newValue;

        EditText newAmtEditText = (EditText)findViewById(R.id.newAmt);
        newAmtEditText.setText(new Double(newMoney).toString());
    }
}


class Currency {

    private HashMap<String , Double> currencyMap;

    public Currency (){}

    public void initialize () {
        currencyMap = new HashMap<>();
        /* Down load from a currency website or financial one */

        currencyMap.put("USD", 1.0);
        currencyMap.put("EUR", 0.85618);
        currencyMap.put("GBP", 0.75068);
        currencyMap.put("CAD", 1.28866);
        currencyMap.put("INR", 68.4066);
        currencyMap.put("MXN", 19.6944);
        currencyMap.put("AUD", 1.32746);
        currencyMap.put("CNY", 6.38694);
        currencyMap.put("MYR",  3.98256);
        currencyMap.put("COP", 2874.98);
    }

    public double getCurrency(String currency ){
        double returnVal = -1;
       Double value =  currencyMap.get(currency);
       if ( value != null) {
           returnVal = value.doubleValue();
       }
       return returnVal;
    }
}
