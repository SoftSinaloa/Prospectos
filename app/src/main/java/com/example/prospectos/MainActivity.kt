package com.example.prospectos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prospectos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCaptura.setOnClickListener{
            val intent = Intent(this, CapturaActivity::class.java)
            startActivity(intent)
        }

        binding.btnListado.setOnClickListener {
            val intent = Intent(this, ListadoActivity::class.java)
            startActivity(intent)
        }

        binding.btnEvaluacion.setOnClickListener {
            val intent = Intent(this, EvaluacionActivity::class.java)
            startActivity(intent)
        }
    }
}