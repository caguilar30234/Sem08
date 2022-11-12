package com.sem08

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sem08.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Objeto Firebase Authentication
    private lateinit var auth : FirebaseAuth

    //Pantalla xml (activity)
    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicializar Authentication
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Click registro
        binding.btRegistro.setOnClickListener(){
            registrar();
        }

        //click login
        binding.btLogin.setOnClickListener(){
            login();
        }
    }

    //Ejemplo: Si se va a retornar algo como un string: private fun registro : String(){}
    private fun registrar(){
        //Se pueden definir variables de dos maneras

        //1. Valor por acepción
        val email = binding.etCorreo.text.toString()
        //2. Valor con tipado
        val clave : String = binding.etClave.text.toString()

        //Registro usuario
        auth.createUserWithEmailAndPassword(email, clave).addOnCompleteListener(this)
        { task ->
            if(task.isSuccessful){
                val user = auth.currentUser
                cargarPantalla(user)
            }
            else{
                Toast.makeText(baseContext, "Falló ${task.exception.toString()}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun cargarPantalla(user : FirebaseUser?){
        if(user != null){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    private fun login(){

        //Leo el email y password de nuevo

        val email = binding.etCorreo.text.toString()
        val clave : String = binding.etClave.text.toString()

        //Inicio el proceso de Login
        auth.signInWithEmailAndPassword(email, clave).addOnCompleteListener{
            result ->
            if(result.isSuccessful){
                val user = auth.currentUser
                cargarPantalla(user)
            }else{
                //el metodo get text hace referencia al archivo string donde R es "resources"
                Toast.makeText(baseContext, getText(R.string.noLogin), Toast.LENGTH_LONG).show()
            }
        }
    }

    //Al iniciar la aplicación
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cargarPantalla(user)
    }
}