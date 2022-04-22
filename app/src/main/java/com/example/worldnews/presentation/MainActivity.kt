package com.example.worldnews.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.worldnews.R
import com.example.worldnews.databinding.ActivityMainBinding
import javax.xml.datatype.DatatypeFactory.newInstance

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWorldNewsFragment()
    }

    private fun initWorldNewsFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.world_news_container, WorldNewsFragment.newInstance())
            .commit()
    }
}