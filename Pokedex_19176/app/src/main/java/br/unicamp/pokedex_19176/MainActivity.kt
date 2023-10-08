package br.unicamp.pokedex_19176

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnFab = findViewById<FloatingActionButton>(R.id.fabEntrar)

        btnFab.setOnClickListener{
            val intent = Intent(this, menuPrincipal::class.java)
            startActivity(intent)
        }
    }



}