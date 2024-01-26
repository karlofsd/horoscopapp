package com.example.horoscopapp.ui.horoscope

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.horoscopapp.databinding.FragmentHoroscopeBinding
import com.example.horoscopapp.domain.model.HoroscopeInfo
import com.example.horoscopapp.domain.model.HoroscopeInfo.*
import com.example.horoscopapp.domain.model.HoroscopeType
import com.example.horoscopapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private var _binding: FragmentHoroscopeBinding? = null
    private lateinit var horoscopeAdapter: HoroscopeAdapter
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initUI(){
        initList()
        initUIState()
    }

    private fun initList(){
        horoscopeAdapter = HoroscopeAdapter{ navigateToDetail(it)}
        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    private fun initUIState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeViewModel.horoscope.collect{
                   horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    private fun navigateToDetail(horoscopeInfo: HoroscopeInfo){
        val type = when(horoscopeInfo){
            Aquarius -> HoroscopeType.Aquarius
            Aries -> HoroscopeType.Aries
            Cancer -> HoroscopeType.Cancer
            Capricorn -> HoroscopeType.Capricorn
            Gemini -> HoroscopeType.Gemini
            Leo -> HoroscopeType.Leo
            Libra -> HoroscopeType.Libra
            Pisces -> HoroscopeType.Pisces
            Sagittarius -> HoroscopeType.Sagittarius
            Scorpio -> HoroscopeType.Scorpio
            Taurus -> HoroscopeType.Taurus
        }
        findNavController().navigate(
            HoroscopeFragmentDirections.actionHoroscopeFragmentToDetailActivity(type)
        )
    }
}