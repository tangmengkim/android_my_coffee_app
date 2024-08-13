package com.example.my_coffee_app;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderSummaryActivity extends AppCompatActivity {

    private float totalAmount = 0;
    private Button btnBack,btnCompleteOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnCompleteOrder =(Button) findViewById(R.id.btnCompleteOrder);
        LinearLayout orderSummaryListLayout = findViewById(R.id.orderSummaryListLayout);
        TextView totalOrderAmountText = findViewById(R.id.totalOrderAmountText);

        ArrayList<Coffee> orderList = getIntent().getParcelableArrayListExtra("orderList", Coffee.class);

        // Display the ordered items and calculate the total amount
        for (Coffee coffee : orderList) {
            if (coffee.getQuantity() > 0) {
                View orderItemView = LayoutInflater.from(this).inflate(R.layout.coffee_item_summary, orderSummaryListLayout, false);
                TextView coffeeNameText = orderItemView.findViewById(R.id.summaryCoffeeNameText);
                TextView coffeeQuantityText = orderItemView.findViewById(R.id.summaryCoffeeQuantityText);
                TextView coffeeTotalPriceText = orderItemView.findViewById(R.id.summaryCoffeeTotalPriceText);

                coffeeNameText.setText(coffee.getName());
                coffeeQuantityText.setText("x" + coffee.getQuantity());
                coffeeTotalPriceText.setText("$" + coffee.getTotalPrice());

                totalAmount += coffee.getTotalPrice();
                orderSummaryListLayout.addView(orderItemView);
            }
        }

        totalOrderAmountText.setText("Total: $" + totalAmount);

        btnBack.setOnClickListener(v -> finish());
        btnCompleteOrder.setOnClickListener(v -> {
            showOrderCompleteToast();
            resetOrderList(orderList);
            Intent intent = new Intent(OrderSummaryActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
            startActivity(intent);
            finish();
        });

    }

    private void showOrderCompleteToast() {
        Toast.makeText(this, "Order Completed. Total Amount: $" + totalAmount, Toast.LENGTH_LONG).show();
    }

    private void resetOrderList(ArrayList<Coffee> orderList) {
        for (Coffee coffee : orderList) {
            coffee.setQuantity(0);
        }
    }


}
