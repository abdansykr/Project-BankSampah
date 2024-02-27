package com.example.praktikum6

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log


class MainMenu : AppCompatActivity() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    fun openMenuPertama(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun openMenuKedua(view: View) {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Ini ada di onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Ini ada di onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Ini ada di onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Ini ada di onDestroy")
    }

}