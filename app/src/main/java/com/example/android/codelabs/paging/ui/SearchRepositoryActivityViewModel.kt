package com.example.android.codelabs.paging.ui

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.android.codelabs.paging.data.RepoRepository
import com.example.android.codelabs.paging.model.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SearchRepositoryActivityViewModel(private val repository: RepoRepository): ViewModel() {

    private val _search : MutableStateFlow<PagingData<Repo>>
        get() {
            TODO()
        }
    val search: Flow<PagingData<Repo>>
        get() {
            TODO()
        }
}