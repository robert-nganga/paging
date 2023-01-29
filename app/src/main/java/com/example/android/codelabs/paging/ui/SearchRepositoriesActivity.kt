package com.example.android.codelabs.paging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.codelabs.paging.databinding.ActivitySearchRepositoriesBinding

class SearchRepositoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}