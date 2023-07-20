package com.example.watcht.ui.view.PopularMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcht.R
import com.example.watcht.data.model.PopularMovieListResponse
import com.example.watcht.databinding.FragmentMovieListBinding
import com.example.watcht.databinding.PaginationLoadBinding
import com.example.watcht.ui.view.PopularMovies.adapter.LoadAdapter
import com.example.watcht.ui.view.PopularMovies.adapter.PopularMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieList.collect {
                    moviesAdapter.submitData(it)
                }
            }
        }
        moviesAdapter.setOnClickItemListener { navigateToDetail(it) }
        binding.recViewPopularMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.recViewPopularMovies.adapter = moviesAdapter


        lifecycleScope.launchWhenCreated {
            moviesAdapter.loadStateFlow.collect {
                val state = it.refresh
                binding.progressBar.isVisible = state is LoadState.Loading
            }
        }
        binding.recViewPopularMovies.adapter = moviesAdapter.withLoadStateFooter(

            LoadAdapter {

                moviesAdapter.retry()

            }

        )


    }

    fun navigateToDetail(item: PopularMovieListResponse.Result) {
        val bundle = Bundle().apply {
            putString("id", item.id.toString())
            putString("type", "saved")
        }
        val navController = Navigation.findNavController(requireView())

        navController.navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)
    }


    companion object {
        fun newInstance() = MovieListFragment()
    }


}