package com.example.przyslowioinator2.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.przyslowioinator2.databinding.FragmentRandomPrzyslowieBinding
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import com.example.przyslowioinator2.viewmodel.RandomPrzyslowieViewModel
import com.example.przyslowioinator2.viewmodel.factory.RandomPrzyslowieViewModelFactory

class RandomPrzyslowieFragment : Fragment() {

    private lateinit var binding: FragmentRandomPrzyslowieBinding
    private lateinit var viewModel: RandomPrzyslowieViewModel

    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PrzyslowiaRepository()
        val viewModelFactory = RandomPrzyslowieViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[RandomPrzyslowieViewModel::class.java]

        viewModel.randomPrzyslowie.observe(this, { response ->
            if(response.isSuccessful) {
                binding.przyslowieTextView.text = response.body()?.content
                moveLogo()
                crossfadeInTextView()
            } else {
                Toast.makeText(context, "Operacja nieudana.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomPrzyslowieBinding.inflate(inflater, container, false)

        setupTextViewAnimation()

        binding.drawPrzyslowieButton.setOnClickListener {
            viewModel.getRandomPrzyslowie()
        }

        return binding.root
    }

    private fun setupTextViewAnimation() {
        binding.przyslowieTextView.visibility = View.GONE

        shortAnimationDuration = resources.getInteger(android.R.integer.config_mediumAnimTime)
    }

    private fun crossfadeInTextView() {
        binding.przyslowieTextView.apply {
            alpha = 0f
            visibility = View.VISIBLE

            animate()
                .alpha(1f)
                .duration = shortAnimationDuration.toLong()
        }
    }

    private fun moveLogo() {
        ObjectAnimator.ofFloat(binding.imageView, "translationY" ,-300f).apply {
            duration = 500
            start()
        }
    }

}