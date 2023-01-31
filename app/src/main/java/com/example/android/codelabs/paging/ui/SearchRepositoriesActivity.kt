package com.example.android.codelabs.paging.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.codelabs.paging.databinding.ActivitySearchRepositoriesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchRepositoriesActivity : AppCompatActivity() {

    private val viewModel: SearchRepositoryActivityViewModel by viewModels()

    private lateinit var reposAdapter: ReposAdapter

    private var _binding : ActivitySearchRepositoriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchRepositoriesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        reposAdapter = ReposAdapter()
        binding.list.adapter = reposAdapter

        var job: Job? = null
        binding.searchRepo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //Creates a simple delay so that the user can finish typing
                job?.cancel()
                job = MainScope().launch {
                    delay(500L)
                    // get the recent text
                    val recentText = s.toString()
                    Log.i("SearchForRepo", recentText)
                    if (recentText.isNotEmpty()){
                        searchRepositories(recentText)
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

//        binding.searchRepo.setOnEditorActionListener { _, action, _ ->
//            if (action == EditorInfo.IME_ACTION_GO){
//                val query = binding.searchRepo.text.toString()
//                Log.i("SearchForRep", query)
//
//                searchRepositories(query)
//                true
//            }else{
//                false
//            }
//        }
    }

    private fun searchRepositories(query: String) {
        lifecycleScope.launch {
            viewModel.searchQuery(query).collect{
                reposAdapter.submitData(it)
            }
        }
    }

}