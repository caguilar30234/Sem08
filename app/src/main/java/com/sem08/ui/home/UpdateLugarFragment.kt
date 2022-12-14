package com.sem08.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sem08.R
import com.sem08.databinding.FragmentUpdateLugarBinding
import com.sem08.model.Lugar
import com.sem08.viewModel.HomeViewModel

class UpdateLugarFragment : Fragment() {

    //Recuperar elementos enviados
    private val args by navArgs<UpdateLugarFragmentArgs>()

    private var _binding: FragmentUpdateLugarBinding? = null

    //!! cuando la variable no es nula
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentUpdateLugarBinding.inflate(inflater,container,false)

        //Cargar Lugar
        binding.etNombre.setText(args.lugarArg.nombre)
        binding.etCorreoLugar.setText(args.lugarArg.correo)
        binding.etTelefono.setText(args.lugarArg.telefono)
        binding.etWeb.setText(args.lugarArg.web)

        binding.btUpdateLugar.setOnClickListener{updateLugar()}
        binding.btDeleteLugar.setOnClickListener{ deleteLugar()}

        return binding.root
    }

    private fun updateLugar(){
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoLugar.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        if(nombre.isNotEmpty()){
            val lugar = Lugar(args.lugarArg.id,nombre,correo,telefono,web,args.lugarArg.rutaAudio, args.lugarArg.rutaImagen)
            homeViewModel.guardarLugar(lugar)
            Toast.makeText(requireContext(),getString(R.string.ms_UpdateLugar),Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)
        }else{
            Toast.makeText(requireContext(),getString(R.string.ms_FaltanValores),Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteLugar() {
        val nombre = binding.etNombre.text.toString()
        val correo = binding.etCorreoLugar.text.toString()
        val telefono = binding.etTelefono.text.toString()
        val web = binding.etWeb.text.toString()
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.msg_seguro_borrado)+"${args.lugarArg.nombre}?")
        builder.setNegativeButton(getString(R.string.msg_no)) {_,_ -> }
        builder.setPositiveButton(getString(R.string.msg_si)) { _, _ ->
            if(nombre.isNotEmpty()){
                val lugar = Lugar(args.lugarArg.id,nombre,correo,telefono,web,args.lugarArg.rutaAudio, args.lugarArg.rutaImagen)
            homeViewModel.eliminarLugar(lugar)
            Toast.makeText(requireContext(), getString(R.string.msg_lugar_deleted), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateLugarFragment_to_nav_home)
            }else{
                Toast.makeText(requireContext(),getString(R.string.ms_RemoveLugar),Toast.LENGTH_LONG).show()
            }
        }
        builder.create().show()
    }
}