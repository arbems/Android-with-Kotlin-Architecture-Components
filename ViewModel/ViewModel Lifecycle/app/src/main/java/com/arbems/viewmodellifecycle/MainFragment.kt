package com.arbems.viewmodellifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private val lifecycleObserver = LifecycleObserver("MyFragment", lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("MyFragment", "onCreate!")
        Log.i("MyFragment", "${lifecycle.currentState}!")

        lifecycle.addObserver(lifecycleObserver)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.i("MyFragment", "onActivityCreated!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("MyFragment", "onCreateView!")
        Log.i("MyFragment", "${lifecycle.currentState}!")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("MyFragment", "onViewCreated!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.i("MyFragment", "onDestroyView!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i("MyFragment", "onDestroy!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("MyFragment", "onAttach!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onDetach() {
        super.onDetach()

        Log.i("MyFragment", "onDetach!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onPause() {
        super.onPause()

        Log.i("MyFragment", "onPause!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onResume() {
        super.onResume()

        Log.i("MyFragment", "onResume!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.i("MyFragment", "onSaveInstanceState!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.i("MyFragment", "onViewStateRestored!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onStart() {
        super.onStart()

        Log.i("MyFragment", "onStart!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }

    override fun onStop() {
        super.onStop()

        Log.i("MyFragment", "onStop!")
        Log.i("MyFragment", "${lifecycle.currentState}!")
    }
}