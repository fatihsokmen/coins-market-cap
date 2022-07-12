package com.github.fatihsokmen.coinsmarketcap.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.fatihsokmen.coinsmarketcap.databinding.ViewCryptoAssetBinding

class CryptoAssetsAdapter(
    private val callback: (asset: CryptoAssetDomain) -> Unit
) : PagingDataAdapter<CryptoAssetDomain, CryptoAssetsAdapter.AssetsViewHolder>(AssetsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AssetsViewHolder(
            ViewCryptoAssetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback
        )

    override fun onBindViewHolder(holder: AssetsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class AssetsViewHolder(
        private val binding: ViewCryptoAssetBinding,
        private val callback: (asset: CryptoAssetDomain) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(asset: CryptoAssetDomain) = with(binding) {
            assetName.text = asset.name
            assetRank.text = asset.rank
            assetSymbol.text = asset.symbol
            assetPrice.text = asset.price
            Glide.with(root.context).load(asset.icon).into(binding.assetIcon)
            root.setOnClickListener { callback(asset) }
        }
    }

    object AssetsComparator : DiffUtil.ItemCallback<CryptoAssetDomain>() {

        override fun areItemsTheSame(oldItem: CryptoAssetDomain, newItem: CryptoAssetDomain) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CryptoAssetDomain, newItem: CryptoAssetDomain) =
            oldItem == newItem
    }
}