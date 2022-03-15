package com.example.przyslowioinator2.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.przyslowioinator2.databinding.FragmentDetailBinding
import com.example.przyslowioinator2.model.Przyslowie
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import com.example.przyslowioinator2.utils.addToClipboard
import com.example.przyslowioinator2.viewmodel.DetailViewModel
import com.example.przyslowioinator2.viewmodel.factory.DetailViewModelFactory

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PrzyslowiaRepository()
        val viewModelFactory = DetailViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        viewModel.przyslowie.observe(this, { response ->
            if(response.isSuccessful ) {
                response.body()?.let {
                    setPrzyslowieToViews(it)
                }
            } else {
                Toast.makeText(context, "Operacja nieudana.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : DetailFragmentArgs? = arguments?.let { DetailFragmentArgs.fromBundle(it) }

        args?.przyslowieId?.let { viewModel.getPrzyslowie(it) }
    }

    private fun setPrzyslowieToViews(przyslowie: Przyslowie) {
        binding.contentTextView.text = przyslowie.content
        setupButtons(przyslowie)
    }

    private fun setupButtons(przyslowie: Przyslowie) {
        setupWiktionaryButton(przyslowie.wiktionary)
        setupAddToClipboardButton(przyslowie.content)
    }

    private fun setupWiktionaryButton(url: String) {
        binding.wiktionaryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    private fun setupAddToClipboardButton(przyslowieContent: String) {
        binding.clipboardButton.setOnClickListener{
            context?.let { it1 ->
                addToClipboard(it1, "Przyslowie", przyslowieContent)
            }
        }
    }
}