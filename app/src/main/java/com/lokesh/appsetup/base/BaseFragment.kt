package com.lokesh.appsetup.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.lokesh.appsetup.util.datastore.DataStoreUtils
import com.lokesh.appsetup.util.NetworkManager
import com.lokesh.appsetup.util.prefs.PrefUtils
import com.lokesh.appsetup.util.globalLiveInternetCheck
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    @Inject
    lateinit var prefUtils: PrefUtils

    @Inject
    lateinit var dataStoreUtils: DataStoreUtils

    lateinit var binding: VB
    var networkManager: NetworkManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateLayout(inflater, container, false)
        initNetwork()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCreate()
    }

    abstract fun inflateLayout(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToContext: Boolean
    ): VB

    open fun initCreate() {
    }

    fun backPressHandle(viewLifeCycle: LifecycleOwner, function: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifeCycle,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    function.invoke()
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun initNetwork() {
        networkManager = NetworkManager(requireActivity())
        networkManager!!.register().observe(viewLifecycleOwner, networkObserver)
    }

    private val networkObserver = Observer<Boolean?> {
        it?.let {
            globalLiveInternetCheck.value = it
            NetworkManager.internetAvailable = it
            observeNetwork(it)
        }
    }

    open fun observeNetwork(isNetworkAvailable: Boolean) {}
}
