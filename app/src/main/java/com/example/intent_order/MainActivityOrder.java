package com.example.intent_order;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityOrder extends AppCompatActivity {

    private RadioGroup radioGroupDrink;
    private RadioButton radioButtonDrink1,radioButtonDrink2,radioButtonDrink3;
    private int drinkFlag;
    private int drinkPrice;
    private CheckBox checkBoxWaffle,checkBoxPancake,checkBoxMuffin;
    private EditText editTextWaffle,editTextPancake,editTextMuffin;
    private Button buttonCancel,buttonCheckout;
    private TextView textViewOrder;
    private Button buttonDrink1,buttonDrink2;
    private int drinkButtonNumber;
    private NumberPicker numberPickerNo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_order);

        //設定開頭標題
        setTitle("Order");


        radioGroupDrink = (RadioGroup) findViewById(R.id.radioGroup_drink);
        radioButtonDrink1 = (RadioButton) findViewById(R.id.radioButton_drink1);
        radioButtonDrink2 = (RadioButton) findViewById(R.id.radioButton_drink2);
        radioButtonDrink3 = (RadioButton) findViewById(R.id.radioButton_drink3);

        drinkFlag=0;
        drinkPrice=0;
        radioGroupDrink.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_drink1:
                        drinkFlag=1;
                        if(drinkButtonNumber==1)
                            drinkPrice=100;
                        else
                            drinkPrice=120;
                        break;

                    case R.id.radioButton_drink2:
                        drinkFlag=2;
                        if(drinkButtonNumber==1)
                            drinkPrice=80;
                        else
                            drinkPrice=120;
                        break;

                    case R.id.radioButton_drink3:
                        drinkFlag=3;
                        if(drinkButtonNumber==1)
                            drinkPrice=50;
                        else
                            drinkPrice=120;
                        break;
                }
            }
        });

        buttonDrink1 = (Button)findViewById(R.id.button_drink1);
        buttonDrink2 = (Button)findViewById(R.id.button_drink2);
        buttonDrink1.setOnClickListener(new MyDrink());
        buttonDrink2.setOnClickListener(new MyDrink());

        checkBoxWaffle = (CheckBox) findViewById(R.id.checkBox_waffle);
        checkBoxPancake = (CheckBox) findViewById(R.id.checkBox_pancake);
        checkBoxMuffin = (CheckBox) findViewById(R.id.checkBox_muffin);

        editTextWaffle = (EditText) findViewById(R.id.editText_waffle);
        editTextPancake= (EditText) findViewById(R.id.editText_pancake);
        editTextMuffin = (EditText) findViewById(R.id.editText_muffin);

        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonCheckout = (Button) findViewById(R.id.button_checkout);
        textViewOrder = (TextView) findViewById(R.id.textView_order);
        textViewOrder.setText("");

        buttonCancel.setOnClickListener(new MyButton());
        buttonCheckout.setOnClickListener(new MyButton());

        numberPickerNo = (NumberPicker)findViewById(R.id.NumberPicker_no);
        numberPickerNo.setMinValue(1);
        numberPickerNo.setMaxValue(10);
        numberPickerNo.setValue(1);
        numberPickerNo.setWrapSelectorWheel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            numberPickerNo.setTextSize(60);
            numberPickerNo.setTextColor(0xff00aa00);
        }

    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_cancel:
                    radioGroupDrink.clearCheck();
                    drinkFlag=0;
                    drinkPrice=0;
                    checkBoxMuffin.setChecked(false);
                    checkBoxPancake.setChecked(false);
                    checkBoxWaffle.setChecked(false);
                    textViewOrder.setText("");
                    editTextMuffin.setText("");
                    editTextPancake.setText("");
                    editTextWaffle.setText("");
                    numberPickerNo.setValue(1);
                    break;

                case R.id.button_checkout:
                    textViewOrder.setText("Your order is : \n");
                    int sum = 0;
                    int drinkQty = numberPickerNo.getValue();
                    if(drinkFlag==0){
                        textViewOrder.append("Please order drink. \n");
                    }else if(drinkFlag==1){
                        textViewOrder.append("Drink: "+radioButtonDrink1.getText()+" x "+drinkQty+"\n");
                        sum +=drinkPrice*drinkQty;
                    }else if(drinkFlag==2){
                        textViewOrder.append("Drink: "+radioButtonDrink2.getText()+" x "+drinkQty+"\n");
                        sum +=drinkPrice*drinkQty;
                    }else if(drinkFlag==3){
                        textViewOrder.append("Drink: "+radioButtonDrink2.getText()+" x "+drinkQty+"\n");
                        sum+=drinkPrice*drinkQty;
                    }
                    textViewOrder.append("Dessert: \n");
                    if(checkBoxWaffle.isChecked()){
                        String number = editTextWaffle.getText().toString();
                        if(number.length()!=0) {
                            textViewOrder.append("Waffle, $100 x" + number + " , ");
                            sum += 100 * Integer.parseInt(number);
                        }
                    }
                    if(checkBoxPancake.isChecked()){
                        String number = editTextPancake.getText().toString();
                        if(number.length()!=0){
                            textViewOrder.append("Pancake, $120 x "+number+" , ");
                            sum+=120 * Integer.parseInt(number);
                        }
                    }
                    if(checkBoxMuffin.isChecked()){
                        String number = editTextMuffin.getText().toString();
                        if(number.length()!=0){
                            textViewOrder.append("Muffin, $180 x "+number);
                            sum+=80 *Integer.parseInt(number);
                        }
                    }

                    textViewOrder.append("\nThe total fee is : $"+sum);
                    break;
            }
        }
    }

    private class MyDrink implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            radioGroupDrink.clearCheck();
            switch (v.getId()){
                case R.id.button_drink1:
                    drinkButtonNumber = 1;
                    radioButtonDrink1.setText("Coffee,$100");
                    radioButtonDrink1.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.latte,0);

                    radioButtonDrink2.setText("Tea,$80");
                    radioButtonDrink2.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.blacktea,0);

                    radioButtonDrink3.setText("Cola,$50");
                    radioButtonDrink3.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.cola_1,0);
                    break;

                case R.id.button_drink2:
                    drinkButtonNumber=2;
                    radioButtonDrink1.setText("Wisky, $120");
                    radioButtonDrink1.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.wisky,0);

                    radioButtonDrink2.setText("Jin, $120");
                    radioButtonDrink2.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.jin,0);

                    radioButtonDrink3.setText("Starbucks, $120");
                    radioButtonDrink3.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.starbucks,0);
                    break;
            }
        }
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}