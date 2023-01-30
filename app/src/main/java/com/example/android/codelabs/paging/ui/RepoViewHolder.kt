package com.example.android.codelabs.paging.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.android.codelabs.paging.R
import com.example.android.codelabs.paging.databinding.RepoViewItemBinding
import com.example.android.codelabs.paging.model.Repo

class RepoViewHolder(private val binding: RepoViewItemBinding): RecyclerView.ViewHolder(binding.root) {

    private var repo: Repo? = null
    init {
        binding.root.setOnClickListener {
            repo?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                binding.root.context.startActivity(intent)
            }
        }
    }

    fun bindData(repo: Repo){
        this.repo = repo
        binding.apply {
            repoName.text = repo.fullName

            if (repo.description != null) {
                repoDescription.text = repo.description
            }

            repoStars.text = repo.stars.toString()
            repoForks.text = repo.forks.toString()

            // if the language is missing, hide the label and the value
            var languageVisibility = View.GONE
            if (!repo.language.isNullOrEmpty()) {
                val resources = binding.root.context.resources
                repoLanguage.text = resources.getString(R.string.language, repo.language)
                languageVisibility = View.VISIBLE
            }
            repoLanguage.visibility = languageVisibility
        }
    }
}
