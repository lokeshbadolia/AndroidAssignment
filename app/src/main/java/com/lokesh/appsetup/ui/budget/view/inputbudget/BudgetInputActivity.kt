package com.lokesh.appsetup.ui.budget.view.inputbudget

import android.content.Intent
import com.lokesh.appsetup.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.lokesh.appsetup.base.BaseActivity
import com.lokesh.appsetup.databinding.ActivityBudgetInputBinding
import com.lokesh.appsetup.ui.budget.offline.entries.ExpenseEntity
import com.lokesh.appsetup.ui.budget.view.viewbudget.BudgetViewActivity
import com.lokesh.appsetup.ui.budget.viewmodel.BudgetViewmodel
import com.lokesh.appsetup.util.hideKeyboard
import com.lokesh.appsetup.util.onClick
import com.lokesh.appsetup.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BudgetInputActivity : BaseActivity<ActivityBudgetInputBinding>() {

    private val vm by viewModels<BudgetViewmodel>()

    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityBudgetInputBinding =
        ActivityBudgetInputBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        fillSpinner()
        buttonInit()
    }

    private fun fillSpinner() {
        val listAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vm.expenseType)
        listAdapter.apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.expenseTypeSpinner.adapter = listAdapter
        binding.expenseTypeSpinner.onItemSelectedListener = spinnerListener
    }

    private val spinnerListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            vm.selectedExp = vm.expenseType[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }

    private fun buttonInit() {
        binding.submitBtn onClick :: checkAndSubmit
        binding.viewAllBtn onClick {
            val intent = Intent(this, BudgetViewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkAndSubmit() {
        val amount = binding.amountBlank.text.toString().trim()
        if(vm.selectedExp != "" && vm.selectedExp != vm.expenseType[0]) {
            if(amount != "") {
                vm.addToOffline(ExpenseEntity(type = vm.selectedExp, amount = amount.toDouble()))
                reset()
            } else showToast(getString(R.string.amount_warning))
        } else showToast(getString(R.string.expense_type_warning))
    }

    private fun reset() {
        binding.amountBlank.setText("")
        binding.expenseTypeSpinner.setSelection(0)
        hideKeyboard()
    }
}