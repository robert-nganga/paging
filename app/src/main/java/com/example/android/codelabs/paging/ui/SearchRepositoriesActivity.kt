package com.example.android.codelabs.paging.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.codelabs.paging.databinding.ActivitySearchRepositoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchRepositoriesActivity : AppCompatActivity() {

    private val viewModel: SearchRepositoryActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}