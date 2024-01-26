package com.example.horoscopapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscopapp.R
import com.example.horoscopapp.databinding.ActivityDetailBinding
import com.example.horoscopapp.domain.model.HoroscopeType.Aquarius
import com.example.horoscopapp.domain.model.HoroscopeType.Aries
import com.example.horoscopapp.domain.model.HoroscopeType.Cancer
import com.example.horoscopapp.domain.model.HoroscopeType.Capricorn
import com.example.horoscopapp.domain.model.HoroscopeType.Gemini
import com.example.horoscopapp.domain.model.HoroscopeType.Leo
import com.example.horoscopapp.domain.model.HoroscopeType.Libra
import com.example.horoscopapp.domain.model.HoroscopeType.Pisces
import com.example.horoscopapp.domain.model.HoroscopeType.Sagittarius
import com.example.horoscopapp.domain.model.HoroscopeType.Scorpio
import com.example.horoscopapp.domain.model.HoroscopeType.Taurus
import com.example.horoscopapp.ui.detail.DetailState.Error
import com.example.horoscopapp.ui.detail.DetailState.Loading
import com.example.horoscopapp.ui.detail.DetailState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        detailViewModel.getDetail(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
        binding.btnBackDetail.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect {
                    when (it) {
                        Loading -> onLoadingState()
                        is Error -> onErrorState(it)
                        is Success -> onSuccessState(it)
                    }
                }
            }
        }
    }

    private fun onSuccessState(detailState: Success) {
        binding.pbDetail.isVisible = false
        binding.tvDetailSign.text = detailState.sign
        binding.tvPrediction.text = detailState.data
        val drawable = when (detailState.type) {
            Aries -> R.drawable.detail_aries
            Taurus -> R.drawable.detail_taurus
            Cancer -> R.drawable.detail_cancer
            Pisces -> R.drawable.detail_pisces
            Gemini -> R.drawable.detail_gemini
            Libra -> R.drawable.detail_libra
            Scorpio -> R.drawable.detail_scorpio
            Leo -> R.drawable.detail_leo
            Capricorn -> R.drawable.detail_capricorn
            Sagittarius -> R.drawable.detail_sagittarius
            Aquarius -> R.drawable.detail_aquarius
        }
        binding.ivDetail.setImageResource(drawable)
    }

    private fun onErrorState(detailState: Error) {
        binding.pbDetail.isVisible = false

    }

    private fun onLoadingState() {
        binding.pbDetail.isVisible = true
    }
}