package com.example.praktikum6

import android.R
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.praktikum6.adapter.BarangAdapter
import com.example.praktikum6.databinding.ActivityMainBinding
import com.example.praktikum6.datasource.Barang
import com.example.praktikum6.datasource.BarangDao
import com.example.praktikum6.datasource.DatabaseBarang
import com.example.praktikum6.helper.AppExecutor
import com.google.android.gms.maps.SupportMapFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseBarang
    private lateinit var barangDao: BarangDao
    private lateinit var appExecutor: AppExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"Ini ada di onCreate")


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseBarang.getDatabase(this)
        barangDao = db.barangDao()
        appExecutor = AppExecutor()

        binding.apply {
            fabAdd.setOnClickListener {
                appExecutor.diskIO.execute {

                    insertDummyData()
                }

                val listBarang: LiveData<List<Barang>> = barangDao.getAllBarang()
                listBarang.observe(this@MainActivity, Observer { list ->


                    lvRoomDb.layoutManager = GridLayoutManager(
                        this@MainActivity,
                        2
                    )
                    lvRoomDb.adapter = BarangAdapter(list)
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Ini ada di Menu Awal")
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
        Log.d(TAG, "Ini ada di onDestroy")
    }



    private fun insertDummyData() {

        val listNamaBarang = listOf<String>(
            "Plastik", "Kardus", "Limbah RT", "Kotoran Hewan"
        )

        val listJenisBarang = listOf<String>(
            "Anorganik", "Anorganik", "Organik", "Organik"
        )

        val listHargaBarang = listOf<Int>(
            850000, 200000, 5000000, 6000000
        )

        val listStatusBarang = listOf<Int>(
            0, 1, 0, 1
        )

        for(i in 0 .. 3) {
            val newBarang = Barang(
                nama = listNamaBarang[i],
                jenis = listJenisBarang[i],
                harga = listHargaBarang[i],
                status = listStatusBarang[i]
            )

            barangDao.insert(newBarang)

        }
    }
}