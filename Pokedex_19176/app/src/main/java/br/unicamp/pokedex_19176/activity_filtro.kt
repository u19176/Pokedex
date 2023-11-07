package br.unicamp.pokedex_19176

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class activity_filtro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filtro)

        val drpTipos1 = findViewById<Spinner>(R.id.drpTipo1)
        val drpTipos2 = findViewById<Spinner>(R.id.drpTipo2)
        val txtDescFiltro = findViewById<EditText>(R.id.txtFiltroDescricao)

        val btnFiltrar = findViewById<Button>(R.id.btnFiltrar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarFiltro)

        val adapter = ArrayAdapter.createFromResource(this, R.array.tipos,android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        drpTipos1.setAdapter(adapter)
        drpTipos2.setAdapter(adapter)

        btnVoltar.setOnClickListener()
        {
            val intent = Intent(this, menuPrincipal::class.java)
            startActivity(intent)
        }

        btnFiltrar.setOnClickListener {
            val filterType1 = drpTipos1.selectedItem.toString()
            val filterType2 = drpTipos2.selectedItem.toString()
            val filterDescription = txtDescFiltro.text.toString()

            val intent = Intent(this, menuPrincipal::class.java)
            if(filterType1 != "" || filterType2 != "" || !filterDescription.isNullOrEmpty()) {
                intent.putExtra("filterTipo1", filterType1)
                intent.putExtra("filterTipo2", filterType2)
                intent.putExtra("filterDescricao", filterDescription)
            }
            startActivity(intent)

        }

    }
}