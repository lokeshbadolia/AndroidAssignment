package com.lokesh.appsetup.ui.budget.repo

import com.lokesh.appsetup.ui.budget.offline.entity.BudgetQueries
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity
import javax.inject.Inject

interface BudgetRepo {
    fun insertExpense(expense: ExpenseEntity)
    fun getExpense() : List<ExpenseEntity>
}

class BudgetRepoImp @Inject constructor(
    private val entries: BudgetQueries
) : BudgetRepo {

    override fun insertExpense(expense: ExpenseEntity) {
        entries.insertExpense(expense)
    }

    override fun getExpense(): List<ExpenseEntity> {
        return entries.fetchAllExpenses()
    }
}