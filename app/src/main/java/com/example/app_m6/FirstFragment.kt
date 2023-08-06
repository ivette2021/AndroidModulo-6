package com.example.app_m6

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
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
            "task_database"
        )
       //  .allowMainThreadQueries()  //obligo a insertar en el hilo principal , pero no es bueno hacer esta practica
            .build()

        //le colocamos el 1 ejecutar hilo secuendario
        val newTask1 = Task(

            task = "Prueba BD 59",
            descripcion = "Prueba de insercion de datos",
            date = "18/07/2023"
        )
        GlobalScope.launch(Dispatchers.IO) {
            dataBase.getTaskDao().insertTask(newTask1) //estos son metodos ya definidos en Dao, si no esta definido alli no aparecera aca autocompletado
            Log.d("Resultado: OK", newTask1.toString())
        }
 //traemos todas las tareas
        GlobalScope.launch(Dispatchers.IO) {
            val allTask = dataBase.getTaskDao().getAlltask1()
            Log.d("Lista de tareas", allTask.toString())
        }
        //elimina una tarea
        GlobalScope.launch {
        //    dataBase.getTaskDao().deleteOneTask(newTask1)
        }

        //eliminar todo
        GlobalScope.launch {
        // dataBase.getTaskDao().deleteAlltask()
        }


    }
    //usamos la instancia de Dao

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}