package com.arbems.viewmodelsharedatabetweenfragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_master.*

class MasterFragment : Fragment() {

    /**
     *  Returns a property delegate to access parent activity's ViewModel, if [factoryProducer] is specified then [ViewModelProvider.Factory] returned by it will be used to create [ViewModel] first time.
     *  Otherwise, the activity's [androidx.activity.ComponentActivity.getDefaultViewModelProviderFactory](default factory) will be used.
     */
    private val viewModel: SharedViewModel by activityViewModels() // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.select(isChecked)
        }
    }
}