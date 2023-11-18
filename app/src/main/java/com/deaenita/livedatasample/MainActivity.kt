package com.deaenita.livedatasample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.deaenita.livedatasample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel =
            ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getDetik().observe(this, Observer {
            binding.tvWaktu.text = it.toString()
        })
        viewModel.selesai.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Selesai",

                    Toast.LENGTH_SHORT).show()

            }
        })
        binding.btnMulai.setOnClickListener{
            if(binding.etWaktu.text.isEmpty() ||

                binding.etWaktu.text.toString()=="0"){

                Toast.makeText(this, "Invalid Number",

                    Toast.LENGTH_SHORT).show()
            }else {
                viewModel.waktu.value =
                    binding.etWaktu.text.toString().toLong()
                viewModel.startTimer()
            }
        }
        binding.btnBerhenti.setOnClickListener {
            viewModel.stopTimer()
        }
        binding.btnReset.setOnClickListener {
            viewModel.stopTimer()
            binding.tvWaktu.text = "0"
        }
    }
}
