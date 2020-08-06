package com.arbems.viewmodelsharedatabetweenfragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels() // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedObserver = Observer<Boolean> { selected ->
            textResult.text = selected?.toString()
        }
        viewModel.selected.observe(viewLifecycleOwner, selectedObserver)
    }
}