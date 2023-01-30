package com.example.android.codelabs.paging.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.api.GithubService
import com.example.android.codelabs.paging.model.Repo
import com.example.android.codelabs.paging.util.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow



class RepoRepository(private val api: GithubService) {

    fun getSearchResultsStream(query: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {RepoPagingSource(api, query)}
        ).flow
    }

}