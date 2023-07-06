package com.example.watcht.ui.view.PopularMovies

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcht.R
import com.example.watcht.core.modelResponse.PopularMovieListResponse
import com.example.watcht.data.ApiRepository
import com.example.watcht.databinding.FragmentMovieListBinding
import com.example.watcht.ui.view.Details.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

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
        viewModel.getMovieList(1)


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

    }


    private fun navigateToDetail(item: PopularMovieListResponse.Result) {

        val bundle = Bundle()
        bundle.putString("id", item.id.toString())
        val movieDetailFragment = MovieDetailFragment()
        movieDetailFragment.arguments = bundle
        findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment, bundle)

    }

    companion object {
        fun newInstance() = MovieListFragment()
    }


}