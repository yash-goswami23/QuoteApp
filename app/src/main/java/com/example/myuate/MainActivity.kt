package com.example.myuate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import org.w3c.dom.Text
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private var index = 0
    lateinit var mainViewModel: MainViewModel

    private val quoteText: TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor: TextView
        get() = findViewById(R.id.quoteAuthor)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory((application))).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

    }
    fun setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun onPrevious(view: View) {
        if(index == 0){
            Toast.makeText(this,"Previous are not available",Toast.LENGTH_SHORT).show()
        }else {
            index--
            setQuote(mainViewModel.prvQuote())
        }
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
        index++
    }
    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}