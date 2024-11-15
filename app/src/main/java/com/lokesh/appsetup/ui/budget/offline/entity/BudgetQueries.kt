package com.lokesh.appsetup.ui.budget.offline.entity

import com.lokesh.appsetup.ui.budget.offline.dao.BudgetDao
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity
import com.lokesh.appsetup.util.prefs.PrefUtils

class BudgetQueries(private val dao: BudgetDao, private val pref: PrefUtils) {

    fun insertExpense(expense: ExpenseEntity) {
        dao.insertExpense(expense)
    }

    fun fetchAllExpenses() : List<ExpenseEntity> {
        return dao.fetchAllExpenses()
    }
}