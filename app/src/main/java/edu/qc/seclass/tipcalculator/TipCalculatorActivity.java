package edu.qc.seclass.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TipCalculatorActivity extends AppCompatActivity {

    double checkAmount;
    int partySize;

    // Declare identifiers
    EditText checkAmountInput;
    EditText partySizeInput;
    Button computeButton;

    EditText Tip15percent;
    EditText Tip20percent;
    EditText Tip25percent;

    EditText Total15percent;
    EditText Total20percent;
    EditText Total25percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize identifiers
        checkAmountInput = findViewById(R.id.checkAmountValue);
        partySizeInput = findViewById(R.id.partySizeValue);

        computeButton = findViewById(R.id.buttonCompute);

        // if the button is clicked, it will broadcast to its listeners
        computeButton.setOnClickListener(v -> {

            // closes the keyboard on the screen upon "Compute Tip" button press
            closeKeyboard();

            // displays a toast message if the check amount input is empty or invalid
            if(checkAmountInput.getText().toString().length() == 0){
                Toast.makeText(getApplicationContext(), "Empty or incorrect value(s)! for Check Amount", Toast.LENGTH_LONG).show();
            }

            // displays a toast message if the party size input is empty or invalid
            else if (partySizeInput.getText().toString().length() == 0){
                Toast.makeText(getApplicationContext(), "Empty or incorrect value(s)! for Party Size", Toast.LENGTH_LONG).show();
            }

            // displays a toast message if the party size input is 0
            else if(Integer.valueOf(partySizeInput.getText().toString()) == 0 ) {
                Toast.makeText(getApplicationContext(), "Please enter party size greater than 0", Toast.LENGTH_LONG).show();
            }

            // else compute Tips and Totals for 15%, 20%, 25%
            else{

                // initialization of all editText Id
                checkAmount = Double.valueOf(checkAmountInput.getText().toString());
                partySize = Integer.valueOf((partySizeInput.getText().toString()));

                Tip15percent = findViewById(R.id.fifteenPercentTipValue);
                Tip20percent = findViewById(R.id.twentyPercentTipValue);
                Tip25percent = findViewById(R.id.twentyfivePercentTipValue);

                Total15percent = findViewById(R.id.fifteenPercentTotalValue);
                Total20percent = findViewById(R.id.twentyPercentTotalValue);
                Total25percent = findViewById(R.id.twentyfivePercentTotalValue);

                // calls computeTip method 3 times to compute the tip for 15%, 20%, 25%
                Tip15percent.setText(String.valueOf(computeTip(checkAmount, partySize, 0.15)));
                Tip20percent.setText(String.valueOf(computeTip(checkAmount, partySize, 0.20)));
                Tip25percent.setText(String.valueOf(computeTip(checkAmount, partySize, 0.25)));

                // calls computeTotal method 3 times to compute the total for 15%, 20%, 25%
                Total15percent.setText(String.valueOf(computeTotal(checkAmount, partySize, 0.15)));
                Total20percent.setText(String.valueOf(computeTotal(checkAmount, partySize, 0.20)));
                Total25percent.setText(String.valueOf(computeTotal(checkAmount, partySize, 0.25)));

            }
        });
    }

    // computes the tip for a given check amount and party size
    public int computeTip(double checkAmount, int partySize, double percent){
        int tip = (int) Math.ceil((checkAmount / partySize) * percent);
        return  tip;
    }

    // computes the total with tip for given a check amount and party size
    public int computeTotal(double checkAmount, int partySize, double percent){
        double tip1 = ((checkAmount / partySize) * percent);
        int total = (int) Math.ceil((checkAmount / partySize) + tip1);
        return  total;
    }

    // closes the keyboard on the screen upon "Compute Tip" button press
    public void closeKeyboard(){
        View viewKey = this.getCurrentFocus();
        if(viewKey != null){
            InputMethodManager input = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(viewKey.getWindowToken(), 0);
        }
    }

}