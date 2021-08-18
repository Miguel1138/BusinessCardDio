package com.miguelsantos.businesscard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miguelsantos.businesscard.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCardBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.addCloseImageBtn.setOnClickListener {
            finish()
        }
    }
}