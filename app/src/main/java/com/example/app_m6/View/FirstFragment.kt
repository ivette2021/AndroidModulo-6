package com.example.app_m6.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_m6.R
import com.example.app_m6.ViewModel.TaskViewModel
import com.example.app_m6.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    //private lateinit var itemViewModel: ItemViewModel
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by activityViewModels() // instancia de viewModel---------
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  val newTask = Task(  //insercion de tareas de repiten tal cual lo unico que las diferencias son el id

            title = "BD59",
            descripcion = "Prueba 059",
            date = "20/07/2023",
            priority = 7,
            state = true
        )

        viewModel.insertTask(newTask)*/

        //instanciar nuestro adaptador
        val adapter = TaskAdapter()
        binding.rvTask.adapter = adapter //ingresamos en recyclerview first fragment
        binding.rvTask.layoutManager = LinearLayoutManager(context)

        binding.rvTask.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL //decoracion que nos da una linea divisoria entre cada elemento
            )
        )

        // button para ir y agregar una nueva tarea

        binding.fab2.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }

        // cargar las tareas al RecyclerView
        viewModel.allTask.observe(viewLifecycleOwner, {
            it?.let {
                adapter.update(it)
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner, {
            it?.let {
                Log.d("Item Selected", it.title) //cambiamos title por id.toString
                viewModel.selected(it) //viewmodel espera que le pasemos una tarea
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment) //pasamos del primer fragmento al segundo fragmento
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}