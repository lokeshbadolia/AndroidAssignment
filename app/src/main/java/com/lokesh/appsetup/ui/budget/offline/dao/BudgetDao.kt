package com.lokesh.appsetup.ui.budget.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity

@Dao
interface BudgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExpense(expense: ExpenseEntity)

    @Query("Select * from ExpenseEntity order by id")
    fun fetchAllExpenses() : List<ExpenseEntity>
}