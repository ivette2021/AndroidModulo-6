package com.example.app_m6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.app_m6.Model.Task
import com.example.app_m6.Model.TaskDataBase
import com.example.app_m6.Model.TaskDao
import com.example.app_m6.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        val dataBase = Room.databaseBuilder(
            requireContext().applicationContext,

            TaskDataBase::class.java,
            "task_database")
            .allowMainThreadQueries()  //obligo a insertar en el hilo principal , pero no es bueno hacer esta practica
            .build()

        val newTask = Task(

            task = "Prueba BD 59",
            descripcion = "Prueba de insercion de datos",
            date = "18/07/2023"
        )

        dataBase.getTaskDao().insertTask(newTask)

    }





    // probaremos nuestro base de datos, agregamos nuestra tarea en base a los parametros de task


    //usamos la instancia de Dao








    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}