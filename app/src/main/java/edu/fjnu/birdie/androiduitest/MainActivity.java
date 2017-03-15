package edu.fjnu.birdie.androiduitest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //AlertDialog
    public void alertDialogIntent(View source){
        //Intent intent = new Intent(MainActivity.this, AlertDialogActivity.class);
       //startActivity(intent);
        LinearLayout ad = (LinearLayout)getLayoutInflater().inflate(R.layout.dialog_alert_dialog_test ,null);
        new AlertDialog.Builder(this)
                .setView(ad)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Login Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }
    //XML Menu
    public void XMLMenuIntent(View source){
        Intent intent = new Intent(MainActivity.this, XMLMenuActivity.class);
        startActivity(intent);
    }
    //Action Mode
    public void actionModeIntent(View source){
        Intent intent = new Intent(MainActivity.this, ActionModeActivity.class);
        startActivity(intent);
    }
}
