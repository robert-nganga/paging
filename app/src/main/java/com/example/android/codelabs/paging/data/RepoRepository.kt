package com.example.android.codelabs.paging.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.android.codelabs.paging.api.GithubService
import com.example.android.codelabs.paging.model.Repo
import kotlinx.coroutines.flow.Flow


private const val NETWORK_PAGE_SIZE = 20
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

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}