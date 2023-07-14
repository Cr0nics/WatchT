package com.example.watcht.ui.view.List

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watcht.databinding.FragmentListBinding
import com.example.watcht.ui.view.PopularMovies.PopularMoviesAdapter
import com.example.watcht.ui.view.menuDetails.settings.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<ListViewModel>()

    @Inject
    lateinit var moviesAdapter: MoviesListAdapter


    companion object {
        fun newInstance() = ListFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       viewModel.getMoviesFromDataBase()

        viewModel.dataSaved.observe(viewLifecycleOwner) { data ->
            moviesAdapter.differ.submitList(data)
            binding.recViewMovies.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = moviesAdapter
            }

        }


    }
}