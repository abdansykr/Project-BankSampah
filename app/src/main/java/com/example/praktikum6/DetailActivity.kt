package com.example.praktikum6

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.praktikum6.databinding.ActivityDetailBinding
import com.example.praktikum6.datasource.DatabaseBarang
import com.example.praktikum6.helper.AppExecutor

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var appExecutors: AppExecutor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(ContentValues.TAG,"Ini ada di Menu Detail")
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appExecutors = AppExecutor()
        val barangId = intent.getIntExtra("barang_id", -1)
        if (barangId != -1) {
            appExecutors.diskIO.execute {
                val dao = DatabaseBarang.getDatabase(this@DetailActivity).barangDao()
                val selectedBarang = dao.getBarangById(barangId)
                binding.apply {
                    etNama.setText(selectedBarang.nama)
                    etJenis.setText(selectedBarang.jenis)
                    etharga.setText(selectedBarang.harga.toString())
                    btnUpdate.setOnClickListener {
                        val updatedBarang = selectedBarang.copy(
                            nama = etNama.text.toString(),
                            jenis = etJenis.text.toString(),
                            harga = etharga.text.toString().toInt()
                        )
                        appExecutors.diskIO.execute {
                            dao.update(updatedBarang)
                            // Lakukan tindakan update lainnya jika diperlukan
                        }
                    }
                    btnDelete.setOnClickListener {
                        appExecutors.diskIO.execute {
                            dao.delete(selectedBarang)
                            // Lakukan tindakan delete lainnya jika diperlukan
                            finish() // Kembali ke MainActivity setelah menghapus
                        }
                    }
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        Log.d(ContentValues.TAG,"Ini ada di Menu Lanjutan")
    }
    override fun onPause() {
        super.onPause()
        Log.d(ContentValues.TAG,"Ini ada di onJeda")
    }
    override fun onStop() {
        super.onStop()
        Log.d(ContentValues.TAG,"Ini ada di onBerhenti")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(ContentValues.TAG,"Ini ada di onMati")
    }
}