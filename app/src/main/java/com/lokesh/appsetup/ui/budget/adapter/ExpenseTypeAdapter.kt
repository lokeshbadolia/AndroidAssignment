package com.lokesh.appsetup.ui.budget.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lokesh.appsetup.databinding.LayoutBudgetRowBinding
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity

@SuppressLint("all")
class ExpenseTypeAdapter(
    private val context: Context
) : RecyclerView.Adapter<ExpenseTypeAdapter.ExpenseViewHolder>() {

    private val list = arrayListOf<ExpenseEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder = ExpenseViewHolder(
        LayoutBudgetRowBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            val expense = list[position]
            holder.binding.id.text = "#${expense.id}"
            holder.binding.type.text = expense.type
            holder.binding.amount.text = expense.amount.toString()
        }
    }

    fun fillList(expenseList : ArrayList<ExpenseEntity>) {
        list.clear()
        list.addAll(expenseList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ExpenseViewHolder(val binding: LayoutBudgetRowBinding) : RecyclerView.ViewHolder(binding.root)
}