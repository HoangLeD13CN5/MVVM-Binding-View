package com.example.samplemvvm.view.adapter.hodler

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samplemvvm.BR
import com.example.samplemvvm.R
import com.example.samplemvvm.entity.Item
import com.example.samplemvvm.viewModel.RepoListViewModel
import kotlinx.android.synthetic.main.view_repo_list_item.view.*

/**
 * Created by HoangLM on 2020-04-02.
 */
class RepoListViewHolder constructor(private val dataBinding: ViewDataBinding, private val repoListViewModel: RepoListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {

    val avatarImage = itemView.item_avatar
    fun setup(itemData: Item) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        Glide.with(itemView)  //2
            .load(itemData.owner.avatar_url) //3
            .into(avatarImage) //8

        itemView.setOnClickListener {
            val bundle = bundleOf("url" to itemData.html_url)
            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment, bundle)
        }
    }
}