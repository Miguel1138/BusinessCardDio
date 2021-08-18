package com.miguelsantos.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miguelsantos.businesscard.data.BusinessCard
import com.miguelsantos.businesscard.data.BusinessCardRepository

class MainViewModel(
    private val repository: BusinessCardRepository
) : ViewModel() {

    fun insertCard(card: BusinessCard) {
        repository.insertCard(card)
    }

    fun getAll(): LiveData<List<BusinessCard>> = repository.getAll()
}

class MainViewModelFactory(private val repository: BusinessCardRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown model class")
    }
}