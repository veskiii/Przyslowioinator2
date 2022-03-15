package com.example.przyslowioinator2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.przyslowioinator2.databinding.FragmentPrzyslowiaBinding
import com.example.przyslowioinator2.fragments.PrzyslowiaFragmentDirections
import com.example.przyslowioinator2.model.Przyslowie

class PrzyslowieRecyclerViewAdapter: RecyclerView.Adapter<PrzyslowieRecyclerViewAdapter.ViewHolder>() {

    private var values = emptyList<Przyslowie>()

    inner class ViewHolder(binding: FragmentPrzyslowiaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPrzyslowiaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.itemView.setOnClickListener {
            val action = PrzyslowiaFragmentDirections.actionPrzyslowiaFragmentToDetailFragment(item.id)
            it.findNavController().navigate(action)
        }

        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    fun setData(newList: List<Przyslowie>) {
        values = newList
        notifyDataSetChanged()
    }

}