package com.example.user.mylocationmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerFragment myListFragment= new RecyclerFragment();
        getFragmentManager().beginTransaction().add(R.id.activity_choose,myListFragment ).commit();
    }
}
