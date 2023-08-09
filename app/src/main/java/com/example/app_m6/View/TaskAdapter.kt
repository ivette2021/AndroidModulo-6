package com.example.app_m6.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.app_m6.Model.model.Task
import com.example.app_m6.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskVH>() {

//  1-  clase interna que representara la comunicacion con el xml
//agregamos viewholder "VH" tenemos que inflar la clase xml item layout
//tenemos que heredar de la clase recyclerView junto viewholder
    // hacer un click en el elemento agregamois onclicklistener

    // lista de datos
    private var mlistTaskEntity = listOf<Task>()


/*****************************  PARA SELECCIONAR *****************************************************/
    //un elemento seleccionado
    private val selectedTaskEntity = MutableLiveData<Task>()


 // 4- funcion para seleccionar
    fun selectedItem():LiveData<Task> =selectedTaskEntity

/*************************************************************************************************************/

//  3- hacemos que la lista se vaya actusalizando  a medida que vamos añadiendo datosmientras ingresamos datos

fun update(listTask: List<Task>){
        mlistTaskEntity= listTask
        notifyDataSetChanged()
}



    inner class TaskVH(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener { //se implementa el miembro

        fun bind(task: Task) {
            binding.tvTitle.text = task.title //ojo que los nombres de los id de xml no son iguales a como los llamamos aqui
            binding.tvDescription.text = task.descripcion
            binding.tvDate.text = task.date
            binding.cbState.isChecked = task.state
            binding.tvPrioridad.text = task.priority.toString() //se le agrega el to string porque es un Int

            //activar el elemento click dentro de la vista
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) { //implementando la interfaz view
               selectedTaskEntity.value= mlistTaskEntity[adapterPosition]
        }

//  2-
    }
    //inflando la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
       return TaskVH( TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = mlistTaskEntity.size //lleva la cuenta de nuestro listado de tarea

    override fun onBindViewHolder(holder: TaskVH, position: Int) {  //le entrega una posicion a cada tarea que se va a guardar
        val Task = mlistTaskEntity[position]
        holder.bind(Task)
    }
}