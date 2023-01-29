package com.example.android.codelabs.paging.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.codelabs.paging.api.GithubService
import com.example.android.codelabs.paging.model.Repo
import okio.IOException
import retrofit2.HttpException


private const val GITHUB_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 20
class RepoPagingSource(private val api: GithubService, private val query: String): PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        // Start paging with the STARTING_KEY if this is the first load
        val pageIndex = params.key ?: GITHUB_STARTING_PAGE_INDEX
        val query = query
        return try {
            val result = api.searchRepos(query, pageIndex, params.loadSize)
            val repos = result.items
            val nextKey = if (repos.isEmpty()){
                null
            }else{
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (pageIndex == GITHUB_STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = nextKey
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}