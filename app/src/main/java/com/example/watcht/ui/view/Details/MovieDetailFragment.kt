package com.example.watcht.ui.view.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import coil.load
import coil.size.Scale
import com.example.watcht.R
import com.example.watcht.databinding.FragmentMovieDetailBinding
import com.example.watcht.utils.utils.POSTER_BASE_URL
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()


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
        val type: String = (requireArguments().getString("type")).toString()

        viewModel.getDetailsUseCase(id.toInt())
        binding.apply {
            prgBarMovies.visibility = View.VISIBLE
            viewModel.movieDetailState.observe(viewLifecycleOwner) { detailState ->

                when (detailState) {

                    is DetailState.LoadingMovieDetails -> {

                        prgBarMovies.visibility = View.VISIBLE

                    }
                    is DetailState.SuccessMovieDetails -> {
                        prgBarMovies.visibility = View.GONE

                        val itBody = detailState.response

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

                        if (type == "saved") {
                            btnSaveOrDelete.setOnClickListener {
                                Toast.makeText(
                                    requireContext(),
                                    "Saved",
                                    Toast.LENGTH_SHORT
                                ).show()
                                viewModel.saveMovie(itBody)
                            }
                            btnSaveOrDelete.setImageResource(R.drawable.ic_add)
                        } else {
                            btnSaveOrDelete.setImageResource(R.drawable.ic_delete)
                            btnSaveOrDelete.setOnClickListener {
                                Toast.makeText(
                                    requireContext(),
                                    "DELETE",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                    }
                    is DetailState.ErrorMovieDetails -> {

                        prgBarMovies.visibility = View.GONE
                        Toast.makeText(context, "Error loading movie details", Toast.LENGTH_SHORT)
                            .show()

                    }


                }


            }

        }
    }


    companion object {
        fun newInstance() = MovieDetailFragment()
    }


}