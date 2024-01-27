package com.example.horoscopapp.ui.luck

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.aristidevs.horoscapp.ui.providers.RandomCardProvider
import com.example.horoscopapp.R
import com.example.horoscopapp.databinding.FragmentLuckBinding
import com.example.horoscopapp.ui.core.listeners.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider: RandomCardProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    private fun preparePrediction() {
        val luck = randomCardProvider.getLucky()
        luck?.let {
            val currPrediction = getString(it.lucky)
            binding.ivLucky.setImageResource(it.image)
            binding.tvLucky.text = currPrediction
            binding.tvShare.setOnClickListener { shareLuck(currPrediction) }
        }
    }

    private fun shareLuck(prediction: String){
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, prediction)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.ivRuleta.setOnTouchListener(object : OnSwipeTouchListener(requireContext()){
            override fun onSwipeLeft() {
                spinRoulette()
            }
        })
    }

    private fun spinRoulette() {
        val random = Random()
        val degrees = random.nextInt(1440) + 360
        val animator =
            ObjectAnimator.ofFloat(binding.ivRuleta, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd { drawCard() }
        animator.start()
    }

    private fun drawCard (){
        val slideUpAnimator = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        slideUpAnimator.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                binding.backCard.isVisible = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                growthCard()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.backCard.startAnimation(slideUpAnimator)
    }

    private fun growthCard(){
        val growthAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.growth)
        growthAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.backCard.isVisible = false
                showPrediction()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.backCard.startAnimation(growthAnimation)
    }

    private fun showPrediction(){
        val disappearAnimation = AlphaAnimation(1.0f,0.0f)
        disappearAnimation.duration = 200
        val appearAnimation = AlphaAnimation(0.0f,1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true

            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }
}