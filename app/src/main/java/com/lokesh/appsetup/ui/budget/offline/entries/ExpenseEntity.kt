package com.lokesh.appsetup.ui.budget.offline.entries

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var type: String,
    var amount : Double,
): Parcelable