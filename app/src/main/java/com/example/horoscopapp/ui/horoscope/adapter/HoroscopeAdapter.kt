package com.example.horoscopapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopapp.R
import com.example.horoscopapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(private var horoscope: List<HoroscopeInfo> = emptyList(), private val onItemSelected: (HoroscopeInfo) -> Unit): RecyclerView.Adapter<HoroscopeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope,parent,false)
        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return horoscope.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscope[position], onItemSelected)
    }

    fun updateList(list: List<HoroscopeInfo>){
        horoscope = list
        notifyDataSetChanged()
    }
}