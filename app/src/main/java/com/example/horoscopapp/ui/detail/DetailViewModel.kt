package com.example.horoscopapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopapp.domain.model.HoroscopeType
import com.example.horoscopapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase): ViewModel() {
    private var _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state

    fun getDetail(sign: HoroscopeType){
        viewModelScope.launch {
            _state.value = DetailState.Loading
            val result = withContext(Dispatchers.IO){ getPredictionUseCase(sign.name) }
            result.let {
                _state.value = DetailState.Success(it!!.horoscope, type = sign, sign = it.sign)
            } ?: run {
                _state.value = DetailState.Error("Result is Null")
            }
        }
    }
}