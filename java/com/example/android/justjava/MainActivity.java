package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2;
    int pricePerCup = 5;
    int whippedCreamPrice = 1;
    int chocolatePrice = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = 0;
        // Gets the user's name
        EditText userName = (EditText) findViewById(R.id.name_editText);
        String name = userName.getText().toString();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCB = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCB.isChecked();

        // Figure out if the user wants chocolate topping
        CheckBox chocolateCB = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCB.isChecked();

        price = calculatePrice(hasWhippedCream, hasChocolate);
        String summaryMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        sendEmail(summaryMessage, name);




    }

    /**
     * Calculates the price of the order.
     *
     * @param hasChocolate    is whether or not the user wants chocolate topping
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @return total price
     */
    public int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        int price = pricePerCup;
        if (hasWhippedCream) {
            price += whippedCreamPrice;
        }
        if (hasChocolate) {
            price += chocolatePrice;
        }

        price = numberOfCoffees * price;
        return price;
    }

    /**
     * @param price           of the order
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @param name            of the user
     * @return text summary
     */
    public String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {

        String summary = "Name: " + name;
        summary += "\nAdd Whipped Cream? " + addWhippedCream;
        summary += "\nAdd Chocolate? " + addChocolate;
        summary += "\nQuantity: " + numberOfCoffees;
        summary += "\nTotal: $" + price;
        summary += "\nThank You!";
        return summary;
    }

    public void increment(View view) {

        if (numberOfCoffees < 100) {
            numberOfCoffees++;
        }
        displayQuantity(numberOfCoffees);

    }

    public void decrement(View view) {

        if (numberOfCoffees > 1) {
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
     * This method triggers the email app to send the order summary
     * @param summary of the order
     * @param name to know whose order it is.
     */
    private void sendEmail(String summary, String name){


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, summary );
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}