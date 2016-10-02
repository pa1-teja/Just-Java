package com.example.pavan.justjava;


 import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
   * This app displays an order form to order coffee.
   */
      public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 99;
    EditText personName;
    Button incrementer,decrementer;
    CheckBox checkBox,chocoCheck;
    boolean whippedCream,chocolate;
     @Override
         protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_main);

         checkBox = (CheckBox) findViewById(R.id.checkbox);
         chocoCheck = (CheckBox) findViewById(R.id.choco_check);
         incrementer = (Button) findViewById(R.id.incrementer);
         decrementer = (Button) findViewById(R.id.decrementer);
         personName = (EditText) findViewById(R.id.person_name);

         display(numberOfCoffees);
         incrementer.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (numberOfCoffees ==100)
                 {
                     display(numberOfCoffees);
                     Toast.makeText(getApplicationContext(),"maximum orders you can make are 100",Toast.LENGTH_SHORT).show();
                     return;
                 }
                 else {
                     numberOfCoffees++;
                     display(numberOfCoffees);
                 }
             }
         });
         decrementer.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (numberOfCoffees <= 1)
                 {
                     display(numberOfCoffees);
                     Toast.makeText(getApplicationContext(), "minimum orders you can make is 1", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 else {
                     numberOfCoffees--;
                     display(numberOfCoffees);
                 }
             }
         });
     }


    public int calculatePrice(int numberOfCoffees,boolean whippedCream,boolean chocolate)
    {
        int basePrice = 5;
        int totalAmount;

        if (whippedCream)
        {
            basePrice+= 1;
        }
        else if(chocolate){
            basePrice+= 2;
        }
        totalAmount =(numberOfCoffees*basePrice);
        return totalAmount;
    }


    /**
           * This method is called when the order button is clicked.
           */
              public void submitOrder(View view) {
                   whippedCream = checkBox.isChecked();
                  chocolate = chocoCheck.isChecked();


                  Intent mail = new Intent(Intent.ACTION_SEND);
                  mail.setType("text/plain");
                  mail.putExtra(Intent.EXTRA_TEXT, createOrderSummary(personName.getText().toString(), whippedCream, chocolate, numberOfCoffees,
                          calculatePrice(numberOfCoffees, whippedCream, chocolate)));
                  mail.putExtra(Intent.EXTRA_SUBJECT,"JustJava");
                  mail.putExtra(Intent.EXTRA_EMAIL,"pavanteja.93@gmail.com");
                  startActivity(mail);


                  Log.i("MainActivity", "whippedCream value : " + whippedCream);
                  Log.i("MainActivity","chocolate value : " + chocolate);
                  //displayMessage(createOrderSummary(personName.getText().toString(),whippedCream,chocolate,numberOfCoffees,
                    //      calculatePrice(numberOfCoffees,whippedCream,chocolate)));

         }
    /**
     * This method displays the given text on the screen.
     */
    private String displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary);
        orderSummaryTextView.setText(message);
        return message;
    }

    protected String createOrderSummary(String person,boolean whippedCream,boolean chocolate,int numberOfCoffees,int totalAmount){
        String summary = "Name: "+person +"\n Add Whipped Cream? "+whippedCream +
                "\n Add Chocolate? "+chocolate +"\n Quantity : " +numberOfCoffees+ "\n Total : " + totalAmount
                + "\n Thank You!";
        return summary;
    }

    /**
           * This method displays the given quantity value on the screen.
           */
                 private void display(int number) {
                 TextView quantityTextView = (TextView) findViewById(
                R.id.quantity);
                 quantityTextView.setText("" + number);
             }

         }
