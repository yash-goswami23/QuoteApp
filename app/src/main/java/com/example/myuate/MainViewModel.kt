package com.example.myuate

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import java.io.IOException

class MainViewModel(val context: Context): ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0
    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {

        val inputSteam = context.assets.open("quotes.json").bufferedReader().use { it.readText() }
        val listQoutesType = object : TypeToken<Array<Quote>>() {}.type
        return Gson().fromJson(inputSteam,listQoutesType)

    }

    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index]
    fun prvQuote() = quoteList[--index]
}