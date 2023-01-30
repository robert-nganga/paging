package com.example.android.codelabs.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android.codelabs.paging.data.RepoRepository
import com.example.android.codelabs.paging.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchRepositoryActivityViewModel @Inject constructor(
        private val repository: RepoRepository): ViewModel() {



    fun searchQuery(query: String) = repository.getSearchResultsStream(query).cachedIn(viewModelScope)
}