package com.example.horoscopapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopapp.databinding.ItemHoroscopeBinding
import com.example.horoscopapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(info: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit){
        binding.tvHoroscope.text = binding.tvHoroscope.context.getString(info.name)
        binding.ivHoroscope.setImageResource(info.img)
        binding.itemHoroscope.setOnClickListener { startRotationAnimation(binding.ivHoroscope){ onItemSelected(info) } }
    }

    private fun startRotationAnimation(view: View, onSelect: () -> Unit){
        view.animate().apply {
            duration = 500
            interpolator = LinearInterpolator()
            rotation(360f)
            withEndAction { onSelect() }
            start()
        }
    }
}