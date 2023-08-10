package com.example.marsapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marsapp.R
import com.example.marsapp.ViewModel.MarsViewModel
import com.example.marsapp.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var _binding: FragmentSecondBinding
    private val viewModel : MarsViewModel by activityViewModels() // Se instancia el ViewModel
    var idMars: String= ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMars = it.getString("id","") // Se obtiene el valor del argumento "id" si existe.
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

        // Se observa el ViewModel para actualizar la interfaz cuando cambia.
        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                Glide.with(_binding.ivTerrain)
                    .load(it.img_src).centerCrop().into(_binding.ivTerrain) // Se carga una imagen usando Glide
                _binding.tvPrice.text= it.price.toString() // Se establece el texto del precio
                _binding.tvType.text = it.type // Se establece el texto del tipo
            }
        })
      // Se configura un clic en el botón de retorno para navegar de nuevo al primer fragmento.
        _binding.btnReturn.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment) // Se navega utilizando el NavController
        }

    }


}