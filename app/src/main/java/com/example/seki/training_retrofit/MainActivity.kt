package com.example.seki.training_retrofit;

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = WeatherFragment.getInstance()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }
}
