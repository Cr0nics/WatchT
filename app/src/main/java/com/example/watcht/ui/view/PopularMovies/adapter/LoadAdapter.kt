package com.example.watcht.ui.view.PopularMovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.watcht.databinding.PaginationLoadBinding


class LoadAdapter(private val refresh: () -> Unit) : LoadStateAdapter<LoadAdapter.ViewHolder>() {

    private lateinit var binding: PaginationLoadBinding



    inner class ViewHolder(refresh: () -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadMoreRetry.setOnClickListener { refresh() }
        }

        fun setData(state: LoadState) {
            binding.apply {
                prgBarLoadMore.isVisible = state is LoadState.Loading
                tvLoadMore.isVisible = state is LoadState.Error
                btnLoadMoreRetry.isVisible = state is LoadState.Error
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        binding = PaginationLoadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(refresh)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }
}