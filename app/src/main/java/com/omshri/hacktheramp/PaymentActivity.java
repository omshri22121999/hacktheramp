package com.omshri.hacktheramp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    private TextView priceTv;
    private TextView paymentTv;
    private TextView discountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        priceTv = findViewById(R.id.price_tv);
        paymentTv = findViewById(R.id.payment_tv);
        discountTv = findViewById(R.id.discount_tv);

        Intent intent = getIntent();

        priceTv.setText("₹ "+String.valueOf(intent.getIntExtra("price",0)));
        discountTv.setText("₹ "+String.valueOf(intent.getIntExtra("discount",0)));
        paymentTv.setText("₹ "+String.valueOf(intent.getIntExtra("price",0)-(intent.getIntExtra("discount",0))));

    }
}