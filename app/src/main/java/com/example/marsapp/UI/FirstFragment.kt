package com.example.marsapp.UI

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
import com.example.marsapp.AdapterMars
import com.example.marsapp.R
import com.example.marsapp.ViewModel.MarsViewModel
import com.example.marsapp.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private lateinit var _binding : FragmentFirstBinding
    private val viewModel : MarsViewModel by activityViewModels()


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
        val adapter = AdapterMars()
        // buscar el recyclerView
        _binding.rvTerrains.adapter = adapter //enviando mi rv para inflar el elemento cargarlo con glide
        _binding.rvTerrains.layoutManager= GridLayoutManager(context,2) //explicamos como se mostrara los objeto: que traiga 2 elementos por cada fila

        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it.let{

                viewModel.selected(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

        })
//--------observar lo que esta en la base de datos con corrutinas--------------------
        viewModel.allTerrains.observe(viewLifecycleOwner, Observer {

            adapter.update(it)
            Log.d("Listado",it.toString())
        })



    }


}