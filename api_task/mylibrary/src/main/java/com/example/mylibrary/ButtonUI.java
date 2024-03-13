package com.example.mylibrary;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class ButtonUI extends androidx.appcompat.widget.AppCompatButton {
    String title = "";

    public ButtonUI(Context context) {
        super(context);
        initialise(context, null);
    }

    private void initialise(Context context, AttributeSet attrs) {
        createUIDesign(context, attrs);
    }


    public void createUIDesign(Context context, AttributeSet attrs) {
        TypedArray data = getContext().obtainStyledAttributes(attrs,R.styleable.ButtonUI);
        //Create params for views---------------
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //Create a layout---------------
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Button button = new Button(context);
        button.setText(data.getString(0));
        button.setTextColor(Integer.parseInt("#FFFFFF"));
        button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        button.setLayoutParams(params);
        linearLayout.addView(button);
    }
}
