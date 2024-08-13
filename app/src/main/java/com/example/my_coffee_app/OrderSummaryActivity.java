package com.example.my_coffee_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderSummaryActivity extends AppCompatActivity {

    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

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
    }
}
