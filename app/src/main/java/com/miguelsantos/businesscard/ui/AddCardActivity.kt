package com.miguelsantos.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.miguelsantos.businesscard.App
import com.miguelsantos.businesscard.R
import com.miguelsantos.businesscard.data.BusinessCard
import com.miguelsantos.businesscard.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCardBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.addCloseImageBtn.setOnClickListener {
            finish()
        }

        binding.addBtnCreateCard.setOnClickListener {
            val newCard = BusinessCard(
                name = binding.addTxtInputName.editText?.text.toString(),
                telephone = binding.addTxtInputTelephone.editText?.text.toString(),
                email = binding.addTxtInputEmail.editText?.text.toString(),
                company = binding.addTxtInputCompany.editText?.text.toString(),
                // TODO Usar sealed class ou enum para pegar o valor das cores e trocar input por um spinner.
                // TODO Crash ao tentar criar cartão com campos em branco.
                backgroundColor = binding.addTxtInputColor.editText?.text.toString()
            )
            viewModel.insertCard(newCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_LONG).show()
            finish()
        }
    }
}