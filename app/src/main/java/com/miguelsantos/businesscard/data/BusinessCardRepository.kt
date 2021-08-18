package com.miguelsantos.businesscard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BusinessCardRepository(private val cardDao: BusinessCardDao) {

    fun insertCard(card: BusinessCard) = runBlocking {
        launch(Dispatchers.IO) {
            cardDao.insertCard(card)
        }
    }

    fun getAll() = cardDao.getAll()
}