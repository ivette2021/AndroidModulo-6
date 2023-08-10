package com.example.marsapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marsapp.Model.Remote.MarsRealState
import com.example.marsapp.databinding.MarsItermBinding

class AdapterMars : RecyclerView.Adapter<AdapterMars.MarsVH>() {


    private var listMarsItem = listOf<MarsRealState>()   //referencia a la lista de los terrenos



    val selectedTerrain = MutableLiveData<MarsRealState>()   // Para seleccinar un elemento, tenemos una variables que tiene un listado de nuestra clase


    fun selectedItem(): LiveData<MarsRealState> = selectedTerrain    //funcion para seleccionar


    fun update(list: List<MarsRealState>) {      //funcion para actualizar el RecyclerView
        listMarsItem = list
        notifyDataSetChanged()   //notifica cuando ocurra un cambio en la lista, insercion o actualizacion
    }


    //1 INNER CLASS referenciamos a la clase de elemento xml que tiene los items,clase de rv y viewholder

    inner class MarsVH(private val binding: MarsItermBinding) :
        RecyclerView.ViewHolder(binding.root),

        View.OnClickListener { //hacemos clickeable el elemento
        fun bin(mars: MarsRealState) {

            // ACA glide no olvidar dependencias , para que cargue las imagenes
            Glide.with(binding.imageView2).load(mars.img_src).centerCrop()
                .into(binding.imageView2) //usamos los contenedores de imagen de xml
            // Activar el Clik
            itemView.setOnClickListener(this) //captamos el click que estamos haciendo sobre la imagen

        }

        override fun onClick(v: View?) {
            // para seleccionar
            selectedTerrain.value = listMarsItem[adapterPosition] //cuando seleccione obtenemos un valor recibimos la poiscion por medio de indices
        }

    }


    //_______________________________Estos Métodos se implementan de Adapter Mars-------------------------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsVH { //
        return MarsVH(MarsItermBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: MarsVH,
        position: Int
    ) { //obtiene la posicion de los elementos
        val terrain = listMarsItem[position]
        holder.bin(terrain)
    }

    override fun getItemCount(): Int =
        listMarsItem.size //retorna el listado de los elementos
}