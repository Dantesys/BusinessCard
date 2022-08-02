package com.dantesys.bcchallenge.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dantesys.bcchallenge.App
import com.dantesys.bcchallenge.R
import com.dantesys.bcchallenge.databinding.ActivityMainBinding
import com.dantesys.bcchallenge.ui.adapter.CardAdapter
import com.dantesys.bcchallenge.util.Image


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val cardViewModel:CardViewModel by viewModels {
        CardViewModelFactory((application as App).cardRepository)
    }
    private val adapter by lazy {
        CardAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.cardList.adapter = adapter
        getAllCard()
        listeners()
    }
    private fun listeners(){
        binding.add.setOnClickListener {
            val intent = Intent(this@MainActivity,AddActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {
            Image.share(this,it)
        }
    }

    private fun getAllCard(){
        cardViewModel.getAll().observe(this) { cards ->
            adapter.submitList(cards)
        }
    }
}
