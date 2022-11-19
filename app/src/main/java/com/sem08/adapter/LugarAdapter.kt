package com.sem08.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sem08.ui.home.HomeFragmentDirections
import com.sem08.databinding.LugarFilaBinding
import com.sem08.model.Lugar

class LugarAdapter : RecyclerView.Adapter<LugarAdapter.LugarViewholder>() {
    //lista de lugares
    private var listaLugares = emptyList<Lugar>()

    fun setLugares(lugares: List<Lugar>) {
        this.listaLugares = lugares
        notifyDataSetChanged() //Provoca que se redibuje la lista
    }

    inner class LugarViewholder(private val itemBinding: LugarFilaBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun dibuja(lugar: Lugar){
            itemBinding.tvNombre.text = lugar.nombre
            itemBinding.tvCorreo.text = lugar.correo
            itemBinding.tvTelefono.text = lugar.telefono
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewholder {
        val itemBinding = LugarFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LugarViewholder(itemBinding)
    }

    override fun onBindViewHolder(holder: LugarViewholder, position: Int) {
        val lugar = listaLugares[position]
        holder.dibuja(lugar)
    }

    override fun getItemCount(): Int {
        return listaLugares.size
    }

}