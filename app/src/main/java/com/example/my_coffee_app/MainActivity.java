package com.example.my_coffee_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Coffee> coffeeList = new ArrayList<>();
    private int totalAmount = 0;
    private Button submitOrderButton;  // Declare it here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitOrderButton = findViewById(R.id.submitOrderButton);  // Initialize it here

        LinearLayout coffeeListLayout = findViewById(R.id.coffeeListLayout);

        // Populate coffee data
        coffeeList.add(new Coffee("Espresso", 5));
        coffeeList.add(new Coffee("Latte", 7));
        coffeeList.add(new Coffee("Cappuccino", 6));

        // Dynamically add coffee items to the layout
        for (Coffee coffee : coffeeList) {
            View coffeeItemView = LayoutInflater.from(this).inflate(R.layout.coffee_item, coffeeListLayout, false);
            TextView coffeeNameText = coffeeItemView.findViewById(R.id.coffeeNameText);
            TextView coffeePriceText = coffeeItemView.findViewById(R.id.coffeePriceText);
            TextView coffeeQuantityText = coffeeItemView.findViewById(R.id.coffeeQuantityText);  // New TextView for quantity
            Button addButton = coffeeItemView.findViewById(R.id.addButton);
            Button minusButton = coffeeItemView.findViewById(R.id.minusButton);

            coffeeNameText.setText(coffee.getName());
            coffeePriceText.setText("$" + coffee.getPrice());
            coffeeQuantityText.setText(String.valueOf(coffee.getQuantity()));  // Set initial quantity

            addButton.setOnClickListener(v -> {
                coffee.setQuantity(coffee.getQuantity() + 1);
                coffeeQuantityText.setText(String.valueOf(coffee.getQuantity()));  // Update quantity display
                updateTotalAmount();
            });

            minusButton.setOnClickListener(v -> {
                if (coffee.getQuantity() > 0) {
                    coffee.setQuantity(coffee.getQuantity() - 1);
                    coffeeQuantityText.setText(String.valueOf(coffee.getQuantity()));  // Update quantity display
                    updateTotalAmount();
                }
            });

            coffeeListLayout.addView(coffeeItemView);
        }

        // Initially, disable the submit button if no items are selected
        submitOrderButton.setEnabled(totalAmount > 0);

        submitOrderButton.setOnClickListener(v -> {
            if (totalAmount > 0) {
                Intent intent = new Intent(MainActivity.this, OrderSummaryActivity.class);
                intent.putParcelableArrayListExtra("orderList", coffeeList);
                startActivity(intent);
            }
        });
    }

    private void updateTotalAmount() {
        totalAmount = 0;
        for (Coffee coffee : coffeeList) {
            totalAmount += coffee.getTotalPrice();
        }
        // Enable or disable the submit button based on the total amount
        submitOrderButton.setEnabled(totalAmount > 0);
    }
}
