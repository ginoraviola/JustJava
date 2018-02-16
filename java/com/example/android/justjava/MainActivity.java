package com.example.android.justjava;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 0;
    int pricePerCup = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String summaryMessage = createOrderSummary(price);
        displayMessage(summaryMessage);

    }
    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    public int calculatePrice(){
        int price = numberOfCoffees * pricePerCup;
        return price;
    }

    public String createOrderSummary(int price){
        String name = "Gino Raviola";
        String summary = "Name: " + name + "\nQuantity: " + numberOfCoffees +
                "\nTotal: " + price + "\nThank You!";
        return summary;
    }

    public void increment(View view){
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);

    }
    public void decrement(View view){
        if(numberOfCoffees>=1){
            numberOfCoffees--;
        }
        displayQuantity(numberOfCoffees);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quant) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quant);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}