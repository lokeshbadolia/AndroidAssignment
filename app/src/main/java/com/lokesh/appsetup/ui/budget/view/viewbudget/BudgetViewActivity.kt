package com.lokesh.appsetup.ui.budget.view.viewbudget

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lokesh.appsetup.base.BaseActivity
import com.lokesh.appsetup.databinding.ActivityBudgetViewBinding
import com.lokesh.appsetup.ui.budget.adapter.ExpenseTypeAdapter
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity
import com.lokesh.appsetup.ui.budget.viewmodel.BudgetViewmodel
import com.lokesh.appsetup.util.onClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetViewActivity : BaseActivity<ActivityBudgetViewBinding>() {

    private val vm by viewModels<BudgetViewmodel>()
    private lateinit var listAdapter: ExpenseTypeAdapter

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityBudgetViewBinding =
        ActivityBudgetViewBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecycler()
        fetchData()
        buttonInit()
    }

    private fun setupRecycler() {
        listAdapter = ExpenseTypeAdapter(this)
        binding.budgetRecyclerView.apply {
            adapter = listAdapter
        }
    }

    private fun fetchData() {
        vm.getExpenseFromOffline()
        vm.list.observe(this, listObserver)
    }

    private fun buttonInit() {
        binding.goBack onClick {
            finish()
        }
    }

    private val listObserver = Observer<ArrayList<ExpenseEntity>> {
        if (it.isNotEmpty()) listAdapter.fillList(it)
    }
}