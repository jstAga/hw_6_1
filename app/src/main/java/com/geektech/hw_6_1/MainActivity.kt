package com.geektech.hw_6_1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.geektech.hw_6_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        initLauncher()
        initClickers()
    }

    private fun setData() {
        binding.etText.setText(intent.getStringExtra(Key.DATA_KEY))
    }

    private fun initLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    binding.etText.setText(result.data?.getStringExtra(Key.DATA_KEY))
                }
            }
    }

    private fun initClickers() {
        with(binding) {
            btnPass.setOnClickListener {
                if (etText.text.isNotEmpty()) {
                    passData()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Edit text is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun passData() {
        val data = Intent(this@MainActivity, SecondActivity::class.java)
        data.putExtra(Key.DATA_KEY, binding.etText.text.toString())
        resultLauncher.launch(data)
    }
}