package com.example.praktikum6.adapter

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum6.DetailActivity
import com.example.praktikum6.databinding.ItemBarangBinding
import com.example.praktikum6.datasource.Barang


class BarangAdapter(
    private val listBarang: List<Barang>
):
    RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {
    class BarangViewHolder (val binding: ItemBarangBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(itemBarang : Barang){
            binding.apply{
                tvNama.text = itemBarang.nama.toString()
                tvJenis.text = itemBarang.jenis.toString()
                tvHarga.text = itemBarang.harga.toString()

                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        DetailActivity::class.java
                    )
                    intent.putExtra("barang_id", itemBarang.id)
                    it.context.startActivity(intent)
                }
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val binding = ItemBarangBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BarangViewHolder(binding)
    }

    override fun getItemCount(): Int = listBarang.size

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val itemBarang = listBarang[position]
        holder.bind(itemBarang)
    }
}
