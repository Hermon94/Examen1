package com.example.hermon.exam1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Exam1 extends AppCompatActivity {

    public  static final String PREFS_NAME = "MyPreferencesFile";
    public  static final String PREFS_NAMES = "MyPreferencesFile2";
    public void convertMoney (View view) {
        int  dolar = 13;
        double euro = 1.12;
        double euromex = 20;
        EditText dinero_a_convertir = (EditText) findViewById(R.id.dinero);
        String strUserName = dinero_a_convertir.getText().toString();
        TextView from = (TextView) findViewById(R.id.Ingreso);
        TextView to = (TextView) findViewById(R.id.Salida);
        String from_text = from.getText().toString();
        String to_text = to.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            dinero_a_convertir.setError("It cannot be empty");
            return;
        }
        if(!dinero_a_convertir.getText().toString().matches("[0-9]+")){
            dinero_a_convertir.setError("It can only be numbers");
            return;
        }
        double dinero = Double.parseDouble(dinero_a_convertir.getText().toString());
        if(from_text.equals("Pesos (Mxn)") && to_text.equals("Dollars")){
            dinero = dinero / dolar;
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi=df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Dollars", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Pesos (Mxn)") && to_text.equals("Euros")){
            dinero = dinero / euromex;
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi=df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Euros", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Pesos (Mxn)") && to_text.equals("Pesos (Mxn)")){
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi=df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Pesos (Mxn)", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Dollars") && to_text.equals("Pesos (Mxn)")){
            dinero = dinero * dolar;
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi= df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Pesos (Mxn)", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Dollars") && to_text.equals("Euros")){
            dinero = dinero / euro;
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi= df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Euros", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Dollars") && to_text.equals("Dollars")){
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi=df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Dollars", Toast.LENGTH_LONG).show();
        }



        if(from_text.equals("Euros") && to_text.equals("Pesos (Mxn)")){
            dinero = dinero * euromex;
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi= df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Pesos (Mxn)", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Euros") && to_text.equals("Dollars")){
            dinero = dinero * euro;
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi=df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Dollars", Toast.LENGTH_LONG).show();
        }
        if(from_text.equals("Euros") && to_text.equals("Euros")){
            DecimalFormat df = new DecimalFormat("0.00");
            String sPi= df.format(dinero);
            Toast.makeText(getApplicationContext(), sPi+" Euros", Toast.LENGTH_LONG).show();
        }
    }

    public void sendToPreferences (View view){
        Intent intent = new Intent(this, Preferencias.class);
        startActivity(intent);
        onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);
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
        TextView from = (TextView) findViewById(R.id.Ingreso);
        TextView to = (TextView) findViewById(R.id.Salida);
        SharedPreferences ret = getSharedPreferences(PREFS_NAMES,0);
        String from_text_ret = ret.getString("from_text_ret", "NotFound");
        String to_text_ret = ret.getString("to_text_ret","NotFound");
        from.setText(from_text_ret);
        to.setText(to_text_ret);

    }
    @Override
    protected void onStop(){
        super.onStop();
        final TextView from = (TextView) findViewById(R.id.Ingreso);
        final TextView to = (TextView) findViewById(R.id.Salida);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("from_text", from.getText().toString());
        editor.putString("to_text", to.getText().toString());
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exam1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
