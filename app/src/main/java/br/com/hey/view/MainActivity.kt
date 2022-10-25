package br.com.hey.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.hey.R
import br.com.hey.network.apiService

class MainActivity : AppCompatActivity() {

    companion object {
        val apiService by lazy { apiService() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}