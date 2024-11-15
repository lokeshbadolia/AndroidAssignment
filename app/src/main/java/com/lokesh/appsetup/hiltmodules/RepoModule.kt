package com.lokesh.appsetup.hiltmodules

import com.lokesh.appsetup.ui.budget.repo.BudgetRepo
import com.lokesh.appsetup.ui.budget.repo.BudgetRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    fun providesBudgetRepository(budgetRepo: BudgetRepoImp): BudgetRepo
}
