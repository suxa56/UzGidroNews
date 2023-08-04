package uz.uzgidro.ugenews.presentation.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.uzgidro.ugenews.databinding.CardNewsBinding
import uz.uzgidro.ugenews.domain.NewsModel

class NewsAdapter: ListAdapter<NewsModel, NewsViewHolder>(NewsDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = CardNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.cardTitle.text = item.title
    }
}