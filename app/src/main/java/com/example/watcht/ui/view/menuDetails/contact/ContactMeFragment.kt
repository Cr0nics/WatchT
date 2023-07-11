package com.example.watcht.ui.view.menuDetails.contact

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watcht.R

class ContactMeFragment : Fragment() {

    companion object {
        fun newInstance() = ContactMeFragment()
    }

    private lateinit var viewModel: ContactMeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_me, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactMeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}