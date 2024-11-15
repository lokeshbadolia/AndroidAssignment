package com.lokesh.appsetup.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.lokesh.appsetup.util.datastore.DataStoreUtils
import com.lokesh.appsetup.util.NetworkManager
import com.lokesh.appsetup.util.prefs.PrefUtils
import com.lokesh.appsetup.util.globalLiveInternetCheck
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    @Inject
    lateinit var prefUtils: PrefUtils

    @Inject
    lateinit var dataStoreUtils: DataStoreUtils

    lateinit var binding: VB

    private var networkManager: NetworkManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        initNetwork()
        initCreate()
    }

    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    private fun initNetwork() {
        networkManager = NetworkManager(this)
        networkManager!!.register().observe(this, networkObserver)
    }

    open fun initCreate() {}

    private val networkObserver = Observer<Boolean?> {
        it?.let {
            globalLiveInternetCheck.value = it
            NetworkManager.internetAvailable = it
            observeNetwork(it)
        }
    }

    open fun observeNetwork(isNetworkAvailable: Boolean) {}

    override fun onDestroy() {
        super.onDestroy()
        networkManager?.unRegister()
    }
}
