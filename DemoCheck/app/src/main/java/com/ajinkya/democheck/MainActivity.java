package com.ajinkya.democheck;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        int versionAPI = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;
        if(versionAPI<=Build.VERSION_CODES.P){
            AlertDialog.Builder alertDialogWarning = new AlertDialog.Builder(this);
            alertDialogWarning.setMessage("Warning");
            alertDialogWarning.setTitle("You need to upgrade the Device.!!");
            alertDialogWarning.setCancelable(false);
            alertDialogWarning.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close
                finish();
            });

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            alertDialogWarning.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                // If user click no then dialog box is canceled.
                dialog.cancel();
            });
            alertDialogWarning.show();
        }else{
            textView.setText("API Version :" + versionAPI + " \n" + "Version Release : " + versionRelease);
        }

    }
}