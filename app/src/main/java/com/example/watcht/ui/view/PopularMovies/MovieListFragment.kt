package com.example.watcht.ui.view.PopularMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcht.R
import com.example.watcht.data.model.PopularMovieListResponse
import com.example.watcht.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {


    private lateinit var binding: FragmentMovieListBinding
    private val viewModel by viewModels<MovieListViewModel>()


    @Inject
    lateinit var moviesAdapter: PopularMoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
//        viewModel.getMovieList(1)
        lifecycleScope.launchWhenCreated {
            viewModel.movieList.collect {
                moviesAdapter.submitData(it)
            }
        }
        moviesAdapter.setOnClickItemListener { navigateToDetail(it) }
        binding.recViewPopularMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.recViewPopularMovies.adapter = moviesAdapter
/*
        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->

            when (dataState) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.Success -> {
                    binding.progressBar.visibility = View.GONE

                    moviesAdapter.setOnClickItemListener {
                        navigateToDetail(it)
                    }
                    moviesAdapter.differ.submitList(dataState.response.results)
                    binding.recViewPopularMovies.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = moviesAdapter
                    }
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "x", Toast.LENGTH_SHORT).show()
                }
            }

        }

*/

    }

    fun navigateToDetail(item: PopularMovieListResponse.Result) {
        val bundle = Bundle().apply {
            putString("id", item.id.toString())
            putString("type","saved")
        }
        val navController = Navigation.findNavController(requireView())

        navController.navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
    }


    companion object {
        fun newInstance() = MovieListFragment()
    }


}