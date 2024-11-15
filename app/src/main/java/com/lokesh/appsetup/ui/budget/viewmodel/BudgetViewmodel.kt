package com.lokesh.appsetup.ui.budget.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lokesh.appsetup.base.BaseViewModel
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity
import com.lokesh.appsetup.ui.budget.repo.BudgetRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BudgetViewmodel @Inject constructor(app: Application, private val repo: BudgetRepo) :
    BaseViewModel(app) {

        var selectedExp = ""

        val expenseType by lazy {
            return@lazy arrayListOf<String>(
                "Select Expense Type",
                "Entertainment",
                "Food",
                "Shopping",
                "Dine Out",
                "Others"
            )
        }

    fun addToOffline(expenseEntity: ExpenseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertExpense(expenseEntity)
        }
    }

    private val _list = MutableLiveData<ArrayList<ExpenseEntity>>()
    val list: LiveData<ArrayList<ExpenseEntity>> = _list

    fun getExpenseFromOffline() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = arrayListOf<ExpenseEntity>()
            list.addAll(repo.getExpense())
            viewModelScope.launch(Dispatchers.Main) {
                _list.value = list
            }
        }
    }
}