package com.cs207.ParkKing.Park;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import layout.GPlusFragment;



public class Login extends AppCompatActivity {
    GPlusFragment gf = new GPlusFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new GPlusFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();

        }
    }
}
