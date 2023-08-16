package com.example.app_m6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_m6.Model.model.Usuario
import com.example.app_m6.databinding.ItemUsuarioBinding

class UsuarioAdapter(private val onItemClick: (Usuario) -> Unit) : RecyclerView.Adapter<UsuarioAdapter.UsuarioVH>() {
    private val usuarios: MutableList<Usuario> = mutableListOf()

    fun update(list: List<Usuario>) {
        usuarios.clear()
        usuarios.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUsuarioBinding.inflate(inflater, parent, false)
        return UsuarioVH(binding)
    }

    override fun onBindViewHolder(holder: UsuarioVH, position: Int) {
        val usuario = usuarios[position]
        holder.bind(usuario)
    }

    override fun getItemCount(): Int = usuarios.size

    inner class UsuarioVH(private val binding: ItemUsuarioBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val usuario = usuarios[position]
                    onItemClick(usuario)
                }
            }
        }

        fun bind(usuario: Usuario) {
            binding.tvUsuario.text = usuario.usuario
            binding.tvNombre.text = usuario.nombre
            binding.tvEdad.text = usuario.edad.toString()

            }
        }
    }

