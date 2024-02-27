package com.example.praktikum6.datasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Barang(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "nama")
    var nama: String? = null,
    @ColumnInfo(name = "jenis")
    var jenis: String? = null,
    @ColumnInfo(name = "harga")
    var harga: Int? = null,
    @ColumnInfo(name = "status")
    var status: Int = 0
)