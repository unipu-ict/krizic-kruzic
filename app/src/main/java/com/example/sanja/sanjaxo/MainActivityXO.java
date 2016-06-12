package com.example.sanja.sanjaxo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivityXO extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_xo);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add("Nova igra");
        return true;
    }

    public void protiv_androida(View view){

        Intent getNameScreenIntent = new Intent(this, PlayerVsAndroid.class);
        startActivity(getNameScreenIntent);

    }

}


