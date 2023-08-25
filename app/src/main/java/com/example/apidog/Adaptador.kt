package com.example.apidog

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.apidog.Model.Local.Entities.DogEntity
import com.example.apidog.databinding.ItemCardBinding

class Adaptador: RecyclerView.Adapter<Adaptador.BreedViewHolder>() {
// referencia para el adapter
private var listBinding= listOf<DogEntity>()
private  val selectedDog= MutableLiveData<DogEntity>()

fun update (list:List<DogEntity>){
    listBinding= list
    notifyDataSetChanged()
}

// función para seleccionar
fun selectedDog(): LiveData<DogEntity> = selectedDog

inner class BreedViewHolder(private  val mbinding : ItemCardBinding):
    RecyclerView.ViewHolder(mbinding.root), View.OnClickListener{

    fun bind(dog: DogEntity){ //los id de los xml con guion bajo se escriben aqui como camelcase

        mbinding.tvNombre.text= dog.status

        itemView.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        // referencia a la selección
        selectedDog.value= listBinding[adapterPosition]
        Log.d("ONCLICK",adapterPosition.toString())
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
    return BreedViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.context)))
}

override fun getItemCount() = listBinding.size

override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
    val dog= listBinding[position]
    holder.bind(dog)
}


}
