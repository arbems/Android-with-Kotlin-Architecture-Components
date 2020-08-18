package com.arbems.viewbinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arbems.viewbinding.databinding.FragmentABinding

class AFragment : Fragment() {

    private var _binding: FragmentABinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentABinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.name.text = "Alberto Moreno"

        binding.button.setOnClickListener {
            binding.name.text = "Carlos Garcia"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}