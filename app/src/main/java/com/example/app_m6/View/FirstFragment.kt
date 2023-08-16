package com.example.app_m6.View

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_m6.Model.model.Usuario
import com.example.app_m6.R
import com.example.app_m6.UsuarioAdapter
import com.example.app_m6.ViewModel.UsuarioVM
import com.example.app_m6.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var adapter: UsuarioAdapter
    private lateinit var usuariosList: List<Usuario>
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UsuarioVM by activityViewModels() // instancia de viewModel
    private var idUsuario: Int = 0
    private var UsuarioSelected: Usuario? = null
    private var usuarioSeleccionado: Usuario? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem().observe(viewLifecycleOwner) {
            it?.let { selectedUsuario ->

                binding.etUsuario.setText(selectedUsuario.usuario)
                binding.etNombre.setText(selectedUsuario.nombre)
                binding.etEdad.setText(selectedUsuario.edad.toString())
                idUsuario = selectedUsuario.id
                UsuarioSelected = selectedUsuario
            }
        }

        // button para añadir

        binding.btnsave.setOnClickListener {

            saveData()
            viewModel.selected(null)
           // findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.btnDelete.setOnClickListener {
            deleteData()
            viewModel.selected(null)
        }

        binding.btnShowAll.setOnClickListener {
            showAllData()
        }
    }
    // funcion para guardar tareas

    private fun saveData() {
        val usuario = binding.etUsuario.text.toString()
        val nombre = binding.etNombre.text.toString()
        val edad = binding.etEdad.text.toString().toInt()

        // SE VALIDAD LOS CAMPOS
        if (usuario.isEmpty() || nombre.isEmpty() || edad <= 0) {
            Toast.makeText(context, " Debe añadir los datos", Toast.LENGTH_LONG).show()
        } else {
            // SI NO HAY NADA CREA UNA NUEVA ACTIVIDAD
            // SI HAY ALGO EN LA BASE DE DATOS LA ACTUALIZA
            if (idUsuario == 0) {
                val newUsuario = Usuario(
                    usuario = usuario,
                    nombre = nombre,
                    edad = edad
                )
                viewModel.insertUsuario(newUsuario)
                Toast.makeText(context, " DATOS GUARDADOS CORRECTAMENTE", Toast.LENGTH_LONG).show()
            } else {
                val newUsuario1 = Usuario(
                    // debemos incluir el id que viene del
                    // observer para saber cual actualizar
                    id = idUsuario,
                    usuario = usuario,
                    nombre = nombre,
                    edad = edad
                )
                viewModel.updateUsuario(newUsuario1)
                Toast.makeText(context, " DATOS ACTUALIZADOS", Toast.LENGTH_LONG).show()
            }
        }
    }
   /* private fun deleteData() {
        if (UsuarioSelected != null) {
            viewModel.deleteUsuario(UsuarioSelected!!)
            Toast.makeText(context, " DATOS ELIMINADOS", Toast.LENGTH_LONG).show()
            binding.etUsuario.text.clear()
            binding.etNombre.text.clear()
            binding.etEdad.text.clear()
        } else {
            Toast.makeText(context, "No hay usuario seleccionado para eliminar", Toast.LENGTH_LONG).show()
        }
    }*/
    private fun deleteData() { //esta funcion esta sujeta a una variable que almacenara momentaneamente el click para posteriormente eliminar por el botno
        if (usuarioSeleccionado != null) {
            viewModel.deleteUsuario(usuarioSeleccionado!!)
            Toast.makeText(context, "DATOS ELIMINADOS", Toast.LENGTH_LONG).show()
            usuarioSeleccionado = null // Limpia la variable después de eliminar
            binding.etUsuario.text.clear()
            binding.etNombre.text.clear()
            binding.etEdad.text.clear()
        } else {
            Toast.makeText(context, "No hay usuario seleccionado para eliminar", Toast.LENGTH_LONG).show()
        }
    }
    private fun showAllData() {
        val usuariosLiveData = viewModel.retrieveAllUsuario()

        usuariosLiveData.observe(viewLifecycleOwner) { usuarios ->
            usuariosList = usuarios // Guarda la lista de usuarios

            binding.Rv.visibility = if (usuarios.isEmpty()) View.GONE else View.VISIBLE

            if (!::adapter.isInitialized) {
                val layoutManager = LinearLayoutManager(requireContext())
                binding.Rv.layoutManager = layoutManager

                adapter = UsuarioAdapter { usuario ->
                    usuarioSeleccionado = usuario // Almacena el usuario seleccionado
                   // viewModel.deleteUsuario(usuario)
                    Toast.makeText(context, "Usuario seleccionado", Toast.LENGTH_LONG).show()
                }
                binding.Rv.adapter = adapter
            }
            adapter.update(usuariosList)
        }
    }


    //----------------esto genera que se pueda borrar los datos guardados con solo clickear---------

    /*private fun showAllData() {
        val usuariosLiveData = viewModel.retrieveAllUsuario()

        // Observa los cambios en el LiveData
        usuariosLiveData.observe(viewLifecycleOwner) { usuarios ->
            // Cambia la visibilidad del RecyclerView
            binding.Rv.visibility = if (usuarios.isEmpty()) View.GONE else View.VISIBLE

            // Si hay datos, configura y muestra el RecyclerView
            if (usuarios.isNotEmpty()) {
                val layoutManager = LinearLayoutManager(requireContext())
                binding.Rv.layoutManager = layoutManager

                // Crea y configura el adaptador para el RecyclerView
                val adapter = UsuarioAdapter { usuario ->
                    viewModel.deleteUsuario(usuario)
                }
                adapter.update(usuarios)
                binding.Rv.adapter = adapter
            }
        }
    }*/

    //---------------------------------esto es con alert dialog no con recycler view------------------------

       /* // Observa los cambios en el LiveData  //esto es con alert dialog no con recycler view
        usuariosLiveData.observe(viewLifecycleOwner) { usuarios ->
            val stringBuilder = StringBuilder()

            for (usuario in usuarios) {
                stringBuilder.append("Usuario: ${usuario.usuario}, Nombre: ${usuario.nombre}, Edad: ${usuario.edad}\n")
            }
            binding.Rv.visibility = if (usuarios.isEmpty()) View.GONE else View.VISIBLE

            if (usuarios.isEmpty()) {
                stringBuilder.append("No hay datos guardados.")
            }

            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setTitle("Usuarios Guardados: ")
            dialogBuilder.setMessage(stringBuilder.toString())
            dialogBuilder.setPositiveButton("Cerrar", null)
            val dialog = dialogBuilder.create()
            dialog.show()
        }*/
//----------------------------------------------------------------------------------------------------------------------


    //usamos la instancia de Dao

    override fun onDestroyView() {
        super.onDestroyView()
        // actualizar view model para destruir
        viewModel.selected(null)
    }
}