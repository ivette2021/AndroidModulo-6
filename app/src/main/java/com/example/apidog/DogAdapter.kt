package com.example.apidog

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apidog.Model.Local.Entities.DogEntity
import com.example.apidog.ViewModel.DogViewModel
import com.example.apidog.databinding.DogItemBinding
import com.example.apidog.databinding.ItemCardBinding

class DogAdapter: RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    // referencia para el adapter
    private var listBinding= listOf<DogEntity>()
    private  val selectedDog= MutableLiveData<DogEntity>()

    fun update (list:List<DogEntity>){
        listBinding= list
        notifyDataSetChanged()
    }

    // función para seleccionar
    fun selectedDog(): LiveData<DogEntity> = selectedDog

    inner class DogViewHolder(private  val mbinding : DogItemBinding):
        RecyclerView.ViewHolder(mbinding.root), View.OnClickListener{

        fun bind(dog: DogEntity){ //los id de los xml con guion bajo se escriben aqui como camelcase

            Glide.with(mbinding.imageView2).load(dog.message).centerCrop().into(mbinding.imageView2)
//            mbinding.tvNombre.text= dog.message
            //mbinding.tvPlantado.text= dog.tipo
           //mbinding.tvUltimoRiego.text= dog.descripcion
//            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View) {
            // referencia a la selección
            selectedDog.value= listBinding[adapterPosition]
            Log.d("ONCLICK",adapterPosition.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(DogItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listBinding.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog= listBinding[position]
        holder.bind(dog)
    }


}
