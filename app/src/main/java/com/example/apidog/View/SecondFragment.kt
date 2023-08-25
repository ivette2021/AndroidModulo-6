package com.example.apidog.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.apidog.DogAdapter
import com.example.apidog.R
import com.example.apidog.ViewModel.DogViewModel
import com.example.apidog.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var _binding: FragmentSecondBinding
    private val viewModel : DogViewModel by activityViewModels() // Se instancia el ViewModel
    var messageDog: String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            messageDog = it.getString("message","") // Se obtiene el valor del argumento "id" si existe.
        }
    }

    // creamos la vista del fragmento.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false) // Se infla el diseño de la vista
        return _binding.root  // Se devuelve la vista inflada

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DEBEMOS INTANCIAR ADAPTER

        val adapter = DogAdapter()
        _binding.rvDogs.adapter= adapter
        _binding.rvDogs.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewModel.getDogList().observe(viewLifecycleOwner, Observer {

            it?.let {
                Log.d("Listado", it.toString())
                adapter.update(it)
            }

        })

        // Se configura un clic en el botón de retorno para navegar de nuevo al primer fragmento.
        _binding.btnReturn.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment) // Se navega utilizando el NavController
        }
    }
}