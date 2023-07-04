package com.example.watcht.ui.view.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watcht.R
import com.example.watcht.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


   private lateinit var binding : FragmentMovieDetailBinding
  //  private lateinit var viewModel: MovieDetailViewModel
  //  @Inject
   // private lateinit var repository: MoviesRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMovieDetailBinding.inflate(layoutInflater,container,false)
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)


    }

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

}