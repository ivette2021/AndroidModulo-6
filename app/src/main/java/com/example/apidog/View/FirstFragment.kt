package com.example.apidog.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidog.DogAdapter
import com.example.apidog.R
import com.example.apidog.ViewModel.DogViewModel
import com.example.apidog.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var _binding : FragmentFirstBinding
    private val viewModel : DogViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // instanciar el adapter
        val adapter = DogAdapter()
        // buscar el recyclerView
        _binding.rvCard.adapter = adapter //enviando mi rv para inflar el elemento cargarlo con glide
        _binding.rvCard.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getDogList().observe(viewLifecycleOwner, Observer  {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }
        })

        adapter.selectedDog().observe(viewLifecycleOwner, Observer {
            it?.let {
                // válidar si capta la seleccion
                Log.d("Seleccion de perro", it.message.toString())

            }
            val bundle = Bundle().apply {
                putString("dogMessage", it.status)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()

    }
}