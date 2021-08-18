package com.miguelsantos.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.miguelsantos.businesscard.App
import com.miguelsantos.businesscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getAllCards()
        setListeners()
    }

    private fun setListeners() {
        binding.mainFabAddCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }

        binding.mainRecyclerCardList.adapter = adapter
        binding.mainRecyclerCardList.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun getAllCards() {
        viewModel.getAll().observe(this, { cardList -> adapter.submitList(cardList) })
    }

}