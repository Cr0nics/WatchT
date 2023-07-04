package com.example.watcht.ui.view.Details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import coil.load
import coil.size.Scale
import com.example.watcht.R
import com.example.watcht.core.modelResponse.PopularMovieListResponse
import com.example.watcht.data.ApiRepository
import com.example.watcht.data.modelResponse.movieDetails.MovieDetails
import com.example.watcht.databinding.FragmentMovieDetailBinding
import com.example.watcht.databinding.FragmentMovieListBinding
import com.example.watcht.utils.utils.POSTER_BASE_URL
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var repository: ApiRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: String = (requireArguments().getString("id")).toString()
        binding.prgBarMovies.visibility = View.GONE
        binding.imgMovie.setOnClickListener {Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
        }

        binding.apply {
            prgBarMovies.visibility = View.VISIBLE
            repository.getMovieDetail(id = id.toInt()).enqueue(object: Callback<MovieDetails>{
                override fun onResponse(
                    call: Call<MovieDetails>,
                    response: Response<MovieDetails>
                ) {
                    when(response.code()){
                        200 -> {
                            prgBarMovies.visibility = View.GONE
                            response.body()?.let { itBody ->
                                val moviePosterURL = POSTER_BASE_URL + itBody.poster_path
                                imgMovie.load(moviePosterURL) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)
                                    scale(Scale.FILL)
                                }
                                imgMovieBack.load(moviePosterURL) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)
                                    scale(Scale.FILL)
                                }
                                tvMovieTitle.text = itBody.title
                                tvMovieTagLine.text = itBody.tagline
                                tvMovieDateRelease.text = itBody.release_date
                                tvMovieRating.text = itBody.vote_average.toString()
                                tvMovieRuntime.text = itBody.runtime.toString()
                                tvMovieBudget.text = itBody.budget.toString()
                                tvMovieRevenue.text = itBody.revenue.toString()
                                tvMovieOverview.text = itBody.overview
                            }
                        }
                        401 -> {Toast.makeText(requireContext(), "401", Toast.LENGTH_SHORT).show()}
                        404 -> {Toast.makeText(requireContext(), "404", Toast.LENGTH_SHORT).show()}
                    }
                }

                override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                        prgBarMovies.visibility = View.GONE
                        Toast.makeText(requireContext(), "OnFailure", Toast.LENGTH_SHORT).show()
                }

            })








        }



    }

    fun show(id: String?) {
        lateinit var x: String

        if (id.isNullOrEmpty()) {
            x = ("showdown")
        } else {
            x = ("showup $id")
        }

        Toast.makeText(requireContext(), x, Toast.LENGTH_SHORT).show()
    }


    companion object {
        fun newInstance() = MovieDetailFragment()
    }

}