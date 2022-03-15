package com.example.przyslowioinator2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.przyslowioinator2.R
import com.example.przyslowioinator2.adapter.PrzyslowieRecyclerViewAdapter
import com.example.przyslowioinator2.repository.PrzyslowiaRepository
import com.example.przyslowioinator2.viewmodel.PrzyslowiaViewModel
import com.example.przyslowioinator2.viewmodel.factory.PrzyslowiaViewModelFactory

/**
 * A fragment representing a list of Przyslowia.
 */
class PrzyslowiaFragment : Fragment() {

    private lateinit var viewModel: PrzyslowiaViewModel
    private val przyslowiaAdapter by lazy { PrzyslowieRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = PrzyslowiaRepository()
        val viewModelFactory = PrzyslowiaViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[PrzyslowiaViewModel::class.java]

        viewModel.przyslowia.observe(this, { response ->
            if(response.isSuccessful ) {
                    response.body()?.let { przyslowiaAdapter.setData(it) }
            } else {
                Toast.makeText(context, "Operacja nieudana.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_przyslowia_list, container, false)

        setupRecyclerView(view)

        viewModel.getPrzyslowia()

        return view
    }

    private fun setupRecyclerView(view: View) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager =  LinearLayoutManager(context)
                adapter = przyslowiaAdapter
            }
        }
    }
}