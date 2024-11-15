package com.lokesh.appsetup.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.lokesh.appsetup.util.datastore.DataStoreUtils
import com.lokesh.appsetup.util.prefs.PrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var prefUtils: PrefUtils

    @Inject
    lateinit var dataStoreUtils: DataStoreUtils
}
