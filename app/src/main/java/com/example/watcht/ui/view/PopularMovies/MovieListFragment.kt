package com.example.watcht.ui.view.PopularMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private lateinit var viewModel: MovieListViewModel

    @Inject
    lateinit var repository: ApiRepository

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
        binding.apply {

            progressBar.visibility = View.VISIBLE
            repository.getPopularMovies(1).enqueue(object :
                retrofit2.Callback<PopularMovieListResponse> {
                override fun onResponse(
                    call: Call<PopularMovieListResponse>,
                    response: Response<PopularMovieListResponse>
                ) {
                    progressBar.visibility = View.GONE
                    when (response.code()) {
                        200 -> {
                            moviesAdapter.setOnClickItemListener {
                                navigateToDetail(it)
                            }
                            response.body().let { itBody ->
                                if (itBody?.results!!.isNotEmpty()) {
                                    moviesAdapter.differ.submitList(itBody.results)
                                }
                                recViewPopularMovies.apply {
                                    layoutManager = LinearLayoutManager(requireContext())
                                    adapter = moviesAdapter
                                }

                            }
                        }
                        400 -> {
                            Toast.makeText(requireContext(), "asd", Toast.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE

                        }
                        401 -> {
                            Toast.makeText(requireContext(), "asd", Toast.LENGTH_SHORT).show()
                            progressBar.visibility = View.GONE

                        }
                    }
                }

                override fun onFailure(call: Call<PopularMovieListResponse>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "OnFailure", Toast.LENGTH_SHORT).show()

                }

            })
        }


    }
    private fun navigateToDetail(item: PopularMovieListResponse.Result){

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