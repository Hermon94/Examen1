package com.example.hermon.exam1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Preferencias extends AppCompatActivity {
    public  static final String PREFS_NAME = "MyPreferencesFile";
    public  static final String PREFS_NAMES = "MyPreferencesFile2";
    public void goBack (View view){
        Intent intent = new Intent(this, Exam1.class);
        startActivity(intent);
        onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SharedPreferences example = getSharedPreferences(PREFS_NAME,0);
        String from_text = example.getString("from_text", "NotFound");
        String to_text = example.getString("to_text","NotFound");

        Spinner monSpinner = (Spinner)findViewById(R.id.spinner_from);
        String[] money = getResources().getStringArray(R.array.money_array);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,money);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner.setAdapter(myAdapter);
        for(int i=0; i < myAdapter.getCount(); i++) {
            if(from_text.trim().equals(myAdapter.getItem(i).toString())){
                monSpinner.setSelection(i);
                break;
            }
        }
        Spinner monSpinner2 = (Spinner)findViewById(R.id.spinner_to);
        String[] money2 = getResources().getStringArray(R.array.money_array);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,money2);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpinner2.setAdapter(myAdapter2);
        for(int i=0; i < myAdapter2.getCount(); i++) {
            if(to_text.trim().equals(myAdapter2.getItem(i).toString())){
                monSpinner2.setSelection(i);
                break;
            }
        }
    }

    protected void onStop(){
        super.onStop();
        final Spinner monSpinner = (Spinner)findViewById(R.id.spinner_from);
        final String from_ret = monSpinner.getSelectedItem().toString();
        final Spinner monSpinner2 = (Spinner)findViewById(R.id.spinner_to);
        final String to_ret = monSpinner2.getSelectedItem().toString();
        SharedPreferences settings = getSharedPreferences(PREFS_NAMES,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("from_text_ret", from_ret);
        editor.putString("to_text_ret", to_ret);
        editor.commit();
    }

}
