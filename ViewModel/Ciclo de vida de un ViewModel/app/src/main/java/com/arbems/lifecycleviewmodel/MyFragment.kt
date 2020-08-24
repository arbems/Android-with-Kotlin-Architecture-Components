package com.arbems.lifecycleviewmodel

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.arbems.lifecycleviewmodel.databinding.FragmentMyBinding
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : Fragment() {

    private lateinit var viewModel: MyViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("Fragment", "onAttach!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            MyViewModelFactory("ViewModel Activity A")
        ).get(MyViewModel::class.java)

        Log.i("Fragment", "onCreate!")
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragment", "onCreateView!")

        val binding: FragmentMyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("Fragment", "onViewCreated!")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            viewModel.sum()
        }

        Log.i("Fragment", "onActivityCreated!")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.i("Fragment", "onDestroyView!")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("Fragment", "onDestroy!")
    }

    override fun onDetach() {
        super.onDetach()

        Log.i("Fragment", "onDetach!")
    }

    override fun onPause() {
        super.onPause()

        Log.i("Fragment", "onPause!")
    }

    override fun onResume() {
        super.onResume()

        Log.i("Fragment", "onResume!")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.i("Fragment", "onSaveInstanceState!")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.i("Fragment", "onViewStateRestored!")
    }

    override fun onStart() {
        super.onStart()

        Log.i("Fragment", "onStart!")
    }

    override fun onStop() {
        super.onStop()

        Log.i("Fragment", "onStop!")
    }
}