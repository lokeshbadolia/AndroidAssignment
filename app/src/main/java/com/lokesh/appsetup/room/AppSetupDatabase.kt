package com.lokesh.appsetup.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lokesh.appsetup.ui.budget.offline.dao.BudgetDao
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity

@Database(
    entities = [ExpenseEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppSetupDatabase : RoomDatabase() {
    abstract val budgetDao: BudgetDao
}
