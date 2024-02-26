package com.ozdamarsevval.currencyappmvp.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozdamarsevval.currencyappmvp.databinding.ItemCurrencyBinding
import com.ozdamarsevval.currencyappmvp.model.currency.Result

class CurrencyAdapter(
    private val onCurrencyClick: (Result) -> Unit
) : ListAdapter<Result, CurrencyAdapter.ProductViewHolder>(ProductDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onCurrencyClick
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductViewHolder(
        private val binding: ItemCurrencyBinding,
        private val onCurrencyClick: (Result) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Result) {
            with(binding) {
                tvCurrency.text = "${currency.name} (${currency.code})"
                root.setOnClickListener {
                    onCurrencyClick(currency)
                }
            }
        }
    }

    class ProductDiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}